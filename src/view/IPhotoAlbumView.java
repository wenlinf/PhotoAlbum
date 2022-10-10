package view;

import java.util.List;

import model.Snapshot;

/**
 * The interface Photo album view.
 */
public interface IPhotoAlbumView {
  /**
   * Render the snapshots on the view specified by the user.
   *
   * @param snapshots the snapshots to be rendered
   * @param args      the optional arguments, including view width, view height, output file name
   */
  void render(List<Snapshot> snapshots, List<String> args);
}
