/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CA_2;

/**
 *
 * @author chrisanich
 */
public interface Menu {
    
    // This interface will serve as the main menu the user
    // Will interact with
    // This Menu needs to list all possible options for the user to interact with
    // We will need to number all of the options 
    enum MenuOption {
        // This enumeration requires a number of keys
        
        SORT_PEOPLE,//0
        SEARCH_PERSON,//1
        ADD_PERSON,//2
        GET_RANDOM_PERSON,//3
        EXIT//4
    }
    
    // Within the interface outside the enum
    // list the methods we created and override them in the class
    // This method will sort people in the the list and show the first 20 ones
    void sortPeople();
    
    // This method will help the user to search a person in the provided
    //database
    void searchPeople();
    
    // this method will allow the user to add a new person
    void addPerson();
    
    //This method will provide the user with a random person data
    void getRandomPerson();
    
}
