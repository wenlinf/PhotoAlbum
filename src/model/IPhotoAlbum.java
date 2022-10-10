package model;

import java.util.List;
import java.util.Map;

/**
 * The interface PhotoAlbum.
 */
public interface IPhotoAlbum {
  /**
   * Move a shape on the album.
   *
   * @param name the name of the shape
   * @param x    the new x coordinate of the shape
   * @param y    the new y coordinate of the shape
   */
  void moveShape(String name, int x, int y);

  /**
   * Change the color of a shape.
   *
   * @param name the name of the shape
   * @param r    the r of the color
   * @param g    the g of the color
   * @param b    the b of the color
   */
  void changeColor(String name, int r, int g, int b);

  /**
   * Scale a shape.
   *
   * @param name the name of the shape
   * @param x    the new x scale of the shape
   * @param y    the new y scale of the shape
   */
  void scaleShape(String name, int x, int y);

  /**
   * Remove a shape from the album.
   *
   * @param name the name of the shape
   */
  void removeShape(String name);

  /**
   * Gets all snapshots.
   *
   * @return all snapshots that have been taken
   */
  List<Snapshot> getAllSnapshots();

  /**
   * Take snapshot of the current album.
   *
   * @param description the description of the snapshot
   * @return the list of all snapshots taken so far
   */
  List<Snapshot> takeSnapshot(String description);

  /**
   * Gets all shapes on the album.
   *
   * @return all shapes on the album
   */
  Map<String, IShape> getAllShapes();

  /**
   * Create a shape on the album.
   *
   * @param name    the name
   * @param shape   the shape
   * @param pos_x   the pos x
   * @param pos_y   the pos y
   * @param color_x the color x
   * @param color_y the color y
   * @param color_z the color z
   * @param x       the x
   * @param y       the y
   * @return the shape
   */
  IShape createShape(
          String name,
          String shape,
          int pos_x,
          int pos_y,
          int color_x,
          int color_y,
          int color_z,
          int x,
          int y);
}
