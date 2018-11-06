package com.cjalturas.utilities;

public class DelayUtils {

  public static final int SEC_1 = 1_000;

  public static void delay(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new RuntimeException("Se interrumpió el delay");
    }
  }

}
