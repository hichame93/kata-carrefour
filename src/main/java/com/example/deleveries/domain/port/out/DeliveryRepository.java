package com.example.deleveries.domain.port.out;

import com.example.deleveries.domain.model.Delivery;
import reactor.core.publisher.Mono;

public interface DeliveryRepository {

    Mono<Delivery> findById(String id);

    Mono<Delivery> save(Delivery delivery);
}
