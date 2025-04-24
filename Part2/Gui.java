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
        horse1Label.setBounds(centerX, 30, 100, 100);
        horse2Label.setBounds(centerX, 140, 100, 100);
        horse3Label.setBounds(centerX, 250, 100, 100);
        
        trackPanel.add(horse1Label);
        trackPanel.add(horse2Label);
        trackPanel.add(horse3Label);

        trackPanel.repaint();

        });
            
    }

    public static void main(String[] args) {
            new Gui();
        }
}