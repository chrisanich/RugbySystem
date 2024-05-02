/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rugbysystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author chrisanich
 */
public class RugbySystem implements Menu{
    
    @Override
    public void sortPeople(){};
    
    @Override
    public void searchPeople(){};
    
    @Override
    public void addPerson(){};
    
    @Override
    public void getRandomPerson(){};
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        
        //Let's create team names from the class Team (in the same project)
        Team team01 = new Team("Colo-Colo");
        Team team02 = new Team("Universidad de Chile");
        Team team03 = new Team("Universidad Católica");
        Team team04 = new Team("O'Higgins de Rancagua");
        
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
                //Here, we create a new Person class element and...
                Person person = new Person(id, first_name, last_name, email, gender);
                
                // Randomly assign a team to the person
                Random random = new Random();
                Team randomTeam = teams.get(random.nextInt(teams.size()));
                person.assignTeam(randomTeam);
                
                
                //we store it on the people list (ArrayList)
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
        menu.main_menu(myKB);
        
        
        //Finally, we close the user scanner to avoid leak of data
        myKB.close();
    }
    
}
