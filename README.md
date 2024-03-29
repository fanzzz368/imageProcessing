# imageProcessing
photoshop program

Changes (Part 3 Image Processing)

Changed load and save to static methods and added ImageProcessingModel model as a parameter
so we can use them in another controller (GUIControllerImpl) too.

Added more cases to the static main method. If user writes "-text", the program will be in
interactive text mode. If user does not enter any commands, the program will default to graphical
user interface view.

Added a ReadImageProcessingModel interface and moved the non-mutable methods that were
in ImageProcessingModel to that interface. This way, the GUI view can only access the model that
implements the ReadImageProcessingModel while the controller has access to the whole model
interface (interface with mutable methods).

Allow ImageProcessingModel to extend ReadImageProcessingModel, so it has access to both mutable and
non-mutable methods.


Changes (Part 2 Image Processing)

We added an interface for the Pixel class (now PixelImpl) and made PixelImpl implement the
Pixel interface because we do not want to call to the concrete class (Pixel class) when we need it.
Adding interface allows us to call the abstract form of it.

We modified all of the fields and changed them to private. Instead of directly
calling the fields of the Pixel class in the model, we use getter and setter methods in the Pixel
class to gain access to the fields.

Made all of the outer loop height and inner loop width so they are all consistent.

A large part of the brightenDarken method was clamping the rgb values and the increment value. A
separate helper method (clamp) was made. When brightenDarken is called, we call the helper method
inside of it. This results in less code duplication.

Called clamp in selectComponent and selectRepresentation as well to make sure the rgb values are 0
and 255 or in between after modification.

We used command design pattern on our "go" method (startImageProcess) to avoid large amount of
code duplication. A private method setKnownCommands was made to map a String to function and
perform the operations when a command is called.

LoadImage and saveImage methods are removed from the controller interface because clients should
not be able to add an image to controller without calling the startImageProcess method.

Controller
The GUIImageController interface represents the controller that takes in the GUI view.

The GUIImageControllerImpl class is an implementation of the GUIImageController interface. It takes
in a model and a GUI view. The actionPerformed method in the class matches a set of operations to
each case. After performing each operation, the controller displays it onto the GUI view.

* In the GUI mode, whenever user selects brighten or darken, the increment will increase or
decrease by 10 values.

The ImageProcessingController interface represents the controller for the image processor model.
It allows the client to input commands into the startImageProcess() method and start using the
features/ operations of image processor.

The loadImage(String filename) method can be used to load image into the image processor model
by passing in a String of the desired file name. The saveImage(String filePath) method can be
used to save image by passing in String of the file path (where user wants to save the image to)
and a key, which is the name of the image saved.

The ImageProcessingControllerImpl class is an implementation of the ImageProcessingController
interface. It takes in a model, view, and Readable input as parameters in its constructor and
throws IllegalArgumentException if any of the three parameters are null. It contains the
startImageProcess method that contains a list of commands the user can give. If the user inputs "q",
the program will quit. When the program quits, nothing happens. In order to perform any
operations on an image, the client must first load an image into the image processor. The client
needs to input "load" followed by the image path of the image they want to load and the name they
want for the image loaded in the command line. After loading, the client can choose to brighten,
darken, select a red, blue, or green component greyscale, flip horizontally, flip vertically,
select value, intensity, or luma greyscale representation, or save. The client can use the
brightening image effect by inputting "brighten", the (positive) integer increment they want to
brighten the image by, the name of the image they want to make changes to, and the name of this
new image in the command line. The client can use the darkening image effect by inputting
"darken", the (negative) integer increment they want to darken the image by, the name of the
image they want to make changes to, and the name of this new image in the command line.
To visualize the red, green, or blue component of an image to create the greyscale, the client
should type "select-component", red, green, or blue (choose one of the color), the name of image
making changes to, and the new name of this image in the command line. To flip an image
horizontally, the client should input "horizontal-flip", name of image being modified, and the name
of this new image in the command line. To flip an image vertically, the client should input
"vertical-flip", name of image being modified, and the name of this new image in the command line.
To visualize the value, intensity, or luma in greyscale image, the client should input
"select-representation", the desired representation (value, intensity, or luma), the name of
image being modified, and the name of this new image in the command line. To save an image, the user
should input "save", the path where the image is being saved to, and the name of the image being
saved in the command line. The program supports the loading and saving of both PPM files and files
of other formats (PNG, JPG, BMP). After each successful operation, a message indicating
that the command was completed will be displayed in the terminal.

The ImageProcessingMain class contains a static main method. It takes the controller and give it
the model and view to display whether an operation is completed successfully or not. This is also
the class that the client use to run the program and type in commands in the command line.

* The main method is updated to support the ability to accept a script file as a command-line
option. When user types "-file" followed by a valid file name, the program run the script and
then exits. When user types "-text", the program will be in interactive text mode. When no command
line options are provided when running the program, the main method displays the graphical user
interface and a screen pops up.

Controller - Commands Package

Inside the commands package under the controller, the ImageProcessingCommand interface allows
the controller to start any image processing operations by housing the method start() that takes in
a model. Start will be called inside startImageProcess() in controller implementation and call to
any of the image processing methods inside the model that the user ask for.

The Blur class represents the blur command. When user inputs blur, the Blur class will be called
and the method inside it (start(ImageProcessingModel model)) will be executed.

The DarkenBrighten class represents the darken and brighten command. When user inputs darken or
brighten, the DarkenBrighten class will be called and the method inside it
(start(ImageProcessingModel model)) will be executed.

