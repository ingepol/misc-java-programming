package com.threads.interrupt;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Example3 {

  public static void main(String[] args) {
    Thread thread = new Thread(new WaitingForUserInput());
    thread.setName("InputWaitingThread");
    //thread.setDaemon(true); //Terminate all threads one application ends
    thread.start();
  }

  private static class WaitingForUserInput implements Runnable {

    @Override
    public void run() {
      try {
        while (true) {
          char input = (char) System.in.read(); // Avoid the interrupted signal
          if (input == 'q') {
            return;
          }
        }
      } catch (IOException e) {
        log.error("An exception was caught " + e);
      }
    }
  }


}
