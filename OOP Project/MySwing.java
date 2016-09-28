
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/*
 * Useful for creating a MySwing type
 * 
 * 
 */
public class MySwing extends JFrame implements ActionListener {
	private MavBay myEnt = new MavBay("");
	private JLabel lockedL= new JLabel("");
	private String [] empNames={"","","","","","",""}; 
	private JList custL;
	private JFrame empF= new JFrame();
	private JFrame mainF= new JFrame();
	private JFrame custF= new JFrame();
	private JFrame itemF=new JFrame();
	private JComboBox yearC;
	private JList empL= new JList(empNames);
	private JList empL1=new JList(empNames);
	private JList itemL=new JList();
	private JList unsoldItemL=new JList();
	private JTextArea empT;
	private JTextArea custT;
	private JTextArea itemT;
	private JScrollPane empS= new JScrollPane(empL);
	private Font myF =new Font("Arial",Font.BOLD, 14);
 
	public MySwing (MavBay ent){
		myEnt=ent; 	
		display();		
		mainF.validate();		
	}
	
	/*
	 * Method: displays the main window for gui
	 * 
	 */
	public void display(){
		mainF = new JFrame();    
		mainF.setTitle("MavBay");
		mainF.setSize(1000,800);
		mainF.setLocation(150,150);
		mainF.setVisible(true);
		mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	    JPanel pan = new JPanel();
	    pan.setLayout(new GridBagLayout());
	    pan.setBackground(new Color(100,100,100));
	    GridBagConstraints c = new GridBagConstraints();	   	       
	    JPanel pan2 = new JPanel();
	    pan2.setBackground(new Color(100,100,100));
	    JPanel pan3 = new JPanel();
	    pan3.setBackground(new Color(100,100,100));	    
	    JLabel empL= new JLabel("Select Year");
	    empL.setForeground(Color.BLUE);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;     
	    c.ipadx= 150;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 1;	    
	    pan.add(empL,c);
	    String [] yrs={"All","2014","2013"};
	    final JComboBox yearC= new JComboBox(yrs);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;     
	    c.ipadx= 150;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 2;
	    pan.add(yearC, c);    
	    JButton but;
	    but = new JButton("Get Revenue");
	    but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	empT.setText("");
            	String myString= (String)yearC.getSelectedItem();
                empT.append(myEnt.stringRevenue(myString));
            }
        });
	    but.setForeground(Color.BLUE);
	    but.setBackground(Color.GRAY);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      
	    c.ipadx= 150;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 2;
	    c.gridy = 3;	    
	    pan.add(but, c); 	  	        
	    empT= new JTextArea();
	    empT.setEditable(false);
	    c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		c.ipady=480;
		c.ipadx=480;
		c.gridwidth=2;
		c.gridheight=8;
		empS= new JScrollPane(empT);
		pan.add(empS,c);     	     
	    JButton butStart = new JButton("Main");
	    butStart = new JButton("Main");
	    butStart.setForeground(Color.BLUE);
	    butStart.setBackground(Color.GRAY);
	    butStart.setActionCommand("Main");
        butStart.addActionListener(this);
	    pan3.add(butStart);
	    JButton butOk=new JButton("Employees");
	    butOk.setForeground(Color.BLUE);
	    butOk.setBackground(Color.GRAY);
	    butOk.setActionCommand("Employees");
	    butOk.addActionListener(this);
	    pan3.add(butOk);
	    JButton butCust = new JButton("Customers");
	    butCust = new JButton("Customers");
	    butCust.setForeground(Color.BLUE);
	    butCust.setBackground(Color.GRAY);
	    butCust.setActionCommand("Customers");
	    butCust.addActionListener(this);
	    pan3.add(butCust);
	    JButton butItem = new JButton("Items");
	    butItem = new JButton("Items");
	    butItem.setForeground(Color.BLUE);
	    butItem.setBackground(Color.GRAY);
	    butItem.setActionCommand("Items");
	    butItem.addActionListener(this);
	    pan3.add(butItem);
	    mainF.add(pan,BorderLayout.CENTER);
	    lockedL = new JLabel("Main");
	    lockedL.setFont(myF);
	    lockedL.setForeground(Color.BLUE);
	    lockedL.setBackground(Color.GRAY);
	    pan2.add(lockedL);
	    mainF.add(pan2,BorderLayout.NORTH);
	    mainF.add(pan3,BorderLayout.SOUTH);
	}
	
	/*
	 * Method: displays employee menu for gui
	 * 
	 */
	public void emp(){
		
		empF = new JFrame();    
		empF.setTitle("MavBay");
		empF.setSize(1000,800);
		empF.setLocation(150,150);
		empF.setVisible(true);
		empF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel pan = new JPanel();
	    pan.setLayout(new GridBagLayout());
	    pan.setBackground(new Color(100,100,100));
	    GridBagConstraints c = new GridBagConstraints();  
	    JPanel pan2 = new JPanel();
	    pan2.setBackground(new Color(100,100,100));
	    JPanel pan3 = new JPanel();
	    pan3.setBackground(new Color(100,100,100));
	    JLabel empLab= new JLabel("Released Employees");
	    empLab.setForeground(Color.BLUE);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;    
	    c.ipadx= 50;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 1;	    
	    pan.add(empL,c);
	    empL1= new JList(myEnt.convertEmp(myEnt.getReleasedEmployees()));
	    empL1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    empS= new JScrollPane(empL1);	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 80;     
	    c.ipadx= 50;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 2;
	    pan.add(empS, c);
	    JButton but;
	    but = new JButton("Released");
	    but.setForeground(Color.BLUE);
	    but.setBackground(Color.GRAY);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;    
	    c.ipadx= 50;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 2;
	    c.gridy = 3;
	    but.addActionListener(this);
	    pan.add(but, c);
	    JLabel empL1= new JLabel("Current Employees");
	    empL1.setForeground(Color.BLUE);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;      
	    c.ipadx= 50;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 4;
	    pan.add(empL1,c);
	    empL = new JList(myEnt.convertEmp(myEnt.getEmployees()));
	    empL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    empS= new JScrollPane(empL);	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 80;     
	    c.ipadx= 50;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 5;
	    pan.add(empS, c);
	    JButton but1;
	    but1 = new JButton("Employee Details");
	    but1.setForeground(Color.BLUE);
	    but1.setBackground(Color.GRAY);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      
	    c.ipadx= 50;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 2;
	    c.gridy = 6;
	    but1.addActionListener(this);
	    pan.add(but1, c);
	    empT= new JTextArea();
	    empT.setEditable(false);
	    c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		c.ipady=480;
		c.ipadx=480;
		c.gridwidth = 2;
		c.gridheight=8;
		pan.add(empT,c);
	    JButton butStart = new JButton("Main");
	    butStart = new JButton("Main");
	    butStart.setForeground(Color.BLUE);
	    butStart.setBackground(Color.GRAY);
	    butStart.setActionCommand("Main");
        butStart.addActionListener(this);
	    pan3.add(butStart);
	    JButton butOk=new JButton("Employees");
	    butOk.setForeground(Color.BLUE);
	    butOk.setBackground(Color.GRAY);
	    butOk.setActionCommand("Employees");
	    butOk.addActionListener(this);
	    pan3.add(butOk);
	    JButton butCust = new JButton("Customers");
	    butCust = new JButton("Customers");
	    butCust.setForeground(Color.BLUE);
	    butCust.setBackground(Color.GRAY);
	    butCust.setActionCommand("Customers");
	    butCust.addActionListener(this);
	    pan3.add(butCust);
	    JButton butItem = new JButton("Items");
	    butItem = new JButton("Items");
	    butItem.setForeground(Color.BLUE);
	    butItem.setBackground(Color.GRAY);
	    butItem.setActionCommand("Items");
	    butItem.addActionListener(this);
	    pan3.add(butItem);
	    empF.add(pan,BorderLayout.CENTER);
	    lockedL = new JLabel("Employees");
	    lockedL.setFont(myF); 
	    lockedL.setForeground(Color.BLUE);
	    lockedL.setBackground(Color.GRAY);
	    pan2.add(lockedL);
	    empF.add(pan2,BorderLayout.NORTH);
	    empF.add(pan3,BorderLayout.SOUTH);
	}
	
	/*
	 * Method: displays customer menu for gui
	 * 
	 */
	public void cust(){
		custF = new JFrame();    
		custF.setTitle("MavBay");
		custF.setSize(1000,800);
		custF.setLocation(150,150);
		custF.setVisible(true);
		custF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel pan = new JPanel();
	    pan.setLayout(new GridBagLayout());
	    pan.setBackground(new Color(100,100,100));
	    GridBagConstraints c = new GridBagConstraints();	    
	    JPanel pan2 = new JPanel();
	    pan2.setBackground(new Color(100,100,100));
	    JPanel pan3 = new JPanel();
	    pan3.setBackground(new Color(100,100,100));    
	    JLabel empL= new JLabel("Customers");
	    empL.setForeground(Color.BLUE);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;     
	    c.ipadx= 200;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 1;	    
	    pan.add(empL,c); 
	    custL= new JList(myEnt.convertCust(myEnt.getCustomer()));	    
	    custL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    empS= new JScrollPane(custL);	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipadx= 200;
	    c.ipady=100;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 2;
	    pan.add(empS, c);
	    String [] years={"All","2014","2013"};
	    final JComboBox yearSelection= new JComboBox(years);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;     
	    c.ipadx= 200;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 3;
	    pan.add(yearSelection, c);
	    JButton but;
	    but = new JButton("Bids");
	    but.setForeground(Color.BLUE);
	    but.setBackground(Color.GRAY);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;    
	    c.ipadx= 200;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 2;
	    c.gridy = 4;
	    but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	custT.setText("");
            	int index=custL.getSelectedIndex();
            	String myString= (String)yearSelection.getSelectedItem();
            	custT.append(myEnt.stringCustBids(index, myString));
            }
        });
	    pan.add(but, c);
	    JButton but1;
	    but1 = new JButton("High/Low");
	    but1.setForeground(Color.BLUE);
	    but1.setBackground(Color.GRAY);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;    
	    c.ipadx= 200;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 2;
	    c.gridy = 5;
	    but1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame frame=new JFrame();
    	    	frame.setSize(250,250);
    	    	String yr= (String)yearSelection.getSelectedItem();
    	    	if(yr.equals("All")){
    	    		JOptionPane.showMessageDialog(null,myEnt.stringCustRev());
    	    	}else{
    	    		JOptionPane.showMessageDialog(null,myEnt.stringCustRev(yr));    	    		
    	    	}
    	    	
            }
        });
	    pan.add(but1, c);
	    custT= new JTextArea();
	    custT.setEditable(false);
	    c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		c.ipady=480;
		c.ipadx=480;
		c.gridwidth = 2;
		c.gridheight=8;
		pan.add(custT,c);     
	    JButton butStart = new JButton("Main");
	    butStart = new JButton("Main");
	    butStart.setForeground(Color.BLUE);
	    butStart.setBackground(Color.GRAY);
	    butStart.setActionCommand("Main");
        butStart.addActionListener(this);
	    pan3.add(butStart);
	    JButton butOk=new JButton("Employees");
	    butOk.setForeground(Color.BLUE);
	    butOk.setBackground(Color.GRAY);
	    butOk.setActionCommand("Employees");
	    butOk.addActionListener(this);
	    pan3.add(butOk);
	    JButton butCust = new JButton("Customers");
	    butCust = new JButton("Customers");
	    butCust.setForeground(Color.BLUE);
	    butCust.setBackground(Color.GRAY);
	    butCust.setActionCommand("Customers");
	    butCust.addActionListener(this);
	    pan3.add(butCust);
	    JButton butItem = new JButton("Items");
	    butItem = new JButton("Items");
	    butItem.setForeground(Color.BLUE);
	    butItem.setBackground(Color.GRAY);
	    butItem.setActionCommand("Items");
	    butItem.addActionListener(this);
	    pan3.add(butItem);
	    custF.add(pan,BorderLayout.CENTER);
	    lockedL = new JLabel("Customers");
	    lockedL.setFont(myF); 
	    lockedL.setForeground(Color.BLUE);
	    lockedL.setBackground(Color.GRAY);
	    pan2.add(lockedL);
	    custF.add(pan2,BorderLayout.NORTH);
	    custF.add(pan3,BorderLayout.SOUTH);
	}
	
	/*
	 * Method: displays item menu for gui
	 * 
	 */
	public void item(){

		itemF = new JFrame();    
		itemF.setTitle("MavBay");
		itemF.setSize(1000,800);
		itemF.setLocation(150,150);
		itemF.setVisible(true);
		itemF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel pan = new JPanel();
	    pan.setLayout(new GridBagLayout());
	    pan.setBackground(new Color(100,100,100));
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel pan2 = new JPanel();
	    pan2.setBackground(new Color(100,100,100));
	    JPanel pan3 = new JPanel();
	    pan3.setBackground(new Color(100,100,100));
	    JLabel empL= new JLabel("Items Not Sold");
	    empL.setForeground(Color.BLUE);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;     
	    c.ipadx= 100;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 1;	    
	    pan.add(empL,c);
	    itemL= new JList(myEnt.convertItem(myEnt.getItems()));
	    itemL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    empS= new JScrollPane(itemL);	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 80;     
	    c.ipadx= 100;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 2;
	    pan.add(empS, c);
	    JButton but;
	    but = new JButton("Not Sold Item Details");
	    but.setForeground(Color.BLUE);
	    but.setBackground(Color.GRAY);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;     
	    c.ipadx= 100;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 2;
	    c.gridy = 3;
	    but.addActionListener(this);
	    pan.add(but, c);
	    JLabel empL1= new JLabel("Items Sold");
	    empL1.setForeground(Color.BLUE);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;     
	    c.ipadx= 100;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 4;	    
	    pan.add(empL1,c);
	    unsoldItemL= new JList(myEnt.convertItem(myEnt.getItemsSold()));
	    unsoldItemL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    empS= new JScrollPane(unsoldItemL);	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 80;    
	    c.ipadx= 100;
	    c.gridwidth = 1;  
	    c.gridx = 2;
	    c.gridy = 5;
	    pan.add(empS, c);
	    JButton but1;
	    but1 = new JButton("Sold Item Details");
	    but1.setForeground(Color.BLUE);
	    but1.setBackground(Color.GRAY);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;     
	    c.ipadx= 100;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 2;
	    c.gridy = 6;
	    but1.addActionListener(this);
	    pan.add(but1, c);
	    itemT= new JTextArea();
	    itemT.setEditable(false);
	    empS= new JScrollPane(itemT);
	    c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		c.ipady=480;
		c.ipadx=480;
		c.gridwidth = 2;
		c.gridheight=8;
		pan.add(empS,c);	     
	    JButton butStart = new JButton("Main");
	    butStart = new JButton("Main");
	    butStart.setForeground(Color.BLUE);
	    butStart.setBackground(Color.GRAY);
	    butStart.setActionCommand("Main");
        butStart.addActionListener(this);
	    pan3.add(butStart);
	    JButton butOk=new JButton("Employees");
	    butOk.setForeground(Color.BLUE);
	    butOk.setBackground(Color.GRAY);
	    butOk.setActionCommand("Employees");
	    butOk.addActionListener(this);
	    pan3.add(butOk);
	    JButton butCust = new JButton("Customers");
	    butCust = new JButton("Customers");
	    butCust.setForeground(Color.BLUE);
	    butCust.setBackground(Color.GRAY);
	    butCust.setActionCommand("Customers");
	    butCust.addActionListener(this);
	    pan3.add(butCust);
	    JButton butItem = new JButton("Items");
	    butItem = new JButton("Items");
	    butItem.setForeground(Color.BLUE);
	    butItem.setBackground(Color.GRAY);
	    butItem.setActionCommand("Items");
	    butItem.addActionListener(this);
	    pan3.add(butItem);
	    itemF.add(pan,BorderLayout.CENTER);
	    lockedL = new JLabel("Items");
	    lockedL.setFont(myF); 
	    lockedL.setForeground(Color.BLUE);
	    lockedL.setBackground(Color.GRAY);
	    pan2.add(lockedL);
	    itemF.add(pan2,BorderLayout.NORTH);
	    itemF.add(pan3,BorderLayout.SOUTH);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
	    
	    String com = e.getActionCommand();
	    if(com.equals("Employee Details")){
	     int ind=empL.getSelectedIndex();
	     empT.setText("");
	     empT.append("Employee Details\n");
	     empT.append(myEnt.stringEmp(ind));
	     
	    }
	    if(com.equals("Oldest Member")){
	    	JFrame fr= new JFrame();
	    	fr.setSize(200,200);
	    	JOptionPane.showMessageDialog(null,myEnt.empServiceLength());
	    }
	    
	    if(com.equals("Released")){
	    	int ind=empL1.getSelectedIndex();
		    String s= (String)empL1.getSelectedValue();
		    empT.setText("");
	        empT.append("Employee Details\n");
    	    empT.append(myEnt.stringReleasedEmp(ind));
	    }
        if(com.equals("Not Sold Item Details")){
            int ind=itemL.getSelectedIndex();
            itemT.setText("");
            itemT.setText(myEnt.stringItemBids(ind));            
        }
        if(com.equals("Sold Item Details")){
        	int ind=unsoldItemL.getSelectedIndex();
        	itemT.setText("");
        	itemT.setText(myEnt.stringSoldItemBids(ind));        	
        }
	    
	    if(com.equals("Main")){	    	
	    	this.empF.setVisible(false);
	    	this.custF.setVisible(false);
	    	this.itemF.setVisible(false);
	    	this.display();	    	
	    }
	    if(com.equals("Employees")){
	    	this.mainF.setVisible(false);
	    	this.custF.setVisible(false);
	    	this.itemF.setVisible(false);
	    	this.emp();	    	
	    }
	    if(com.equals("Customers")){
	    	this.empF.setVisible(false);
	    	this.mainF.setVisible(false);
	    	this.itemF.setVisible(false);
	    	this.cust();
	    }
        if(com.equals("Items")){
        	this.empF.setVisible(false);
	    	this.mainF.setVisible(false);
	    	this.custF.setVisible(false);
	    	this.item();
	    }
        	     
	  }
}
