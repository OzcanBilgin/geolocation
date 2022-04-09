package com.example.demo.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.example.demo.dto.request.CourierRequestDTO;
import com.example.demo.dto.response.CourierDTO;
import com.example.demo.entities.Courier;
import com.example.demo.entities.Store;
import com.example.demo.mappers.CourierMaping;
import com.example.demo.repositories.IStoreRepository;
import com.example.demo.utils.Calculator;

@Service
public class StoreServiceImpl implements IStoreService {

	@Autowired
	private IStoreRepository storesRepository;

	@Autowired
	private CourierMaping maping;

	@Override
	public Store getByGeolocationWithin(Double lat, Double lng) {
		return storesRepository.findByGeolocationWithin(Calculator.createCircleAreaWithLocations(lat, lng));
	}

	@Override
	public void saveByCourier(CourierRequestDTO request) {

		final Store store = getByGeolocationWithin(request.getLat(), request.getLng());

		if (store != null && !isCourierTimesBetween(store, request.getCourierId())) {
			save(preparedStoreData(store, request));
		} else {
			update(request);
		}

	}

	@Override
	public void update(CourierRequestDTO request) {
		Store store = storesRepository.queryByCourierId(request.getCourierId())
				.orElseThrow(() -> new NullPointerException());
		save(preparedStoreData(store, request));
	}

	@Override
	public CourierDTO queryingTotalDistances(int id) {
		Store store = storesRepository.queryingTotalDistances(id).orElseThrow(() -> new NullPointerException());
		Courier tst= store.getCourierList().stream().filter(item-> item.getId() == id).findAny().get();
		return maping.courierEntityToCourierDTO(tst);
	}

	@Override
	public void save(Store store) {
		storesRepository.save(store);
	}

	private Boolean isCourierTimesBetween(final Store store, final int id) {
		List<Store> result = storesRepository.findCourierTimeBetween(id,
				LocalDateTime.now().minus(Duration.ofMinutes(1)), LocalDateTime.now());
		if (CollectionUtils.isEmpty(result))
			return false;
		return true;
	}

	private Store preparedStoreData(final Store store, final CourierRequestDTO request) {
		Set<Courier> allCourierList;

		Courier courier = maping.courierRequestDTOToCourierEntity(request);

		Double totalDistance = Calculator.totalDistance(store.getGeolocation().getX(), store.getGeolocation().getY(),
				request.getLat(), request.getLng(),
				courier.getTotalDistances() == null ? 0.0 : courier.getTotalDistances());
		courier.setTotalDistances(totalDistance);

		if (containsId(store.getCourierList(), courier.getId()))
			allCourierList = updateCourierList(store.getCourierList(), courier);
		else
			allCourierList = insertCourierList(store.getCourierList(), courier);

		store.getCourierList().addAll(allCourierList);

		return store;

	}

	@Override
	public List<Store> getAll() {
		return storesRepository.findAll();
	}

	public boolean containsId(final Set<Courier> list, final int id) {
		return list.stream().filter(item -> item.getId() == id).findFirst().isPresent();
	}

	public Set<Courier> updateCourierList(final Set<Courier> list, final Courier courier) {
		return list.stream().filter(item -> item.getId() == courier.getId()).map(element -> {
			element.setId(courier.getId());
			element.setLastLacation(courier.getLastLacation());
			element.setTime(courier.getTime());
			element.setTotalDistances(courier.getTotalDistances());
			return element;
		}).collect(Collectors.toSet());
	}

	public Set<Courier> insertCourierList(final Set<Courier> list, final Courier courier) {
		list.add(courier);
		return list;
	}
}
