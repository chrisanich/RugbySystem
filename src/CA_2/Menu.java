package CA_2;

/**
 * CA_2: Rugby Club System: Second assignment for the subject of Algorithms and 
 * Constructs of the Higher Diploma in Science in Computing of CCT College
 * Dublin, Ireland.
 * Lecturer: Kayoum Khbuli
 * @author Christopher Anich (Student number: 2023202)
 * 12-05-2024
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
    
    //This enum is to list all the different types of coach. We add the
    //parentheses to display the option in a more appealing and human way.
    //It is better "Head Coach" than "HEAD_COACH" on the displayed menu.
    public enum CoachType {
        HEAD_COACH("Head Coach"),
        ASSISTANT_COACH("Assistant Coach"),
        ASSISTANT_FORWARDS_COACH("Assistant Forwards Coach"),
        ACADEMY_FORWARDS_COACH("Academy Fordwards Coach"),
        SCRUM_COACH("Scrum Coach");

        private final String displayName;

        CoachType(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
    
    //This enum is to list all the different types of player. We add the
    //parentheses to display the option in a more appealing and human way.
    //It is better "The 8-Man" than "THE_8_MAN" on the displayed menu.
    public enum PlayerType {
        PROMP("Promp"),
        HOOKER("Hooker"),
        FLANKER("Flanker"),
        THE_8_MAN("The 8-Man"),
        FULLBACK("Fullback");

        private final String displayName;

        PlayerType(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }


}
