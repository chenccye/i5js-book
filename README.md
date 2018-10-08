[iText 5 Java Samples: book](http://developers.itextpdf.com/content/itext-5-examples/itext-action-second-edition)
---
These are the examples written in the context of the book "iText in Action - Second Editon" (2010)

水印遮挡文字的问题：`img.setAlignment(Image.UNDERLYING);`

iText生成PDF文件，每页显示页码以及总页数的实现  
http://honda418.iteye.com/blog/513746  
```java
import java.io.FileOutputStream;  
  
import com.lowagie.text.Document;  
import com.lowagie.text.Element;  
import com.lowagie.text.ExceptionConverter;  
import com.lowagie.text.Font;  
import com.lowagie.text.PageSize;  
import com.lowagie.text.Paragraph;  
import com.lowagie.text.pdf.BaseFont;  
import com.lowagie.text.pdf.PdfContentByte;  
import com.lowagie.text.pdf.PdfPageEventHelper;  
import com.lowagie.text.pdf.PdfTemplate;  
import com.lowagie.text.pdf.PdfWriter;  
  
public class PdfExport extends PdfPageEventHelper{  
      
    public PdfTemplate tpl;  
    public BaseFont bf;  
  
    public static void main(String[] args) {  
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);  
        try {             
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\HelloItext.pdf"));  
            writer.setPageEvent(new PdfExport());  
              
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
      
            document.open();  
              
            Paragraph title = new Paragraph("测试内容。。。。", new Font(bfChinese,15));  
            title.setAlignment(Element.ALIGN_CENTER);  
            document.add(title);      
        } catch (Exception de) {  
            de.printStackTrace();  
        }  
        document.close();  
    }  
      
    public void onOpenDocument(PdfWriter writer, Document document) {  
        try {  
            tpl = writer.getDirectContent().createTemplate(100, 100);  
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);     
        }  
        catch(Exception e) {  
            throw new ExceptionConverter(e);  
        }  
    }  
      
    public void onEndPage(PdfWriter writer, Document document) {  
       //在每页结束的时候把“第x页”信息写道模版指定位置  
        PdfContentByte cb = writer.getDirectContent();  
        cb.saveState();  
        String text = "第" + writer.getPageNumber() + "页,共";  
        cb.beginText();  
        cb.setFontAndSize(bf, 8);  
        cb.setTextMatrix(460, 786);//定位“第x页,共” 在具体的页面调试时候需要更改这xy的坐标  
        cb.showText(text);  
        cb.endText();  
        cb.addTemplate(tpl, 492, 786);//定位“y页” 在具体的页面调试时候需要更改这xy的坐标  
  
        cb.saveState();  
        cb.stroke();  
        cb.restoreState();         
        cb.closePath();//sanityCheck();  
    }  
      
    public void onCloseDocument(PdfWriter writer, Document document) {  
       //关闭document的时候获取总页数，并把总页数按模版写道之前预留的位置  
       tpl.beginText();  
       tpl.setFontAndSize(bf, 8);  
       tpl.showText(Integer.toString(writer.getPageNumber() - 1)+"页");  
       tpl.endText();  
       tpl.closePath();//sanityCheck();  
    }  
}  
```
