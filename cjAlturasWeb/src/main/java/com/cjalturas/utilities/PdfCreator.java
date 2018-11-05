package com.cjalturas.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;


public class PdfCreator {

  public class Rotate extends PdfPageEventHelper {

    protected PdfNumber orientation = PdfPage.PORTRAIT;

    public void setOrientation(PdfNumber orientation) {
      this.orientation = orientation;
    }

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
      writer.addPageDictEntry(PdfName.ROTATE, orientation);
    }
  }

  class Base64ImageProvider extends AbstractImageProvider {

    @Override
    public Image retrieve(String src) {
      int pos = src.indexOf("base64,");
      try {
        if (src.startsWith("data") && pos > 0) {
          byte[] img = Base64.decode(src.substring(pos + 7));
          return Image.getInstance(img);
        } else {
          return Image.getInstance(src);
        }
      } catch (BadElementException ex) {
        return null;
      } catch (IOException ex) {
        return null;
      }
    }

    @Override
    public String getImageRootPath() {
      return null;
    }
  }

  public static void main(String[] args) {
    new PdfCreator().createPDf2(null);
    System.out.println("end");
  }

//  public void createPDf() {
//    try {
//      
//       URL file2 = this.getClass().getResource("templateCert.html");
//      
//      OutputStream file = new FileOutputStream(new File("E:\\tmp\\HTMLtoPDF.pdf"));
////      Document document = new Document();
//      Document document = new Document(PageSize.A5.rotate(), 0f, 0f, 0f, 0f);
//
//      PdfWriter writer = PdfWriter.getInstance(document, file);
////      Rotate event = new Rotate();
////      writer.setPageEvent(event);
////      document.setPageSize(PageSize.A4);
//
////      Rotate event = new Rotate();
////      event.
////      
////      PdfWriter writer = PdfWriter.getInstance(document, file);
//      StringBuilder htmlString = new StringBuilder();
//      htmlString.append(new String("<html><body> This is HMTL to PDF conversion Example<table border='2' align='center'> "));
//      htmlString.append(new String("<tr><td>JavaCodeGeeks</td><td><a href='examples.javacodegeeks.com'>JavaCodeGeeks</a> </td></tr>"));
//      htmlString.append(new String("<tr> <td> Google Here </td> <td><a href='www.google.com'>Google</a> </td> </tr></table></body></html>"));
//
//      String content = htmlString.toString();
//      content = obtenerContenidoArchivo("E:\\tmp\\base2.html");
//
//      document.open();
////      event.setOrientation(PdfPage.LANDSCAPE);
//      
//      
//      
//      
//
//      InputStream is = new ByteArrayInputStream(content.getBytes());
//      XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
//      
//      
//      PdfContentByte canvas = writer.getDirectContentUnder();
//      Image image = Image.getInstance("E:\\tmp\\bck1.jpg");
//      image.scaleAbsolute(PageSize.A5.rotate());
//      image.setAbsolutePosition(0, 0);
//      canvas.addImage(image);
//      
//      
//      document.close();
//      file.close();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//  }

  public void createPDf2(HashMap<String, String> map) {
    try {

//      String str = obtenerContenidoArchivo("E:\\tmp\\base2.html");

      URL file2 = this.getClass().getResource("/templateCert.html");
      String pp = file2.getPath();

      pp = StringUtils.removeStart(pp, "/");
      String str = obtenerContenidoArchivo(pp);

      for (Map.Entry<String, String> entry : map.entrySet()) {
        str = str.replaceAll(entry.getKey(), entry.getValue());
      }

      // step 1
//      Document document = new Document();
      Document document = new Document(PageSize.A5.rotate(), 0f, 0f, 0f, 0f);
//      Document document = new Document(PageSize.A5.rotate(), 0f, 0f, 0f, 0f);

      // step 2
      FileOutputStream os = new FileOutputStream("E:\\tmp\\HTMLtoPDF.pdf");
      PdfWriter writer = PdfWriter.getInstance(document, os);
//      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file2.getPath()));
      // step 3
      document.open();
      // step 4

      // CSS
      CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

      // HTML
      HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
      htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
      htmlContext.setImageProvider(new Base64ImageProvider());

      // Pipelines
      PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
      HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
      CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

      // XML Worker
      XMLWorker worker = new XMLWorker(css, true);
      XMLParser p = new XMLParser(worker);
      p.parse(new ByteArrayInputStream(str.getBytes()));

      PdfContentByte canvas = writer.getDirectContentUnder();
      Image image = Image.getInstance("E:\\tmp\\bck1.jpg");
      image.scaleAbsolute(PageSize.A5.rotate());
      image.setAbsolutePosition(0, 0);
      canvas.addImage(image);

      // step 5
      document.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  
  public DefaultStreamedContent createPDf2AndDownload(HashMap<String, String> map) {
    try {

//      String str = obtenerContenidoArchivo("E:\\tmp\\base2.html");

      URL file2 = this.getClass().getResource("/templateCert.html");
      String pp = file2.getPath();

      pp = StringUtils.removeStart(pp, "/");
      String str = obtenerContenidoArchivo(pp);

      for (Map.Entry<String, String> entry : map.entrySet()) {
        str = str.replaceAll(entry.getKey(), entry.getValue());
      }

      // step 1
//      Document document = new Document();
      Document document = new Document(PageSize.A5.rotate(), 0f, 0f, 0f, 0f);
//      Document document = new Document(PageSize.A5.rotate(), 0f, 0f, 0f, 0f);

      // step 2
//      FileOutputStream os = new FileOutputStream("E:\\tmp\\HTMLtoPDF.pdf");
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      PdfWriter writer = PdfWriter.getInstance(document, os);
//      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file2.getPath()));
      // step 3
      document.open();
      // step 4

      // CSS
      CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

      // HTML
      HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
      htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
      htmlContext.setImageProvider(new Base64ImageProvider());

      // Pipelines
      PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
      HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
      CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

      // XML Worker
      XMLWorker worker = new XMLWorker(css, true);
      XMLParser p = new XMLParser(worker);
      p.parse(new ByteArrayInputStream(str.getBytes()));

      PdfContentByte canvas = writer.getDirectContentUnder();
      Image image = Image.getInstance("E:\\tmp\\bck1.jpg");
      image.scaleAbsolute(PageSize.A5.rotate());
      image.setAbsolutePosition(0, 0);
      canvas.addImage(image);

      // step 5
      document.close();
      
      ByteArrayInputStream stream = new ByteArrayInputStream(os.toByteArray());
      DefaultStreamedContent fileDownload = new DefaultStreamedContent(stream, "application/pdf", "certificadoooo.pdf");
      
      return fileDownload;
      
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  public static String obtenerContenidoArchivo(String rutaAbsolutaArchivo) throws IOException {
    String content = new String(Files.readAllBytes(Paths.get(rutaAbsolutaArchivo)), Charset.forName("UTF-8"));
    return content;
  }
}
