package com.olympofitwear.olympo.olympo_api.api.model.output;

import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class PaymentRepresentationModel {
    private UUID id;
    private OffsetDateTime paymentDate;
    private OrderRepresentationModel order;
}
