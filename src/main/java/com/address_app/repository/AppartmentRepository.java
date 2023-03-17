package com.address_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.address_app.model.Appartment;

public interface AppartmentRepository extends JpaRepository<Appartment, Long> {

}
