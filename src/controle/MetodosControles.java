package controle;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MetodosControles extends PlainDocument{
    
    public void insertString( int offs, String str, AttributeSet a ) throws BadLocationException {
         super.insertString( offs, str.toUpperCase(), a );
      }
}
