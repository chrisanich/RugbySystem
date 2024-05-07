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
    //We start creating all the atributes of the class. We start with the name
    //of the team as a String and two array lists to store the inviduals of
    //class Coach and Player
    private String team_name;
    private ArrayList<Coach>  coaches;
    private ArrayList<Player> players;
    
    //We create a constructor (method) for this class, which allows us tho add different
    //attributes to it. So, every time we create an object with this class
    //it will have all the attributes inside it.
    public Team (String team_name) {
        //With this line we assign the team_name string, coaches and players array lists,
        //from the constructor to the Team class, determining finally their stored value.
        this.team_name = team_name;
        this.coaches = new ArrayList<>();
        this.players = new ArrayList<>();

    }
    
    //This is called the getter, and declares a public method called
    //getTeamName(), which returns the playerType variable when called
    public String getTeamName() {
        return team_name;
    }
    
    //This next block is to add the coaches to teams (still in process of 
    //implementation)
    public void addCoach (Coach coach) {
        coaches.add(coach);
    }
    
    //This next block is to add the players to teams (still in process of 
    //implementation)
    public void addPlayer (Player player) {
        players.add(player);
    }
}