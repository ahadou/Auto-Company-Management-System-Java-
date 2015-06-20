package eventsAndGUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import functional.Car;
import functional.Customer;
import functional.Employee;
import functional.Planner;
import functional.SQLStorage;
import functional.Transaction;

public class MainMenuWindow extends JFrame
{
	//Declare Variables (GUI Components)
	protected static final long serialVersionUID = 1L;
	protected JMenuBar menuBar = new JMenuBar();
	        protected JMenu customerMenu, employeeMenu, carMenu, transactionMenu, plannerMenu;
    protected static JMenuItem custEdit, custSearch, custShowAll, empCreate, empEdit, empDelete, empSearch, empShowAll, carEdit, carSearch, carShowAll,
						    searchTransactions, showAllTransactions, createNewTransaction, editTransaction, createMemo, showAllMemos, editMemo, 
							deleteMemo, deleteTransaction;
	protected static JTextField showDate, txtPlannerDate,  txtPlannerTime, txtPlannerDescription,  txtSearchReminderEdit,
							txtDeleteReminder, txtDeleteTransaction, txtTransCustName, txtTransCustAddress,
							txtTransCustPhone, txtTransCustDOB, txtTransCustInsureC, txtTransCustNCB, txtTransCustMake,
							txtTransCustModel, txtTransCustReg, txtTransCustColor, txtTransCustEngine, txtTransCustPrice, 
							txtTransCustVAT, txtTransCustTotal, txtBuyOrSell, txtSearchTransactionEdit, txtSearchTransID, txtSearchEmployeeJob,
							txtSearchTransName, txtSearchTransCarMake, txtEmpJob, txtEmpName, txtCustomerName, txtCustomerAddress, txtCustomerPhone,
							txtCustomerDOB, txtSearchCarsID, txtSearchCarsMake, txtSearchCarsModel, txtSearchBuyOrSell, txtCarMake, txtCarModel, txtCarRegistration, 
							txtDeleteEmployee, txtCarColor, txtCarBuyOrSell, txtCarEngine, txtSearchCarEdit, txtSearchCustomerID, txtEmpSalary, txtSearchEmployeeID,
							txtSearchCustomerEdit, txtEmpDOB, txtSearchEmployeeEdit, txtSearchEmployeeName, txtSearchCustomerAddress, txtEmpAddress, txtEmpPhone, txtSearchCustomerName;
	         private JLabel plannerHeader, lblPlannerDate, lblPlannerTime, lblPlannerDescription, lblTransCustName, lblTransCustAddress,
							lblTransCustPhone, lblTransCustDOB, lblTransCustInsureC, lblTransCustNCB, lblTransCustMake, lblTransCustModel,
							lblTransCustReg, lblTransCustColor, lblTransCustEngine, lblTransCustPrice, lblTransCustVAT, lblTransCustTotal,
							lblBuyOrSell, lblSearchTransName, lblSearchEmployee, lblSearchEmployeeID, lblSearchEmployeeJob, lblSearchTransCarMake;
   protected static JButton btnCalcTotalPrice, btnNewRemindAdd, btnNewTransactionAdd, btnReminderUpdate, btnTransactionUpdate,
							btnSearchReminderEdit, btnSearchTransactionEdit,  btnDeleteReminder, btnDeleteTransaction, btnSearchCarEdit, 
							btnDeleteEmployee, btnUpdateCar, btnExitCar, btnEmployeeAdd, 
							btnEmployeeExit,  btnSearchEmployeeEdit, btnUpdateCustomer, btnEmployeeUpdate, btnExitCustomer, btnSearchCustomerEdit;
            private JButton btnSearchCustomerID, btnSearchEmployeeID, btnSearchCarMake, btnSearchEmployeeName, btnSearchCarModel, btnSearchCarBuySell, btnSearchCustomerName,
    						btnSearchCustomerAddress, btnSearchCarID,  btnSearchEmployeeJob, btnSearchTransID, btnSearchTransName, btnSearchTransCarMake, btnTimeRefresh, 
    						btnNewRemindExit, btnNewTransactionExit;
	protected static JLabel lblEnterIDReminder, lbldeleteReminder, lbldeleteTransaction, lblEnterIDTransaction, transactionHeader, 
							lblSearchTransactions, lblSearchCars, lblEnterIDCar, lblSearchCustomers, lblSearchTransID, lblCars,
							lblSearchCarsID, lblCustomer, lblSearchCarsMake, lblEnterIDEmployee, lblSearchCarsModel, lblSearchBuyOrSell, lblCarMake,
							lblCarModel, lblCarRegistration, lblCarColor, lblCarEngine, lblCarBuyOrSell, lblSearchCustomerID, lblSearchCustomerName,
							lblSearchCustomerAddress, lblCustomerName, lblCustomerAddress, lblCustomerPhone, lblSearchEmployeeName,
							lblCustomerDOB, lblEmpJob, lbldeleteEmployee, lblEnterIDCustomer, lblEmpName, lblEmpAddress, lblEmpPhone, lblEmpSalary, 
							employeeHeader, lblEmpDOB;
	protected static JPanel searchTransIDPanel, searchEmployeeNamePanel, searchCustomerIdPanel, searchCarIdPanel, searchCarMakePanel, 
							searchCarModelPanel, searchCarBuySellPanel, searchTransNamePanel, carPanel, searchEmployeeIdPanel, searchEmployeeJobPanel,
							searchTransCarMakePanel, searchCustomerNamePanel, searchCustomerAddressPanel, customerPanel, employeePanel, newReminderPanel, transactionPanel; 
	protected static JTextArea  transactionShow, carShow, customerShow, employeeShow;
	protected static JTextArea plannerShow = new JTextArea();
	protected static JScrollPane scroller1, scroller2, scroller3, scroller4, scroller5;
	protected static JComboBox<String> searchOptions, listCustomerSearch, listEmployeeSearch, listCarSearch; 
	//combo box options
	private String[] choices = {"Select Here", "Id", "Name", "Car Make"};
	private String[] carChoices = {"Select Here", "Id", "Make", "Model", "Buy/Sell"};
	private String[] customerChoices = {"Select Here", "Id", "Name", "Address"};
	private String[] employeeChoices = {"Select Here", "Id", "Name", "Job"};
	//custom fonts
	protected Font customFont1 = new Font("Courier", Font.BOLD, 15);
	protected Font customFont2 = new Font("Courier", Font.ITALIC, 13);
	protected Font customFont3 = new Font("Sans Serif", Font.BOLD, 22);
	protected Font customFont4 = new Font("Sans Serif", Font.BOLD, 40);
	//declaring my event handlers
	protected ButtonHandler btnEventHandler;
	protected MenuHandler menuEventHandler;
	protected ListHandler lstEventHandler;
	//declaring and instantiating my custom SQL storage class and result set variable
	protected static SQLStorage dataStorage = new SQLStorage();
	protected static ResultSet results;
	//declaring and instantiating my custom classes
	protected static Planner myPlanner = new Planner();
	protected static Car myCar = new Car();
	protected static Transaction myTransaction = new Transaction();
	protected static Employee myEmployee = new Employee();
	protected static Customer myCustomer = new Customer();
	//Creates date class and formats it to a string for text field
	protected static Date date = new Date();
	protected static SimpleDateFormat formatDate = new SimpleDateFormat();
	protected static String reportDate = formatDate.format(date);
	
