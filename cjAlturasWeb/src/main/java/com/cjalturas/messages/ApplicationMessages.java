package com.cjalturas.messages;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjalturas.model.control.CourseLogic;


/**
 * Administra los mensajes cargados desde un archivo de propiedades.
 * @author Edison
 */
public class ApplicationMessages {

  private static ApplicationMessages applicationMessages;

  private static Properties propertiesMessages;

  private static final Logger log = LoggerFactory.getLogger(CourseLogic.class);

  private ApplicationMessages() {
    loadMessagesProperties();
  }

  public static ApplicationMessages getInstance() {
    if (applicationMessages == null) {
      applicationMessages = new ApplicationMessages();
    }
    return applicationMessages;
  }

  private void loadMessagesProperties() {
    propertiesMessages = new Properties();
    InputStream input = null;

    try {
      input = getClass().getClassLoader().getResourceAsStream("messages.properties");
      propertiesMessages.load(input);
    } catch (IOException e) {
      log.error("Error cargando el archivo de propiedades de la aplicación", e);
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          log.error("Error cerrando el archivo de propiedades de la aplicación", e);
        }
      }
    }

  }

  public String getMessage(String messageProperty, Object... params) {
    return MessageFormat.format(propertiesMessages.getProperty(messageProperty), params);
  }

}
