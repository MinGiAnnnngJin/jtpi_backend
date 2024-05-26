package com.example.jtpi_backend.repository;

import com.example.jtpi_backend.domain.SearchParameters;
import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.domain.entity.PassInformation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class PassRepositorympl {

    private final PassRepository passRepository;
    public PassRepositorympl(PassRepository passRepository) {

        this.passRepository = passRepository;

    }


    @PersistenceContext
    private EntityManager entityManager;
//검색
/*
    public List<PassInformation> searchPasses(SearchParameters searchParams) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PassInformation> query = cb.createQuery(PassInformation.class);
        Root<PassInformation> pass = query.from(PassInformation.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchParams.getQuery() != null) {
            Predicate titlePredicate = cb.like(pass.get("title"), prepareLikePattern(searchParams.getQuery()));
            Predicate cityNamesPredicate = cb.like(pass.get("cityNames"), prepareLikePattern(searchParams.getQuery()));
            predicates.add(cb.or(titlePredicate, cityNamesPredicate));
        }
        if (searchParams.getDepartureCity() != null) {
            predicates.add(cb.like(pass.get("cityNames"), prepareLikePattern(searchParams.getDepartureCity())));
        }
        if (searchParams.getArrivalCity() != null) {
            predicates.add(cb.like(pass.get("cityNames"), prepareLikePattern(searchParams.getArrivalCity())));
        }
        if (searchParams.getTransportType() != null) {
            predicates.add(cb.equal(pass.get("transportType"), searchParams.getTransportType()));
        }
        if (searchParams.getCityNames() != null) {
            predicates.add(cb.like(pass.get("cityNames"), prepareLikePattern(searchParams.getCityNames())));
        }
        if (searchParams.getDuration() != null) {
            predicates.add(cb.equal(pass.get("period"), searchParams.getDuration()));
        }
        if (searchParams.getQuantityAdults() != null) {
            predicates.add(cb.equal(pass.get("quantityAdults"), searchParams.getQuantityAdults()));
        }
        if (searchParams.getQuantityChildren() != null) {
            predicates.add(cb.equal(pass.get("quantityChildren"), searchParams.getQuantityChildren()));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }

    private String prepareLikePattern(String input) {
        return input != null ? "%" + input + "%" : null;
    }*/

       public PassDetailDTO findById(Integer passId) {
        Optional<PassInformation> passInformationOpt = passRepository.findById(passId);
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
