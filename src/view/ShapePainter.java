package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import model.IShape;
import model.Oval;
import model.Rectangle;

/**
 * The class paints the shapes in a snapshot of the graphical view.
 */
public class ShapePainter extends JPanel {
  private List<IShape> shapes;

  private static final int BG_COLOR_R = 63;
  private static final int BG_COLOR_G = 138;
  private static final int BG_COLOR_B = 242;

  /**
   * Instantiates a new Shape painter.
   *
   * @param shapes the shapes
   */
  public ShapePainter(List<IShape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Sets shapes.
   *
   * @param shapes the shapes
   */
  public void setShapes(List<IShape> shapes) {
    this.shapes = shapes;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics = (Graphics2D) g;

    // set the background color of the panel
    this.setBackground(new Color(BG_COLOR_R, BG_COLOR_G, BG_COLOR_B));

    // paint all the shapes of the snapshot
    for (IShape shape : shapes) {
      graphics.setColor(new Color(shape.getColor().getR(),
              shape.getColor().getG(),
              shape.getColor().getB()));
      if (shape instanceof Oval) {
        graphics.drawOval(
                shape.getPosition().getPosX(),
                shape.getPosition().getPosY(),
                shape.getScale().getScaleX(),
                shape.getScale().getScaleY());
        graphics.fillOval(
                shape.getPosition().getPosX(),
                shape.getPosition().getPosY(),
                shape.getScale().getScaleX(),
                shape.getScale().getScaleY());
      } else if (shape instanceof Rectangle) {
        graphics.drawRect(shape.getPosition().getPosX(),
                shape.getPosition().getPosY(),
                shape.getScale().getScaleX(),
                shape.getScale().getScaleY());
        graphics.fillRect(
                shape.getPosition().getPosX(),
                shape.getPosition().getPosY(),
                shape.getScale().getScaleX(),
                shape.getScale().getScaleY());
      }
    }
  }
}
