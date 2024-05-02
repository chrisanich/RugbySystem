package CA_2;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author chrisanich
 */
public class Method {


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
            System.out.println("Team: " + person.getTeam().getTeamName());
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
        String firstName = scanner.nextLine();

        // Prompt for Last Name
        System.out.println("Enter the Last Name of the person:");
        String lastName = scanner.nextLine();

        // Prompt for Email
        System.out.println("Enter the Email of the person:");
        String email = scanner.nextLine();

        // Prompt for Gender
        System.out.println("Enter the Gender of the person:");
        String gender = scanner.nextLine();

        // Generate ID
        int id = nextId++;

        // Create and add the person to the list
        people.add(new Person(id, firstName, lastName, email, gender));
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
