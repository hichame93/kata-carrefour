package com.example.deleveryManagement.exception;

public class DeliveryNotFoundException extends RuntimeException {
    public DeliveryNotFoundException(String deliveryId) {
        super("Delivery not found with ID: " + deliveryId);
    }
}
