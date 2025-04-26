import javax.swing.*;
import java.awt.*;

public class Gui {
    private JFrame frame;
    private JPanel trackPanel;
    private JButton startButton;
    private JButton trackSettingsButton;
    private JButton horseSelection;
    private JButton extraButton3;
    private JButton extraButton4;
    private String background = "grass";
    private int selectedLaneCount = 6;
    private int selectedTrackLength = 80;
    private String trackCondition = "dry";
    private JLabel weatherDisplayLabel;
    private String[] horseBreeds = new String[6];
    private String[] horseCoats = new String[6];
    private String[] horseImages = new String[6];

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

        horseSelection = new JButton("Horse Selection");
        horseSelection.setSize(250, 50);
        horseSelection.setLocation(50, 125);
        frame.add(horseSelection);

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
            JLabel bottomLabel = new JLabel();
            bottomLabel.setBounds(63, 150, 400, 200);
            bottomLabel.setOpaque(true);
            settingsFrame.add(bottomLabel);

            //"pick your track"
            JLabel trackLabel = new JLabel("Pick your track:");
            trackLabel.setBounds(225, 80, 200, 25);
            settingsFrame.add(trackLabel);
        
            // ComboBox with different tracks
            String[] trackOptions = {"Grass", "Snowy", "Desert", "Volcanic"};
            JComboBox<String> trackComboBox = new JComboBox<>(trackOptions);
            trackComboBox.setBounds(175, 100, 200, 25);
            settingsFrame.add(trackComboBox);

