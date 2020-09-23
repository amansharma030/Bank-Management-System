import java.text.DecimalFormat;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  



public class Account {
	
	 private int customerNumber;
	 private int pinNumber;
	 double currentBalance = 0;
	 double savingBalance = 0;
     static int k;
	 
 


	Scanner input = new Scanner(System.in);
	DecimalFormat moneyFormat= new DecimalFormat("'Rs.' ###,##0.00");
	
	public int setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;                          
		return customerNumber;
		
	}
	
	
	public int getCustomerNumber() {
		return customerNumber;
	}
	
	public int setPinNumber(int pinNumber) {
		this.pinNumber= pinNumber;
		return pinNumber;
	}
	
	public int getPinNumber() {
		return pinNumber;
	}
	

	
	public void calcCurrentWithdraw(double amount) {
		currentBalance = (currentBalance - amount);
		
		  
	}
	
	public void calcSavingWithdraw(double amount) {
		savingBalance = (savingBalance - amount);
		
	}
	
	public void calcCurrentDeposit(double amount) {
		currentBalance = (currentBalance + amount);
		
	}
	
	public void calcSavingDeposit(double amount) {
		savingBalance = (savingBalance + amount);
		
	}
	
	public void getCurrentWithdrawInput() throws Exception{
		System.out.println("Current Account Balance: " + moneyFormat.format(currentBalance));
		System.out.println("Amount you want to withdraw from Chekcing Account: ");
		double amount = input.nextDouble();
		
		if((currentBalance - amount) >= 0) {

			calcCurrentWithdraw(amount);
			System.out.println("New Current Account balance: "+ moneyFormat.format(currentBalance));
			
			k=currentSerialNumber();
			
			if(k==0)                  
			{
				k=1;
			}
			else {
			k++;
		}
			
			entries(k, date(), "Current", "Debited", amount, currentBalance);
			
			
		}else {
			System.out.println("Balance cannot be negative: " + "\n");
		}
	}
	
	
	

		public void getSavingawithdrawInput() throws Exception  {
			System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
			System.out.println("Amount you want to withdraw from Saving Account: ");
			double amount=input.nextDouble();
			
			
			if((savingBalance - amount) >= 0)
			{

			calcSavingWithdraw(amount);
				System.out.println("New Saving Account Balance: " + savingBalance + "\n");
				
				k=currentSerialNumber();
				
				if(k==0)                   
				{
					k=1;
				}
				else {
				
				k++;
			}
				
				entries(k, date(), " Saving", "Debited ", amount, savingBalance);
				
				
			}else {
				System.out.println("Balance cannot be negative: " + "\n");
			}
		}
		
		public void getCurrentDepositInput() throws Exception {
			
			System.out.println("Current Account Balance: " + moneyFormat.format(currentBalance));
			System.out.println("Amount you want to deposit from Current Account: ");
			double amount=input.nextDouble();
			
			
			if((currentBalance + amount) >= 0)
			{
				
				calcCurrentDeposit(amount);
				System.out.println("New Current Account Balance: " + moneyFormat.format(currentBalance));
			
             k=currentSerialNumber();
				
				if(k==0)                   
				{
					k=1;
				}
				else {
				k++;
			}
				
				entries(k, date(), "Current", "Credited ", amount, currentBalance);
			
			}else {
				System.out.println("Balance cannot be negative: " + "\n");
			}
		}
		
public void getSavingDepositInput() throws Exception {
			
			System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
			System.out.println("Amount you want to deposit from Saving Account: ");
			double amount=input.nextDouble();
			 
			if((savingBalance + amount) >= 0)
			{
 
				calcSavingDeposit(amount);
				
				  System.out.println("New Saving Account Balance: " + moneyFormat.format(savingBalance));
			
				  k=currentSerialNumber();
					
					if(k==0)                   
					{
						k=1;
					}
					else {
					
					k++;
				}
					
					entries(k, date(), "Saving", "Credited", amount, savingBalance);
			
			}else {
				System.out.println("Balance cannot be negative: " + "\n");
			}
		}

public static void entries(int SerialNumber,String Date, String Account, String Particular, double Amount, double Balance) throws Exception{
	String url="jdbc:mysql://localhost:3306/Aman";
	String uname= "root";
	String pass = "root";
 
	

	String query= "insert into customer values (" + SerialNumber + ",'" + Date + "','" + Account + "','" + Particular + "'," + Amount + "," + Balance + ")";

	Class.forName("com.mysql.jdbc.Driver");
	
	Connection con = DriverManager.getConnection(url,uname,pass);
	Statement st=con.createStatement();

	int count=st.executeUpdate(query);
	 	 		
	
	st.close();
	con.close();
	
}


public static void complaintBox() throws Exception{
	String url="jdbc:mysql://localhost:3306/Aman";
	String uname= "root";
	String pass = "root";

	String query="insert into complaint(date,customerNumber,complaints) values (?,?,?)";
	
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(url,uname,pass);
	PreparedStatement st=con.prepareStatement(query);
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter Your Customer Number");
	int u = sc.nextInt();
	sc.nextLine();
	System.out.println("");
	System.out.println("Start Writing Your Complaint...");
	System.out.println("Press Enter to Submit");
	String p =sc.nextLine();
	
	st.setString(1, date());
	st.setInt(2, u);
	st.setString(3, p);
	
	int count = st.executeUpdate();

	
	
	
	st.close();
	con.close();
}

public static int currentSerialNumber() throws Exception
{
	String url="jdbc:mysql://localhost:3306/Aman";
	String uname= "root";
	String pass = "root";
	
	
	String query= "select SerialNumber from customer\r\n" + 
			"ORDER BY SerialNumber DESC  \r\n" + 
			"LIMIT 1;";
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(url,uname,pass);
	Statement st=con.createStatement();
	
	ResultSet rs= st.executeQuery(query);
	
	int n =0;
	
	
	System.out.println("");
	
	
	System.out.println("");
	
	while(rs.next())
	{
	
	n =rs.getInt(1) ;
	}
	return n;
		
	
}

public void accountStatement() throws Exception
{
	DecimalFormat moneyFormat= new DecimalFormat("###,##0.00");
	
	
	String url="jdbc:mysql://localhost:3306/Aman";
	String uname= "root";
	String pass = "root";
	
	
	String query= "select * from customer";
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(url,uname,pass);
	Statement st=con.createStatement();
	
	ResultSet rs= st.executeQuery(query);
	
	String name ="";
	
	
	System.out.println("------------------------------------------------------------------------------------------------------");
	
	System.out.printf("%10s %15s %25s %15s %15s %15s", "SerialNumber", "Date", "Account", "Particular", "Amount(Rs)", "Balance(Rs)");
	
	System.out.println();
	System.out.println("------------------------------------------------------------------------------------------------------");
	
	
	while(rs.next())
	{
	
	
	System.out.format("%5s %30s %17s %15s %15s %15s",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),moneyFormat.format(rs.getDouble(5)),moneyFormat.format(rs.getDouble(6)));
	System.out.println();
	}
	
	st.close();
	con.close();
	
}


public static String date()
{  
    Date date = Calendar.getInstance().getTime();  //changing Date to String
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
    String strDate = dateFormat.format(date);  
    return strDate;  
     
 } 
}
