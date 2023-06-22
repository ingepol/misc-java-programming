package com.vinsguru.webfluxpatterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.vinsguru.webfluxpatterns.sec02")
public class WebFluxPatternsApplication {

  public static void main(final String[] args) {
    SpringApplication.run(WebFluxPatternsApplication.class);
  }
}
