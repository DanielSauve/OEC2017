import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 27/01/17.
 */
public class GraphicPanel extends JPanel {

    private double scale;

    public GraphicPanel() {
        this.scale = 1.0;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.BLACK);

        g2d.drawLine(10,10,100,100);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        repaint();
//    }

}
