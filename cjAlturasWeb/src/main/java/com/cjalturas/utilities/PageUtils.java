package com.cjalturas.utilities;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;


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
   * Limpia el valor de un inputNumber.
   * 
   * @param inputText campo de texto que se quiere limpiar.
   */
  public static void clearTextBox(InputNumber inputNumber) {
    if (inputNumber != null) {
      inputNumber.setValue(null);
    }
  }

  /**
   * Limpia el valor de un combobox.
   * 
   * @param combobox campo de combo que se quiere limpiar.
   */
  public static void clearComboBox(SelectOneMenu combo) {
    combo.resetValue();
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
   * Habilita un inputNumber
   * @param inputNumber a habilitar.
   */
  public static void enableTextbox(InputNumber inputNumber) {
    inputNumber.setDisabled(false);
  }
  
  /**
   * Habilita un comboBox
   * @param comboBox a habilitar.
   */
  public static void enableComboBox(SelectOneMenu comboBox) {
    comboBox.setDisabled(false);
  }

  /**
   * Deshabilita un inputText
   * @param inputText a deshabilitar.
   */
  public static void disableTextbox(InputText inputText) {
    inputText.setDisabled(true);
  }
  
  /**
   * Deshabilita un inputNumber
   * @param inputNumber a deshabilitar.
   */
  public static void disableTextbox(InputNumber inputNumber) {
    inputNumber.setDisabled(true);
  }

  /**
   * Deshabilita un comboBox
   * @param comboBox a deshabilitar.
   */
  public static void disableComboBox(SelectOneMenu comboBox) {
    comboBox.setDisabled(true);
  }

}
