package guiview;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * The Histogram class represents four histograms, each histogram represents each component value
 * (+ intensity) and their frequencies in an image. Histogram is an extension of JPanel.
 */
public class Histogram extends JPanel {
  private Map<Integer, Integer> red;
  private Map<Integer, Integer> green;
  private Map<Integer, Integer> blue;
  private Map<Integer, Integer> intensity;

  /**
   * The first constructor for the Histogram class. It takes in map of integer to integer for red,
   * green, blue, and intensity components.
   * @param red the red values and their frequencies
   * @param blue the blue values and their frequencies
   * @param green the green values and their frequencies
   * @param intensity the intensity values and their frequencies
   */
  public Histogram(Map<Integer, Integer> red, Map<Integer, Integer> blue,
                   Map<Integer, Integer> green, Map<Integer, Integer> intensity) {
    this.red = red;
    this.blue = blue;
    this.green = green;
    this.intensity = intensity;
  }

  /**
   * The second constructor for the Histogram class. It sets the map of the component values and
   * their frequencies to empty. Then it iterates through a for loop from values of 0 to 255.
   */
  public Histogram() {
    this.red = new HashMap();
    this.blue = new HashMap();
    this.green = new HashMap();
    this.intensity = new HashMap();

    for (int i = 0; i < 255; ++i) {
      this.red.put(i, 0);
      this.blue.put(i, 0);
      this.green.put(i, 0);
      this.intensity.put(i, 0);
    }
  }

  // the paintComponent method draws the histograms with the x-axis being the component values
  // (0-255) while the y-axis is the frequency a value appears in a give image
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this.red != null && this.green != null && this.blue != null && this.intensity != null) {
      Graphics2D g2 = (Graphics2D)g;

      for (int i = 0; i <= 255; ++i) {
        this.red.get(i);
        this.blue.get(i);
        this.green.get(i);
        this.intensity.get(i);

        g2.setColor(Color.red);
        g2.drawLine(i, 220 - this.red.get(i), i, 220);

        g2.setColor(Color.blue);
        g2.drawLine(i + 255, 220 - this.blue.get(i), i + 255, 220);

        g2.setColor(Color.green);
        g2.drawLine(i + 510, 220 - this.green.get(i), i + 510, 220);

        g2.setColor(Color.yellow);
        g2.drawLine(i + 765, 220 - this.intensity.get(i), i + 765, 220);

      }
    }

  }
}
