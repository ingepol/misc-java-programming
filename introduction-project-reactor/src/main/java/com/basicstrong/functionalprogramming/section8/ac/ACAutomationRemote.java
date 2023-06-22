package com.basicstrong.functionalprogramming.section8.ac;

import com.basicstrong.functionalprogramming.section8.interfaces.Command;
import lombok.Setter;

@Setter
public class ACAutomationRemote {

  Command command;

  public void buttonPressed() {
    command.execute();
  }

}
