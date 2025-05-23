package com.example.deleveryManagement.adapter.in.web;


import com.example.deleveryManagement.domain.model.Delivery;
import com.example.deleveryManagement.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a delevery by ID")
    public Mono<Delivery> getDeliveryById(@PathVariable String id) {
        return deliveryService.getDelivery(id);
    }

    @PutMapping("/{id}/address")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update a delevery address")
    public Mono<Void> updateAddress(@PathVariable String id, @RequestBody String newAddress) {
        return deliveryService.updateAddress(id, newAddress);
    }

    @PutMapping("/{id}/slot")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update a slot")
    public Mono<Void> updateSlot(@PathVariable String id, @RequestBody String newSlot) {
        return deliveryService.updateSlot(id, newSlot);
    }
}
