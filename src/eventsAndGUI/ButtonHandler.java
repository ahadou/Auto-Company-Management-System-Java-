package eventsAndGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

	//This event class handles all of the buttons in the program
    class ButtonHandler implements ActionListener
	{
    	@Override
		public void actionPerformed(ActionEvent e)
		{	
			String sql;
			String passTotToString = "";
			String transNCB ="";
			String strCarPrice = "";
			String strCarVAT = "";
			String strCarTotal = "";
			String buyOrSell = "";
			double carPrice, carVAT, carTotal; 
			String strEmpSalary = "";
			
			switch(e.getActionCommand())
			{
				case "Check Current Time":
					MainMenuWindow.reportDate = MainMenuWindow.formatDate.format(new Date());
					MainMenuWindow.showDate.setText(MainMenuWindow.reportDate);
					break;
				
				case "Add Reminder":
					MainMenuWindow.myPlanner.setDate(MainMenuWindow.txtPlannerDate.getText());
					MainMenuWindow.myPlanner.setTime(MainMenuWindow.txtPlannerTime.getText());
					MainMenuWindow.myPlanner.setDescription(MainMenuWindow.txtPlannerDescription.getText());
					
					if(MainMenuWindow.myPlanner.getDate().isEmpty() || MainMenuWindow.myPlanner.getTime().isEmpty() 
							|| MainMenuWindow.myPlanner.getDescription().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "You have not entered some information",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						sql = "INSERT INTO planner (Date, Time, Description) VALUES "
							+ "('"+MainMenuWindow.myPlanner.getDate()+"', '"+MainMenuWindow.myPlanner.getTime()+"', '"+MainMenuWindow.myPlanner.getDescription()+"')"; 
					
						MainMenuWindow.dataStorage.executeStatement(sql);
					}
					
					MainMenuWindow.txtPlannerDate.setText(null);
					MainMenuWindow.txtPlannerTime.setText(null);
					MainMenuWindow.txtPlannerDescription.setText(null);
					break;
				
				case "Exit Reminders":
					MainMenuWindow.newReminderPanel.setVisible(false);
					MainMenuWindow.lblEnterIDReminder.setVisible(false);
					MainMenuWindow.btnSearchReminderEdit.setVisible(false);
					MainMenuWindow.txtSearchReminderEdit.setVisible(false);
					MainMenuWindow.btnReminderUpdate.setVisible(false);
					MainMenuWindow.txtPlannerDate.setText(null);
					MainMenuWindow.txtPlannerTime.setText(null);
					MainMenuWindow.txtPlannerDescription.setText(null);
					MainMenuWindow.lbldeleteReminder.setVisible(false);
					MainMenuWindow.btnDeleteReminder.setVisible(false);
					MainMenuWindow.txtDeleteReminder.setVisible(false);
					MainMenuWindow.txtDeleteReminder.setText("0");
					MainMenuWindow.txtSearchReminderEdit.setText("0");
					break;
				
				case "Find Reminder":
					try 
					{
						MainMenuWindow.myPlanner.setPlanID(Integer.parseInt(MainMenuWindow.txtSearchReminderEdit.getText()));
					} 
					catch (NumberFormatException e1) 
					{
						JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT Date, Time, Description FROM planner WHERE ID = " +MainMenuWindow.myPlanner.getPlanID();
					
					MainMenuWindow.results = MainMenuWindow.dataStorage.getResults(sql);
					
					try 
					{
						while(MainMenuWindow.results.next())
						{
							MainMenuWindow.myPlanner.setDate(MainMenuWindow.results.getString("Date"));
							MainMenuWindow.myPlanner.setTime(MainMenuWindow.results.getString("Time"));
							MainMenuWindow.myPlanner.setDescription(MainMenuWindow.results.getString("Description"));
						}
						
						MainMenuWindow.txtPlannerDate.setText(MainMenuWindow.myPlanner.getDate());
						MainMenuWindow.txtPlannerTime.setText(MainMenuWindow.myPlanner.getTime());
						MainMenuWindow.txtPlannerDescription.setText(MainMenuWindow.myPlanner.getDescription());
						
						MainMenuWindow.results.close();
					} 
					catch (SQLException es)
					{
						JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					MainMenuWindow.txtSearchReminderEdit.setEditable(false);
					MainMenuWindow.btnReminderUpdate.setEnabled(true);
					break;
				
				case "Update Reminder":
					MainMenuWindow.myPlanner.setPlanID(Integer.parseInt(MainMenuWindow.txtSearchReminderEdit.getText()));
					MainMenuWindow.myPlanner.setDate(MainMenuWindow.txtPlannerDate.getText());
					MainMenuWindow.myPlanner.setTime(MainMenuWindow.txtPlannerTime.getText());
					MainMenuWindow.myPlanner.setDescription(MainMenuWindow.txtPlannerDescription.getText());
					
					if(MainMenuWindow.myPlanner.getDate().isEmpty() || MainMenuWindow.myPlanner.getTime().isEmpty() 
							|| MainMenuWindow.myPlanner.getDescription().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "You have not entered some information",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						sql = "UPDATE planner SET Date = '" +MainMenuWindow.myPlanner.getDate()+ "', Time = '" +MainMenuWindow.myPlanner.getTime()+ "', "
								+ "Description = '" +MainMenuWindow.myPlanner.getDescription()+ "' WHERE ID = " +MainMenuWindow.myPlanner.getPlanID();
					
						MainMenuWindow.dataStorage.executeStatement(sql);
						
						JOptionPane.showMessageDialog(null, "Successful Update",
								"Update", JOptionPane.INFORMATION_MESSAGE);
					}
					
					MainMenuWindow.txtPlannerDate.setText(null);
					MainMenuWindow.txtPlannerTime.setText(null);
					MainMenuWindow.txtPlannerDescription.setText(null);
					break;
				
				case "Delete Reminder":
					try
					{
						MainMenuWindow.myPlanner.setPlanID(Integer.parseInt(MainMenuWindow.txtDeleteReminder.getText()));
						sql = "DELETE FROM planner WHERE id = '" +MainMenuWindow.myPlanner.getPlanID()+ "'";
					
						MainMenuWindow.dataStorage.executeStatement(sql);
						MainMenuWindow.txtDeleteReminder.setText(null);
					}
					catch(NumberFormatException se)
					{
						JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					break;
				
				case "Exit Transactions":
					MainMenuWindow.transactionPanel.setVisible(false);
					MainMenuWindow.transactionHeader.setVisible(false);
					MainMenuWindow.btnCalcTotalPrice.setVisible(false);
					MainMenuWindow.lblEnterIDTransaction.setVisible(false);
					MainMenuWindow.btnSearchTransactionEdit.setVisible(false);
					MainMenuWindow.txtSearchTransactionEdit.setVisible(false);
					MainMenuWindow.btnTransactionUpdate.setEnabled(false);
					MainMenuWindow.btnTransactionUpdate.setVisible(false);
					MainMenuWindow.lbldeleteTransaction.setVisible(false);
					MainMenuWindow.txtDeleteTransaction.setVisible(false);
					MainMenuWindow.btnDeleteTransaction.setVisible(false);
					MainMenuWindow.txtTransCustName.setText(null);
					MainMenuWindow.txtTransCustAddress.setText(null);
					MainMenuWindow.txtTransCustPhone.setText(null);
					MainMenuWindow.txtTransCustDOB.setText(null);
					MainMenuWindow.txtTransCustName.setText(null);
					MainMenuWindow.txtTransCustInsureC.setText(null);
					MainMenuWindow.txtTransCustNCB.setText(null);
					MainMenuWindow.txtTransCustMake.setText(null);
					MainMenuWindow.txtTransCustModel.setText(null);
					MainMenuWindow.txtTransCustReg.setText(null);
					MainMenuWindow.txtTransCustColor.setText(null);
					MainMenuWindow.txtTransCustEngine.setText(null);
					MainMenuWindow.txtTransCustPrice.setText(null);
					MainMenuWindow.txtTransCustVAT.setText(null);
					MainMenuWindow.txtTransCustTotal.setText(null);
					MainMenuWindow.txtBuyOrSell.setText(null);
					break;	
				
				case "Add Transaction":
					MainMenuWindow.myTransaction.setName(MainMenuWindow.txtTransCustName.getText());
					MainMenuWindow.myTransaction.setAddress(MainMenuWindow.txtTransCustAddress.getText());
					MainMenuWindow.myTransaction.setPhoneNumber(MainMenuWindow.txtTransCustPhone.getText());
					MainMenuWindow.myTransaction.setDOB(MainMenuWindow.txtTransCustDOB.getText());
					MainMenuWindow.myTransaction.setInsuranceCompany(MainMenuWindow.txtTransCustInsureC.getText());
					transNCB = MainMenuWindow.txtTransCustNCB.getText();
					MainMenuWindow.myCar.setCarMake(MainMenuWindow.txtTransCustMake.getText());
					MainMenuWindow.myCar.setCarModel(MainMenuWindow.txtTransCustModel.getText());
					MainMenuWindow.myCar.setCarRegistration(MainMenuWindow.txtTransCustReg.getText());
					MainMenuWindow.myCar.setCarColour(MainMenuWindow.txtTransCustColor.getText());
					MainMenuWindow.myCar.setCarEngine(MainMenuWindow.txtTransCustEngine.getText());
					buyOrSell  = MainMenuWindow.txtBuyOrSell.getText();
					carPrice = Double.parseDouble(MainMenuWindow.txtTransCustPrice.getText());
					carVAT = Double.parseDouble(MainMenuWindow.txtTransCustVAT.getText());
					carTotal = Double.parseDouble(MainMenuWindow.txtTransCustTotal.getText());
					
					strCarPrice = ""+carPrice;
					strCarVAT = ""+carVAT;
					strCarTotal = ""+carTotal;
					
					if(MainMenuWindow.myTransaction.getName().isEmpty() || MainMenuWindow.myTransaction.getAddress().isEmpty() 
							|| MainMenuWindow.myTransaction.getPhoneNumber().isEmpty() || MainMenuWindow.myTransaction.getDOB().isEmpty() 
							|| MainMenuWindow.myTransaction.getInsuranceCompany().isEmpty() || transNCB.isEmpty() 
							|| MainMenuWindow.myCar.getCarMake().isEmpty() || MainMenuWindow.myCar.getCarModel().isEmpty()
							|| MainMenuWindow.myCar.getCarRegistration().isEmpty() || MainMenuWindow.myCar.getCarColour().isEmpty() 
							|| MainMenuWindow.myCar.getCarEngine().isEmpty() || strCarPrice.isEmpty()
							|| strCarVAT.isEmpty() || strCarTotal.isEmpty() || buyOrSell.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "You have not entered some information",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(buyOrSell.equalsIgnoreCase("Buy") || buyOrSell.equalsIgnoreCase("Sell"))
						{
							sql = "INSERT INTO transactions (CustomerName, CustomerAddress, CustomerPhone, CustomerDOB, "
									+ "InsuranceCompany, NCB, CarMake, CarModel, CarReg, CarColor, CarEngine, CarPrice,"
									+ "CarVAT, CarTotal, BuyOrSell) VALUES ('"+MainMenuWindow.myTransaction.getName()+"', '"+MainMenuWindow.myTransaction.getAddress()+"', '"+MainMenuWindow.myTransaction.getPhoneNumber()+"', '"+MainMenuWindow.myTransaction.getDOB()+"', '"
									+MainMenuWindow.myTransaction.getInsuranceCompany()+"', '"+transNCB+"', '"+MainMenuWindow.myCar.getCarMake()+"', '"+MainMenuWindow.myCar.getCarModel()+"', '"+MainMenuWindow.myCar.getCarRegistration()+"', '"
									+MainMenuWindow.myCar.getCarColour()+"', '"+ MainMenuWindow.myCar.getCarEngine()+"', '"+strCarPrice+"', '"+strCarVAT+"', '"+strCarTotal+"', '"+buyOrSell+"')";
						
							MainMenuWindow.dataStorage.executeStatement(sql);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please enter either 'Buy' or 'Sell'",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
						
						MainMenuWindow.txtTransCustName.setText(null);
						MainMenuWindow.txtTransCustAddress.setText(null);
						MainMenuWindow.txtTransCustPhone.setText(null);
						MainMenuWindow.txtTransCustDOB.setText(null);
						MainMenuWindow.txtTransCustName.setText(null);
						MainMenuWindow.txtTransCustInsureC.setText(null);
						MainMenuWindow.txtTransCustNCB.setText(null);
						MainMenuWindow.txtTransCustMake.setText(null);
						MainMenuWindow.txtTransCustModel.setText(null);
						MainMenuWindow.txtTransCustReg.setText(null);
						MainMenuWindow.txtTransCustColor.setText(null);
						MainMenuWindow.txtTransCustEngine.setText(null);
						MainMenuWindow.txtTransCustPrice.setText(null);
						MainMenuWindow.txtTransCustVAT.setText(null);
						MainMenuWindow.txtTransCustTotal.setText(null);
						MainMenuWindow.txtBuyOrSell.setText(null);
						
					}
				break;
				
				case "Calculate Total":
					
					MainMenuWindow.myTransaction.setPrice(Double.parseDouble(MainMenuWindow.txtTransCustPrice.getText()));
					MainMenuWindow.myTransaction.setVAT(Double.parseDouble(MainMenuWindow.txtTransCustVAT.getText()));
					
					carTotal = MainMenuWindow.myTransaction.calcTotalCost(MainMenuWindow.myTransaction.getPrice(), 
							MainMenuWindow.myTransaction.getVAT());
					
					passTotToString = ""+carTotal;
					MainMenuWindow.txtTransCustTotal.setText(passTotToString);
				break;
				
				case "Find Transactions":
					try 
					{
						MainMenuWindow.myTransaction.setTransID(Integer.parseInt(MainMenuWindow.txtSearchTransactionEdit.getText()));
					} 
					catch (NumberFormatException e1) 
					{
						JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT CustomerName, CustomerAddress, CustomerPhone, CustomerDOB, InsuranceCompany, NCB, CarMake, "
							+ "CarModel, CarReg, CarColor, CarEngine, CarPrice, CarVAT, CarTotal, BuyOrSell"
							+ " FROM transactions WHERE TransCustID = " +MainMenuWindow.myTransaction.getTransID();
					
					MainMenuWindow.results = MainMenuWindow.dataStorage.getResults(sql);
					
					try 
					{
						while(MainMenuWindow.results.next())
						{
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
							buyOrSell = MainMenuWindow.results.getString("BuyOrSell");
							strCarPrice = MainMenuWindow.results.getString("CarPrice");
							strCarVAT = MainMenuWindow.results.getString("CarVAT");
							strCarTotal = MainMenuWindow.results.getString("CarTotal");
							
						}
						
						MainMenuWindow.txtTransCustName.setText(MainMenuWindow.myTransaction.getName());
						MainMenuWindow.txtTransCustAddress.setText(MainMenuWindow.myTransaction.getAddress());
						MainMenuWindow.txtTransCustPhone.setText(MainMenuWindow.myTransaction.getPhoneNumber());
						MainMenuWindow.txtTransCustDOB.setText(MainMenuWindow.myTransaction.getDOB());
						MainMenuWindow.txtTransCustInsureC.setText(MainMenuWindow.myTransaction.getInsuranceCompany());
						MainMenuWindow.txtTransCustNCB.setText(transNCB);
						MainMenuWindow.txtTransCustMake.setText(MainMenuWindow.myCar.getCarMake());
						MainMenuWindow.txtTransCustModel.setText(MainMenuWindow.myCar.getCarModel());
						MainMenuWindow.txtTransCustReg.setText(MainMenuWindow.myCar.getCarRegistration());
						MainMenuWindow.txtTransCustColor.setText(MainMenuWindow.myCar.getCarColour());
						MainMenuWindow.txtTransCustEngine.setText(MainMenuWindow.myCar.getCarEngine());
						MainMenuWindow.txtBuyOrSell.setText(buyOrSell);
						MainMenuWindow.txtTransCustPrice.setText(strCarPrice);
						MainMenuWindow.txtTransCustVAT.setText(strCarVAT);
						MainMenuWindow.txtTransCustTotal.setText(strCarTotal);
						
						
						MainMenuWindow.results.close();
					} 
					catch (SQLException es)
					{
						JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					MainMenuWindow.txtSearchTransactionEdit.setEditable(false);
					MainMenuWindow.btnTransactionUpdate.setEnabled(true);
					break;
					
				case "Update Transaction":
					MainMenuWindow.myTransaction.setTransID(Integer.parseInt(MainMenuWindow.txtSearchTransactionEdit.getText()));
					MainMenuWindow.myTransaction.setName(MainMenuWindow.txtTransCustName.getText());
					MainMenuWindow.myTransaction.setAddress(MainMenuWindow.txtTransCustAddress.getText());
					MainMenuWindow.myTransaction.setPhoneNumber(MainMenuWindow.txtTransCustPhone.getText());
					MainMenuWindow.myTransaction.setDOB(MainMenuWindow.txtTransCustDOB.getText());
					MainMenuWindow.myTransaction.setInsuranceCompany(MainMenuWindow.txtTransCustInsureC.getText());
					transNCB = MainMenuWindow.txtTransCustNCB.getText();
					MainMenuWindow.myCar.setCarMake(MainMenuWindow.txtTransCustMake.getText());
					MainMenuWindow.myCar.setCarModel(MainMenuWindow.txtTransCustModel.getText());
					MainMenuWindow.myCar.setCarRegistration(MainMenuWindow.txtTransCustReg.getText());
					MainMenuWindow.myCar.setCarColour(MainMenuWindow.txtTransCustColor.getText());
					MainMenuWindow.myCar.setCarEngine(MainMenuWindow.txtTransCustEngine.getText());
					buyOrSell  = MainMenuWindow.txtBuyOrSell.getText();
					carPrice = Double.parseDouble(MainMenuWindow.txtTransCustPrice.getText());
					carVAT = Double.parseDouble(MainMenuWindow.txtTransCustVAT.getText());
					carTotal = Double.parseDouble(MainMenuWindow.txtTransCustTotal.getText());
					
					strCarPrice = ""+carPrice;
					strCarVAT = ""+carVAT;
					strCarTotal = ""+carTotal;
					
					if(MainMenuWindow.myTransaction.getName().isEmpty() || MainMenuWindow.myTransaction.getAddress().isEmpty()
							|| MainMenuWindow.myTransaction.getPhoneNumber().isEmpty() || MainMenuWindow.myTransaction.getDOB().isEmpty()
							|| MainMenuWindow.myTransaction.getInsuranceCompany().isEmpty() || transNCB.isEmpty() || MainMenuWindow.myCar.getCarMake().isEmpty()
							|| MainMenuWindow.myCar.getCarModel().isEmpty() || MainMenuWindow.myCar.getCarRegistration().isEmpty() || MainMenuWindow.myCar.getCarColour().isEmpty()
							|| MainMenuWindow.myCar.getCarEngine().isEmpty() || buyOrSell.isEmpty() || strCarPrice.isEmpty()
							|| strCarVAT.isEmpty() || strCarTotal.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "You have not entered some information",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(buyOrSell.equalsIgnoreCase("Buy") || buyOrSell.equalsIgnoreCase("Sell"))
						{
							sql = "UPDATE transactions SET CustomerName = '" +MainMenuWindow.myTransaction.getName()+ "', CustomerAddress = '" +MainMenuWindow.myTransaction.getAddress()+ "', "
									+ "CustomerPhone = '" +MainMenuWindow.myTransaction.getPhoneNumber()+ "', CustomerDOB = '" +MainMenuWindow.myTransaction.getDOB()+ "', "
											+ "InsuranceCompany = '" +MainMenuWindow.myTransaction.getInsuranceCompany()+ "', NCB = '" +transNCB+ "', CarMake = '" +MainMenuWindow.myCar.getCarMake()+ "', "
											+ "CarModel = '" +MainMenuWindow.myCar.getCarModel()+ "', CarReg = '" +MainMenuWindow.myCar.getCarRegistration()+ "', CarColor = '" +MainMenuWindow.myCar.getCarColour()+ "', "
											+ "CarEngine = '" +MainMenuWindow.myCar.getCarEngine()+ "', CarPrice = '" +strCarPrice+ "', CarVAT = '" +strCarVAT+ "', CarTotal = '" +strCarTotal+ "', BuyOrSell = '" +buyOrSell+ "' "
													+ "WHERE TransCustID = " +MainMenuWindow.myTransaction.getTransID();
							
							MainMenuWindow.dataStorage.executeStatement(sql);
							
							JOptionPane.showMessageDialog(null, "Successful Update",
									"Update", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please enter either 'Buy' or 'Sell'",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
						
						MainMenuWindow.txtTransCustName.setText(null);
						MainMenuWindow.txtTransCustAddress.setText(null);
						MainMenuWindow.txtTransCustPhone.setText(null);
						MainMenuWindow.txtTransCustDOB.setText(null);
						MainMenuWindow.txtTransCustName.setText(null);
						MainMenuWindow.txtTransCustInsureC.setText(null);
						MainMenuWindow.txtTransCustNCB.setText(null);
						MainMenuWindow.txtTransCustMake.setText(null);
						MainMenuWindow.txtTransCustModel.setText(null);
						MainMenuWindow.txtTransCustReg.setText(null);
						MainMenuWindow.txtTransCustColor.setText(null);
						MainMenuWindow.txtTransCustEngine.setText(null);
						MainMenuWindow.txtTransCustPrice.setText(null);
						MainMenuWindow.txtTransCustVAT.setText(null);
						MainMenuWindow.txtTransCustTotal.setText(null);
						MainMenuWindow.txtBuyOrSell.setText(null);
					}
				break;
				
				case "Delete Transaction":
					try
					{
						MainMenuWindow.myTransaction.setTransID(Integer.parseInt(MainMenuWindow.txtDeleteTransaction.getText()));
						sql = "DELETE FROM transactions WHERE TransCustID = '" +MainMenuWindow.myTransaction.getTransID()+ "'";
					
						MainMenuWindow.dataStorage.executeStatement(sql);
						MainMenuWindow.txtDeleteTransaction.setText(null);
					}
					catch(NumberFormatException se)
					{
						JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
					}
				break;
				
				case "Search by ID":
					try 
					{
						MainMenuWindow.transactionShow.setText(null);
						MainMenuWindow.myTransaction.setTransID(Integer.parseInt(MainMenuWindow.txtSearchTransID.getText()));
					} 
					catch (NumberFormatException e1) 
					{
						JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT TransCustID, CustomerName, CustomerAddress, CustomerPhone, CustomerDOB, InsuranceCompany, NCB, CarMake, "
							+ "CarModel, CarReg, CarColor, CarEngine, CarPrice, CarVAT, CarTotal, BuyOrSell"
							+ " FROM transactions WHERE TransCustID = " +MainMenuWindow.myTransaction.getTransID();
					
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
						
						MainMenuWindow.txtSearchTransID.setText(null);
						
						MainMenuWindow.results.close();
					} 
					catch (SQLException e1) 
					{
						JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				break;
				
				case "Search by Name":
					try 
					{
						MainMenuWindow.transactionShow.setText(null);
						MainMenuWindow.myTransaction.setName(MainMenuWindow.txtSearchTransName.getText());
					} 
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, "Enter a name and try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT TransCustID, CustomerName, CustomerAddress, CustomerPhone, CustomerDOB, InsuranceCompany, NCB, CarMake, "
							+ "CarModel, CarReg, CarColor, CarEngine, CarPrice, CarVAT, CarTotal, BuyOrSell"
							+ " FROM transactions WHERE CustomerName LIKE '%" +MainMenuWindow.myTransaction.getName()+ "%'";
					
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
				break;
				
				case "Search by Make":
					try 
					{
						MainMenuWindow.transactionShow.setText(null);
						MainMenuWindow.myCar.setCarMake(MainMenuWindow.txtSearchTransCarMake.getText());
					} 
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, "Enter a name and try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT TransCustID, CustomerName, CustomerAddress, CustomerPhone, CustomerDOB, InsuranceCompany, NCB, CarMake, "
							+ "CarModel, CarReg, CarColor, CarEngine, CarPrice, CarVAT, CarTotal, BuyOrSell"
							+ " FROM transactions WHERE CarMake LIKE '%" +MainMenuWindow.myCar.getCarMake()+ "%'";
					
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
				break;
				
				case "Search Car ID":
					try 
					{
						MainMenuWindow.carShow.setText(null);
						MainMenuWindow.myTransaction.setTransID(Integer.parseInt(MainMenuWindow.txtSearchCarsID.getText()));
					} 
					catch (NumberFormatException e1) 
					{
						JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT TransCustID, CarMake, CarModel, CarReg, CarColor, CarEngine, BuyOrSell"
							+ " FROM transactions WHERE TransCustID = " +MainMenuWindow.myTransaction.getTransID();
					
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
							buyOrSell = MainMenuWindow.results.getString("BuyOrSell");
							 
							MainMenuWindow.carShow.append(String.format("%-3s %-4d %-7s %-15s %-8s %-15s %-6s %-10s %-8s %-14s %-9s %-15s %-8s %-5s\n",
									 "ID:", MainMenuWindow.myTransaction.getTransID(), "MAKE:", MainMenuWindow.myCar.getCarMake(), 
									 "MODEL:", MainMenuWindow.myCar.getCarModel(), "REGISTRATION:", MainMenuWindow.myCar.getCarRegistration(),
									 "COLOUR:", MainMenuWindow.myCar.getCarColour(), "ENGINE:", MainMenuWindow.myCar.getCarEngine(),
									 "BUYORSELL:", buyOrSell));
						}
						
						MainMenuWindow.txtSearchTransID.setText(null);
						
						MainMenuWindow.results.close();
					} 
					catch (SQLException e1) 
					{
						JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					MainMenuWindow.txtSearchCarsID.setText(null);
					
				break;
				
				case "Search Car Make":
					try 
					{
						MainMenuWindow.carShow.setText(null);
						MainMenuWindow.myCar.setCarMake(MainMenuWindow.txtSearchCarsMake.getText());
					} 
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, "Enter a make and try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT TransCustID, CarMake, CarModel, CarReg, CarColor, CarEngine, BuyOrSell"
							+ " FROM transactions WHERE CarMake LIKE '%" +MainMenuWindow.myCar.getCarMake()+ "%'";
					
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
							buyOrSell = MainMenuWindow.results.getString("BuyOrSell");
							
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
					
					MainMenuWindow.txtSearchCarsMake.setText(null);
				break;
				
				case "Search Car Model":
					try 
					{
						MainMenuWindow.carShow.setText(null);
						MainMenuWindow.myCar.setCarModel(MainMenuWindow.txtSearchCarsModel.getText());
					} 
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, "Enter a model and try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT TransCustID, CarMake, CarModel, CarReg, CarColor, CarEngine, BuyOrSell"
							+ " FROM transactions WHERE CarModel LIKE '%" +MainMenuWindow.myCar.getCarModel()+ "%'";
					
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
							buyOrSell = MainMenuWindow.results.getString("BuyOrSell");
							
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
					
					MainMenuWindow.txtSearchCarsModel.setText(null);
				break;
				
				case "Search Car Buy/Sell":
					try 
					{
						MainMenuWindow.carShow.setText(null);
						buyOrSell = (MainMenuWindow.txtSearchBuyOrSell.getText());
					} 
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, "Enter 'Buy' or 'Sell' and try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					if(buyOrSell.equalsIgnoreCase("Buy") || buyOrSell.equalsIgnoreCase("Sell"))
					{
					
					sql = "SELECT TransCustID, CarMake, CarModel, CarReg, CarColor, CarEngine, BuyOrSell"
							+ " FROM transactions WHERE BuyOrSell LIKE '%" +buyOrSell+ "%'";
					
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
								buyOrSell = MainMenuWindow.results.getString("BuyOrSell");
							
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
					else
					{
						JOptionPane.showMessageDialog(null, "Enter 'Buy' or 'Sell' and try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					MainMenuWindow.txtSearchBuyOrSell.setText(null);
				break;
				
				case "Exit Car":
					MainMenuWindow.carPanel.setVisible(false);
					MainMenuWindow.lblEnterIDCar.setVisible(false);
					MainMenuWindow.btnSearchCarEdit.setVisible(false);
					MainMenuWindow.txtSearchCarEdit.setVisible(false);
				break;
				
				case "Find Cars":
					try 
					{
						MainMenuWindow.myTransaction.setTransID(Integer.parseInt(MainMenuWindow.txtSearchCarEdit.getText()));
					} 
					catch (NumberFormatException e1) 
					{
						JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT CarMake, CarModel, CarReg, CarColor, CarEngine, BuyOrSell"
							+ " FROM transactions WHERE TransCustID = " +MainMenuWindow.myTransaction.getTransID();
					
					MainMenuWindow.results = MainMenuWindow.dataStorage.getResults(sql);
					
					try 
					{
						while(MainMenuWindow.results.next())
						{
							MainMenuWindow.myCar.setCarMake(MainMenuWindow.results.getString("CarMake"));
							MainMenuWindow.myCar.setCarModel(MainMenuWindow.results.getString("CarModel"));
							MainMenuWindow.myCar.setCarRegistration(MainMenuWindow.results.getString("CarReg"));
							MainMenuWindow.myCar.setCarColour(MainMenuWindow.results.getString("CarColor"));
							MainMenuWindow.myCar.setCarEngine(MainMenuWindow.results.getString("CarEngine"));
							buyOrSell = (MainMenuWindow.results.getString("BuyOrSell"));
						}
						
						MainMenuWindow.txtCarMake.setText(MainMenuWindow.myCar.getCarMake());
						MainMenuWindow.txtCarModel.setText(MainMenuWindow.myCar.getCarModel());
						MainMenuWindow.txtCarRegistration.setText(MainMenuWindow.myCar.getCarRegistration());
						MainMenuWindow.txtCarColor.setText(MainMenuWindow.myCar.getCarColour());
						MainMenuWindow.txtCarEngine.setText(MainMenuWindow.myCar.getCarEngine());
						MainMenuWindow.txtCarBuyOrSell.setText(buyOrSell);
						
						
						MainMenuWindow.results.close();
					} 
					catch (SQLException es)
					{
						JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					MainMenuWindow.txtSearchCarEdit.setEditable(false);
					MainMenuWindow.btnUpdateCar.setEnabled(true);
				break;
				
				case "Update Car":
					MainMenuWindow.myTransaction.setTransID(Integer.parseInt(MainMenuWindow.txtSearchCarEdit.getText()));
					MainMenuWindow.myCar.setCarMake(MainMenuWindow.txtCarMake.getText());
					MainMenuWindow.myCar.setCarModel(MainMenuWindow.txtCarModel.getText());
					MainMenuWindow.myCar.setCarRegistration(MainMenuWindow.txtCarRegistration.getText());
					MainMenuWindow.myCar.setCarColour(MainMenuWindow.txtCarColor.getText());
					MainMenuWindow.myCar.setCarEngine(MainMenuWindow.txtCarEngine.getText());
					buyOrSell = (MainMenuWindow.txtCarBuyOrSell.getText());
					
					if(MainMenuWindow.myCar.getCarMake().isEmpty() || MainMenuWindow.myCar.getCarModel().isEmpty()
							|| MainMenuWindow.myCar.getCarRegistration().isEmpty() || MainMenuWindow.myCar.getCarColour().isEmpty()
							|| MainMenuWindow.myCar.getCarEngine().isEmpty() || buyOrSell.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "You have not entered some information",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						sql = "UPDATE transactions SET CarMake = '" +MainMenuWindow.myCar.getCarMake()+ "', CarModel = '" +MainMenuWindow.myCar.getCarModel()+ "', CarReg = '" +MainMenuWindow.myCar.getCarRegistration()+ "', CarColor = '" +MainMenuWindow.myCar.getCarColour()+ "', "
										+ "CarEngine = '" +MainMenuWindow.myCar.getCarEngine()+ "', BuyOrSell = '" +buyOrSell+ "' WHERE TransCustID = " +MainMenuWindow.myTransaction.getTransID();
						
						MainMenuWindow.dataStorage.executeStatement(sql);
						
						JOptionPane.showMessageDialog(null, "Successful Update",
								"Update", JOptionPane.INFORMATION_MESSAGE);
					}
					
					MainMenuWindow.txtSearchCarEdit.setText(null);
					MainMenuWindow.txtCarMake.setText(null);
					MainMenuWindow.txtCarModel.setText(null);
					MainMenuWindow.txtCarRegistration.setText(null);
					MainMenuWindow.txtCarColor.setText(null);
					MainMenuWindow.txtCarEngine.setText(null);
					MainMenuWindow.txtCarBuyOrSell.setText(null);
				break;
				
				case "Search Customer ID":
					try 
					{
						MainMenuWindow.customerShow.setText(null);
						MainMenuWindow.myCustomer.setCustomerID(Integer.parseInt(MainMenuWindow.txtSearchCustomerID.getText()));
					} 
					catch (NumberFormatException e1) 
					{
						JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT TransCustID, CustomerName, CustomerAddress, CustomerPhone, CustomerDOB"
							+ " FROM transactions WHERE TransCustID = " +MainMenuWindow.myCustomer.getCustomerID();
					
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
							 
							MainMenuWindow.customerShow.append(String.format("%-3s %-4d %-5s %-15s %-8s %-15s %-6s %-10s %-8s %-14s\n",
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
					
					MainMenuWindow.txtSearchCustomerID.setText(null);
				break;
				
				case "Search Cust Name":
					try 
					{
						MainMenuWindow.customerShow.setText(null);
						MainMenuWindow.myCustomer.setName(MainMenuWindow.txtSearchCustomerName.getText());
					} 
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, "Enter a name and try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT TransCustID, CustomerName, CustomerAddress, CustomerPhone, CustomerDOB FROM transactions WHERE CustomerName LIKE '%" +MainMenuWindow.myCustomer.getName()+ "%'";
					
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
							 
							MainMenuWindow.customerShow.append(String.format("%-3s %-4d %-5s %-15s %-8s %-15s %-6s %-10s %-8s %-14s\n",
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
					
					MainMenuWindow.txtSearchCustomerName.setText(null);
				break;
				
				case "Search Cust Address":
					try 
					{
						MainMenuWindow.customerShow.setText(null);
						MainMenuWindow.myCustomer.setAddress(MainMenuWindow.txtSearchCustomerAddress.getText());
					} 
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, "Enter a name and try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT TransCustID, CustomerName, CustomerAddress, CustomerPhone, CustomerDOB FROM transactions WHERE CustomerAddress LIKE '%" +MainMenuWindow.myCustomer.getAddress()+ "%'";
					
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
							 
							MainMenuWindow.customerShow.append(String.format("%-3s %-4d %-5s %-15s %-8s %-15s %-6s %-10s %-8s %-14s\n",
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
					
					MainMenuWindow.txtSearchCustomerAddress.setText(null);
				break;
				
				case "Find Customers":
				try 
				{
					MainMenuWindow.myCustomer.setCustomerID(Integer.parseInt(MainMenuWindow.txtSearchCustomerEdit.getText()));
				} 
				catch (NumberFormatException e1) 
				{
					JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				sql = "SELECT CustomerName, CustomerAddress, CustomerPhone, CustomerDOB"
						+ " FROM transactions WHERE TransCustID = " +MainMenuWindow.myCustomer.getCustomerID();
				
				MainMenuWindow.results = MainMenuWindow.dataStorage.getResults(sql);
				
				try 
				{
					while(MainMenuWindow.results.next())
					{
						MainMenuWindow.myCustomer.setName(MainMenuWindow.results.getString("CustomerName"));
						MainMenuWindow.myCustomer.setAddress(MainMenuWindow.results.getString("CustomerAddress"));
						MainMenuWindow.myCustomer.setPhoneNumber(MainMenuWindow.results.getString("CustomerPhone"));
						MainMenuWindow.myCustomer.setDOB(MainMenuWindow.results.getString("CustomerDOB"));
					}
					
					MainMenuWindow.txtCustomerName.setText(MainMenuWindow.myCustomer.getName());
					MainMenuWindow.txtCustomerAddress.setText(MainMenuWindow.myCustomer.getAddress());
					MainMenuWindow.txtCustomerPhone.setText(MainMenuWindow.myCustomer.getPhoneNumber());
					MainMenuWindow.txtCustomerDOB.setText(MainMenuWindow.myCustomer.getDOB());
					
					MainMenuWindow.results.close();
				} 
				catch (SQLException es)
				{
					JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				
				MainMenuWindow.txtSearchCustomerEdit.setEditable(false);
				MainMenuWindow.btnUpdateCustomer.setEnabled(true);
				break;
				
				case "Update Customer":
					MainMenuWindow.myCustomer.setCustomerID(Integer.parseInt(MainMenuWindow.txtSearchCustomerEdit.getText()));
					MainMenuWindow.myCustomer.setName(MainMenuWindow.txtCustomerName.getText());
					MainMenuWindow.myCustomer.setAddress(MainMenuWindow.txtCustomerAddress.getText());
					MainMenuWindow.myCustomer.setPhoneNumber(MainMenuWindow.txtCustomerPhone.getText());
					MainMenuWindow.myCustomer.setDOB(MainMenuWindow.txtCustomerDOB.getText());
					
					if(MainMenuWindow.myCustomer.getName().isEmpty() || MainMenuWindow.myCustomer.getAddress().isEmpty() 
							|| MainMenuWindow.myCustomer.getPhoneNumber().isEmpty() || MainMenuWindow.myCustomer.getDOB().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "You have not entered some information",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						sql = "UPDATE transactions SET CustomerName = '" +MainMenuWindow.myCustomer.getName()+ "', CustomerAddress = '" +MainMenuWindow.myCustomer.getAddress()+ "', CustomerPhone = '" +MainMenuWindow.myCustomer.getPhoneNumber()+ "',"
								+ "CustomerDOB = '" +MainMenuWindow.myCustomer.getDOB()+ "' WHERE TransCustID = " +MainMenuWindow.myCustomer.getCustomerID();
						
						MainMenuWindow.dataStorage.executeStatement(sql);
						
						JOptionPane.showMessageDialog(null, "Successful Update",
								"Update", JOptionPane.INFORMATION_MESSAGE);
					}
					
					MainMenuWindow.txtSearchCustomerEdit.setText(null);
					MainMenuWindow.txtCustomerName.setText(null);
					MainMenuWindow.txtCustomerAddress.setText(null);
					MainMenuWindow.txtCustomerPhone.setText(null);
					MainMenuWindow.txtCustomerDOB.setText(null);
				break;
				
				case "Exit Customer":
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
				break;
				
				case "Add Employee":
					MainMenuWindow.myEmployee.setName(MainMenuWindow.txtEmpName.getText());
					MainMenuWindow.myEmployee.setAddress(MainMenuWindow.txtEmpAddress.getText());
					MainMenuWindow.myEmployee.setPhoneNumber(MainMenuWindow.txtEmpPhone.getText());
					MainMenuWindow.myEmployee.setDOB(MainMenuWindow.txtEmpDOB.getText());
					MainMenuWindow.myEmployee.setEmployeeJob(MainMenuWindow.txtEmpJob.getText());
					strEmpSalary = (MainMenuWindow.txtEmpSalary.getText());
					
					if(MainMenuWindow.myEmployee.getName().isEmpty() || MainMenuWindow.myEmployee.getAddress().isEmpty() 
							|| MainMenuWindow.myEmployee.getPhoneNumber().isEmpty() || MainMenuWindow.myEmployee.getDOB().isEmpty()
							|| MainMenuWindow.myEmployee.getEmployeeJob().isEmpty() || strEmpSalary.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "You have not entered some information",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						sql = "INSERT INTO employees (EmployeeName, EmployeeAddress, EmployeePhone, EmployeeDOB, EmployeeJob, EmployeeSalary) VALUES "
							+ "('"+MainMenuWindow.myEmployee.getName()+"', '"+MainMenuWindow.myEmployee.getAddress()+"', '"+MainMenuWindow.myEmployee.getPhoneNumber()+"', '"
									+MainMenuWindow.myEmployee.getDOB()+"', '"+MainMenuWindow.myEmployee.getEmployeeJob()+"', '"+strEmpSalary+"')";
									
						MainMenuWindow.dataStorage.executeStatement(sql);
					}
					
					MainMenuWindow.txtEmpName.setText(null);
					MainMenuWindow.txtEmpAddress.setText(null);
					MainMenuWindow.txtEmpPhone.setText(null);
					MainMenuWindow.txtEmpDOB.setText(null);
					MainMenuWindow.txtEmpJob.setText(null);
					MainMenuWindow.txtEmpSalary.setText(null);
				break;
				
				case "Exit Employee":
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
					MainMenuWindow.employeePanel.setVisible(false);
					MainMenuWindow.employeeHeader.setVisible(false);
				break;
				
				case "Find Employee":
					try 
					{
						MainMenuWindow.myEmployee.setEmployeeID(Integer.parseInt(MainMenuWindow.txtSearchEmployeeEdit.getText()));
					} 
					catch (NumberFormatException e1) 
					{
						JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT EmployeeName, EmployeeAddress, EmployeePhone, EmployeeDOB, EmployeeJob, EmployeeSalary"
							+ " FROM employees WHERE EmpID = " +MainMenuWindow.myEmployee.getEmployeeID();
					
					MainMenuWindow.results = MainMenuWindow.dataStorage.getResults(sql);
					
					try 
					{
						while(MainMenuWindow.results.next())
						{
							MainMenuWindow.myEmployee.setName(MainMenuWindow.results.getString("EmployeeName"));
							MainMenuWindow.myEmployee.setAddress(MainMenuWindow.results.getString("EmployeeAddress"));
							MainMenuWindow.myEmployee.setPhoneNumber(MainMenuWindow.results.getString("EmployeePhone"));
							MainMenuWindow.myEmployee.setDOB(MainMenuWindow.results.getString("EmployeeDOB"));
							MainMenuWindow.myEmployee.setEmployeeJob(MainMenuWindow.results.getString("EmployeeJob"));
							strEmpSalary = (MainMenuWindow.results.getString("EmployeeSalary"));
						}
						
						MainMenuWindow.txtEmpName.setText(MainMenuWindow.myEmployee.getName());
						MainMenuWindow.txtEmpAddress.setText(MainMenuWindow.myEmployee.getAddress());
						MainMenuWindow.txtEmpPhone.setText(MainMenuWindow.myEmployee.getPhoneNumber());
						MainMenuWindow.txtEmpDOB.setText(MainMenuWindow.myEmployee.getDOB());
						MainMenuWindow.txtEmpJob.setText(MainMenuWindow.myEmployee.getEmployeeJob());
						MainMenuWindow.txtEmpSalary.setText(strEmpSalary);
						
						MainMenuWindow.results.close();
					} 
					catch (SQLException es)
					{
						JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					MainMenuWindow.btnEmployeeAdd.setEnabled(false);
					MainMenuWindow.txtSearchEmployeeEdit.setEditable(false);
					MainMenuWindow.btnEmployeeUpdate.setEnabled(true);
				break;
				
				case "Update Employee":
					MainMenuWindow.myEmployee.setEmployeeID(Integer.parseInt(MainMenuWindow.txtSearchEmployeeEdit.getText()));
					MainMenuWindow.myEmployee.setName(MainMenuWindow.txtEmpName.getText());
					MainMenuWindow.myEmployee.setAddress(MainMenuWindow.txtEmpAddress.getText());
					MainMenuWindow.myEmployee.setPhoneNumber(MainMenuWindow.txtEmpPhone.getText());
					MainMenuWindow.myEmployee.setDOB(MainMenuWindow.txtEmpDOB.getText());
					MainMenuWindow.myEmployee.setEmployeeJob(MainMenuWindow.txtEmpJob.getText());
					strEmpSalary = (MainMenuWindow.txtEmpSalary.getText());
					
					if(MainMenuWindow.myEmployee.getName().isEmpty() || MainMenuWindow.myEmployee.getAddress().isEmpty() 
							|| MainMenuWindow.myEmployee.getPhoneNumber().isEmpty() || MainMenuWindow.myEmployee.getDOB().isEmpty()
							|| MainMenuWindow.myEmployee.getEmployeeJob().isEmpty() || strEmpSalary.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "You have not entered some information",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						sql = "UPDATE employees SET EmployeeName = '" +MainMenuWindow.myEmployee.getName()+ "', EmployeeAddress = '" +MainMenuWindow.myEmployee.getAddress()+ "', EmployeePhone = '" +MainMenuWindow.myEmployee.getPhoneNumber()+ "',"
								+ "EmployeeDOB = '" +MainMenuWindow.myEmployee.getDOB()+ "', EmployeeJob = '" +MainMenuWindow.myEmployee.getEmployeeJob()+"', EmployeeSalary = '" +strEmpSalary+"' WHERE EmpID = " +MainMenuWindow.myEmployee.getEmployeeID();
						
						MainMenuWindow.dataStorage.executeStatement(sql);
						
						JOptionPane.showMessageDialog(null, "Successful Update",
								"Update", JOptionPane.INFORMATION_MESSAGE);
					}
					
					MainMenuWindow.txtSearchEmployeeEdit.setText(null);
					MainMenuWindow.txtEmpName.setText(null);
					MainMenuWindow.txtEmpAddress.setText(null);
					MainMenuWindow.txtEmpPhone.setText(null);
					MainMenuWindow.txtEmpDOB.setText(null);
					MainMenuWindow.txtEmpJob.setText(null);
					MainMenuWindow.txtEmpSalary.setText(null);
				break;
				
				case "Delete Employee":
					try
					{
						MainMenuWindow.myCustomer.setCustomerID(Integer.parseInt(MainMenuWindow.txtDeleteEmployee.getText()));
						sql = "DELETE FROM employees WHERE EmpID = '" +MainMenuWindow.myCustomer.getCustomerID()+ "'";
					
						MainMenuWindow.dataStorage.executeStatement(sql);
						MainMenuWindow.txtDeleteEmployee.setText(null);
					}
					catch(NumberFormatException se)
					{
						JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
					}
				break;
				
				case "Search Employee ID":
					try 
					{
						MainMenuWindow.employeeShow.setText(null);
						MainMenuWindow.myEmployee.setEmployeeID(Integer.parseInt(MainMenuWindow.txtSearchEmployeeID.getText()));
					} 
					catch (NumberFormatException e1) 
					{
						JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT EmpID, EmployeeName, EmployeeAddress, EmployeePhone, EmployeeDOB, EmployeeJob, EmployeeSalary"
							+ " FROM employees WHERE EmpID = " +MainMenuWindow.myEmployee.getEmployeeID();
					
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
							 
							MainMenuWindow.employeeShow.append(String.format("%-3s %-4d %-5s %-15s %-8s %-15s %-6s %-10s %-4s %-14s %-4s %-15s %-7s %-9s\n",
									 "ID:", MainMenuWindow.myEmployee.getEmployeeID(), "NAME:", MainMenuWindow.myEmployee.getName(), 
									 "    ADDRESS:", MainMenuWindow.myEmployee.getAddress(), "PHONE:", MainMenuWindow.myEmployee.getPhoneNumber(),
									 "    DOB:", MainMenuWindow.myEmployee.getDOB(), "JOB:", MainMenuWindow.myEmployee.getEmployeeJob(), "YEAR SALARY:", strEmpSalary));
						}
						
						MainMenuWindow.results.close();
					} 
					catch (SQLException e1) 
					{
						JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					MainMenuWindow.txtSearchEmployeeID.setText(null);
				break;
				
				case "Search Emp Name":
					try 
					{
						MainMenuWindow.employeeShow.setText(null);
						MainMenuWindow.myEmployee.setName(MainMenuWindow.txtSearchEmployeeName.getText());
					} 
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, "Enter a name and try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT EmpID, EmployeeName, EmployeeAddress, EmployeePhone, EmployeeDOB, EmployeeJob, EmployeeSalary "
							+ "FROM employees WHERE EmployeeName LIKE '%" +MainMenuWindow.myEmployee.getName()+ "%'";
					
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
							 
							MainMenuWindow.employeeShow.append(String.format("%-3s %-4d %-5s %-15s %-8s %-15s %-6s %-10s %-4s %-14s %-4s %-15s %-7s %-9s\n",
									 "ID:", MainMenuWindow.myEmployee.getEmployeeID(), "NAME:", MainMenuWindow.myEmployee.getName(), 
									 "    ADDRESS:", MainMenuWindow.myEmployee.getAddress(), "PHONE:", MainMenuWindow.myEmployee.getPhoneNumber(),
									 "    DOB:", MainMenuWindow.myEmployee.getDOB(), "JOB:", MainMenuWindow.myEmployee.getEmployeeJob(), "YEAR SALARY:", strEmpSalary));
						}
						
						MainMenuWindow.results.close();
					} 
					catch (SQLException e1) 
					{
						JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					MainMenuWindow.txtSearchEmployeeName.setText(null);
				break;
				
				case "Search Emp Job":
					try 
					{
						MainMenuWindow.employeeShow.setText(null);
						MainMenuWindow.myEmployee.setEmployeeJob(MainMenuWindow.txtSearchEmployeeJob.getText());
					} 
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, "Enter a name and try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					sql = "SELECT EmpID, EmployeeName, EmployeeAddress, EmployeePhone, EmployeeDOB, EmployeeJob, EmployeeSalary "
							+ "FROM employees WHERE EmployeeJob LIKE '%" +MainMenuWindow.myEmployee.getEmployeeJob()+ "%'";
					
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
							 
							MainMenuWindow.employeeShow.append(String.format("%-3s %-4d %-5s %-15s %-8s %-15s %-6s %-10s %-4s %-14s %-4s %-15s %-7s %-9s\n",
									 "ID:", MainMenuWindow.myEmployee.getEmployeeID(), "NAME:", MainMenuWindow.myEmployee.getName(), 
									 "    ADDRESS:", MainMenuWindow.myEmployee.getAddress(), "PHONE:", MainMenuWindow.myEmployee.getPhoneNumber(),
									 "    DOB:", MainMenuWindow.myEmployee.getDOB(), "JOB:", MainMenuWindow.myEmployee.getEmployeeJob(), "YEAR SALARY:", strEmpSalary));
						}
						
						MainMenuWindow.results.close();
					} 
					catch (SQLException e1) 
					{
						JOptionPane.showMessageDialog(null, "There has been a database connection error - try again", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					MainMenuWindow.txtSearchEmployeeJob.setText(null);
				break;
				
			}//end switch structure
			
		}//end action performed
		
	}//end ButtonHandler Class