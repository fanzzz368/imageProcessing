package controller.commands;

import model.ImageProcessingModel;

/**
 * This class is one of the commands part of the ImageProcessingCommand interface. It allows the
 * controller to call DarkenBrighten, performs the appropriate command, and brighten
 * or darken the image.
 */
public class DarkenBrighten implements ImageProcessingCommand {

  private int increment;
  private String previousName;
  private String newName;


  /**
   * This constructor initializes three fields. It allows the client to select value to
   * change rgb values by, call the image that they want to modify,
   * as well as set a new name for the image.
   * @param increment the value image is brighten or darken by
   * @param previousName the image name that the client wants to modify
   * @param newName the new name for the modified image
   */
  public DarkenBrighten(int increment, String previousName, String newName) {
    this.increment = increment;
    this.previousName = previousName;
    this.newName = newName;
  }

  public void start(ImageProcessingModel model) {
    model.setName(newName);
    model.brightenDarken(increment, previousName);
  }
}
