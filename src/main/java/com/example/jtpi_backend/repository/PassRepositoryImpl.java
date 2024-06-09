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
            String routeInformation,
            Integer duration,
            Integer minPrice,
            Integer maxPrice

    ) {
        // Use CriteriaBuilder for the non-price related queries
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PassInformation> cq = cb.createQuery(PassInformation.class);
        Root<PassInformation> pass = cq.from(PassInformation.class);

        List<Predicate> predicates = new ArrayList<>();

        // Convert '0' to null for all search parameters
        // Convert '0' to null for all search parameters
        if ("0".equals(searchQuery)) {
            searchQuery = null;
        }
        if ("0".equals(departureCity)) {
            departureCity = null;
        }
        if ("0".equals(arrivalCity)) {
            arrivalCity = null;
        }
        if ("0".equals(transportType)) {
            transportType = null;
        }
        if ("0".equals(routeInformation)) {
            routeInformation = null;
        }
        if (duration != null && duration == 0) {
            duration = null;
        }
        if (minPrice != null && minPrice == 0) {
            minPrice = null;
        }
        if (maxPrice != null && maxPrice == 0) {
            maxPrice = null;
        }

        // Search query in title or cityNames
        if (searchQuery != null && !searchQuery.isEmpty()) {
            String normalizedSearchQuery = searchQuery.replace("역", "").replace(" ", "");

            Predicate searchPredicate = cb.or(
                    cb.like(cb.function("REPLACE", String.class, pass.get("title"), cb.literal(" "), cb.literal("")), "%" + normalizedSearchQuery + "%"),
                    cb.like(cb.function("REPLACE", String.class, pass.get("routeInformation"), cb.literal(" "), cb.literal("")), "%" + normalizedSearchQuery + "%"),
                    cb.like(cb.function("REPLACE", String.class, pass.get("stationNames"), cb.literal(" "), cb.literal("")), "%" + normalizedSearchQuery + "%")
            );
            predicates.add(searchPredicate);
        }

        // Add departureCity and arrivalCity conditions
        if (departureCity != null && !departureCity.isEmpty()) {
            String normalizedSearchQuery = departureCity.replace("역", "").replace(" ", "");
            predicates.add( cb.like(cb.function("REPLACE", String.class, pass.get("stationNames"), cb.literal(" "), cb.literal("")), "%" + normalizedSearchQuery + "%"));
        }
        if (arrivalCity != null && !arrivalCity.isEmpty()) {
            String normalizedSearchQuery = arrivalCity.replace("역", "").replace(" ", "");
            predicates.add( cb.like(cb.function("REPLACE", String.class, pass.get("stationNames"), cb.literal(" "), cb.literal("")), "%" + normalizedSearchQuery + "%"));
        }

        // Add other cityNames conditions
        if (routeInformation != null && !routeInformation.isEmpty()) {
            predicates.add(cb.like(pass.get("routeInformation"), "%" +routeInformation + "%"));
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

        // Combine all predicates with AND
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Execute the query for non-price filters
        List<PassInformation> results = entityManager.createQuery(cq).getResultList();

        // Filter results by price range using a native query
        if (minPrice != null && maxPrice != null) {
            String sql = "SELECT * FROM PassInformation " +
                    "WHERE EXISTS (" +
                    "    SELECT 1 " +
                    "    FROM (" +
                    "        SELECT CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(price, ',', numbers.n), ',', -1) AS UNSIGNED) AS individual_price " +
                    "        FROM (SELECT 1 n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5) numbers " +
                    "        WHERE LENGTH(price) - LENGTH(REPLACE(price, ',', '')) >= numbers.n - 1" +
                    "    ) temp " +
                    "    WHERE individual_price BETWEEN ?1 AND ?2" +
                    ")";
            Query nativeQuery = entityManager.createNativeQuery(sql, PassInformation.class);
            nativeQuery.setParameter(1, minPrice);
            nativeQuery.setParameter(2, maxPrice);


            List<PassInformation> priceFilteredResults = nativeQuery.getResultList();
            results.retainAll(priceFilteredResults);
        }

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
//상세정보
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
        dto.setRouteInformation(passInformation.getRouteInformation());
        dto.setPrice(passInformation.getPrice());
        return dto;
    }
}
