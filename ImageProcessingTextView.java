package view;

import java.io.IOException;

import model.ImageProcessingModel;

/**
 * The ImageProcessingTextView class is an implementation of the ImageProcessingView. It takes in
 * an ImageProcessingModel model and an out appendable and contains a renderMessage that
 * allows messages to be transmitted to the controller.
 */
public class ImageProcessingTextView implements ImageProcessingView {

  private Appendable out;

  // has model as a field in case future designs require the model to be inputted into the view
  private ImageProcessingModel model;
  // make all the fields private

  /**
   * The first constructor for the ImageProcessingTextView class. It takes in a non-null model
   * and a System.out out appendable.
   * @param model an ImageProcessingModel model
   * @throws IllegalArgumentException when the provided model is null
   */
  public ImageProcessingTextView(ImageProcessingModel model)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The provided model is null.");
    }
    this.model = model;
    this.out = System.out;
  }

  /**
   * The second constructor for ImageProcessingTextView class. It takes in a non-null model and
   * an out appendable.
   * @param model an ImageProcessingModel model
   * @param out the out appendable
   * @throws IllegalArgumentException when the provided model is null
   */
  public ImageProcessingTextView(ImageProcessingModel model, Appendable out)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The provided model is null.");
    }
    this.model = model;
    this.out = out;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }
}

