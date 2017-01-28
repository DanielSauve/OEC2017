
import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 27/01/17.
 */
public class GraphicPanel extends JPanel {

    private double scale;
    private GridGraphicManager gridManager;

    public GraphicPanel() {
        this.scale = 1.0;
        this.gridManager = new GridGraphicManager(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.gridManager.drawGrid(g);
    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        repaint();
//    }

}