	//MainMenuWindowConstructor
	public MainMenuWindow()
	{
		//creating frame
		setTitle("Main Menu");
		setSize(1365, 810);
		setResizable(false); 
		//instantiating my custom event handler classes
		btnEventHandler = new ButtonHandler();
		menuEventHandler = new MenuHandler();
		lstEventHandler = new ListHandler();
		
		//set background of frame and layout
	    setContentPane(new JLabel(new ImageIcon("images/AutoBackground.jpg")));
	    setLayout(null);
	    
	    //calling methods to create GUI components
	    createMenuBar();
	    createTimeAndTimeRefresh();
	    createPlannerArea();
	    createNewReminderPanel();
	    createSearchToEditReminder();
	    createReminderUpdateButton();
	    deleteReminder();
	    createTransactionPanel();
	    createTotalCalcButton();
	    createCarArea();
	    createCustomerArea();
	    createTransactionArea();
	    createEmployeeArea();
	    createTransactionUpdateButton();
	    createSearchToEditTransaction();
	    createEmployeePanel();
	    deleteEmployee();
	    createSearchToEditEmployee();
	    createCarPanelAndEditOptions();
	    createCustomerPanelAndEditOptions();
	    createSearchTransactionsSelectionAndPanels();
	    createSearchCarsSelectionAndPanels();
	    createSearchCustomersSelectionAndPanels();
	    createSearchEmployeesSelectionAndPanels();
	    deleteTransaction();
	    setCustomerMenu();
	    setEmployeeMenu();
	    setCarMenu();
	    setTransactionMenu();
	    setPlannerMenu();
	    
	    //set visibility and exit on close
	    setVisible(true);
		
	    //add window listener for closing the application
	    addWindowListener(new WindowAdapter() 
	    {
	    	public void windowClosing(WindowEvent evt) 
	        {
	    		//calls onExit()
	    	    onExit();
	    	}
	    });
	    
	}//End of constructor
	
	//Called inside the window listener when the application closes
	public void onExit() 
	{
		try
		{
	       if(SQLStorage.stmnt!=null)
	    	   SQLStorage.stmnt.close();
	    }
		catch(SQLException se)
		{
			JOptionPane.showMessageDialog(null, "The database statements were not closed, database still open",
					"Error", JOptionPane.ERROR_MESSAGE);
 		}
		try
		{
	       if(SQLStorage.con!=null)
	    	   SQLStorage.con.close();
		}
	    catch(SQLException se)
   	    {
	    	JOptionPane.showMessageDialog(null, "The database statements were not closed, database still open",
					"Error", JOptionPane.ERROR_MESSAGE);
	    }
		 
		System.exit(0);
	}
	
	//Creates the Menu bar
	private void createMenuBar()
	{
		menuBar.setLocation(0, 0);
	    menuBar.setSize(1365, 200);
	    setJMenuBar(menuBar);
	}
	
	//Delete reminder button, text field and label
	private void deleteReminder()
	{
		lbldeleteReminder = new JLabel("Input ID of reminder to delete");
		lbldeleteReminder.setSize(300, 30);
		lbldeleteReminder.setLocation(840, 10);
		lbldeleteReminder.setVisible(false);
		lbldeleteReminder.setForeground(Color.white);
		add(lbldeleteReminder);
		btnDeleteReminder = new JButton("Delete Reminder");
		btnDeleteReminder.setSize(130, 30);
		btnDeleteReminder.setLocation(840, 40);
		btnDeleteReminder.setVisible(false);
		add(btnDeleteReminder);
		btnDeleteReminder.addActionListener(btnEventHandler);
		txtDeleteReminder = new JTextField();
		txtDeleteReminder.setSize(60, 30);
		txtDeleteReminder.setLocation(980, 40);
		txtDeleteReminder.setVisible(false);
		add(txtDeleteReminder);
	}
	
	//Delete transaction button, text field and label
	private void deleteTransaction()
	{
		lbldeleteTransaction = new JLabel("Input ID of transaction to delete");
		lbldeleteTransaction.setSize(300, 30);
		lbldeleteTransaction.setLocation(280, 480);
		lbldeleteTransaction.setVisible(false);
		lbldeleteTransaction.setForeground(Color.white);
		add(lbldeleteTransaction);
		btnDeleteTransaction = new JButton("Delete Transaction");
		btnDeleteTransaction.setSize(160, 30);
		btnDeleteTransaction.setLocation(280, 520);
		btnDeleteTransaction.setVisible(false);
		add(btnDeleteTransaction);
		btnDeleteTransaction.addActionListener(btnEventHandler);
		txtDeleteTransaction = new JTextField();
		txtDeleteTransaction.setSize(60, 30);
		txtDeleteTransaction.setLocation(280, 560);
		txtDeleteTransaction.setVisible(false);
		add(txtDeleteTransaction);
	}
	
	//Creates an update button to update reminders
	private void createReminderUpdateButton()
	{
		btnReminderUpdate = new JButton("Update Reminder");
		btnReminderUpdate.addActionListener(btnEventHandler);
		btnReminderUpdate.setSize(200, 40);
		btnReminderUpdate.setLocation(680, 450);
		btnReminderUpdate.setEnabled(false);
		btnReminderUpdate.setVisible(false);
		add(btnReminderUpdate);
	}
	
	//Creates an update button to update transactions
	private void createTransactionUpdateButton()
	{
		btnTransactionUpdate = new JButton("Update Transaction");
		btnTransactionUpdate.addActionListener(btnEventHandler);
		btnTransactionUpdate.setSize(165, 40);
		btnTransactionUpdate.setLocation(20, 560);
		btnTransactionUpdate.setEnabled(false);
		btnTransactionUpdate.setVisible(false);
		add(btnTransactionUpdate);
	}
	
