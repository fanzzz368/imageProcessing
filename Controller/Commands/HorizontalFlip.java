package controller.commands;


import model.ImageProcessingModel;

/**
 * This class is one of the commands part of the ImageProcessingCommand interface. It allows the
 * controller to call HorizontalFlip and performs the appropriate command and horizontally
 * flips the image.
 */
public class HorizontalFlip implements ImageProcessingCommand {
  private String previousName;
  private String newName;

  /**
   * This constructor initializes two fields. It allows the client to call to the image they
   * want to modify and the new name for the modified image.
   * @param previousName the image name on which the client wants to image process
   * @param newName the new name for the modified image.
   */
  public HorizontalFlip(String previousName, String newName) {
    this.previousName = previousName;
    this.newName = newName;
  }

  @Override
  public void start(ImageProcessingModel model) {
    model.setName(newName);
    model.flipImageHorizontal(previousName);
  }
}
