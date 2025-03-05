package fmfi.sbdemo.adapter.persistance;

import fmfi.sbdemo.core.api.*;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "transaction") // Specifies that the class is a JPA entity.
@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // implement equals & hashcode based on non-nullable id attribute
public class TransactionEntity {

  @Id @EqualsAndHashCode.Include private String transactionId; // primary key

  private PaymentDetailEmbeddable paymentDetail;

  @AttributeOverride(name = "iban", column = @Column(name = "sender_account_iban"))
  @AttributeOverride(name = "name", column = @Column(name = "sender_account_name"))
  @AttributeOverride(name = "bic", column = @Column(name = "sender_account_bic"))
  @AttributeOverride(name = "balance.amount", column = @Column(name = "sender_account_balance_value"))
  @AttributeOverride(name = "balance.currency", column = @Column(name = "sender_account_balance_currency"))
  private AccountEmbeddable senderAccount;

  @AttributeOverride(name = "iban", column = @Column(name = "target_account_iban"))
  @AttributeOverride(name = "name", column = @Column(name = "target_account_name"))
  @AttributeOverride(name = "bic", column = @Column(name = "target_account_bic"))
  @AttributeOverride(name = "balance.amount", column = @Column(name = "target_account_balance_value"))
  @AttributeOverride(name = "balance.currency", column = @Column(name = "target_account_balance_currency"))
  private AccountEmbeddable targetAccount;

  @Embedded private PaymentSymbolsEmbeddable paymentSymbols;
}


@Embeddable
@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
class PaymentDetailEmbeddable {
  @Enumerated(EnumType.STRING) // use enum literal name
  private TransactionStatus status;
  private MoneyEmbeddable amount;
  private java.time.LocalDate effectiveDate;
  private String description;
}


@Embeddable
@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
class AccountEmbeddable {
  @Column(nullable = false)
  private String iban;
  private String name;
  private String bic;
  private MoneyEmbeddable balance;
}


@Embeddable
@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
class PaymentSymbolsEmbeddable {
  @Column(name = "variable_symbol") private String variable;
  @Column(name = "specific_symbol") private String specific;
  @Column(name = "constant_symbol") private String constant;
  private String payerReference;
}

@Embeddable
@NoArgsConstructor @AllArgsConstructor
class MoneyEmbeddable {
  private java.math.BigDecimal amount;
  private String currency;

  public Money toMoney() { return new Money(amount, currency); }
}

// Hibernate requires no-arg constructors 
//https://gitlab.com/fmfi-uniba/cloud-native-apps/ib-in-sb/-/wikis/Spring-Boot-Practicals/Internet-banking-in-Spring-Boot-(2)