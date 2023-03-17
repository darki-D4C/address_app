package com.address_app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.address_app.repository.CityRepository;
import com.address_app.repository.HouseRepository;
import com.address_app.repository.StreetRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	CityRepository cityRepository;
	@Autowired
	StreetRepository streetRepository;
	@Autowired
	HouseRepository houseRepository;

	@GetMapping("/cities")
	public ResponseEntity<Object> getCitiesWithAppartments() {
		try {
			Object cities = cityRepository.getCitiesWithAppartments();
			if (cities == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/streets")
	public ResponseEntity<Object> getStreetsByCity(@RequestParam(required = true) int city_id) {
		Object streets = streetRepository.getStreetsWithAppartments(city_id);
		if (streets == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(streets, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/houses", params = "city_id")
	public ResponseEntity<Object> getHousesByCity(@RequestParam(required = true) int city_id) {
		Object houses = houseRepository.getHousesWithAppartmentsByCity(city_id);

		if (houses != null) {
			return new ResponseEntity<>(houses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/houses", params = "street_id")
	public ResponseEntity<Object> getHousesByStreet(@RequestParam(required = true) int street_id) {
		Object houses = houseRepository.getHousesWithAppartmentsByStreet(street_id);

		if (houses != null) {
			return new ResponseEntity<>(houses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// example string = Ярославль, Союзная, 144
	@PostMapping("/getHouse") 
	public ResponseEntity<Object> getHouseIdByAddress(@RequestParam(required = true) String address) {
		try {
			List<String> list = new ArrayList<>(Arrays.asList(address.split(", ")));
			String house = houseRepository.getHouseByAddress(list.get(0), list.get(1), Integer.parseInt(list.get(2)));
			if (house == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(house, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
