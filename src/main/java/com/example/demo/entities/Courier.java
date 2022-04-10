package com.example.demo.entities;

import java.time.LocalDateTime;
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
@AllArgsConstructor
@NoArgsConstructor
public class Courier {
	
	@Id
    private int id;
	
	private Double totalDistances;
	
	private LocalDateTime time;
	
	private Point lastLacation;
	
}
