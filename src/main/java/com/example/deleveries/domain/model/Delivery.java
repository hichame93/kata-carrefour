package com.example.deleveries.domain.model;

import com.example.deleveries.type.DeliveryMode;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    private String id;
    private String address;
    private String slot;
    private DeliveryMode state;
}
