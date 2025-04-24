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

            ImageIcon bgIcon = new ImageIcon("background.png");
            bgIcon = new ImageIcon(bgIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH));
            JLabel backgroundLabel = new JLabel(bgIcon);
            backgroundLabel.setBounds(0, 0, 800, 400);
            trackPanel.add(backgroundLabel);

            // Horse setup
            Horse horse1 = new Horse('A', "Horse 1", 0.9);
            Horse horse2 = new Horse('B', "Horse 2", 0.8);
            Horse horse3 = new Horse('C', "Horse 3", 0.75);

            ImageIcon horse1Icon = new ImageIcon(new ImageIcon("horse1.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            ImageIcon horse2Icon = new ImageIcon(new ImageIcon("horse2.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            ImageIcon horse3Icon = new ImageIcon(new ImageIcon("horse3.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));

            JLabel horse1Label = new JLabel(horse1Icon);
            JLabel horse2Label = new JLabel(horse2Icon);
            JLabel horse3Label = new JLabel(horse3Icon);

            int y = 160;
            horse1Label.setBounds(0, y, 120, 120);
            horse2Label.setBounds(0, y, 120, 120);
            horse3Label.setBounds(0, y, 120, 120);

            trackPanel.add(horse1Label);
            trackPanel.add(horse2Label);
            trackPanel.add(horse3Label);

            trackPanel.setComponentZOrder(backgroundLabel, trackPanel.getComponentCount() - 1);
            trackPanel.repaint();

            
            Race race = new Race(80, trackPanel, horse1Label, horse2Label, horse3Label);
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
