package com.cjalturas.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Clase utilitaria para realizar formatos generales.
 * @author Edison
 */
public class FormatUtils {

  /** Formato de fecha simple sin tiempo. */
  public static final String SIMPLE_FORMAT_DATE = "dd/MM/yyyy";

  /** Formato de fecha usado en la generación del certificado. */
  public static final String CERTIFICATE_DATE = "dd 'de' MMMMM 'de' yyyy";

  /**
   * Realiza la conversión de una fecha al formato de fechas por defecto.
   * @param date fecha que se desea convertir a String.
   * @return cadena de texto con el formato de fecha esperado.
   */
  public static String convertDate(Date date) {
    return convertDate(date, SIMPLE_FORMAT_DATE);
  }

  /**
   * Realiza la conversión de una fecha a un formato especifico.
   * @param date fecha que se desea convertir a String.
   * @param formatDate formato de fecha al que se desea convertir.
   * @return cadena de texto con el formato de fecha recibido.
   */
  public static String convertDate(Date date, String formatDate) {
    Locale esLocale = new Locale("es", "CO");
    SimpleDateFormat sdf = new SimpleDateFormat(formatDate, esLocale);
    return sdf.format(date);
  }

}
