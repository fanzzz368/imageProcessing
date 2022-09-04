package controller.commands;

import model.ImageProcessingModel;

/**
 * This class is one of the commands part of the ImageProcessingCommand interface. It allows the
 * controller to call VerticalFlip and performs the appropriate command and vertically flips the
 * image.
 */
public class VerticalFlip implements ImageProcessingCommand {
  private String previousName;
  private String newName;

  /**
   * This method initializes two fields. The fields are the current image name and the new modified
   * image's name.
   * @param previousName the image that the client wants to edit
   * @param newName the new image name that the client has modified
   */
  public VerticalFlip(String previousName, String newName) {
    this.previousName = previousName;
    this.newName = newName;
  }

  @Override
  public void start(ImageProcessingModel model) {
    model.setName(newName);
    model.flipImageVertical(previousName);
  }
}
