package com.reactive.webfluxdemo.dto;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Response {

  private Date date = new Date();
  private int output;

  public Response(final int output) {
    this.output = output;
  }
}
