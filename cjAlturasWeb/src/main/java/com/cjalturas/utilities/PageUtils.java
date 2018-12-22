package com.cjalturas.utilities;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectbooleanbutton.SelectBooleanButton;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.cjalturas.date.DateProvider;


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
   * Limpia el valor de un componente password.
   * passwordText componente password que se quiere limpiar.
   */
  public static void clear(Password component) {
    if (component != null) {
      component.setValue(null);
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
   * Habilita un componente SelectBooleanButton
   * @param componente SelectBooleanButton que se desea habilitar.
   */
  public static void enableComponent(SelectBooleanButton component) {
    if (component != null) {
      component.setDisabled(false);
    }
  }
  
  /**
   * Habilita un componente Password
   * @param componente Password que se desea habilitar.
   */
  public static void enableComponent(Password component) {
    if (component != null) {
      component.setDisabled(false);
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
   * Deshabilita un componente de contraseña
   * @param componente de contraseña a deshabilitar.
   */
  public static void disableComponent(Password component) {
    component.setDisabled(true);
  }

  /**
   * Deshabilita un inputText
   * @param inputText a deshabilitar.
   */
  public static void disableTextbox(InputText inputText) {
    inputText.setDisabled(true);
  }
  
  /**
   * Deshabilida un calendar
   * @param calendar componente calendario que se desea deshabilitar
   */
  public static void disableCalendar(Calendar calendar) {
    calendar.setDisabled(true);
  }
  
  public static void enableCalendar(Calendar calendar) {
    calendar.setDisabled(false);
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

  public static void clearCalendar(Calendar calendar) {
    if (calendar != null) {
      calendar.setValue(DateProvider.getInstance().getCurrentDate());
    }
  }

  public static void clearBooleanButton(SelectBooleanButton selectBooleanButton, Boolean value) {
    if (selectBooleanButton != null) {
      selectBooleanButton.setValue(value);
    }
  }

  public static void clearTextArea(InputTextarea inputTextarea) {
    if (inputTextarea != null) {
      inputTextarea.setValue(null);
    }
  }

  

  

  

}
