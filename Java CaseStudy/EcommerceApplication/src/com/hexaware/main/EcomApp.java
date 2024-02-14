/**
 * This is an  E-Commerce Application
 * 
 * @author Srishti R
 * @version 1.0
 * @since 2024-01-20
 */
package com.hexaware.main;
import com.hexaware.controller.EcomAppController;
public class EcomApp {
	/**
	 * Here stands the main class which describes the functionalities in the application.
	 * There is MainMenu with four managements
	 *  1.Customer  2.product  3.order 4.Cart 
	 */

    public static void main(String[] args) {
    	System.out.println("\033[1;35m<<<<<<<<<<<<<<<<<<<<<<<<<<!WELCOME TO ECOMMERCE APPLICATION!>>>>>>>>>>>>>>>>>>>>>>>>>>\033[0m");
        System.out.println("\033[1;34m ------------------------------------------------------------------------------------------------------------------------------------------------\033[0m");
        System.out.println("\033[1;31m=================================CASE STUDY=============================================\033[0m");
        System.out.println("\033[1;32m()())()))()))))(((((())()()()()()()()()()()()()()()())))))))()())()()()()()()()()()())))))))(((((((((((((()()()()()()()()()()))))))((((()()()()()\033[0m");
        EcomAppController.startApp();
       
}
    public static void displayMainMenu()
    {
    	System.out.println("\033[1;33mMain Menu:\033[0m");
        System.out.println("\033[1;36m1. Customer Management\033[0m");
        System.out.println("\033[1;35m2. Product Management\033[0m");
        System.out.println("\033[1;33m3. Cart Management\033[0m");
        System.out.println("\033[1;34m4. Order Management\033[0m");
        System.out.println("\033[1;31m0. Exit\033[0m");
        System.out.print("\033[1;32mEnter your choice: \033[0m");    
    }
}