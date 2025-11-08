package com.olympofitwear.olympo.olympo_api.api.model.output;

import com.olympofitwear.olympo.olympo_api.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class OrderRepresentationModel {
    private UUID id;
    private OffsetDateTime orderDate;
    private OrderStatus orderStatus;
    private ClientResumeModel client;
}
