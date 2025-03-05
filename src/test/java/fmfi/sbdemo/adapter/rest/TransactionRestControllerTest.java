package fmfi.sbdemo.adapter.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fmfi.sbdemo.core.api.GetLatestCurrentAccountTransactionsUseCase;
import fmfi.sbdemo.core.api.*;
import fmfi.sbdemo.core.api.TransactionDirection;
import fmfi.sbdemo.core.api.TransactionStatus;
import fmfi.sbdemo.core.api.CurrentAccountTransactionListItemDto;
import fmfi.sbdemo.core.api.CurrentAccountTransactionsDto;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TransactionRestController.class) // apply only configuration relevant to MVC tests
class TransactionRestControllerTest {

  @Autowired
  private MockMvc mockMvc; // Main entry point for server-side Spring MVC test support

  @MockitoBean // Registers mock as a spring bean required for the tests
  private GetLatestCurrentAccountTransactionsUseCase getTransactionsUseCase;

  @Test
  void getLatestCurrentAccountTransactions() throws Exception {
    // setup
    var iban = "SK8975000000000012345671";

    var resultDto = new CurrentAccountTransactionsDto(List.of(
        new CurrentAccountTransactionListItemDto(
            /* transactionId */ "667bb620-04c6-45cb-8746-80cf932c7a01",
            /* amount */ Money.eur(42),
            /* partnerName */ "Acme corp.",
            /* variableSymbol */ "123456",
            /* description */ "Anvil" ,
            /* accountBalance */ Money.eur(958),
            /* effectiveDate */ LocalDate.of(2022, 9, 24),
            /* status */ TransactionStatus.PROCESSED,
            /* direction */ TransactionDirection.DEBIT
        ),
        new CurrentAccountTransactionListItemDto(
            /* transactionId */ "1717c635-d19a-4399-b911-2d30578c065d",
            /* amount */ Money.eur(10),
            /* partnerName */ "Acme corp.",
            /* variableSymbol */ null,
            /* description */ "Cashback" ,
            /* accountBalance */ Money.eur(970),
            /* effectiveDate */ LocalDate.of(2022, 9, 25),
            /* status */ TransactionStatus.PROCESSED,
            /* direction */ TransactionDirection.CREDIT
        )
    ));

    when(getTransactionsUseCase.getLatestCurrentAccountTransactions(iban)).thenReturn(resultDto);

    String expectedResponseJson = """
        {
          "transactions": [
            {
              "transactionId": "667bb620-04c6-45cb-8746-80cf932c7a01",
              "amount": { "amount": 42, "currency": "EUR" },
              "partnerName": "Acme corp.",
              "variableSymbol": "123456",
              "description": "Anvil",
              "accountBalance": { "amount": 958, "currency": "EUR" },
              "effectiveDate": "2022-09-24",
              "status": "PROCESSED",
              "direction": "DEBIT"
            },
            {
              "transactionId": "1717c635-d19a-4399-b911-2d30578c065d",
              "amount": { "amount": 10, "currency": "EUR" },
              "partnerName": "Acme corp.",
              "variableSymbol": null,
              "description": "Cashback",
              "accountBalance": { "amount": 970, "currency": "EUR" },
              "effectiveDate": "2022-09-25",
              "status": "PROCESSED",
              "direction": "CREDIT"
            }
          ]
        }""";

    // run & verify
    this.mockMvc
        .perform(get("/api/current-accounts/{accountNumber}/transactions", iban))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponseJson));
  }

}
