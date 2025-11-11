package com.olympofitwear.olympo.olympo_api.api.assembler;

import com.olympofitwear.olympo.olympo_api.api.model.output.PaymentRepresentationModel;
import com.olympofitwear.olympo.olympo_api.domain.model.Payment;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PaymentAssembler {
    private final ModelMapper modelMapper;

    public PaymentRepresentationModel toModel(Payment payment) {
        return modelMapper.map(payment, PaymentRepresentationModel.class);
    }
}
