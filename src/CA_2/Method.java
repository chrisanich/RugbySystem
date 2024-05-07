package CA_2;


import CA_2.Menu.CoachType;
import CA_2.Menu.PlayerType;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
//We import the Function interface from the util.fuction package. This allows
//us to use the apply() method. This, that will be explained later, is to be
//able to get an especific attribute from a Person, that the reason of
//Function<Person, String> attributeGetter to get random attributes for the
//"Generate Random People with Coach Types and Teams" option of the
//program.
import java.util.function.Function;

/**
 * CA_2: Rugby Club System: Second assignment for the subject of Algorithms and 
 * Constructs of the Higher Diploma in Science in Computing of CCT College
 * Dublin, Ireland.
 * Lecturer: Kayoum Khbuli
 * @author Christopher Anich (Student number: 2023202)
 * 12-05-2024
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
                //We create a new instansce of the Random class and call it
                //"random". With this we can get a random number.
                Random random = new Random();
                
                // Convert CoachType enum values to ArrayList
                ArrayList<CoachType> coachTypesList = new ArrayList<>(List.of(CoachType.values()));
                
                // Randomly select coach type
                CoachType randomCoachType = CoachType.values()[random.nextInt(CoachType.values().length)];
                // Convert CoachType enum values to ArrayList

                
                // Randomly select coach type
                PlayerType randomPlayerType = PlayerType.values()[random.nextInt(PlayerType.values().length)]; 
                
                //We randomly assign as player (70%) or coach (30%). This
                //values were considered because, without being a scholar, 
                //I understand that there are always more
                //players than coaches.
                boolean isPlayer = random.nextDouble() < 0.7;
                //Here, we create a new Person class instance called "person",
                //which we initialise below, inside the if statement.
                Person person;
                //Inside this if condition we determine if the person of the
                //loop belongs to the Player or Coach class.
                //Whether the person created belogs to Player or Coach class
                //person has a constructor that takes the parameters id, first_name, 
                //last_name, email and gender.
                //Therefore, this line initialises a new Person object with
                //the before-mentioned paramenters.
                if (isPlayer) {
                    person = new Player(id, first_name, last_name, email, gender, randomPlayerType);
                } else {
                    person = new Coach(id, first_name, last_name, email, gender, randomCoachType);
                }


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
        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        //Execution of the main_menu method, from where the whole program is
        //started (Menu_Imp class)
        menu.main_menu(myKB, people, teams, filename);
        
        
        //Finally, we close the user scanner to avoid leak of data
        myKB.close();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //In this case the algorith used is a merge method. In this case is named
    //sortPeople in honor of the name given in the requirements, but it
    //correspond to a merge sort method.
    //In this case, the method accepts three elements, the array list people
    //(which comes from the document Club_Form.txt), left and right, integers
    //that represents the boundaries of the sublists sorted, which are sorted
    //in this method.
    //This list splits the array list "people" into smaller parts, sorting each
    //part individually, and then putting them back together.
    //THe sortPeople method takes people array list, the int left (left boundary)
    //and int right (right boundary)
    public void sortPeople(List<Person> people, int left, int right) {
        //This if statement compares if "left" is less to "right", what means
        //that there are still parts to sort.
        if (left < right) {
            //We create a "mid" variable int to store the value of the
            //middle of the part which is sorted by using the next arithmetic
            //formula and will become part of the recursive method "sortPeople"
            int mid = (left + right) / 2;

            //To sort the left half: we need to recursively call the "sortPeople"
            //method, but this time, using mid as the right boundary. This "mid"
            //boundary will change every recursion until the left of the above
            //if statement is equal to the right one.
            sortPeople(people, left, mid); 
            //Sorting right half: we need to recursively call the "sortPeople"
            //method, but this time, using mid plus 1 as the left boundary.
            //BY adding 1, we ensure we start from the index immidiately after
            //the mid element, hence mid + 1.
            //This "mid" boundary will change every recursion until the left
            //of the above if statement is equal to the right one. 
            sortPeople(people, mid + 1, right);

            //After we have both small parts sorted, we merge them in a small
            //sublist by calling the "merge"method.
            //Basically, is to sort recursevely and and then merge to get
            //everything sorted.
            merge(people, left, mid, right);
        }
    }

    //THis is the "merge" method to merge the parts from the "sortPeople" method
    //It acceps three attributes, the array list people, the left boundary,
    //the right part of of the boundary and the always and recursively changing
    //mid variable.
    public void merge(List<Person> people, int left, int mid, int right) {
        //We create an array list called "temp" to temporarily inside the method
        //store the information of the class Person (lets remember that this
        //class has all the attibutes then inherited to the Coach and Player
        //classes.
        List<Person> temp = new ArrayList<>();
        //The "i" and "j" variables will track the current position in the left
        //and right halves of the assesed sublist
        //Here we instantiate a variable "i" to store the incoming left int.
        int i = left;
        //Here we instantiate a variable j to store the incoming mid int plus 1
        //to represent the next value after the middle
        int j = mid + 1;

        //THis will loop will continue always i and j are within their respective
        //halves of the sublist
        while (i <= mid && j <= right) {
            //At this line, we get the people at index "i", that is the one to
            //the most left of the sublist. We get its first name, wich comes
            //from the getFirst list of the Person class. We igonore case and
            //compare it with the first name of the person on "people" at index
            //"j" (look at the parenthesis at the right).
            //"0 >" checks if the comparison result is less than "0", indicating 
            //that the first name of the person at index "i" precedes the 1st 
            //name of the person at index "j" alphabetically.
            //If the condition is true, the Person object at index i from 
            //the left sublist is added to the temp list, and the i pointer 
            //is incremented.
            if (0 > people.get(i).getFirstName().compareToIgnoreCase(people.get(j).getFirstName())) {
                temp.add(people.get(i));
                i++;
            //If the condition is false (meaning the first name of the person at 
            //index j comes before or is equal to the first name of the person 
            //at index i alphabetically), the Person object at index j from the 
            //right sublist is added to the temp list, and the j pointer is 
            //incremented.
            } else {
                //This loop adds any remaining elements from the right half of 
                //the sublist to the temp list.
                temp.add(people.get(j));
                j++;
            }
        }

        // Add remaining elements from the left half
        while (i <= mid) {
            temp.add(people.get(i));
            i++;
        }

        //This loop adds any remaining elements from the left half of the 
        //sublist to the temp list.
        while (j <= right) {
            temp.add(people.get(j));
            j++;
        }

        //Let's copy sorted elements back to the original list. This loop adds 
        //any remaining elements from the right half of the sublist to the 
        //temp list.
        //Finally, this loop copies the sorted elements from the temp list back 
        //to the original people list, starting from index left. It iterates 
        //through the temp list, and for each element, it sets the corresponding 
        //element in the people list at position left + k.
        for (int k = 0; k < temp.size(); k++) {
            people.set(left + k, temp.get(k));
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    //The next method is to search people according to the input from the user.
    //THis method accepts the people array list and the string first_name,
    //which is the variable we are looking for.
    public void searchPeople(List<Person> people, String first_name) {
        //We create a new variable found of type boolean, which is initialised
        //with a false value and will help to track individuals during the 
        //search.
        boolean found = false;
        //THe next line corresponds to a for, which notation is possible thanks
        //the use of Person as a class and person as array list, but the meaning
        //is the same that the traditional one.
        for (Person person : people) {
            //IN the next line, there is a comparison between what is stored
            //in the person object of class Person and what was written by the
            //user, ignoring casee.
            if (person.getFirstName().equalsIgnoreCase(first_name)) {
                //Print the details of the "found" person, which was declared
                //"false" some lines ago, so, if a matching person is found and
                //it's  the first match (found is still false), it prints a 
                //message indicating that that person(s) was found and updates
                //the found variable to true.
                if (!found) {
                    System.out.println("Person(s) found:");
                    found = true;
                }
                //Here, the details of the person found are displayed to the
                //user on the command line.
                System.out.println("ID: " + person.getId());
                System.out.println("First Name: " + person.getFirstName());
                System.out.println("Last Name: " + person.getLastName());
                System.out.println("Email: " + person.getEmail());
                System.out.println("Gender: " + person.getGender());
                //If the person was assigned to a tean, a message is displayed
                //indicating it.
                if (person.getTeam() != null) {
                    System.out.println("Team: " + person.getTeam().getTeamName());
                }

                //If the person was assinged as a player (Staff type), its type 
                //is display along the type of coach as well
                if (person instanceof Player) {
                    System.out.println("Staff: Player");
                    // Display player type for players
                    System.out.println("Player Type: " + ((Player) person).getPlayerType());
                //If the person is not a Player, but an instance of the Coach
                //class, its type is display with the type of coach as well
                } else if (person instanceof Coach) {
                    System.out.println("Staff: Coach");
                    //We display coach type for coaches
                    System.out.println("Coach Type: " + ((Coach) person).getCoachType());
                }
                //We print an empty line only for aesthetical purposes
                System.out.println();
            }
        }

        //THis last line checks if the if statement finds a false found, what
        //means that no matching people were found
        if (!found) {
            System.out.println("No person with first name '" + first_name + "' found.");
        }
    }

    
    ////////////////////////////////////////////////////////////////////////////
    //When adding a person, we use this method, which takes two parameters,
    //the people array list and the teams array list.
    public void addPerson(List<Person> people, List<Team> teams) {
        //In this line we create a new scanner for the user to enter their
        //choices
        Scanner myKB = new Scanner(System.in);

        //Here, we prompt the user to enter the first name of the person their
        //are adding to the list
        System.out.println("Enter the First Name of the person:");
        String first_name = myKB.nextLine();

        //Here, we prompt the user to enter the last name of the person their
        //are adding to the list
        System.out.println("Enter the Last Name of the person:");
        String last_name = myKB.nextLine();

        //Here, we prompt the user to enter the email of the person their
        //are adding to the list
        String email;
        while (true) {
            System.out.println("Enter the Email of the person:");
            email = myKB.nextLine();
            if (isValidEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }

        //Here, we prompt the user to enter the gender of the person their
        //are adding to the list
        System.out.println("Enter the Gender of the person:");
        String gender = myKB.nextLine();

        //This is the first step to add a proper "id to the new individual".
        //We define the variable id, but do not declare it.
        int id;
        //THis if statement is to add the next available id number to the first 
        //individual after the last one of the provided list in the .txt file.
        if (people.isEmpty()) {
            //If the list is empty, start the ID from 1001
            id = 1001;
        } else {
            //Otherwise, find the maximum ID and increment it by 1
            int maxId = 0;
            //In this for loop, we iterate getting every ID until the one found
            //is equal or less than the maxId, which was intanstiate as 0 in the
            //previous line of comments.
            for (Person person : people) {
                if (person.getId() > maxId) {
                    maxId = person.getId();
                }
            }
             //And here, we increment maxId by 1 in every iteration, so, there is
            //going to be a point when maxId will be equal to "person.getId()"
            //and the for loop will stop, giving way to the next line of code.
            id = maxId + 1;
        }

        //In the next lines we ask the user to select a team from the 
        //list and display this one along with their 
        //indices.
        System.out.println("Select the Team:");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println((i + 1) + ". " + teams.get(i).getTeamName());
        }
        //The variable "teamIndex" is define outside the while loop. I use to do
        //this to avoid problems if I need this variable further. Sometimes,
        //I understand the scope of the variable and leave it inside the method
        //or condition, but this is not the case.
        int teamIndex;
        //This next block of code ensures that the user enters a valid team 
        //number by repeatedly prompting until a valid integer within the range 
        //of available teams is provided.
        while (true) {
            //The next statement will be executed as long as the user enters an
            //integer through myKB(keyboard input).
            while (!myKB.hasNextInt()) {
                System.out.println("Please enter a valid team number!");
                myKB.next(); // Consume the invalid input
            }
            teamIndex = myKB.nextInt();

            //If the input is an integer, it checks if it falls within the valid 
            //range (1 to the size of the teams list (team.size()).
            //If the input is valid, it breaks out of the loop.
            if (teamIndex >= 1 && teamIndex <= teams.size()) {
                break; //We got a valid input, so we exit the loop.
            } else {
                //Otherwise, we get an error print
                System.out.println("Please enter a valid team number!");
            }
        }
        //This next line retrieves the selected team from 
        //the "teams" list based on the index entered by the user. The 
        //substraction of 1, as always is to get the correct index in the list.
        Team selectedTeam = teams.get(teamIndex - 1);

        //Here, we prompt the user to choose between adding a Player or a Coach
        System.out.println("Select the kind of staff:");
        System.out.println("1. Player");
        System.out.println("2. Coach");
        //THis next int will store the choice of the user, though, it is still
        //not initialised. Anyway, this keeps it out of the scope of the next
        //while loop.
        int staffChoice;
        
        while (true) {
            //THis is an error message in case the user enters a value that is
            //not an integer.
            while (!myKB.hasNextInt()) {
                System.out.println("Please enter a valid choice!");
                myKB.next(); // Consume the invalid input
            }
            //staffChoise was defined only few line above, now we assign the 
            //input from the user to it an conditionate it in the following 
            //if statement.
            staffChoice = myKB.nextInt();

            //If the input from the user is an integer, we check if it 
            //corresponds to a valid choice (1 for player or 2 for coach).
            if (staffChoice == 1 || staffChoice == 2) {
                break; //If th input is valid, we break the loop.
            } else {
                //If the input is not valid, we display an error message asking
                //for a valid inñut.
                System.out.println("Please enter a valid choice!");
            }
        }
        if (staffChoice == 1) {
            // If Player
            System.out.println("Select the type of Player:");
            System.out.println("1. Promp");
            System.out.println("2. Hooker");
            System.out.println("3. Flanker");
            System.out.println("4. The 8-Man");
            System.out.println("5. Fullback");
            int playerTypeChoice = myKB.nextInt();
            PlayerType playerType = null;
            switch (playerTypeChoice) {
                case 1:
                    playerType = PlayerType.PROMP;
                    break;
                case 2:
                    playerType = PlayerType.HOOKER;
                    break;
                case 3:
                    playerType = PlayerType.FLANKER;
                    
                case 4:
                    playerType = PlayerType.THE_8_MAN;
                    break;
                case 5:
                    playerType = PlayerType.FULLBACK;
                    break;
                default:
                    System.out.println("Invalid choice, setting to Hooker.");
                    playerType = PlayerType.HOOKER;
                    break;
            }
            Person person = new Player(id, first_name, last_name, email, gender, playerType);
            person.assignTeam(selectedTeam);
            people.add(person);
            System.out.println(first_name + " " + last_name + " has been added as \"" + playerType + "\" to " + "\"" + selectedTeam.getTeamName() + "\" successfully.");
            // Display coach information
            displayPersonInformation(person);
        } else if (staffChoice == 2) {
            // If Coach
            System.out.println("Select the type of Coach:");
            System.out.println("1. Head Coach");
            System.out.println("2. Assistant Coach");
            System.out.println("3. Assistant Forwards Coach");
            System.out.println("4. Academy Fordwards Coach");
            System.out.println("5. Scrum Coach");
            int coachTypeChoice = myKB.nextInt();
            
            ////////////////////////////////////////////////////////////////////
            CoachType coachType = null;
            switch (coachTypeChoice) {
                case 1:
                    coachType = CoachType.HEAD_COACH;
                    break;
                case 2:
                    coachType = CoachType.ASSISTANT_COACH;
                    break;
                case 3:
                    coachType = CoachType.ASSISTANT_FORWARDS_COACH;
                    
                case 4:
                    coachType = CoachType.ACADEMY_FORWARDS_COACH;
                    break;
                case 5:
                    coachType = CoachType.SCRUM_COACH;
                    break;
                default:
                    System.out.println("Invalid choice, setting to Assistant Coach.");
                    coachType = CoachType.ASSISTANT_COACH;
                    break;
            }
            Person person = new Coach(id, first_name, last_name, email, gender, coachType);
            person.assignTeam(selectedTeam);
            people.add(person);
            System.out.println(first_name + " " + last_name + " has been added as \"" + coachType + "\" to " + "\"" + selectedTeam.getTeamName() + "\" successfully.");
            // Display coach information
            displayPersonInformation(person);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // Helper method to display person information
    private void displayPersonInformation(Person person) {
        System.out.println("ID: " + person.getId());
        System.out.println("First Name: " + person.getFirstName());
        System.out.println("Last Name: " + person.getLastName());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Gender: " + person.getGender());
        System.out.println("Team: " + person.getTeam().getTeamName());
        if (person instanceof Player) {
            System.out.println("Staff: Player");
            // Display player type for players
            System.out.println("Player Type: " + ((Player) person).getPlayerType());
        } else if (person instanceof Coach) {
            System.out.println("Staff: Coach");
            // Display coach type for coaches
            System.out.println("Coach Type: " + ((Coach) person).getCoachType());
        }
        System.out.println();
    }

    // Helper method to validate email format
    private boolean isValidEmail(String email) {
        // Email format pattern with dot after '@'
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$";
        // Check if the email matches the pattern
        return email.matches(emailPattern);
    }

////////////////////////////////////////////////////////////////////////////////

    public void getRandomPerson(List<Person> people, List<Team> teams, String filename) {
        Random random = new Random();

        // Ensure there's at least one person to add
        if (people.isEmpty()) {
            System.out.println("The list is empty. Please add some people before generating a random person.");
            return;
        }

        // Get a random person from the existing list
        Person randomPerson = people.get(random.nextInt(people.size()));

        // Generate a new ID for the new person
        int newId = people.stream().mapToInt(Person::getId).max().orElse(0) + 1;

        // Randomly select attributes from existing people
        String firstName = getRandomAttribute(people, Person::getFirstName);
        String lastName = getRandomAttribute(people, Person::getLastName);
        String email = getRandomAttribute(people, Person::getEmail);
        String gender = getRandomAttribute(people, Person::getGender);

        // Generate random coach type for coaches
        CoachType randomCoachType = CoachType.values()[random.nextInt(CoachType.values().length)];

        // Create the individual
        Person person;
        boolean isPlayer = random.nextDouble() < 0.7; // 70% chance of being a player
        if (isPlayer) {
            PlayerType randomPlayerType = PlayerType.values()[random.nextInt(PlayerType.values().length)]; // Generate random player type
            person = new Player(newId, firstName, lastName, email, gender, randomPlayerType); // Set the random player type
        } else {
            person = new Coach(newId, firstName, lastName, email, gender, randomCoachType);
        }

        // Assign the individual to a random team
        Team randomTeam = teams.get(random.nextInt(teams.size()));
        person.assignTeam(randomTeam);

        // Add the individual to the list of people
        people.add(person);

        // Print the information of the newly added person
        System.out.println("New person added:");
        System.out.println("ID: " + person.getId());
        System.out.println("First Name: " + person.getFirstName());
        System.out.println("Last Name: " + person.getLastName());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Gender: " + person.getGender());
        System.out.println("Team: " + person.getTeam().getTeamName());
        if (person instanceof Player) {
            System.out.println("Staff: Player");
            // Display player type for players
            System.out.println("Player Type: " + ((Player) person).getPlayerType());
        } else if (person instanceof Coach) {
            System.out.println("Staff: Coach");
            System.out.println("Coach Type: " + ((Coach) person).getCoachType());
        }
        System.out.println();
    }

    // Helper method to get a random attribute from existing people
    private String getRandomAttribute(List<Person> people, Function<Person, String> attributeGetter) {
        Random random = new Random();
        // Get a random person from the list
        Person randomPerson = people.get(random.nextInt(people.size()));
        // Get the specified attribute from the random person
        return attributeGetter.apply(randomPerson);
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

            // Add label indicating player or coach
            if (person instanceof Player) {
                System.out.println("Staff: Player");
                // Display player type for players
                System.out.println("Player Type: " + ((Player) person).getPlayerType());
            } else if (person instanceof Coach) {
                System.out.println("Staff: Coach");
                // Display coach type for coaches
                System.out.println("Coach Type: " + ((Coach) person).getCoachType());
            }

            System.out.println();
            counter++; //Here the counter adds 1 every loop.
        }
    }
}
