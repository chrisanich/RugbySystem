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
         
        //We prompt the user to write the name of a file .txt to read.
        System.out.println("Please enter a filename to read: ");
        //////////
        //////////
        //////////
        //////////
        //////////
        //Replace this for delivery
        String filename = myKB.nextLine();
        //String filename = "Club_Form.txt";
        
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
    public List<Person> sortPeople(List<Person> people, int left, int right) {
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
        return people;
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
    //It is a binary search algorithm that takes as attribute the array list
    //"people" and a string.
    //It is important to highlight that the array list was already sorted when
    //the search option was chosen by calling the sort method in the Menu_Imp
    //java file, just before the searching method.
    //So, this line defines the searchPeople method, which takes 2 arguments,
    //a "people" ´Person class array list (database) and a string first name,
    //which is coming from the input of the user.
    public void searchPeople(List<Person> people, String first_name) {
        //We initialise variables for binary search
        //"low" is set to 0 to indicate the lowest index of the search
        int low = 0;
        //high is the highest index on the search, so it correspond to the size of
        //the people array list less 1 (because the inclusion of 0 as index)
        int high = people.size() - 1;
        //This boolean variable found is false until the value is found
        boolean found = false;

        //In this binary while loop, we iterate while low is not less than 
        //high.
        while (low <= high) {
            //In this line we calculate the middle index of current range (in the
            //while loop) and call it mid (integer)
            int mid = low + (high - low) / 2;
            //Then, the person at that index is retrieve from people.
            Person person = people.get(mid); // Get person at mid index

            //Here, we create an int variable to comparison, because by calling
            //compareToIgnoreCase we can get a result 0 in case the strings are
            //equal so, we can go to the next if statement and print al the data
            //of the player if found.
            int comparison = person.getFirstName().compareToIgnoreCase(first_name);

            //If first name matches, we print that the person was found and set 
            //"found" boolean variable to true
            if (comparison == 0) {
                if (!found) {
                    System.out.println("Person(s) found:");
                    found = true;
                }
                System.out.println("ID: " + person.getId());
                System.out.println("First Name: " + person.getFirstName());
                System.out.println("Last Name: " + person.getLastName());
                System.out.println("Email: " + person.getEmail());
                System.out.println("Gender: " + person.getGender());
                if (person.getTeam() != null) {
                    System.out.println("Team: " + person.getTeam().getTeamName());
                }
                if (person instanceof Player) {
                    System.out.println("Staff: Player");
                    System.out.println("Player Type: " + ((Player) person).getPlayerType());
                } else if (person instanceof Coach) {
                    System.out.println("Staff: Coach");
                    System.out.println("Coach Type: " + ((Coach) person).getCoachType());
                }
                System.out.println();
                // Move low and high pointers to continue binary search
                low = mid + 1;
                high = mid - 1;
            }
            //If the comparison result is less than 0 ("compareToIgnoreCase"), 
            //it means the first_name being searched for comes after the Person 
            //object's first name in lexicographic order. So, it adjusts the low 
            //pointer to search the right half of the current range.
            else if (comparison < 0) {
                low = mid + 1;
            }
            //Now, if the comparison result is greater than 0, it means the 
            //first_name being searched for comes before the Person object's 
            //first name in lexicographic order. So, it adjusts the high pointer 
            //to search the left half of the current range.
            else {
                high = mid - 1;
            }
        }
        //If the person is not found, we print the next message.
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
        //are adding to the list. This input will be checked inside this while
        //loop by calling the "isValidEmail" method created below, which returns
        //a boolean, which only allows to continue with the addPerson method if
        //the input meets the requirements of an email, as it is seen afterwards.
        String email;
        while (true) {
            System.out.println("Enter the Email of the person:");
            email = myKB.nextLine();
            //Here is the call to the "isValidEmail"
            if (isValidEmail(email)) {
                break;
            } else {
                //In case the input is not valid as an email, the user is
                //prompted to write again.
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
        
        //This is not the best practice, to have a true statement inside a while
        //condition, but here it meets what we need, to check if the user
        //entered an option.
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
        //This is in case the user chooses Player (option 1)
        if (staffChoice == 1) {
            //We prompt the user to choose among the next 5 types of players
            System.out.println("Select the type of Player:");
            System.out.println("1. Promp");
            System.out.println("2. Hooker");
            System.out.println("3. Flanker");
            System.out.println("4. The 8-Man");
            System.out.println("5. Fullback");
            //We create the next int variable to store the option, which will
            //be assessed in the next switch case statement. This stores a
            //number from the scanner "myKB (System.in)"
            int playerTypeChoice = myKB.nextInt();
            //We initialise the variable "playerType" of class PlayerType to null
            //store the chosen type of player chosen later on.
            PlayerType playerType = null;
            //This is the switch case where the user enters their option and
            //selects one of the next alternatives.
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
                //In case the user does not chooses a write option, the player
                //is assigned automatically to the team "Colo-Colo". This is
                //because it was decided that every player must belong to a team.
                default:
                    System.out.println("Invalid choice, setting to Hooker.");
                    playerType = PlayerType.HOOKER;
                    break;
            }
            //We create a variable "person" of "Person" class to store a new
            //player with all their attributes.
            Person person = new Player(id, first_name, last_name, email, gender, playerType);
            //This person is assigned the selected team through the method
            //assignTeam from the Person class.
            person.assignTeam(selectedTeam);
            //In the next line we assigned the new person to the people array
            //list, which stores all the information provided initially by the
            //provided .txt document attached to the project.
            people.add(person);
            //In this line the programs says the user that the person was added
            //succesfully for the user to check out that the information
            //was properly added.
            System.out.println(first_name + " " + last_name + " has been added as \"" + playerType + "\" to " + "\"" + selectedTeam.getTeamName() + "\" successfully.");
            //In this next line, we simply print the all the data or attributes of
            //our added new person for the user to re-check out visually that the 
            //information was properly added. For this, we call the 
            //"displayPersonInformation" method, that is the last of this Method
            //class.
            displayPersonInformation(person);
            
          //This is in case the user chooses Coach (option 2)
        } else if (staffChoice == 2) {
            //We prompt the user to choose among the next 5 types of coach
            System.out.println("Select the type of Coach:");
            System.out.println("1. Head Coach");
            System.out.println("2. Assistant Coach");
            System.out.println("3. Assistant Forwards Coach");
            System.out.println("4. Academy Fordwards Coach");
            System.out.println("5. Scrum Coach");
            //We create the next int variable to store the option, which will
            //be assessed in the next switch case statement. This stores a
            //number from the scanner "myKB (System.in)"
            int coachTypeChoice = myKB.nextInt();
            //We initialise the variable "coachType" of class CoachType to null
            //store the chosen type of coach chosen later on.
            CoachType coachType = null;
            //This is the switch case where the user enters their option and
            //selects one of the next alternatives.
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
                //In case the user does not chooses a write option, the coach
                //is assigned automatically to the team "Colo-Colo". This is
                //because it was decided that every coach must belong to a team.    
                default:
                    System.out.println("Invalid choice, setting to Assistant Coach.");
                    coachType = CoachType.ASSISTANT_COACH;
                    break;
            }
            //We create a variable "person" of "Person" class to store a new
            //coach with all their attributes.
            Person person = new Coach(id, first_name, last_name, email, gender, coachType);
            //This person is assigned the selected team through the method
            //assignTeam from the Person class.
            person.assignTeam(selectedTeam);
            //In the next line we assigned the new person to the people array
            //list, which stores all the information provided initially by the
            //provided .txt document attached to the project.
            people.add(person);
            //In this line the programs says the user that the person was added
            //succesfully for the user to check out that the information
            //was properly added.
            System.out.println(first_name + " " + last_name + " has been added as \"" + coachType + "\" to " + "\"" + selectedTeam.getTeamName() + "\" successfully.");
            //In this next line, we simply print the all the data or attributes of
            //our added new person for the user to re-check out visually that the 
            //information was properly added. For this, we call the 
            //"displayPersonInformation" method, that is the last of this Method
            //class.
            displayPersonInformation(person);
        } else {
            //This is printed in case the user enters an invalid option.
            System.out.println("Invalid choice.");
        }
    }

    //This method is created to display the information of the added person.
    //It could have been inside the addPerson method, but the choice of printing
    //in an apart method was to keep the code more organised.
    //This method takes a person variable as an attribute or parameter.
    private void displayPersonInformation(Person person) {
        //The method prints every data from every person through the "getter"
        //from the class Person
        System.out.println("ID: " + person.getId());
        System.out.println("First Name: " + person.getFirstName());
        System.out.println("Last Name: " + person.getLastName());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Gender: " + person.getGender());
        System.out.println("Team: " + person.getTeam().getTeamName());
        //We get to the part where the displayed information depends on the type
        //of staff, Player or Coach. For this we use the "instanceof" method to
        //know if the person object is an instance of "Player" class.
        if (person instanceof Player) {
            //If the option is a player, this is printed followed by the type of
            //player
            System.out.println("Staff: Player");
            System.out.println("Player Type: " + ((Player) person).getPlayerType());
          //The same for Coach if coach is chosen.
        } else if (person instanceof Coach) {
            System.out.println("Staff: Coach");
            System.out.println("Coach Type: " + ((Coach) person).getCoachType());
        }
        //This line only prints an space for aesthetic purposes.
        System.out.println();
    }

    //This methos is just to check if the email format is correct in the previous
    //method. To be accepted, the input must have an string followed by at (@),
    //followed by another string, a dot (.) and a final string. If this
    //requirements are met, the input is accepted, otherwise, it must be written
    //again.
    private boolean isValidEmail(String email) {
        // Email format pattern with dot after '@'
        //This is a new variable of string class which stores what was said
        //just above, it checks string, at, string, dot, string.
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$";
        // Check if the email matches the pattern
        //It returns the email if it matches the pattern.
        return email.matches(emailPattern);
    }

////////////////////////////////////////////////////////////////////////////////
    //This is our last method of the assignment. It is meant to take random
    //data from the list of people provided on the .txt file and create new
    //individuals to populate the database of players and coaches of the rugby
    //teams.
    //THis method "getRandomPerson" takes three attributes, the people array,
    //which contains all the information from the file organised in an array list.
    //It takes the array list teams to be assigned to the players
    public void getRandomPerson(List<Person> people, List<Team> teams) {
        //Like before, we create a random variable from Random class to be
        //able to get random numbers in our method.
        Random random = new Random();

        //This if condition is here in case there is no people in our people
        //array. In that case, an error message is displayed and returns from
        //the method.
        if (people.isEmpty()) {
            System.out.println("The list is empty. Please add some people before generating a random person.");
            return;
        }

        //This next line is very important to add the new individuals properly
        //to our list of people. An int variable "newID" is created and initialised
        //with the integer value of the following code. To explain it, we take
        //the people array list and apply the "stream()" operation, which sequentially
        //opperates through the element of it, to continue with the "mapToInt"
        //method. It converts every Person object in its corresponding "id".
        //With the max()operation we get the maximum element of the stream and
        //adds 1 to add the new person to the last position or ID.
        //The .orElse(0) operation is optional and returns 0 in case the people
        //Array is empty.
        int newId = people.stream().mapToInt(Person::getId).max().orElse(0) + 1;

        //In the next four lines, we call the getRandomAttribute, which is below
        //this one and store the gotten string in every person attibute.
        String firstName = getRandomAttribute(people, Person::getFirstName);
        String lastName = getRandomAttribute(people, Person::getLastName);
        String email = getRandomAttribute(people, Person::getEmail);
        String gender = getRandomAttribute(people, Person::getGender);

        //CoachType is a method from the Coach attribute, which is also one
        //of our enums on the Menu interface.
        //We create a variable of this class to store a random coach type.
        //We start witch CoachType method, then continue with values(), which
        //returns an array containing all the enum constants in their respective
        //order. The random inside the [] generates a random number between 0
        //(inclusive) and the length of the enum constants (exclusive). This is,
        //among 0 and 4, the 5 coach type options
        CoachType randomCoachType = CoachType.values()[random.nextInt(CoachType.values().length)];

        //We create a person variable of Person class.
        Person person;
        //This boolean "isPlayer", randomly determines, with a 70% chance, if a
        //person is a player or not. If not, they are a coach.
        boolean isPlayer = random.nextDouble() < 0.7; // 70% chance of being a player
        if (isPlayer) {
            //IN case of being player, a similar case to the coach type is applied
            //to get a random number among 0 and 4, according to the number of
            //player types
            PlayerType randomPlayerType = PlayerType.values()[random.nextInt(PlayerType.values().length)]; // Generate random player type
            person = new Player(newId, firstName, lastName, email, gender, randomPlayerType); // Set the random player type
        } else {
            //This else is the same but if the person turns out to be a coach
            person = new Coach(newId, firstName, lastName, email, gender, randomCoachType);
        }

        //THis line creates a random element from the teams array, which stores
        //The team names and returns us a random one.
        Team randomTeam = teams.get(random.nextInt(teams.size()));
        //In this line we assign that random team to the person.
        person.assignTeam(randomTeam);

        //And finally, that person, with all their attributes, is stored in the
        //people array list as a new individual:
        people.add(person);

        //Here we print all the information of the new person
        System.out.println("New person added:");
        System.out.println("ID: " + person.getId());
        System.out.println("First Name: " + person.getFirstName());
        System.out.println("Last Name: " + person.getLastName());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Gender: " + person.getGender());
        System.out.println("Team: " + person.getTeam().getTeamName());
        //This if and else condition is like before, one alternative is printed
        //if the new individual is a player and other one if is a coach.
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

    //This is the beforemention method to get a random attribute from the list
    //of people to create a new random one to populate the array list.
    //This method takes the people array list and a Function method <Person, String>
    //called attributeGetter. The Function is an interface that comes from the
    //package "java.util.function" This is very useful, because accept an argument
    //(in our case Person) and produces a String result, wich is the attibute of
    //the random person of the list we are taking the attribute from.
    private String getRandomAttribute(List<Person> people, Function<Person, String> attributeGetter) {
        //We create a random variable of Random class to apply whithin the method
        Random random = new Random();
        //This line generates a random index withing the range of the people
        //list and returns the Person object at that random index. This person
        //will be assigned to randomPerson variable of Person class.
        Person randomPerson = people.get(random.nextInt(people.size()));
        // Get the specified attribute from the random person
        return attributeGetter.apply(randomPerson);
    }

    ////////////////////////////////////////////////////////////////////////////

    public void printList(List<Person> people) {
        //We create a counter i to stop the loop and display only 20 records.
        int counter = 0;
        //This is a loop that will iterate every person from the people array
        //list, and will break when the before instantiate counter gets 20.
        //THis is to display only 20 people of the list, as ask in the
        //requirements
        for (Person person : people) {
            if (counter >= 20) {
                break; //Here we break or exit the loop if the counter gets 20
            }
            //Then, in the loop, we print every attribute of every person,
            //including their team, if it a player, a coach and their respective
            //type.
            System.out.println("ID: " + person.getId());
            System.out.println("First Name: " + person.getFirstName());
            System.out.println("Last Name: " + person.getLastName());
            System.out.println("Email: " + person.getEmail());
            System.out.println("Gender: " + person.getGender());
            System.out.println("Team: " + person.getTeam().getTeamName()); // Display the assigned team name

            //If the person a player, that will be displayed plus its type.
            if (person instanceof Player) {
                System.out.println("Staff: Player");
                // Display player type for players
                System.out.println("Player Type: " + ((Player) person).getPlayerType());
            //If the person a coach, that will be displayed plus its type.
            } else if (person instanceof Coach) {
                System.out.println("Staff: Coach");
                // Display coach type for coaches
                System.out.println("Coach Type: " + ((Coach) person).getCoachType());
            }

            System.out.println();
            counter++;//Here the counter adds 1 every loop until it gets 20 as
                     //determined before.
        }
    }
}
