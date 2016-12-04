
    static JButton btnPrevWeek, btnNextWeek,btnAddAssignment, btnAddEvent, btnEditAssignment, btnEditEvent, btnRefresh, btnAccount, btnAssignment;
    static JTable tblWeekCalendar;
    static JFrame frmMain;
    static Container pane;
    static JScrollPane stblCalendar; //The scrollpane
    static JLayeredPane pnlCalendar;
    static int realYear, realMonth, realDay, currentYear, currentMonth;
    static JTable tblCalendar;
    static JPanel pnlButtons;
    static DefaultTableModel mtblCalendar; //Table model
    
    static JPanel pnlTime;
    static JTable tblTime;
    static DefaultTableModel timeTable; //Table model
    int calendarwidth;
    
    public HomePage(){
    	calendarwidth = this.calendarwidth;
    }
    
    
    public void DisplayHomePage(){
    	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}
		
		//frame setup

		frmMain = new JFrame ("Calendar"); //Create frame
		frmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);//Set screen to full
        pane = frmMain.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //controls
       	btnNextWeek = new JButton ("Next Week");
        btnPrevWeek = new JButton ("Prev Week");
        btnAddAssignment = new JButton ("Add Assignment");
        btnAddEvent = new JButton ("Add Event");
        btnEditAssignment = new JButton ("Edit Assignment");
        btnEditEvent = new JButton ("Edit Event");
        btnRefresh = new JButton("Refresh Calendar");
        btnAccount = new JButton("Account");
        btnAssignment = new JButton("ECE 323 HW");
        
        
        mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblCalendar = new JTable(mtblCalendar);
        stblCalendar = new JScrollPane(tblCalendar);
        
        pnlCalendar = new JLayeredPane();
        pnlButtons = new JPanel(null);
        pnlTime = new JPanel(null);
        
        timeTable = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblTime = new JTable(timeTable);
                
        btnPrevWeek.addActionListener(new btnPrevWeek_Action());
        btnNextWeek.addActionListener(new btnNextWeek_Action());
        
        
        frmMain.getContentPane().setBackground(Color.white);
        Color BabyBlue =  new Color(157,205,255);
        Color back = new Color(125,10,10);
        //Add controls to pane
        pane.add(pnlCalendar);
        pnlCalendar.add(stblCalendar,new Integer(1));
        pnlCalendar.setBackground(back);
        pnlCalendar.add(btnAssignment,new Integer(2));
        
        btnPrevWeek.setBackground(BabyBlue); 
        btnNextWeek.setBackground(BabyBlue);
        btnNextWeek.setContentAreaFilled(false);
        btnNextWeek.setOpaque(true);
        btnPrevWeek.setContentAreaFilled(false);
        btnPrevWeek.setOpaque(true);
        Border thickBorder = new LineBorder(Color.WHITE,2);
        btnNextWeek.setBorder(thickBorder);
        btnPrevWeek.setBorder(thickBorder);
       
        pnlButtons.setBackground(back);
        pnlTime.setBackground(back);
        
      
        
        pane.add(pnlButtons);
        pnlButtons.add(btnPrevWeek);
        pnlButtons.add(btnNextWeek);
        pnlButtons.add(btnAccount);
        pnlButtons.add(btnAddAssignment);
        pnlButtons.add(btnEditAssignment);
        pnlButtons.add(btnAddEvent);
        pnlButtons.add(btnEditEvent);
        pnlButtons.add(btnRefresh);
        
        pane.add(pnlTime);
        pnlTime.add(tblTime);
        
        
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth() ;
		int height = (int)screenSize.getHeight()-30;
        //Set bounds
        int x = (int) (width/6)*5-140;
        int y = (int) (width/6)*4-60;
        int calendarheight = height - 175;
        calendarwidth = width - 50;
        int timeheight = calendarheight-10	;
        
        int day = 0;
        int spot = ((calendarwidth/7)*day+5);
        
        pnlButtons.setBounds(0,0,width,138);
        pnlCalendar.setBounds(50,138 , calendarwidth, calendarheight);
        pnlTime.setBounds(0,138,50,calendarheight);
        btnRefresh.setBounds(y,60,120,30);
        btnAddAssignment.setBounds(y,100,120,30);
        btnAddEvent.setBounds(x,20,120,30); 
        btnEditAssignment.setBounds(x,100,120,30); 
        btnEditEvent.setBounds(x,60,120,30);
        btnPrevWeek.setBounds(10, 105, 90, 30);
        btnNextWeek.setBounds(width-110, 105, 90, 30);
        btnAccount.setBounds(10, 20, 90, 30);
        stblCalendar.setBounds(0,0,calendarwidth,calendarheight );
        tblTime.setBounds(0,25,50,timeheight);
        frmMain.setMinimumSize(new Dimension(400,400));				//set minimize size
        frmMain.setVisible(true);		//make visible
        btnAssignment.setBounds(spot, 40, calendarwidth/7-10, 58);
        
        
        
        GregorianCalendar cal = new GregorianCalendar();//get real calendar
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH)+1; //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;
        
        //Set row/column count
        tblCalendar.setRowHeight(calendarheight);
        mtblCalendar.setRowCount(1);
        
        tblTime.setRowHeight(timeheight/17);
        timeTable.setRowCount(17);
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
        	/*
        //Set column headers
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
         	 
         	 int dayofthemonth = Integer.parseInt(dateref);
         	 int whichmonth = Integer.parseInt(monthref);
         	 int whichyear = Integer.parseInt(yearref);
         	System.out.println(whichmonth+ " " + dayofthemonth + " " + whichyear);
           
           
           	
        	
       
        String[] headers = new String[7]; //All headers
        int jj = dayofthemonth%7;
        int counter2 =jj;
        String month = "December";
        while(0<=jj){
        	headers[jj] = theday[dayofthemonth] + " "+month+" " + dayofthemonth ;
        	System.out.println(headers[jj]);
        	dayofthemonth--;
        	if(dayofthemonth==0){
        		dayofthemonth= 30;
        		month = "November";
        	}
        	jj--;
        }
        jj = dayofthemonth%7;
        int counter3 = counter2;
        while(jj<7 && counter3>0){
        	headers[jj] = theday[dayofthemonth] + " December " + dayofthemonth ;
        	System.out.println(headers[jj]);
        	if(jj==31){
        		jj = 1;
        	}
        	jj++;
        	dayofthemonth++;
        	
        }
        */
        for (int i = 0; i<7; i++){
        	String headers[] = {"Sun","Mon","Tues","Wed","Thurs","Fri","Sat"};
        	mtblCalendar.addColumn(headers[i]);
        }
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
/*	public int findHorizontialPosition(int day, int width){
		int spot = ((width/7)+((width/7)/2))*day;
		return spot;
	}*/

	class btnPrevWeek_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){}
		}
	class btnNextWeek_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){}
		}
	
}