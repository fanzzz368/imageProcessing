package model;

import java.util.List;
import java.util.Map;

/**
 * The ReadImageProcessingModel interface contains all the methods in the model that does not
 * mutate anything. ImageProcessingModel is an extension of this interface.
 */
public interface ReadImageProcessingModel {

  /**
   * The getImage method returns the image the user wants to get by matching the image with the
   * inputted image name. The requested image should be already stored in the imageLibrary (map of
   * 2D arraylist of pixel) for the method to be able to return anything.
   *
   * @param image the name of the requested image
   * @return a 2D arraylist of Pixel (an image)
   */
  List<List<Pixel>> getImage(String image);

  /**
   * The setComponentLibrary method returns the red, green, or blue components of an image the
   * user wants to get by matching the image with the inputted image name. The requested image
   * should be already stored in the imageLibrary (map of 2D arraylist of pixel) for the method to
   * be able to return anything.
   * @param image the name of the requested image
   * @return am arraylist of red, green, or blue components of an image
   */
  Map<Integer, Integer> getComponentLibrary(String image, String color);

  /**
   * Gets the width of the image. Counts the number of rows in a 2D arraylist.
   *
   * @return the width of the image loaded in the image processor
   */
  int getImageWidth();

  /**
   * Gets the height of the image. Counts the number of columns in a 2D arraylist.
   *
   * @return the height of the image loaded in the image processor.
   */
  int getImageHeight();


}
