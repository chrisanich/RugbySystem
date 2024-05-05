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
        //Execution of the methods
        menu.main_menu(myKB, people, teams, filename);
        
        
        //Finally, we close the user scanner to avoid leak of data
        myKB.close();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
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
            if (people.get(i).getFirstName().compareToIgnoreCase(people.get(j).getFirstName()) <= 0) {
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
    public void searchPeople(List<Person> people, String first_name) {
        boolean found = false;
        for (Person person : people) {
            if (person.getFirstName().equalsIgnoreCase(first_name)) {
                // Print the details of the found person
                if (!found) {
                    System.out.println("Person(s) found:");
                    found = true;
                }
                System.out.println("ID: " + person.getId());
                System.out.println("First Name: " + person.getFirstName());
                System.out.println("Last Name: " + person.getLastName());
                System.out.println("Email: " + person.getEmail());
                System.out.println("Gender: " + person.getGender());
                // Check if the person is assigned to a team
                if (person.getTeam() != null) {
                    System.out.println("Team: " + person.getTeam().getTeamName());
                }

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
            }
        }

        // If no matching people were found
        if (!found) {
            System.out.println("No person with first name '" + first_name + "' found.");
        }
    }

    
    ////////////////////////////////////////////////////////////////////////////
    public void addPerson(List<Person> people, List<Team> teams) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for First Name
        System.out.println("Enter the First Name of the person:");
        String first_name = scanner.nextLine();

        // Prompt for Last Name
        System.out.println("Enter the Last Name of the person:");
        String last_name = scanner.nextLine();

        // Prompt for Email
        String email;
        while (true) {
            System.out.println("Enter the Email of the person:");
            email = scanner.nextLine();
            if (isValidEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }

        // Prompt for Gender
        System.out.println("Enter the Gender of the person:");
        String gender = scanner.nextLine();

        // Generate ID
        int id;
        if (people.isEmpty()) {
            // If the list is empty, start the ID from 1001
            id = 1001;
        } else {
            // Otherwise, find the maximum ID and increment it by 1
            int maxId = 0;
            for (Person person : people) {
                if (person.getId() > maxId) {
                    maxId = person.getId();
                }
            }
            id = maxId + 1;
        }

        // Prompt for Team selection
        System.out.println("Select the Team:");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println((i + 1) + ". " + teams.get(i).getTeamName());
        }

        int teamIndex;
        while (true) {
            // Ensure the input is an integer
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid team number!");
                scanner.next(); // Consume the invalid input
            }
            teamIndex = scanner.nextInt();

            // Check if the input is within the valid range
            if (teamIndex >= 1 && teamIndex <= teams.size()) {
                break; // Valid input, exit the loop
            } else {
                System.out.println("Please enter a valid team number!");
            }
        }

        Team selectedTeam = teams.get(teamIndex - 1);

        // Prompt for Staff selection
        System.out.println("Select the kind of staff:");
        System.out.println("1. Player");
        System.out.println("2. Coach");
        int staffChoice = scanner.nextInt();
        if (staffChoice == 1) {
            // If Player
            System.out.println("Select the type of Player:");
            System.out.println("1. Promp");
            System.out.println("2. Hooker");
            System.out.println("3. Flanker");
            System.out.println("4. The 8-Man");
            System.out.println("5. Fullback");
            int playerTypeChoice = scanner.nextInt();
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
            System.out.println("Player added successfully.");
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
            int coachTypeChoice = scanner.nextInt();
            
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
            System.out.println("Coach added successfully.");
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
