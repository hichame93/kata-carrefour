package com.example.deleveryManagement.service;

import com.example.deleveryManagement.domain.model.Delivery;
import reactor.core.publisher.Mono;

public interface DeliveryService {
    Mono<Delivery> getDelivery(String id);

    Mono<Void> updateAddress(String id, String newAddress);

    Mono<Void> updateSlot(String id, String newSlot);
}