	//Creates the car panel and its edit car options
	private void createCarPanelAndEditOptions()
	{
		carPanel = new JPanel();
		carPanel.setLayout(new GridLayout(7,2));
		carPanel.setSize(500, 400);
		carPanel.setLocation(20, 70);
		lblCarMake = new JLabel("Car Make:");
		lblCarModel = new JLabel("Car Model:");
		lblCarRegistration = new JLabel("Car Reg:");
		lblCarColor = new JLabel("Car Colour:");
		lblCarEngine = new JLabel("Car Engine:");
		lblCarBuyOrSell = new JLabel("Car Buy Or Sell:");
		txtCarMake  = new JTextField();
		txtCarModel = new JTextField();
		txtCarRegistration = new JTextField();
		txtCarColor = new JTextField();
		txtCarEngine = new JTextField();
		txtCarBuyOrSell = new JTextField();
		btnUpdateCar = new JButton("Update Car");
		btnExitCar = new JButton("Exit Car");
		
		carPanel.add(lblCarMake);
		carPanel.add(txtCarMake);
		carPanel.add(lblCarModel);
		carPanel.add(txtCarModel);
		carPanel.add(lblCarRegistration);
		carPanel.add(txtCarRegistration);
		carPanel.add(lblCarColor);
		carPanel.add(txtCarColor);
		carPanel.add(lblCarEngine);
		carPanel.add(txtCarEngine);
		carPanel.add(lblCarBuyOrSell);
		carPanel.add(txtCarBuyOrSell);
		carPanel.add(btnUpdateCar);
		carPanel.add(btnExitCar);
		btnUpdateCar.setEnabled(false);
		btnUpdateCar.addActionListener(btnEventHandler);
		transactionPanel.add(btnNewTransactionExit);
		btnExitCar.addActionListener(btnEventHandler);
		add(carPanel);
		carPanel.setBorder(BorderFactory.createTitledBorder("Car Panel"));
		carPanel.setVisible(false);
		
		lblEnterIDCar = new JLabel("Enter ID of Car you want to edit");
		lblEnterIDCar.setLocation(20, 480);
		lblEnterIDCar.setSize(250, 30);
		lblEnterIDCar.setForeground(Color.white);
		lblEnterIDCar.setVisible(false);
		add(lblEnterIDCar);
			 
		btnSearchCarEdit = new JButton("Find Cars");
	    btnSearchCarEdit.addActionListener(btnEventHandler);
		btnSearchCarEdit.setLocation(85, 520);
		btnSearchCarEdit.setSize(145, 30);
		btnSearchCarEdit.setVisible(false);
		add(btnSearchCarEdit);
			    
		txtSearchCarEdit = new JTextField();
		txtSearchCarEdit.setLocation(20, 520);
		txtSearchCarEdit.setSize(50, 30);
		txtSearchCarEdit.setVisible(false);
		add(txtSearchCarEdit );
			
		}//end createTransactionPanel

	//Creates the customer panel and edit options
	private void createCustomerPanelAndEditOptions()
	{
		customerPanel = new JPanel();
		customerPanel.setLayout(new GridLayout(5,2));
		customerPanel.setSize(500, 400);
		customerPanel.setLocation(20, 70);
		lblCustomerName = new JLabel("Customer Name:");
		lblCustomerAddress = new JLabel("Customer Address:");
		lblCustomerPhone = new JLabel("Customer Phone:");
		lblCustomerDOB = new JLabel("Customer DOB:");
		txtCustomerName = new JTextField();
		txtCustomerAddress = new JTextField();
		txtCustomerPhone = new JTextField();
		txtCustomerDOB = new JTextField();
		btnUpdateCustomer = new JButton("Update Customer");
		btnExitCustomer = new JButton("Exit Customer");
			
		customerPanel.add(lblCustomerName);
		customerPanel.add(txtCustomerName);
		customerPanel.add(lblCustomerAddress);
		customerPanel.add(txtCustomerAddress);
		customerPanel.add(lblCustomerPhone);
		customerPanel.add(txtCustomerPhone);
		customerPanel.add(lblCustomerDOB);
		customerPanel.add(txtCustomerDOB);
		customerPanel.add(btnUpdateCustomer);
		customerPanel.add(btnExitCustomer);
		btnUpdateCustomer.setEnabled(false);
		btnUpdateCustomer.addActionListener(btnEventHandler);
		customerPanel.add(btnExitCustomer);
		btnExitCustomer.addActionListener(btnEventHandler);
		add(customerPanel);
		customerPanel.setBorder(BorderFactory.createTitledBorder("Customer Panel"));
		customerPanel.setVisible(false);
		
		lblEnterIDCustomer = new JLabel("Enter ID of Customer you want to edit");
		lblEnterIDCustomer.setLocation(20, 480);
		lblEnterIDCustomer.setSize(250, 30);
		lblEnterIDCustomer.setForeground(Color.white);
		lblEnterIDCustomer.setVisible(false);
		add(lblEnterIDCustomer);
				 
		btnSearchCustomerEdit = new JButton("Find Customers");
		btnSearchCustomerEdit.addActionListener(btnEventHandler);
		btnSearchCustomerEdit.setLocation(85, 520);
		btnSearchCustomerEdit.setSize(145, 30);
		btnSearchCustomerEdit.setVisible(false);
		add(btnSearchCustomerEdit);
				    
		txtSearchCustomerEdit = new JTextField();
		txtSearchCustomerEdit.setLocation(20, 520);
		txtSearchCustomerEdit.setSize(50, 30);
		txtSearchCustomerEdit.setVisible(false);
		add(txtSearchCustomerEdit);
	}//end createTransactionPanel
	
	//creates the text field label and button for search the transaction you want to edit
	private void createSearchToEditTransaction()
	{
		 lblEnterIDTransaction = new JLabel("Enter ID of transaction you want to edit");
		 lblEnterIDTransaction.setLocation(20, 480);
		 lblEnterIDTransaction.setSize(250, 30);
		 lblEnterIDTransaction.setForeground(Color.white);
		 lblEnterIDTransaction.setVisible(false);
		 add(lblEnterIDTransaction);
		 
		 btnSearchTransactionEdit = new JButton("Find Transactions");
		 btnSearchTransactionEdit.addActionListener(btnEventHandler);
		 btnSearchTransactionEdit.setLocation(85, 520);
		 btnSearchTransactionEdit.setSize(145, 30);
		 btnSearchTransactionEdit.setVisible(false);
		 add(btnSearchTransactionEdit);
		    
		 txtSearchTransactionEdit = new JTextField();
		 txtSearchTransactionEdit.setLocation(20, 520);
		 txtSearchTransactionEdit.setSize(50, 30);
		 txtSearchTransactionEdit.setVisible(false);
		 add(txtSearchTransactionEdit );
	}
	
