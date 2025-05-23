package com.example.deleveryManagement.service;

import com.example.deleveryManagement.domain.model.Delivery;
import com.example.deleveryManagement.domain.port.out.DeliveryRepository;
import com.example.deleveryManagement.exception.DeliveryStateException;
import com.example.deleveryManagement.service.impl.DeliveryServiceImpl;
import com.example.deleveryManagement.type.DeliveryMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DeliveryServiceTest {
    private DeliveryRepository deliveryRepository;
    private DeliveryServiceImpl deliveryService;

    @BeforeEach
    void setUp() {
        deliveryRepository = mock(DeliveryRepository.class);
        deliveryService = new DeliveryServiceImpl(deliveryRepository);
    }

    @Test
    void getDelivery_shouldReturnDelivery_whenExists() {
        Delivery expected = new Delivery("1", "123 Main St", "Tomorrow", DeliveryMode.ACCEPTED);
        when(deliveryRepository.findById("1")).thenReturn(Mono.just(expected));

        Delivery actual = deliveryService.getDelivery("1").block();
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAddress(), actual.getAddress());
    }


    @Test
    void updateAddress_shouldSucceed_whenStateIsNotReady() {
        Delivery delivery = new Delivery("1", "Old Address", "Tomorrow", DeliveryMode.ACCEPTED);
        when(deliveryRepository.findById("1")).thenReturn(Mono.just(delivery));
        when(deliveryRepository.save(any())).thenReturn(Mono.just(delivery));

        assertDoesNotThrow(() -> deliveryService.updateAddress("1", "New Address").block());
        verify(deliveryRepository).save(argThat(d -> d.getAddress().equals("New Address")));
    }

    @Test
    void updateAddress_shouldThrow_whenStateIsReady() {
        Delivery delivery = new Delivery("1", "Old Address", "Tomorrow", DeliveryMode.READY);
        when(deliveryRepository.findById("1")).thenReturn(Mono.just(delivery));

        Exception exception = assertThrows(DeliveryStateException.class, () ->
                deliveryService.updateAddress("1", "New Address").block()
        );
        assertEquals("Cannot modify address when delivery is READY", exception.getMessage());
    }


    @Test
    void updateSlot_shouldSucceed_whenStateIsNotReady() {
        Delivery delivery = new Delivery("1", "123 Main St", "Old Slot", DeliveryMode.ACCEPTED);
        when(deliveryRepository.findById("1")).thenReturn(Mono.just(delivery));
        when(deliveryRepository.save(any())).thenReturn(Mono.just(delivery));

        assertDoesNotThrow(() -> deliveryService.updateSlot("1", "New Slot").block());
        verify(deliveryRepository).save(argThat(d -> d.getSlot().equals("New Slot")));
    }

    @Test
    void updateSlot_shouldThrow_whenStateIsReady() {
        Delivery delivery = new Delivery("1", "123 Main St", "Old Slot", DeliveryMode.READY);
        when(deliveryRepository.findById("1")).thenReturn(Mono.just(delivery));

        Exception exception = assertThrows(DeliveryStateException.class, () ->
                deliveryService.updateSlot("1", "New Slot").block()
        );
        assertEquals("Cannot modify slot when delivery is READY", exception.getMessage());
    }

}
