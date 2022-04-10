package com.example.demo.controllers;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.request.CourierRequestDTO;
import com.example.demo.dto.response.CourierDTO;
import com.example.demo.services.IStoreService;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

	@Autowired
	private IStoreService storeService;

	@PostMapping("/save")
	public ResponseEntity<String> saveByLocations(@RequestBody @Valid CourierRequestDTO request) {
		storeService.saveByLocations(request);
		return ResponseEntity.ok("SUCCESSFUL");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CourierDTO> getTotalDistance(@PathVariable("id") int id) {
		return ResponseEntity.ok(storeService.getTotalTravelDistance(id));
	}

}
