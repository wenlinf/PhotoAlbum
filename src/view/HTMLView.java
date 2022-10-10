package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.IShape;
import model.Oval;
import model.Rectangle;
import model.Snapshot;

/**
 * The HTML view of the photo album.
 */
public class HTMLView implements IPhotoAlbumView {
  private int pageWidth;
  private int pageHeight;

  @Override
  public void render(List<Snapshot> snapshots, List<String> args) {
    String outFileName;
    // if user does not provide a view width and view height, the view is default to 1000 * 1000
    if (args.size() == 1) {
      pageWidth = 1000;
      pageHeight = 1000;
      outFileName = args.get(0);
    } else {
      pageWidth = Integer.parseInt(args.get(0));
      pageHeight = Integer.parseInt(args.get(1));
      outFileName = args.get(2);
    }
    File file = new File(outFileName);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write("<!DOCTYPE html>\n");
      writer.write("<html>\n<body>\n");
      writer.write("<div>\n");

      int num = snapshots.size();
      for (int i = 0; i < num; i++) {
        writer.write("<div style=\"border: 5px solid red; background-color:#7ddbcd\">\n");
        writer.write(
                "<h2> Snapshot " + (i + 1) + " - "
                + snapshots.get(i).getID()
                + "</h2>\n");
        writer.write("<div>\nDescription: ");
        if (snapshots.get(i).getDescription() != null) {
          writer.write(snapshots.get(i).getDescription());
        }
        writer.write("\n</div>\n<br/>\n");
        writer.write("<svg width=\"" + this.pageWidth
                + "\" height=\"" + this.pageHeight + "\">\n");
        for (int j = 0; j < snapshots.get(i).getShapes().size(); j++) {
          writeHTML(writer, snapshots.get(i).getShapes().get(j));
        }
        writer.write("</svg>\n</div>\n");
        writer.write("</div>\n<br/>\n<br/>\n");
      }
      writer.write("</body>\n</html>");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Helper method to write all the shapes into the HTML file.
   */
  private void writeHTML(BufferedWriter writer, IShape shape) throws IOException {
    if (shape instanceof Oval) {
      writer.write("<ellipse cx=\"" + shape.getPosition().getPosX()
              + "\" cy=\"" + shape.getPosition().getPosY()
              + "\" rx=\"" + shape.getScale().getScaleX()
              + "\" ry=\"" + shape.getScale().getScaleY()
              + "\" fill=\"rgb(" + shape.getColor().getR() + ","
              + shape.getColor().getG() + ","
              + shape.getColor().getB() + ")\">\n</ellipse>\n");
    } else if (shape instanceof Rectangle) {
      writer.write("<rect x=\"" + shape.getPosition().getPosX()
              + "\" y=\"" + shape.getPosition().getPosY()
              + "\" width=\"" + shape.getScale().getScaleX()
              + "\" height=\"" + shape.getScale().getScaleY()
              + "\" fill=\"rgb(" + shape.getColor().getR() + ","
              + shape.getColor().getG() + ","
              + shape.getColor().getB() + ")\">\n</rect>\n");
    }
  }
}
