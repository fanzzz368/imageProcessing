package controller.commands;

import model.ImageProcessingModel;

/**
 * This interface is used to make all the commands into one interface. The start method is stored
 * here, so it allows each method to be called and performed.
 */
public interface ImageProcessingCommand {

  /**
   * This method allows the controller to start any of the image processing methods using
   * the image processing methods found in the model. These methods include select-component,
   * vertical-flip, horizontal-flip, greyscale, darkenBrighten, sepia, sharpen, select-
   * representation, and blur.
   * @param model the image that the image processing is being done on
   */
  void start(ImageProcessingModel model);

}
