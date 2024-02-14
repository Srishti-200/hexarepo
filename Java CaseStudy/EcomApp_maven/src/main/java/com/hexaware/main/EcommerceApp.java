/**
 * This is an E-Commerce Application
 * 
 * @author Srishti R
 * @version 1.0
 * @since 2024-01-20
 */
package com.hexaware.main;

import com.hexaware.controller.EcommerceAppController;

public class EcommerceApp {
    
    /**
     * Here stands the main class which describes the functionalities in the application.
     * There is MainMenu with four managements
     *  1.Customer  2.product  3.order 4.Cart 
     */
    public static void main(String[] args) {
        System.out.println("\033[1;35m<<<<<<<<<<<<<<<<<<<<<<<<<<!WELCOME TO ECOMMERCE APPLICATION!>>>>>>>>>>>>>>>>>>>>>>>>>>\033[0m");
        System.out.println("\033[1;34m ------------------------------------------------------------------------------------------------------------------------------------------------\033[0m");
        System.out.println("\033[1;31m=================================CASE STUDY=============================================\033[0m");
        System.out.println("\033[1;32m()())()))()))))(((((())()()()()()()()()()()()()()())))))))()())()()()()()()()()()())))))))(((((((((((((()()()()()()()()()()))))))((((()()()()()\033[0m");
        EcommerceAppController.startApp();
    }
    
    public static void displayMainMenu() {
        System.out.println("\033[1;33mMain Menu:\033[0m");
        System.out.println("\033[1;36mCustomer Management\033[0m");
        System.out.println("\033[1;35mProduct Management\033[0m");
        System.out.println("\033[1;33mCart Management\033[0m");
        System.out.println("\033[1;34mOrder Management\033[0m");
        System.out.println("\033[1;31m0. Exit\033[0m");
        System.out.print("\033[1;32mEnter your choice: \033[0m");
    }
}
