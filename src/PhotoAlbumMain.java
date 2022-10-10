import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import controller.IPhotoAlbumController;
import controller.PhotoAlbumController;
import model.IPhotoAlbum;
import model.PhotoAlbumModel;
import view.HTMLView;
import view.IPhotoAlbumView;
import view.GraphicalView;

/**
 * The main class of the program.
 */
public class PhotoAlbumMain {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) throws IllegalArgumentException {
    // if user doesn't provide any arguments, throw an exception
    int num = args.length;
    if (num == 0) {
      throw new IllegalArgumentException("Please at least specify an input file and a view");
    }

    // start to read the arguments and process them
    String inFileName = "";
    String outFileName = "";
    String viewType = "";
    String windowWidth = null;
    String windowHeight = null;
    for (int i = 0; i < num; i++) {
      if ("-in".equals(args[i])) {
        if (i + 1 >= num) {
          throw new IllegalArgumentException("No input file specified");
        }
        inFileName = args[++i];
        // check if the next argument provided is a txt file name
        if (!inFileName.endsWith(".txt")) {
          throw new IllegalArgumentException("Illegal file name/type, need to be a txt file.");
        }
      } else if ("-out".equals(args[i])) {
        if (i + 1 >= num) {
          throw new IllegalArgumentException("No output file specified");
        }
        outFileName = args[++i];
        // check if the next argument provided is an html file name
        if (!outFileName.endsWith(".html")) {
          throw new IllegalArgumentException("Illegal output file name/type,"
                  + " need to be an html file");
        }
      } else if ("-v".equals(args[i]) || args[i].equals("-view")) {
        // if no view is specified, throw an exception
        if (i + 1 >= num) {
          throw new IllegalArgumentException("No view specified");
        }
        viewType = args[++i];
      } else if (args[i].matches("\\d+")) {
        // check whether user provided a view width and height
          windowWidth = args[i];
          windowHeight = args[++i];
      }
    }

    // check if user provided view width and height and store them
    List<String> extraArgs = new ArrayList<>();
    if (windowWidth != null) {
      extraArgs.add(windowWidth);
    }
    if (windowHeight != null) {
      extraArgs.add(windowHeight);
    }
    // if only one number is provided, throw and exception
    if (extraArgs.size() == 1) {
      throw new IllegalArgumentException("Only one number is provided. Need two.");
    }

    // check what type of view user wants to use
    IPhotoAlbumView view;
    if (viewType.equalsIgnoreCase("web")) {
      view = new HTMLView();
    } else if (viewType.equalsIgnoreCase("graphical")) {
      view = new GraphicalView();
    } else {
      throw new IllegalArgumentException("View type not supported");
    }

    // if user wants web view but do not provide an output file, throw an exception
    if ("web".equalsIgnoreCase(viewType) && outFileName.isEmpty()) {
      throw new IllegalArgumentException("Need to specify an output file for HTML view");
    }

    IPhotoAlbum model = new PhotoAlbumModel();
    IPhotoAlbumController controller = new PhotoAlbumController(model, view);
    extraArgs.add(outFileName);

    // check whether input file exists, if not, throw an exception
    File inFile = new File(inFileName);
    if (!inFile.exists()) {
      throw new IllegalArgumentException("File doesn't exist");
    }
    FileReader reader = null;
    try {
      reader = new FileReader(inFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // call the controller to start the program
    controller.go(reader, extraArgs);
  }
}