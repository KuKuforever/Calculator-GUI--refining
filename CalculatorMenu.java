import javax.swing.JMenuBar ;
import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent ;
import javax.swing.JMenu ;
import javax.swing.JMenuItem ;

public class MenuFactory extends JMenuBar implements ActionListener {
   
   public static final String EXIT = "exit";
   public static final String FILE = "file";
   
   private JMenuItem exitItem   =   null ;
   private JMenu     fileMenu   =   null ;
   
	public MenuFactory() {
   
      setExitItem(EXIT);
      setFileMenu(FILE);
      add(getFileMenu());
      
   }
	
   public void actionPerformed(ActionEvent e){
   
      if(e.getSource() == exitItem)
      System.exit(0);
      
   }
   
   public final JMenu getFileMenu(){
   
      return fileMenu ;
      
   }
   
   public final JMenuItem getExitItem(){
   
      return exitItem ;
      
   }
   
   public final void setExitItem(String exit){
   
      if(exitItem == null){
      
         exitItem = new JMenuItem(exit);
         exitItem.addActionListener(this);
         
      }
   }
   
   public final void setFileMenu(String file){
   
      if(fileMenu == null){
      
         fileMenu = new JMenu(file);
         fileMenu.add(getExitItem());
         
      }
   }   
}