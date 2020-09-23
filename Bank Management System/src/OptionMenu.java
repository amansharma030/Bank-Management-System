import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.Map.Entry;
import java.text.DecimalFormat;

public class OptionMenu extends Account {
Scanner menuInput = new Scanner(System.in);
DecimalFormat moneyFormat= new DecimalFormat("'Rs.' ###,##0.00");

HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();
int x=0;
public void getLogin() throws IOException, Exception{
	 int customerNumber = 0; int pinNumber= 0; 
while(x<=1) {
		try {
			
			
			getPin();
			System.out.println("==================================");
			System.out.println("Welcome to Bank Management System"); 
			System.out.println("==================================");
			
			
			
			System.out.println("Enter Your Customer Number: ");
	
			
			setCustomerNumber(menuInput.nextInt());
			System.out.println("Enter Your Pin Number: ");
			
			
			setPinNumber(menuInput.nextInt());
					
				}catch (Exception e) {
					System.out.println("\n" + "Invalid character(s). Only numbers. "+ "\n");
					x=2;
				}

		for (Entry<Integer, Integer> entry : data.entrySet()) {
			if (entry.getKey() == getCustomerNumber() && entry.getValue() == getPinNumber())
			{
				
						if(x==1)
						{
							Scanner pinInput = new Scanner(System.in);
							
							System.out.println("Enter New Pin Number");
							
							
							int newPin= pinInput.nextInt(); 
							
							updatePin(getCustomerNumber(),newPin); 
							
						
							System.out.println("\n");
							System.out.println("Pin Is Changed Sucessfully");
							 
							System.out.println("");
						}
				 
							getAccountType();
						
						
						
			
		}
			else if(x!=2) {
				System.out.println("\n" + "Wrong Coustomer Number or Pin Number." + "\n");
			}
	
		}
	}

	
}

                public void getAccountType() throws Exception
                 {
                	System.out.println("");
                	System.out.println("Select the Account you want to access: ");
                	System.out.println("Type 1 - Current Account");
                	System.out.println("Type 2 - Saving Account ");
                	System.out.println("Type 3 - Account Statement");
                	System.out.println("Type 4 - Change PIN");
                	System.out.println("Type 5 - File Complaint");
                	System.out.println("Type 6 - Log Out");
                	
                	
                	System.out.println("Choice: ");
                	
                	
                	selection = menuInput.nextInt();
                	
                	switch (selection) {
                	case 1:
                		getCurrent();
                		break;
                	case 2:
                		getSaving();
                		break;
                	
                	case 3:
                		accountStatement();
                		break;
                		
                	case 4:
                		x=1;
                		getLogin();
                		break;
                		
                	case 5:
                		complaintBox();
                		break;
                		
                	case 6:
                   		System.out.println("Thank You for using ATM, bye.");
                		break;
                		
                		default:
                		System.out.println("\n" + "Invalid Choice." + "\n");
                		getAccountType();
                	                	}
                }
                
             public void getCurrent() throws Exception {
                	System.out.println("Current Account: ");
                	System.out.println("Type 1 - View Balance");
                	System.out.println("Type 2 - Withdraw Funds");
                	System.out.println("Type 3 - Deposit Funds");
                	System.out.println("Type 4 - Log Out");
                	System.out.println("Choice: "); 
                	
                    selection = menuInput.nextInt();
                    
                    switch (selection) {
                    case 1:
                    	   System.out.println("Current Account Balance: " + "Rs. " + moneyFormat.format(currentBalance));
                                       	 
                    getAccountType();
                    break;               
                    
                    case 2:
                    getCurrentWithdrawInput();
                    getAccountType();
                    break;
                    
                    case 3:
                    getCurrentDepositInput();
                    getAccountType();
                    break;
                    
                    case 4:
                    System.out.println("Thank You for using ATM, bye.");
                    break;
                    
                    default:
                    	System.out.println("\n" + "Invalid choice." + "\n");
                    	getCurrent();
                    	                    
                    
                    }
                }
                
             int selection;
             
                public void getSaving() throws Exception{
                	System.out.println("Saving Account: ");
                	System.out.println("Type 1 - View Balance");
                	System.out.println("Type 2 - Withdraw Funds");
                	System.out.println("Type 3 - Deposit Funds");
                	System.out.println("Type 4 - Log Out");
                	System.out.println("Choice: "); 
                	
                	selection = menuInput.nextInt();
                	
                	switch(selection) {
                	case 1:
                	System.out.println("Current Account Balance: " + moneyFormat.format(savingBalance));
                    getAccountType();
                    break;
                	
                	 case 2:
                	 getSavingawithdrawInput();
                     getAccountType();
                     break;
                     
                	 case 3:
                     getSavingDepositInput();
                     getAccountType();
                     break;
                    
                	 case 4:
                	 System.out.println("Thank You for using this ATM, bye.");
                	 break;
                	 
                    default:
                    	System.out.println("\n" + "Invalid choice." + "\n");
                    	getSaving();
                    	
                	}
                	
                	
                }
                
                                 
                public void getPin() throws Exception
                {
                	String url="jdbc:mysql://localhost:3306/Aman";
                	String uname= "root";
                	String pass = "root";
                	

                	
                	String query= "select * from pin";
                	
                	Class.forName("com.mysql.jdbc.Driver");
                	Connection con = DriverManager.getConnection(url,uname,pass);
                	Statement st=con.createStatement();
                	
                	ResultSet rs= st.executeQuery(query);
                	
                	int n =0;
                	int o =0;

                	
                	System.out.println("");
                	
                	
                	
                	System.out.println("");
                	
                	while(rs.next())
                	{
                	
                	n =rs.getInt(1) ;
                    o =rs.getInt(2) ;
                	data.put(n,o) ;
               	
                
                	}
                
                	st.close();
                	con.close();
                	                	                	
                }
                
                
                public static void updatePin(int x,int y) throws Exception
                {
                	                	
                	
                	String url="jdbc:mysql://localhost:3306/Aman";
                	String uname= "root";
                	String pass = "root";
  	
                	String query= "update pin set pass="+y+" where username="+x+"";
                	
                	Class.forName("com.mysql.jdbc.Driver");
                	
                	Connection con = DriverManager.getConnection(url,uname,pass);
                	Statement st=con.createStatement();

                	int count=st.executeUpdate(query);
                	 	               	
                	
                	st.close();
                	con.close();
                	
                	
                }
                
                
      public void changePin() throws Exception
                {
                	
                	getLogin();
                	
                }
                
}
