import javax.swing.*;
import java.awt.*;

public class Gui {
    private JFrame frame;
    private JPanel trackPanel;
    private JButton startButton;
    private JButton trackSettingsButton;
    private JButton extraButton2;
    private JButton extraButton3;
    private JButton extraButton4;

    public Gui() {
        frame = new JFrame("Horse Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 490);
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
        startButton.setSize(250, 50);
        startButton.setLocation(50, 350);
        frame.add(startButton);

        //extra buttons
        trackSettingsButton = new JButton("Track settings");
        trackSettingsButton.setSize(250, 50);
        trackSettingsButton.setLocation(50, 50);
        frame.add(trackSettingsButton);

        extraButton2 = new JButton("extra button 2");
        extraButton2.setSize(250, 50);
        extraButton2.setLocation(50, 125);
        frame.add(extraButton2);

        extraButton3 = new JButton("extra button 3");
        extraButton3.setSize(250, 50);
        extraButton3.setLocation(50, 200);
        frame.add(extraButton3);

        extraButton4 = new JButton("extra button 4");
        extraButton4.setSize(250, 50);
        extraButton4.setLocation(50, 275);
        frame.add(extraButton4);

        trackSettingsButton.addActionListener(e -> {
            //track frame
            JFrame settingsFrame = new JFrame("Track Settings");
            settingsFrame.setSize(550, 400);
            settingsFrame.setLocationRelativeTo(frame);
            settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            settingsFrame.setResizable(false);
            settingsFrame.setLayout(null);
            settingsFrame.setVisible(true);
        
            //track panel
            JPanel bottomPanel = new JPanel();
            bottomPanel.setBackground(Color.LIGHT_GRAY);
            bottomPanel.setBounds(63, 125, 400, 200); 
            settingsFrame.add(bottomPanel);

            JLabel trackLabel = new JLabel("Pick your track:");

            trackLabel.setBounds(225, 20, 200, 25);
            settingsFrame.add(trackLabel);
        
            // ComboBox with track options
            String[] trackOptions = {"Grass", "Snowy", "Desert", "Volcanic"};
            JComboBox<String> trackComboBox = new JComboBox<>(trackOptions);
            trackComboBox.setBounds(175, 50, 200, 25);
            settingsFrame.add(trackComboBox);
        });


        startButton.addActionListener(e -> {
            System.out.println("started race");

            //background image
            ImageIcon bgIcon = new ImageIcon("background.png");
            bgIcon = new ImageIcon(bgIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH));
            JLabel backgroundLabel = new JLabel(bgIcon);

            backgroundLabel.setBounds(0, 0, 800, 400);
            trackPanel.add(backgroundLabel);

            // Horse setup
            char[] symbols = {'A', 'B', 'C', 'D', 'E', 'F'};
            Horse[] horses = new Horse[6];
            JLabel[] horseLabels = new JLabel[6];
            double[] confidences = {0.9, 0.85, 0.8, 0.75, 0.7, 0.65};
            ImageIcon[][] frames = new ImageIcon[6][4];

            //set horses and animation frames
            for (int i=0; i < 6; i++) {
                horses[i] = new Horse(symbols[i], "Horse " + (i + 1), confidences[i]);
                frames[i][0] = new ImageIcon(new ImageIcon("horse" + (i + 1) + "_1.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                frames[i][1] = new ImageIcon(new ImageIcon("horse" + (i + 1) + "_2.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                frames[i][2] = new ImageIcon(new ImageIcon("horse" + (i + 1) + "_3.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                frames[i][3] = new ImageIcon(new ImageIcon("horse" + (i + 1) + "_4.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                horses[i].setAnimationFrames(frames[i]);
                horseLabels[i] = new JLabel(frames[i][0]);
                horseLabels[i].setBounds(0,160,120,120);
                trackPanel.add(horseLabels[i]);
            }

            trackPanel.setComponentZOrder(backgroundLabel, trackPanel.getComponentCount() -1);
            trackPanel.repaint();

            Race race = new Race(80, trackPanel, horseLabels);
            for (int i=0; i < 6; i++) {
                race.addHorse(horses[i], i);
            }
            new Thread(race::startRace).start();
        });
    }   

    public static void main(String[] args) {
        new Gui();
    }
}
