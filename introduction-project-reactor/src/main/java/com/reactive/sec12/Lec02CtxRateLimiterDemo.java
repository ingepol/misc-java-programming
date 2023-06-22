package com.reactive.sec12;

import com.reactive.sec12.helper.BookService;
import com.reactive.sec12.helper.UserService;
import reactor.util.context.Context;

import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec02CtxRateLimiterDemo {

  public static void main(final String[] args) {
    BookService.getBook()
        .repeat(2)
        .contextWrite(UserService.userCategoryContext())
        .contextWrite(Context.of("user", "mike"))
        .subscribe(subscriber());
  }
}
