package CA_2;

import CA_2.Person;
import CA_2.Method;
import CA_2.Menu.MenuOption;
import java.util.Scanner;
import java.util.List;

/**
 * CA_2: Rugby Club System: Second assignment for the subject of Algorithms and 
 * Constructs of the Higher Diploma in Science in Computing of CCT College
 * Dublin, Ireland.
 * Lecturer: Kayoum Khbuli
 * @author Christopher Anich (Student number: 2023202)
 * 12-05-2024
 */
public class Menu_Imp {
    
    public static void main_menu (Scanner myKB, List<Person> people, List<Team> teams, String filename) {
        Scanner scanner = new Scanner(System.in);
        Method method = new Method(); // Create an instance of the Method class
        
        Menu.MenuOption selectOption = null;
        
        do {
            System.out.println("*****MAIN MENU*****");
            System.out.println("-------------------");
            System.out.println("Please Select an option: ");
            System.out.println("1. Sort people alphabetically by first name");
            System.out.println("2. Search people");
            System.out.println("3. Add a person to the list");
            System.out.println("4. Generate a random player");
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
                        method.sortPeople(people, 0, people.size() - 1);
                        method.printList(people);
                        break;
                    case SEARCH_PERSON:
                        System.out.println("\nPlease enter the first name of the player or coach"
                                + " you are looking for: ");
                        method.searchPeople(people, myKB.nextLine()); // Call the method from the Method class
                        break;
                    case ADD_PERSON:
                        method.addPerson(people, teams); // Call the method from the Method class
                        break;
                    case GET_RANDOM_PERSON:
                        method.getRandomPerson(people, teams, filename); // Call the method from the Method class
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

