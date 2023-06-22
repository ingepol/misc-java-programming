package com.basicstrong.functionalprogramming.section8.mobile;

import com.basicstrong.functionalprogramming.section6.interfaces.Consumer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MobileBuilder {

  int ram;
  int storage;
  int battery;
  int camera;
  String processor;
  double screenSize;

  public MobileBuilder with(Consumer<MobileBuilder> builderFields) {
    builderFields.accept(this);
    return this;
  }

  public Mobile createMobile() {
    return new Mobile(this);
  }


}
