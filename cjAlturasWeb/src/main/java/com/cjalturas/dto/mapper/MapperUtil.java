package com.cjalturas.dto.mapper;

public class MapperUtil {

  public static String getValue(String value) {
    return value != null ? value : null;
  }

  public static boolean getBooleanValueFromString(String enabled) {
    return (enabled != null && enabled == "1") ? true : false;
  }

}
