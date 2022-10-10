package model;

import java.util.List;

/**
 * The type Snapshot. Represents a snapshot taken of the current photo album.
 */
public class Snapshot implements ISnapshot {
  private String ID;
  private String timestamp;
  private String description;
  private List<IShape> shapes;

  /**
   * Instantiates a new Snapshot.
   *
   * @param ID          the id
   * @param timestamp   the timestamp
   * @param description the description
   * @param shapes      the shapes
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Snapshot(String ID, String timestamp, String description, List<IShape> shapes)
          throws IllegalArgumentException{
    if (ID == null || ID.equals("")) {
      throw new IllegalArgumentException("ID cannot be empty");
    }
    if (timestamp == null || timestamp.equals("")) {
      throw new IllegalArgumentException("Must have a timestamp");
    }
    this.ID = ID;
    this.timestamp = timestamp;
    this.description = description;
    this.shapes = shapes;
  }

  @Override
  public String getID() {
    return ID;
  }

  @Override
  public String getTimestamp() {
    return timestamp;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public List<IShape> getShapes() {
    return shapes;
  }

  @Override
  public String toString() {
    String shapeInfoStr = "Shape Information:\n";
    if (this.shapes != null) {
      for (IShape shape : this.shapes) {
        shapeInfoStr += shape.toString() + "\n";
      }
    }

    return "Snapshot ID: " + ID + '\n' +
            "Timestamp: " + timestamp + '\n' +
            "Description: " + description + '\n'
            + shapeInfoStr;
  }
}
