package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The type PhotoAlbumModel. A class that represents a photo album where shapes can be
 * displayed, changed, created, removed, transformed etc.
 */
public class PhotoAlbumModel implements IPhotoAlbum {
  private Map<String, IShape> shapes;
  private List<Snapshot> snapshots;

  /**
   * Instantiates a new PhotoAlbumModel.
   */
  public PhotoAlbumModel() {
    this.shapes = new LinkedHashMap<>();
    this.snapshots = new ArrayList<>();
  }

  @Override
  public Map<String, IShape> getAllShapes() {
    return this.shapes;
  }

  @Override
  public void changeColor(String name, int r, int g, int b)
          throws IllegalArgumentException {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape doesn't exist");
    }
    this.shapes.get(name).setColor(r, g, b);
  }

  @Override
  public void moveShape(String name, int x, int y) throws IllegalArgumentException {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape doesn't exist");
    }
    this.shapes.get(name).setPosition(x, y);
  }

  @Override
  public IShape createShape(String name,
                            String shape,
                            int pos_x,
                            int pos_y,
                            int color_r,
                            int color_g,
                            int color_b,
                            int x,
                            int y) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Must provide a name");
    }
    if (this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape with this name already exists, use another name");
    }
    if (shape == null || shape.equals("")) {
      throw new IllegalArgumentException("Must provide a shape name");
    }
    IShape newShape;
    if (shape.equalsIgnoreCase("Oval")) {
      newShape = new Oval(color_r, color_g, color_b, name,  x, y, pos_x, pos_y);
    } else if (shape.equalsIgnoreCase("rectangle")) {
      newShape = new Rectangle(color_r, color_g, color_b, name, x, y, pos_x, pos_y);
    } else {
      throw new IllegalArgumentException("Shape doesn't exist");
    }
    this.shapes.put(name, newShape);
    return newShape;
  }

  @Override
  public void scaleShape(String name, int scaleX, int scaleY)
          throws IllegalArgumentException {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape doesn't exist");
    }
    if (scaleX <= 0 || scaleY <= 0) {
      throw new IllegalArgumentException("Value should be positive");
    }
    this.shapes.get(name).setScale(scaleX, scaleY);
  }

  @Override
  public void removeShape(String name) throws IllegalArgumentException {
    if (name == null || !shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape doesn't exist");
    }
    shapes.remove(name);
  }

  @Override
  public List<Snapshot> getAllSnapshots() {
    return this.snapshots;
  }

  @Override
  public List<Snapshot> takeSnapshot(String description) {
    String IDStr = UUID.randomUUID().toString();
    SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String timeStampStr = timeStamp.format(new Timestamp(System.currentTimeMillis()));
    List<IShape> currentShapesCopy = new ArrayList<>();
    IShape shape;
    for (Map.Entry<String, IShape> shapeEntry : shapes.entrySet()) {
      shape = shapeEntry.getValue();
      currentShapesCopy.add(shape.createCopy());
    }
    Snapshot snapshot = new Snapshot(
            IDStr,
            timeStampStr,
            description,
            currentShapesCopy);
    this.snapshots.add(snapshot);
    return this.snapshots;
  }
}
