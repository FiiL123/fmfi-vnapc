package fmfi.sbdemo.core.domain;

import fmfi.sbdemo.core.api.*;

public interface ApplyFeesForPaymentOrderSpi {
    void applyFeesForPaymentOrder(PaymentOrderDto paymentOrder);
}
