package com.olympofitwear.olympo.olympo_api.api.model.input;

import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import com.olympofitwear.olympo.olympo_api.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class OrderModelInput {
    private OffsetDateTime orderDate;
    private OrderStatus orderStatus;
    private ClientIdModelInput client;
}