	//Creates search transactions combo box option and search panels
	public void createSearchTransactionsSelectionAndPanels()
	{
		searchOptions = new JComboBox<String>(choices);
		searchOptions.setSize(300, 25);
		searchOptions.setLocation(25, 600);
		searchOptions.setMaximumRowCount(5);
		searchOptions.addItemListener(lstEventHandler);
		add(searchOptions);
		searchOptions.setVisible(false);
		
		lblSearchTransactions = new JLabel("Search the transactions by:");
		lblSearchTransactions.setSize(300, 25);
		lblSearchTransactions.setLocation(25, 575);
		lblSearchTransactions.setForeground(Color.white);
		add(lblSearchTransactions);
		lblSearchTransactions.setVisible(false);
		
		lblSearchTransID = new JLabel("Enter ID"); 
		lblSearchTransName = new JLabel("Enter Customer Name");
		lblSearchTransCarMake = new JLabel("Enter Car Make");
		txtSearchTransID = new JTextField();
		txtSearchTransName = new JTextField();
		txtSearchTransCarMake = new JTextField();
		btnSearchTransID = new JButton("Search by ID");
		btnSearchTransName = new JButton("Search by Name");
		btnSearchTransCarMake = new JButton("Search by Make");
		
		searchTransIDPanel = new JPanel();
		searchTransIDPanel.setLocation(350, 585);
		searchTransIDPanel.setSize(280, 70);
		searchTransIDPanel.setLayout(new GridLayout(2,2));
		searchTransIDPanel.add(lblSearchTransID);
		searchTransIDPanel.add(txtSearchTransID);
		btnSearchTransID.addActionListener(btnEventHandler);
		searchTransIDPanel.add(btnSearchTransID);
		searchTransIDPanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchTransIDPanel);
		searchTransIDPanel.setVisible(false);
		
