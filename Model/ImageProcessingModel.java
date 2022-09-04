package model;


import java.util.List;

/**
 * This interface represents the operations offered by the image processing
 * model. One object of the model represents an image processor.
 */
public interface ImageProcessingModel extends ReadImageProcessingModel {

  /**
   * Add method adds the given 2D arraylist of pixel to the image processor. It loads the model
   * with the image, so the user can start making operations on it.
   *
   * @param board the image loaded into the image processor
   */
  void add(List<List<Pixel>> board);

  /**
   * The selectComponent method allows the user to create a greyscale version
   * of an image. User has the choice to choose whether they want to visualize
   * the red, green, or blue components of the pixels in the image when they
   * create the greyscale image.
   *
   * @param color the color component visualized in greyscale
   * @param key   the image name of the image user wants to modify
   */
  void selectComponent(String color, String key);

  /**
   * The selectRepresentation method allows the user to create a greyscale version
   * of an image based on the value, intensity, or luma. Value is the maximum value
   * of the three components for each pixel; intensity is the average of the three components
   * for each pixel, and luma is the weighted sum 0.2126r + 0.7152g + 0.0722b.
   *
   * @param representation the representation (value, intensity, or luma) user wants to visualize
   * @param key            the image name of the image user wants to modify
   */
  void selectRepresentation(String representation, String key);

  /**
   * The flipImageHorizontal method allows an image to be flipped horizontally. The left side of
   * the original image is now on the right side of the modified image.
   *
   * @param key the image name of the image user wants to modify
   */
  void flipImageHorizontal(String key);

  /**
   * The flipImageVertical method allows an image to be flipped vertically. The top of
   * the original image is now on the bottom of the modified image.
   *
   * @param key the image name of the image user wants to modify
   */
  void flipImageVertical(String key);

  /**
   * THe brightenDarken method allows an image to be brightened or darkened by a given amount.
   * The larger the given increment, the more the image is brightened or darkened.
   *
   * @param increment how much the image is darkened (negative) /brightened (positive) by
   * @param key       the image name of the image user wants to modify
   */
  void brightenDarken(int increment, String key);

  /**
   * The setName method sets the image name in the image processor to the given name. The given
   * name can be called again if the user wants to reference that image again.
   *
   * @param name the image name
   */
  void setName(String name);

  /**
   * The blur method allows a selected image to be blurred using the "Gaussian" blur. After
   * applying the blur filter, the image will be more blurry than before.
   *
   * @param key the image name of the image user wants to modify
   */
  void blur(String key);

  /**
   * The sharpen method allows a selected image to be sharpened. After
   * applying the sharpened filter, the image will be more crisp than before.
   *
   * @param key the image name of the image user wants to modify
   */
  void sharpen(String key);

  /**
   * The greyscale method takes the luma component of pixel and transforms an image so that it is
   * composed of only shades of grey. The equation 0.02126r + 0.7152g + 0.0722b is used to color
   * an image with color to greyscale.
   * @param key the image name of the image the user wants to modify
   */
  void greyscale(String key);

  /**
   * The sepia method allows a selected image to be colored transformed into another tone.
   * After this color transformation, the image will have a characteristic reddish brown tone.
   * @param key the image name of the image the user wants to modify
   */
  void sepia(String key);

  //  void downscale(int scaleHeight, int scaleWidth, String key);
}
