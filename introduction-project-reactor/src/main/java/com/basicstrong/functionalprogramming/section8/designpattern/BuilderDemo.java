package com.basicstrong.functionalprogramming.section8.designpattern;

import com.basicstrong.functionalprogramming.section8.mobile.Mobile;
import com.basicstrong.functionalprogramming.section8.mobile.MobileBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuilderDemo {

  public static void main(String[] args) {
    /*
    is all about providing flexibility in Object Creation. The intent of the builder design
    pattern is to isolate the construction of a complex object from its representation
     */
    MobileBuilder builder = new MobileBuilder();
    Mobile myMobile = builder.with(myBuilder -> {
      myBuilder.setRam(4);
      myBuilder.setBattery(4000);
      myBuilder.setProcessor("A12 Bionic");
    }).createMobile();

    log.info(myMobile.toString());


  }
}
