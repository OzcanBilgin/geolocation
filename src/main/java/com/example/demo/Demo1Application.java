package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.example.demo.entities.Store;
import com.example.demo.repositories.IStoreRepository;

@SpringBootApplication
@EnableMongoRepositories
public class Demo1Application implements CommandLineRunner {

	@Autowired
	private IStoreRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.saveAll(Arrays.asList(new Store(1, "Ataşehir MMM Migros", new Point(40.9923307, 29.1244229), null),
				new Store(2, "Novada MMM Migros", new Point(40.986106, 29.1161293), null),
				new Store(3, "Beylikdüzü 5M Migros", new Point(41.0066851, 28.6552262), null),
				new Store(4, "Ortaköy MMM Migros", new Point(41.055783, 29.0210292), null),
				new Store(5, "Caddebostan MMM Migros", new Point(40.9632463, 29.0630908), null)));
	}

}
