package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The ImageModel is an implementation of the ImageProcessingModel. It represents an image
 * processor that has the ability to make changes to an image, including darkening or brightening,
 * visualizing the value, intensity, or luma, flipping horizontally or vertically, and visualizing
 * the red, green, or blue value in each pixel.
 */
public class ImageModel implements ImageProcessingModel {

  private List<List<Pixel>> pixelSequence;

  // string imageName, 2D ArrayList of pixels
  private TreeMap<String, List<List<Pixel>>> imageLibrary;

  private String imageName;

  // arraylist of all the red components in an image
  private Map<Integer,  Integer> redLibrary;

  // arraylist of all the green components in an image
  private Map<Integer,  Integer> greenLibrary;

  // arraylist of all the blue components in an image
  private Map<Integer,  Integer> blueLibrary;

  // arraylist of all the intensity components in an image
  private Map<Integer,  Integer> intensityLibrary;

  /**
   * The constructor for the ImageModel class that initializes the 2D arraylist of pixels to an
   * empty 2D arraylist. It also initializes an empty hashmap of String key to 2D arraylist of
   * pixels that allows user to store attempts of their image after mutating it. It also stores
   * the name of the image as an empty string at first.
   */
  public ImageModel() {
    this.imageName = "";
    this.pixelSequence = new ArrayList<>();
    this.imageLibrary = new TreeMap<>();
    this.redLibrary = new HashMap<>();
    this.greenLibrary = new HashMap<>();
    this.blueLibrary = new HashMap<>();
    this.intensityLibrary = new HashMap<>();
  }

  @Override
  public void setName(String name) {
    this.imageName = name;
  }

  // add method add image to pixel sequence
  @Override
  public void add(List<List<Pixel>> board) {
    this.pixelSequence = board;
    imageLibrary.put(imageName, board);
  }

  // pass in fraction fo scale (how much we want to decrease)
  //  @Override
  //  public void downscale(int scaleHeight, int scaleWidth, String key) {
  //    List<List<Pixel>> newBoard = deepCopy(key);
  //    List<List<Pixel>> targetBoard = new ArrayList<>();
  //    // map pixel in original image to a pixel in new image
  //    Map<Pixel, Pixel> downscalePixel = new HashMap<>();
  //
  //    // the height and width of image before downscale
  //    int currentHeight = newBoard.size(); // 10 x 10
  //    int currentWidth = newBoard.get(0).size();
  //
  //    // the height and width of target board
  //    int targetHeight = currentHeight * scaleHeight; // 10 * 1/2 = 5 x 5
  //    int targetWidth = currentWidth * scaleWidth;
  //
  //    for (int x = 0; x < currentHeight; x++) {
  //      for (int y = 0; y < currentWidth; y++) {
  //        double heightProportionOrig = x / currentHeight; // 0.3
  //        double widthProportionOrig = y / currentWidth;
  //
  //        Pixel currentPixel = newBoard.get(x).get(y);
  //        Pixel newPixel = newBoard.get(targetHeight - x).get(x);
  //        downscalePixel.put(currentPixel, newPixel); // maps original to downscale
  //        // height of the new board
  //        targetBoard.add(downscalePixel.get(currentPixel));
  //      }
  //    }
  //  }

  //  private boolean sameProportion(List<List<Pixel>> image, )

  @Override
  public List<List<Pixel>> getImage(String image) {
    return this.imageLibrary.get(image);
  }

  // sets the values in each component library
  // image taken in is the image being set
  private void setComponentLibrary(String image) {

    for (int i = 0; i <= 255; i++) {
      this.redLibrary.put(i, 0);
      this.greenLibrary.put(i, 0);
      this.blueLibrary.put(i, 0);
      this.intensityLibrary.put(i, 0);
    }

    List<List<Pixel>> selectedImage = this.imageLibrary.get(image);
    for (int x = 0; x < selectedImage.size(); x++) {
      for (int y = 0; y < selectedImage.get(x).size(); y++) {

        Pixel currentPixel = selectedImage.get(x).get(y);
        int redValue = currentPixel.getRColor();
        int temp = this.redLibrary.get(redValue) + 1;
        this.redLibrary.put(redValue, temp);
        int blueValue = currentPixel.getBColor();
        this.blueLibrary.put(blueValue, this.blueLibrary.get(blueValue) + 1);
        int greenValue = currentPixel.getGColor();
        this.greenLibrary.put(greenValue, this.greenLibrary.get(greenValue) + 1);
        int intensityValue = this.getIntensity(currentPixel);
        this.intensityLibrary.put(intensityValue, this.intensityLibrary.get(intensityValue) + 1);
      }
    }
  }

