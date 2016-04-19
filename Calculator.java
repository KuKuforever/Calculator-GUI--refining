import java.awt.Color ;
import java.awt.Container ;
import java.awt.Cursor ;
import java.awt.FlowLayout ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;
import java.awt.event.KeyEvent ;
import java.awt.event.KeyListener ;
import java.awt.event.MouseEvent ;
import java.awt.event.MouseListener ;
import java.awt.GridLayout ;
import java.text.DecimalFormat ;
import javax.swing.JButton ;
import javax.swing.JFrame  ;
import javax.swing.JMenu ;
import javax.swing.JMenuBar ;
import javax.swing.JMenuItem ;
import javax.swing.JPanel ;
import javax.swing.JOptionPane ;
import javax.swing.JTextField ;

public class Calculator extends JFrame implements ActionListener, MouseListener, KeyListener{
public static final String DEFAULT_TITLE       =   "Java Calculator v1.00" ;
public static final int    BUTTON_COLUMNS      =   4 ;
public static final int    BUTTON_ROWS         =   5 ;
public static final int    DEFAULT_HEIGHT      =   250 ;
public static final int    DEFAULT_WIDTH       =   411;

private Color       bufferColor          =   null ;
private JPanel      buttonPanel          =   null ;
private JTextField  displayText          =   null ;
private JMenu       calculatorMenu       =   null ;
private JMenuBar    calculatorMenuBar    =   null ;
private JMenuItem   calculatorMenuItem   =   null ;
private String      title                =   DEFAULT_TITLE ;
private char        lastOperation        =   ' ' ;
private double      buffer               =   0.0 ;
private int         height               =   DEFAULT_HEIGHT ;
private int         width                =   DEFAULT_WIDTH ;

   public Calculator(){
   
      createCalculator() ;
   }
   
   @Override
   public void actionPerformed(ActionEvent e){
   char buttonValue   =   ' ' ; 
     
      if(e.getSource() == calculatorMenuItem)
         System.exit(0) ;         
      else buttonValue   =   ((JButton)e.getSource()).getText().charAt(0) ;
       
      switch (buttonValue) {
         case 'B':   if (displayText.getText().length() > 1) {
                        displayText.setText(
                           returnDisplayValue(displayText.getText().substring(0, (displayText.getText().length() - 1)))
                        ) ;
                        break ;
                     }
         case 'C':   displayText.setText("0") ;
                     buffer           =   0.0 ;
                     break ;
         case '%':   displayText.setText(
                        returnDisplayValue(String.valueOf((Double.parseDouble(displayText.getText()) / 100)))
                     ) ;
                     buffer           =   Double.parseDouble(displayText.getText()) ;
                     break ;
         case '+': case '-':  case '*':  case '/' :
                     buffer           =   Double.parseDouble(displayText.getText()) ;
                     displayText.setText("") ;  
                     lastOperation    =   buttonValue ;                
                     break ;
         case 'S':   buffer           =   Math.sqrt(Double.parseDouble(displayText.getText())) ;
                     displayText.setText(returnDisplayValue(String.valueOf(buffer))) ;
                     lastOperation    =   buttonValue ;
                     break ;
         case '=':   if ( isValidOperation(lastOperation)) {
                        buffer        =   calculate(buffer, Double.parseDouble(displayText.getText()), lastOperation) ;
                        displayText.setText("") ;
                        displayText.setText(returnDisplayValue(String.valueOf(buffer))) ;
                        lastOperation =   ' ' ;
                     }
                     break ;
         case '.':   if (displayText.getText().indexOf('.') == -1)
                        displayText.setText(displayText.getText() + buttonValue) ;
                     break ;
         default :   if (Character.isDigit(buttonValue) || (buttonValue == '.')) {
                        if ((displayText.getText().indexOf('.') == 0) || Character.isDigit(buttonValue))
                           displayText.setText( returnDisplayValue(displayText.getText() + buttonValue)) ;
                     }
      }
   }
      
