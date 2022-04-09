package com.example.demo.services;

import java.util.List;
import com.example.demo.dto.request.CourierRequestDTO;
import com.example.demo.dto.response.CourierDTO;
import com.example.demo.entities.Store;

public interface IStoreService {

	Store getByGeolocationWithin(Double lat, Double lng);

	void saveByCourier(CourierRequestDTO request);

	void update(CourierRequestDTO request);

	CourierDTO queryingTotalDistances(int id);

	void save(Store store);

	List<Store> getAll();

}
