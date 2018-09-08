package com.cjalturas.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.primefaces.model.UploadedFile;

/**
 * Set de utilidades basadas en el manejo de archivos.
 * @author Edison
 */
public class FileUtils {

  /**
   * Convierte un archivo a su equivalente en Base64.
   * @param file archivo a convertir.
   * @return cadena codificada en base 64 del archivo.
   */
  public static String convertFileToBase64(UploadedFile file) {
    String base64File = "";
    try (InputStream imageInFile = file.getInputstream()) {
      byte imageData[] = file.getContents();
      imageInFile.read(imageData);
      base64File = Base64.getEncoder().encodeToString(imageData);
    } catch (FileNotFoundException e) {
      System.out.println("File not found" + e);
    } catch (IOException ioe) {
      System.out.println("Exception while reading the File " + ioe);
    }
    return base64File;
  }
}
