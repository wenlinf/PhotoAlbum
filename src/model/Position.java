package model;

import java.util.Objects;

/**
 * The type Position. Represents the x and y coordinates of the shapes.
 */
public class Position {
  private int posX;
  private int posY;

  /**
   * Instantiates a new Position.
   *
   * @param posX the pos x
   * @param posY the pos y
   */
  public Position(int posX, int posY) {
    this.posX = posX;
    this.posY = posY;
  }

  /**
   * Gets pos x.
   *
   * @return the pos x
   */
  public int getPosX() {
    return posX;
  }

  /**
   * Sets pos x.
   *
   * @param posX the pos x
   */
  public void setPosX(int posX) {
    this.posX = posX;
  }

  /**
   * Gets pos y.
   *
   * @return the pos y
   */
  public int getPosY() {
    return posY;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Position)) return false;
    Position position = (Position) o;
    return getPosX() == position.getPosX() && getPosY() == position.getPosY();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPosX(), getPosY());
  }

  /**
   * Sets pos y.
   *
   * @param posY the pos y
   */
  public void setPosY(int posY) {
    this.posY = posY;
  }
}
