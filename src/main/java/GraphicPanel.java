
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

    public void mouseScrolled(Integer scroll) {
        if(scroll > 0) {
            gridManager.increaseZoom();
        } else {
            gridManager.decreaseZoom();
        }
    }

    public void upClick() {
        gridManager.moveUp();
    }

    public void downClick() {
        gridManager.moveDown();
    }

    public void rightClick() {
        gridManager.moveRight();
    }

    public void leftClick() {
        gridManager.moveLeft();
    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        repaint();
//    }

}
