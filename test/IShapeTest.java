import org.junit.Before;
import org.junit.Test;

import model.IShape;
import model.Oval;
import model.Rectangle;

import static org.junit.Assert.*;

/**
 * The type Shape test.
 */
public class IShapeTest {
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
  }

  /**
   * Test set color.
   */
  @Test
  public void testSetColor() {
    assertEquals(200, shape1.getColor().getR());
    assertEquals(200, shape1.getColor().getG());
    assertEquals(200, shape1.getColor().getB());
    shape1.setColor(123, 111, 23);
    assertEquals(123, shape1.getColor().getR());
    assertEquals(111, shape1.getColor().getG());
    assertEquals(23, shape1.getColor().getB());
    shape1.setColor(44, 55, 66);
    assertEquals(44, shape1.getColor().getR());
    assertEquals(55, shape1.getColor().getG());
    assertEquals(66, shape1.getColor().getB());

    assertEquals(252, shape2.getColor().getR());
    assertEquals(33, shape2.getColor().getG());
    assertEquals(110, shape2.getColor().getB());
    shape2.setColor(111, 222, 33);
    assertEquals(111, shape2.getColor().getR());
    assertEquals(222, shape2.getColor().getG());
    assertEquals(33, shape2.getColor().getB());
  }

  /**
   * Test illegal set color.
   */
  @Test
  public void testIllegalSetColor() {
    try {
      shape1.setColor(-1, 234, 234);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      shape1.setColor(1, 256, 234);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      shape1.setColor(256, 234, 234);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      shape1.setColor(234, -2, 234);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      shape1.setColor(234, 2, 256);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      shape1.setColor(234, 2, -1);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  /**
   * Sets position.
   */
  @Test
  public void setPosition() {
    assertEquals(300, shape1.getPosition().getPosX(), 0.01);
    assertEquals(300, shape1.getPosition().getPosY(), 0.01);
    shape1.setPosition(234, 555);
    assertEquals(234, shape1.getPosition().getPosX(), 0.01);
    assertEquals(555, shape1.getPosition().getPosY(), 0.01);
    shape1.setPosition(198, 435);
    assertEquals(198, shape1.getPosition().getPosX(), 0.01);
    assertEquals(435, shape1.getPosition().getPosY(), 0.01);

    assertEquals(200, shape2.getPosition().getPosX(), 0.01);
    assertEquals(250, shape2.getPosition().getPosY(), 0.01);
    shape2.setPosition(234, 555);
    assertEquals(234, shape2.getPosition().getPosX(), 0.01);
    assertEquals(555, shape2.getPosition().getPosY(), 0.01);
  }

  /**
   * Sets scale.
   */
  @Test
  public void setScale() {
    assertEquals(30, shape1.getScale().getScaleX());
    assertEquals(60, shape1.getScale().getScaleY());
    shape1.setScale(45, 66);
    assertEquals(45, shape1.getScale().getScaleX());
    assertEquals(66, shape1.getScale().getScaleY());
    shape1.setScale(99, 333);
    assertEquals(99, shape1.getScale().getScaleX());
    assertEquals(333, shape1.getScale().getScaleY());

    assertEquals(234, shape3.getScale().getScaleX());
    assertEquals(56, shape3.getScale().getScaleY());
    shape3.setScale(56, 33);
    assertEquals(56, shape3.getScale().getScaleX());
    assertEquals(33, shape3.getScale().getScaleY());
  }

  /**
   * Test illegal set scale.
   */
  @Test
  public void testIllegalSetScale() {
    try {
      shape2.setScale(-1, 10);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      shape2.setScale(0, 10);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      shape2.setScale(1, 0);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      shape2.setScale(1, -1);
      fail("Failed to throw an exception");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  /**
   * Test create copy.
   */
  @Test
  public void testCreateCopy() {
    assertEquals(shape1, shape1.createCopy());
    assertNotSame(shape1, shape1.createCopy());
    assertEquals(shape2, shape2.createCopy());
    assertNotSame(shape2, shape2.createCopy());
    assertEquals(shape3, shape3.createCopy());
    assertNotSame(shape3, shape3.createCopy());
  }

  /**
   * Gets name.
   */
  @Test
  public void getName() {
    assertEquals("O", shape1.getName());
    assertEquals("R", shape2.getName());
    assertEquals("R1", shape3.getName());
  }

  /**
   * Test equals.
   */
  @Test
  public void testEquals() {
    IShape shape1Equal =  new Oval(200,
            200,
            200,
            "O",
            30,
            60,
            300,
            300);
    assertEquals(shape1Equal, shape1);
    IShape shape2Equals = new Rectangle(
            252,
            33,
            110,
            "R",
            676,
            34,
            200,
            250);
    assertEquals(shape2Equals, shape2);
    IShape shape3Equals = new Rectangle(
            22,
            133,
            120,
            "R1",
            234,
            56,
            44,
            55);
    assertEquals(shape3Equals, shape3);
  }

  /**
   * Test to string.
   */
  @Test
  public void testToString() {
    String expect1 = "Name: O\n"
            + "Type: Oval\n"
            + "Center: (300.0,300.0), X radius: 30.0, Y radius: 60.0, Color: (200,200,200)\n";
    assertEquals(expect1, shape1.toString());
    String expect2 = "Name: R\n"
            + "Type: Rectangle\n"
            + "Min Corner: (200.0,250.0), Width: 676.0, Height: 34.0, Color: (252,33,110)\n";
    assertEquals(expect2, shape2.toString());
    String expect3 = "Name: R1\n"
            + "Type: Rectangle\n"
            + "Min Corner: (44.0,55.0), Width: 234.0, Height: 56.0, Color: (22,133,120)\n";
  }
}