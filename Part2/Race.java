import java.util.concurrent.TimeUnit;
import javax.swing.*;

/**
 * A three-horse race for gui
 * 
 * @author Luis
 * @version 4.0
 */
public class Race {
    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;
    private JPanel trackPanel;
    private JLabel horse1Label;
    private JLabel horse2Label;
    private JLabel horse3Label;

    public Race(int distance, JPanel trackPanel, JLabel horse1Label, JLabel horse2Label, JLabel horse3Label) {
        this.raceLength = distance;
        this.trackPanel = trackPanel;
        this.horse1Label = horse1Label;
        this.horse2Label = horse2Label;
        this.horse3Label = horse3Label;
    }

    public void addHorse(Horse theHorse, int laneNumber) {
        if (laneNumber == 1) {
            lane1Horse = theHorse;
        } else if (laneNumber == 2) {
            lane2Horse = theHorse;
        } else if (laneNumber == 3) {
            lane3Horse = theHorse;
        }
    }

    public void startRace() {
        boolean finished = false;

        lane1Horse.goBackToStart();
        lane2Horse.goBackToStart();
        lane3Horse.goBackToStart();

        Horse winningHorse = null;

        int centerX = (trackPanel.getWidth() - 120) / 2;

        while (!finished) {
            moveHorse(lane1Horse);
            moveHorse(lane2Horse);
            moveHorse(lane3Horse);
            int lead = Math.max(
                lane1Horse.getDistanceTravelled(),
                Math.max(lane2Horse.getDistanceTravelled(), lane3Horse.getDistanceTravelled())
            );

            horse1Label.setLocation(centerX - (lead - lane1Horse.getDistanceTravelled()) * 10, 150);
            horse2Label.setLocation(centerX - (lead - lane2Horse.getDistanceTravelled()) * 10, 150);
            horse3Label.setLocation(centerX - (lead - lane3Horse.getDistanceTravelled()) * 10, 150);

            trackPanel.repaint();

            if (raceWonBy(lane1Horse)) {
                winningHorse = lane1Horse;
                finished = true;
            } else if (raceWonBy(lane2Horse)) {
                winningHorse = lane2Horse;
                finished = true;
            } else if (raceWonBy(lane3Horse)) {
                winningHorse = lane3Horse;
                finished = true;
            }

            if (!finished &&
                lane1Horse.hasFallen() &&
                lane2Horse.hasFallen() &&
                lane3Horse.hasFallen()) {
                JOptionPane.showMessageDialog(trackPanel, "All horses have fallen. No winner.");
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
        double basicFall = 0.1;
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
