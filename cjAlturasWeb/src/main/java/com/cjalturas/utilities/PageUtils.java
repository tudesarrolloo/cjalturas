package com.cjalturas.utilities;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;


/**
 * Utilidades para ayudar en las acciones sobre las páginas.
 * @author Edison
 */
public class PageUtils {

  /**
   * Limpia el valor de un inputText.
   * 
   * @param inputText campo de texto que se quiere limpiar.
   */
  public static void clearTextBox(InputText inputText) {
    if (inputText != null) {
      inputText.setValue(null);
    }
  }

  /**
   * Deshabilita un botón
   * @param button botón a deshabilitar.
   */
  public static void disableButton(CommandButton button) {
    if (button != null) {
      button.setDisabled(true);
    }
  }

  /**
   * Habilita un botón
   * @param button botón a deshabilitar.
   */
  public static void enableButton(CommandButton button) {
    if (button != null) {
      button.setDisabled(false);
    }
  }

  /**
   * Habilita un inputText
   * @param inputText a habilitar.
   */
  public static void enableTextbox(InputText inputText) {
    inputText.setDisabled(false);
  }

  /**
   * Deshabilita un inputText
   * @param inputText a deshabilitar.
   */
  public static void disableTextbox(InputText inputText) {
    inputText.setDisabled(true);
  }

}
