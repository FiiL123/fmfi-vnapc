package fmfi.sbdemo.core.domain;

import fmfi.sbdemo.core.api.*;

@org.springframework.stereotype.Service // register this service as Spring bean
@lombok.AllArgsConstructor
public class PaymentOrderService implements CreatePaymentOrderUseCase {

  private final SendPaymentOrderRequestSpi sendPaymentOrderRequestSpi;

  private final ApplyFeesForPaymentOrderSpi applyFeesForPaymentOrderSpi;


  @Override
  public PaymentOrderDto createPaymentOrder(PaymentOrderRequestDto paymentOrderRequest) {

    // TODO check logged user permission for account

    // TODO validate payment order request

    var paymentOrder = sendPaymentOrderRequestSpi.sendPaymentOrderRequest(paymentOrderRequest);

    applyFeesForPaymentOrderSpi.applyFeesForPaymentOrder(paymentOrder);

    return paymentOrder;
  }

}
