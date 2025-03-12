package fmfi.sbdemo.adapter.rest;

import fmfi.sbdemo.core.api.*;
import org.springframework.web.bind.annotation.*;

@RestController // register this class as Spring MVC REST controller
@lombok.AllArgsConstructor
public class PaymentOrderRestController {

  // this field will be initialized by Spring's constructor dependency injection
  private final CreatePaymentOrderUseCase createPaymentOrderUseCase;


  @PostMapping("/api/payment-orders")
  public PaymentOrderDto createPaymentOrder(
          @RequestBody PaymentOrderRequestDto paymentOrderRequest
  ) {
    return createPaymentOrderUseCase.createPaymentOrder(paymentOrderRequest);
  }

}
