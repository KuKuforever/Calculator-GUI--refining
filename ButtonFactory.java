import java.awt.Color ;
import javax.swing.JButton ;

public class ButtonFactory {
  
	ButtonFactory() {	}
	
	public static JButton createButton(String label, Color color){
   JButton newButton   =   null ;
   
      newButton = new JButton(label) ;
      newButton.setBackground(color) ;
   
	   return newButton ;
	}
}