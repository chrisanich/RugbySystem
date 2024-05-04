package CA_2;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author chrisanich
 */
public class Method {
    
    ////////////////////////////////////////////////////////////////////////////

    public void readFile() {
        Menu_Imp menu = new Menu_Imp();
        //First step is to create a Scanner to read input from the user's
        //keyboard.
        Scanner myKB = new Scanner(System.in);
        
        //We create an instance of the interface to allow us
        RugbySystem rugby = new RugbySystem();
        
        //Since the number options in the enums 
        //is indexed from the 0 position 
        //Therefor we need to create a way to deduct user input from -1 to get a 0
        Menu.MenuOption selectOption = null;
         
        //We prompt the user to write the name of a file .txt to read.
        System.out.println("Please enter a filename to read: ");
        //////////
        //////////
        //////////
        //////////
        //////////
        //Replace this for delivery
        //String filename = myKB.nextLine();
String filename = "Club_Form.txt";
        
        //Now, we create an array list wich will store every Person (Class).
        //This will help us organise every line of data of our file in a
        //different index of the list.
        List<Person> people = new ArrayList<>();
        
        //Let's create team names from the class Team (in the same project)
        Team team01 = new Team("Colo-Colo");
        Team team02 = new Team("Universidad de Chile");
        Team team03 = new Team("Universidad Católica");
        Team team04 = new Team("O'Higgins de Rancagua");
        
        //Now, we create an array list wich will store every Team (Class).
        List<Team> teams = new ArrayList<>();
        teams.add(team01);
        teams.add(team02);
        teams.add(team03);
        teams.add(team04);
        
        //We instantiate a variable int count to be able to
        //int count = 0;
        
        //The next try catch contains a Scanner readFileLines, which stores the
        //information recovered by the FileReader from the text (.txt file)
        try (Scanner readFileLines = new Scanner(new FileReader(filename))) {
            //This allows us to start reading from the 2nd line of the file, 
            //considering that it contains the titles or description of every
            //column of data
            readFileLines.nextLine();
            
            //We create line string to read every line as a whole, to store it
            //As a String and then split it by using the split() method from
            //Java libraries using "," as argument (the separator of csv files)
            String line;
            //This while loop will iterate until there is no more lines to read
            while (readFileLines.hasNextLine()) {
                //Here we use the before mention String line
                line = readFileLines.nextLine();
                //This array is created to store every attibute from our Person
                //class in a different index, until the is no more to read on
                //the line. Which are separated by "," according to the split() 
                //method.
                //There exist only 5 elements for line, what we aready know, so, 
                //each one was assigned to a different index, from 0 to 4.
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String first_name = parts[1];
                String last_name = parts[2];
                String email = parts[3];
                String gender = parts[4];
                //Here, we create a new Person class instance, which at the same
                //type has a constructor that takes the parameters id, first_name, 
                //last_name, email and gender.
                //Therefore, this line initialises a new Person object with
                //the before-mentioned paramenters.
                Person person = new Person(id, first_name, last_name, email, gender);
                
                //We create a new instansce of the Random class and call it
                //"random". With this we can get a random number.
                Random random = new Random();
                //"random.nextInt(teams.size())" takes the size of teams, that in
                //this case is 4 (4 created teams) and through the random
                //instance, gets a random number between 0 (inclusive) and the
                //size of "teams", which in this case is 4.
                //Afterwards, "teams.get(random.nextInt(teams.size()))" gets
                //the team at the randomly generated index from the list and
                //stores it as a Team intance called randomTeam.
                Team randomTeam = teams.get(random.nextInt(teams.size()));
                //Next, this randomTeam variable (which, by the way, is a class
                //in the program) is taken as an argument of the assignTeam
                //method hosted on the Person class code.
                person.assignTeam(randomTeam);
                
                
                //And finally, we add this "person" to the people array list.
                people.add(person);
            }
            
            //We confirm that the file was read successfully
            System.out.println("\nFile read successfully...\n");
            
        } catch (IOException e) {
            System.out.println("Filename incorrect. Please enter a valid one...");
        }
        
        //Let's print the contents of the 'people' array list
        /*
        for (Person person : people) {
            System.out.println("ID: " + person.getId());
            System.out.println("First Name: " + person.getFirstName());
            System.out.println("Last Name: " + person.getLastName());
            System.out.println("Email: " + person.getEmail());
            System.out.println("Gender: " + person.getGender());
            System.out.println("Team: " + person.getTeam().getTeamName()); //Display the assigned team name
            System.out.println();
        }
        */
        
        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        //Execution of the methods
        menu.main_menu(myKB, people);
        
        
        //Finally, we close the user scanner to avoid leak of data
        myKB.close();
    }
    
