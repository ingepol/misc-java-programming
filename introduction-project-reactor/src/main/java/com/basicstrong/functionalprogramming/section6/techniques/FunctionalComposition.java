package com.basicstrong.functionalprogramming.section6.techniques;

import com.basicstrong.functionalprogramming.section6.Square;
import com.basicstrong.functionalprogramming.section6.interfaces.Function;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionalComposition {

  public static void main(String[] args) {
    // The difference with chaining is that composition follows reverse direction to the 
    // direction we use when chaining so when we compose two functions the second function
    // gets executed first and then the first one is applied on the result returned
    // by the second one
    Function<Square, Integer> fun1 = Square::getArea;
    Function<Integer, Double> fun2 = Math::sqrt;
    Function<Square, Double> getSide = fun2.compose(fun1);
    Double side = getSide.apply(Square.builder().area(10).build());
    log.info(String.valueOf(side));
  }
}
