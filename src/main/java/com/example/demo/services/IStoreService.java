package com.example.demo.services;

import com.example.demo.dto.request.CourierRequestDTO;
import com.example.demo.dto.response.CourierDTO;
import com.example.demo.entities.Store;

public interface IStoreService {

	Store getByGeolocationWithin(Double lat, Double lng);

	void saveByLocations(CourierRequestDTO request);

	void update(CourierRequestDTO request);

	CourierDTO getTotalTravelDistance(int id);

	void save(Store store);

}