    public void sortPeople(List<Person> people, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Sort the left half
            sortPeople(people, left, mid);
            // Sort the right half
            sortPeople(people, mid + 1, right);

            // Merge the sorted halves
            merge(people, left, mid, right);
        }
    }

    public void merge(List<Person> people, int left, int mid, int right) {
        List<Person> temp = new ArrayList<>();
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            // Compare the last names of Person objects
            if (people.get(i).getLastName().compareToIgnoreCase(people.get(j).getLastName()) <= 0) {
                temp.add(people.get(i));
                i++;
            } else {
                temp.add(people.get(j));
                j++;
            }
        }

        // Add remaining elements from the left half
        while (i <= mid) {
            temp.add(people.get(i));
            i++;
        }

        // Add remaining elements from the right half
        while (j <= right) {
            temp.add(people.get(j));
            j++;
        }

        // Copy sorted elements back to the original List
        for (int k = 0; k < temp.size(); k++) {
            people.set(left + k, temp.get(k));
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public void searchPeople(List<Person> people, String last_name) {
        for (Person person : people) {
            if (person.getLastName().equalsIgnoreCase(last_name)) {
                // Print the details of the found person
                System.out.println("Person found:");
                System.out.println("ID: " + person.getId());
                System.out.println("First Name: " + person.getFirstName());
                System.out.println("Last Name: " + person.getLastName());
                System.out.println("Email: " + person.getEmail());
                System.out.println("Gender: " + person.getGender());
                System.out.println();
                return; // Exit the method after finding the first matching person
            }
        }

        // If the loop completes without finding the person
        System.out.println("Person with last name '" + last_name + "' not found.");
    }
    
    ////////////////////////////////////////////////////////////////////////////
    private int nextId = 1; // Next available ID
    public void addPerson(List<Person> people) {
    Scanner scanner = new Scanner(System.in);

    // Prompt for First Name
    System.out.println("Enter the First Name of the person:");
    String first_name = scanner.nextLine();

    // Prompt for Last Name
    System.out.println("Enter the Last Name of the person:");
    String last_name = scanner.nextLine();

    // Prompt for Email
    System.out.println("Enter the Email of the person:");
    String email = scanner.nextLine();

    // Prompt for Gender
    System.out.println("Enter the Gender of the person:");
    String gender = scanner.nextLine();

    // Generate ID
    int id;
    if (people.isEmpty()) {
        // If the list is empty, start the ID from 1
        id = 1;
    } else {
        // Otherwise, increment the ID of the last person by 1
        id = people.get(people.size() - 1).getId() + 1;
    }

    // Create a new Person object
    Person person = new Person(id, first_name, last_name, email, gender);

    // Add the person to the list of people
    people.add(person);

    System.out.println("Person added successfully.");
    }
    
    ////////////////////////////////////////////////////////////////////////////

    public void getRandomPerson(List<Person> people) {
        Random random = new Random();
        int randomIndex = random.nextInt(people.size());
        Person randomPerson = people.get(randomIndex);
        // Print the details of the randomly selected person
        System.out.println("Random person:");
        System.out.println("ID: " + randomPerson.getId());
        System.out.println("First Name: " + randomPerson.getFirstName());
        System.out.println("Last Name: " + randomPerson.getLastName());
        System.out.println("Email: " + randomPerson.getEmail());
        System.out.println("Gender: " + randomPerson.getGender());
        System.out.println("Team: " + randomPerson.getTeam().getTeamName());
        System.out.println();
}
    
    ////////////////////////////////////////////////////////////////////////////

    public void printList(List<Person> people) {
        //We create a counter i to stop the loop and display only 20 records.
        int counter = 0;
        for (Person person : people) {
            if (counter >= 20) {
                break; //Here we break or exit the loop if the counter gets 20
            }
            System.out.println("ID: " + person.getId());
            System.out.println("First Name: " + person.getFirstName());
            System.out.println("Last Name: " + person.getLastName());
            System.out.println("Email: " + person.getEmail());
            System.out.println("Gender: " + person.getGender());
            System.out.println("Team: " + person.getTeam().getTeamName()); // Display the assigned team name
            System.out.println();
            counter++; //Here the counter adds 1 every loop.
        }
    }
}
