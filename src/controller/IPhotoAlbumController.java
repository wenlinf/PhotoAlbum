package controller;

import java.util.List;

/**
 * The interface for the controller.
 */
public interface IPhotoAlbumController {
  /**
   * Takes in a Readable and process the data being read it. It calls the methods in IPhotoAlbum
   * based on what is read in and calls the view to render the information.
   *
   * @param in   the in
   * @param args the args
   * @throws IllegalArgumentException the illegal argument exception
   */
  void go(Readable in, List<String> args) throws IllegalArgumentException;
}
