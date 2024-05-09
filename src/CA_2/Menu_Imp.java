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

//This class is the implementation of the whole menu of the program. Hence, we
//create a class called Menu_Imp (menu implementation)
public class Menu_Imp {
    //This is the creation of the main menu of the application as a method.
    //The main_menu methods accepts a scanner, two array lists and another
    //String as arguments)
    public static void main_menu (Scanner myKB, List<Person> people, List<Team> teams, String filename) {
        //This first variable method from class Method is created to be able to
        //call all the methods hosted inside the Method class (or file)
        Method method = new Method(); // Create an instance of the Method class
        //This next line creates the variable selectOption from the MenuOption
        //in the Menu class
        Menu.MenuOption selectOption = null;
        //This next do-while loop host the whole menu to repeat the prompt for
        //an option every time the user enters something different than EXIT.
        do {
            System.out.println("*****MAIN MENU*****");
            System.out.println("-------------------");
            System.out.println("Please Select an option: ");
            System.out.println("1. Sort people alphabetically by first name");
            System.out.println("2. Search people");
            System.out.println("3. Add a person to the list");
            System.out.println("4. Generate a random person");
            System.out.println("5. Exit the program");
            //The next while loop is to ask the user for a valid number every
            //time their enter something that is not a number (INT)
            while (!myKB.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                //With this line the scanner myKB is cleaned
                myKB.next();
            }
            //We create a new variable int to store the option from the keyboard
            //(myKB) of the user, which has to be an integer (nextInt)
            int option = myKB.nextInt();
            
            // Consume the newline character left in the buffer
            myKB.nextLine();  // Add this line to consume the newline character
            
            //If the option is less than 1 or more than the number of options of
            //the menu, a message asking for a right option is displayed on the
            //console
            if (option < 1 || option > MenuOption.values().length) {
                System.out.println("Please select one of the options available!");
            } else {
                //If the option is right, we have the next alternatives:
                //(By the way, selectOption stores the information of the
                //integer option less 1 (because de indexes consider 0 (:-))
                selectOption = Menu.MenuOption.values()[option - 1];
                switch (selectOption) {
                    case SORT_PEOPLE:
                        //In this case, the sortPeople method is executed
                        //followed by the printList method for the user to see
                        //an alphabetically arrange by first name number of
                        //individuals from a file. Only 20 people are displayed
                        //according to the requirements
                        method.sortPeople(people, 0, people.size() - 1);
                        method.printList(people);
                        break;
                    case SEARCH_PERSON:
                        //In this case, we prompt the user to enter the first name of
                        //the searched person. I could have add this to the method, but,
                        //it is going to be implemented in the next update.
                        System.out.println("\nPlease enter the first name of the player or coach"
                                + " you are looking for: ");
                        //The searchPeople method from the Method class (called thanks
                        //the method variable created above). This method accepts
                        //the array list people and the scanner myKB (keyboard)
                        //to be able to accept an input from the user and search
                        //in that array list (people)
                        method.searchPeople(people, myKB.nextLine()); // Call the method from the Method class
                        break;
                    case ADD_PERSON:
                        //This option calls the method addPerson, which adds a person
                        //with all their attributes (first name, last name, email, gender, team, coach or player,
                        //type of player (if player) or type of coach (if coach)
                        method.addPerson(people, teams); // Call the method from the Method class
                        break;
                    case GET_RANDOM_PERSON:
                        //With this method we can get a completely new and random person created from
                        //the data provided on the .txt file. That person is stored at the end of
                        //the list, respecting the "id" value
                        method.getRandomPerson(people, teams); // Call the method from the Method class
                        break;
                    case EXIT:
                        //If this option is chosen, the user exits the program, but before this, the
                        //next "Exiting the program..." is displayed
                        System.out.println("Exiting the program...");
                        break;
                }
            }
        //This while statement express the condition to repeate the do-while
        //every time the condition inside the parentheses is met. In this case,
        //the menu will be displayed every time the user does not enter
        //a number from 1 to 5.
        } while (selectOption != Menu.MenuOption.EXIT);
    }
}

