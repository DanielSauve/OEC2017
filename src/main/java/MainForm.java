import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

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

    public MainForm() {

        //Handle set hour button
        hourGoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //hourTextInput.getText();
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
            }
        });

        //Give output for generator info button
        goGenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Give output for path button
        pathGoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Give output for houses on button
        housesOnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Give output for revenue per hour
        revenuePerHourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Give output for revenue up to button
        revenueUpToButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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
                loadFileDialog();
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
    public static void main(String[] args) {
        MainForm form = new MainForm();
        form.setupForm();
    }
}
