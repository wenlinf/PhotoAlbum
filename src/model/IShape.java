package model;

/**
 * The interface Shape.
 */
public interface IShape {
  /**
   * Sets color.
   *
   * @param r the r
   * @param g the g
   * @param b the b
   */
  void setColor(int r, int g, int b);

  /**
   * Gets color.
   *
   * @return the color
   */
  Color getColor();

  /**
   * Sets position.
   *
   * @param x the x
   * @param y the y
   */
  void setPosition(int x, int y);

  /**
   * Gets position.
   *
   * @return the position
   */
  Position getPosition();

  /**
   * Sets scale.
   *
   * @param x the x
   * @param y the y
   */
  void setScale(int x, int y);

  /**
   * Gets scale.
   *
   * @return the scale
   */
  Scale getScale();

  /**
   * Create copy shape.
   *
   * @return the shape
   */
  IShape createCopy();

  /**
   * Gets name.
   *
   * @return the name
   */
  String getName();
}
