package guiview;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * Features is the GUI for image processing program. Java Swing is used to build the GUI, and it
 * shows the image currently being worked on. The histogram of the visible image, featuring its red,
 * green, blue, and intensity, is shown as a line chart on the screen.
 */
public interface Features {

  /**
   * When an invalid file or image is selected to be loaded, a pop-up window will appear. The
   * message on the window tells user to select a valid file.
   */
  void errorMessage();

  /**
   * This method draws the image that is loaded into to the gui. It allows image to be displayed.
   * @param fileName name of image that the user loads into the gui.
   */
  ImageIcon drawImage(String fileName);

  /**
   * Allows everything in GUI to be displayed on screen. Set everything to visible.
   */
  void display();


  /**
   * Allows the buttons and menus in GUI to have functionality when user clicks on them. Takes in
   * an "action".
   * @param listener the action listener
   */
  void setListener(ActionListener listener);

  /**
   * Sets the file path if the user choose a ppm, jpg, png, bmp image to be loaded. It also allows
   * user to save an image to given path as well.
   * @param load whether an image is loaded
   * @return a String of the file path
   */
  String setFilePath(boolean load);

}
