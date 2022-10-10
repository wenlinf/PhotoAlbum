import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import controller.IPhotoAlbumController;
import controller.PhotoAlbumController;
import model.IPhotoAlbum;
import view.IPhotoAlbumView;

import static org.junit.Assert.*;

/**
 * The type Photo album controller test.
 */
public class IPhotoAlbumControllerTest {
  private IPhotoAlbumView view;
  private IPhotoAlbum model;
  private IPhotoAlbumController controller1;

  /**
   * Test go.
   */
  @Test
  public void testGo() {
    // test that the controller can call the correct method based on input
    StringBuilder viewLog = new StringBuilder();
    // create a mock view for testing
    this.view = new MockView(viewLog);
    // create a mock model for testing
    StringBuilder log = new StringBuilder();
    this.model = new MockModel(log);
    this.controller1 = new PhotoAlbumController(model, view);
    List<String> args = new ArrayList<>();
    args.add("800");
    args.add("600");
    args.add("output.txt");
    StringReader command = new StringReader("shape   myrect   rectangle  200  200 50  100  255  0  0");
    controller1.go(command, args);
    assertEquals("name: myrect, shape: rectangle, pos_x: 200, pos_y: 200, color_x: 255, "
            + "color_y: 0, color_z: 0, x: 50, y: 100\n", log.toString());
    assertEquals("Snapshot ID: randomID\n"
            + "Timestamp: randomTiestamp\n"
            + "Description: description\n"
            + "Shape Information:\n"
            + "\n"
            + "800\n"
            + "600\n"
            + "output.txt\n", viewLog.toString());
    command = new StringReader("snapShot After first selfie");
    controller1.go(command, args);
    assertEquals("name: myrect, shape: rectangle, pos_x: 200, pos_y: 200, color_x: 255, "
            + "color_y: 0, color_z: 0, x: 50, y: 100\n"
            + "snapshot taken. Description: After first selfie \n", log.toString());
    assertEquals("Snapshot ID: randomID\n" +
            "Timestamp: randomTiestamp\n" +
            "Description: description\n" +
            "Shape Information:\n" +
            "\n" +
            "800\n" +
            "600\n" +
            "output.txt\n" +
            "Snapshot ID: randomID\n" +
            "Timestamp: randomTiestamp\n" +
            "Description: description\n" +
            "Shape Information:\n" +
            "\n" +
            "800\n" +
            "600\n" +
            "output.txt\n", viewLog.toString());
    command = new StringReader("resize   myrect   200 300");
    controller1.go(command, args);
    assertEquals("name: myrect, shape: rectangle, pos_x: 200, pos_y: 200, color_x: 255, "
            + "color_y: 0, color_z: 0, x: 50, y: 100\n"
            + "snapshot taken. Description: After first selfie \n"
            + "scale shape: myrect x: 200 y: 300\n", log.toString());
    assertEquals("Snapshot ID: randomID\n"
            + "Timestamp: randomTiestamp\n"
            + "Description: description\n"
            + "Shape Information:\n"
            + "\n"
            + "800\n"
            + "600\n"
            + "output.txt\n"
            + "Snapshot ID: randomID\n"
            + "Timestamp: randomTiestamp\n"
            + "Description: description\n"
            + "Shape Information:\n"
            + "\n"
            + "800\n"
            + "600\n"
            + "output.txt\n"
            + "Snapshot ID: randomID\n"
            + "Timestamp: randomTiestamp\n"
            + "Description: description\n"
            + "Shape Information:\n"
            + "\n"
            + "800\n"
            + "600\n"
            + "output.txt\n", viewLog.toString());
    command = new StringReader("color myrect 233 244 255");
    controller1.go(command, args);
    assertEquals("name: myrect, shape: rectangle, pos_x: 200, pos_y: 200, color_x: 255, "
            + "color_y: 0, color_z: 0, x: 50, y: 100\n"
            + "snapshot taken. Description: After first selfie \n"
            + "scale shape: myrect x: 200 y: 300\n"
            + "change color: myrect r: 233 g: 244 b: 255\n", log.toString());
    assertEquals("Snapshot ID: randomID\n"
            + "Timestamp: randomTiestamp\n"
            + "Description: description\n"
            + "Shape Information:\n"
            + "\n"
            + "800\n"
            + "600\n"
            + "output.txt\n"
            + "Snapshot ID: randomID\n"
            + "Timestamp: randomTiestamp\n"
            + "Description: description\n"
            + "Shape Information:\n"
            + "\n"
            + "800\n"
            + "600\n"
            + "output.txt\n"
            + "Snapshot ID: randomID\n"
            + "Timestamp: randomTiestamp\n"
            + "Description: description\n"
            + "Shape Information:\n"
            + "\n"
            + "800\n"
            + "600\n"
            + "output.txt\n"
            + "Snapshot ID: randomID\n"
            + "Timestamp: randomTiestamp\n"
            + "Description: description\n"
            + "Shape Information:\n"
            + "\n"
            + "800\n"
            + "600\n"
            + "output.txt\n", viewLog.toString());
    command = new StringReader("remove myrect");
    controller1.go(command, args);
    assertEquals("name: myrect, shape: rectangle, pos_x: 200, pos_y: 200, color_x: 255, "
            + "color_y: 0, color_z: 0, x: 50, y: 100\n"
            + "snapshot taken. Description: After first selfie \n"
            + "scale shape: myrect x: 200 y: 300\n"
            + "change color: myrect r: 233 g: 244 b: 255\n"
            + "Remove shape: myrect\n", log.toString());
    command = new StringReader("move myrect 200 200");
    controller1.go(command, args);
    assertEquals("name: myrect, shape: rectangle, pos_x: 200, pos_y: 200, color_x: 255, "
            + "color_y: 0, color_z: 0, x: 50, y: 100\n"
            + "snapshot taken. Description: After first selfie \n"
            + "scale shape: myrect x: 200 y: 300\n"
            + "change color: myrect r: 233 g: 244 b: 255\n"
            + "Remove shape: myrect\n"
            + "move shape: myrect x: 200 y: 200\n", log.toString());
  }

