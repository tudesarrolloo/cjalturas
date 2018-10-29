package com.cjalturas.exceptions;

import java.io.Serializable;


public class CustomException extends Exception implements Serializable {

  /** Serial id. */
  private static final long serialVersionUID = -2329135486894310890L;

  /** Mensaje personalizado para el cliente. */
  private String clientMessage;

  public CustomException(String message) {
    super(message);
  }

  public CustomException(String message, Throwable cause) {
    super(message, cause);
  }

  public CustomException(String message, String clientMessage, Throwable cause) {
    super(message, cause);
    this.clientMessage = clientMessage;
  }

  public String getClientMessage() {
    return clientMessage;
  }

  public void setClientMessage(String clientMessage) {
    this.clientMessage = clientMessage;
  }

}
