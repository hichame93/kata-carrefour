package com.example.deleveryManagement.in.web;

import com.example.deleveryManagement.adapter.in.web.DeliveryController;
import com.example.deleveryManagement.domain.model.Delivery;
import com.example.deleveryManagement.service.DeliveryService;
import com.example.deleveryManagement.type.DeliveryMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeliveryControllerTest {

    private WebTestClient webTestClient;
    private DeliveryService deliveryService;

    @BeforeEach
    void setUp() {
        deliveryService = mock(DeliveryService.class);
        DeliveryController controller = new DeliveryController(deliveryService);
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    void shouldReturnDelivery_whenFound() {
        Delivery delivery = new Delivery("1", "123 Main St", "Tomorrow", DeliveryMode.ACCEPTED);
        when(deliveryService.getDelivery("1")).thenReturn(Mono.just(delivery));

        webTestClient.get().uri("/deliveries/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.address").isEqualTo("123 Main St");
    }
}
