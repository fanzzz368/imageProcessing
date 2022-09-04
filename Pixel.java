package model;

/**
 * The Pixel interface represents the operations offered by the pixel implementation. It allows
 * a pixel's r,g,b component to be retrieved and set to a value.
 */
public interface Pixel {

  /**
   * Gets the r value of the pixel. Returns the r value.
   * @return the pixel's r value
   */
  int getRColor();

  /**
   * Sets the r value of the pixel to the given value. Change the original value of r.
   * @param value the value of r component
   */
  void setRValue(int value);

  /**
   * Gets the g value of the pixel. Returns the g value.
   * @return the pixel's g value
   */
  int getGColor();

  /**
   * Sets the g value of the pixel to the given value. Change the original value of g.
   * @param value the value of g component
   */
  void setGValue(int value);

  /**
   * Gets the b value of the pixel. Returns the b value.
   * @return the pixel's b value
   */
  int getBColor();

  /**
   * Sets the b value of the pixel to the given value. Change the original value of b.
   * @param value the value of r=b component
   */
  void setBValue(int value);
}
