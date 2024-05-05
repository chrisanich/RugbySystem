package CA_2;

import CA_2.Menu.PlayerType;

/**
 * CA_2: Rugby Club System: Second assignment for the subject of Algorithms and 
 * Constructs of the Higher Diploma in Science in Computing of CCT College
 * Dublin, Ireland.
 * Lecturer: Kayoum Khbuli
 * @author Christopher Anich (Student number: 2023202)
 * 12-05-2024
 */
public class Player extends Person {
    private Team assignedTeam;
    //We create attributes to give the coaches a different type
    private PlayerType playerType; // Change the type to CoachType enum

    public Player(int id, String first_name, String last_name, String email, String gender, PlayerType playerType) {
        super(id, first_name, last_name, email, gender);
        this.playerType = playerType; // Assign the coach type passed from the constructor
    }
    
    // Getter and setter for coaching style
    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
