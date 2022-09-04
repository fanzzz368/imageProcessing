package controller.commands;


import model.ImageProcessingModel;

/**
 * This class is one of the commands part of the ImageProcessingCommand interface. It allows the
 * controller to call selectComponent and performs the appropriate command and selects
 * one of the rbg values.
 */
public class SelectComponent  implements ImageProcessingCommand {
  private String component;
  private String previousName;
  private String newName;

  /**
   * This constructor initializes the three fields that select component takes in. It allows the
   * method to grab the previous image and make changes on the image and save it as a new image.
   * @param component the rgb component the client would like to choose
   * @param previousName the name of the image that the client would like to modify
   * @param newName the new name of the modified image
   */
  public SelectComponent(String component, String previousName, String newName) {
    this.component = component;
    this.previousName = previousName;
    this.newName = newName;
  }

  @Override
  public void start(ImageProcessingModel model) {
    model.setName(newName);
    model.selectComponent(component, previousName);
  }
}
