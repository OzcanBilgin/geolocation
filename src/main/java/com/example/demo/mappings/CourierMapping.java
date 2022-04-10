package com.example.demo.mappings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.data.geo.Point;
import com.example.demo.dto.request.CourierRequestDTO;
import com.example.demo.dto.response.CourierDTO;
import com.example.demo.entities.Courier;

@Mapper( componentModel = "spring", imports = {LocalDateTime.class, LocalDate.class,Point.class,Double.class } )
public abstract class CourierMapping {
	
	@Mappings({
		@Mapping(source = "courierId", target="id"),
		@Mapping(source = "time", target="time"),
		@Mapping(target= "lastLacation", expression = "java(new Point(courierRequestDTO.getLat(), courierRequestDTO.getLng() ))")
	})
	public abstract Courier courierRequestDTOToCourierEntity(CourierRequestDTO courierRequestDTO);
	
	public abstract CourierDTO courierEntityToCourierDTO(Courier courier);
	
	
	@Mappings({
		@Mapping(source = "courierRequestDTO.courierId", target="id"),
		@Mapping(source = "courierRequestDTO.time", target="time"),
		@Mapping(target= "lastLacation", expression = "java(new Point(courierRequestDTO.getLat(), courierRequestDTO.getLng() ))")
	})
	public abstract Courier mergeCourierRequestDTOWithCourierEntity(CourierRequestDTO courierRequestDTO, @MappingTarget Courier courier);


}
