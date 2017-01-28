
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

    /**
     * Handles a mouse scroll event, to zoom in the display.
     * @param scroll The amount that was scrolled. Positive for down, negative for up.
     */
    public void mouseScrolled(Integer scroll) {
        if(scroll < 0) {
            gridManager.increaseZoom();
        } else {
            gridManager.decreaseZoom();
        }
    }

    /**
     * Handler for the up button being clicked.
     */
    public void upClick() {
        gridManager.moveUp();
    }

    /**
     * Handler for the down button being clicked.
     */
    public void downClick() {
        gridManager.moveDown();
    }

    /**
     * Handler for the right button being clicked.
     */
    public void rightClick() {
        gridManager.moveRight();
    }

    /**
     * Handler for the left button being clicked.
     */
    public void leftClick() {
        gridManager.moveLeft();
    }
}
