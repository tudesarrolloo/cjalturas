package com.cjalturas.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;


public class FormatUtils {

  private static final String SIMPLE_FORMAT_DATE = "dd/MM/yyyy";

  public static String convertDate(Date date) {
    return convertDate(date, SIMPLE_FORMAT_DATE);
  }

  private static String convertDate(Date date, String formatDate) {
    SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
    return sdf.format(new Date());
  }

}
