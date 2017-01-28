import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by David on 26/01/17.
 * https://examples.javacodegeeks.com/desktop-java/ide/intellij-gui-designer-example/
 */
public class MainForm {
    private JTextArea testTextArea;
    private JButton hourGoButton;
    private JPanel mainPanel;
    private JPanel graphicPanel;
    private JPanel controlPanel;
    private JTextArea hourTextInput;
    private JButton decHourButton;
    private JButton incHourButton;
    private JPanel informationPanel;

    public MainForm() {
        hourGoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testTextArea.setText("CLICK!");
            }
        });
        hourGoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hourTextInput.getText();
            }
        });
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(new Dimension(800,800));
        f.setContentPane(new MainForm().mainPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
