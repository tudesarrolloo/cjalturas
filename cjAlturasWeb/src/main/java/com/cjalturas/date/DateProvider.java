package com.cjalturas.date;

import java.util.Date;


/**
 * Administra los mensajes cargados desde un archivo de propiedades.
 * @author Edison
 */
public class DateProvider {

  private static DateProvider instance;

  private DateProvider() {
    super();
  }

  public static DateProvider getInstance() {
    if (instance == null) {
      instance = new DateProvider();
    }
    return instance;
  }

  public Date getCurrentDate() {
    return new Date();
  }

}
