import javax.swing.*;
import java.awt.*;

public class Gui {
    private JFrame frame;
    private JPanel track;
    private JButton startButton;
    private JButton trackSettingsButton;
    private JButton horseSelectionButton;
    private static final int MIN_LANES = 1;
    private static final int MAX_LANES = 6;
    private String background = "grass";
    private int selectedLaneCount = 6;
    private int selectedTrackLength = 80;
    private String trackCondition = "dry";
    private JLabel weatherDisplayLabel;
    private Horse[] horses = new Horse[6];
    private String[] horseNames = {"Midnight", "American Pharoah", "Bold Ben", "Black Caviar", "Colt", "Hoof hearted"};
    private String[] horseBreeds = new String[6];
    private String[] horseCoats = new String[6];
    private String[] horseImages = new String[6];

    public Gui() {

        //initial horses
        char[] symbols = {'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 0; i < horses.length; i++) {
            horses[i] = new Horse(symbols[i], horseNames[i], 0.5);
        }

        //inital frame
        frame = new JFrame("Horse Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 490);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //panel to display horse race
        track = new JPanel();
        track.setBackground(new Color(20, 20, 20));
        track.setSize(800, 400);
        track.setLocation(350, 25);
        track.setLayout(null);
        frame.add(track);

        //track settings button
        trackSettingsButton = new JButton("Track settings");
        trackSettingsButton.setSize(250, 50);
        trackSettingsButton.setLocation(50, 50);
        frame.add(trackSettingsButton);

        //button activates "track settings" 
        trackSettingsButton.addActionListener(e -> TrackSettings());

        //horse selection button
        horseSelectionButton = new JButton("Horse Selection");
        horseSelectionButton.setSize(250, 50);
        horseSelectionButton.setLocation(50, 200);
        frame.add(horseSelectionButton);

        //button activates "HorseSelection"
        horseSelectionButton.addActionListener(e -> HorseSelection());

        startButton = new JButton("Start Race");
        startButton.setSize(250, 50);
        startButton.setLocation(50, 350);
        frame.add(startButton);

        //button activates "startRace"
        startButton.addActionListener(e -> startRace());
    }

    private void TrackSettings() {

        //frame for track settings
        JFrame settingsFrame = new JFrame("Track Settings");
        settingsFrame.setSize(550, 400);
        settingsFrame.setLocationRelativeTo(frame);
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setResizable(false);
        settingsFrame.setLayout(null);
        settingsFrame.setVisible(true);

        
        JLabel bottomLabel = new JLabel();
        bottomLabel.setBounds(63, 150, 400, 200);
        bottomLabel.setOpaque(true);
        settingsFrame.add(bottomLabel);


        JLabel trackLabel = new JLabel("Pick your track:");
        trackLabel.setBounds(225, 80, 200, 25);
        settingsFrame.add(trackLabel);

        String[] trackOptions = {"Grass", "Snowy", "Desert", "Volcanic"};
        JComboBox<String> trackComboBox = new JComboBox<>(trackOptions);
        trackComboBox.setBounds(175, 100, 200, 25);
        settingsFrame.add(trackComboBox);

        //combobox to change map and preview image
        trackComboBox.addActionListener(event -> {
            String selected = (String) trackComboBox.getSelectedItem();
            if (selected != null) {
                background = selected.toLowerCase();
                ImageIcon tsIcon = new ImageIcon(new ImageIcon("Maps/" + background + ".png").getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH));
                bottomLabel.setIcon(tsIcon);
            }
        });

        JLabel laneLabel = new JLabel("Number of lanes:");
        laneLabel.setBounds(60, 20, 150, 25);
        settingsFrame.add(laneLabel);

        //spinner for lane amount choice
        JSpinner laneSpinner = new JSpinner(new SpinnerNumberModel(selectedLaneCount, MIN_LANES, MAX_LANES, 1));
        laneSpinner.setBounds(60, 50, 60, 25);
        settingsFrame.add(laneSpinner);

        //label for track length
        JLabel lengthLabel = new JLabel("Track Length:");
        lengthLabel.setBounds(420, 20, 100, 25);
        settingsFrame.add(lengthLabel);

        //spinner for track length choice
        JSpinner lengthSpinner = new JSpinner(new SpinnerNumberModel(selectedTrackLength, 50, 300, 10));
        lengthSpinner.setBounds(420, 50, 60, 25);
        settingsFrame.add(lengthSpinner);

        //changes values
        laneSpinner.addChangeListener(e -> selectedLaneCount = (Integer) laneSpinner.getValue());
        lengthSpinner.addChangeListener(e -> selectedTrackLength = (Integer) lengthSpinner.getValue());

        //label for weather
        JLabel weatherLabel = new JLabel("Pick weather:");
        weatherLabel.setBounds(225, 20, 200, 25);
        settingsFrame.add(weatherLabel);

        //combobox for weather options
        String[] weatherOptions = {"Dry", "Icy", "Wet", "Meteor shower!"};
        JComboBox<String> weatherComboBox = new JComboBox<>(weatherOptions);
        weatherComboBox.setBounds(175, 50, 200, 25);
        settingsFrame.add(weatherComboBox);

        //label for track condition
        weatherDisplayLabel = new JLabel("Track Condition");
        weatherDisplayLabel.setBounds(350, 430, 400, 25);
        frame.add(weatherDisplayLabel);

        //combobox to change display of track condition
        weatherComboBox.addActionListener(event -> {
            String selected = (String) weatherComboBox.getSelectedItem();
            if (selected != null) {
                trackCondition = selected.toLowerCase();
                weatherDisplayLabel.setText("Track Condition: " + selected);
            }
        });
    }

