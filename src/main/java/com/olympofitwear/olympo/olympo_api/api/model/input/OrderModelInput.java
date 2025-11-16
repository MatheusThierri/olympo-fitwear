package com.olympofitwear.olympo.olympo_api.api.model.input;

import com.olympofitwear.olympo.olympo_api.domain.model.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModelInput {
    @NotNull
    private OrderStatus orderStatus;
}
