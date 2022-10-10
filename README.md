# PhotoAlbum
A photo album application that can create animated photos by reading in a text file. A sample file can be found in resources/buildings.txt.

The file contains a list of commands our program needs to execute. Our program will create shapes based on the type of the shapes, positions, sizes, that it reads from the input file. It will create screenshots if the file asks the program to create a screenshot.

The photo album have two different views, one is an interactive graphical view, the other one is a static HTML view

User can run the program with commands. User can use -v to specify what view they want to see, for example:
- MyProgram -in buildings.txt -out myWeb.html  -v web
- MyProgram -in buildings.txt -v graphical 800 800

# Design

## Classes and Interfaces
### Model
- IPhotoAlbum: an interface defining what a photo model should be able to do.
    - It can creat, transform, move, scale, remove shapes from the photo model as well as getting all
      the shapes currently on the model.
    - It can take a snapshot of the model and get all the snapshots that have been taken so far.
- PhotoAlbumModel:
    - implements the IPhotoAlbum interface.
- IShape: an interface defining what a shape on a photo model should be able to do.
    - It can set and get the color, scale, position of the shape itself.
    - It can create a copy of itself.
- AbstractShape:
    - an abstract class that implements common methods  of the IShape interface that
      will be shared among all concrete shape classes.
- Oval, Rectangle:
    - concrete classes that extend the AbstractShape class.
- Color:
    - represents a color using RGB. Used by shape classes to represent the color of
      the shapes.
- Position:
    - represents the position with the X and Y coordinates. Used by shape classes to represent
      the position of the shapes on the model.
- Scale:
    - represents the scale of a shape horizontally and vertically.
- ISnapshot: an interface defining what a snapshot should be able to do.
    - It can get the ID, timestamp, description, and all the shapes of the snapshot taken.
- Snapshot:
    - implements the ISnapshot interface.

### Controller
- IPhotoAlbumController: an interface defining what a photo album controller should be able to do.
    - It can start the program by parsing the commands in the input file, call the methods in the
      model, and calls the view to render the information.
- PhotoAlbumController:
    - Implements the IPhotoAlbumController interface.

### View
- IPhotoAlbumView: an interface defining what a photo album view should be able to do.
    - It can render the snapshots that are passed to the view.
- GraphicalView:
    - implements the IPhotoAlbumView, uses Swing to render the view to user.
- HTMLView:
    - implements the IPhotoAlbumView, generates an HTML file
- ShapePainter:
    - A helper class to paint the snapshot panel for the GraphicalView class.

### Main class
- PhotoAlbumMain: the application entry point

# Changes Made
- data types for x and y coordinates of shapes: changed from double to int to work better with the
  swing view
