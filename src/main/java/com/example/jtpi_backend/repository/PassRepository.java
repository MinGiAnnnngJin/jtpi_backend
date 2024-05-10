package com.example.jtpi_backend.repository;

import com.example.jtpi_backend.domain.entity.PassInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassRepository extends JpaRepository<PassInformation, Integer> {
}
