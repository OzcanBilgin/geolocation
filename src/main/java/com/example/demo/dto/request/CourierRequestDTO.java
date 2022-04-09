package com.example.demo.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourierRequestDTO {
	
	@NotNull(message = "Courier id is required")
	private int courierId;
	
	@NotNull(message = "Time may not be null")
	private LocalDateTime time;
	
	@NotNull(message = "Lat may not be null")
	private Double lat;
	
	@NotNull(message = "Lng may not be null")
	private Double lng;

}
