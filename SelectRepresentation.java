package controller.commands;

import model.ImageProcessingModel;

/**
 * This class is one of the commands part of the ImageProcessingCommand interface. It allows the
 * controller to call SelectRepresentation and performs the appropriate command and
 * selects either the luma, intensity, or value.
 */
public class SelectRepresentation implements ImageProcessingCommand {
  private String representation;
  private String previousName;
  private String newName;

  /**
   * This constructor initializes three fields for the method. It includes the component the client
   * wishes to choose as well as the image they want to perform the image processing on and
   * the new image name.
   * @param representation the client can choose from three components : value, intensity, luma
   * @param previousName the image name that the client wants to modify
   * @param newName the name of the new image that the client has modified
   */
  public SelectRepresentation(String representation, String previousName, String newName) {
    this.representation = representation;
    this.previousName = previousName;
    this.newName = newName;
  }

  @Override
  public void start(ImageProcessingModel model) {
    model.setName(newName);
    model.selectRepresentation(representation, previousName);
  }
}
