package com.example.deleveries.adapter.in.web;


import com.example.deleveries.domain.model.Delivery;
import com.example.deleveries.service.DeliveryService;
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
    public Mono<Delivery> getDelivery(@PathVariable String id) {
        return deliveryService.getDelivery(id);
    }

    @PutMapping("/{id}/address")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> updateAddress(@PathVariable String id, @RequestBody String newAddress) {
        return deliveryService.updateAddress(id, newAddress);
    }

    @PutMapping("/{id}/slot")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> updateSlot(@PathVariable String id, @RequestBody String newSlot) {
        return deliveryService.updateSlot(id, newSlot);
    }
}
