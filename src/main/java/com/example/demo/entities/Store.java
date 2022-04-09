package com.example.demo.entities;

import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Store {

	@Id
	private int id;

	private String name;

	private Point geolocation;

	private Set<Courier> courierList = new HashSet<Courier>();

}
