
/**
 * Write a description of class Horse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Horse
{
    //Fields of class Horse
    private char horseSymbol;
    private String horseName;
    private double horseConfidence;
    private boolean hasFallen;
    private int distanceTravelled;

    
      
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
       this.horseSymbol = horseSymbol;
       this.horseName = horseName;
       setConfidence(horseConfidence);
       this.hasFallen = false;
       this.distanceTravelled = 0;
    }
    
    //Other methods of class Horse
    public void fall()
    {
        this.hasFallen = true;
    }
    
    public double getConfidence()
    {
        return horseConfidence;
    }
    
    public int getDistanceTravelled()
    {
        return distanceTravelled;
    }
    
    public String getName()
    {
        return horseName;
    }
    
    public char getSymbol()
    {
        return horseSymbol;
    }
    
    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }
    
    public boolean hasFallen()
    {
        return hasFallen;
    }

    public void moveForward()
    {
        if (!hasFallen) {
            this.distanceTravelled++;
        }
    }

    public void setConfidence(double newConfidence)
    {
        if (newConfidence < 0) {
        this.horseConfidence = 0;
    } else if (newConfidence > 1) {
        horseConfidence = 1;
    } else {
        this.horseConfidence = newConfidence;
    }
}
    
    public void setSymbol(char newSymbol)
    {
        this.horseSymbol = newSymbol;
    }
    
}
