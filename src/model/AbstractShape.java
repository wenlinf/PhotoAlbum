package model;

import java.util.Objects;

/**
 * The type AbstractShape. An abstract class to represent shapes that have a name, a color,
 * a scale, and a position.
 */
public abstract class AbstractShape implements IShape {
  private String name;
  private Color color;
  private Scale scale;
  private Position position;

  /**
   * Instantiates a new Abstract shape.
   *
   * @param r      the r
   * @param g      the g
   * @param b      the b
   * @param name   the name
   * @param scaleX the scale x
   * @param scaleY the scale y
   * @param posX   the pos x
   * @param posY   the pos y
   */
  public AbstractShape(
          int r,
          int g,
          int b,
          String name,
          int scaleX,
          int scaleY,
          int posX,
          int posY) {
    this.name = name;
    this.color = new Color(r, g, b);
    this.scale = new Scale(scaleX, scaleY);
    this.position = new Position(posX, posY);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setColor(int r, int g, int b) {
    this.color.setR(r);
    this.color.setG(g);
    this.color.setB(b);
  }

  @Override
  public void setScale(int scaleX, int scaleY) {
    this.scale.setScaleX(scaleX);
    this.scale.setScaleY(scaleY);
  }

  @Override
  public Scale getScale() {
    return this.scale;
  }

  @Override
  public void setPosition(int x, int y) {
    this.position.setPosX(x);
    this.position.setPosY(y);
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractShape)) return false;
    AbstractShape that = (AbstractShape) o;
    return Objects.equals(
            getName(), that.getName())
            && Objects.equals(getColor(), that.getColor())
            && Objects.equals(getScale(), that.getScale())
            && Objects.equals(getPosition(), that.getPosition());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getColor(), getScale(), getPosition());
  }

  @Override
  public String toString() {
    String type = "";
    String pos = "";
    String scaleStr = "";
    String colorStr = "Color: ("
            + this.color.getR() + ","
            + this.color.getG() + ","
            + this.color.getB() + ")";
    if (this instanceof Rectangle) {
      type += "Rectangle";
      pos += "Min Corner: ";
      scaleStr += "Width: " + this.getScale().getScaleX()
              + ", Height: " + this.getScale().getScaleY() + ", ";
    } else if (this instanceof Oval) {
      type += "Oval";
      pos += "Center: ";
      scaleStr += "X radius: " + this.getScale().getScaleX()
              + ", Y radius: " + this.getScale().getScaleY() + ", ";
    }
    pos += "(" + this.position.getPosX() + "," + this.position.getPosY() + "), ";
    return "Name: " + name + '\n'
            + "Type: " + type + '\n'
            + pos
            + scaleStr
            + colorStr + '\n';
  }
}