  @Override
  public Map<Integer, Integer> getComponentLibrary(String image, String color) {
    if (color.equalsIgnoreCase("red")) {
      this.setComponentLibrary(image);
      return this.redLibrary;
    } else if (color.equalsIgnoreCase("green")) {
      this.setComponentLibrary(image);
      return this.greenLibrary;
    } else if (color.equalsIgnoreCase("blue")) {
      this.setComponentLibrary(image);
      return this.blueLibrary;
    } else if (color.equalsIgnoreCase("intensity")) {
      this.setComponentLibrary(image);
      return this.intensityLibrary;
    }
    return null;
  }

  // makes a copy of the 2D arraylist of pixels that the client wants
  private List<List<Pixel>> deepCopy(String key) throws IllegalArgumentException {
    List<List<Pixel>> newBoard = new ArrayList<List<Pixel>>();
    for (int x = 0; x < imageLibrary.get(key).size(); x++) { // height
      List<Pixel> pixelList = new ArrayList<Pixel>();
      for (int y = 0; y < imageLibrary.get(key).get(x).size(); y++) { // width
        pixelList.add(new PixelImpl(imageLibrary.get(key).get(x).get(y)));
      }
      newBoard.add(pixelList);
    }
    return newBoard;
  }

  @Override
  public int getImageHeight() {
    return this.pixelSequence.size();
  }

  @Override
  public int getImageWidth() {
    return this.pixelSequence.get(0).size();
  }

  @Override
  public void selectComponent(String pixelColor, String key) {

    List<List<Pixel>> newBoard = deepCopy(key);

    for (int x = 0; x < newBoard.size(); x++) {
      for (int y = 0; y < newBoard.get(x).size(); y++) {
        if (pixelColor.equalsIgnoreCase("red")) {

          // get the value of red int of pixel
          int setColor = newBoard.get(x).get(y).getRColor();
          Pixel newPixel = newBoard.get(x).set(y, new PixelImpl(setColor, setColor, setColor));
          this.clamp(newPixel, 0);
          // ex. new Color(10,5,6) -> ex. new Color(10,10,10)
        }
        if (pixelColor.equalsIgnoreCase("blue")) {
          int setColor = newBoard.get(x).get(y).getBColor(); // get the value of blue int
          Pixel newPixel = newBoard.get(x).set(y, new PixelImpl(setColor, setColor, setColor));
          this.clamp(newPixel, 0);
        }
        if (pixelColor.equalsIgnoreCase("green")) {
          int setColor = newBoard.get(x).get(y).getGColor(); // get the value of green int
          Pixel newPixel = newBoard.get(x).set(y, new PixelImpl(setColor, setColor, setColor));
          this.clamp(newPixel, 0);
        }
      }
    }
    imageLibrary.put(this.imageName, newBoard);
  }

  @Override
  public void selectRepresentation(String representation, String key) {
    List<List<Pixel>> newBoard = deepCopy(key);

    for (int x = 0; x < newBoard.size(); x++) {
      for (int y = 0; y < newBoard.get(x).size(); y++) {

        if (representation.equalsIgnoreCase("value")) {
          int value = this.getValue(newBoard.get(x).get(y));

          Pixel newPixel = newBoard.get(x).set(y, new PixelImpl(value, value, value));
          this.clamp(newPixel, 0);

          // ex. new Color(10,5,6) -> ex. new Color(10,10,10)
        } else if (representation.equalsIgnoreCase("intensity")) {
          int intensity = this.getIntensity(newBoard.get(x).get(y));

          Pixel newPixel = newBoard.get(x).set(y, new PixelImpl(intensity, intensity, intensity));
          this.clamp(newPixel, 0);

        } else if (representation.equalsIgnoreCase("luma")) {
          int luma = this.getLuma(newBoard.get(x).get(y));

          Pixel newPixel = newBoard.get(x).set(y, new PixelImpl(luma, luma, luma));
          this.clamp(newPixel, 0);
        }
      }
    }
    imageLibrary.put(this.imageName, newBoard);
  }

  // ex. new Color(214, 245, 120) ---> 245
  private int getValue(Pixel pixelColor) {
    return Math.max(Math.max(pixelColor.getRColor(), pixelColor.getGColor()),
            pixelColor.getBColor());
  }

