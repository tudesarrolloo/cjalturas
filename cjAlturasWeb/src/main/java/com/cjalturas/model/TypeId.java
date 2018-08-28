package com.cjalturas.model;

import java.util.HashMap;

public class TypeId {
  
  private static HashMap<String, String> typesId;
  
  static {
    typesId = new HashMap<String, String>();
    typesId.put("CC", "Cédula de ciudadanía");
    typesId.put("CE","Cédula extranjería");
    typesId.put("PP","Pasaporte");
  }
  
  public static HashMap<String, String> getTypesId() {
    return typesId;
  }

}
