package com.learnreactiveprograming;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.Serializable;
import java.util.List;

@Slf4j
public class DistinctPOC {

  public static void main(final String[] args) {
    final List<Cart> carts = List.of(Cart.builder()
            .identifier("1234")
            .product(Product.builder()
                .id(1L)
                .partNumber("4568")
                .build())
            .build(),
        Cart.builder()
            .identifier("1234")
            .product(Product.builder()
                .id(2L)
                .partNumber("4569")
                .build())
            .build(),
        Cart.builder()
            .identifier("1235")
            .product(Product.builder()
                .id(1L)
                .partNumber("4568")
                .build())
            .build(),
        Cart.builder()
            .identifier("1235")
            .product(Product.builder()
                .id(1L)
                .partNumber("4568")
                .build())
            .build());
    Flux.fromIterable(carts)
        .distinct(cart -> cart.getIdentifier() + cart.getProduct().getId())
        .subscribe(cart -> log.info("cart:  {}", cart));
  }

  @Builder
  @Getter
  @ToString
  private static class Cart implements Serializable {

    private final String identifier;

    private final Product product;
  }

  @Builder
  @Getter
  @ToString
  private static class Product implements Serializable {

    private String partNumber;

    private Long id;
  }


}
