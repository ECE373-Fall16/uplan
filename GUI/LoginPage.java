import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.IOException;

import java.util.*;

@SuppressWarnings("serial")
class LoginPage extends JPanel implements ActionListener{
	static JFrame frmLogin,frmCreate;
	static Container pane,pane1;
	static JLayeredPane pnlLogin;
	static JLabel lblLogo,lblPassword,lblUser,lblUsername,lblEmail,lblNameofUser,lblCurrentBedtime,lblCurrentWakeuptime,lblWrongInformation;
	static int width, height,logowidth,logoheight,frmwidth,frmheight,frmwidth2,frmheight2;
	static private Client c;
	static LinkedList<JButton> btnAssignments;
	static JButton btnLogin, btnCreate,btnCancel,btnCreate1;
	static JTextField user,JTextEmail,JTextName, JTextPassword,JTextUsername;
	static JPasswordField password;
	static String userinput,passwordinput;
	static String username;
	static JComboBox JComboStarttime, JComboEndtime;
	static boolean result;
	boolean done,done2;
    BufferedImage img = null;
    static String[] times,dates,Assignments,Events;
    static Integer[] timeneeded,priority;
    static Color Babyblue,back;
    private static HomePage1 a;

	public LoginPage() {
    	c= new Client();
    	a = new HomePage1();
	}
	public void LoginUser(){
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
        Babyblue =  new Color(157,205,255);
        back = new Color(125,10,10);
		
        //FRAME FORMATTING
		frmLogin = new JFrame ("UPlan"); //Create frame
		frmwidth = width/((int)(width/341.5));
		frmheight = (int)(6*height/8);
		frmLogin.setSize(frmwidth,frmheight);//Set screen to full
		frmLogin.setLocation((width/4)+(width/10), height/8);
        pane = frmLogin.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createComboBoxes();
        
        //IMAGE
		try {img = ImageIO.read(this.getClass().getResource("Logo.png"));} 
		catch (IOException e) {e.printStackTrace();}
		logowidth = (int)(frmwidth*.75);
        logoheight = frmheight/4;
        Image scaled = img.getScaledInstance(logowidth,logoheight, Image.SCALE_FAST);
        ImageIcon icon = new ImageIcon(scaled);
		
		//BUTTONS
		btnLogin = new JButton ("Login");
        btnCreate = new JButton ("Create Account");
        btnCancel = new JButton("Cancel");
        btnCreate1 = new JButton("Create");
        
        JTextField user = new JTextField();
        JPasswordField password = new JPasswordField(40);
        JTextField JTextName = new JTextField();
        JTextField JTextEmail = new JTextField();
        JTextField JTextPassword = new JTextField();
        JTextField JTextUsername = new JTextField();
        JComboBox<String>JComboStarttime = new JComboBox<>(times);
        JComboBox<String>JComboEndtime = new JComboBox<>(times);
        
        //PANEL
		pnlLogin = new JLayeredPane();
        
        //LABELS
        lblLogo = new JLabel(icon);
        lblUser = new JLabel("Username");
        lblPassword = new JLabel("Password");
        lblUsername = new JLabel("Choose Username:");
        lblEmail= new JLabel("Enter Email:"); 
        lblNameofUser = new JLabel("Enter Name(First_Last):");
        lblCurrentBedtime = new JLabel("Choose Bedtime:");
        lblCurrentWakeuptime = new JLabel("Choose Wake Up Time:");
        lblWrongInformation = new JLabel("You Got Something Wrong Bitch");
        lblUsername.setForeground(Color.WHITE);
        lblEmail.setForeground(Color.WHITE);
        lblNameofUser.setForeground(Color.WHITE);
        lblCurrentBedtime.setForeground(Color.WHITE);
        lblCurrentWakeuptime.setForeground(Color.WHITE);
        
        
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
      btnLogin.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      		  a.DisplayHomePage();
      		  a.DisplayCalendarEvents();
      		  /*userinput = user.getText();
      		  passwordinput = String.valueOf(password.getPassword());
      		  both[0] = userinput;
      		  both[1] = passwordinput;
      		  result = c.Login(both);
      		  if(result = true){
      	      	username = both[0];
      	      	System.out.println(both[0]+"  "+both[1]);
      	      	done = true;
      			  }
      	      	else if(result = false){
      	      	frmLogin.setVisible(false);
      	      	a.DisplayHomePage();
      	      	a.DisplayCalendarEvents();
      	      	username = null;
      	      	done = false;
      	      */}
      		  });
      btnCreate.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      		  	frmCreate = new JFrame ("Account Information"); //Create frame
      	    	int frmwidth2 = (int)(width/3);
      	  	  	int frmheight2 = (int)(height/1.25);
      	  	  	frmCreate.setSize(frmwidth2,frmheight2);//Set screen to full
      	  	  	frmCreate.setLocation((width/3), height/8);
      	  	  	pane1 = frmCreate.getContentPane(); //Get content pane
      	  	  	pane1.setLayout(null); //Apply null layout
      	  	  	frmCreate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      	  	  	frmCreate.setBackground(back);
      	  	  	pane1.setBackground(Color.DARK_GRAY);
      	  	  	int imgwidth = (int)(frmwidth2/1.33);
      	  	  	int imgheight = (int)(frmheight2/5);
      	  	  	Image scaled1 = img.getScaledInstance(imgwidth,imgheight, Image.SCALE_FAST);
      	  	  	ImageIcon icon = new ImageIcon(scaled1);
      	  	  	lblLogo = new JLabel(icon);
      	        lblPassword = new JLabel("Enter a Password");
      	  	  	pane1.add(lblUsername);
      	  	  	pane1.add(lblEmail);
      	  	  	pane1.add(lblNameofUser);
      	  	  	pane1.add(lblCurrentBedtime);
      	  	  	pane1.add(lblCurrentWakeuptime);
      	  	  	pane1.add(lblLogo);
      	  	  	pane1.add(btnCancel);
      	  	  	pane1.add(btnCreate1);
      	 	  	pane1.add(lblPassword);
      	 	  	pane1.add(JTextPassword);
      	  	  	pane1.add(JComboStarttime);
      	 	  	pane1.add(JComboEndtime);
      	 	  	pane1.add(JTextEmail);
      	 	  	pane1.add(JTextName);
      	 	  	pane1.add(JTextUsername);
      	 	  	int col = (int)(frmwidth2/7);
      	  	  	int boxw = (int)(width/13.6);
      	  	  	int gap = (int) (height/18.425);
      		  	int boxh = (int)(height/36.85);
      	   		
      	     	lblLogo.setBounds(frmwidth2/8,frmheight2/30,imgwidth,imgheight);
      		  	lblUsername.setBounds(col,gap*6,boxw*4,boxh);
      	   		lblEmail.setBounds(col,gap*5,boxw*4,boxh);
          		lblNameofUser.setBounds(col,gap*4,boxw*4,boxh);
   	     		lblCurrentBedtime.setBounds(col,gap*7,boxw*4,boxh);
      	     	lblCurrentWakeuptime.setBounds(col,gap*8,boxw*4,boxh);
      	     	lblPassword.setBounds(col,gap*9,boxw*4,boxh);
      	   		btnCreate1.setBounds(frmwidth2/12,(int)(frmheight2*7/10),(int)(frmwidth2/3), (int)(frmheight2/10));
      	   		btnCancel.setBounds(frmwidth2*7/12, (int)(frmheight2*7/10), (int)(frmwidth2/3),(int)(frmheight2/10));
      	   		JComboStarttime.setBounds(col+boxw*2, gap*7, boxw, boxh);
          		JComboEndtime.setBounds(col+boxw*2, gap*8, boxw,boxh);
          		JTextEmail.setBounds(col+boxw*2, gap*5, boxw,boxh);
          		JTextName.setBounds(col+boxw*2, gap*4, boxw,boxh);
          		JTextPassword.setBounds(col+boxw*2, gap*9,boxw,boxh);
          		JTextUsername.setBounds(col+boxw*2, gap*6, boxw, boxh);
          		
          		lblPassword.setForeground(Color.WHITE);
      	     	lblUsername.setFont(new Font("Calibri", Font.PLAIN, 18));
      	     	lblEmail.setFont(new Font("Calibri", Font.PLAIN, 18));
      	     	lblNameofUser.setFont(new Font("Calibri", Font.PLAIN, 18));
      	     	lblCurrentBedtime.setFont(new Font("Calibri", Font.PLAIN, 18));
      	     	lblCurrentWakeuptime.setFont(new Font("Calibri", Font.PLAIN, 18));
      	     	lblPassword.setFont(new Font("Calibri", Font.PLAIN, 18));

      	     	frmCreate.setVisible(true);
      	     	frmLogin.setVisible(false);
      	  btnCancel.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
      	  		frmCreate.setVisible(false);
      	  		frmLogin.setVisible(true);
      	  			}});
     	  		
      	  btnCreate1.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
      	  		frmCreate.setVisible(false);
      	  		a.DisplayHomePage();
      	  		a.DisplayCalendarEvents();
      	  			
      	  		}});
      	  }});
      }


	public void createComboBoxes(){
        times = new String[34];
	      int hh = 7;
	      for(int aa = 0;aa<34;aa++){
	    	  if(aa%2 == 0){
	    			if(aa<10){
	    				times[aa] = hh+":00am";
	    			}
	    			else if(aa == 10){
	    				times[aa] = hh+":00pm";
	    			}
	    			else{
	    				times[aa] = hh-12+":00pm";
	    			}
	    	  }
	    	  else{
	    		  if(aa<10){
	    				times[aa] = hh+":30am";
	    			}
	    		  else if(aa == 11){
	    			  times[aa] = hh+":30pm";
	    		  }
	    			else{
	    				times[aa] = hh-12+":30pm";
	    			}
	    		  hh++;
	    	  }	    	  
	      }
	      
	    dates = new String[31];
	    for(int bb = 1; bb<32;bb++){
	    	dates[bb-1] = "12/"+bb+"/16";
	    }
        timeneeded = new Integer[20];
        for(int cc = 1;cc<21;cc++){
        	timeneeded[cc-1] = cc;
        }
        priority = new Integer[3];
        for(int dd = 1;dd<4;dd++){
        	priority[dd-1] = dd;
        }
        Assignments = new String[4];
        for(int ee = 0;ee<4;ee++){
        	Assignments[ee] = "HW"+(ee+1);
        }
        Events = new String[4];
        for(int ee = 0;ee<4;ee++){
        	Events[ee] = "Event "+(ee+1);
        }
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
      	
	
