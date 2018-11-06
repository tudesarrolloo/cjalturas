package com.cjalturas.model;

import java.util.HashMap;

public class TypeId {
  
  private static HashMap<String, String> typesId;
  
  static {
    typesId = new HashMap<String, String>();
    typesId.put("Cédula de ciudadanía","CC" );
    typesId.put("Cédula extranjería","CE");
    typesId.put("Pasaporte","PP");
    typesId.put("Tarjeta Identidad","TI");
  }
  
  public static HashMap<String, String> getTypesId() {
    return typesId;
  }

}
