package view;

import java.io.IOException;

/**
 * This interface represents operations that should be offered by
 * a view for the image processor. The view messages to be transmitted to the controller.
 */
public interface ImageProcessingView {

  /**
   * It transmits an inputted message to the provided data destination.
   * It takes in no arguments.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;

}
