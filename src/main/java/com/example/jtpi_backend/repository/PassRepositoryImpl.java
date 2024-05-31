package com.example.jtpi_backend.repository;

import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.domain.entity.PassInformation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.Query;
@Primary
@Repository
public class PassRepositoryImpl implements PassRepositoryCustom{

    private final PassRepository passRepository;
    public PassRepositoryImpl(@Lazy PassRepository passRepository) {

        this.passRepository = passRepository;

    }
    @PersistenceContext
    private EntityManager entityManager;

    //신규
    @Override
    public List<PassInformation> findSlideShowNewPasses() {
        String sql = "SELECT * FROM PassInformation ORDER BY create_at DESC LIMIT 4";
        Query query = entityManager.createNativeQuery(sql, PassInformation.class);
        return query.getResultList();
    }

    //추천
    @Override
    public List<PassInformation> findSlideShowRecommendedPasses() {
        String sql = "SELECT pi.* FROM RecommendedPass rp " +
                "JOIN PassInformation pi ON rp.recommendedPassID = pi.passID " +
                "ORDER BY RAND() " +
                "LIMIT 4";
        Query query = entityManager.createNativeQuery(sql, PassInformation.class);
        return query.getResultList();
    }
    //검색
    @Override
    public List<PassInformation> searchPassesByCriteria(
            String searchQuery,
            String departureCity,
            String arrivalCity,
            String transportType,
            String cityNames,
            Integer duration,
            Integer minPrice,
            Integer maxPrice
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PassInformation> cq = cb.createQuery(PassInformation.class);
        Root<PassInformation> pass = cq.from(PassInformation.class);

        List<Predicate> predicates = new ArrayList<>();

        // Convert '0' to null for all search parameters
        if (searchQuery != null && searchQuery.equals("0")) {
            searchQuery = null;
        }
        if (departureCity != null && departureCity.equals("0")) {
            departureCity = null;
        }
        if (arrivalCity != null && arrivalCity.equals("0")) {
            arrivalCity = null;
        }
        if (transportType != null && transportType.equals("0")) {
            transportType = null;
        }
        if (cityNames != null && cityNames.equals("0")) {
            cityNames = null;
        }
        if (duration != null && duration.equals(0)) {
            duration = null;
        }
        if (minPrice != null && minPrice.equals(0)) {
            minPrice = null;
        }
        if (maxPrice != null && maxPrice.equals(0)) {
            maxPrice = null;
        }

        // Search query in title or cityNames
        if (searchQuery != null && !searchQuery.isEmpty()) {
            Predicate searchPredicate = cb.or(
                    cb.like(pass.get("title"), "%" + searchQuery + "%"),
                    cb.like(pass.get("cityNames"), "%" + searchQuery + "%")
            );
            predicates.add(searchPredicate);
        }

        // Add departureCity and arrivalCity conditions
        if (departureCity != null && !departureCity.isEmpty()) {
            predicates.add(cb.like(pass.get("stationNames"), "%" + departureCity + "%"));
        }
        if (arrivalCity != null && !arrivalCity.isEmpty()) {
            predicates.add(cb.like(pass.get("stationNames"), "%" + arrivalCity + "%"));
        }

        // Add other cityNames conditions
        if (cityNames != null && !cityNames.isEmpty()) {
            predicates.add(cb.like(pass.get("cityNames"), "%" + cityNames + "%"));
        }

        // Other predicates for transportType
        if (transportType != null && !transportType.isEmpty()) {
            if (transportType.equals("1")) {  // 전철, 버스 혼합
                predicates.add(cb.equal(pass.get("transportType"), "전철, 버스"));
            } else if (transportType.equals("2")) {  // 전철 + 전철, 버스
                predicates.add(cb.or(
                        cb.equal(pass.get("transportType"), "전철"),
                        cb.like(pass.get("transportType"), "%전철, 버스%")
                ));
            } else if (transportType.equals("3")) {  // 버스 + 전철, 버스
                predicates.add(cb.or(
                        cb.equal(pass.get("transportType"), "버스"),
                        cb.like(pass.get("transportType"), "%전철, 버스%")
                ));
            }
        }

        // Handle duration
        if (duration != null) {
            if (duration == 1) {
                predicates.add(cb.equal(pass.get("period"), 1));
            } else if (duration == 2) {
                predicates.add(cb.equal(pass.get("period"), 2));
            } else if (duration >= 3) {
                predicates.add(cb.greaterThanOrEqualTo(pass.get("period"), 3));
            }
        }

        // Add price range filter if both minPrice and maxPrice are provided
        if (minPrice != null && maxPrice != null) {
            // Create a subquery to handle the price filtering
            Subquery<Integer> subquery = cq.subquery(Integer.class);
            Root<PassInformation> subRoot = subquery.from(PassInformation.class);
            Expression<Integer> priceExpression = cb.function("CAST", Integer.class, cb.function("UNNEST", String.class, subRoot.get("price")));
            subquery.select(subRoot.get("id")).where(
                    cb.and(
                            cb.equal(subRoot.get("id"), pass.get("id")),
                            cb.between(priceExpression, minPrice, maxPrice)
                    )
            );
            predicates.add(cb.exists(subquery));
        }

        // Combine all predicates with AND
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        TypedQuery<PassInformation> query = entityManager.createQuery(cq);
        List<PassInformation> results = query.getResultList();

        // Log query results
        System.out.println("Query Results: " + results);

        return results;
    }

    //상세정보
/*    public PassDetailDTO findById(Integer passId) {
        Optional<PassInformation> passInformationOpt = passRepository.findById(passId);
        if (passInformationOpt.isPresent()) {
            PassInformation passInformation = passInformationOpt.get();
            return convertToPassDetailDTO(passInformation);
        }
        return null;
    }*/

    @Override
    public PassDetailDTO findPassDetailById(Integer passId) {
        Optional<PassInformation> passInformationOpt = Optional.ofNullable(entityManager.find(PassInformation.class, passId));
        if (passInformationOpt.isPresent()) {
            PassInformation passInformation = passInformationOpt.get();
            return convertToPassDetailDTO(passInformation);
        }
        return null;
    }
    //북마크
    public PassSearchResultDTO findBookmarkResultById(Integer passId) {
        Optional<PassInformation> passInformationOpt = passRepository.findById(passId);
        if (passInformationOpt.isPresent()) {
            PassInformation passInformation = passInformationOpt.get();
            return convertToPassSearchResultDTO(passInformation);
        }
        return null;
    }

    private PassDetailDTO convertToPassDetailDTO(PassInformation passInformation) {
        return new PassDetailDTO(
                passInformation.getpassID(),
                passInformation.getImageURL(),
                passInformation.getTransportType(),
                passInformation.getTitle(),
                passInformation.getCityNames(),
                passInformation.getPrice(),
                passInformation.getPeriod(),
                passInformation.getProductDescription(),
                passInformation.getBenefitInformation(),
                passInformation.getReservationInformation(),
                passInformation.getRefundInformation()

        );
    }

    private PassSearchResultDTO convertToPassSearchResultDTO(PassInformation passInformation) {
        PassSearchResultDTO dto = new PassSearchResultDTO();
        dto.setpassID(passInformation.getpassID());
        dto.setImageUrl(passInformation.getImageURL());
        dto.setTitle(passInformation.getTitle());
        dto.setCityNames(passInformation.getCityNames());
        dto.setPrice(passInformation.getPrice());
        return dto;
    }
}
