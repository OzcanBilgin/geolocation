package com.example.demo.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.example.demo.entities.Store;

public interface IStoreRepository extends MongoRepository<Store, Integer> {
	
	Store findByGeolocationWithin(Circle circle);
	
	@Query(value = "{ 'courierList': { $elemMatch: { 'id' : ?0 } }}")
	Optional<Store> queryByCourierId(int id);
	
	@Query("{$and : ["
			+ "{'courierList': { $elemMatch: { 'id' : ?0 } }}, "
			+ "{ 'courierList': { $elemMatch: { 'time' : {$gte: ?1, $lte:?2 } } }}"
			+ "]} ")
	List<Store> findCourierTimeBetween(int id, LocalDateTime startDate, LocalDateTime endDate);

	@Query(value = "{ 'courierList': { $elemMatch: { 'id' : ?0 } }}")
	Optional<Store> queryingTotalDistances(int id);
}
