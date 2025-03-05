package fmfi.sbdemo;

import org.springframework.boot.context.properties.*;

@ConfigurationProperties("app.hello")
public record HelloConfigProperties(String greeting) {

  public HelloConfigProperties(String greeting) {
    // set greeting default value to "Hello"
    this.greeting = greeting == null ? "Hello" : greeting;
  }
}