    private void HorseSelection() {

        //frame for horse selection
        JFrame horseFrame = new JFrame("Horse Selection");
        horseFrame.setSize(550, 500);
        horseFrame.setLocationRelativeTo(frame);
        horseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        horseFrame.setResizable(false);
        horseFrame.setLayout(null);
        horseFrame.setVisible(true);

        //label to customise
        JLabel selectionLabel = new JLabel("Customise Your Horse:");
        selectionLabel.setBounds(180, 10, 200, 25);
        horseFrame.add(selectionLabel);

        //label to select what horse
        JLabel horseLabel = new JLabel("Select Horse:");
        horseLabel.setBounds(100, 50, 150, 25);
        horseFrame.add(horseLabel);

        //combobox with horse names
        JComboBox<String> horseBox = new JComboBox<>(horseNames);
        horseBox.setBounds(250, 50, 150, 25);
        horseFrame.add(horseBox);

        //select the breed for the horse
        JLabel breedLabel = new JLabel("Select Breed:");
        breedLabel.setBounds(100, 100, 150, 25);
        horseFrame.add(breedLabel);

        //combobox for breeds
        String[] breeds = {"Thoroughbred", "Arabian", "Quarter Horse", "Clydesdale"};
        JComboBox<String> breedBox = new JComboBox<>(breeds);
        breedBox.setBounds(250, 100, 150, 25);
        horseFrame.add(breedBox);

        //select coat colours
        JLabel coatLabel = new JLabel("Select Coat Colour:");
        coatLabel.setBounds(100, 150, 150, 25);
        horseFrame.add(coatLabel);

        //combobox for colours
        String[] coats = {"Brown", "Black", "White"};
        JComboBox<String> coatBox = new JComboBox<>(coats);
        coatBox.setBounds(250, 150, 150, 25);
        horseFrame.add(coatBox);

        //preview panel to display the horse
        JPanel previewPanel = new JPanel();
        previewPanel.setBounds(150, 210, 250, 230);
        previewPanel.setBackground(Color.LIGHT_GRAY);
        horseFrame.add(previewPanel);

        //changes horse preview box and horses coat colour for race
        coatBox.addActionListener(event -> {
            String selectedCoat = (String) coatBox.getSelectedItem();
            int selectedHorse = horseBox.getSelectedIndex();
            String horseImage = "horses/brown"; // default brown

            if (selectedCoat != null) {
                if (selectedCoat.equalsIgnoreCase("Black")) {
                    horseImage = "horses/black";
                } else if (selectedCoat.equalsIgnoreCase("White")) {
                    horseImage = "horses/white";
                }
            }

            horseCoats[selectedHorse] = selectedCoat;
            horseImages[selectedHorse] = horseImage;

            ImageIcon horsePreview = new ImageIcon(
                new ImageIcon(horseImage + "_1.png").getImage().getScaledInstance(previewPanel.getWidth(), previewPanel.getHeight(), Image.SCALE_SMOOTH)
            );
            previewPanel.removeAll();
            JLabel previewHorse = new JLabel(horsePreview);
            previewHorse.setBounds(0, 0, previewPanel.getWidth(), previewPanel.getHeight());
            previewPanel.add(previewHorse);
            previewPanel.revalidate();
            previewPanel.repaint();
        });

        //changes breed for race
        breedBox.addActionListener(event -> {
            String selectedBreed = (String) breedBox.getSelectedItem();
            int selectedHorse = horseBox.getSelectedIndex();
            horseBreeds[selectedHorse] = selectedBreed;
        });
    }

