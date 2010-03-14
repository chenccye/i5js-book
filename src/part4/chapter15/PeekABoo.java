/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */
package part4.chapter15;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfLayer;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * This example was written by Bruno Lowagie. It is part of the book 'iText in
 * Action' by Manning Publications. 
 * ISBN: 1932394796
 * http://www.1t3xt.com/docs/book.php 
 * http://www.manning.com/lowagie/
 */

public class PeekABoo {
	public static String RESULT1 = "results/part4/chapter15/peek-a-boo1.pdf";
	public static String RESULT2 = "results/part4/chapter15/peek-a-boo2.pdf";

	
	public void createPdf(String filename, boolean on) throws DocumentException, IOException {
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document,
			new FileOutputStream(filename));
		writer.setViewerPreferences(PdfWriter.PageModeUseOC);
		writer.setPdfVersion(PdfWriter.VERSION_1_5);
		// step 3
		document.open();
		// step 4
		PdfLayer layer = new PdfLayer("Do you see me?", writer);
		layer.setOn(on);
		BaseFont bf = BaseFont.createFont();
		PdfContentByte cb = writer.getDirectContent();
		cb.beginText();
		cb.setTextMatrix(50, 790);
		cb.setLeading(24);
		cb.setFontAndSize(bf, 18);
		cb.showText("Do you see me?");
		cb.beginLayer(layer);
		cb.newlineShowText("Peek-a-Boo!!!");
		cb.endLayer();
		cb.endText();
		// step 5
		document.close();
	}
	
	/**
	 * A simple example with optional content.
	 * 
	 * @param args
	 *            no arguments needed here
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static void main(String[] args) throws DocumentException, IOException {
		PeekABoo peekaboo = new PeekABoo();
		peekaboo.createPdf(RESULT1, true);
		peekaboo.createPdf(RESULT2, false);
	}
}
