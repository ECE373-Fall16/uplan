import javax.imageio.ImageIO;


import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

class LoginPage extends JPanel implements ActionListener{
	static JFrame frmLogin;
	static Container pane;
	static JLayeredPane pnlLogin;
	static JLabel lblLogo,lblPassword,lblUser;
	static int width, height,logowidth,logoheight,frmwidth,frmheight;
	static private Client c;
	static LinkedList<JButton> btnAssignments;
	static JButton btnLogin, btnCreate;
	static JTextField user;
	static JPasswordField password;
	static String userinput,passwordinput;
	static String username;
	static boolean result;
	

	public LoginPage() {
    	c = new Client();
	}
	public String LoginUser(){
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}
		String[] both = new String[2];
		
		//GET SCREENSIZE FOR USER'S COMPUTER
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)screenSize.getWidth() ;
		height = (int)screenSize.getHeight()-((int)(screenSize.getHeight()/24.6));
		
		//COLOR CREATION FOR ELEMENTS
        Color BabyBlue =  new Color(157,205,255);
        Color back = new Color(125,10,10);
		
        //FRAME FORMATTING
		frmLogin = new JFrame ("UPlan"); //Create frame
		frmwidth = width/((int)(width/341.5));
		frmheight = (int)(6*height/8);
		frmLogin.setSize(frmwidth,frmheight);//Set screen to full
		frmLogin.setLocation((width/4)+(width/10), height/8);
        pane = frmLogin.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //IMAGE
        BufferedImage img = null;
		try {img = ImageIO.read(this.getClass().getResource("Logo.png"));} 
		catch (IOException e) {e.printStackTrace();}
		logowidth = (int)(frmwidth*.75);
        logoheight = frmheight/4;
        Image scaled = img.getScaledInstance(logowidth,logoheight, Image.SCALE_FAST);
        ImageIcon icon = new ImageIcon(scaled);
		
		//BUTTONS
		btnLogin = new JButton ("Login");
        btnCreate = new JButton ("Create Account");
        btnLogin.setActionCommand("login");
        btnCreate.setActionCommand("create");

        JTextField user = new JTextField();
        JPasswordField password = new JPasswordField(40);
        
        btnLogin.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		  userinput = user.getText();
        		  passwordinput = String.valueOf(password.getPassword());
        		  both[0] = userinput;
        		  both[1] = passwordinput;
        		  result = c.Login(both);
        		  }});
        btnCreate.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    
        	  } 
        	} );
        
        if(result = true){
        	username = both[0];
		  }
        else if(result = false){
        	username = null;
        }
        
        //PANEL
		pnlLogin = new JLayeredPane();
        
        //LABELS
        lblLogo = new JLabel(icon);
        lblUser = new JLabel("Username");
        lblPassword = new JLabel("Password");
        
        
        pane.add(pnlLogin);
        pnlLogin.add(btnLogin);
        pnlLogin.add(btnCreate);
        pnlLogin.add(lblLogo);
        pnlLogin.add(password);
        pnlLogin.add(user);
        pnlLogin.add(lblUser);
        pnlLogin.add(lblPassword);
        //BOUNDS
        int gap = (int)((frmwidth-logowidth)/1.4);
        pnlLogin.setBounds(0,0,frmwidth, frmheight);
        btnLogin.setBounds((int)(width/22.76),frmheight-((int)(height/4.372)),((int)(width/11.3833)),((int)(height/18.425)));
        btnCreate.setBounds((int)(width/6.209),frmheight-((int)(height/4.372)),((int)(width/11.3833)),((int)(height/18.425)));
        lblLogo.setBounds(gap,(int)(height/14.74), logowidth, logoheight);
        user.setBounds((int)(frmwidth/2.5), frmheight/2-60,100,((int)(height/30)));
        password.setBounds((int)(frmwidth/2.5), frmheight/2,100,((int)(height/30))) ;
        lblPassword.setBounds((int)(frmwidth/2.5), frmheight/2, gap, gap);
        lblUser.setBounds((int)(frmwidth/2.5), frmheight/2-60, gap, gap);
        
        //COLOR
        frmLogin.getContentPane().setBackground(Color.DARK_GRAY);
        pnlLogin.setBackground(Color.DARK_GRAY);
        lblPassword.setForeground(Color.WHITE);
        lblUser.setForeground(Color.WHITE);

        
        password.setEchoChar('*');
        //MAKE VISIBLE
        frmLogin.setMinimumSize(new Dimension(400,400));				//set minimize size
        frmLogin.setVisible(true);		
        
        System.out.println(username);
        return username;
		

        
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}    
	    
	    
	    
}