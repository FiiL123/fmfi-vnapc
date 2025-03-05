package fmfi.sbdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
public class HelloWorldController {

  // this field will be initialized by Spring's constructor dependency injection
  private final HelloConfigProperties configProperties;

    public HelloWorldController(HelloConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @GetMapping("/api/hello")
  public String sayHello(
      @RequestParam(name = "subject", defaultValue = "World") String subject
  ) {
    return configProperties.greeting() + ", " + subject;
  }

}
