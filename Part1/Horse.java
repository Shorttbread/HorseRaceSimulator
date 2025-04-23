
/**
 * @author Luis Oliver
 * @version Version 1
 */
public class Horse
{
    //Fields of class Horse
    private final String name;
    private char symbol;
    private int distanceTravelled;
    private boolean hasFallen;
    private double confidence;

    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        setConfidence(horseConfidence);
        this.distanceTravelled = 0;
        this.hasFallen = false; }
    
    //Other methods of class Horse
    public void fall() {
        this.hasFallen = true;
    }
    
    public void goBackToStart() {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }
    
    public void moveForward() {
        if (!hasFallen) {
            this.distanceTravelled++;
        }
    }
    
    public void setConfidence(double newConfidence) {
        if (newConfidence < 0) {
            this.confidence = 0.0;
        } else if (newConfidence > 1) {
            this.confidence = 1.0;
        } else {
            this.confidence = newConfidence;
        }
    }
    
    public void setSymbol(char newSymbol) {
        this.symbol = newSymbol;
    }

    public String getName() {
        return this.name;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public double getConfidence() {
        return this.confidence;
    }

    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public boolean hasFallen() {
        return this.hasFallen;
    }
}