/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author chrisanich
 */
    public class Coach extends Person {
    private Team assignedTeam;
    
    public Coach(int id, String first_name, String last_name, String email, String gender, Team team) {
        super(id, first_name, last_name, email, gender);
        this.assignedTeam = team;
    }
    
    public void assignTeam(Team team) {
        this.assignedTeam = team;
    }
}