The Greyscale class represents the greyscale command. When user inputs greyscale, the greyscale
class will be called and the method inside it (start(ImageProcessingModel model)) will be executed.

The SelectComponent class represents the select-component command. When user inputs
select-component,the SelectComponent class will be called and the method inside it
(start(ImageProcessingModel model)) will be executed.

The SelectRepresentation class represents the select-representation command. When user inputs
select-representation,the SelectRepresentation class will be called and the method inside it
(start(ImageProcessingModel model)) will be executed.

The Sepia class represents the sepia command. When user inputs sepia,the Sepia class will be
called and the method inside it (start(ImageProcessingModel model)) will be executed.

The Sharpen class represents the sharpen command. When user inputs sharpen,the Sharpen class will
be called and the method inside it (start(ImageProcessingModel model)) will be executed.

The VerticalFlip class represents the vertical-flip command. When user inputs
vertical-flip,the VerticalFlip class will be called and the method inside it
(start(ImageProcessingModel model)) will be executed.

Model
The ImageProcessingModel interface represents the operations offered by the image processor model.
Aside from the commands/operations in the controller, this interface also allows numerous edits/
versions of an image to be stored in a imageLibrary map and accessed through getImage method. It
also extends the ReadImageProcessingModel interface.

The ReadImageProcessingModel interface contains all the methods in the model that does not have the
ability to mutate. ImageProcessingModel extends this interface. Has the ability to getImage,
getComponentLibrary, getImageWidth, and getImageHeight.


The ImageModel class is an implementation of the ImageProcessingModel, and it represents an image
processor. It has the ability to make changes to a loaded image through its various operations.
If client decides to perform the features on the model when no image is loaded, nothing will happen.
The image processor (model) will continue to do nothing until an image is loaded through the
controller.

* When calculating the intensity of an image, the ceiling of the average of the three components in
a Pixel is taken and cast to an int.

* When calculating the luma value, the ceiling of the weighted sum of the luma is taken and cast
to an int.

* For applying kernel filter to image, we make the pixel components doubles and multiplied them
with the values in the provided kernel. Then we add that value with all the same pixel component
value with in the kernel size. We got the ceiling of that value and then cast to int to get the
rgb values of each pixel.

* For each rgb values in color transformation, rgb values are made into doubles. The original
values of a pixel are multiplied with specific numbers in the color matrix. The ceiling values of
the rgb values after modification were obtained and casted to int.

The Pixel interface represents all the operations a pixel can perform, such as getting and setting
the rgb values of a pixel.

The PixelImpl class represents a single pixel in a image (which is represented by a 2D arraylist of
Pixel). It is an implementation of the Pixel interface, and each PixelImpl contains a
red, green, and blue value component.

GUI View
The Features interface represents the operations for the graphical user interface. Java Swing is
used to show and display the image loaded and modified on a screen. The methods used for display
are drawImage (returns an ImageIcon), display, setListener (allows buttons and menus to have
functionality), and setFilePath.

The FeaturesImpl class is an extension of JFrame and an implementation of the Features view
interface. It takes in a model and display the GUI on the screen. There is a choose image button
to load, save image button to save, menu of modifications to select modification on the image,
area to show the image being modified, and histograms (each visualising a different component
of the image & one being the intensity).

The Histogram class is an extension of JPanel, and it represents four histograms. It takes in a
map of integer to integer of red, green, blue, intensity values to their frequencies. It has
a paintComponent method that takes in Graphics that draws the vertical lines in the histogram.
The x-axis of the histograms goes from 0 to 255, representing the component values of the Pixels in
an image. The y-axis represents the frequency a value appears in an image.

View
The ImageProcessingView interface represents the operations offered by the view of an image
processor. It allows messages from the view to be transmitted to the controller.

The ImageProcessingTextView class is an implementation of the ImageProcessingView interface. It
takes in model and an out appendable that facilitates in transmitting message to provided
destination.

Script of Commands (Assignment 4 - Part 1)
1. load a file by providing the file (res/helloworld.ppm) as part of the command line argument
   type in "load res/helloworld.ppm hw" and press enter/return
2. when the "load completed" message shows up on the screen, perform a vertical flip on the loaded
   image by first calling the command "vertical-flip" then calling the name of the file you want
   to vertically flip. Followed by the new name that you want to name this changed file. Wait for
   command completed message "vertical-flip completed"
3. If you want to do more changes to the original image. you can again call the command,in this case
   the command is "brighten followed by how much you want to brighten" and then the file you want to
   brighten and the new name for this changed picture
4.  If you want to do more changes to the changed image. you can again call the command, and call
    the file that you want to change more. In this case it is "hw-b" and then name the new change
    into something else
5. Once you have decided that you're done with making changes to the photo, you can save your new
    image by calling the command "save" the followed by the path you want to save it to (and the
    format of the file) and then the name of this file you're saving. This then will appear
    in your save folder after the save process is completed.

Example of the commands function:

load res/helloworld.ppm hw
load completed

vertical-flip hw hw-v
vertical-flip completed

brighten 20 hw hw-b
darken/brighten completed

select-representation intensity hw-b hw-b-i
select-representation completed

select-component green hw-b-i hw-b-i-g
select-component completed

save res/hw-b-i-g.ppm hw-b-i-g
save completed


Citation: The images are of our own creation, and we authorized the use of them in this project.
Fanny Zheng, Carly Wenig
