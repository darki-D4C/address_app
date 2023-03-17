package com.address_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.address_app.model.House;

public interface HouseRepository extends JpaRepository<House, Long> {

    @Query(value = "SELECT (cit.name || ', ' || str.name|| ', ' || hou.number) as address, COUNT(hou.id) AS app_count FROM CITIES AS cit INNER JOIN STREETS AS str ON cit.id = str.city_id INNER JOIN HOUSES AS hou ON str.id = hou.street_id INNER JOIN APPARTMENTS AS app ON hou.id = app.house_id WHERE cit.id = :#{#city_id} GROUP  BY hou.id", nativeQuery = true)
    List<Object> getHousesWithAppartmentsByCity(@Param("city_id") int city_id);

    @Query(value = "SELECT (cit.name || ', ' || str.name|| ', ' || hou.number) as address, COUNT(hou.id) AS app_count FROM CITIES AS cit INNER JOIN STREETS AS str ON cit.id = str.city_id INNER JOIN HOUSES AS hou ON str.id = hou.street_id INNER JOIN APPARTMENTS AS app ON hou.id = app.house_id WHERE str.id = :#{#street_id} GROUP  BY hou.id", nativeQuery = true)
    List<Object> getHousesWithAppartmentsByStreet(@Param("street_id") int street_id);
    
    @Query(value = "SELECT hou.id FROM    Cities AS cit INNER JOIN Streets AS str ON cit.id = str.city_id INNER JOIN Houses AS hou ON str.id = hou.street_id INNER JOIN Appartments AS app ON hou.id = app.house_id WHERE cit.name = :#{#city_name} AND str.name = :#{#street_name} AND hou.number = :#{#house_number} GROUP BY hou.id", nativeQuery = true)
    String getHouseByAddress(@Param("city_name") String city_name, @Param("street_name") String street_name, @Param("house_number") int house_number);
}
