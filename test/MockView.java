import java.util.List;

import model.Snapshot;
import view.IPhotoAlbumView;

/**
 * The class is a mock view class to test the controller.
 */
public class MockView implements IPhotoAlbumView {
  private StringBuilder log;

  /**
   * Instantiates a new Mock view.
   *
   * @param log the log
   */
  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void render(List<Snapshot> snapshots, List<String> args) {
    for (Snapshot snapshot : snapshots) {
      log.append(snapshot.toString()).append("\n");
    }
    for (int i = 0; i< args.size(); i++) {
      log.append(args.get(i)).append("\n");
    }
  }
}
