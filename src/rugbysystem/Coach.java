/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rugbysystem;

/**
 *
 * @author chrisanich
 */
public class Coach extends Person {
    
    public Coach (int id, String first_name, String last_name, String email, String gender, String team) {
        // Make sure that every attribute you are super-ing from here
        // is constructed at the parent level
        super (id, first_name, last_name, email, gender, team);
    }
    
    
}
