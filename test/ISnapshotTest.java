import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.IShape;
import model.ISnapshot;
import model.Oval;
import model.Rectangle;
import model.Snapshot;

import static org.junit.Assert.*;

/**
 * The type Snapshot test.
 */
public class ISnapshotTest {
  private ISnapshot snapshot1;
  private ISnapshot snapshot2;
  private ISnapshot snapshot3;

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
    shape1 = new Oval(200,
            200,
            200,
            "O",
            30,
            60,
            300,
            300);
    shape2 = new Rectangle(
            252,
            33,
            110,
            "R",
            676,
            34,
            200,
            250);
    shape3 = new Rectangle(
            22,
            133,
            120,
            "R1",
            234,
            56,
            44,
            55);
    List<IShape> shapes1 = new ArrayList<>();
    shapes1.add(shape1.createCopy());
    String ID = "44bf642c-9e72-41cb-bae9-982e57988f0e";
    String timestamp = "09-04-2022 13:04:37";
    snapshot1 = new Snapshot(ID, timestamp, "first snapshot", shapes1);
    List<IShape> shapes2 = new ArrayList<>();
    String ID2 = "f3fc6eac-92af-4b4b-9725-0a3c90616225";
    String timestamp2 = "09-04-2022 13:16:37";
    shapes2.add(shape1.createCopy());
    shapes2.add(shape2.createCopy());
    snapshot2 = new Snapshot(ID2, timestamp2, "second", shapes2);
    List<IShape> shapes3 = new ArrayList<>();
    String ID3 = "17f8544c-9e84-4bd5-9ece-92bb5a73a0c7";
    String timestamp3 = "09-04-2022 13:34:37";
    shapes3.add(shape1.createCopy());
    shapes3.add(shape2.createCopy());
    shapes3.add(shape3.createCopy());
    snapshot3 = new Snapshot(ID3, timestamp3, null, shapes3);
  }

  /**
   * Test bad constructor.
   */
  @Test
  public void testBadConstructor() {
    List<IShape> shapes1 = new ArrayList<>();
    shapes1.add(shape1.createCopy());
    try {
      snapshot1 = new Snapshot(null,
              "09-04-2022 13:34:37",
              "hello", shapes1);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      snapshot1 = new Snapshot("",
              "09-04-2022 13:34:37",
              "hello", shapes1);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      snapshot1 = new Snapshot("17f8544c-9e84-4bd5-9ece-92bb5a73a0c7",
              "",
              "hello", shapes1);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      snapshot1 = new Snapshot("17f8544c-9e84-4bd5-9ece-92bb5a73a0c7",
              null,
              "hello", shapes1);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  /**
   * Gets id.
   */
  @Test
  public void getID() {
    assertEquals("44bf642c-9e72-41cb-bae9-982e57988f0e", snapshot1.getID());
    assertEquals("f3fc6eac-92af-4b4b-9725-0a3c90616225", snapshot2.getID());
    assertEquals("17f8544c-9e84-4bd5-9ece-92bb5a73a0c7", snapshot3.getID());
  }

  /**
   * Gets timestamp.
   */
  @Test
  public void getTimestamp() {
    assertEquals("09-04-2022 13:04:37", snapshot1.getTimestamp());
    assertEquals("09-04-2022 13:16:37", snapshot2.getTimestamp());
    assertEquals("09-04-2022 13:34:37", snapshot3.getTimestamp());
  }

  /**
   * Gets shapes.
   */
  @Test
  public void getShapes() {
    List<IShape> expect = new ArrayList<>();
    expect.add(shape1);
    assertEquals(expect, snapshot1.getShapes());
    List<IShape> expect2 = new ArrayList<>();
    expect2.add(shape1);
    expect2.add(shape2);
    assertEquals(expect2, snapshot2.getShapes());
    List<IShape> expect3 = new ArrayList<>();
    expect3.add(shape1);
    expect3.add(shape2);
    expect3.add(shape3);
    assertEquals(expect3, snapshot3.getShapes());
  }

  /**
   * Test get description.
   */
  @Test
  public void testGetDescription() {
    assertEquals("first snapshot", snapshot1.getDescription());
    assertEquals("second", snapshot2.getDescription());
    assertNull(snapshot3.getDescription());
    System.out.println(snapshot1.toString());
    System.out.println(snapshot2.toString());
  }

  /**
   * Test to string.
   */
  @Test
  public void testToString() {
    String expect1 = "Snapshot ID: 44bf642c-9e72-41cb-bae9-982e57988f0e\n"
            + "Timestamp: 09-04-2022 13:04:37\n"
            + "Description: first snapshot\n"
            + "Shape Information:\n"
            + "Name: O\n"
            + "Type: Oval\n"
            + "Center: (300.0,300.0), X radius: 30.0, Y radius: 60.0, Color: (200,200,200)\n\n";
    assertEquals(expect1, snapshot1.toString());
    String expect2 = "Snapshot ID: f3fc6eac-92af-4b4b-9725-0a3c90616225\n"
            + "Timestamp: 09-04-2022 13:16:37\n"
            + "Description: second\n"
            + "Shape Information:\n"
            + "Name: O\n"
            + "Type: Oval\n"
            + "Center: (300.0,300.0), X radius: 30.0, Y radius: 60.0, Color: (200,200,200)\n\n"
            + "Name: R\n"
            + "Type: Rectangle\n"
            + "Min Corner: (200.0,250.0), Width: 676.0, Height: 34.0, Color: (252,33,110)\n\n";
    assertEquals(expect2, snapshot2.toString());
  }
}