/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */
package part4.chapter14;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class TextExample4 {
	
	public static JTextPane createTextPane() throws BadLocationException {
        String[] string =
                { "Akira Kurosawa",
        		  " (",            //regular
                  "\u9ed2\u6fa4 \u660e",                                   //italic
                  " or ",                                    //bold
                  "\u9ed2\u6ca2 \u660e",                                      //small
                  ", Kurosawa Akira, 23 March 1910 � 6 September 1998) was a " +
			"Japanese film director, producer, screenwriter and editor. " +
			"In a career that spanned 50 years, Kurosawa directed 30 films. " +
			"He is widely regarded as one of the most important and " +
			"influential filmmakers in film history."};

        String[] styles =
                { "bold", "regular", "japanese", "regular",
        		"japanese", "regular"
                };

        JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc);

            for (int i=0; i < string.length; i++) {
                doc.insertString(doc.getLength(), string[i],
                                 doc.getStyle(styles[i]));
        }

        return textPane;
    }

	public static void addStylesToDocument(StyledDocument doc) {
        //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().
                        getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setFontFamily(def, "SansSerif");

        Style regular = doc.addStyle("regular", def);
        
        Style japanese = doc.addStyle("japanese", def);
        StyleConstants.setFontFamily(japanese, "MS PGothic");

        Style s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);
    }
	
	public static void main(String s[]) throws BadLocationException {
		JFrame f = new JFrame("Kurosawa");
	    f.getContentPane().add( createTextPane(), "Center" );

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	System.exit(0);
            }
        });
        f.setSize(new Dimension(300, 150));
        f.setVisible(true);
    }

}
