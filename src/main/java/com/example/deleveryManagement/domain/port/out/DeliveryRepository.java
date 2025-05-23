package com.example.deleveryManagement.domain.port.out;

import com.example.deleveryManagement.domain.model.Delivery;
import reactor.core.publisher.Mono;

public interface DeliveryRepository {

    Mono<Delivery> findById(String id);

    Mono<Delivery> save(Delivery delivery);
}