            //shows preview image
            trackComboBox.addActionListener(event -> {
                String selected = (String) trackComboBox.getSelectedItem();
                if (selected != null) {
                    background = selected.toLowerCase(); // combobox changing background to selected

                    ImageIcon tsIcon = new ImageIcon(new ImageIcon(background + ".png").getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH)); //preview showing
                    bottomLabel.setIcon(tsIcon);

                }
            });

            
            JLabel laneLabel = new JLabel("Number of lanes:");
            laneLabel.setBounds(60, 20, 150, 25);
            settingsFrame.add(laneLabel);

            //for lane count
            JSpinner laneSpinner = new JSpinner(new SpinnerNumberModel(selectedLaneCount, 1, 6, 1));
            laneSpinner.setBounds(60, 50, 60 ,25);
            settingsFrame.add(laneSpinner);
            
            JLabel lengthLabel = new JLabel("Track Length:");
            lengthLabel.setBounds(420, 20, 100, 25);
            settingsFrame.add(lengthLabel);

            //for track length
            JSpinner lengthSpinner = new JSpinner(new SpinnerNumberModel(selectedTrackLength, 50, 300, 10));
            lengthSpinner.setBounds(420, 50, 60, 25);
            settingsFrame.add(lengthSpinner);

            //changes value
            laneSpinner.addChangeListener(e2 -> selectedLaneCount = (Integer) laneSpinner.getValue());
            lengthSpinner.addChangeListener(e2 -> selectedTrackLength = (Integer) lengthSpinner.getValue());

            // Track condition
            JLabel weatherLabel = new JLabel("Pick weather:");
            weatherLabel.setBounds(225, 20, 200, 25);
            settingsFrame.add(weatherLabel);

            String[] weatherOptions = {"Dry", "Muddy", "Icy"};
            JComboBox<String> weatherComboBox = new JComboBox<>(weatherOptions);
            weatherComboBox.setBounds(175, 50, 200, 25);
            settingsFrame.add(weatherComboBox);

            // Weather display label below track panel
            weatherDisplayLabel = new JLabel("Track Condition: Dry");
            weatherDisplayLabel.setBounds(350, 430, 400, 25);
            frame.add(weatherDisplayLabel);


            weatherComboBox.addActionListener(event -> {
                String selected = (String) weatherComboBox.getSelectedItem();
                if (selected != null) {
                    trackCondition = selected.toLowerCase();
                    weatherDisplayLabel.setText("Track Condition: " + selected);
                }
            });
        });
        horseSelection.addActionListener(e3 -> {
            JFrame horseFrame = new JFrame("Horse Selection");
            horseFrame.setSize(550, 500);
            horseFrame.setLocationRelativeTo(frame);
            horseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            horseFrame.setResizable(false);
            horseFrame.setLayout(null);
            horseFrame.setVisible(true);

            JLabel selectionLabel = new JLabel("Customise Your Horse:");
            selectionLabel.setBounds(180, 10, 200, 25);
            horseFrame.add(selectionLabel);

            JLabel horseLabel = new JLabel("Select Horse:");
            horseLabel.setBounds(100, 50, 150, 25);
            horseFrame.add(horseLabel);

            String[] horses = {"Horse 1", "Horse 2", "Horse 3", "Horse 4", "Horse 5", "Horse 6"};
            JComboBox<String> horseBox = new JComboBox<>(horses);
            horseBox.setBounds(250, 50, 150, 25);
            horseFrame.add(horseBox);

            JLabel breedLabel = new JLabel("Select Breed:");
            breedLabel.setBounds(100, 100, 150, 25);
            horseFrame.add(breedLabel);

            String[] breeds = {"Thoroughbred", "Arabian", "Quarter Horse", "Clydesdale"};
            JComboBox<String> breedBox = new JComboBox<>(breeds);
            breedBox.setBounds(250, 100, 150, 25);
            horseFrame.add(breedBox);

            JLabel coatLabel = new JLabel("Select Coat Colour:");
            coatLabel.setBounds(100, 150, 150, 25);
            horseFrame.add(coatLabel);

            String[] coats = {"Brown", "Black", "White"};
            JComboBox<String> coatBox = new JComboBox<>(coats);
            coatBox.setBounds(250, 150, 150, 25);
            horseFrame.add(coatBox);

            JPanel previewPanel = new JPanel();
            previewPanel.setBounds(150, 210, 250, 230);
            previewPanel.setBackground(Color.LIGHT_GRAY);
            horseFrame.add(previewPanel);

            coatBox.addActionListener(event -> {
                String selectedCoat = (String) coatBox.getSelectedItem();
                int selectedHorse = horseBox.getSelectedIndex();
                String horseImage = "horses/brown.png"; //the default

                if (selectedCoat == null || selectedCoat.equals("Brown")) {
                    horseImage = "horses/brown.png";
                } else if (selectedCoat.equals("Black")) {
                    horseImage = "horses/black.png";
                } else if (selectedCoat.equals("White")) {
                    horseImage = "horses/white.png";
                }

                horseCoats[selectedHorse] = selectedCoat;
                horseImages[selectedHorse] = horseImage;

                // Preview
                ImageIcon horsePreview = new ImageIcon(
                    new ImageIcon(horseImage).getImage().getScaledInstance(previewPanel.getWidth(), previewPanel.getHeight(), Image.SCALE_SMOOTH)
                );
                previewPanel.removeAll();
                JLabel previewHorseLabel = new JLabel(horsePreview);
                previewHorseLabel.setBounds(0, 0, previewPanel.getWidth(), previewPanel.getHeight());
                previewPanel.add(previewHorseLabel);
                previewPanel.revalidate();
                previewPanel.repaint();
            });
            breedBox.addActionListener(event -> {
                String selectedBreed = (String) breedBox.getSelectedItem();
                int selectedHorse = horseBox.getSelectedIndex();
                horseBreeds[selectedHorse] = selectedBreed;
            });
        });

        startButton.addActionListener(e -> {

            //background image
            ImageIcon bgIcon = new ImageIcon(background + ".png");
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
            for (int i = 0; i < selectedLaneCount; i++) {
                horses[i] = new Horse(symbols[i], "Horse " + (i + 1), confidences[i]);
            
                // Use selected colour images (if none selected it will be the brown horse)
                String imgPath = (horseImages[i] != null) ? horseImages[i] : "horse" + (i + 1) + "_1.png";
            
                //designates the images to the horse
                frames[i][0] = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                frames[i][1] = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                frames[i][2] = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                frames[i][3] = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            
                horses[i].setAnimationFrames(frames[i]);
            
                horseLabels[i] = new JLabel(frames[i][0]);
                horseLabels[i].setBounds(0, 160, 120, 120);
                trackPanel.add(horseLabels[i]);
            }

            trackPanel.setComponentZOrder(backgroundLabel, trackPanel.getComponentCount() -1);
            trackPanel.repaint();

            //adds the horse
            Race race = new Race(selectedTrackLength, trackPanel, horseLabels);
            for (int i=0; i < selectedLaneCount; i++) {
                race.addHorse(horses[i], i);
            }
            new Thread(race::startRace).start();
        });
    }   

    public static void main(String[] args) {
        new Gui();
    }
}