    private void startRace() {
        track.removeAll();
        track.repaint();

        //generates map
        ImageIcon bgIcon = new ImageIcon("Maps/" + background + ".png");
        bgIcon = new ImageIcon(bgIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH));
        JLabel backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setBounds(0, 0, 800, 400);
        track.add(backgroundLabel);

        JLabel[] horseLabels = new JLabel[6];
        ImageIcon[][] frames = new ImageIcon[6][4];
        double[] confidences = new double[6];

        //randomises confidence above 0.4
        for (int i = 0; i < confidences.length; i++) {
            confidences[i] = ((Math.random() * 0.6) + 0.4);
        }

        //generates the amount of horses
        for (int i = 0; i < selectedLaneCount; i++) {
            horses[i].setConfidence(confidences[i]);
            horses[i].goBackToStart();

            //affects horses confidence based on the track conditions
            if (trackCondition.equals("Icy")) {
                horses[i].setConfidence(horses[i].getConfidence() - 0.2);
            } else if (trackCondition.equals("Wet")) {
                horses[i].setConfidence(horses[i].getConfidence() - 0.1);
            } else if (trackCondition.equals("Meteor shower!")) {
                horses[i].setConfidence(horses[i].getConfidence() - 0.5);
            }

            //generates images for animation
            String imagePathForAni = (horseImages[i] == null) ? "horses/brown" : horseImages[i];

            frames[i][0] = new ImageIcon(new ImageIcon(imagePathForAni + "_1.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            frames[i][1] = new ImageIcon(new ImageIcon(imagePathForAni + "_2.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            frames[i][2] = new ImageIcon(new ImageIcon(imagePathForAni + "_3.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            frames[i][3] = new ImageIcon(new ImageIcon(imagePathForAni + "_4.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));

            horses[i].setAnimationFrames(frames[i]);

            horseLabels[i] = new JLabel(frames[i][0]);
            horseLabels[i].setBounds(0, 160, 120, 120);
            track.add(horseLabels[i]);
        }

        //sets order so background is always on the back
        track.setComponentZOrder(backgroundLabel, track.getComponentCount() - 1);
        track.repaint();

        Race race = new Race(selectedTrackLength, track, horseLabels);
        for (int i = 0; i < selectedLaneCount; i++) {
            race.addHorse(horses[i], i);
        }
        new Thread(race::startRace).start();
    }

    public static void main(String[] args) {
        new Gui();
    }
}