  // gets the intensity (the average of the three components for each pixel)
  private int getIntensity(Pixel pixelColor) {
    return (int) Math.ceil((pixelColor.getRColor()
            + pixelColor.getGColor()
            + pixelColor.getBColor()) / 3.0);
  }

  // gets the luma (the weighted sum) --- take ceiling and cast to int
  private int getLuma(Pixel pixelColor) {
    return (int) Math.ceil(0.2126 * pixelColor.getRColor())
            + (int) Math.ceil(0.7152 * pixelColor.getGColor())
            + (int) Math.ceil(0.0722 * pixelColor.getBColor());
  }

  // flip the columns -- take the columns on left and put on the right
  @Override
  public void flipImageHorizontal(String key) {

    List<List<Pixel>> newBoard = deepCopy(key);
    List<List<Pixel>> emptyBoard = new ArrayList<>();

    for (int x = 0; x < newBoard.size(); x++) { //height
      List<Pixel> temp = new ArrayList<>();
      for (int y = 0; y < newBoard.get(x).size(); y++) { // width

        int width = newBoard.get(x).size();
        temp.add(newBoard.get(x).get(width - 1 - y));
      }
      emptyBoard.add(temp);
    }
    imageLibrary.put(this.imageName, emptyBoard);
  }

  // flip the rows -- take the rows on top and put on the bottom
  @Override
  public void flipImageVertical(String key) {
    List<List<Pixel>> newBoard = deepCopy(key);
    List<List<Pixel>> emptyBoard = new ArrayList<>();

    for (int x = 0; x < newBoard.size(); x++) { // height
      List<Pixel> temp = new ArrayList<>();
      for (int y = 0; y < newBoard.get(0).size(); y++) { // width

        int height = newBoard.size();
        temp.add(newBoard.get(height - 1 - x).get(y));
      }
      emptyBoard.add(temp);
    }
    imageLibrary.put(this.imageName, emptyBoard);
  }

  @Override
  public void brightenDarken(int increment, String key) {
    List<List<Pixel>> newBoard = deepCopy(key);

    for (int x = 0; x < newBoard.size(); x++) {
      for (int y = 0; y < newBoard.get(x).size(); y++) {
        Pixel currentPixel = newBoard.get(x).get(y);

        this.clamp(currentPixel, increment);
      }
    }
    imageLibrary.put(this.imageName, newBoard);
  }

  @Override
  public void blur(String key) {
    List<List<Pixel>> newBoard = deepCopy(key);

    // blur filter kernel applied to every channel of every pixel
    Double[][] blurKernel = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };

    // would the method works the same way if we turn the kernel to list?
    List<List<Double>> blurKernelList = this.makeToList(blurKernel);

