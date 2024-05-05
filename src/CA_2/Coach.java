package CA_2;

import CA_2.Menu.CoachType;

/**
 * CA_2: Rugby Club System: Second assignment for the subject of Algorithms and 
 * Constructs of the Higher Diploma in Science in Computing of CCT College
 * Dublin, Ireland.
 * Lecturer: Kayoum Khbuli
 * @author Christopher Anich (Student number: 2023202)
 * 12-05-2024
 */
public class Coach extends Person {
    private Team assignedTeam;
    //We create attributes to give the coaches a different type
    private CoachType coachType; // Change the type to CoachType enum

    public Coach(int id, String first_name, String last_name, String email, String gender, CoachType coachType) {
        super(id, first_name, last_name, email, gender);
        this.coachType = coachType; // Assign the coach type passed from the constructor
    }
    
    // Getter and setter for coaching style
    public CoachType getCoachType() {
        return coachType;
    }

    public void setCoachType(CoachType coachType) {
        this.coachType = coachType;
    }
}


