package com.example.demo.utils;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;

public class Calculator {
	
	private final static Double KILOMETERS = 0.1;
	
	public static Circle createCircleAreaWithLocations(Double lat, Double lng) {
		Point point = new Point(lat,lng); 
		Distance radius = new Distance(KILOMETERS, Metrics.KILOMETERS);
		return new Circle(point,radius);
	}

	public static Double totalDistance(double oldLat, double oldLon, double lat, double lon,double oldTotal) {
		double distance= 0.0;
		if ((oldLat == lat) && (oldLon == lon)) {
			return  distance + oldTotal;
		}
		else {
			double theta = oldLon - lon;
			double dist = Math.sin(Math.toRadians(oldLat)) * Math.sin(Math.toRadians(lat)) + Math.cos(Math.toRadians(oldLat)) * Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			
			return (dist + oldTotal);
		}
	}
}