    for (int x = 0; x < newBoard.size(); x++) {
      for (int y = 0; y < newBoard.get(x).size(); y++) {
        //Pixel currentPixel = new PixelImpl(newBoard.get(x).get(y));

        // as we iterate through the board, the blur filter is applied to each pixel
        newBoard.get(x).set(y, this.applyKernel(blurKernelList, x, y, imageLibrary.get(key)));
        // final check clamp the values

      }
    }
    imageLibrary.put(this.imageName, newBoard);

  }

  @Override
  // sharpens an image
  public void sharpen(String key) {
    List<List<Pixel>> newBoard = deepCopy(key);

    // blur filter kernel applied to every channel of every pixel
    Double[][] sharpenKernel = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}
    };

    // would the method works the same way if we turn the kernel to list?
    List<List<Double>> sharpenKernelList = this.makeToList(sharpenKernel);

    // getting out of bounds
    // looping through the image pixels board
    for (int x = 0; x < newBoard.size(); x++) {
      for (int y = 0; y < newBoard.get(x).size(); y++) {

        // as we iterate through the board, sharpen filter is applied to each pixel
        newBoard.get(x).set(y, this.applyKernel(sharpenKernelList, x, y, imageLibrary.get(key)));
        // final check clamp the values
      }
    }
    imageLibrary.put(this.imageName, newBoard);

  }

  // apply the center to the first pixel and then apply surrounding pixels to surrounding kernels
  private Pixel applyKernel(List<List<Double>> kernel, int x, int y, List<List<Pixel>> image) {
    double r = 0;
    double b = 0;
    double g = 0;

    // start at -1 if 3x3 kernel
    for (int i = -(kernel.size() - 1) / 2; i <= (kernel.size() - 1) / 2; i++) { // height
      for (int j = -(kernel.size() - 1) / 2; j <= (kernel.size() - 1) / 2; j++) { // width
        if (!this.outBounds(x + i, y + j, image)) {
          // looping thru kernel
          Pixel currentPixel = image.get(x + i).get(y + j);

          r = r + currentPixel.getRColor() *
                  kernel.get(i + ((kernel.size() - 1) / 2)).get(j + ((kernel.size() - 1) / 2));
          g = g + currentPixel.getGColor() *
                  kernel.get(i + ((kernel.size() - 1) / 2)).get(j + ((kernel.size() - 1) / 2));
          b = b + currentPixel.getBColor() *
                  kernel.get(i + ((kernel.size() - 1) / 2)).get(j + ((kernel.size() - 1) / 2));
        }
      }
    }
    Pixel newPixel = new PixelImpl((int) Math.ceil(r), (int) Math.ceil(g), (int) Math.ceil(b));
    this.clamp(newPixel, 0);
    return newPixel;
  }

  // check if pixel position is out of bound
  private boolean outBounds(int x, int y, List<List<Pixel>> image) {
    return (x >= image.size() || x < 0 || y >= image.get(0).size() || y < 0);
  }

  // clamps the rbg values, so they are between 0 and 255
  private void clamp(Pixel pixel, int value) {
    int red = pixel.getRColor() + value;
    int blue = pixel.getBColor() + value;
    int green = pixel.getGColor() + value;

    if (green >= 0 && green <= 255) {
      pixel.setGValue(green);
    } else if (green < 0) {
      pixel.setGValue(0);
    } else if ((green > 255)) {
      pixel.setGValue(255);
    }

    if (blue >= 0 && blue <= 255) {
      pixel.setBValue(blue);
    } else if ((blue > 255)) {
      pixel.setBValue(255);
    } else if (blue < 0) {
      pixel.setBValue(0);
    }

    if (red >= 0 && red <= 255) {
      pixel.setRValue(red);
    } else if ((red > 255)) {
      pixel.setRValue(255);
    } else if ((red < 0)) {
      pixel.setRValue(0);

    }
  }

  @Override
  public void greyscale(String key) {
    List<List<Pixel>> newBoard = deepCopy(key);

    // luma color transformation
    Double[][] lumaMatrix = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
    };

    List<List<Double>> lumaList = makeToList(lumaMatrix);

    // as we iterate each pixel in the image, update the pixel to the new pixel
    for (int x = 0; x < newBoard.size(); x++) {
      for (int y = 0; y < newBoard.get(x).size(); y++) {

        this.colorTransformation(lumaList, newBoard.get(x).get(y));

      }
    }
    imageLibrary.put(this.imageName, newBoard);
  }

  @Override
  public void sepia(String key) {
    List<List<Pixel>> newBoard = deepCopy(key);

    // luma color transformation
    Double[][] sepiaMatrix = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131},
    };

    List<List<Double>> sepiaList = makeToList(sepiaMatrix);

    // as you iterate through each pixel in the board, add the same values to each pixel,
    // but different numbers for different channels
    for (int x = 0; x < newBoard.size(); x++) {
      for (int y = 0; y < newBoard.get(x).size(); y++) {

        this.colorTransformation(sepiaList, newBoard.get(x).get(y));

      }
    }
    imageLibrary.put(this.imageName, newBoard);
  }

  // makes a 2D array into 2D arraylist
  private List<List<Double>> makeToList(Double[][] array) {
    List<List<Double>> list = new ArrayList<>();
    for (Double[] component : array) {
      list.add(Arrays.asList(component));
    }
    return list;
  }


  // updates to new pixel
  private void colorTransformation(List<List<Double>> colorMatrix, Pixel pixel) {

    int initRed = pixel.getRColor();
    int initGreen = pixel.getGColor();
    int initBlue = pixel.getBColor();

    double r = 0;
    double g = 0;
    double b = 0;

    r =  colorMatrix.get(0).get(0) * initRed
            + colorMatrix.get(0).get(1) * initGreen
            + colorMatrix.get(0).get(2) * initBlue;

    g =  colorMatrix.get(1).get(0) * initRed
            + colorMatrix.get(1).get(1) * initGreen
            + colorMatrix.get(1).get(2) * initBlue;

    b =  colorMatrix.get(2).get(0) * initRed
            + colorMatrix.get(2).get(1) * initGreen
            + colorMatrix.get(2).get(2) * initBlue;

    pixel.setRValue((int) Math.ceil(r));
    pixel.setGValue((int) Math.ceil(g));
    pixel.setBValue((int) Math.ceil(b));
    this.clamp(pixel, 0);
  }
}
