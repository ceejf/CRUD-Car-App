package com.example.CRUDCarApp.repo;

import com.example.CRUDCarApp.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
    Page<Car> findByMakeContainingIgnoreCase(String make, Pageable pageable);

}
