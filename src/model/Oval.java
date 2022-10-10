package model;

/**
 * The type Oval.
 */
public class Oval extends AbstractShape {
  /**
   * Instantiates a new Oval.
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
  public Oval(
          int r,
          int g,
          int b,
          String name,
          int scaleX,
          int scaleY,
          int posX,
          int posY) {
    super(r, g, b, name, scaleX, scaleY, posX, posY);
  }

  @Override
  public IShape createCopy() {
    IShape oval = new Oval(
            this.getColor().getR(),
            this.getColor().getG(),
            this.getColor().getB(),
            this.getName(),
            this.getScale().getScaleX(),
            this.getScale().getScaleY(),
            this.getPosition().getPosX(),
            this.getPosition().getPosY());
    return oval;
  }
}