   double calculate(double loperand, double roperand, char operator) {
   double result = 0.0 ;
      
         switch (operator) {
            case '+': result   =   loperand + roperand ;
                      break ;
            case '-': result   =   loperand - roperand ;
                      break ;
            case '*': result   =   loperand * roperand ;
                      break ;
            case '/': result   =   loperand / roperand ;
                      break ;
            default:  throw new IllegalArgumentException("calculate: Illegal operator encountered") ;   
         }
         return result ;
   }
    
   private JButton[] createButtons() {
   JButton calculatorButtons[]   =   null ;

      calculatorButtons       =   new JButton[BUTTON_COLUMNS * BUTTON_ROWS] ;
		
      calculatorButtons[0]    =    ButtonFactory.createButton("7", Color.LIGHT_GRAY) ;
      calculatorButtons[1]    =    ButtonFactory.createButton("8", Color.LIGHT_GRAY) ;
      calculatorButtons[2]    =    ButtonFactory.createButton("9", Color.LIGHT_GRAY) ;
      calculatorButtons[3]    =    ButtonFactory.createButton("+", Color.PINK) ;
      calculatorButtons[4]    =    ButtonFactory.createButton("4", Color.LIGHT_GRAY) ;
      calculatorButtons[5]    =    ButtonFactory.createButton("5", Color.LIGHT_GRAY) ;
      calculatorButtons[6]    =    ButtonFactory.createButton("6", Color.LIGHT_GRAY) ;
      calculatorButtons[7]    =    ButtonFactory.createButton("-", Color.PINK) ;
      calculatorButtons[8]    =    ButtonFactory.createButton("1", Color.LIGHT_GRAY) ;
      calculatorButtons[9]    =    ButtonFactory.createButton("2", Color.LIGHT_GRAY) ;
      calculatorButtons[10]   =    ButtonFactory.createButton("3", Color.LIGHT_GRAY) ;
      calculatorButtons[11]   =    ButtonFactory.createButton("*", Color.PINK) ;
      calculatorButtons[12]   =    ButtonFactory.createButton(".", Color.LIGHT_GRAY) ;
      calculatorButtons[13]   =    ButtonFactory.createButton("0", Color.LIGHT_GRAY) ;
      calculatorButtons[14]   =    ButtonFactory.createButton("Sqrt", Color.PINK) ;
      calculatorButtons[15]   =    ButtonFactory.createButton("/", Color.PINK) ;
      calculatorButtons[16]   =    ButtonFactory.createButton("Clear", Color.ORANGE) ;
      calculatorButtons[17]   =    ButtonFactory.createButton("BackSpace", Color.ORANGE) ;
      calculatorButtons[18]   =    ButtonFactory.createButton("%", Color.PINK) ;
      calculatorButtons[19]   =    ButtonFactory.createButton("=", Color.GREEN) ;
      
      for(int i=0 ; i<(BUTTON_COLUMNS * BUTTON_ROWS); i++) {
         calculatorButtons[i].addActionListener(this) ;
         calculatorButtons[i].addMouseListener(this) ;
      }
      
      return calculatorButtons ;
   }
   

      
         
   
   private void createCalculator() {
   Container  contentPane             =   null ;
   JButton    calculatorButtons[]     =   null ;
   JPanel     calculatorButtonPanel   =   null ;
   
      if ( ((calculatorButtons       =   createButtons()) != null) &&
           ((displayText             =   createDisplayText()) != null) &&
           ((calculatorButtonPanel   =   createButtonPanel(calculatorButtons)) != null) ) {
           
         setTitle(title) ;
         super.setSize(width,height) ;
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
      
         contentPane = getContentPane() ;
         contentPane.setLayout(new FlowLayout()) ;
         contentPane.add(displayText) ;
         contentPane.add(calculatorButtonPanel) ;
         addWindowListener(new WindowHandler()) ;
      
         getRootPane().setJMenuBar(createMenu()) ;
      } else
         throw new IllegalArgumentException("createCalculator: Illegal argument encountered") ;   
   }
   
