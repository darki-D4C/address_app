package com.address_app.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.address_app.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT  cit.name, COUNT(*) AS houses_count FROM CITIES AS cit INNER JOIN STREETS AS str ON cit.id = str.city_id INNER JOIN HOUSES AS hou ON str.id = hou.street_id GROUP BY cit.name", nativeQuery = true)
    List<Object> getCitiesWithAppartments();
}