		searchTransNamePanel = new JPanel();
		searchTransNamePanel.setLocation(350, 585);
		searchTransNamePanel.setSize(280, 70);
		searchTransNamePanel.setLayout(new GridLayout(2,2));
		searchTransNamePanel.add(lblSearchTransName);
		searchTransNamePanel.add(txtSearchTransName);
		searchTransNamePanel.add(btnSearchTransName);
		btnSearchTransName.addActionListener(btnEventHandler);
		searchTransNamePanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchTransNamePanel);
		searchTransNamePanel.setVisible(false);
		
		searchTransCarMakePanel = new JPanel();
		searchTransCarMakePanel.setLocation(350, 585);
		searchTransCarMakePanel.setSize(280, 70);
		searchTransCarMakePanel.setLayout(new GridLayout(2,2));
		searchTransCarMakePanel.add(lblSearchTransCarMake);
		searchTransCarMakePanel.add(txtSearchTransCarMake);
		searchTransCarMakePanel.add(btnSearchTransCarMake);
		btnSearchTransCarMake.addActionListener(btnEventHandler);
		searchTransCarMakePanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchTransCarMakePanel);
		searchTransCarMakePanel.setVisible(false);
	}
	
	//Creates search cars combo box option and search panels
	public void createSearchCarsSelectionAndPanels()
	{
		listCarSearch = new JComboBox<String>(carChoices);
		listCarSearch.setSize(300, 25);
		listCarSearch.setLocation(25, 600);
		listCarSearch.setMaximumRowCount(5);
		listCarSearch.addItemListener(lstEventHandler);
		add(listCarSearch);
		listCarSearch.setVisible(false);
		
		lblSearchCars = new JLabel("Search the Cars by:");
		lblSearchCars.setSize(300, 25);
		lblSearchCars.setLocation(25, 575);
		lblSearchCars.setForeground(Color.white);
		add(lblSearchCars);
		lblSearchCars.setVisible(false); 
			
		lblSearchCarsID = new JLabel("Enter Car ID"); 
		lblSearchCarsMake = new JLabel("Enter Cars Make");
		lblSearchCarsModel = new JLabel("Enter Cars Model");
		lblSearchBuyOrSell = new JLabel("Enter Car Buy or sell");
		txtSearchCarsID  = new JTextField();
		txtSearchCarsMake = new JTextField();
		txtSearchCarsModel = new JTextField();
		txtSearchBuyOrSell = new JTextField();
		btnSearchCarID = new JButton("Search Car ID");
		btnSearchCarMake = new JButton("Search Car Make");
		btnSearchCarModel = new JButton("Search Car Model");
		btnSearchCarBuySell = new JButton("Search Car Buy/Sell");
			
		searchCarIdPanel = new JPanel();
		searchCarIdPanel.setLocation(350, 585);
		searchCarIdPanel.setSize(280, 70);
		searchCarIdPanel.setLayout(new GridLayout(2,2));
		searchCarIdPanel.add(lblSearchCarsID);
		searchCarIdPanel.add(txtSearchCarsID);
		btnSearchCarID.addActionListener(btnEventHandler);
		searchCarIdPanel.add(btnSearchCarID);
		searchCarIdPanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchCarIdPanel);
		searchCarIdPanel.setVisible(false);
			
		searchCarMakePanel = new JPanel();
		searchCarMakePanel.setLocation(350, 585);
		searchCarMakePanel.setSize(280, 70);
		searchCarMakePanel.setLayout(new GridLayout(2,2));
		searchCarMakePanel.add(lblSearchCarsMake);
		searchCarMakePanel.add(txtSearchCarsMake);
		searchCarMakePanel.add(btnSearchCarMake);
		btnSearchCarMake.addActionListener(btnEventHandler);
		searchCarMakePanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchCarMakePanel);
		searchCarMakePanel.setVisible(false);
			
		searchCarModelPanel = new JPanel();
		searchCarModelPanel.setLocation(350, 585);
		searchCarModelPanel.setSize(280, 70);
		searchCarModelPanel.setLayout(new GridLayout(2,2));
		searchCarModelPanel.add(lblSearchCarsModel);
		searchCarModelPanel.add(txtSearchCarsModel);
		searchCarModelPanel.add(btnSearchCarModel);
		btnSearchCarModel.addActionListener(btnEventHandler);
		searchCarModelPanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchCarModelPanel);
		searchCarModelPanel.setVisible(false);
			
		searchCarBuySellPanel = new JPanel();
		searchCarBuySellPanel.setLocation(350, 585);
		searchCarBuySellPanel.setSize(280, 70);
		searchCarBuySellPanel.setLayout(new GridLayout(2,2));
		searchCarBuySellPanel.add(lblSearchBuyOrSell);
		searchCarBuySellPanel.add(txtSearchBuyOrSell);
		searchCarBuySellPanel.add(btnSearchCarBuySell);
		btnSearchCarBuySell.addActionListener(btnEventHandler);
		searchCarBuySellPanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchCarBuySellPanel);
		searchCarBuySellPanel.setVisible(false);
	}
	
	//Creates search customer combo box option and search panels
	public void createSearchCustomersSelectionAndPanels()
	{
		listCustomerSearch = new JComboBox<String>(customerChoices);
		listCustomerSearch.setSize(300, 25);
		listCustomerSearch.setLocation(25, 600);
		listCustomerSearch.setMaximumRowCount(5);
		listCustomerSearch.addItemListener(lstEventHandler);
		add(listCustomerSearch);
		listCustomerSearch.setVisible(false);
		
		lblSearchCustomers = new JLabel("Search the Customers by:");
		lblSearchCustomers.setSize(300, 25);
		lblSearchCustomers.setLocation(25, 575);
		lblSearchCustomers.setForeground(Color.white);
		add(lblSearchCustomers);
		lblSearchCustomers.setVisible(false); 
			
		lblSearchCustomerID = new JLabel("Enter Customer ID"); 
		lblSearchCustomerName = new JLabel("Enter Customer Name");
		lblSearchCustomerAddress = new JLabel("Enter Customer Address");
		txtSearchCustomerID  = new JTextField();
		txtSearchCustomerName = new JTextField();
		txtSearchCustomerAddress = new JTextField();
		btnSearchCustomerID = new JButton("Search Customer ID");
		btnSearchCustomerName = new JButton("Search Cust Name");
		btnSearchCustomerAddress = new JButton("Search Cust Address");
			
		searchCustomerIdPanel = new JPanel();
		searchCustomerIdPanel.setLocation(350, 585);
		searchCustomerIdPanel.setSize(325, 70);
		searchCustomerIdPanel.setLayout(new GridLayout(2,2));
		searchCustomerIdPanel.add(lblSearchCustomerID);
		searchCustomerIdPanel.add(txtSearchCustomerID);
		btnSearchCustomerID.addActionListener(btnEventHandler);
		searchCustomerIdPanel.add(btnSearchCustomerID);
		searchCustomerIdPanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchCustomerIdPanel);
		searchCustomerIdPanel.setVisible(false);
			
		searchCustomerNamePanel = new JPanel();
		searchCustomerNamePanel.setLocation(350, 585);
		searchCustomerNamePanel.setSize(325, 70);
		searchCustomerNamePanel.setLayout(new GridLayout(2,2));
		searchCustomerNamePanel.add(lblSearchCustomerName);
		searchCustomerNamePanel.add(txtSearchCustomerName);
		searchCustomerNamePanel.add(btnSearchCustomerName);
		btnSearchCustomerName.addActionListener(btnEventHandler);
		searchCustomerNamePanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchCustomerNamePanel);
		searchCustomerNamePanel.setVisible(false);
			
		searchCustomerAddressPanel = new JPanel();
		searchCustomerAddressPanel.setLocation(350, 585);
		searchCustomerAddressPanel.setSize(325, 70);
		searchCustomerAddressPanel.setLayout(new GridLayout(2,2));
		searchCustomerAddressPanel.add(lblSearchCustomerAddress);
		searchCustomerAddressPanel.add(txtSearchCustomerAddress);
		searchCustomerAddressPanel.add(btnSearchCustomerAddress);
		btnSearchCustomerAddress.addActionListener(btnEventHandler);
		searchCustomerAddressPanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchCustomerAddressPanel);
		searchCustomerAddressPanel.setVisible(false);
	}
	
	//Creates search employees combo box option and search panels
	public void createSearchEmployeesSelectionAndPanels()
	{
		listEmployeeSearch = new JComboBox<String>(employeeChoices);
		listEmployeeSearch.setSize(300, 25);
		listEmployeeSearch.setLocation(25, 600);
		listEmployeeSearch.setMaximumRowCount(5);
		listEmployeeSearch.addItemListener(lstEventHandler);
		add(listEmployeeSearch);
		listEmployeeSearch.setVisible(false);
		
		lblSearchEmployee = new JLabel("Search the Employees by:");
		lblSearchEmployee.setSize(300, 25);
		lblSearchEmployee.setLocation(25, 575);
		lblSearchEmployee.setForeground(Color.white);
		add(lblSearchEmployee);
		lblSearchEmployee.setVisible(false); 
			
		lblSearchEmployeeID = new JLabel("Enter Employee ID"); 
		lblSearchEmployeeName = new JLabel("Enter Employee Name");
		lblSearchEmployeeJob = new JLabel("Enter Employee Job");
		txtSearchEmployeeID  = new JTextField();
		txtSearchEmployeeName = new JTextField();
		txtSearchEmployeeJob = new JTextField();
		btnSearchEmployeeID = new JButton("Search Employee ID");
		btnSearchEmployeeName = new JButton("Search Emp Name");
		btnSearchEmployeeJob = new JButton("Search Emp Job");
			
		searchEmployeeIdPanel = new JPanel();
		searchEmployeeIdPanel.setLocation(350, 585);
		searchEmployeeIdPanel.setSize(325, 70);
		searchEmployeeIdPanel.setLayout(new GridLayout(2,2));
		searchEmployeeIdPanel.add(lblSearchEmployeeID);
		searchEmployeeIdPanel.add(txtSearchEmployeeID);
		searchEmployeeIdPanel.add(btnSearchEmployeeID);
		btnSearchEmployeeID.addActionListener(btnEventHandler);
		searchEmployeeIdPanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchEmployeeIdPanel);
		searchEmployeeIdPanel.setVisible(false);
			
		searchEmployeeNamePanel = new JPanel();
		searchEmployeeNamePanel.setLocation(350, 585);
		searchEmployeeNamePanel.setSize(325, 70);
		searchEmployeeNamePanel.setLayout(new GridLayout(2,2));
		searchEmployeeNamePanel.add(lblSearchEmployeeName);
		searchEmployeeNamePanel.add(txtSearchEmployeeName);
		searchEmployeeNamePanel.add(btnSearchEmployeeName);
		btnSearchEmployeeName.addActionListener(btnEventHandler);
		searchEmployeeNamePanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchEmployeeNamePanel);
		searchEmployeeNamePanel.setVisible(false);
			
		searchEmployeeJobPanel = new JPanel();
		searchEmployeeJobPanel.setLocation(350, 585);
		searchEmployeeJobPanel.setSize(325, 70);
		searchEmployeeJobPanel.setLayout(new GridLayout(2,2));
		searchEmployeeJobPanel.add(lblSearchEmployeeJob);
		searchEmployeeJobPanel.add(txtSearchEmployeeJob);
		searchEmployeeJobPanel.add(btnSearchEmployeeJob);
		btnSearchEmployeeJob.addActionListener(btnEventHandler);
		searchEmployeeJobPanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
		add(searchEmployeeJobPanel);
		searchEmployeeJobPanel.setVisible(false);
	}
	
	//Creates the add new reminder panel
	private void createNewReminderPanel()
	{
		newReminderPanel = new JPanel();
		newReminderPanel.setLayout(new GridLayout(4,2));
		newReminderPanel.setSize(445, 200);
		newReminderPanel.setLocation(890, 450);
		lblPlannerDate = new JLabel("Date");
		lblPlannerTime = new JLabel("Time");
		lblPlannerDescription = new JLabel("Description");
		txtPlannerDate = new JTextField();
		txtPlannerTime = new JTextField();
		txtPlannerDescription = new JTextField();
		btnNewRemindAdd = new JButton("Add Reminder");
		btnNewRemindAdd.addActionListener(btnEventHandler);
		btnNewRemindExit = new JButton("Exit Reminders");
		btnNewRemindExit.addActionListener(btnEventHandler);
		newReminderPanel.add(lblPlannerDate);
		newReminderPanel.add(txtPlannerDate);
		newReminderPanel.add(lblPlannerTime);
		newReminderPanel.add(txtPlannerTime);
		newReminderPanel.add(lblPlannerDescription);
		newReminderPanel.add(txtPlannerDescription);
		newReminderPanel.add(btnNewRemindAdd);
		newReminderPanel.add(btnNewRemindExit);
		newReminderPanel.setBorder(BorderFactory.createTitledBorder("Reminder Panel"));
	    add(newReminderPanel);
	    newReminderPanel.setVisible(false);
	}
	
	//creates the new transaction panel
	private void createTransactionPanel()
	{
		transactionPanel = new JPanel();
		transactionPanel.setLayout(new GridLayout(16,2));
		transactionPanel.setSize(500, 400);
		transactionPanel.setLocation(20, 70);
		lblTransCustName = new JLabel("Customer Name:");
		lblTransCustAddress = new JLabel("Customer Address:");
		lblTransCustPhone = new JLabel("Customer Phone:");
		lblTransCustDOB = new JLabel("Customer DOB:");
		lblTransCustInsureC = new JLabel("Customer Insurance Company:");
		lblTransCustNCB = new JLabel("Customer Years NoClaimsBonus:");
		lblTransCustMake = new JLabel("Car Make:");
		lblTransCustModel = new JLabel("Car Model:");
		lblTransCustReg = new JLabel("Car Registration");
		lblTransCustColor = new JLabel("Car Color");
		lblTransCustEngine = new JLabel("Car Engine");
		lblBuyOrSell = new JLabel("Buying or Selling the car? ..Enter 'Buy'/'Sell'");
		lblTransCustPrice = new JLabel("Car Price");
		lblTransCustVAT = new JLabel("VAT Amount%");
		lblTransCustTotal = new JLabel("Total Price");
		txtTransCustName = new JTextField();
		txtTransCustAddress = new JTextField();
		txtTransCustPhone = new JTextField();
		txtTransCustDOB = new JTextField();
		txtTransCustInsureC = new JTextField();
		txtTransCustNCB = new JTextField();
		txtTransCustMake = new JTextField();
		txtTransCustModel = new JTextField();
		txtTransCustReg = new JTextField();
		txtTransCustColor = new JTextField();
		txtTransCustEngine = new JTextField();
		txtTransCustPrice = new JTextField();
		txtTransCustVAT = new JTextField();
		txtTransCustTotal = new JTextField();
		txtTransCustTotal.setEditable(false);
		txtBuyOrSell = new JTextField();
		btnNewTransactionAdd = new JButton("Add Transaction");
		btnNewTransactionExit = new JButton("Exit Transactions");
		transactionHeader = new JLabel("Transactions");
		transactionHeader.setSize(150, 30);
		transactionHeader.setLocation(210, 20);
		transactionHeader.setForeground(Color.white);
		transactionHeader.setFont(customFont3);
		transactionPanel.add(lblTransCustName);
		transactionPanel.add(txtTransCustName);
		transactionPanel.add(lblTransCustAddress);
		transactionPanel.add(txtTransCustAddress);
		transactionPanel.add(lblTransCustPhone);
		transactionPanel.add(txtTransCustPhone);
		transactionPanel.add(lblTransCustDOB);
		transactionPanel.add(txtTransCustDOB);
		transactionPanel.add(lblTransCustInsureC);
		transactionPanel.add(txtTransCustInsureC);
		transactionPanel.add(lblTransCustNCB);
		transactionPanel.add(txtTransCustNCB);
		transactionPanel.add(lblTransCustMake);
		transactionPanel.add(txtTransCustMake);
		transactionPanel.add(lblTransCustModel);
		transactionPanel.add(txtTransCustModel);
		transactionPanel.add(lblTransCustReg);
		transactionPanel.add(txtTransCustReg);
		transactionPanel.add(lblTransCustColor);
		transactionPanel.add(txtTransCustColor);
		transactionPanel.add(lblTransCustEngine);
		transactionPanel.add(txtTransCustEngine);
		transactionPanel.add(lblBuyOrSell);
		transactionPanel.add(txtBuyOrSell);
		transactionPanel.add(lblTransCustPrice);
		transactionPanel.add(txtTransCustPrice);
		transactionPanel.add(lblTransCustVAT);
		transactionPanel.add(txtTransCustVAT);
		transactionPanel.add(lblTransCustTotal);
		transactionPanel.add(txtTransCustTotal);
		transactionPanel.add(btnNewTransactionAdd);
		transactionPanel.setBorder(BorderFactory.createTitledBorder("Transaction Panel"));
		btnNewTransactionAdd.addActionListener(btnEventHandler);
		transactionPanel.add(btnNewTransactionExit);
		btnNewTransactionExit.addActionListener(btnEventHandler);
		add(transactionHeader);
		add(transactionPanel);
		transactionPanel.setVisible(false);
		transactionHeader.setVisible(false);
	}//end createTransactionPanel
	
	//creates the new employee panel
	private void createEmployeePanel()
	{
		employeePanel = new JPanel();
		employeePanel.setLayout(new GridLayout(7,2));
		employeePanel.setSize(500, 400);
		employeePanel.setLocation(20, 70);
		lblEmpName = new JLabel("Employee Name:");
		lblEmpAddress = new JLabel("Employee Address:");
		lblEmpPhone = new JLabel("Employee Phone:");
		lblEmpDOB = new JLabel("Employee DOB:");
		lblEmpJob = new JLabel("Employee Job:");
		lblEmpSalary = new JLabel("Employee Yearly Salary:");
		
		txtEmpName = new JTextField();
		txtEmpAddress = new JTextField();
		txtEmpPhone = new JTextField();
		txtEmpDOB = new JTextField();
		txtEmpJob = new JTextField();
		txtEmpSalary = new JTextField();
		
		btnEmployeeAdd = new JButton("Add Employee");
		btnEmployeeExit = new JButton("Exit Employee");
		employeeHeader = new JLabel("Employees");
		employeeHeader.setSize(150, 30);
		employeeHeader.setLocation(210, 20);
		employeeHeader.setForeground(Color.white);
		employeeHeader.setFont(customFont3);
		employeePanel.add(lblEmpName);
		employeePanel.add(txtEmpName);
		employeePanel.add(lblEmpAddress);
		employeePanel.add(txtEmpAddress);
		employeePanel.add(lblEmpPhone);
		employeePanel.add(txtEmpPhone);
		employeePanel.add(lblEmpDOB);
		employeePanel.add(txtEmpDOB);
		employeePanel.add(lblEmpJob);
		employeePanel.add(txtEmpJob);
		employeePanel.add(lblEmpSalary);
		employeePanel.add(txtEmpSalary);
		
		employeePanel.add(btnEmployeeAdd);
		employeePanel.setBorder(BorderFactory.createTitledBorder("Employee Panel"));
		btnEmployeeAdd.addActionListener(btnEventHandler);
		employeePanel.add(btnEmployeeExit);
		btnEmployeeExit.addActionListener(btnEventHandler);
		add(employeeHeader);
		add(employeePanel);
		employeePanel.setVisible(false);
		employeeHeader.setVisible(false);
	}
	
	//creates the delete employee components
	private void deleteEmployee()
	{
		lbldeleteEmployee = new JLabel("Input ID of employee to delete");
		lbldeleteEmployee.setSize(300, 30);
		lbldeleteEmployee.setLocation(280, 480);
		lbldeleteEmployee.setVisible(false);
		lbldeleteEmployee.setForeground(Color.white);
		add(lbldeleteEmployee);
		btnDeleteEmployee = new JButton("Delete Employee");
		btnDeleteEmployee.setSize(160, 30);
		btnDeleteEmployee.setLocation(280, 520);
		btnDeleteEmployee.setVisible(false);
		add(btnDeleteEmployee);
		btnDeleteEmployee.addActionListener(btnEventHandler);
		txtDeleteEmployee = new JTextField();
		txtDeleteEmployee.setSize(60, 30);
		txtDeleteEmployee.setLocation(280, 560);
		txtDeleteEmployee.setVisible(false);
		add(txtDeleteEmployee);
	}
	
	//Creates the edit reminder buttons, labels, text fields
	private void createSearchToEditEmployee()
	{
		lblEnterIDEmployee = new JLabel("Enter ID of employee you want to edit");
		lblEnterIDEmployee.setLocation(20, 480);
		lblEnterIDEmployee.setSize(210, 30);
     	lblEnterIDEmployee.setForeground(Color.white);
    	lblEnterIDEmployee.setVisible(false);
	    add(lblEnterIDEmployee);
			    
	    btnSearchEmployeeEdit = new JButton("Find Employee");
		btnSearchEmployeeEdit.addActionListener(btnEventHandler);
    	btnSearchEmployeeEdit.setLocation(85, 520);
	    btnSearchEmployeeEdit.setSize(135, 30);
	    btnSearchEmployeeEdit.setVisible(false);
		add(btnSearchEmployeeEdit);
			    
		txtSearchEmployeeEdit = new JTextField();
		txtSearchEmployeeEdit.setLocation(20, 520);
		txtSearchEmployeeEdit.setSize(50, 30);
		txtSearchEmployeeEdit.setVisible(false);
		add(txtSearchEmployeeEdit);
			 
		btnEmployeeUpdate = new JButton("Update Employee");
		btnEmployeeUpdate.addActionListener(btnEventHandler);
		btnEmployeeUpdate.setSize(165, 40);
		btnEmployeeUpdate.setLocation(20, 560);
		btnEmployeeUpdate.setEnabled(false);
		btnEmployeeUpdate.setVisible(false);
		add( btnEmployeeUpdate);
	}
	
	//creates the calculate total button
	private void createTotalCalcButton()
	{
		btnCalcTotalPrice = new JButton("Calculate Total");
		btnCalcTotalPrice.setLocation(530, 370);
		btnCalcTotalPrice.setSize(120, 40);
		btnCalcTotalPrice.addActionListener(btnEventHandler);
		add(btnCalcTotalPrice);
		btnCalcTotalPrice.setVisible(false);
	}
	
	//Creates show transactions area
	private void createTransactionArea()
	{
		transactionShow = new JTextArea(1000, 15);
		scroller2 = new JScrollPane(transactionShow);
		scroller2.setLocation(30, 70);
		scroller2.setSize(600, 500);
		transactionShow.setEditable(false);
		scroller2.setVisible(false);
		add(scroller2);
	}
	
	//creates show cars area
	private void createCarArea()
	{
		lblCars = new JLabel("Cars");
		lblCars.setForeground(Color.white);
		lblCars.setLocation(200, 20);
		lblCars.setSize(90, 60);
		lblCars.setFont(customFont4);
		lblCars.setVisible(false);
		add(lblCars);
		
		carShow = new JTextArea(1000, 15);
		scroller3 = new JScrollPane(carShow);
		scroller3.setLocation(30, 70);
		scroller3.setSize(600, 500);
		carShow.setEditable(false);
		scroller3.setVisible(false);
		add(scroller3);
	}
	
	//creates show employee area
	private void createEmployeeArea()
	{
		employeeShow = new JTextArea(1000, 15);
		scroller5 = new JScrollPane(employeeShow);
		scroller5.setLocation(30, 70);
		scroller5.setSize(600, 500);
		employeeShow.setEditable(false);
		scroller5.setVisible(false);
		add(scroller5);
	}

	//creates show customers area
	private void createCustomerArea()
	{
		lblCustomer = new JLabel("Customer");
		lblCustomer.setForeground(Color.white);
		lblCustomer.setLocation(200, 20);
		lblCustomer.setSize(220, 60);
		lblCustomer.setFont(customFont4);
		lblCustomer.setVisible(false);
		add(lblCustomer);
			
		customerShow = new JTextArea(1000, 15);
		scroller4 = new JScrollPane(customerShow);
		scroller4.setLocation(30, 70);
		scroller4.setSize(600, 500);
		customerShow.setEditable(false);
		scroller4.setVisible(false);
		add(scroller4);
	}
	
	//Creates the text area that shows the reminders
	private void createPlannerArea()
	{
		plannerHeader = new JLabel("Reminders");
	    plannerHeader.setLocation(1030, 90);
	    plannerHeader.setSize(110, 30);
	    plannerHeader.setFont(customFont3);
	    plannerHeader.setForeground(Color.white);
	    add(plannerHeader);
	
	    plannerShow = new JTextArea(1000, 4);
	    scroller1 = new JScrollPane(plannerShow);
		scroller1.setSize(500, 300);
		scroller1.setLocation(835, 140);
		plannerShow.setEditable(false);
		add(scroller1);
	}
	
	//Creates the edit reminder buttons, labels, text fields
	private void createSearchToEditReminder()
	{
		 lblEnterIDReminder = new JLabel("Enter ID of reminder you want to edit");
		 lblEnterIDReminder.setLocation(670, 490);
		 lblEnterIDReminder.setSize(210, 30);
		 lblEnterIDReminder.setForeground(Color.white);
		 lblEnterIDReminder.setVisible(false);
		 add(lblEnterIDReminder);
		    
		 btnSearchReminderEdit = new JButton("Find Reminder");
		 btnSearchReminderEdit.addActionListener(btnEventHandler);
		 btnSearchReminderEdit.setLocation(680, 520);
		 btnSearchReminderEdit.setSize(135, 30);
		 btnSearchReminderEdit.setVisible(false);
		 add(btnSearchReminderEdit);
		    
		 txtSearchReminderEdit = new JTextField();
		 txtSearchReminderEdit.setLocation(825, 520);
		 txtSearchReminderEdit.setSize(50, 30);
		 txtSearchReminderEdit.setVisible(false);
		 add(txtSearchReminderEdit);
	}
	
	//Creates the time label and refresh time button
	private void createTimeAndTimeRefresh()
	{
		 btnTimeRefresh = new JButton("Check Current Time");
		 btnTimeRefresh.setLocation(1050, 40);
		 btnTimeRefresh.setSize(160, 30);
	     add(btnTimeRefresh);
		 btnTimeRefresh.addActionListener(btnEventHandler);
		    
		 showDate = new JTextField();
		 showDate.setText(reportDate);
		 showDate.setEditable(false);
		 showDate.setLocation(1220, 40);
		 showDate.setSize(110, 30);
		 add(showDate);
	}
	
	//Creates planner menu and items then adds to menu bar
	private void setPlannerMenu() 
	{
		plannerMenu = new JMenu("Day Reminder");
		plannerMenu.setFont(customFont1);
		plannerMenu.setForeground(Color.blue);
		menuBar.add(plannerMenu);
		
		createMemo = new JMenuItem("New Reminder");
		createMemo.setFont(customFont2);
		createMemo.setForeground(Color.blue);
		plannerMenu.add(createMemo);
	    createMemo.addActionListener(menuEventHandler);
		
		showAllMemos = new JMenuItem("View all Reminders");
		showAllMemos.setFont(customFont2);
		showAllMemos.setForeground(Color.blue);
		plannerMenu.add(showAllMemos);
		showAllMemos.addActionListener(menuEventHandler);
		
		editMemo = new JMenuItem("Edit Reminders");
		editMemo.setFont(customFont2);
		editMemo.setForeground(Color.blue);
		plannerMenu.add(editMemo);
		editMemo.addActionListener(menuEventHandler);
		
		deleteMemo = new JMenuItem("Delete Reminder");
		deleteMemo.setFont(customFont2);
		deleteMemo.setForeground(Color.blue);
		plannerMenu.add(deleteMemo);
	    deleteMemo.addActionListener(menuEventHandler);
	}

	//Creates transaction menu and items then adds to menu bar
	private void setTransactionMenu() 
	{
		transactionMenu = new JMenu("Transactions");
		transactionMenu.setFont(customFont1);
		transactionMenu.setForeground(Color.blue);
		menuBar.add(transactionMenu);
		
		showAllTransactions = new JMenuItem("View all Transactions");
		showAllTransactions.setFont(customFont2);
		showAllTransactions.setForeground(Color.blue);
		transactionMenu.add(showAllTransactions);
	    showAllTransactions.addActionListener(menuEventHandler);
		
		createNewTransaction = new JMenuItem("New Transaction");
		createNewTransaction.setFont(customFont2);
		createNewTransaction.setForeground(Color.blue);
		transactionMenu.add(createNewTransaction);
		createNewTransaction.addActionListener(menuEventHandler);
		
		searchTransactions = new JMenuItem("Search Transactions");
		searchTransactions.setFont(customFont2);
		searchTransactions.setForeground(Color.blue);
		transactionMenu.add(searchTransactions);
		searchTransactions.addActionListener(menuEventHandler);
		
		editTransaction = new JMenuItem("Edit Transaction");
		editTransaction.setFont(customFont2);
		editTransaction.setForeground(Color.blue);
		transactionMenu.add(editTransaction);
		editTransaction.addActionListener(menuEventHandler);
		
		deleteTransaction = new JMenuItem("Delete Transaction");
		deleteTransaction.setFont(customFont2);
		deleteTransaction.setForeground(Color.blue);
		transactionMenu.add(deleteTransaction);
		deleteTransaction.addActionListener(menuEventHandler);
	}
	
	//Creates car menu and items then adds to menu bar
	private void setCarMenu() 
	{
		carMenu = new JMenu("Cars");
		carMenu.setFont(customFont1);
		carMenu.setForeground(Color.blue);
		menuBar.add(carMenu);
	    
	    carEdit = new JMenuItem("Edit Car");
	    carEdit.setFont(customFont2);
	    carEdit.setForeground(Color.blue);
	    carMenu.add(carEdit);
	    carEdit.addActionListener(menuEventHandler);
	    
	    carSearch = new JMenuItem("Search Cars");
	    carSearch.setFont(customFont2);
	    carSearch.setForeground(Color.blue);
	    carMenu.add(carSearch);
	    carSearch.addActionListener(menuEventHandler);
	    
	    carShowAll = new JMenuItem("View all Cars");
	    carShowAll.setFont(customFont2);
	    carShowAll.setForeground(Color.blue);
	    carMenu.add(carShowAll);
	    carShowAll.addActionListener(menuEventHandler);
	}
	
	//Creates employee menu and items then adds to menu bar
	private void setEmployeeMenu() 
	{
		employeeMenu = new JMenu("Employee");
		employeeMenu.setFont(customFont1);
		employeeMenu.setForeground(Color.blue);
	    menuBar.add(employeeMenu);
	    
	    empCreate = new JMenuItem("Create new Employee");
	    empCreate.setFont(customFont2);
	    empCreate.setForeground(Color.blue);
	    employeeMenu.add(empCreate);
	    empCreate.addActionListener(menuEventHandler);
	    
	    empEdit = new JMenuItem("Edit Employee");
	    empEdit.setFont(customFont2);
	    empEdit.setForeground(Color.blue);
	    employeeMenu.add(empEdit);
	    empEdit.addActionListener(menuEventHandler);
	    
	    empDelete = new JMenuItem("Delete Employee");
	    empDelete.setFont(customFont2);
	    empDelete.setForeground(Color.blue);
	    employeeMenu.add(empDelete);
	    empDelete.addActionListener(menuEventHandler);
	    
	    empSearch = new JMenuItem("Search Employees");
	    empSearch.setFont(customFont2);
	    empSearch.setForeground(Color.blue);
	    employeeMenu.add(empSearch);
	    empSearch.addActionListener(menuEventHandler);
	    
	    empShowAll = new JMenuItem("View all Employees");
	    empShowAll.setFont(customFont2);
	    empShowAll.setForeground(Color.blue);
	    employeeMenu.add(empShowAll);
	    empShowAll.addActionListener(menuEventHandler);
	}
	
	//Creates customer menu and items then adds to menu bar
	private void setCustomerMenu() 
	{
		customerMenu = new JMenu("Customer");
		customerMenu.setFont(customFont1);
		customerMenu.setForeground(Color.blue);
	    menuBar.add(customerMenu);
	    
	    custEdit = new JMenuItem("Edit Customer");
	    custEdit.setFont(customFont2);
	    custEdit.setForeground(Color.blue);
	    customerMenu.add(custEdit);
	    custEdit.addActionListener(menuEventHandler);
	    
	    custSearch = new JMenuItem("Search Customers");
	    custSearch.setFont(customFont2);
	    custSearch.setForeground(Color.blue);
	    customerMenu.add(custSearch);
	    custSearch.addActionListener(menuEventHandler);
	    
	    custShowAll = new JMenuItem("View all Customers");
	    custShowAll.setFont(customFont2);
	    custShowAll.setForeground(Color.blue);
	    customerMenu.add(custShowAll);
	    custShowAll.addActionListener(menuEventHandler);
	}

}//end MainMenuWindow Class
