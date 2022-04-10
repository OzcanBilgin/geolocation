package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import com.example.demo.dto.request.CourierRequestDTO;
import com.example.demo.entities.Courier;
import com.example.demo.entities.Store;
import com.example.demo.mappings.CourierMapping;
import com.example.demo.repositories.IStoreRepository;
import com.example.demo.services.StoreServiceImpl;
import com.example.demo.utils.Calculator;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

	@Mock
	private IStoreRepository storeRepository;

	@InjectMocks
	private StoreServiceImpl storeService;

	@Mock
	private CourierMapping mapping;

	@Before
	public void setup() {
		System.out.println("************** START ************");
	}

	@Test
	public void whenSaveByCourierCalledWithValidRequest_itShouldReturnValid() {
		Set<Courier> couriers = new HashSet<Courier>();
		CourierRequestDTO courierRequestDTO = new CourierRequestDTO(1, LocalDateTime.parse("2022-04-09T13:49:00"),
				40.9923307, 29.1244229);

		Courier courier = new Courier(1, 0.0, LocalDateTime.parse("2022-04-09T13:49:00"),
				new Point(40.9923307, 29.1244229));
		couriers.add(courier);
		Store store = new Store(1, "Ataşehir MMM Migros", new Point(40.9923307, 29.1244229), couriers);



		when(storeRepository.findByGeolocationWithin(
				Calculator.createCircleAreaWithLocations(courierRequestDTO.getLat(), courierRequestDTO.getLng())))
				.thenReturn(store);

		when(mapping.courierRequestDTOToCourierEntity(courierRequestDTO)).thenReturn(courier);

		storeService.saveByLocations(courierRequestDTO);
		verify(storeRepository, times(1)).save(store);

	}

	@Test
	public void whenGetTotalDistanceCalledWithCourierId_itShouldReturnValidTotalDistance() {
		Set<Courier> couriers = new HashSet<Courier>();

		Courier courier = new Courier(1, 0.0, LocalDateTime.parse("2022-04-09T13:49:00"),
				new Point(40.9923307, 29.1244229));
		couriers.add(courier);
		Store store = new Store(1, "Ataşehir MMM Migros", new Point(40.9923307, 29.1244229), couriers);

		when(storeRepository.queryingTotalDistances(1)).thenReturn(Optional.of(store));

		storeService.getTotalTravelDistance(1);

		assertEquals(store.getId(), 1);


	}

}
