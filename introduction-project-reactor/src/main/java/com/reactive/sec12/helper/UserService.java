package com.reactive.sec12.helper;


import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

public class UserService {

  private static final Map<String, String> MAP = Map.of(
      "sam", "std",
      "mike", "prime"
  );

  public static Function<Context, Context> userCategoryContext() {
    return ctx -> {
      final String user = ctx.get("user").toString();
      final String category = MAP.get(user);
      return ctx.put("category", category);
    };
  }
}
