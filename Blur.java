package controller.commands;

import model.ImageProcessingModel;

/**
 * This class is one of the commands part of the ImageProcessingCommand interface. It allows the
 * controller to call Blue and performs the appropriate command and blurs the image.
 */
public class Blur implements ImageProcessingCommand {

  private String previousName;
  private String newName;

  /**
   * This constructor initializes two fields. It allows the client to call the image that they
   * want to modify as well as set a new name for the image.
   * @param previousName the image name that the client wants to modify
   * @param newName the new name for the modified image
   */
  public Blur(String previousName, String newName) {
    this.previousName = previousName;
    this.newName = newName;
  }

  @Override
  public void start(ImageProcessingModel model) {
    model.setName(newName);
    model.blur(previousName);
  }
}
