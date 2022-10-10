package model;

import java.util.List;

/**
 * The interface Snapshot.
 */
public interface ISnapshot {
  /**
   * Gets the id of the snapshot.
   *
   * @return the id String
   */
  String getID();

  /**
   * Gets the timestamp of the snapshot.
   *
   * @return the timestamp String
   */
  String getTimestamp();

  /**
   * Gets description.
   *
   * @return the description String
   */
  String getDescription();

  /**
   * Gets the shapes of the snapshot.
   *
   * @return the list of all shapes of the snapshot
   */
  List<IShape> getShapes();
}
