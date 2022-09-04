
package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import guiview.Features;
import guiview.FeaturesImpl;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingTextView;


/**
 * THe GUIProcessingMain class has the method main for the GUI controller. It takes the controller
 *  and give it the model and GUI view to display the loaded image and its operations on the GUI
 *  view.
 */
public class ImageProcessingMain {

  /**
   * It initializes an ImageProcessingModel (an image processor) and put it in
   * the GUI controller implementor. The static method also takes in GUI features view and
   * allows the user to use the image processor.
   *
   * @param args the command user wants to perform on the image processor
   */
  public static void main(String[] args) {
    Readable in;

    if (args.length != 0) {
      if (args[0].equals("-file")) {
        String filePath = args[1];
        try {
          in = new FileReader(filePath);
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
        ImageProcessingModel model = new ImageModel();
        new ImageProcessingControllerImpl(model, new ImageProcessingTextView(model),
                in).startImageProcess();
        // interactive text mode
      } else if (args[0].equals("-text")) {
        ImageProcessingModel model = new ImageModel();
        new ImageProcessingControllerImpl(model, new ImageProcessingTextView(model),
                new InputStreamReader(System.in)).startImageProcess();
      } // open the GUI
    } else {
      ImageProcessingModel model = new ImageModel();
      Features view = new FeaturesImpl(model);
      new GUIImageControllerImpl(model, view);
    }
  }
}
