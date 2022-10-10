package controller;

import java.util.List;
import java.util.Scanner;

import model.IPhotoAlbum;
import model.Snapshot;
import view.IPhotoAlbumView;

/**
 * The controller of the program.
 */
public class PhotoAlbumController implements IPhotoAlbumController {
  private IPhotoAlbumView view;
  private IPhotoAlbum model;

  /**
   * Instantiates a new Photo album controller.
   *
   * @param model the model
   * @param view  the view
   */
  public PhotoAlbumController(IPhotoAlbum model, IPhotoAlbumView view) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void go(Readable in, List<String> args) throws IllegalArgumentException {
    Scanner scanner = new Scanner(in);
    String name;
    int r;
    int g;
    int b;
    int width;
    int height;
    int posX;
    int posY;
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] arguments = line.trim().split("\\s+");
      String firstArg = arguments[0];
      if ("shape".equalsIgnoreCase(firstArg)) {
        // if the number of arguments is not enough to create a shape, throw an exception
        if (arguments.length != 10) {
          throw new IllegalArgumentException("Wrong number of arguments to create shape");
        }
        name = arguments[1];
        String type = arguments[2];
        try {
          posX = Integer.parseInt(arguments[3]);
          posY = Integer.parseInt(arguments[4]);
          width = Integer.parseInt(arguments[5]);
          height = Integer.parseInt(arguments[6]);
          r = Integer.parseInt(arguments[7]);
          g = Integer.parseInt(arguments[8]);
          b = Integer.parseInt(arguments[9]);
        } catch (NumberFormatException e) {
          // if parameters provided are not integers, throw an exception
          throw new IllegalArgumentException("Wrong type of arguments to create a shape");
        }
        this.model.createShape(
                name,
                type,
                posX,
                posY,
                r,
                g,
                b,
                width,
                height);
      } else if ("move".equalsIgnoreCase(firstArg)) {
        if (arguments.length != 4) {
          throw new IllegalArgumentException("Wrong number of arguments to move shape");
        }
        name = arguments[1];
        try {
          posX = Integer.parseInt(arguments[2]);
          posY = Integer.parseInt(arguments[3]);
        } catch (NumberFormatException e) {
          // if parameters provided are not integers, throw an exception
          throw new IllegalArgumentException("Wrong type of arguments to move a shape");
        }
        this.model.moveShape(name, posX, posY);
      } else if ("color".equalsIgnoreCase(firstArg)) {
        if (arguments.length != 5) {
          throw new IllegalArgumentException("Wrong number of arguments to change color");
        }
        name = arguments[1];
        try {
          r = Integer.parseInt(arguments[2]);
          g = Integer.parseInt(arguments[3]);
          b = Integer.parseInt(arguments[4]);
        } catch (NumberFormatException e) {
          // if parameters provided are not integers, throw an exception
          throw new IllegalArgumentException("Wrong type of arguments to change color");
        }
        this.model.changeColor(name, r, g, b);
      } else if ("resize".equalsIgnoreCase(firstArg)) {
        if (arguments.length != 4) {
          throw new IllegalArgumentException("Wrong number of arguments to resize shape");
        }
        name = arguments[1];
        try {
          width = Integer.parseInt(arguments[2]);
          height = Integer.parseInt(arguments[3]);
        } catch (NumberFormatException e) {
          // if parameters provided are not integers, throw an exception
          throw new IllegalArgumentException("Wrong type of arguments to resize shape");
        }
        this.model.scaleShape(name, width, height);
      } else if ("remove".equalsIgnoreCase(firstArg)) {
        if (arguments.length != 2) {
          throw new IllegalArgumentException("Wrong number of arguments to remove shape");
        }
        name = arguments[1];
        this.model.removeShape(name);
      } else if ("snapshot".equalsIgnoreCase(firstArg)) {
        StringBuilder description = new StringBuilder();
        if (arguments.length > 1) {
          for (int i = 1; i < arguments.length; i++) {
            description.append(arguments[i]).append(" ");
          }
        }
        this.model.takeSnapshot(description.toString());
      }
    }
    List<Snapshot> snapshots = this.model.getAllSnapshots();
    this.view.render(snapshots, args);
  }
}
