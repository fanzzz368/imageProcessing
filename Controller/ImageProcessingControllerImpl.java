package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;

import controller.commands.Blur;
import controller.commands.DarkenBrighten;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.ImageProcessingCommand;
import controller.commands.SelectComponent;
import controller.commands.SelectRepresentation;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.VerticalFlip;
import model.ImageProcessingModel;
import model.Pixel;
import model.PixelImpl;
import view.ImageProcessingView;


/**
 * The ImageProcessingControllerImpl class is an implementation of the ImageProcessingController
 * interface. It takes in a model, view, and input that is used in the startImageProcess method,
 * loadImage method, and saveImage method.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {

  private ImageProcessingModel model;

  private ImageProcessingView view;

  private Readable input;

  private Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands;

  /**
   * The constructor for Image Processing Controller implementation.
   * It includes a model, view, and input.
   *
   * @param model the model for the game
   * @param view  the view of the game
   * @param input the input readable
   * @throws IllegalArgumentException when model, view, or input is null
   */
  public ImageProcessingControllerImpl(ImageProcessingModel model,
                                       ImageProcessingView view, Readable input) {
    if ((model == null) || (view == null) || (input == null)) {
      throw new IllegalArgumentException("Model, view, or input is null.");
    }
    this.model = model;
    this.view = view;
    this.input = input;
    this.knownCommands = this.setKnownCommands();
  }


  // this method is to store all the known commands
  private Map<String, Function<Scanner, ImageProcessingCommand>> setKnownCommands() {
    knownCommands = new HashMap<>();
    knownCommands.put("brighten", s -> new DarkenBrighten(s.nextInt(), s.next(), s.next()));
    knownCommands.put("darken", s -> new DarkenBrighten(s.nextInt(), s.next(), s.next()));
    knownCommands.put("select-component", s -> new SelectComponent(s.next(), s.next(), s.next()));
    knownCommands.put("horizontal-flip", s -> new HorizontalFlip(s.next(), s.next()));
    knownCommands.put("vertical-flip", s -> new VerticalFlip(s.next(), s.next()));
    knownCommands.put("select-representation", s -> new
            SelectRepresentation(s.next(), s.next(), s.next()));
    knownCommands.put("sharpen", s -> new Sharpen(s.next(), s.next()));
    knownCommands.put("greyscale", s -> new Greyscale(s.next(), s.next()));
    knownCommands.put("sepia", s -> new Sepia(s.next(), s.next()));
    knownCommands.put("blur", s -> new Blur(s.next(), s.next()));

    return knownCommands;
  }

  // it runs the program
  // it is the "go" method
  @Override
  public void startImageProcess() throws NullPointerException {
    Scanner scanner = new Scanner(this.input);
    while (scanner.hasNext()) {
      ImageProcessingCommand c;
      String in = scanner.next();

      try {
        if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
          return;
        } else if (in.equalsIgnoreCase("load")) {
          String imagePath = scanner.next(); // this is the image path
          String fileName5 = scanner.next(); // name of new image
          this.model.setName(fileName5);
          loadImage(imagePath, this.model); // check this
          this.renderMessageHelper("load completed ");
        } else if (in.equalsIgnoreCase("save")) {
          String imagePath1 = scanner.next();
          String key = scanner.next();
          saveImage(imagePath1, key, this.model); // check this
          this.renderMessageHelper("save completed ");
        } else {
          Function<Scanner, ImageProcessingCommand> cmd =
                  knownCommands.getOrDefault(in, null);
          if (cmd == null) {
            this.renderMessageHelper("Invalid input ");
          } else {
            c = cmd.apply(scanner);
            c.start(this.model);
            this.renderMessageHelper("operation completed ");
          }
        }
      } catch (NullPointerException e) {
        this.renderMessageHelper("Image not found. Please try again");

      }
    }
  }

  // throws when IllegalStateException instead of IOException
  private void renderMessageHelper(String message) {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private static String getFileExtension(String fileName) {
    String extension = "";

    int i = fileName.lastIndexOf('.');
    if (i >= 0) {
      extension = fileName.substring(i + 1);
    }
    return extension;
  }

  /**
   * Loads a ppm file or file of other formats (jpg, png, bmp). Determines what image to load
   * and what type it is through the given image path.
   * @param fileName image path
   * @param model given model
   */
  public static void loadImage(String fileName, ImageProcessingModel model) {
    if (getFileExtension(fileName).equals("ppm")) {
      loadPPM(fileName, model);
    } else {
      load2(fileName, model);
    }
  }

  private static void loadPPM(String fileName, ImageProcessingModel model) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      System.out.println("File " + fileName + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    // into a list<list<pixel> board
    // make an image
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    // create new board
    List<List<Pixel>> image = new ArrayList<List<Pixel>>();
    for (int i = 0; i < height; i++) {
      // row
      List<Pixel> row = new ArrayList<Pixel>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        Pixel pixel = new PixelImpl(r, g, b);
        row.add(pixel);
      }
      image.add(row);
    }
    model.add(image);
  }


  /**
   * Saves an image in ppm, jpg, png, or bmp format. Saves the image to the given name.
   * @param filePath the given file path
   * @param key image name
   * @param model given model
   */
  public static void saveImage(String filePath, String key, ImageProcessingModel model) {
    if (filePath.endsWith(".ppm")) {
      savePPM(filePath, key, model);
    } else {
      save2(filePath, key, model);
    }
  }

  private static void savePPM(String filePath, String key, ImageProcessingModel model) {
    BufferedWriter bufferedWriter = null;

    try {
      List<List<Pixel>> image = model.getImage(key);

      if (image == null) {
        System.out.println("image null");
        throw new IOException();
      }

      String magicNumber = "P3\n";
      String widthHeight =  image.get(0).size() + " " + image.size();
      String maxValue = "\n255\n";
      // for loop
      // temp variable
      String s = "";
      for (List<Pixel> row: image) {
        for (Pixel pixel : row) {
          int blue = pixel.getBColor();
          int red = pixel.getRColor();
          int green = pixel.getGColor();
          s = s + red + "\n" + green + "\n" + blue + "\n";
        }
      }

      File file = new File(filePath);

      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fileWriter = new FileWriter(file);
      bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(magicNumber);
      bufferedWriter.write(widthHeight);
      bufferedWriter.write(maxValue);
      bufferedWriter.write(s);

    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalStateException(e);
    }

    try {
      if (bufferedWriter != null) {
        try {
          bufferedWriter.close();
        } catch (IOException e) {
          throw new IllegalStateException(e);
        }
      }
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }
  }

  // loads the other file types, such as bmp, jpg, and png
  private static void load2(String filename, ImageProcessingModel model) {
    BufferedImage image = null;

    try {
      image = ImageIO.read(new File(filename));
      //System.out.println("Reading complete.");
    } catch (IOException e) {
      //System.out.println("error with loading");

      // methods in bufferedImage to get the rgb values

    }

    int width = image.getWidth();
    int height = image.getHeight();

    List<List<Pixel>> newImage = new ArrayList<List<Pixel>>();

    // retrieves the individual rgb values of each pixel in the file
    for (int i = 0; i < height; i++) {
      List<Pixel> row = new ArrayList<Pixel>();
      for (int j = 0; j < width; j++) {
        int rgb = image.getRGB(j, i); // get the width and then height
        Color newColor = new Color(rgb);

        int r = newColor.getRed();
        int g = newColor.getGreen();
        int b = newColor.getBlue();

        Pixel newPixel = new PixelImpl(r, g, b);
        row.add(newPixel);
      }
      newImage.add(row);
    }
    model.add(newImage);
  }

  // saves a file to the other file formats, such as bmp,jpg, and png
  private static void save2(String outPutPath, String key, ImageProcessingModel model) {
    BufferedImage bi;
    // the format is the last three letters of the output path
    String format = outPutPath.substring(outPutPath.indexOf(".") + 1);

    try {
      // convert the 2D arraylist of pixel to bufferedImage
      List<List<Pixel>> image = model.getImage(key);
      int height = image.size();
      int width = image.get(0).size();
      bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
      for (int x = 0; x < height; x++) { // height
        for (int y = 0; y < width; y++) { // width
          Pixel pixel = image.get(x).get(y);
          int r = pixel.getRColor();
          int b = pixel.getBColor();
          int g = pixel.getGColor();
          Color newColor = new Color(r, g, b);
          int rgb = newColor.getRGB();
          bi.setRGB(y, x, rgb);
        }
      }

      File file = new File(outPutPath);

      if (!file.exists()) {
        file.createNewFile();
      }

      ImageIO.write(bi, format, file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}



