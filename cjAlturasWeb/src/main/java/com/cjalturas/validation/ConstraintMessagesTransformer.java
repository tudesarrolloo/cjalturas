package com.cjalturas.validation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.commons.lang3.StringUtils;


public class ConstraintMessagesTransformer {

  private static final String NOT_EMPTY = "org.hibernate.validator.constraints.NotEmpty";

  private static final String NOT_EMPTY_HEADER = "Los siguientes campos no pueden estar vacíos:|";

  private ArrayList<String> fieldsNoEmpty = new ArrayList<>();
  
  private ArrayList<String> otherMessages = new ArrayList<>();

  public <T> void transform(Set<ConstraintViolation<T>> constraints) {

    if (!constraints.isEmpty()) {
      for (ConstraintViolation<T> constraintViolation : constraints) {
        String errorType = constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().getName();
        if (NOT_EMPTY.equals(errorType)) {
          Method mapFieldsMethod;
          try {
            mapFieldsMethod = constraintViolation.getRootBean().getClass().getMethod("getMapFields");
            Map<String, String> mapFields = (Map<String, String>) mapFieldsMethod.invoke(null);
            String keyField = constraintViolation.getPropertyPath().toString();
            if (StringUtils.isNotEmpty(keyField) && mapFields.containsKey(keyField)) {
              fieldsNoEmpty.add(mapFields.get(keyField));
            }
          } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }else {
          StringBuilder strMessage = new StringBuilder();
          strMessage.append(constraintViolation.getPropertyPath().toString());
          strMessage.append(" - ");
          strMessage.append(constraintViolation.getMessage());
          strMessage.append(". \n");
          otherMessages.add(strMessage.toString());
        }

      }

    }
  }

  public String getValidationMessage() {
    StringBuilder strMessage = new StringBuilder();
    if (!fieldsNoEmpty.isEmpty()) {
      strMessage.append(NOT_EMPTY_HEADER);
      String fields = "";
      for (String field : fieldsNoEmpty) {
        fields = fields + field + " - ";
      }
      fields = !fields.isEmpty() ? fields.substring(0, fields.length() - 3) : fields;
      strMessage.append(fields);
    }
    
    for (String otherMessage : otherMessages) {
      strMessage.append(otherMessage);
    }

    return strMessage.toString();
  }

}
