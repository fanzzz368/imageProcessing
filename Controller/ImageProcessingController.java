package controller;

/**
 * The ImageProcessingController interface represents the controller for the image processing
 * models. It contains the startImageProcess method that allows user to input the commands into
 * the image processor and start using the features. It also allows user to load and save an image.
 */
public interface ImageProcessingController {

  /**
   * The startImageProcess method represents the "go" method for the ImageProcessingController.
   * @throws IllegalStateException only if the controller is
   *            unable to successfully read input or transmit output
   */
  void startImageProcess() throws IllegalStateException;


}
