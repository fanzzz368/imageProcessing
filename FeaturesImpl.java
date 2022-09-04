package guiview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.BoxLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ImageProcessingModel;
import model.Pixel;
import model.ReadImageProcessingModel;

/**
 * The FeaturesImpl class is an implementation of the Features interface and an extension of JFrame.
 * It allows buttons and menus and the overall GUI to be displayed.
 */
public class FeaturesImpl extends JFrame implements guiview.Features {
  private ReadImageProcessingModel model;
  private JMenuItem menuItemRed;
  private JMenuItem menuItemGreen;
  private JMenuItem menuItemBlue;
  private JMenuItem menuItemValue;
  private JMenuItem menuItemIntensity;
  private JMenuItem menuItemLuma;
  private JMenuItem menuItemHorizontal;
  private JMenuItem menuItemVertical;
  private JMenuItem menuItemSepia;
  private JMenuItem menuItemGreyscale;
  private JMenuItem menuItemBlur;
  private JMenuItem menuItemSharpen;
  private JMenuItem menuItemDarken;
  private JMenuItem menuItemBrighten;
  private JButton imageOpenButton;
  private JButton imageSaveButton;
  private JLabel imageLabel;
  private JPanel histogramPanel;

  /**
   * The constructor for the FeaturesImpl class. It takes in a given model. It sets the title of
   * the GUI, sets buttons for loading and saving images, and provides a menu for all the
   * operations that can be performed. It also has space for the image and the histograms
   * visualizing a photo's red, green, blue components and its intensity.
   * @param model the given model
   */
  public FeaturesImpl(ImageProcessingModel model) {
    // sets the main panel and its title
    this.setTitle("Image Processing");
    this.setSize(1200, 1000);
    this.model = model;

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, 3));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    this.add(mainScrollPane);

    // button for loading image
    JPanel imageOpenPanel = new JPanel();
    imageOpenPanel.setLayout(new FlowLayout());
    mainPanel.add(imageOpenPanel);
    this.imageOpenButton = new JButton("Choose an image");
    this.imageOpenButton.setActionCommand("Open image");
    imageOpenPanel.add(this.imageOpenButton);

    // button for saving image
    JPanel imageSavePanel = new JPanel();
    imageOpenPanel.setLayout(new FlowLayout());
    mainPanel.add(imageSavePanel);
    this.imageSaveButton = new JButton("Save an image");
    this.imageSaveButton.setActionCommand("Save image");
    imageSavePanel.add(this.imageSaveButton);

    // a menu bar containing the menus for all the operations that can be performed on an image
    JMenuBar menuBar = new JMenuBar();
    menuBar.setLayout(new FlowLayout());
    mainPanel.add(menuBar);
    JMenu menu = new JMenu("Menu of Modifications");
    menu.setMnemonic(65);
    // select component menu item
    menuBar.add(menu);
    JMenu submenu = new JMenu("select component");
    submenu.setMnemonic(83);
    this.menuItemRed = new JMenuItem("red");
    this.menuItemRed.setActionCommand("select-component red");
    submenu.add(this.menuItemRed);
    this.menuItemGreen = new JMenuItem("green");
    this.menuItemGreen.setActionCommand("select-component green");
    submenu.add(this.menuItemGreen);
    this.menuItemBlue = new JMenuItem("blue");
    this.menuItemBlue.setActionCommand("select-component blue");
    submenu.add(this.menuItemBlue);
    menu.add(submenu);

    // select representation menu item
    submenu = new JMenu("select representation");
    submenu.setMnemonic(83);
    this.menuItemValue = new JMenuItem("value");
    this.menuItemValue.setActionCommand("select-representation value");
    submenu.add(this.menuItemValue);
    this.menuItemIntensity = new JMenuItem("intensity");
    this.menuItemIntensity.setActionCommand("select-representation intensity");
    submenu.add(this.menuItemIntensity);
    this.menuItemLuma = new JMenuItem("luma");
    this.menuItemLuma.setActionCommand("select-representation luma");
    submenu.add(this.menuItemLuma);
    menu.add(submenu);

    // horizontal flip menu item
    this.menuItemHorizontal = new JMenuItem("horizontal flip", 84);
    this.menuItemHorizontal.setActionCommand("horizontal flip");
    menu.add(this.menuItemHorizontal);

    // vertical flip menu item
    this.menuItemVertical = new JMenuItem("vertical flip", 84);
    this.menuItemVertical.setActionCommand("vertical flip");
    menu.add(this.menuItemVertical);

    // sepia menu item
    this.menuItemSepia = new JMenuItem("sepia", 84);
    this.menuItemSepia.setActionCommand("sepia");
    menu.add(this.menuItemSepia);

    // greyscale menu item
    this.menuItemGreyscale = new JMenuItem("greyscale", 84);
    this.menuItemGreyscale.setActionCommand("greyscale");
    menu.add(this.menuItemGreyscale);

    // blur menu item
    this.menuItemBlur = new JMenuItem("blur", 84);
    this.menuItemBlur.setActionCommand("blur");
    menu.add(this.menuItemBlur);

    // sharpen menu item
    this.menuItemSharpen = new JMenuItem("sharpen", 84);
    this.menuItemSharpen.setActionCommand("sharpen");
    menu.add(this.menuItemSharpen);

    // darken menu item
    this.menuItemDarken = new JMenuItem("darken", 84);
    this.menuItemDarken.setActionCommand("darken");
    menu.add(this.menuItemDarken);

    // brighten menu item
    this.menuItemBrighten = new JMenuItem("brighten", 84);
    this.menuItemBrighten.setActionCommand("brighten");
    menu.add(this.menuItemBrighten);
    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image Being Modified"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);
    this.imageLabel = new JLabel();
    new JScrollPane();
    JScrollPane imageScrollPane = new JScrollPane(this.imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(100, 400));
    imagePanel.add(imageScrollPane);

    // histogram
    this.histogramPanel = new JPanel();
    this.histogramPanel.setBorder(BorderFactory.createTitledBorder("Histograms"));
    this.histogramPanel.setLayout(new BoxLayout(this.histogramPanel, 3));
    this.histogramPanel.setPreferredSize(new Dimension(100, 250));
    JScrollPane histogramScroll = new JScrollPane(this.histogramPanel);
    mainPanel.add(histogramScroll);
  }

  @Override
  public void errorMessage() {
    JOptionPane.showMessageDialog(null, "Selected image/file is not valid");
  }

  @Override
  public ImageIcon drawImage(String fileName) {
    List<List<Pixel>> currentImage = this.model.getImage(fileName);
    int height = currentImage.size();
    int width = ((List)currentImage.get(0)).size();
    BufferedImage bi = new BufferedImage(width, height, 5);

    for (int x = 0; x < height; ++x) {
      for (int y = 0; y < width; ++y) {
        Pixel pixel = (Pixel)((List)currentImage.get(x)).get(y);
        int r = pixel.getRColor();
        int b = pixel.getBColor();
        int g = pixel.getGColor();
        Color newColor = new Color(r, g, b);
        int rgb = newColor.getRGB();
        bi.setRGB(y, x, rgb);
      }
    }

    ImageIcon newImage = new ImageIcon(bi);
    this.imageLabel.setIcon(newImage);
    Histogram histogram = new guiview.Histogram(this.model.getComponentLibrary(fileName, "red"),
            this.model.getComponentLibrary(fileName, "blue"),
            this.model.getComponentLibrary(fileName, "green"),
            this.model.getComponentLibrary(fileName, "intensity"));
    histogram.setSize(new Dimension(1200, 250));
    histogram.setLayout(new FlowLayout());
    this.histogramPanel.add(histogram);
    this.repaint();
    return newImage;
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public void setListener(ActionListener listener) {
    this.imageOpenButton.addActionListener(listener);
    this.imageSaveButton.addActionListener(listener);
    this.menuItemRed.addActionListener(listener);
    this.menuItemGreen.addActionListener(listener);
    this.menuItemBlue.addActionListener(listener);
    this.menuItemValue.addActionListener(listener);
    this.menuItemIntensity.addActionListener(listener);
    this.menuItemLuma.addActionListener(listener);
    this.menuItemHorizontal.addActionListener(listener);
    this.menuItemVertical.addActionListener(listener);
    this.menuItemSepia.addActionListener(listener);
    this.menuItemGreyscale.addActionListener(listener);
    this.menuItemBlur.addActionListener(listener);
    this.menuItemSharpen.addActionListener(listener);
    this.menuItemDarken.addActionListener(listener);
    this.menuItemBrighten.addActionListener(listener);
  }

  @Override
  public String setFilePath(boolean load) {
    JFileChooser fchooser = new JFileChooser();
    int result = 0;

    if (load) {
      FileNameExtensionFilter filter =
              new FileNameExtensionFilter("Image formats",
                      new String[]{"jpg", "ppm", "png", "bmp", "jpeg"});
      fchooser.setFileFilter(filter);
      result = fchooser.showOpenDialog(this);

    } else {
      fchooser.showSaveDialog(this);
    }
    if (result == JFileChooser.APPROVE_OPTION) {
      return fchooser.getSelectedFile().getAbsolutePath();
    }
    return null;
  }
}

