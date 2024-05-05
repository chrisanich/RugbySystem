package CA_2;

import java.util.ArrayList;

/**
 * CA_2: Rugby Club System: Second assignment for the subject of Algorithms and 
 * Constructs of the Higher Diploma in Science in Computing of CCT College
 * Dublin, Ireland.
 * Lecturer: Kayoum Khbuli
 * @author Christopher Anich (Student number: 2023202)
 * 12-05-2024
 */
public class Team { 
    //We start creating all the atributes of the class. What attributes are
    //necessary were invented.
    private String team_name;
    private ArrayList<Coach>  coaches;
    private ArrayList<Player> players;
    
    //We create a constructor (method) for this class, which allows us tho add different
    //attributes to it. So, every time we create an object with this class
    //it will have all the attributes inside it.
    public Team (String team_name) {
        //The next line add different attributes to every actor from the class
        //Coach. With this, we create atributes and assign its value when using
        //this method
        this.team_name = team_name;
        this.coaches = new ArrayList<>();
        this.players = new ArrayList<>();

    }
    
    //Inside the class we are calling a method to get the information we need 
    //for everyone of the attributes of the class
    public String getTeamName() {
        return team_name;
    }
    
    public void addCoach (Coach coach) {
        coaches.add(coach);
    }
    
    public void addPlayer (Player player) {
        players.add(player);
    }
}
