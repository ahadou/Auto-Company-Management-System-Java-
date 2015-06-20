package eventsAndGUI;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

//This event class handles all the drop down list options in the program
public class ListHandler implements ItemListener
{
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		if(e.getSource() == MainMenuWindow.searchOptions)
		{
			if(MainMenuWindow.searchOptions.getSelectedIndex() == 0)
			{
				MainMenuWindow.transactionShow.setText(null);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
			}
			else if(MainMenuWindow.searchOptions.getSelectedIndex() == 1)
			{
				MainMenuWindow.transactionShow.setText(null);
				MainMenuWindow.searchTransIDPanel.setVisible(true);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
			}
			else if(MainMenuWindow.searchOptions.getSelectedIndex() == 2)
			{
				MainMenuWindow.transactionShow.setText(null);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(true);
				MainMenuWindow.searchTransCarMakePanel.setVisible(false);
			}
			else if(MainMenuWindow.searchOptions.getSelectedIndex() == 3)
			{
				MainMenuWindow.transactionShow.setText(null);
				MainMenuWindow.searchTransIDPanel.setVisible(false);
				MainMenuWindow.searchTransNamePanel.setVisible(false);
				MainMenuWindow.searchTransCarMakePanel.setVisible(true);
			}
		}
		
		if(e.getSource() == MainMenuWindow.listCarSearch)
		{
			if(MainMenuWindow.listCarSearch.getSelectedIndex() == 0)
			{
				MainMenuWindow.carShow.setText(null);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
			}
			else if(MainMenuWindow.listCarSearch.getSelectedIndex() == 1)
			{
				MainMenuWindow.carShow.setText(null);
				MainMenuWindow.searchCarIdPanel.setVisible(true);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
			}
			else if(MainMenuWindow.listCarSearch.getSelectedIndex() == 2)
			{
				MainMenuWindow.carShow.setText(null);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(true);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
			}
			else if(MainMenuWindow.listCarSearch.getSelectedIndex() == 3)
			{
				MainMenuWindow.carShow.setText(null);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(true);
				MainMenuWindow.searchCarBuySellPanel.setVisible(false);
			}
			else if(MainMenuWindow.listCarSearch.getSelectedIndex() == 4)
			{
				MainMenuWindow.carShow.setText(null);
				MainMenuWindow.searchCarIdPanel.setVisible(false);
				MainMenuWindow.searchCarMakePanel.setVisible(false);
				MainMenuWindow.searchCarModelPanel.setVisible(false);
				MainMenuWindow.searchCarBuySellPanel.setVisible(true);
			}
		}
		
		if(e.getSource() == MainMenuWindow.listCustomerSearch)
		{
			if(MainMenuWindow.listCustomerSearch.getSelectedIndex() == 0)
			{
				MainMenuWindow.customerShow.setText(null);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
			}
			else if(MainMenuWindow.listCustomerSearch.getSelectedIndex() == 1)
			{
				MainMenuWindow.customerShow.setText(null);
				MainMenuWindow.searchCustomerIdPanel.setVisible(true);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
			}
			else if(MainMenuWindow.listCustomerSearch.getSelectedIndex() == 2)
			{
				MainMenuWindow.customerShow.setText(null);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(true);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(false);
			}
			else if(MainMenuWindow.listCustomerSearch.getSelectedIndex() == 3)
			{
				MainMenuWindow.customerShow.setText(null);
				MainMenuWindow.searchCustomerIdPanel.setVisible(false);
				MainMenuWindow.searchCustomerNamePanel.setVisible(false);
				MainMenuWindow.searchCustomerAddressPanel.setVisible(true);
			}
		}
		
		if(e.getSource() == MainMenuWindow.listEmployeeSearch)
		{
			if(MainMenuWindow.listEmployeeSearch.getSelectedIndex() == 0)
			{
				MainMenuWindow.employeeShow.setText(null);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				
			}
			else if(MainMenuWindow.listEmployeeSearch.getSelectedIndex() == 1)
			{
				MainMenuWindow.employeeShow.setText(null);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(true);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				
			}
			else if(MainMenuWindow.listEmployeeSearch.getSelectedIndex() == 2)
			{
				MainMenuWindow.employeeShow.setText(null);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(true);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(false);
				
			}
			else if(MainMenuWindow.listEmployeeSearch.getSelectedIndex() == 3)
			{
				MainMenuWindow.employeeShow.setText(null);
				MainMenuWindow.searchEmployeeIdPanel.setVisible(false);
				MainMenuWindow.searchEmployeeNamePanel.setVisible(false);
				MainMenuWindow.searchEmployeeJobPanel.setVisible(true);
				
			}
		}
	}

}
