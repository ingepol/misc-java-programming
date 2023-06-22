package com.basicstrong.functionalprogramming.section8.designpattern;

import com.basicstrong.functionalprogramming.section8.ac.AC;
import com.basicstrong.functionalprogramming.section8.ac.ACAutomationRemote;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandDemo {

  public static void main(String[] args) {
    /*
    Encapsulating a request as and object.
    A way to encapsulate a request as an object and parameterize clients 
    with different requests and perform corresponding operations 
     */

    AC ac1 = new AC();
    AC ac2 = new AC();

    ACAutomationRemote remote = new ACAutomationRemote();
    remote.setCommand(ac2::turnOn);
    remote.buttonPressed();
    remote.setCommand(ac1::incTemp);
    remote.buttonPressed();
    remote.setCommand(ac2::turnOff);
    remote.buttonPressed();
  }
}
