package com.reactive.webfluxdemo.controller;

import com.reactive.webfluxdemo.dto.Response;
import com.reactive.webfluxdemo.service.MathService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("math")
@RequiredArgsConstructor
public class MathController {

  private final MathService mathService;

  @GetMapping("square/{input}")
  public Response findSquare(@PathVariable final int input) {
    return this.mathService.findSquare(input);
  }

  @GetMapping("table/{input}")
  public List<Response> multiplicationTable(@PathVariable final int input) {
    return this.mathService.multiplicationTable(input);
  }


}
