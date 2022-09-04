package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import guiview.Features;
import model.ImageProcessingModel;
import static controller.ImageProcessingControllerImpl.saveImage;


/**
 * This class represents an implementation of the GUIImageController interface. It allows the
 * controller to call actions in the GUI view and execute them.
 */
public class GUIImageControllerImpl implements ActionListener, GUIImageController {
  private ImageProcessingModel model;
  private Features view;
  private String currentName;

  /**
   * The constructor only takes in the model and view to initialize them. This creates the view
   * based on the model currently selected in the GUI.
   * @param model the given model
   * @param view the GUI view
   */
  public GUIImageControllerImpl(ImageProcessingModel model, Features view) {
    this.model = model;
    this.view = view;
    view.setListener(this);
    view.display();
    this.currentName = "";
  }

  @Override
  public void actionPerformed(ActionEvent e) throws NullPointerException {

    try {
      switch (e.getActionCommand()) {
        // click on button, select an image, load image based on filepath
        case "Open image": {
          currentName = "currentImage";
          this.currentName = "currentImage";
          this.model.setName(this.currentName);
          String path = this.view.setFilePath(true);
          ImageProcessingControllerImpl.loadImage(path, this.model);
          this.view.drawImage(this.currentName);
        }
        break;
        // click on button, save the image user is currently seeing, save to select path
        case "Save image": {
          String path = view.setFilePath(false);
          saveImage(path, currentName, this.model); // filepath, key, model
          this.view.drawImage(currentName);
        }
        break;
        case "select-component red": {
          this.model.selectComponent("red", currentName);
          view.drawImage(currentName);
        }
        break;
        case "select-component blue": {
          this.model.selectComponent("blue", currentName);
          view.drawImage(currentName);
        }
        break;
        case "select-component green": {
          this.model.selectComponent("green", currentName);
          view.drawImage(currentName);
        }
        break;
        case "sepia": {
          this.model.sepia(currentName); // how to get the previous operation's image name
          view.drawImage(currentName);
        }
        break;
        case "select-representation value": {
          this.model.selectRepresentation("value", currentName);
          view.drawImage(currentName);
        }
        break;
        case "select-representation intensity": {
          this.model.selectRepresentation("intensity", currentName);
          view.drawImage(currentName);
        }
        break;
        case "select-representation luma": {
          this.model.selectRepresentation("luma", currentName);
          view.drawImage(currentName);
        }
        break;
        case "greyscale": {
          this.model.greyscale(currentName);
          view.drawImage(currentName);
        }
        break;
        case "sharpen": {
          this.model.sharpen(currentName);
          view.drawImage(currentName);
        }
        break;
        case "brighten": {
          this.model.brightenDarken(10, currentName);
          view.drawImage(currentName);
        }
        break;
        case "darken": {
          this.model.brightenDarken(-10, currentName);
          view.drawImage(currentName);
        }
        break;
        case "blur": {
          this.model.blur(currentName);
          view.drawImage(currentName);
        }
        break;
        case "horizontal flip": {
          this.model.flipImageHorizontal(currentName);
          view.drawImage(currentName);
        }
        break;
        case "vertical flip": {
          this.model.flipImageVertical(currentName);
          view.drawImage(currentName);
        }
        break;
        default: {
          view.errorMessage();
        }
        break;
      }
    } catch (NullPointerException w) {
      // pop up window
      view.errorMessage();
    }
  }
}
