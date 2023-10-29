package com.sb.foodsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.foodsystem.converter.RestaurantConverter;
import com.sb.foodsystem.exception.RestaurantNotFoundException;
import com.sb.foodsystem.model.RestaurantDTO;
import com.sb.foodsystem.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
	
	@Autowired
    private RestaurantService restaurantService;
	
	@SuppressWarnings("unused")
	private RestaurantConverter restaurantConverter;
	
	public RestaurantController(RestaurantService restaurantService, RestaurantConverter restaurantConverter) {
		super();
		this.restaurantService = restaurantService;
		this.restaurantConverter = restaurantConverter;
	}

	//Retrieve a restaurant by ID
    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long restaurantId) throws RestaurantNotFoundException {
    	RestaurantDTO restaurant = restaurantService.getRestaurantById(restaurantId);
        if (restaurant == null) {
            throw new RestaurantNotFoundException("Restaurant not found with ID: " + restaurantId);
        }
        return ResponseEntity.ok(restaurant);
    }

    //Retrieve all restaurants
    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants(@PathVariable Long restaurantId) {
        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurants(restaurantId);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    // Create a new restaurant
    @PostMapping
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return ResponseEntity.ok(restaurantDTO);
    }

    
    // Update an existing restaurant
    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable Long restaurantId, @RequestBody RestaurantDTO updatedRestaurant) {
        RestaurantDTO updated = restaurantService.updateRestaurant(restaurantId, updatedRestaurant);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a restaurant by ID
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
