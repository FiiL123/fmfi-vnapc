package fmfi.sbdemo.core.domain;

import fmfi.sbdemo.core.api.GetLatestCurrentAccountTransactionsUseCase;
import fmfi.sbdemo.core.api.CurrentAccountTransactionsDto;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@org.springframework.stereotype.Service // register this service as Spring bean
@lombok.AllArgsConstructor
public class CurrentAccountTransactionService implements GetLatestCurrentAccountTransactionsUseCase {

  private final FindLastCurrentAccountTransactionsByAccountNumberSpi findTransactionsSpi;

  private final CurrentAccountTransactionConfigProperties configProperties;

  @Override
  public CurrentAccountTransactionsDto getLatestCurrentAccountTransactions(String accountNumber) {
    // TODO check logged user permission for account

    return findTransactionsSpi.findLastCurrentAccountTransactionsByAccountNumber(
            /* accountNumber */ accountNumber,
            /* transactionRecordCount */ configProperties.getLatestCurrentAccountTransactionsMaxRecordCount()
    );
  }
}

