package fmfi.sbdemo.core.domain;

import fmfi.sbdemo.core.api.*;

public interface SendPaymentOrderRequestSpi {
    PaymentOrderDto sendPaymentOrderRequest(PaymentOrderRequestDto paymentOrderRequest);
}