   private JTextField createDisplayText() {
   JTextField displayText   =   null ;
   
      displayText   =   new JTextField(15) ;
      displayText.setText("0") ;
      displayText.setHorizontalAlignment(JTextField.RIGHT) ;
      displayText.addKeyListener(this) ;
      displayText.setEditable(false) ;
      
      return displayText ;
   }
   
   private JPanel createButtonPanel(JButton calculatorButtons[]) {
   JPanel buttonPanel   =   null ;
   
      if( (calculatorButtons != null) && (calculatorButtons.length == (BUTTON_COLUMNS * BUTTON_ROWS)) ) {
         buttonPanel   =   new JPanel() ;
         buttonPanel.setLayout(new GridLayout(BUTTON_ROWS,BUTTON_COLUMNS)) ;
         for (JButton button : calculatorButtons)
            buttonPanel.add(button) ;
      } else
         throw new IllegalArgumentException("createButtonPanel: Illegal argument encountered") ;
         
      return buttonPanel ;
   }
   
   private JMenuBar createMenu() {
      calculatorMenu       =   MenuFactory.createMenu("File") ; 
      calculatorMenuBar    =   MenuFactory.createMenuBar() ;
      calculatorMenuItem   =   MenuFactory.createMenuItem("Exit") ;
      calculatorMenuItem.setMnemonic(KeyEvent.VK_E) ;
      calculatorMenuItem.addActionListener(this) ;
      calculatorMenu.add(calculatorMenuItem) ;
      calculatorMenuBar.add(calculatorMenu) ;

      return calculatorMenuBar ;
   }
   
   @Override
   public final int getHeight(){
      return height ;
   }
   
   @Override
   public final int getWidth(){
      return width ;
   }
	
   private boolean isValidOperation(char operation) {
   boolean isValid   =   false ;
	
      if ( (operation == '+') || (operation == '-') || (operation == '/') || (operation == '*') )
         isValid   =   true ;
		
      return isValid ;
   }
	
   @Override
   public void keyPressed(KeyEvent e) { }
	
   @Override
   public void keyReleased(KeyEvent e) { }
	
   @Override
   public void keyTyped(KeyEvent e) { }
	
   private String returnDisplayValue(String displayThisValue) {
   DecimalFormat calculatorFormat   =   null ;
   Double aNumber ;
	
      calculatorFormat   =   new DecimalFormat("#####0.######;-#####0.######") ;
      displayThisValue   =   calculatorFormat.format( (Double.parseDouble(displayThisValue.replaceAll(",","")))) ;
		
      return displayThisValue ;
   }
	
   @Override
   public void mouseClicked(MouseEvent evt) { }
	
   @Override
   public void mousePressed(MouseEvent evt) { }
	
   @Override
   public void mouseReleased(MouseEvent evt) { }

   @Override
   public void mouseEntered(MouseEvent evt) {
	if(evt.getComponent() instanceof JButton){
	   getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)) ;
	   bufferColor   =  evt.getComponent().getForeground() ;
	   evt.getComponent().setForeground(Color.WHITE) ; 
	}
   }
	
   @Override
   public void mouseExited(MouseEvent evt) { 
	if(evt.getComponent() instanceof JButton) {
	   getContentPane().setCursor(Cursor.getDefaultCursor()) ;
	   evt.getComponent().setForeground(bufferColor) ;
	}
   }


   
   public final void setHeight(int newHeightValue){
      if (newHeightValue > 0)
         height   =   newHeightValue ;
   }
   
   @Override
   public void setSize(int newHeightValue, int newWidthValue){
      if((newHeightValue > 0) && (newWidthValue > 0)){
         setHeight(newHeightValue) ;
         setWidth(newWidthValue) ;
         super.setSize(getWidth(), getHeight()) ;
      }
   }
   
   @Override
   public void setTitle(String newTitle){
      if((newTitle != null) && (newTitle.length() > 0)){
         title   =   newTitle ;
         super.setTitle(title) ;
      }
   }
   
   public final void setWidth(int newWidthValue){
      if(newWidthValue > 0)
         width   =   newWidthValue ;
   }
}

//Kan Xue