  /**
   * Test bad go.
   */
  @Test
  public void testBadGo() {
    StringBuilder viewLog = new StringBuilder();
    this.view = new MockView(viewLog);
    // create a mock model for testing
    StringBuilder log = new StringBuilder();
    this.model = new MockModel(log);
    this.controller1 = new PhotoAlbumController(model, view);
    // test when there are not enough parameters to create a shape
    List<String> args = new ArrayList<>();
    args.add("output.txt");
    try {
      StringReader command = new StringReader("shape S0 rectangle 100 75 20 15 255 0");
      controller1.go(command, args);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // test passed
    }
    try {
      StringReader command = new StringReader("resize S3 200");
      controller1.go(command, args);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // test passed
    }
    try {
      StringReader command = new StringReader("color S0 0 255");
      controller1.go(command, args);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // test passed
    }
    try {
      StringReader command = new StringReader("move S0 600 ");
      controller1.go(command, args);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // test passed
    }
    try {
      StringReader command = new StringReader("remove");
      controller1.go(command, args);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // test passed
    }
    // test when paramter is not an integer
    try {
      StringReader command = new StringReader("shape S0 rectangle 100 75 20 15 255 0 f");
      controller1.go(command, args);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // test passed
    }
    try {
      StringReader command = new StringReader("move S0 600 ff");
      controller1.go(command, args);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // test passed
    }
    try {
      StringReader command = new StringReader("resize S0 600 ff");
      controller1.go(command, args);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // test passed
    }
    try {
      StringReader command = new StringReader("color S0 233 90 ff");
      controller1.go(command, args);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // test passed
    }
  }
}