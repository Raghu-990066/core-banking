package com.training.amdocs;
//import jdbc_conn
//public class BaseClass {
//	public static void main(String args[]) {
//		System.out.println("hello world!!");
//		System.out.println("th");
//	}
//
//
//}
//package bankino;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class BaseClass {//full

	public static void main(String[] args) throws SQLException {
		Scanner s=new Scanner(System.in);
		try {
   		 Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@//localhost:1521/orcl.iiht.tech";

			String userName = "scott";

			String password = "tiger";

			Connection con= DriverManager.getConnection(url,userName,password);
//			#bank_base_user

			if(con!=null) {
				System.out.println("connected");
			}
			else
			{
				System.out.println("connected failed");
			}
		

            int user_choice;
			
			
				System.out.println();
				System.out.println("1) Open a new bank account");
				System.out.println("2) Deposit to a bank account");
				System.out.println("3) Withdraw to bank account");
				System.out.println("4) Print account balance");
				System.out.println("5) Quit");
				System.out.println();
				System.out.println("Enter choice [1-5]");
				user_choice=s.nextInt();
				switch(user_choice) {
				       case 1://case user creation
				    	   System.out.println("Enter a customer name");
				    	   String user_name=s.next();
				    	   
				    	   System.out.println("Enter customer phone number");
				    	   String phone_no =s.next();
				    	   
				    	   System.out.println("Enter customer address");
				    	   String user_address=s.next();
				    	
				    	   System.out.println("Enetr a opening balance");
				    	   double user_bal=s.nextDouble();				    				    	     
			    	        Statement st1=con.createStatement();
			    	        ResultSet rs1=st1.executeQuery("SELECT * FROM (select acc_no from bank_base_user ORDER BY acc_no DESC) where rownum = 1 ");
			    	       
			    	        while(rs1.next()) {
			    	        	int in_number;
			    	        	System.out.println(rs1.getString(1));
			    	        	
						    	in_number = rs1.getInt(1);
						    	in_number += 1;
						    	System.out.println(in_number);
						    	    	        			    	        
				             if(con !=null) {
							    	
							    PreparedStatement ps;
								ps=con.prepareStatement("insert into bank_base_user values(?,?,?,?,?)");
							    ps.setInt(1, in_number);
								ps.setString(2, user_name);
								
								ps.setString(3, phone_no);
								ps.setString(4, user_address);
								ps.setDouble(5, user_bal);
								
								int i=ps.executeUpdate();
								if (i==1)
									System.out.println("record inserted successfully and get an account number is  "+in_number);
								else
								  System.out.println("invalid insertion");
							    }				    	  
			    	        }
				    	   break;
				       case 2://case deposit
				    	
				    	    System.out.println("Enter a account number");
			    	        int acc_no_deposit=s.nextInt();
			    	       
			    	        System.out.println("Enter amount to deposit");
			    	        int user_bal_deposit=s.nextInt();
					   
				            PreparedStatement ps1=con.prepareStatement("update bank_base_user set account_bal=account_bal+? where acc_no=?");
			    	        ps1.setInt(1, user_bal_deposit);
			    	        ps1.setInt(2, acc_no_deposit);
			    	        ps1.executeUpdate();
			    	        Statement st2=con.createStatement();
			    	        ResultSet rs2=st2.executeQuery("select account_bal from bank_base_user where acc_no="+acc_no_deposit);
			    	        System.out.println("Account Number = " + acc_no_deposit);
			    	        while(rs2.next()) {
			    	        System.out.println("Current Balance : "+rs2.getString(1));
			    	        }
			    	        rs2.close();
			    	         break; 		
			    	        			
			    	      
				       case 3://Case withdraw
				    	   System.out.println("Enter a account number");
		    	           int acc_no_withdraw=s.nextInt();
		    	           System.out.println("Enter amount to withdraw");
		    	           int amt_withdraw=s.nextInt();
		    	           PreparedStatement ps2=con.prepareStatement("update bank_base_user set account_bal=account_bal-? where acc_no=?");
		    	           ps2.setInt(1, amt_withdraw);
		    	           ps2.setInt(2, acc_no_withdraw);
		    	           ps2.executeUpdate();
		    	           
		    	           
		    	           Statement st3=con.createStatement();
			    	       ResultSet rs3=st3.executeQuery("select account_bal from bank_base_user where acc_no="+acc_no_withdraw);
			    	       System.out.println("Account Number = " + acc_no_withdraw);
			    	       while(rs3.next()) {
			    	       System.out.println("Current Balance : "+rs3.getString(1));
			    	       }
			    	       rs3.close();
		    	           break; 	
				    	    
				      case 4://Case bal
				    	    System.out.println("Enter a account number");
			    	        int anum=s.nextInt();
			    	        Statement st=con.createStatement();
			    	        ResultSet rs=st.executeQuery("select  *from bank_base_user where acc_no ="+anum);
			    	        
			    	        while(rs.next()) {
			    	        	System.out.println("User accountNumber : "+rs.getString(1)+"\n "+"User Name : "+rs.getString(2)+"\n "+"User Balance : "+rs.getString(5));
			    	        }
			    	        rs.close();
			    	        break;
			    	        
				       case 5://case exit
				    	   System.out.println("Operation Terminated");
				    	   System.exit(0);
				}
			}
		
			
			catch(Exception e) {
				e.printStackTrace();
			}
		
	}	
}

	
		
			
	


	