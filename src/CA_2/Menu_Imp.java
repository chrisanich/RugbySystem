/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.util.Scanner;
import CA_2.Menu.MenuOption;
import CA_2.Method;

/**
 *
 * @author chrisanich
 */
public class Menu_Imp {
    
    public static void main_menu (Scanner myKB) {
        Scanner scanner = new Scanner(System.in);
        Method method = new Method(); // Create an instance of the Method class
        
        Menu.MenuOption selectOption = null;
        
        do {
            System.out.println("*****MAIN MENU*****");
            System.out.println("-------------------");
            System.out.println("Please Select an option: ");
            System.out.println("1. Sort people alphabetically by last name");
            System.out.println("2. Search people");
            System.out.println("3. Add a person to the list");
            System.out.println("4. Get a random person from the list");
            System.out.println("5. Exit the program");
        
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                scanner.next();
            }

            int option = scanner.nextInt();
            
            if (option < 1 || option > MenuOption.values().length) {
                System.out.println("Please select from one of the options available!");
            } else {
                selectOption = Menu.MenuOption.values()[option - 1];
                switch (selectOption) {
                    case SORT_PEOPLE:
                        method.sortPeople(); // Call the method from the Method class
                        break;
                    case SEARCH_PERSON:
                        method.searchPeople(); // Call the method from the Method class
                        break;
                    case ADD_PERSON:
                        method.addPerson(); // Call the method from the Method class
                        break;
                    case GET_RANDOM_PERSON:
                        method.getRandomPerson(); // Call the method from the Method class
                        break;
                    case EXIT:
                        System.out.println("Exiting Program..");
                        break;
                    default:
                        System.out.println("Invalid option please select again! ");
                }
            }
        } while (selectOption != Menu.MenuOption.EXIT);
    }
}

