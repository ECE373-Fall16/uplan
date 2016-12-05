import javax.imageio.ImageIO;
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
import javax.swing.JInternalFrame;

class HomePage{
    static JButton btnPrevWeek, btnNextWeek,btnAddAssignment, btnAddEvent, btnEditAssignment, btnEditEvent, btnRefresh, btnAccount;
    static JTable tblWeekCalendar;
    static JFrame frmMain;
    static Container pane;
    static JScrollPane stblCalendar; //The scrollpane
    static JLayeredPane pnlCalendar,pnlButtons,pnlTime;
    static int realYear, realMonth, realDay, currentYear, currentMonth;
    static JTable tblCalendar;
    static DefaultTableModel mtblCalendar; //Table model
    static JLabel lblLogo;
    static JTable tblTime;
    static DefaultTableModel timeTable; //Table model
    int calendarwidth, width, height,calendarheight;
    static final String IMG_PATH = "src/Logo/Capture.jpg";
    static LinkedList<JButton> btnAssignments;
    static JInternalFrame frmAddAssignment,frmEditAssignment,frmAddEvent,pnlEditEvent,pnlAccount;
    private Client c;
    
    public HomePage(){
    	c = new Client();
    	btnAssignments = new LinkedList<JButton>();
    }
    
    
    public void DisplayHomePage(){
    	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}
		
		//FRAME SETUP

