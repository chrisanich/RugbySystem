/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rugbysystem;

/**
 *
 * @author chrisanich
 */
public class Team extends Team_Class {
    
    public Team (int team_id, String team_name, String team_coach, String team_country, String team_city) {
        // Make sure that every attribute you are super-ing from here
        // is constructed at the parent level
        super (team_id, team_name, team_coach, team_country, team_city);
    }  
}