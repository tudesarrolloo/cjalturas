package com.cjalturas.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 * Administra todos los mensajes que son mostrados en las páginas de la aplicación.
 * @author Edison
 */
public class ZMessManager extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public final static String ALL = "All ";

  public final static String ENTCHILD = "related tables(childs)";

  public final static String FOREIGNDATA = "foreign classes data: ";

  public static String ENTITY_SUCCESFULLYSAVED = "Entity succesfully saved";

  public static String ENTITY_SUCCESFULLYDELETED = "Entity succesfully deleted";

  public static String ENTITY_SUCCESFULLYMODIFIED = "Entity succesfully modified";

  public static String ENTITY_WITHSAMEKEY = "Another Entity with the same key was found";

  public static String ENTITY_NOENTITYTOUPDATE = "No Entity was found, with the typed key ";

  public ZMessManager() {
  }

  public ZMessManager(String exception) {
    super(exception);
  }

  public class NotValidFieldException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public NotValidFieldException(String info) {
      super("The value for the field: \"" + info + "\" is not valid");
    }
  }

  public class NullEntityExcepcion extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public NullEntityExcepcion(String info) {
      super("The " + info + " Entity can not be null or empty");
    }
  }

  public class EmptyFieldException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public EmptyFieldException(String info) {
      super("The value for the field: \"" + info + "\" can not be null or empty");
    }
  }

  public class NotValidFormatException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public NotValidFormatException(String info) {
      super("The Format or length for the field: \"" + info + "\" is not valid");
    }
  }

  public class DeletingException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public DeletingException(String info) {
      super("The Entity you are trying to delete " + "may have related information, " + "please before trying again, " + "check the data on the entity, \""
          + info + "\"");
    }
  }

  public class ForeignException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public ForeignException(String info) {
      super("There was no data related with the input \"" + info + "\"");
    }
  }

  public class GettingException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public GettingException(String info) {
      super("There was an exception getting " + info);
    }
  }

  public class FindingException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public FindingException(String info) {
      super("There was an exception trying to find " + info);
    }
  }

  public class DuplicateException extends ZMessManager {
    private static final long serialVersionUID = 1L;

    public DuplicateException(String message) {
      super(message);
    }
  }

  /**
   * Crea mensaje debido al guardado existoso para una entidad.
   * @param message Mensaje a mostrar
   */
  public static void addSaveMessage(String message) {
    addInfoMessage(message);
  }

  /**
   * Crea mensaje debido a la modificación existosa para una entidad.
   * @param message Mensaje a mostrar
   */
  public static void addEditMessage(String message) {
    addInfoMessage(message);
  }

  /**
   * Crea mensaje debido a la eliminació existosa para una entidad.
   * @param message Mensaje a mostrar
   */
  public static void addDeleteMessage(String message) {
    addInfoMessage(message);
  }

  /**
   * Se crea un mensaje de información para una pantalla.
   * @param message mensaje informativo que se va a mostrar.
   */
  private static void addInfoMessage(String message) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
  }

  /**
   * Se crea un mensaje de error para una pantalla.
   * @param message mensaje de error a mostrar.
   */
  public static void addErrorMessage(String message) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
  }

}
