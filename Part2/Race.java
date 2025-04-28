import java.util.concurrent.TimeUnit;
import javax.swing.*;

/**
 * A three-horse race for gui
 * 
 * @author Luis
 * @version 6.0
 */
public class Race {
    private int raceLength;
    private Horse[] horses = new Horse[6];
    private JPanel trackPanel;
    private JLabel[] horseLabels = new JLabel[6];

    //constructor
    public Race(int distance, JPanel panel, JLabel[] labels) {
        this.raceLength = distance;
        this.trackPanel = panel;
        this.horseLabels = labels;
    }
    //horse lanes 
    public void addHorse(Horse theHorse, int pos) {
        if (pos >= 0 && pos < 6) {
            horses[pos] = theHorse;
        }
    }
    //to start race
    public void startRace() {
        boolean finished = false;

        for (int i=0; i < horses.length; i++) {
            if (horses[i] !=null) {
                horses[i].goBackToStart();
            }
        }
        Horse winningHorse = null;

        //moves horse
        while (!finished) {
            int lead = 0;
            boolean allFallen = true;

            for (int i=0; i < horses.length; i++) {
                if (horses[i] != null) {
                    moveHorse(horses[i]);
                    if(horses[i].getDistanceTravelled() > lead) {
                        lead = horses[i].getDistanceTravelled();
                    }
                }
            }

            int centerX = (trackPanel.getWidth() - 120) / 2;

            for(int i=0; i< horses.length; i++) {
                if (horses[i] != null) {
                    horseLabels[i].setLocation(centerX - (lead - horses[i].getDistanceTravelled()) * 10, 160);
                    horseLabels[i].setIcon(horses[i].getNextAnimationFrame());
                }
            }
            trackPanel.repaint();
            
            //figures out winner
            for (int i = 0; i < horses.length; i++) {
                if (horses[i] != null && raceWonBy(horses[i])) {
                    winningHorse = horses[i];
                    finished = true;
                    break;
                }
            }
            
            //if all horses have fallen
            for (int i = 0; i<horses.length; i++) {
                if (horses[i] !=null && !horses[i].hasFallen()) {
                    allFallen = false;
                    break;
                }
            }

            if (!finished && allFallen) {
                JOptionPane.showMessageDialog(trackPanel, "All horses have fallen, no Winner");
                return;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (winningHorse != null) {
            JOptionPane.showMessageDialog(trackPanel, "And the winner is... " + winningHorse.getName() + "!");
        }
    }

    private void moveHorse(Horse horse) {
        double basicFall = 0.05;
        if (!horse.hasFallen()) {
            if (Math.random() < horse.getConfidence()) {
                horse.moveForward();
            }
            if (Math.random() < (basicFall * (1 - horse.getConfidence()))) {
                horse.fall();
            }
        }
    }

    private boolean raceWonBy(Horse horse) {
        return horse.getDistanceTravelled() >= raceLength;
    }
}