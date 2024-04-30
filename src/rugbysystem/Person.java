/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rugbysystem;

/**
 *
 * @author chrisanich
 */
public class Person {
    //We start creating all the atributes of the class. What attributes are
    //necessary were obtained from the "Club_Form.txt" document.
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String team;
    
    //We create a constructor (method) for this class, which allows us tho add different
    //attributes to it. So, every time we create an object with this class
    //it will have all the attributes inside it.
    public Person (int id, String first_name, String second_name, String email, String gender, String team) {
        //The next line add different attributes to every actor from the class
        //Coach. With this, we create atributes and assign its value when using
        //this method
        
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.team = team;
    }
    
    //Inside the class we are calling a method to get the information we need 
    //for everyone of the attributes of the class
    public int getId () {
        //This method returns the current state(value) of "this.id" 
        return id;
    }
    
    public String getFirstName () {
        //This method returns the current state(value) of "this.first_name" 
        return first_name;
    }
    
    public String getLastName () {
        //This method returns the current state(value) of "this.last_name" 
        return last_name;
    }
    
    public String getEmail () {
        //This method returns the current state(value) of "this.email" 
        return email;
    }
    
    public String getGender () {
        //This method returns the current state(value) of "this.gender" 
        return gender;
    }  
    
    public String getTeam () {
        //This method returns the current state(value) of "this.gender" 
        return team;
    }
}