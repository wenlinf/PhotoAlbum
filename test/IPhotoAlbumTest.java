import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Color;
import model.IPhotoAlbum;
import model.IShape;
import model.Oval;
import model.PhotoAlbumModel;
import model.Snapshot;

import static org.junit.Assert.*;

/**
 * The type Photo album test.
 */
public class IPhotoAlbumTest {
  private IPhotoAlbum photoAlbum1;

  private IShape shape1;
  private IShape shape2;
  private IShape shape3;

  /**
   * Sets up.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {
    photoAlbum1 = new PhotoAlbumModel();
    shape1 = photoAlbum1.createShape(
            "O",
            "oval",
            200,
            300,
            12,
            11,
            12,
            30,
            60);
    shape2 = photoAlbum1.createShape(
            "R",
            "Rectangle",
            100,
            200,
            234,
            110,
            223,
            56,
            23);
    shape3 = photoAlbum1.createShape(
            "R1",
            "Rectangle",
            100,
            200,
            234,
            110,
            223,
            56,
            23);
  }

  /**
   * Test create shape.
   */
  @Test
  public void testCreateShape() {
    IShape oval1 = photoAlbum1.createShape(
            "O1",
            "oval",
            35,
            24,
            33,
            22,
            45,
            50,
            100);
    Oval expectOval1 = new Oval(
            33,
            22,
            45,
            "O1",
            50,
            100,
            35,
            24);
    assertEquals(expectOval1, oval1);
    IShape oval2 = photoAlbum1.createShape(
            "O2",
            "oval",
            35,
            24,
            33,
            22,
            45,
            50,
            100);
    Oval expectOval2 = new Oval(
            33,
            22,
            45,
            "O2",
            50,
            100,
            35,
            24);
    assertEquals(expectOval2, oval2);
    IShape rectangle1 = photoAlbum1.createShape(
            "R2",
            "Rectangle",
            35,
            24,
            33,
            22,
            45,
            50,
            100);
    Oval expectRect1 = new Oval(
            33,
            22,
            45,
            "R2",
            50,
            100,
            35,
            24);
    assertEquals(expectRect1, rectangle1);
  }

