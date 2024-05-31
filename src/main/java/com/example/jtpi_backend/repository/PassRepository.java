package com.example.jtpi_backend.repository;

import com.example.jtpi_backend.domain.SearchParameters;
import com.example.jtpi_backend.domain.entity.PassInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassRepository extends JpaRepository<PassInformation, Integer>, PassRepositoryCustom {


}


