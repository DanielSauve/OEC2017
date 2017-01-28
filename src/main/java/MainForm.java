import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.io.File;
import java.util.*;

/**
 * Created by David on 26/01/17.
 * https://examples.javacodegeeks.com/desktop-java/ide/intellij-gui-designer-example/
 */
public class MainForm {
    private JTextArea testTextArea;
    private JButton hourGoButton;
    private JPanel mainPanel;
    private JPanel controlPanel;
    private JTextArea hourTextInput;
    private JButton decHourButton;
    private JButton incHourButton;
    private JPanel informationPanel;
    private JLabel componentNameLabel;
    private JTextArea node1TextBox;
    private JTextArea node2TextBok;
    private JButton deleteConnectionButton;
    private GraphicPanel graphicPanel;
    private JPanel gridControlPanel;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JTextArea outputText;
    private JButton housesOnButton;
    private JButton revenuePerHourButton;
    private JButton revenueUpToButton;
    private JTextArea generatorText;
    private JButton goGenButton;
    private JTextArea pathHouseText;
    private JTextArea genPathText;
    private JButton pathGoButton;
    private JButton clearOutputButton;
    private JScrollPane scrollPane;
    private JFrame mainFrame;
    private InputFileReader reader;
    private HashMap<Integer, House> houses;
    private HashMap<String, Generator> generators;
    private HashMap<String, Node> nodes;
    private ArrayList<Link> links;
    private ArrayList<SuperNode> graph;
    private java.util.List<Generator> generatorList;
    private java.util.List<House> houseList;
    private PathPlanner planner;

    public MainForm() {

        reader = new InputFileReader();

        houses = reader.readHouse();
        generators = reader.readGenerators();
        nodes = reader.readNode();
        links = reader.readLink(houses, nodes, generators);

        graph = new ArrayList<SuperNode>();
        graph.addAll(houses.values());
        graph.addAll(generators.values());
        graph.addAll(nodes.values());
        generatorList = new ArrayList<Generator>();
        for (Generator generator: generators.values()){
            generatorList.add(generator);
        }
        houseList = new ArrayList<House>();
        for (House house: houses.values()){
            houseList.add(house);
        }
        reader.readHousePower(houses);
        planner = new PathPlanner(generatorList, graph);
        MetaFunctions.printPath(planner.findPath(houseList.get(0)));
        for (int i = 1; i <= 8; i++) {
            for (House house : houses.values()) {
                if (house.getOn(i) == 1) {
                    java.util.List<SuperNode> path = planner.findPath(house);
                    Float cost = planner.findCost(house);
                }

            }
        }

        //Handle set hour button
        hourGoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }
        });

        //Handle decrement hour button
        decHourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //step back
            }
        });

        //Handle increment hour button
        incHourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //step forward
            }
        });

        //Handle up click button
        upButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphicPanel.upClick();
            }
        });

        //Handle down click button
        downButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphicPanel.downClick();
            }
        });

        //handle left click button
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphicPanel.leftClick();
            }
        });

        //Handle right click button
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphicPanel.rightClick();
            }
        });

        //Give output for delete connection button
        deleteConnectionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Delete connection
                String n1 = node1TextBox.getText();
                String n2 = node2TextBok.getText();
                //check if valid
                //handleInput();
            }
        });

        //Give output for generator info button
        goGenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String gen = generatorText.getText();
                //Check gen
                //handleInput();
            }
        });

        //Give output for path button
        pathGoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String house = pathHouseText.getText();
                String gen = pathHouseText.getText();
                //check valid
                //handleInput();
            }
        });

        //Give output for houses on button
        housesOnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer value = checkInt(hourTextInput.getText());
                if( value < 8 ) {
                    java.util.List<House> onHouses = MetaFunctions.getOnHousesPerHour(houseList, value);
                    for(House house: onHouses){
                        outputText.append(house.toString());
                    }
                }
            }
        });

        //Give output for revenue per hour
        revenuePerHourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //handleInput();
                Integer hour = checkInt(hourTextInput.getText());
                if(hour >= 0 && hour <= 8) {
                    outputText.append(MetaFunctions.revenueInHour(hour, planner).toString());
                }

            }
        });

        //Give output for revenue up to button
        revenueUpToButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //handleInput();
                Integer hour = checkInt(hourTextInput.getText());
                if(hour >= 0 && hour <= 8) {
                    outputText.append( MetaFunctions.totalRevenueUpToHour(hour, planner).toString());
                }
            }
        });

        //Clear the output box
        clearOutputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputText.setText("");
            }
        });
    }

    /**
     * Sets up the form including all components.
     */
    private void setupForm() {

        //Create the frame to use in the window
        this.mainFrame = new JFrame();
        mainFrame.setSize(new Dimension(800, 800));
        mainFrame.setContentPane(new MainForm().mainPanel);

        //Make sure the window closes properly when exiting
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Create menu bar and items
        JMenuBar mainMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem fileOpenMenuItem = new JMenuItem("Load file...");
        fileOpenMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = loadFileDialog();
                if(file.exists()) {
                    reader.setFileName(file.getName());
                }

            }
        });

        //Add menus together
        fileMenu.add(fileOpenMenuItem);
        mainMenuBar.add(fileMenu);

        //Add menu to the frame
        mainFrame.setJMenuBar(mainMenuBar);

        //Show the frame to the user
        mainFrame.setVisible(true);
    }

    /**
     * Opens a file chooser dialog, and returns a chosen file.
     * @return File if 'open' was used, or null if cancelled.
     */
    private File loadFileDialog(){
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(mainFrame);
        if( returnValue == JFileChooser.APPROVE_OPTION ) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    /**
     * Handles all input designed for CLI version, and outputs it to the output box
     */
    private void handleInput() {

    }

    private Integer checkInt(String value) {
        try {
            Integer input = Integer.parseInt(value);
            return input;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        MainForm form = new MainForm();
        form.setupForm();
    }
}
