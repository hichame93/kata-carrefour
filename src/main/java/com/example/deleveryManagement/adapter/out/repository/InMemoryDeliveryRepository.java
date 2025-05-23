package com.example.deleveryManagement.adapter.out.repository;

import com.example.deleveryManagement.domain.model.Delivery;
import com.example.deleveryManagement.domain.port.out.DeliveryRepository;
import com.example.deleveryManagement.type.DeliveryMode;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryDeliveryRepository implements DeliveryRepository {
    private final Map<String, Delivery> store = new ConcurrentHashMap<>();

    public InMemoryDeliveryRepository() {
        store.put("1", new Delivery("1", "1 rue de la Paix", "Paris", DeliveryMode.ACCEPTED));
    }

    @Override
    public Mono<Delivery> findById(String id) {
        return Mono.justOrEmpty(store.get(id));
    }

    @Override
    public Mono<Delivery> save(Delivery delivery) {
        store.put(delivery.getId(), delivery);
        return Mono.just(delivery);
    }
}
