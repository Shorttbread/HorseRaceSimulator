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
        frame.setVisible(true);
        frame.setResizable(false);
    
        // panel for the track to be shown in
        trackPanel = new JPanel();

        trackPanel.setBackground(new Color(20, 20, 20)); 
        trackPanel.setSize(800, 400);
        trackPanel.setLocation(350,25);
        trackPanel.setLayout(null); 
        frame.add(trackPanel);
        frame.setVisible(true);


        startButton = new JButton("start Race");
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

        ImageIcon horse1Icon = new ImageIcon("horse1.png");
        ImageIcon horse2Icon = new ImageIcon("horse2.png");
        ImageIcon horse3Icon = new ImageIcon("horse3.png");

        horse1Icon = new ImageIcon(horse1Icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        horse2Icon = new ImageIcon(horse2Icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        horse3Icon = new ImageIcon(horse3Icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        
        JLabel horse1Label = new JLabel(horse1Icon);
        JLabel horse2Label = new JLabel(horse2Icon);
        JLabel horse3Label = new JLabel(horse3Icon);
        
        int centerX = (trackPanel.getWidth() - 100) / 2;
        int baseY = (trackPanel.getHeight() - 100) / 2;
        horse1Label.setBounds(centerX, baseY, 120, 120);
        horse2Label.setBounds(centerX, baseY, 120, 120);
        horse3Label.setBounds(centerX, baseY, 120, 120);
        
        trackPanel.add(horse1Label);
        trackPanel.add(horse2Label);
        trackPanel.add(horse3Label);

        trackPanel.setComponentZOrder(backgroundLabel, trackPanel.getComponentCount() - 1);

        trackPanel.repaint();

        new Thread(() -> {
            int x1 = 0, x2 = 0, x3 = 0;
            int raceLength = 100;

            while (true) {
                // Simulate random movement
                if (Math.random() < 0.9) x1++;
                if (Math.random() < 0.8) x2++;
                if (Math.random() < 0.75) x3++;

                int lead = Math.max(x1, Math.max(x2, x3));

            
                horse1Label.setLocation(centerX - (lead - x1) * 10, horse1Label.getY());
                
                horse2Label.setLocation(centerX - (lead - x2) * 10, horse2Label.getY());
                
                horse3Label.setLocation(centerX - (lead - x3) * 10, horse3Label.getY());

                if (x1 >= raceLength || x2 >= raceLength || x3 >= raceLength) {
                    String winner = x1 >= raceLength ? "Horse 1" : x2 >= raceLength ? "Horse 2" : "Horse 3";
                    JOptionPane.showMessageDialog(frame, "And the winner is... " + winner + "!");
                    break;}
                

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    });
}
    public static void main(String[] args) {
            new Gui();
        }
}