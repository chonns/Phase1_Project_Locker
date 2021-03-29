package com.dell.locker.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

import com.dell.locker.model.UserCredentials;
import com.dell.locker.model.Users;

public class AuthenticateUserBasedOnOptions {
		
		//Input variables
		private static Scanner keyboard;
		private static Scanner input;
		private static Scanner lockerInput;
		
		//Output variables 
		private static PrintWriter output;
		private static PrintWriter lockerOutput;
		
		//Model to store data
		private static Users users;
		private static UserCredentials userCredentials;
		
		
		public static void main(String[] args) {
			initApp();
			Dashbord();
			signInOptions();

		}
		

		public static void Dashbord() {
			System.out.println("******************************************");
			System.out.println("*			                 *");
			System.out.println("*         Welcome To Locker.com		 *");
			System.out.println("*      Your Personal Digital Locker	 *");
			System.out.println("*					 *");
			System.out.println("******************************************");
			
		}
		public static void signInOptions() {
			System.out.println("1. Register User");
			System.out.println("2. Login ");
			int option = keyboard.nextInt();
			switch(option) {
				case 1 : 
					registerUser();
					break;
				case 2 :
					loginUser();
					break;
				default :
					System.out.println("Please select 1 Or 2");
					break;
			}
			keyboard.close();
			input.close();
		}
		
		public static void lockerOptions(String inpUsername) {
			System.out.println("1. Fetch All Stored Account Credentials ");
			System.out.println("2. Add New Account Detials ");
			System.out.println("3. Delete All Stored Account Credentials ");
			int option = keyboard.nextInt();
			switch(option) {
				case 1 : 
					fetchCredentials(inpUsername);
					break;
				case 2 :
					storeCredentials(inpUsername);
					break;
				case 3 :
					removeCredentials(inpUsername);
					break;
				default :
					System.out.println("Please select 1 , 2 or 3");
					break;
			}
			lockerInput.close();
		}
		
	
		public static void registerUser() {
			System.out.println("******************************************");
			System.out.println("*					*");
			System.out.println("*   Register User	*");
			System.out.println("*					*");
			System.out.println("******************************************");
			
			System.out.println("Enter Username :");
			String username = keyboard.next();
			users.setUsername(username);
			
			System.out.println("Enter Password :");
			String password = keyboard.next();
			users.setPassword(password);
			
			output.println(users.getUsername());
			output.println(users.getPassword());
			
			System.out.println("User Registration Suscessful !");
			output.close();
			
		}
		public static void loginUser() {
			System.out.println("******************************************");
			System.out.println("*										 *");
			System.out.println("*        Welcome to Login Page	         *");
			System.out.println("*					                     *");
			System.out.println("******************************************");
			System.out.println("Enter Username :");
			String inpUsername = keyboard.next();
			boolean found = false;
			while(input.hasNext() && !found) {
				if(input.next().equals(inpUsername)) {
					System.out.println("Enter Password :");
					String inpPassword = keyboard.next();
					if(input.next().equals(inpPassword)) {
						System.out.println("*****Login Successfull*****");
						found = true;
						lockerOptions(inpUsername);
						break;
					}
				}
			}
			if(!found) {
				System.out.println("User Not Found : Login Failure : 404");
			}
			
		}
		
		//store credentails
		public static void storeCredentials(String loggedInUser) {
			System.out.println("*******************************************");
			System.out.println("*					    *");
			System.out.println("*     Welcome to Digital Locker 	          *");
			System.out.println("*	  Store your Credentials here*");
			System.out.println("*******************************************");
			
			userCredentials.setLoggedInUser(loggedInUser);
			
			System.out.println("Enter Site Name :");
			String siteName = keyboard.next();
			userCredentials.setSiteName(siteName);
			
			System.out.println("Enter Username :");
			String username = keyboard.next();
			userCredentials.setUsername(username);
			
			System.out.println("Enter Password :");
			String password = keyboard.next();
			userCredentials.setPassword(password);
			
			lockerOutput.println(userCredentials.getLoggedInUser());
			lockerOutput.println(userCredentials.getSiteName());
			lockerOutput.println(userCredentials.getUsername());
			lockerOutput.println(userCredentials.getPassword());
			
			System.out.println("YOUR CREDS ARE STORED AND SECURED!");
			lockerOutput.close();		
		}
		
		//fetch credentials
		public static void fetchCredentials(String inpUsername) {
			System.out.println("*******************************************");
			System.out.println("*					*");
			System.out.println("*   Welcome to Digital Locker 	 *");
			System.out.println("*   Your Account Details	 *");
			System.out.println("*					*");
			System.out.println("*******************************************");
			System.out.println(inpUsername);
			
			
			while(lockerInput.hasNext()) {

				if(lockerInput.next().equals(inpUsername)) 
				{
					
							System.out.println("Site Name: "+lockerInput.next());
							System.out.println("User Name: "+lockerInput.next());
							System.out.println("User Password: "+lockerInput.next());
				}
					
									
				}
			}
		
		
		//delete credentials for specific user
		public static void removeCredentials(String inpUsername) {

			System.out.println("*******************************************");
			System.out.println("*					*");
			System.out.println("*   Welcome to Digital Locker  	 *");
			System.out.println("*   Deleting Ur Account Delatis 	 *");
			System.out.println("*					*");
			System.out.println("*******************************************");
			System.out.println(inpUsername);
			
			while(lockerInput.hasNext()) {

				if(lockerInput.next().equals(inpUsername)) 
				{
			
					System.out.println("Enter Site Name :");
					String siteName = "";
					userCredentials.setSiteName(siteName);
					
					System.out.println("Enter Username :");
					String username = "";
					userCredentials.setUsername(username);
					
					System.out.println("Enter Password :");
					String password = "";
					userCredentials.setPassword(password);
							
					lockerOutput.println(userCredentials.getSiteName());
					lockerOutput.println(userCredentials.getUsername());
					lockerOutput.println(userCredentials.getPassword());
				}
			}
			lockerOutput.close();
		}
				
		public static void initApp() {

			File  Usersdb = new File("Users_DB.txt");
			File  UserAccountdb = new File("UserAccounts_DB.txt");
			
			try {
				//read data from Users file
				input = new Scanner(Usersdb);
				
				//red data from UserAccount file
				lockerInput = new Scanner(UserAccountdb);
				
				//read data from keyboard
				keyboard = new Scanner(System.in);
				
				//out put 
				output = new PrintWriter( new FileWriter(Usersdb,true));
				lockerOutput = new PrintWriter( new FileWriter(UserAccountdb,true));
				
				users = new Users();
				userCredentials  = new UserCredentials();
				
				
			} catch (IOException e) {
				System.out.println("404 : File Not Found ");
			}
			
		}


}


