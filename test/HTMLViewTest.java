import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.IShape;
import model.Oval;
import model.Rectangle;
import model.Snapshot;
import view.HTMLView;

import static org.junit.Assert.*;

/**
 * The type Html view test.
 */
public class HTMLViewTest {
  private List<Snapshot> snapshots;
  private HTMLView view;
  private Snapshot snapshot1;
  private Snapshot snapshot2;
  private Snapshot snapshot3;

  private static final String TEST_OUTPUT = "testHTML.html";

  /**
   * Sets up.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {
    this.snapshots = new ArrayList<>();
    IShape shape1 = new Oval(200,
            200,
            200,
            "O",
            30,
            60,
            300,
            300);
    IShape shape2 = new Rectangle(
            252,
            33,
            110,
            "R",
            676,
            34,
            200,
            250);
    IShape shape3 = new Rectangle(
            22,
            133,
            120,
            "R1",
            234,
            56,
            44,
            55);
    SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String timeStampStr = timeStamp.format(new Timestamp(System.currentTimeMillis()));
    List<IShape> shapes = new ArrayList<>();
    shapes.add(shape1.createCopy());
    shapes.add(shape2.createCopy());

    this.snapshot1 = new Snapshot(
            UUID.randomUUID().toString(),
            timeStampStr,
            "First snapshot",
            shapes);
    this.snapshots.add(snapshot1);
    this.view = new HTMLView();

    List<IShape> shapes2 = new ArrayList<>();
    shapes2.add(shape2.createCopy());
    shapes2.add(shape3.createCopy());
    this.snapshot2 = new Snapshot(
            UUID.randomUUID().toString(),
            timeStampStr,
            "Second snapshot",
            shapes2);

    List<IShape> shapes3 = new ArrayList<>();
    shapes3.add(shape1.createCopy());
    shapes3.add(shape2.createCopy());
    shapes3.add(shape3.createCopy());
    this.snapshot3 = new Snapshot(
            UUID.randomUUID().toString(),
            timeStampStr,
            "Third snapshot",
            shapes3);
  }

  /**
   * test render.
   */
  @Test
  public void testRender() {
    List<String> args = new ArrayList<>();
    args.add(TEST_OUTPUT);
    view.render(this.snapshots, args);
    String expectContent = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "<div>\n" +
            "<div style=\"border: 5px solid red; background-color:#7ddbcd\">\n" +
            "<div>\n" +
            "Description: First snapshot\n" +
            "</div>\n" +
            "<br/>\n" +
            "<svg width=\"1000\" height=\"1000\">\n" +
            "<ellipse cx=\"300\" cy=\"300\" rx=\"30\" ry=\"60\" fill=\"rgb(200,200,200)\">\n" +
            "</ellipse>\n" +
            "<rect x=\"200\" y=\"250\" width=\"676\" height=\"34\" fill=\"rgb(252,33,110)\">\n" +
            "</rect>\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "<br/>\n" +
            "<br/>\n" +
            "</body>\n" +
            "</html>";
    StringBuilder sb = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(TEST_OUTPUT))){
      String line = reader.readLine();
      while (line != null) {
        sb.append(line).append("\n");
        line = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    String actual = sb.toString();
    int start = actual.indexOf("<h2>");
    int end = actual.indexOf("</h2>");
    actual = actual.substring(0, start) + actual.substring(end + 6);
    actual = actual.substring(0, actual.length() - 1);
    assertEquals(expectContent, actual);

    this.snapshots.add(snapshot2);
    this.view.render(this.snapshots, args);

    String expect2 = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "<div>\n" +
            "<div style=\"border: 5px solid red; background-color:#7ddbcd\">\n" +
            "<div>\n" +
            "Description: First snapshot\n" +
            "</div>\n" +
            "<br/>\n" +
            "<svg width=\"1000\" height=\"1000\">\n" +
            "<ellipse cx=\"300\" cy=\"300\" rx=\"30\" ry=\"60\" fill=\"rgb(200,200,200)\">\n" +
            "</ellipse>\n" +
            "<rect x=\"200\" y=\"250\" width=\"676\" height=\"34\" fill=\"rgb(252,33,110)\">\n" +
            "</rect>\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "<br/>\n" +
            "<br/>\n" +
            "<div style=\"border: 5px solid red; background-color:#7ddbcd\">\n" +
            "<div>\n" +
            "Description: Second snapshot\n" +
            "</div>\n" +
            "<br/>\n" +
            "<svg width=\"1000\" height=\"1000\">\n" +
            "<rect x=\"200\" y=\"250\" width=\"676\" height=\"34\" fill=\"rgb(252,33,110)\">\n" +
            "</rect>\n" +
            "<rect x=\"44\" y=\"55\" width=\"234\" height=\"56\" fill=\"rgb(22,133,120)\">\n" +
            "</rect>\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "<br/>\n" +
            "<br/>\n" +
            "</body>\n" +
            "</html>";

    StringBuilder sb2 = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(TEST_OUTPUT));
      String line = reader.readLine();
      while (line != null) {
        sb2.append(line).append("\n");
        line = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    String actual2 = sb2.toString();
    start = actual2.indexOf("<h2>");
    end = actual2.indexOf("</h2>");
    actual2 = actual2.substring(0, start) + actual2.substring(end + 6);

    start = actual2.indexOf("<h2>");
    end = actual2.indexOf("</h2>");
    actual2 = actual2.substring(0, start) + actual2.substring(end + 6);

    actual2 = actual2.substring(0, actual2.length() - 1);
    assertEquals(expect2, actual2);

    args.clear();
    args.add("800");
    args.add("600");
    args.add(TEST_OUTPUT);
    this.snapshots.add(this.snapshot3);
    this.view.render(this.snapshots, args);

    String expect3 = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "<div>\n" +
            "<div style=\"border: 5px solid red; background-color:#7ddbcd\">\n" +
            "<div>\n" +
            "Description: First snapshot\n" +
            "</div>\n" +
            "<br/>\n" +
            "<svg width=\"800\" height=\"600\">\n" +
            "<ellipse cx=\"300\" cy=\"300\" rx=\"30\" ry=\"60\" fill=\"rgb(200,200,200)\">\n" +
            "</ellipse>\n" +
            "<rect x=\"200\" y=\"250\" width=\"676\" height=\"34\" fill=\"rgb(252,33,110)\">\n" +
            "</rect>\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "<br/>\n" +
            "<br/>\n" +
            "<div style=\"border: 5px solid red; background-color:#7ddbcd\">\n" +
            "<div>\n" +
            "Description: Second snapshot\n" +
            "</div>\n" +
            "<br/>\n" +
            "<svg width=\"800\" height=\"600\">\n" +
            "<rect x=\"200\" y=\"250\" width=\"676\" height=\"34\" fill=\"rgb(252,33,110)\">\n" +
            "</rect>\n" +
            "<rect x=\"44\" y=\"55\" width=\"234\" height=\"56\" fill=\"rgb(22,133,120)\">\n" +
            "</rect>\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "<br/>\n" +
            "<br/>\n" +
            "<div style=\"border: 5px solid red; background-color:#7ddbcd\">\n" +
            "<div>\n" +
            "Description: Third snapshot\n" +
            "</div>\n" +
            "<br/>\n" +
            "<svg width=\"800\" height=\"600\">\n" +
            "<ellipse cx=\"300\" cy=\"300\" rx=\"30\" ry=\"60\" fill=\"rgb(200,200,200)\">\n" +
            "</ellipse>\n" +
            "<rect x=\"200\" y=\"250\" width=\"676\" height=\"34\" fill=\"rgb(252,33,110)\">\n" +
            "</rect>\n" +
            "<rect x=\"44\" y=\"55\" width=\"234\" height=\"56\" fill=\"rgb(22,133,120)\">\n" +
            "</rect>\n" +
            "</svg>\n" +
            "</div>\n" +
            "</div>\n" +
            "<br/>\n" +
            "<br/>\n" +
            "</body>\n" +
            "</html>";
    StringBuilder sb3 = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(TEST_OUTPUT));
      String line = reader.readLine();
      while (line != null) {
        sb3.append(line).append("\n");
        line = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    String actual3 = sb3.toString();
    start = actual3.indexOf("<h2>");
    end = actual3.indexOf("</h2>");
    actual3 = actual3.substring(0, start) + actual3.substring(end + 6);
    start = actual3.indexOf("<h2>");
    end = actual3.indexOf("</h2>");
    actual3 = actual3.substring(0, start) + actual3.substring(end + 6);
    start = actual3.indexOf("<h2>");
    end = actual3.indexOf("</h2>");
    actual3 = actual3.substring(0, start) + actual3.substring(end + 6);

    actual3 = actual3.substring(0, actual3.length() - 1);
    assertEquals(expect3, actual3);
  }
}