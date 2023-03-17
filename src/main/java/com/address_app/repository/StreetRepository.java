package com.address_app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.address_app.model.Street;

public interface StreetRepository extends JpaRepository<Street, Long> {

    @Query(value = "SELECT str.name, COUNT(*) AS houses_count FROM STREETS AS str INNER JOIN HOUSES AS hou ON str.id = hou.street_id WHERE str.city_id = :#{#city_id} GROUP BY str.name", nativeQuery = true)
    List<Object> getStreetsWithAppartments(@Param("city_id") int city_id);
}
