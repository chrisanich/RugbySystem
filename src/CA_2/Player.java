package CA_2;

//In this line we import the PlayerType enum from Menu class,
import CA_2.Menu.PlayerType;

/**
 * CA_2: Rugby Club System: Second assignment for the subject of Algorithms and 
 * Constructs of the Higher Diploma in Science in Computing of CCT College
 * Dublin, Ireland.
 * Lecturer: Kayoum Khbuli
 * @author Christopher Anich (Student number: 2023202)
 * 12-05-2024
 */

//In this Player class declaration we indicate extends to inherite the properties
// and methods from the Person class.
public class Player extends Person {
    //With this line declares a private instance of type Team and represents the
    //team the player was assigned to (currently unused).///////////////////////
    private Team assignedTeam;
    //We create attributes to give the players a different type through
    //the PlayerType class (imported above)
    private PlayerType playerType; // Change the type to PlayerType enum
    
    //In this line we declare the constructor of Player that takes six parameters
    public Player(int id, String first_name, String last_name, String email, String gender, PlayerType playerType) {
        //We call the constructor of the superclass "Person" with its paramenters
        //to iniciatise the inherited fields.
        super(id, first_name, last_name, email, gender);
        //With this line we assign the playerType parameter from the constructor
        //to the playerType variable of the Player class, determining finally its
        //stored value
        this.playerType = playerType; // Assign the player type passed from the constructor
    }
    
    //This is called the getter, and declares a public method called
    //getPlayerType(), which returns the playerType variable when called
    public PlayerType getPlayerType() {
        return playerType;
    }
    
    //This line declares a public method which sets the "playerType to the value
    //provided as a paramenter. The line inside the method 
    //(this.playerType = playerType;) assigns the value of 
    //playerType variable of "Player" class when called.
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
