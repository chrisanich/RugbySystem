package CA_2;

import CA_2.Method;

/**
 *
 * @author chrisanich
 */
public class RugbySystem implements Menu{
    
    @Override
    public void sortPeople(){};
    
    @Override
    public void searchPeople(){};
    
    @Override
    public void addPerson(){};
    
    @Override
    public void getRandomPerson(){};
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Method method = new Method();
        method.readFile();
    }
    
}