		frmMain = new JFrame ("Calendar"); //Create frame
		frmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);//Set screen to full
        pane = frmMain.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //BUTTON DECLARATION
       	btnNextWeek = new JButton ("Next Week");
        btnPrevWeek = new JButton ("Prev Week");
        btnAddAssignment = new JButton ("Add Assignment");
        btnAddEvent = new JButton ("Add Event");
        btnEditAssignment = new JButton ("Edit Assignment");
        btnEditEvent = new JButton ("Edit Event");
        btnRefresh = new JButton("Refresh Calendar");
        btnAccount = new JButton("Account");

        
        //GET SCREENSIZE FOR USER'S COMPUTER
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)screenSize.getWidth() ;
		height = (int)screenSize.getHeight()-((int)(screenSize.getHeight()/24.6));
		
		//READING/SCALING IN LOGO
        BufferedImage img = null;
		try {
			img = ImageIO.read(this.getClass().getResource("Logo.png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		Image scaled = img.getScaledInstance((int)(width/2.2766),(int)(height/7.38), Image.SCALE_FAST);
        ImageIcon icon = new ImageIcon(scaled);
        lblLogo = new JLabel(icon);
                
        //TABLE CREATION
        timeTable = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblTime = new JTable(timeTable);
        mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblCalendar = new JTable(mtblCalendar);
        stblCalendar = new JScrollPane(tblCalendar);
        
        //PANEL CREATION
        pnlCalendar = new JLayeredPane();
        pnlButtons = new JLayeredPane();
        pnlTime = new JLayeredPane();
        
                
        //ACTION LISTENERS FOR BUTTONS
        btnPrevWeek.addActionListener(new btnPrevWeek_Action());
        btnNextWeek.addActionListener(new btnNextWeek_Action());
        btnAccount.addActionListener(new btnAccount_Action());
        btnAddAssignment.addActionListener(new btnAddAssignment_Action());
        btnEditAssignment.addActionListener(new btnEditAssignment_Action());
        btnAddEvent.addActionListener(new btnAddEvent_Action());
        btnEditEvent.addActionListener(new btnEditEvent_Action());
        btnRefresh.addActionListener(new btnRefresh_Action());
  
        //COLOR CREATION FOR ELEMENTS
        Color BabyBlue =  new Color(157,205,255);
        Color back = new Color(125,10,10);
        
        //ADD ELEMENTS TO PANELS
        pane.add(pnlCalendar);
        pnlCalendar.add(stblCalendar,new Integer(1));
        pnlCalendar.setBackground(back);        
        
        //BUTTONS PANEL
        pane.add(pnlButtons);
        pnlButtons.add(btnPrevWeek);
        pnlButtons.add(btnNextWeek);
        pnlButtons.add(btnAccount);
        pnlButtons.add(btnAddAssignment);
        pnlButtons.add(btnEditAssignment);
        pnlButtons.add(btnAddEvent);
        pnlButtons.add(btnEditEvent);
        pnlButtons.add(btnRefresh);
        pnlButtons.add(lblLogo);
        
        //TIME PANEL
        pane.add(pnlTime);
        pnlTime.add(tblTime);
        
        //SET BACKGROUDNS AND OTHER
        frmMain.getContentPane().setBackground(back);
        btnPrevWeek.setBackground(Color.GRAY); 
        btnNextWeek.setBackground(Color.GRAY);
        btnNextWeek.setContentAreaFilled(false);
        btnNextWeek.setOpaque(true);
        btnPrevWeek.setContentAreaFilled(false);
        btnPrevWeek.setOpaque(true);
        Border thickBorder = new LineBorder(Color.BLACK,2);
        btnNextWeek.setBorder(thickBorder);
        btnPrevWeek.setBorder(thickBorder);
        pnlButtons.setBackground(back);
        pnlTime.setBackground(back);
        pnlCalendar.setBackground(back);
        
        
        //GET BOUND RELIANT COORDINATES
        int x = (int) (width/6)*5-(int)width/14;
        int y = (int) (width/6)*4-(int)width/68;
        calendarheight = height - (int)(height/4.2);
        calendarwidth = width - (int)(width/27.32);
        int timeheight = calendarheight-(int)(height/73.8)	;
        
        
        
        
        
        //SETTING LOCATIONS FOR ALL COMPONENTS
        
        //PANELS
        pnlButtons.setBounds(0,0,width,(int)(height/5.35));
        pnlCalendar.setBounds((int)(width/27.32),(int)(height/5.35), calendarwidth, calendarheight);
        pnlTime.setBounds(0,(int)(height/5.35),(int)(width/27.32),calendarheight);
        
        //BUTTONS
        btnRefresh.setBounds((int)(width/73.8),(int)(height/12.3),(int)(width/11.38),(int)(height/24.6));
        btnAddAssignment.setBounds(y,(int)(height/12.3),(int)(width/11.38),(int)(height/24.6));
        btnAddEvent.setBounds(y,(int)(height/36.9),(int)(width/11.38),(int)(height/24.6)); 
        btnEditAssignment.setBounds(x,(int)(height/12.3),(int)(width/11.38),(int)(height/24.6)); 
        btnEditEvent.setBounds(x,(int)(height/36.9),(int)(width/11.38),(int)(height/24.6));
        btnPrevWeek.setBounds((int)(width/27),(int)(height/7.03),((int)(width/19.51)), (int)(height/24.6));
        btnNextWeek.setBounds(width-((int)(width/17.0575)),(int)(height/7.03),(int)(width/19.51), (int)(height/24.6));
        btnAccount.setBounds((int)(width/73.8),(int)(height/36.9), (int)(width/11.38), (int)(height/24.6));
        
        //OTHER
        stblCalendar.setBounds(0,0,calendarwidth,calendarheight);
        tblTime.setBounds(0,(int)(height/29.52),(int)(width/27.32),timeheight);
        lblLogo.setBounds((int)(width/6.83),(int)(height/73.8),600,120);
        
        //MAKE VISIBLE
        frmMain.setMinimumSize(new Dimension(400,400));				//set minimize size
        frmMain.setVisible(true);		
        
        
        
        
        
        
        
        //GET CALENDAR OBJECT WITH DATE, NOT SURE IF NECESARY
        
        GregorianCalendar cal = new GregorianCalendar();//get real calendar
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH)+1; //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;
        
        
        
        
        //SET ROW/COLUMN COUNT
        
        tblCalendar.setRowHeight(calendarheight);
        mtblCalendar.setRowCount(1);
        
        tblTime.setRowHeight(timeheight/17);
        timeTable.setRowCount(17);
        
        
        
        
        
        //CODING WEEKDAY FOR MONTH OF DECEMBER
        
        int [] daysofDecember = new int[33];
        String [] theday = new String[33];
        	for(int a = 1;a<=32;a++){
        		if(a%7 == 1){
        			daysofDecember[a] = 4;
        			theday[a] = "Thursday";
        		}
        		if(a%7 == 2){
        			daysofDecember[a] = 5;
        			theday[a] = "Friday";
        		}
        		if(a%7 == 3){
        			daysofDecember[a] = 6;
        			theday[a] = "Saturday";
        		}
        		if(a%7 == 4){
        			daysofDecember[a] = 0;
        			theday[a] = "Sunday";
        		}
        		if(a%7 == 5){
        			daysofDecember[a] = 1;
        			theday[a] = "Monday";
        		}
        		if(a%7 == 6){
        			daysofDecember[a] = 2;
        			theday[a] = "Tuesday";
        		}
        		if(a%7 == 7){
        			daysofDecember[a] = 3;
        			theday[a] = "Wednesday";
        		}
        	}
        	
        	
        	
        //GETTING REAL DATE AND CONVERT TO INT FORM
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
         	 String date = sdf.format(new Date());
         	 char[] array= date.toCharArray();
         	 String dateref = "";
         	 String monthref = "";
         	 String yearref = "";
         	 int counter = 0;
         	 
         	 for(int c = 0; c<date.length();c++){
         		 if(array[c] == '/'){
         			 counter++;
         		 }
         		 else if(counter == 0){
         			 monthref += array[c];
         		 }
         		 else if(counter == 1){
         			 dateref += array[c];
         		 }
         		 else if(counter == 2){
         			 yearref += array[c];
         		 }
         	 }
         	 counter = 0;	 
       	 int dayofthemonth = Integer.parseInt(dateref);
       	 int whichmonth = Integer.parseInt(monthref);
       	 int whichyear = Integer.parseInt(yearref);
       	 
       	 
       	 
       	 
       	 //FILLING OF HEADER ARRAY FOR CALENDAR
       	 
       	 String[] headers = new String[7]; //All headers
       	 int jj = daysofDecember[dayofthemonth];
       	 int counter2 =jj;
       	 String[] week = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
       	 String month = "December";
       	 int dayclone = dayofthemonth;
       	 int counter3 = dayclone;
       	 while(0<=jj){
       		 headers[jj] = week[jj] + " "+month+" " + dayclone ;
       		 dayclone--;
       		 if(dayclone==0){
       			 dayclone= 30;
       			 month = "November";
       		 }
       		 jj--;
       	 }
       	 dayclone = dayofthemonth;
       	 jj = dayofthemonth%7;
       	 counter2++;
       	 dayclone++;
        while(counter2<7){
        	headers[counter2] = theday[dayclone] + " December " + dayclone ;
        	dayclone++;
        	if(dayclone==31){
        		jj = 1;
        	}
        	counter2++;
        }
        for (int i = 0; i<7; i++){
        	mtblCalendar.addColumn(headers[i]);
        }
        
        
        
        
        //CREATION OF TIMES FOR TIME COLUMN
        
        timeTable.addColumn("T");
        for(int b = 7; b<24;b++){
        	if(b<12){
        		tblTime.setValueAt((Object)b+":00am",b-7, 0);
        	}
        	if(b == 12){
        		tblTime.setValueAt((Object)"12:00pm", b-7, 0);
        	}
        	if(b>12){
        		tblTime.setValueAt((Object)(b-12)+":00pm", b-7, 0);
        	}
        }
        
        
        
        
        //TABLE SPECIFICATIONS
        
        
        tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background
        //No resize/reorder
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);
        //single cell selection
        tblCalendar.setColumnSelectionAllowed(true);
        tblCalendar.setRowSelectionAllowed(true);
        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCalendar.setShowVerticalLines(true);
        
        tblTime.getParent().setBackground(back); //Set background
        //No resize/reorder
        tblTime.getTableHeader().setResizingAllowed(false);
        tblTime.getTableHeader().setReorderingAllowed(false);
        //single cell selection
        tblTime.setColumnSelectionAllowed(true);
        tblTime.setRowSelectionAllowed(true);
        tblTime.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblTime.setShowVerticalLines(true);
        tblTime.setShowHorizontalLines(true);
	}
    
    
    
    
    
    //FIND COORDINATES FOR ASSIGNMENTS/EVENTS
    
    public int findHorizontialPosition(int day){
		int spot = ((calendarwidth/7)*day);
		return spot-day*((int)(width/683));
	}
    public int findVerticalStart(int hour1,int minute){
    	int start = ((calendarheight/17)*hour1);
    	return start;
    			//+(calendarheight/1020)*minute;
    }
    public int findVerticalEnd(int hour2,int minute){
    	int end = ((calendarheight/17)*hour2);
    	return end;//+(calendarheight/1020)*1020;
    }
    
    
    
    
    //BUTTON ACITON METHODS
    
	class btnPrevWeek_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){}
		}
	class btnNextWeek_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){}
		}
	class btnAccount_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){}
		}
	
	class btnAddAssignment_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			JLayeredPane pnlAddAssignment = new JLayeredPane();
			pane.add(pnlAddAssignment);
			pnlAddAssignment.setBackground(Color.WHITE);
			pnlAddAssignment.setBounds(width/4, height/4, width/2, height/2);
			
			
		}
		}
	class btnEditAssignment_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){}
		}
	class btnAddEvent_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){}
		}
	class btnEditEvent_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){}
		}
	class btnRefresh_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){}
		}
	
	
	
	//DISPLAY ASSIGNMENTS ON CALENDAR
	
	public void DisplayCalendarEvents(){
        LinkedList<CalendarEvent> calList = null;
		try {
			calList = c.display();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int size = calList.size();
        JButton[] list1 = new JButton[size];
        int date1,dayof,starthour,endhour,startmin,endmin, spot,place1,place2;
        for(int ii = 0; ii<size;ii++){
        	CalendarEvent a = calList.get(ii);
        	String name = a.getName();
        	Date starttime = a.getStartTime();
        	Date endtime = a.getEndTime();
        	
        	String location = a.getLocation();
        	date1 = starttime.getDate();
        	dayof = starttime.getDay();
        	starthour = starttime.getHours();
			endhour = endtime.getHours();
			startmin = starttime.getMinutes();
			endmin = endtime.getMinutes();
            list1[ii] = new JButton(name+"   "+starthour%12+":"+startmin+"0-"+endhour%12+":"+endmin+"0");
            pnlCalendar.add(list1[ii],new Integer(2));
            spot = findHorizontialPosition(dayof);
            place1 = findVerticalStart(starthour-6, startmin);
            place2 = findVerticalEnd(endhour-6,endmin);
            //System.out.println(place1 + "   "+ place2 + "     " + calendarheight+ "   "+starthour+"  "+endhour);
            list1[ii].setBounds((spot),place1,calendarwidth/7-(int)(width/136.6),place2-place1);
        	}
        }
        
	public void ClearCalendar(){}
	
}
