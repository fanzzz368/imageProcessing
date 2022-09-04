package model;

/**
 * The class represents a single pixel in an image, and it is an implementation of the
 * Pixel interface. Each pixel has a position in the image and
 * a color that contains red, green, and blue values.
 */
public class PixelImpl implements Pixel {

  private int r; // Color (int r, int g, int b)
  private int g;
  private int b;


  /**
   * The first constructor for pixel class that takes in red, green, and blue values. The pixel's
   * three values will be set to the integers that were passed in.
   *
   * @param r the red value
   * @param g the green value
   * @param b the blue value
   */
  public PixelImpl(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * The second constructor for pixel class that takes in one pixel and set the pixel's r,g,b
   * values to the given pixel's values.
   *
   * @param pixel one pixel with r,g,b values
   */
  public PixelImpl(Pixel pixel) {
    this.r = pixel.getRColor();
    this.b = pixel.getBColor();
    this.g = pixel.getGColor();
  }

  @Override
  public int getRColor() {
    return this.r;
  }

  @Override
  public void setRValue(int value) {
    this.r = value;
  }

  @Override
  public int getGColor() {
    return this.g;
  }

  @Override
  public void setGValue(int value) {
    this.g = value;
  }

  @Override
  public int getBColor() {
    return this.b;
  }

  @Override
  public void setBValue(int value) {
    this.b = value;
  }

}
