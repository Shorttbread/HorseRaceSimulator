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
        });
    }

    public static void main(String[] args) {
            new Gui();
        }
}