package CA_2;

/**
 *
 * @author chrisanich
 */
    public class Player extends Person {
    private Team assignedTeam;
    
    public Player(int id, String first_name, String last_name, String email, String gender, Team team) {
        super(id, first_name, last_name, email, gender);
        this.assignedTeam = team;
    }
    
    public void assignTeam(Team team) {
        this.assignedTeam = team;
    }
}

