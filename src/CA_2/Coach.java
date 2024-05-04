package CA_2;

import CA_2.Menu.CoachType;

/**
 *
 * @author chrisanich
 */
public class Coach extends Person {
    private Team assignedTeam;
    //We create attributes to give the coaches a different type
    private String coach_type;

    public Coach(int id, String first_name, String last_name, String email, String gender, CoachType coachType) {
        super(id, first_name, last_name, email, gender);
        this.coach_type = coach_type;

    }
    
    // Getter and setter for coaching style
    public String getCoachType() {
        return coach_type;
    }

    public void setCoachType(String coachingStyle) {
        this.coach_type = coach_type;
    }
}


