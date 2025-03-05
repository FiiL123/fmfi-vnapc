package fmfi.sbdemo.core.api;

import fmfi.sbdemo.core.api.*;

public interface GetLatestCurrentAccountTransactionsUseCase {
  CurrentAccountTransactionsDto getLatestCurrentAccountTransactions(String accountNumber);
}
