package CA_2;

//In this line we import the CoachType enum from Menu class,
import CA_2.Menu.CoachType;

/**
 * CA_2: Rugby Club System: Second assignment for the subject of Algorithms and 
 * Constructs of the Higher Diploma in Science in Computing of CCT College
 * Dublin, Ireland.
 * Lecturer: Kayoum Khbuli
 * @author Christopher Anich (Student number: 2023202)
 * 12-05-2024
 */

//In this Coach class declaration we indicate extends to inherite the properties
// and methods from the Person class.
public class Coach extends Person {
    //With this line declares a private instance of type Team and represents the
    //team the coach was assigned to.
    private Team assignedTeam;
    //We create attributes to give the coaches a different type through
    //the CoachType class (imported above)
    private CoachType coachType; // Change the type to CoachType enum
    
    //In this line we declare the constructor of Coach that takes six parameters
    public Coach(int id, String first_name, String last_name, String email, String gender, CoachType coachType) {
        //We call the constructor of the superclass "Person" with its paramenters
        //to iniciatise the inherited fields.
        super(id, first_name, last_name, email, gender);
        //With this line we assign the coachType parameter from the constructor
        //to the coachType variable of the Coach class, determining finally its
        //stored value
        this.coachType = coachType; // Assign the coach type passed from the constructor
    }
    
    //This is called the getter, and declares a public method called
    //getCoachType(), which returns the coachType variable when called
    public CoachType getCoachType() {
        return coachType;
    }
    
    //This line declares a public method which sets the "coachTYpe to the value
    //provided as a paramenter. The line inside the method 
    //(this.coachType = coachType;) assigns the value of 
    //coachType variable of "Coach" class when called.
    public void setCoachType(CoachType coachType) {
        this.coachType = coachType;
    }
}


