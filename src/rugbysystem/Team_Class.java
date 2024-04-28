/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rugbysystem;

/**
 *
 * @author chrisanich
 */
public class Team_Class { 
    //We start creating all the atributes of the class. What attributes are
    //necessary were invented.
    private int team_id;
    private String team_name;
    private String team_coach;
    private String team_country;
    private String team_city;
    
    //We create a constructor (method) for this class, which allows us tho add different
    //attributes to it. So, every time we create an object with this class
    //it will have all the attributes inside it.
    public Team_Class (int team_id, String team_name, String team_coach, String team_country, String team_city) {
        //The next line add different attributes to every actor from the class
        //Coach. With this, we create atributes and assign its value when using
        //this method
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_coach = team_coach;
        this.team_country = team_country;
        this.team_city = team_city;
    }
    
    //Inside the class we are calling a method to get the information we need 
    //for everyone of the attributes of the class
    public int getTeamId () {
        //This method returns the current state(value) of "this.team_id" 
        return team_id;
    }
    
    public String getTeamName () {
        //This method returns the current state(value) of "this.team_name" 
        return team_name;
    }
    
    public String getTeamCoach () {
        //This method returns the current state(value) of "this.team_name" 
        return team_coach;
    }
    
    public String getTeamCountry () {
        //This method returns the current state(value) of "this.team_country" 
        return team_country;
    }
    
    public String getTeamCity () {
        //This method returns the current state(value) of "this.team_city" 
        return team_city;
    }
}
