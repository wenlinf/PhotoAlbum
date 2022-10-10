import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.IPhotoAlbum;
import model.IShape;
import model.Oval;
import model.Snapshot;

/**
 * The class is a mock model class to test the controller.
 */
public class MockModel implements IPhotoAlbum {
  private StringBuilder log;

  /**
   * Instantiates a new Mock model.
   *
   * @param log the log
   */
  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void moveShape(String name, int x, int y) {
    log.append("move shape: " + name + " x: " + x + " y: " + y + "\n");
  }

  @Override
  public void changeColor(String name, int r, int g, int b) {
    log.append("change color: " + name + " r: " + r + " g: " + g + " b: " + b + "\n");
  }

  @Override
  public void scaleShape(String name, int x, int y) {
    log.append("scale shape: " + name + " x: " + x + " y: " + y + "\n");
  }

  @Override
  public void removeShape(String name) {
    log.append("Remove shape: " + name + "\n");
  }

  @Override
  public List<Snapshot> getAllSnapshots() {
    List<Snapshot> snapshots = new ArrayList<>();
    Snapshot snapshot1 = new Snapshot(
            "randomID",
            "randomTiestamp",
            "description",
            null);
    snapshots.add(snapshot1);
    return snapshots;
  }

  @Override
  public List<Snapshot> takeSnapshot(String description) {
    log.append("snapshot taken. Description: " + description + "\n");
    return null;
  }

  @Override
  public Map<String, IShape> getAllShapes() {
    Map<String, IShape> shapes = new LinkedHashMap<>();
    Oval oval = new Oval(
            233,
            233,
            233,
            "oval",
            60,
            30,
            300,
            200);
    shapes.put("test", oval);
    return shapes;
  }

  @Override
  public IShape createShape(
          String name,
          String shape,
          int pos_x,
          int pos_y,
          int color_x,
          int color_y,
          int color_z,
          int x,
          int y) {
    this.log.append(
            "name: " + name
            + ", shape: " + shape
            + ", pos_x: " + pos_x
            + ", pos_y: " + pos_y
            + ", color_x: " + color_x
            + ", color_y: " + color_y
            + ", color_z: " + color_z
            + ", x: " + x
            + ", y: " + y + "\n");
    return null;
  }
}
