package model;

import java.util.Objects;

public class Color {
  private int r;
  private int g;
  private int b;

  public Color(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Invalid color parameters");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public void setR(int r) throws IllegalArgumentException {
    if (r < 0 || r > 255) {
      throw new IllegalArgumentException("Invalid value");
    }
    this.r = r;
  }

  public void setG(int g) throws IllegalArgumentException {
    if (g < 0 || g > 255) {
      throw new IllegalArgumentException("Invalid value");
    }
    this.g = g;
  }

  public void setB(int b) throws IllegalArgumentException {
    if (b < 0 || b > 255) {
      throw new IllegalArgumentException("Invalid value");
    }
    this.b = b;
  }

  public int getR() {
    return r;
  }

  public int getG() {
    return g;
  }

  public int getB() {
    return b;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Color)) return false;
    Color color = (Color) o;
    return getR() == color.getR() && getG() == color.getG() && getB() == color.getB();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getR(), getG(), getB());
  }
}
