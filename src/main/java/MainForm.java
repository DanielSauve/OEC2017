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
    private JButton button1;
    private JTable table1;
    private JPanel jpan;

    public MainForm() {
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testTextArea.setText("CLICK!");
            }
        });
    }
    /*
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(new Dimension(300,300));
        f.setContentPane(new MainForm().jpan);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    */
}
