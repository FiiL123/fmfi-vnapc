package fmfi.sbdemo.adapter.rest;

import fmfi.sbdemo.core.api.*;
import org.springframework.web.bind.annotation.*;

@RestController // register this class as Spring MVC REST controller
@lombok.AllArgsConstructor // creates constructor with all arguments that will be used by Spring
public class TransactionRestController {

  // this field will be initialized by Spring's constructor dependency injection
  private final GetLatestCurrentAccountTransactionsUseCase getLatestCurrentAccountTransactionsUseCase;

  @GetMapping("/api/current-accounts/{accountNumber}/transactions")
  public CurrentAccountTransactionsDto getLatestCurrentAccountTransactions(
      @PathVariable(name = "accountNumber") String accountNumber
  ) {
    return getLatestCurrentAccountTransactionsUseCase.getLatestCurrentAccountTransactions(accountNumber);
  }
}
