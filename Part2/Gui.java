import javax.swing.*;
import java.awt.*;

public class Gui {
    private JFrame frame;
    private JPanel trackPanel;
    private JButton startButton;

    public Gui() {
        frame = new JFrame("Horse Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 500);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Track Panel
        trackPanel = new JPanel();
        trackPanel.setBackground(new Color(20, 20, 20));
        trackPanel.setSize(800, 400);
        trackPanel.setLocation(350, 25);
        trackPanel.setLayout(null);
        frame.add(trackPanel);

        // Start Button
        startButton = new JButton("Start Race");
        startButton.setSize(200, 30);
        startButton.setLocation(500, 425);
        frame.add(startButton);

        startButton.addActionListener(e -> {
            System.out.println("started race");

            //background image
            ImageIcon bgIcon = new ImageIcon("background.png");
            bgIcon = new ImageIcon(bgIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH));
            JLabel backgroundLabel = new JLabel(bgIcon);
            backgroundLabel.setBounds(0, 0, 800, 400);
            trackPanel.add(backgroundLabel);

            // Horse setup
            Horse horse1 = new Horse('A', "Horse 1", 0.9);
            Horse horse2 = new Horse('B', "Horse 2", 0.8);
            Horse horse3 = new Horse('C', "Horse 3", 0.75);

            //animation icons
            ImageIcon[] horse1Frames = new ImageIcon[] {
                new ImageIcon(new ImageIcon("horse1_1.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)),
                new ImageIcon(new ImageIcon("horse1_2.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)),
                new ImageIcon(new ImageIcon("horse1_3.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)),
                new ImageIcon(new ImageIcon("horse1_4.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))
            };
            ImageIcon[] horse2Frames = new ImageIcon[] {
                new ImageIcon(new ImageIcon("horse2_1.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)),
                new ImageIcon(new ImageIcon("horse2_2.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)),
                new ImageIcon(new ImageIcon("horse2_3.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)),
                new ImageIcon(new ImageIcon("horse2_4.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))
            };
            ImageIcon[] horse3Frames = new ImageIcon[] {
                new ImageIcon(new ImageIcon("horse3_1.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)),
                new ImageIcon(new ImageIcon("horse3_2.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)),
                new ImageIcon(new ImageIcon("horse3_3.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)),
                new ImageIcon(new ImageIcon("horse3_4.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))
            };

            //sets the animation frames
            horse1.setAnimationFrames(horse1Frames);
            horse2.setAnimationFrames(horse2Frames);
            horse3.setAnimationFrames(horse3Frames);
            JLabel horse1Label = new JLabel(horse1Frames[0]);
            JLabel horse2Label = new JLabel(horse2Frames[0]);
            JLabel horse3Label = new JLabel(horse3Frames[0]);

            int y = 160;

            horse1Label.setBounds(0, y, 160, 160);
            horse2Label.setBounds(0, y, 160, 160);
            horse3Label.setBounds(0, y, 160, 160);

            trackPanel.add(horse1Label);
            trackPanel.add(horse2Label);
            trackPanel.add(horse3Label);

            trackPanel.setComponentZOrder(backgroundLabel, trackPanel.getComponentCount() - 1);
            trackPanel.repaint();
            

            
            Race race = new Race(50, trackPanel, horse1Label, horse2Label, horse3Label);
            race.addHorse(horse1, 1);
            race.addHorse(horse2, 2);
            race.addHorse(horse3, 3);

            new Thread(race::startRace).start();
        });
    }

    public static void main(String[] args) {
        new Gui();
    }
}
