package model;

import java.util.Objects;

/**
 * The type Scale. Representing the scale of the shape vertically and horizontally.
 */
public class Scale {
  private int scaleX;
  private int scaleY;

  /**
   * Instantiates a new Scale.
   *
   * @param scaleX the horizontal scale of the shape
   * @param scaleY the vertical scale of the shape
   */
  public Scale(int scaleX, int scaleY) throws IllegalArgumentException {
    if (scaleX <= 0 || scaleY <= 0) {
      throw new IllegalArgumentException("Invalid value");
    }
    this.scaleX = scaleX;
    this.scaleY = scaleY;
  }

  /**
   * Gets scale x.
   *
   * @return the scale x
   */
  public int getScaleX() {
    return scaleX;
  }

  /**
   * Sets scale x.
   *
   * @param scaleX the scale x
   */
  public void setScaleX(int scaleX) {
    if (scaleX <= 0) {
      throw new IllegalArgumentException("Invalid value");
    }
    this.scaleX = scaleX;
  }

  /**
   * Gets scale y.
   *
   * @return the scale y
   */
  public int getScaleY() {
    return scaleY;
  }

  /**
   * Sets scale y.
   *
   * @param scaleY the scale y
   */
  public void setScaleY(int scaleY) {
    if (scaleY <= 0) {
      throw new IllegalArgumentException("Invalid value");
    }
    this.scaleY = scaleY;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Scale)) return false;
    Scale scale = (Scale) o;
    return getScaleX() == scale.getScaleX() && getScaleY() == scale.getScaleY();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getScaleX(), getScaleY());
  }
}
