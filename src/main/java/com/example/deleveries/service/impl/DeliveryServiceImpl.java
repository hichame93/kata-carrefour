package com.example.deleveries.service.impl;

import com.example.deleveries.domain.model.Delivery;
import com.example.deleveries.domain.port.out.DeliveryRepository;
import com.example.deleveries.service.DeliveryService;
import com.example.deleveries.type.DeliveryMode;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Mono<Delivery> getDelivery(String id) {
        return deliveryRepository.findById(id);
    }

    @Override
    public Mono<Void> updateAddress(String id, String address) {
        return deliveryRepository.findById(id)
                .flatMap(delivery -> {
                    if (delivery.getState() != DeliveryMode.READY) {
                        delivery.setAddress(address);
                        return deliveryRepository.save(delivery).then();
                    } else {
                        return Mono.error(new IllegalStateException("Cannot modify address when delivery is READY"));
                    }
                });
    }

    @Override
    public Mono<Void> updateSlot(String id, String slot) {
        return deliveryRepository.findById(id)
                .flatMap(delivery -> {
                    if (delivery.getState() != DeliveryMode.READY) {
                        delivery.setSlot(slot);
                        return deliveryRepository.save(delivery).then();
                    } else {
                        return Mono.error(new IllegalStateException("Cannot modify slot when delivery is READY"));
                    }
                });
    }
}