  /**
   * Test create illegal shape.
   */
  @Test
  public void testCreateIllegalShape() {
    try {
      shape1 = photoAlbum1.createShape(
              "",
              "oval",
              200,
              300,
              12,
              12,
              13,
              30,
              60);
      fail("Name cannot be null");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
    try {
      shape1 = photoAlbum1.createShape(
              null,
              "oval",
              200,
              300,
              13,
              11,
              233,
              30,
              60);
      fail("Name cannot be null");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
    // Test creating shape with duplicate name
    try {
      shape2 = photoAlbum1.createShape(
              "O",
              "oval",
              200,
              300,
              33,
              33,
              33,
              30,
              60);
      fail("Name cannot be null");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
    try {
      shape2 = photoAlbum1.createShape(
              "Z",
              null,
              200,
              300,
              44,
              44,
              44,
              30,
              60);
      fail("illegal shape name");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
    try {
      shape2 = photoAlbum1.createShape(
              "P",
              "",
              200,
              300,
              23,
              23,
              23,
              30,
              60);
      fail("illegal shape name");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
    try {
      shape2 = photoAlbum1.createShape(
              "L",
              "randomShape",
              200,
              300,
              55,
              55,
              55,
              30,
              60);
      fail("illegal shape name");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
    try {
      shape2 = photoAlbum1.createShape(
              "O4",
              "oval",
              200,
              300,
              -10,
              23,
              23,
              30,
              60);
      fail("illegal color value");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
    try {
      shape2 = photoAlbum1.createShape(
              "O5",
              "oval",
              200,
              300,
              1,
              300,
              3,
              30,
              60);
      fail("illegal color value");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
    try {
      shape2 = photoAlbum1.createShape(
              "O6",
              "oval",
              200,
              300,
              2,
              34,
              -1,
              30,
              60);
      fail("illegal color value");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
  }

  /**
   * Move shape.
   */
  @Test
  public void moveShape() {
    assertEquals(200, shape1.getPosition().getPosX(), 0.01);
    photoAlbum1.moveShape(shape1.getName(), 300, 200);
    assertEquals(300, shape1.getPosition().getPosX(), 0.01);
    assertEquals(200, shape1.getPosition().getPosY(), 0.01);

    photoAlbum1.moveShape(shape1.getName(), 200, 300);
    assertEquals(300, shape1.getPosition().getPosY(), 0.01);
    assertEquals(200, shape1.getPosition().getPosX(), 0.01);
    assertEquals(300, shape1.getPosition().getPosY(), 0.01);

    assertEquals(100, shape2.getPosition().getPosX(), 0.01);
    assertEquals(200, shape2.getPosition().getPosY(), 0.01);
    photoAlbum1.moveShape(shape2.getName(), 450, 380);
    assertEquals(450, shape2.getPosition().getPosX(), 0.01);
    assertEquals(380, shape2.getPosition().getPosY(), 0.01);

    photoAlbum1.moveShape(shape2.getName(), -100, 340);
    assertEquals(-100, shape2.getPosition().getPosX(), 0.01);
    assertEquals(340, shape2.getPosition().getPosY(), 0.01);
    photoAlbum1.moveShape(shape2.getName(), 0, 0);
    assertEquals(0, shape2.getPosition().getPosX(), 0.01);
    assertEquals(0, shape2.getPosition().getPosY(), 0.01);
  }

  /**
   * Change color.
   */
  @Test
  public void changeColor() {
    Color original = new Color(12, 11, 12);
    assertEquals(original, shape1.getColor());
    Color color = new Color(24, 55, 66);
    photoAlbum1.changeColor(shape1.getName(), 24, 55, 66);
    assertEquals(color, shape1.getColor());
    Color color2 = new Color(233, 233, 233);
    photoAlbum1.changeColor(shape1.getName(), 233, 233, 233);
    assertEquals(color2, shape1.getColor());

    Color original2 = new Color(234, 110, 223);
    assertEquals(original2, shape2.getColor());
    Color color3 = new Color(123, 234, 234);
    photoAlbum1.changeColor(shape2.getName(), 123, 234, 234);
    assertEquals(color3, shape2.getColor());
    Color color4 = new Color(0, 0, 0);
    photoAlbum1.changeColor(shape2.getName(), 0, 0, 0);
    assertEquals(color4, shape2.getColor());
  }

  /**
   * Test illegal change color.
   */
  @Test
  public void testIllegalChangeColor() {
    try {
      photoAlbum1.changeColor(shape2.getName(), -123, 234, 234);
      fail("Illegal color");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      photoAlbum1.changeColor(shape2.getName(), 123, 266, 234);
      fail("Illegal color");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      photoAlbum1.changeColor(shape2.getName(), 123, 234, -234);
      fail("Illegal color");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  /**
   * Scale shape.
   */
  @Test
  public void scaleShape() {
    assertEquals(30, shape1.getScale().getScaleX(), 0.01);
    assertEquals(60, shape1.getScale().getScaleY(), 0.01);
    photoAlbum1.scaleShape(shape1.getName(), 200, 300);
    assertEquals(200, shape1.getScale().getScaleX());
    assertEquals(300, shape1.getScale().getScaleY());
    photoAlbum1.scaleShape(shape1.getName(), 3, 1);
    assertEquals(3, shape1.getScale().getScaleX());
    assertEquals(1, shape1.getScale().getScaleY());
    photoAlbum1.scaleShape("O", 9, 110);
    assertEquals(9, shape1.getScale().getScaleX());
    assertEquals(110, shape1.getScale().getScaleY());

    assertEquals(56, shape2.getScale().getScaleX());
    assertEquals(23, shape2.getScale().getScaleY());
    photoAlbum1.scaleShape(shape2.getName(), 77, 99);
    assertEquals(77, shape2.getScale().getScaleX());
    assertEquals(99, shape2.getScale().getScaleY());
    photoAlbum1.scaleShape(shape2.getName(), 249, 222);
    assertEquals(249, shape2.getScale().getScaleX());
    assertEquals(222, shape2.getScale().getScaleY());
    photoAlbum1.scaleShape("R", 123, 231);
    assertEquals(123, shape2.getScale().getScaleX());
    assertEquals(231, shape2.getScale().getScaleY());
  }

  /**
   * Test illegal scale shape.
   */
  @Test
  public void testIllegalScaleShape() {
    try {
      photoAlbum1.scaleShape(shape1.getName(), -1, 100);
      fail("Illegal scale");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
    try {
      photoAlbum1.scaleShape("randomName", -1, 100);
      fail("Shape with given name doesn't exist");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
    try {
      photoAlbum1.scaleShape(shape1.getName(), 1, -100);
      fail("Shape with given name doesn't exist");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
  }

  /**
   * Remove shape.
   */
  @Test
  public void removeShape() {
    Map<String, IShape> allShapes = photoAlbum1.getAllShapes();
    assertEquals(3, allShapes.size());
    photoAlbum1.createShape("O1", "oval", 500, 200, 4, 3, 2, 4, 6);
    assertEquals(4, allShapes.size());
    assertTrue(allShapes.containsKey("O"));
    photoAlbum1.removeShape("O");
    allShapes = photoAlbum1.getAllShapes();
    assertEquals(3, allShapes.size());
    assertFalse(allShapes.containsKey("O"));

    assertTrue(allShapes.containsKey("R"));
    photoAlbum1.removeShape("R");
    assertEquals(2, allShapes.size());
    assertFalse(allShapes.containsKey("R"));

    assertTrue(allShapes.containsKey("O1"));
    photoAlbum1.removeShape("O1");
    allShapes = photoAlbum1.getAllShapes();
    assertEquals(1, allShapes.size());
    assertFalse(allShapes.containsKey("O1"));
  }

  /**
   * Test illegal remove shape.
   */
  @Test
  public void testIllegalRemoveShape() {
    try {
      photoAlbum1.removeShape("Not exist");
      fail("Failed to throw and exception");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
    try {
      photoAlbum1.removeShape(null);
      fail("Failed to throw and exception");
    } catch (IllegalArgumentException e) {
      //Test passed
    }
  }

  /**
   * Gets all snapshots.
   */
  @Test
  public void getAllSnapshots() {
    SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    photoAlbum1.takeSnapshot("test");
    List<Snapshot> allSnapshots = photoAlbum1.getAllSnapshots();
    Snapshot snapshot1 = allSnapshots.get(0);
    assertEquals(snapshot1.getTimestamp(),
            timeStamp.format(new Timestamp(System.currentTimeMillis())));
    List<IShape> shapes1 = snapshot1.getShapes();
    List<IShape> expectShapes1 = new ArrayList<>();
    expectShapes1.add(shape1);
    expectShapes1.add(shape2);
    expectShapes1.add(shape3);
    assertEquals(expectShapes1, shapes1);
    shape1.setColor(34, 34, 34);
    assertNotEquals(expectShapes1, shapes1);
    assertEquals("test", snapshot1.getDescription());
    System.out.println(snapshot1);
  }

  /**
   * Test take snapshot.
   */
  @Test
  public void testTakeSnapshot() {
    String pattern = "dd-MM-yyyy HH:mm:ss";
    SimpleDateFormat timeStamp = new SimpleDateFormat(pattern);
    photoAlbum1.takeSnapshot("take a snapshot");
    List<Snapshot> allSnapshots = photoAlbum1.getAllSnapshots();
    Snapshot snapshot1 = allSnapshots.get(0);
    assertEquals(snapshot1.getTimestamp().substring(0, pattern.length() - 3),
            timeStamp.format(new Timestamp(System.currentTimeMillis()))
                    .substring(0, pattern.length() - 3));
    List<IShape> shapes1 = snapshot1.getShapes();
    List<IShape> expectShapes1 = new ArrayList<>();
    expectShapes1.add(shape1);
    expectShapes1.add(shape2);
    expectShapes1.add(shape3);
    assertEquals(expectShapes1, shapes1);
    shape1.setColor(23, 23, 23);
    assertNotEquals(expectShapes1, shapes1);
    assertEquals("take a snapshot", snapshot1.getDescription());
    System.out.println(snapshot1);

    photoAlbum1.takeSnapshot("take another snapshot");
    allSnapshots = photoAlbum1.getAllSnapshots();
    Snapshot snapshot2 = allSnapshots.get(1);
    assertEquals(snapshot2.getTimestamp().substring(0, pattern.length() - 3),
            timeStamp.format(new Timestamp(System.currentTimeMillis()))
                    .substring(0, pattern.length() - 3));
    List<IShape> shapes2 = snapshot2.getShapes();
    assertEquals(expectShapes1, shapes2);
    assertEquals("take another snapshot", snapshot2.getDescription());
    System.out.println(snapshot2);

    shape3.setPosition(333, 333);
    assertNotEquals(expectShapes1, shapes2);
    photoAlbum1.takeSnapshot("take a third snapshot");
    allSnapshots = photoAlbum1.getAllSnapshots();
    Snapshot snapshot3 = allSnapshots.get(2);
    assertEquals(snapshot3.getTimestamp().substring(0, pattern.length() - 3),
            timeStamp.format(new Timestamp(System.currentTimeMillis()))
                    .substring(0, pattern.length() - 3));
    assertEquals(expectShapes1, snapshot3.getShapes());
    assertEquals("take a third snapshot", snapshot3.getDescription());
    System.out.println(snapshot3);
  }
}