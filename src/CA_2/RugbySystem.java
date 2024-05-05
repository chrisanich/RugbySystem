package CA_2;

//We import the Method class, which contains all the methods in the software
import CA_2.Method;

/**
 * CA_2: Rugby Club System: Second assignment for the subject of Algorithms and 
 * Constructs of the Higher Diploma in Science in Computing of CCT College
 * Dublin, Ireland.
 * Lecturer: Kayoum Khbuli
 * @author Christopher Anich (Student number: 2023202)
 * 12-05-2024
 */
public class RugbySystem implements Menu{
    
    //In this case we use @Override to indicate that the next method is
    //defined in an interface, in this case, the "Menu" interface
    @Override
    public void sortPeople(){};
    
    //In this case we use @Override to indicate that the next method is
    //defined in an interface, in this case, the "Menu" interface
    @Override
    public void searchPeople(){};
    
    //In this case we use @Override to indicate that the next method is
    //defined in an interface, in this case, the "Menu" interface
    @Override
    public void addPerson(){};
    
    //In this case we use @Override to indicate that the next method is
    //defined in an interface, in this case, the "Menu" interface
    @Override
    public void getRandomPerson(){};
    

    /**
     * @param args the command line arguments
     */
    
    //The main method is very simple, it only creates the variable method
    //of class Method to be able to call the "readFile" method. This last one
    //starts the whole code pipeline.
    public static void main(String[] args) {
        Method method = new Method();
        method.readFile();
    }
    
}