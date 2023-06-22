package com.basicstrong.functionalprogramming.section8.mobile;

import lombok.Getter;

@Getter
public class Mobile {

  final int ram;
  final int storage;
  final int battery;
  final int camera;
  final String processor;
  final double screenSize;

  public Mobile(MobileBuilder builder) {
    this.ram = builder.ram;
    this.storage = builder.storage;
    this.battery = builder.battery;
    this.camera = builder.camera;
    this.processor = builder.processor;
    this.screenSize = builder.screenSize;
  }

  @Override
  public String toString() {
    return String.format("Specification- RAM: %d Storage: %d"
            + "Battery: %d Camera: %d MP Processor: %s Display: %f",
        this.ram, this.storage, this.battery, this.camera, this.processor, this.screenSize);
  }
}
