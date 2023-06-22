package com.basicstrong.functionalprogramming.section8.floor;

import com.basicstrong.functionalprogramming.section8.interfaces.Flooring;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteFlooring implements Flooring {

  @Override
  public void installation() {
    log.info("The concrete flooring is installed!");
  }
}
