package eventsAndGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

	//This event class handles all the menu items in the program
    class MenuHandler implements ActionListener
	{
    	@Override
		public void actionPerformed(ActionEvent e)
		{	
			JMenuItem mItem = (JMenuItem) e.getSource();
			String sql;
			String transNCB ="";
			String strCarPrice = "";
			String strCarVAT = "";
			String strCarTotal = "";
			String buyOrSell = "";
			String strEmpSalary = "";
			
			if(mItem == MainMenuWindow.createMemo)
			{
				MainMenuWindow.newReminderPanel.setVisible(true);
				MainMenuWindow.lblEnterIDReminder.setVisible(false);
				MainMenuWindow.btnSearchReminderEdit.setVisible(false);
				MainMenuWindow.txtSearchReminderEdit.setVisible(false);
				MainMenuWindow.btnReminderUpdate.setVisible(false);
				MainMenuWindow.btnNewRemindAdd.setEnabled(true);
				MainMenuWindow.btnNewRemindAdd.setVisible(true);
				MainMenuWindow.txtPlannerDate.setText(null);
				MainMenuWindow.txtPlannerTime.setText(null);
				MainMenuWindow.txtPlannerDescription.setText(null);
			}
			else if(mItem == MainMenuWindow.editMemo)
			{
				MainMenuWindow.lblEnterIDReminder.setVisible(true);
				MainMenuWindow.btnSearchReminderEdit.setVisible(true);
				MainMenuWindow.txtSearchReminderEdit.setVisible(true);
				MainMenuWindow.txtSearchReminderEdit.setEditable(true);
				MainMenuWindow.btnReminderUpdate.setVisible(true);
				MainMenuWindow.btnReminderUpdate.setEnabled(false);
				MainMenuWindow.newReminderPanel.setVisible(true);
				MainMenuWindow.btnNewRemindAdd.setEnabled(false);
				MainMenuWindow.txtPlannerDate.setText(null);
				MainMenuWindow.txtPlannerTime.setText(null);
				MainMenuWindow.txtPlannerDescription.setText(null);
				MainMenuWindow.txtSearchReminderEdit.setText("0");
				
			}
			else if(mItem == MainMenuWindow.showAllMemos)
			{
				MainMenuWindow.plannerShow.setText(null);
				
				sql = "SELECT ID, Date, Time, Description FROM planner";
				MainMenuWindow.results = MainMenuWindow.dataStorage.getResults(sql);
				
				try 
				{
					while(MainMenuWindow.results.next())
					{
						MainMenuWindow.myPlanner.setPlanID(MainMenuWindow.results.getInt("ID"));
						MainMenuWindow.myPlanner.setDate(MainMenuWindow.results.getString("Date"));
						MainMenuWindow.myPlanner.setTime(MainMenuWindow.results.getString("Time"));
						MainMenuWindow.myPlanner.setDescription(MainMenuWindow.results.getString("Description"));
						
						MainMenuWindow.plannerShow.append(String.format("%-3s %-5d %-5s %-12s %-5s %-10s %12s %-40s \n",
								 "ID:", MainMenuWindow.myPlanner.getPlanID(), "DATE:", MainMenuWindow.myPlanner.getDate(), 
								 "TIME:", MainMenuWindow.myPlanner.getTime(), "DESCRIPTION:", MainMenuWindow.myPlanner.getDescription()));
					}
					
					MainMenuWindow.results.close();
				} 
				catch (SQLException e1) 
				{
					JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
		
			}
			else if(mItem == MainMenuWindow.deleteMemo)
			{
				MainMenuWindow.lbldeleteReminder.setVisible(true);
				MainMenuWindow.btnDeleteReminder.setVisible(true);
				MainMenuWindow.txtDeleteReminder.setVisible(true);
			}
			else if(mItem == MainMenuWindow.createNewTransaction)
			{
				MainMenuWindow.transactionPanel.setVisible(true);
				MainMenuWindow.transactionHeader.setVisible(true);
				MainMenuWindow.btnCalcTotalPrice.setVisible(true);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setEnabled(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
			}
			else if(mItem == MainMenuWindow.showAllTransactions)
			{
				MainMenuWindow.scroller2.setVisible(true);
				MainMenuWindow.transactionHeader.setVisible(true);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setEnabled(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.transactionShow.setText(null);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
				
				sql = "SELECT TransCustID, CustomerName, CustomerAddress, CustomerPhone, CustomerDOB, InsuranceCompany, "
						+ "NCB, CarMake, CarModel, CarReg, CarColor, CarEngine, CarPrice, CarVAT, CarTotal, "
						+ "BuyOrSell FROM transactions";
				
				MainMenuWindow.results = MainMenuWindow.dataStorage.getResults(sql);
				
				try 
				{
					while(MainMenuWindow.results.next())
					{
						MainMenuWindow.myTransaction.setTransID(MainMenuWindow.results.getInt("TransCustID"));
						MainMenuWindow.myTransaction.setName(MainMenuWindow.results.getString("CustomerName"));
						MainMenuWindow.myTransaction.setAddress(MainMenuWindow.results.getString("CustomerAddress"));
						MainMenuWindow.myTransaction.setPhoneNumber(MainMenuWindow.results.getString("CustomerPhone"));
						MainMenuWindow.myTransaction.setDOB(MainMenuWindow.results.getString("CustomerDOB"));
						MainMenuWindow.myTransaction.setInsuranceCompany(MainMenuWindow.results.getString("InsuranceCompany"));
						transNCB = MainMenuWindow.results.getString("NCB");
						MainMenuWindow.myCar.setCarMake(MainMenuWindow.results.getString("CarMake"));
						MainMenuWindow.myCar.setCarModel(MainMenuWindow.results.getString("CarModel"));
						MainMenuWindow.myCar.setCarRegistration(MainMenuWindow.results.getString("CarReg"));
						MainMenuWindow.myCar.setCarColour(MainMenuWindow.results.getString("CarColor"));
						MainMenuWindow.myCar.setCarEngine(MainMenuWindow.results.getString("CarEngine"));
						strCarPrice = MainMenuWindow.results.getString("CarPrice");
						strCarVAT = MainMenuWindow.results.getString("CarVAT");
						strCarTotal = MainMenuWindow.results.getString("CarTotal");
						buyOrSell = MainMenuWindow.results.getString("BuyOrSell");
						
						MainMenuWindow.transactionShow.append(String.format("\n%-3s %-5d %-5s %-17s %-7s %-17s %-6s %-14s %-4s %-13s"
								+ "%-11s %-13s %-4s %-2s \n%-5s %-15s %-6s %-15s %-4s %-11s %-7s %-15s %-7s %-12s \n%-6s %-10s"
								+ "%-4s %-3s %-6s %-10s %-9s %-4s\n", "ID:", MainMenuWindow.myTransaction.getTransID(), 
								"NAME:", MainMenuWindow.myTransaction.getName(), "ADDRESS:", MainMenuWindow.myTransaction.getAddress(),
								"PHONE:", MainMenuWindow.myTransaction.getPhoneNumber(), "DOB:", MainMenuWindow.myTransaction.getDOB(), 
								"INSURANCE:", MainMenuWindow.myTransaction.getInsuranceCompany(), "NCB:", transNCB, "MAKE:", MainMenuWindow.myCar.getCarMake(), 
								"MODEL:", MainMenuWindow.myCar.getCarModel(), "REG:", MainMenuWindow.myCar.getCarRegistration(), 
								"COLOUR:", MainMenuWindow.myCar.getCarColour(), "ENGINE:", MainMenuWindow.myCar.getCarEngine(), 
								"Price:", strCarPrice, "VAT:", strCarVAT, "TOTAL:", strCarTotal, "BUYORSELL:", buyOrSell));
						}
					
					MainMenuWindow.results.close();
				} 
				catch (SQLException e1) 
				{
					JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mItem == MainMenuWindow.editTransaction)
			{
				MainMenuWindow.transactionHeader.setVisible(true);
				MainMenuWindow.transactionPanel.setVisible(true);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(true);
				MainMenuWindow.lblEnterIDTransaction.setVisible(true);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(true);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(true);
				MainMenuWindow.btnTransactionUpdate.setEnabled(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(true);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setEditable(true);
			}
			else if(mItem == MainMenuWindow.deleteTransaction)
			{
				MainMenuWindow.transactionHeader.setVisible(true);
				MainMenuWindow.transactionPanel.setVisible(true);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(true);
				MainMenuWindow.lblEnterIDTransaction.setVisible(true);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(true);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(true);
				MainMenuWindow.btnTransactionUpdate.setEnabled(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(true);
				MainMenuWindow.lbldeleteTransaction.setVisible(true);
				MainMenuWindow.txtDeleteTransaction.setVisible(true);
				MainMenuWindow.btnDeleteTransaction.setVisible(true);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
			}
			else if(mItem == MainMenuWindow.searchTransactions)
			{
				MainMenuWindow.transactionHeader.setVisible(true);
				MainMenuWindow.transactionPanel.setVisible(true);
				MainMenuWindow.scroller2.setVisible(true);
				MainMenuWindow.searchOptions.setVisible(true);
				MainMenuWindow.lblSearchTransactions.setVisible(true);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setEnabled(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.txtSearchTransName.setText(null);
				MainMenuWindow.txtSearchTransID.setText(null);
				MainMenuWindow.txtSearchTransCarMake.setText(null);
				MainMenuWindow.searchOptions.setSelectedIndex(0);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
			}
			else if(mItem == MainMenuWindow.carShowAll)
			{
				MainMenuWindow.lblCars.setVisible(true);
				MainMenuWindow.scroller3.setVisible(true);
				MainMenuWindow.transactionHeader.setVisible(false);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
				MainMenuWindow.carShow.setText(null);
				
				sql = "SELECT TransCustID, CarMake, CarModel, CarReg, CarColor, CarEngine, BuyOrSell FROM transactions";
				MainMenuWindow.results = MainMenuWindow.dataStorage.getResults(sql);
				
				try 
				{
					while(MainMenuWindow.results.next())
					{
						MainMenuWindow.myTransaction.setTransID(MainMenuWindow.results.getInt("TransCustID"));
						MainMenuWindow.myCar.setCarMake(MainMenuWindow.results.getString("CarMake"));
						MainMenuWindow.myCar.setCarModel(MainMenuWindow.results.getString("CarModel"));
						MainMenuWindow.myCar.setCarRegistration(MainMenuWindow.results.getString("CarReg"));
						MainMenuWindow.myCar.setCarColour(MainMenuWindow.results.getString("CarColor"));
						MainMenuWindow.myCar.setCarEngine(MainMenuWindow.results.getString("CarEngine"));
						buyOrSell = (MainMenuWindow.results.getString("BuyOrSell"));
						
						MainMenuWindow.carShow.append(String.format("%-3s %-4d %-7s %-15s %-8s %-15s %-6s %-10s %-8s %-14s %-9s %-15s %-8s %-5s\n",
								 "ID:", MainMenuWindow.myTransaction.getTransID(), "MAKE:", MainMenuWindow.myCar.getCarMake(), 
								 "MODEL:", MainMenuWindow.myCar.getCarModel(), "REGISTRATION:", MainMenuWindow.myCar.getCarRegistration(),
								 "COLOUR:", MainMenuWindow.myCar.getCarColour(), "ENGINE:", MainMenuWindow.myCar.getCarEngine(),
								 "BUYORSELL:", buyOrSell));
					}
					
					MainMenuWindow.results.close();
				} 
				catch (SQLException e1) 
				{
					JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mItem == MainMenuWindow.carSearch)
			{
				MainMenuWindow.scroller3.setVisible(true);
				MainMenuWindow.lblCars.setVisible(true);
				MainMenuWindow.listCarSearch.setVisible(true);
				MainMenuWindow.listCarSearch.setSelectedIndex(0);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.transactionHeader.setVisible(false);
				MainMenuWindow.carShow.setText(null);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(true);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
			}
			else if(mItem == MainMenuWindow.carEdit)
			{
				MainMenuWindow.lblCars.setVisible(true);
				MainMenuWindow.carPanel.setVisible(true);
				MainMenuWindow.lblEnterIDCar.setVisible(true);
				MainMenuWindow.btnSearchCarEdit.setVisible(true);
				MainMenuWindow.txtSearchCarEdit.setVisible(true);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.transactionHeader.setVisible(false);
				MainMenuWindow.btnUpdateCar.setEnabled(false);
				MainMenuWindow.txtSearchCarEdit.setEditable(true);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
			}
			if(mItem == MainMenuWindow.custShowAll)
			{
				MainMenuWindow.lblCustomer.setVisible(true);
				MainMenuWindow.scroller4.setVisible(true);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.transactionHeader.setVisible(false);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.customerShow.setText(null);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				
				sql = "SELECT TransCustID, CustomerName, CustomerAddress, CustomerPhone, CustomerDOB FROM transactions";
				MainMenuWindow.results = MainMenuWindow.dataStorage.getResults(sql);
				
				try 
				{
					while(MainMenuWindow.results.next())
					{
						MainMenuWindow.myCustomer.setCustomerID(MainMenuWindow.results.getInt("TransCustID"));
						MainMenuWindow.myCustomer.setName(MainMenuWindow.results.getString("CustomerName"));
						MainMenuWindow.myCustomer.setAddress(MainMenuWindow.results.getString("CustomerAddress"));
						MainMenuWindow.myCustomer.setPhoneNumber(MainMenuWindow.results.getString("CustomerPhone"));
						MainMenuWindow.myCustomer.setDOB(MainMenuWindow.results.getString("CustomerDOB"));
						
						MainMenuWindow.customerShow.append(String.format("%-3s %-4d %-5s %-15s %-8s %-20s %-6s %-12s %-4s %-17s\n",
								 "ID:", MainMenuWindow.myCustomer.getCustomerID(), "NAME:", MainMenuWindow.myCustomer.getName(), 
								 "ADDRESS:", MainMenuWindow.myCustomer.getAddress(), "PHONE:", MainMenuWindow.myCustomer.getPhoneNumber(),
								 "DOB:", MainMenuWindow.myCustomer.getDOB()));
					}
					
					MainMenuWindow.results.close();
				} 
				catch (SQLException e1) 
				{
					JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(mItem == MainMenuWindow.custSearch)
			{
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.transactionHeader.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(true);
				MainMenuWindow.scroller4.setVisible(true);
				MainMenuWindow.listCustomerSearch.setVisible(true);
				MainMenuWindow.lblSearchCustomers.setVisible(true);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.listCustomerSearch.setSelectedIndex(0);
				MainMenuWindow.customerShow.setText(null);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
			}
			else if(mItem == MainMenuWindow.custEdit)
			{
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(true);
				MainMenuWindow.lblEnterIDCustomer.setVisible(true);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(true);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(true);
				MainMenuWindow.customerShow.setText(null);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.transactionHeader.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
			}
			else if(mItem == MainMenuWindow.empCreate)
			{
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.btnEmployeeAdd.setEnabled(true);
				MainMenuWindow.employeePanel.setVisible(true);
				MainMenuWindow.employeeHeader.setVisible(true);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.transactionHeader.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
			}
			else if(mItem == MainMenuWindow.empEdit)
			{
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(true);
				MainMenuWindow.employeeHeader.setVisible(true);
				MainMenuWindow.lblEnterIDEmployee.setVisible(true);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(true);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(true);
				MainMenuWindow.btnEmployeeUpdate.setVisible(true);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.transactionHeader.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.btnEmployeeAdd.setEnabled(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
			}
			else if(mItem == MainMenuWindow.empDelete)
			{
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				MainMenuWindow.lbldeleteEmployee.setVisible(true);
				MainMenuWindow.btnDeleteEmployee.setVisible(true);
				MainMenuWindow.txtDeleteEmployee.setVisible(true);
				MainMenuWindow.scroller5.setVisible(false);
				MainMenuWindow.employeeHeader.setVisible(true);
				MainMenuWindow.employeePanel.setVisible(true);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.transactionHeader.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
			}
			else if(mItem == MainMenuWindow.empShowAll)
			{
				MainMenuWindow.scroller5.setVisible(true);
				MainMenuWindow.employeeHeader.setVisible(true);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.transactionHeader.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setVisible(false);
				MainMenuWindow.employeeShow.setText(null);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
				
				sql = "SELECT EmpID, EmployeeName, EmployeeAddress, EmployeePhone, EmployeeDOB, EmployeeJob, EmployeeSalary FROM employees";
				MainMenuWindow.results = MainMenuWindow.dataStorage.getResults(sql);
				
				try 
				{
					while(MainMenuWindow.results.next())
					{
						MainMenuWindow.myEmployee.setEmployeeID(MainMenuWindow.results.getInt("EmpID"));
						MainMenuWindow.myEmployee.setName(MainMenuWindow.results.getString("EmployeeName"));
						MainMenuWindow.myEmployee.setAddress(MainMenuWindow.results.getString("EmployeeAddress"));
						MainMenuWindow.myEmployee.setPhoneNumber(MainMenuWindow.results.getString("EmployeePhone"));
						MainMenuWindow.myEmployee.setDOB(MainMenuWindow.results.getString("EmployeeDOB"));
						MainMenuWindow.myEmployee.setEmployeeJob(MainMenuWindow.results.getString("EmployeeJob"));
						strEmpSalary = (MainMenuWindow.results.getString("EmployeeSalary"));
						
						MainMenuWindow.employeeShow.append(String.format("%-3s %-4d %-5s %-15s %-8s %-16s %-6s %-12s %-4s %-17s %-4s %-15s %-7s %-10s\n",
								 "ID:", MainMenuWindow.myEmployee.getEmployeeID(), "NAME:", MainMenuWindow.myEmployee.getName(), 
								 "   ADDRESS:", MainMenuWindow.myEmployee.getAddress(), "PHONE:", MainMenuWindow.myEmployee.getPhoneNumber(),
								 "   DOB:", MainMenuWindow.myEmployee.getDOB(), "JOB:", MainMenuWindow.myEmployee.getEmployeeJob(),
								 "YEAR SALARY:", strEmpSalary));
					}
					
					MainMenuWindow.results.close();
				} 
				catch (SQLException e1) 
				{
					JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mItem == MainMenuWindow.empSearch)
			{
				MainMenuWindow.employeeShow.setText(null);
				MainMenuWindow.scroller5.setVisible(true);
				MainMenuWindow.employeeHeader.setVisible(true);
				MainMenuWindow.listEmployeeSearch.setVisible(true);
				MainMenuWindow.lbldeleteEmployee.setVisible(false);
				MainMenuWindow.btnDeleteEmployee.setVisible(false);
				MainMenuWindow.txtDeleteEmployee.setVisible(false);
				MainMenuWindow.employeePanel.setVisible(false);
				MainMenuWindow.lblEnterIDEmployee.setVisible(false);
				MainMenuWindow.btnSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.txtSearchEmployeeEdit.setVisible(false);
				MainMenuWindow.btnEmployeeUpdate.setVisible(false);
				MainMenuWindow.customerPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCustomer.setVisible(false);
				MainMenuWindow.btnSearchCustomerEdit.setVisible(false);
				MainMenuWindow.txtSearchCustomerEdit.setVisible(false);
				MainMenuWindow.scroller3.setVisible(false);
				MainMenuWindow.lblCars.setVisible(false);
				MainMenuWindow.listCarSearch.setVisible(false);
				MainMenuWindow.transactionPanel.setVisible(false);
				MainMenuWindow.btnCalcTotalPrice.setVisible(false);
				MainMenuWindow.searchOptions.setVisible(false);
				MainMenuWindow.scroller2.setVisible(false);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
				MainMenuWindow.lblSearchTransactions.setVisible(false);
				MainMenuWindow.lblEnterIDTransaction.setVisible(false);
				MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
				MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
				MainMenuWindow.btnTransactionUpdate.setVisible(false);
				MainMenuWindow.lbldeleteTransaction.setVisible(false);
				MainMenuWindow.txtDeleteTransaction.setVisible(false);
				MainMenuWindow.btnDeleteTransaction.setVisible(false);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
				MainMenuWindow.transactionHeader.setVisible(false);
				MainMenuWindow.carPanel.setVisible(false);
				MainMenuWindow.lblEnterIDCar.setVisible(false);
				MainMenuWindow.btnSearchCarEdit.setVisible(false);
				MainMenuWindow.txtSearchCarEdit.setVisible(false);
				MainMenuWindow.lblCustomer.setVisible(false);
				MainMenuWindow.scroller4.setVisible(false);
				MainMenuWindow.listCustomerSearch.setVisible(false);
				MainMenuWindow.lblSearchCustomers.setVisible(false);
				MainMenuWindow.lblSearchCars.setVisible(false);
				MainMenuWindow.listEmployeeSearch.setSelectedIndex(0);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
			}
			
		}//end action performed
	
	}//end menu handler class
