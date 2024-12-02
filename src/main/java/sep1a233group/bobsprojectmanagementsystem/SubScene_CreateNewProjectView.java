package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

/** <p>This class controls the GUI related view and methods concerning the "create new project" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.</p>
 * @Author: K. Dashnaw
 */
public class SubScene_CreateNewProjectView implements Scene_ControllerInterface
{
  //Residential Project control attributes
  @FXML VBox vBoxResidentialProjectType;
  @FXML Button buttonResidentialProjectType;
  @FXML ImageView imgResidentialProjectType;
  @FXML Label labelResidentialProjectType;
  @FXML GridPane gridResidentialUniqueData;
  @FXML TextField tResBathroomNumber;
  @FXML TextField tResNumberKitchens;
  @FXML TextField tResNumberOfOtherPlumbing;
  @FXML TextField tResDuration;
  @FXML TextField tResBuildingSize;

  //Commercial Project control attributes
  @FXML VBox vBoxCommercialProjectType;
  @FXML Button buttonCommercialProjectType;
  @FXML ImageView imgCommercialProjectType;
  @FXML Label labelCommercialProjectType;
  @FXML GridPane gridCommercialUniqueData;
  @FXML TextField tComNumberFloors;
  @FXML TextField tComDuration;
  @FXML TextField tComBuildingSize;
  @FXML TextArea taComIntendedUse;

  //Industrial Project control attributes
  @FXML VBox vBoxIndustrialProjectType;
  @FXML Button buttonIndustrialProjectType;
  @FXML ImageView imgIndustrialProjectType;
  @FXML Label labelIndustrialProjectType;
  @FXML GridPane gridIndustrialUniqueData;
  @FXML TextField tIndDuration;
  @FXML TextField tIndFacilitySize;
  @FXML TextArea taIndFacilityType;

  //Road Project control attributes
  @FXML VBox vBoxRoadProjectType;
  @FXML Button buttonRoadProjectType;
  @FXML ImageView imgRoadProjectType;
  @FXML Label labelRoadProjectType;
  @FXML GridPane gridRoadUniqueData;
  @FXML TextField tRDLength;
  @FXML TextField tRDWidth;
  @FXML TextField tRDDuration;
  @FXML TextArea taRDBridgeTunnelInfo;
  @FXML TextArea taRDEnvironmentInfo;

  //Shared Control attributes
  @FXML TextField GUI_Console;
  @FXML Label labelProjectTypePrompt;
  @FXML GridPane gridProjectDataContainer;
  @FXML Label labelProjectDataPrompt;
  @FXML Button buttonCreateProject;
  @FXML Button buttonCancel;
  @FXML TextArea taProjectDescription;
  @FXML TextArea taManagersComments;
  @FXML DatePicker date_EndDateField;
  @FXML CheckBox checkBox_AddToDashBoard;
  @FXML Label labelLastProjectSave;
  @FXML Label labelHTMLExportDate;

  //Other field Attributes:
  private MainModel activeModel;
  private SceneController sceneController;
  private boolean projectTypeSelected;
  private String projectTypeSelectedString;
  private int loadCounter; //Keeps track of how many times critical methods have been executed. This is to prevent unnecessary use of confirmation prompts.






  /** <p>This method initiates the scene/stage it is called on and ties it to the mapping done in the SceneController,
   * thus allowing the overall SceneController to know about this active stage/scene.<br>It is only run on the first initialization.</p>
   * @param activeModel a MainModel Object reference attached to each scene. It allows the scene to call methods from the model to perform operations.
   * @param sceneController a reference to the overall responsible SceneController, which ties all the sub-scenes/stages together.
   * */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    //Sets necessary attributes:
    setActiveModel(activeModel);
    setSceneController(sceneController);
    this.setGUI_Console(this.GUI_Console);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    //Ensure the elements shown are in the proper format for a clean view load:
    refresh();

    //Set the load counter used to check if user has previously performed critical methods or not.
    loadCounter = 0;
    System.out.println("Create New Project panel is now active");
  }






  /**<p>This method is called every time this scene/stage becomes active. It is used to refresh onscreen data. </p>*/
  @Override public void refresh()
  {
    //Refresh the page, as it is shown on a clean load:
    setProjectTypeSelected(false);
    labelProjectTypePrompt.setVisible(true);
    if(this.getActiveModel().getFileManager().getLastDataSaveTime() != null)
    {
      labelLastProjectSave.setText("Project file version: " + this.getActiveModel().getFileManager().getLastDataSaveTime());
    }
    else
    {
      labelLastProjectSave.setText("Project file version: Unknown");
    }
    if(this.getActiveModel().getFileManager().getLastWebExportTime() != null)
    {
      labelHTMLExportDate.setText("Last HTML export : " + this.getActiveModel().getFileManager().getLastWebExportTime());
    }
    else
    {
      labelHTMLExportDate.setText("Last HTML export : Unknown");
    }
    this.getSceneController().hideGridElementNode(gridProjectDataContainer, true);
    this.getSceneController().hideLabelElementNode(labelProjectDataPrompt, true);
    this.getSceneController().hideAllUniqueProjectDataFields(true, gridResidentialUniqueData, gridCommercialUniqueData, gridIndustrialUniqueData, gridRoadUniqueData);
    this.getSceneController().hideButtonElementNode(buttonCreateProject, true);
    this.getSceneController().hideButtonElementNode(buttonCancel, true);

    //Reset all windows and fields to initial width/height:
    resizeAllElementHeight(185);
    resizeProjectButtonWidth(245);
    resetProjectTypeImagesTransparency();
    resetLabelTextSizes();
    System.out.println("New Project Creation Scene is now the active stage.");

    //Loop though non fx:id referenced data fields and reset these.
    for (Node node : gridProjectDataContainer.getChildren())
    {
      if (node instanceof TextArea)
      {
        ((TextArea) node).setText("");
      }
      else if (node instanceof TextField)
      {
        ((TextField) node).setText("");
        ((TextField) node).setTooltip(null);
        node.setStyle("-fx-text-fill: black;");
      }
      else if(node instanceof DatePicker)
      {
        ((DatePicker) node).getEditor().setText("");
      }
      else if(node instanceof CheckBox)
      {
        ((CheckBox) node).setSelected(false);
        node.setDisable(false);
      }
    }
    taProjectDescription.setText("");
    taManagersComments.setText("");
    checkBox_AddToDashBoard.setText("Add project to Dashboard?");

    //Page has been refreshed. Reset the load counter.
    loadCounter = 0;

    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }






  /** <p>Gets a reference to the GUI_Console on this page.</p>
   * @return TextField containing a reference to this pages' GUI Console.
   * @Author: K. Dashnaw
   * */
  public TextField getGUI_Console()
  {
    return GUI_Console;
  }






  /** <p>Sets a reference to the GUI_Console on this page.</p>
   * @param GUI_Console TextField containing a reference to this pages' GUI Console.
   * @Author: K. Dashnaw
   * */
  public void setGUI_Console(TextField GUI_Console)
  {
    this.GUI_Console = GUI_Console;
  }






  /** <p> Gets the overall SceneController object that is responsible for managing this scenes navigability between application pages.</p>
   * @return a SceneController object reference that points to this scenes' overall controller.
   * @Author: K. Dashnaw
   * */
  public SceneController getSceneController()
  {
    return sceneController;
  }






  /** <p> Sets the overall SceneController object that is responsible for managing this scenes navigability between application pages.</p>
   * @param sceneController a SceneController object reference that points to this scenes' overall controller.
   * @Author: K. Dashnaw
   * */
  public void setSceneController(SceneController sceneController)
  {
    this.sceneController = sceneController;
  }






  /** <p>Sets a boolean that indicates whether the user has selected a projectType yet, or not.</p>
   * @param projectTypeSelected A boolean that if TRUE indicates that the user has selected a project on the GUI, else FALSE.
   * @Author: K. Dashnaw
   */
  private void setProjectTypeSelected(boolean projectTypeSelected)
  {
    this.projectTypeSelected = projectTypeSelected;
  }






  /** <p>Gets a boolean that indicates whether the user has selected a projectType yet, or not.</p>
   * @return A boolean that if TRUE indicates that the user has selected a project on the GUI, else FALSE.
   * @Author: K. Dashnaw
   */
  public boolean isProjectTypeSelected()
  {
    return projectTypeSelected;
  }






  /** <p>Gets a String containing the name of the selected project type.
   * This is used for evaluating which project type should be created once the user confirms their data.</p>
   * @return A String containing one of the following values "ResidentialProjectType", "CommercialProjectType", "IndustrialProjectType" or "RoadProjectType"
   * @Author: K. Dashnaw
   */
  public String getProjectTypeSelectedString()
  {
    return projectTypeSelectedString;
  }






  /** <p>Sets a String containing the name of the selected project type.
   * This is used for evaluating which project type should be created once the user confirms their data.</p>
   * @param projectTypeSelectedString A String containing one of the following values "ResidentialProjectType", "CommercialProjectType", "IndustrialProjectType" or "RoadProjectType"
   * @Author: K. Dashnaw
   */
  private void setProjectTypeSelectedString(String projectTypeSelectedString)
  {
    this.projectTypeSelectedString = projectTypeSelectedString;
  }






  /** <p>Gets the active project model.</p>
   * @return a MainModel object reference.
   * @Author: K. Dashnaw
   * */
  public MainModel getActiveModel()
  {
    return activeModel;
  }






  /** <p>Sets the active project model.</p>
   * @param activeModel a MainModel object reference.
   * @Author: K. Dashnaw
   * */
  public void setActiveModel(MainModel activeModel)
  {
    this.activeModel = activeModel;
  }






  /** <p>This method simply calls the common method with the same name, from the SceneController.<br>
   * Check SceneController.openWindow() for a more detailed description.</p>
   * @param actionEvent ActionEvent that contains a reference to the element which prompted this method to execute.
   * @throws IOException If something unexpected occurs.
   * @Author: K. Dashnaw
   * */
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //Check if user has selected a projectType yet. If not, it is impossible for the user to have entered any data on this GUI view yet.
    if (this.isProjectTypeSelected() && !(createPromptWindow()))
    {
      //Prompt the user to confirm they really wish to navigate away from the creation view - loosing all unsaved data.
      //If the user aborted, we break the current method here.
      return;
    }
    //Load the scene the user selected.
    String buttonText = ((Button) actionEvent.getSource()).getText().toLowerCase();
    this.getSceneController().openWindow(buttonText, this.getGUI_Console());
  }






  /** <p>This method simply calls the common method with the same name, from the SceneController.<br>
   * Check SceneController.exportToWeb() for a more detailed description.</p>
   * @Author: K.Dashnaw
   * */
  public void exportToWeb()
  {
    this.getSceneController().exportToWeb();

    //Update GUI Console message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }






  /** <p>This method simply calls the common method with the same name, from the SceneController.<br>
   * Check SceneController.exitApplication() for a more detailed description.</p>
   * @Author: K. Dashnaw
   * */
  public void exitApplication()
  {
    //Check if user has selected a projectType yet. If not, it is impossible for the user to have entered any data on this GUI view yet.
    if (this.isProjectTypeSelected() && !(createPromptWindow()))
    {
      //Prompt the user to confirm they really wish to navigate away from the creation view - loosing all unsaved data.
      //If the user aborted, we break the current method here.
      return;
    }
    //Proceed with application termination.
    this.getSceneController().exitApplication();

    //Update console message, in case an error occurred above:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }






  /** <p>Is called as an on-action event from the one of the 4 buttons on screen, where you select which project type to create.
   * It initializes the next stage of the creation process, making visible shared project data fields
   * as well as unique data fields for the selected project type.</p>
   * @param actionEvent A ActionEvent reference to the source element which prompted this method to execute.
   * @Author: K. Dashnaw
   */
  public void getProjectTypeSelected(ActionEvent actionEvent)
  {
    String buttonText = ((Button) actionEvent.getSource()).getText();
    setProjectTypeSelected(true);
    setProjectTypeSelectedString(buttonText);

    //Initialize a new default project of the selected type.
    this.getActiveModel().newActiveProject(this.getProjectTypeSelectedString());
    switch (this.getProjectTypeSelectedString())
    {
      case "ResidentialProjectType":
        this.getActiveModel().initializeCreateProjectGUI(tResBathroomNumber, tResNumberKitchens, tResNumberOfOtherPlumbing, tResDuration, tResBuildingSize);
        break;
      case "CommercialProjectType":
        this.getActiveModel().initializeCreateProjectGUI(tComNumberFloors, tComDuration, tComBuildingSize, taComIntendedUse);
        break;
      case "IndustrialProjectType":
        this.getActiveModel().initializeCreateProjectGUI(tIndDuration, tIndFacilitySize, taIndFacilityType);
        break;
      case "RoadProjectType":
        this.getActiveModel().initializeCreateProjectGUI(tRDLength, tRDWidth, tRDDuration, taRDBridgeTunnelInfo, taRDEnvironmentInfo);
        break;
    }

    //If we already loaded the page, but the user is simply changing project types in view, we display a confirmation window warning about data loss.
    if (loadCounter > 0)
    {
      if (createPromptWindowNoRefresh())
      {
        loadPageFields();
      }
    }
    else
    {
      loadPageFields();
    }
    loadCounter++;
  }






  /** <p>Loads the data fields on this page, ensuring these are initialized in a reset manner, as well as initializing
   * a new temporary project of the selected project type for use in regard to input validation while user inputs information</p>
   * @Author: K. Dashnaw
   * */
  public void loadPageFields()
  {
    //Display the common project data fields:
    this.getSceneController().hideGridElementNode(gridProjectDataContainer, false);
    this.getSceneController().hideLabelElementNode(labelProjectDataPrompt, false);

    //Display the unique data fields relating to the selected project type:
    this.getSceneController().showUniqueProjectDataFields(gridResidentialUniqueData, gridCommercialUniqueData, gridIndustrialUniqueData, gridRoadUniqueData);

    //Display the buttons the user may interact with:
    this.getSceneController().hideButtonElementNode(buttonCreateProject, false);
    this.getSceneController().hideButtonElementNode(buttonCancel, false);

    //Hide the "Select Project Type" prompt and resize the project type pictures for more space on screen:
    labelProjectTypePrompt.setVisible(false);
    resizeAllElementHeight(80);
    resizeProjectButtonWidth(106);
    setNonSelectedProjectTypesTransparent();
  }






  /** <p>Resizes all the elements relating to the initial project selection.
   * This in order to provide more space on screen for data field entries.</p>
   * @param height An Integer containing the height in pixels to apply.
   * @Author: K. Dashnaw
   */
  private void resizeAllElementHeight(int height)
  {
    //Resize the image elements:
    imgResidentialProjectType.setFitHeight(height - 5);
    imgCommercialProjectType.setFitHeight(height - 5);
    imgIndustrialProjectType.setFitHeight(height - 5);
    imgRoadProjectType.setFitHeight(height - 5);

    //Resize the button elements:
    resizeButtonHeight(buttonResidentialProjectType, height);
    resizeButtonHeight(buttonCommercialProjectType, height);
    resizeButtonHeight(buttonIndustrialProjectType, height);
    resizeButtonHeight(buttonRoadProjectType, height);

    //Resize the label elements:
    changeLabelTextSize(labelResidentialProjectType, 11, false);
    changeLabelTextSize(labelCommercialProjectType, 11, false);
    changeLabelTextSize(labelIndustrialProjectType, 11, false);
    changeLabelTextSize(labelRoadProjectType, 11, false);
    resizeLabelHeight(labelProjectTypePrompt, 0);

    //Resize the vBox elements:
    vBoxResidentialProjectType.setMaxHeight(height);
    vBoxCommercialProjectType.setMaxHeight(height);
    vBoxIndustrialProjectType.setMaxHeight(height);
    vBoxRoadProjectType.setMaxHeight(height);
  }






  /** <p>Method used to resize a button's height.
   * Resizes maxHeight, prefHeight and minHeight at once.</p>
   * @param buttonID A reference to the Button element to resize.
   * @param height An Integer containing the height, in pixels, to apply.
   * @Author: K. Dashnaw
   */
  public void resizeButtonHeight(Button buttonID, int height)
  {
    buttonID.setMaxHeight(height);
    buttonID.setPrefHeight(height);
    buttonID.setMinHeight(height);
  }






  /** <p>Method used to resize a button's width.
   * Resizes maxWidth, prefWidth and minWidth at once.</p>
   * @param buttonID A reference to the Button element to resize.
   * @param width An Integer containing the width, in pixels, to apply.
   * @Author: K. Dashnaw
   */
  public void resizeButtonWidth(Button buttonID, int width)
  {
    buttonID.setMinWidth(width);
    buttonID.setPrefWidth(width);
    buttonID.setMaxWidth(width);
  }






  /** <p>Method used to resize a Label's height.
   * Resizes maxHeight, prefHeight and minHeight at once.</p>
   * @param labelID A reference to the Label element to resize.
   * @param height An Integer containing the height, in pixels, to apply.
   * @Author: K. Dashnaw
   */
  public void resizeLabelHeight(Label labelID, int height)
  {
    labelID.setMinHeight(height);
    labelID.setPrefHeight(height);
    labelID.setMaxHeight(height);
  }






  /** <p>Method used to resize the 4 project buttons, which have integrated images inside.</p>
   * @param width An Integer containing the width, in pixels, to apply.
   * @Author: K. Dashnaw
   */
  private void resizeProjectButtonWidth(int width)
  {
    resizeButtonWidth(buttonResidentialProjectType, width);
    resizeButtonWidth(buttonCommercialProjectType, width);
    resizeButtonWidth(buttonIndustrialProjectType, width);
    resizeButtonWidth(buttonRoadProjectType, width);
  }






  /** <p>Method used to change the text size of a labels integrated text.</p>
   * @param labelID A reference to the Label element to resize.
   * @param textSize An Integer containing the text size to apply.
   * @param bold TRUE if text should be bold, otherwise FALSE.
   * @Author: K. Dashnaw
   */
  public void changeLabelTextSize(Label labelID, int textSize, boolean bold)
  {
    if (bold)
    {
      labelID.setFont(Font.font("System", FontWeight.BOLD, textSize));
    }
    else
    {
      labelID.setFont(Font.font("System", textSize));
    }
  }






  /** <p>Method used to reset all project label sizes to their initial values.<br>
   * Is used upon stage refresh/reset.</p>
   * @Author: K. Dashnaw
   */
  private void resetLabelTextSizes()
  {
    changeLabelTextSize(labelResidentialProjectType, 14, true);
    changeLabelTextSize(labelCommercialProjectType, 14, true);
    changeLabelTextSize(labelIndustrialProjectType, 14, true);
    changeLabelTextSize(labelRoadProjectType, 14, true);
    resizeLabelHeight(labelProjectTypePrompt, 30);
  }






  /** <p>Method used to change the opacity of non-selected project type images for a clearer GUI experience.</p>
   * @Author: K. Dashnaw
   */
  private void setNonSelectedProjectTypesTransparent()
  {
    double discreetOpacityLevel = 0.3;
    double nonDiscreetOpacityLevel = 1;
    switch (this.getProjectTypeSelectedString())
    {
      case "ResidentialProjectType":
        imgResidentialProjectType.setOpacity(nonDiscreetOpacityLevel);
        imgCommercialProjectType.setOpacity(discreetOpacityLevel);
        imgIndustrialProjectType.setOpacity(discreetOpacityLevel);
        imgRoadProjectType.setOpacity(discreetOpacityLevel);
        break;
      case "CommercialProjectType":
        imgResidentialProjectType.setOpacity(discreetOpacityLevel);
        imgCommercialProjectType.setOpacity(nonDiscreetOpacityLevel);
        imgIndustrialProjectType.setOpacity(discreetOpacityLevel);
        imgRoadProjectType.setOpacity(discreetOpacityLevel);
        break;
      case "IndustrialProjectType":
        imgResidentialProjectType.setOpacity(discreetOpacityLevel);
        imgCommercialProjectType.setOpacity(discreetOpacityLevel);
        imgIndustrialProjectType.setOpacity(nonDiscreetOpacityLevel);
        imgRoadProjectType.setOpacity(discreetOpacityLevel);
        break;
      case "RoadProjectType":
        imgResidentialProjectType.setOpacity(discreetOpacityLevel);
        imgCommercialProjectType.setOpacity(discreetOpacityLevel);
        imgIndustrialProjectType.setOpacity(discreetOpacityLevel);
        imgRoadProjectType.setOpacity(nonDiscreetOpacityLevel);
        break;
    }
  }






  /** <p>Method used to reset all project image types to their initial values.</p>
   * @Author: K. Dashnaw
   */
  private void resetProjectTypeImagesTransparency()
  {
    imgResidentialProjectType.setOpacity(1);
    imgCommercialProjectType.setOpacity(1);
    imgIndustrialProjectType.setOpacity(1);
    imgRoadProjectType.setOpacity(1);
  }






  /** <p>>Method used to create a confirmation prompt window, in order to prompt the user before navigating away from the creation view,
   * which could otherwise result loss of entered non-saved data.!</p
   * @return A boolean that returns TRUE if the uses confirms they wish to proceed with the action, or FALSE if the user aborts.
   * @Author: K. Dashnaw
   */
  public boolean createPromptWindow()
  {
    String confirmationAction = this.getSceneController().createPromptWindow("WARNING: Any unsaved date will be lost.\n\nDo you wish to proceed?\n");

    switch (confirmationAction)
    {
      case "cancelPressed":
        this.getSceneController().setGUI_ConsoleMessage("Cancel aborted.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel aborted.");
        return false; //do not proceed with cancel operation
      case "confirmationPressed":
        this.getSceneController().setGUI_ConsoleMessage("Cancel confirmed. All data fields on 'Create new project' screen have been reset to default values.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel confirmed. Resetting all data fields! Any unsaved data is removed.");
        refresh();
        return true; //Proceed with cancel operation
      default:
        return false; //do not proceed with cancel operation
    }
  }






  /** <p>Method used to create a confirmation prompt window, in order to prompt the user before navigating away from the creation view,
   * which could otherwise result loss of entered non-saved data.!</p>
   * <p>This method is used when changing project type after already selected an initial type. This method does not refresh all data fields,
   * but only the ones that are unique to the selected project.</p>
   * @return A boolean that returns TRUE if the uses confirms they wish to proceed with the action, or FALSE if the user aborts.
   * @Author: K. Dashnaw
   */
  public boolean createPromptWindowNoRefresh()
  {
    String confirmationAction = this.getSceneController().createPromptWindow(
        "WARNING: Any unsaved date will be lost.\n\nDo you wish to proceed?\n");

    switch (confirmationAction)
    {
      case "cancelPressed":
        this.getSceneController().setGUI_ConsoleMessage("Cancel aborted.");
        this.getGUI_Console()
            .setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel aborted.");
        return false; //do not proceed with cancel operation

      case "confirmationPressed":
        this.getSceneController().setGUI_ConsoleMessage(
            "Cancel confirmed. Project specific data fields on screen have been reset to default values.");
        this.getGUI_Console()
            .setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel confirmed. Project specific data fields on screen have been reset to default values.");
        return true; //Proceed with cancel operation

      default:
        return false;
    }
  }






  /** <p>Input validation method called from the local scene control model, which gets a call from the .fxml scene upon interacting with a
   * TextField with this method set as an "OnKey" event.<br><br></p>
   * <p>The code specifics are executed in the main SceneController. This call just redirects to the main SceneController while
   * adding/handling any additional local logic needed for this specific scene.</p>
   * <p>WARNING: This method MUST be run on a TextField in order to avoid potential crashes/errors.</p>
   * @param keyNode A reference to the KeyEvent which activated this method.
   * @return TRUE if the TextField attached to the OnKey event is NOT empty. Else FALSE if it is empty.
   * @Author: K. Dashnaw
   */
  public boolean validate_NotEmpty(KeyEvent keyNode)
  {
    resetValidation();
    if(getSceneController().validate_NotEmpty(keyNode))
    {
      addTemporaryProjectData(keyNode);
      return true;
    }
    else
    {
      //Update console with error set in SceneController
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      return false;
    }
  }






  /** <p>Input validation method to validate that the TextField is NOT empty NOR a number, which is called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "OnKey" event.<br><br></p>
   * <p>The code specifics are executed in the main SceneController. This call just redirects to the main SceneController while
   * adding/handling any additional local logic needed for this specific scene.</p>
   * <p>WARNING: This method MUST be run on a TextField in order to avoid potential crashes/errors.</p>
   * @param keyNode A reference to the KeyEvent which activated this method.
   * @return TRUE if the TextField attached to the OnKey event is NOT empty AND not a number. Else FALSE if it is empty.
   * @Author: K. Dashnaw
   */
  public boolean validate_NotEmpty_NotNumber(KeyEvent keyNode)
  {
    resetValidation();
    if(getSceneController().validate_NotEmpty_NotNumber(keyNode, "Error in data values while creating new project. Please review and correct!"))
    {
      addTemporaryProjectData(keyNode);
      return true;
    }
    else
    {
      //Update console with error set in SceneController
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      return false;
    }
  }






  /** <p>Input validation method to validate that the TextField is NOT empty NOR a negative number NOR a String, which is called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "OnKey" event.<br><br></p>
   * <p>The code specifics are executed in the main SceneController. This call just redirects to the main SceneController while
   * adding/handling any additional local logic needed for this specific scene.</p>
   * <p>WARNING: This method MUST be run on a TextField in order to avoid potential crashes/errors.</p>
   * @param keyNode A reference to the KeyEvent which activated this method.
   * @return TRUE if the TextField attached to the OnKey event is NOT empty AND not a number. Else FALSE if it is empty.
   * @Author: K. Dashnaw
   */
  public boolean validate_NotEmpty_NotString_NotNegative(KeyEvent keyNode)
  {
    resetValidation();
    if(getSceneController().validate_NotEmpty_NotString_NotNegative(keyNode, "Error in data values while creating new project. Please review and correct!"))
    {
      addTemporaryProjectData(keyNode);
      return true;
    }
    else
    {
      //Update console with error set in SceneController
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      return false;
    }
  }






  /** <p>Method disables the "save" button and is used in conjunction with the validation fields to ensure that the
   * "save" button only is enabled when proper data is ready to be added to the system.</p>
   * @Author: K. Dashnaw
   * */
  private void resetValidation()
  {
    buttonCreateProject.setDisable(true);

    //Update console with an error:
    this.getSceneController().setGUI_ConsoleMessage("");
    this.getGUI_Console()
        .setText(this.getSceneController().getGUI_ConsoleMessage());

  }






  /** <p>This code is run locally in this class. It simply checks if the given DatePicker contains any data or not.
   * If DatePicker is empty it applies a tooltip, if not it removes the tooltip.</p>
   * @param node a DatePicker object containing a reference to the element which should be evaluated if empty or not.
   * @Author: K. Dashnaw
   * */
  private void emptyDatePickerCode(DatePicker node)
  {
    //Add a Show a tooltip!
    this.getSceneController().addErrorTooltip(node, "-fx-text-fill: red;", "Field cannot be empty.");
    node.getEditor().setStyle("-fx-prompt-text-fill: red;");

    //Update console with an error:
    this.getSceneController().setGUI_ConsoleMessage("Error in data values while creating new project. Please review and correct!");
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }






  /** <p>Runs as an "On Action" event in the .fxml scene on a DatePicker upon interacted with.
   * It performs validation on the selected date and adds this to the active project.</p>
   * @param actionEvent A ActionEvent reference to the source element which prompted this method to execute.
   * @Author: K. Dashnaw
   */
  public void validate_DatePicker(ActionEvent actionEvent)
  {
    buttonCreateProject.setDisable(true);
    DatePicker date = (DatePicker) actionEvent.getSource();

    TextField dateValue = new TextField();
    dateValue.setText(date.getEditor().getText());
    dateValue.setPromptText(date.getPromptText());

    //Check if field is empty:
    if (date.getEditor().getText().isBlank())
    {
      emptyDatePickerCode(date);
    }
    else
    {
      addTemporaryProjectData(dateValue);
    }
  }






  /** <p>Can be called from eventHandlers in the .fxml scene, which do not require input validation,
   * or from the input validation methods in this class.<br>
   * Method adds the received KeyEvent node to the project data.</p>
   * <p>WARNING: KeyEvent node must have a source type of TextField, else errors will occur.</p>
   * @param keyNode A reference to the node belonging to the KeyEvent that triggered this method.
   * @Author: K. Dashnaw
   * */
  public void addTemporaryProjectData(KeyEvent keyNode)
  {
    TextField userInput = (TextField) keyNode.getSource();
    addTemporaryProjectData(userInput);
  }






  /** <p>Can be called from eventHandlers in the .fxml scene, which do not require input validation,
   * or from the input validation methods in this class.<br>
   * Method adds the received KeyEvent node to the project data.</p>
   * <p>WARNING: KeyEvent node must have a source type of TextArea, else errors will occur.</p>
   * @param keyNode A reference to the node belonging to the KeyEvent that triggered this method.
   * @Author: K. Dashnaw
   * */
  public void addTemporaryProjectData_TextArea(KeyEvent keyNode)
  {
    TextArea userInput = (TextArea) keyNode.getSource();
    TextField text2 = new TextField();
    text2.setText(userInput.getText());
    text2.setPromptText(userInput.getPromptText());
    addTemporaryProjectData(text2);
  }






  /** <p>This method is used in conjunction with the above input validation methods.
   * It allows the typed text fields to be passed onto the "addCommonProjectData" input validation step
   * It simply ensures that the proper add Method is called (based on project Type).</p>
   * @param text A TextField containing a reference to the source TextField that contains the String Data to add to the project.
   * @Author: K. Dashnaw
   * */
  public void addTemporaryProjectData(TextField text)
  {
    //Get the project type:
    switch (this.getProjectTypeSelectedString())
    {
      case "ResidentialProjectType":
        addTemporaryResidentialData((ResidentialProject) this.getActiveModel().getSelectedProject(), text);
        break;
      case "CommercialProjectType":
        addTemporaryCommercialData((CommercialProject) this.getActiveModel().getSelectedProject(), text);
        break;
      case "IndustrialProjectType":
        addTemporaryIndustrialData((IndustrialProject) this.getActiveModel().getSelectedProject(), text);
        break;
      case "RoadProjectType":
        addTemporaryRoadData((RoadProject) this.getActiveModel().getSelectedProject(), text);
        break;
    }
  }






  /** <p>This method is called from "On Action" EventHandlers in the .fxml scene
   * Method performs calls a validation check on all screen data fields, and if all data is valid, calls for the selected data
   * to be validated and added to the active project <br><br>
   * It receives a "ActionEvent node" parses this as a "CheckBox" and checks if it is selected or not.<br><br>
   * <b>Warning: ActionEvent node must have a source type of CheckBox, else errors will occur.</b></p>
   * @param actionEvent A ActionEvent reference to the source element which prompted this method to execute.
   * @Author: K. Dashnaw
   * */
  public void checkBoxChecker(ActionEvent actionEvent)
  {
    buttonCreateProject.setDisable(true);
    CheckBox checkBox = (CheckBox) actionEvent.getSource();
    TextField value = new TextField();
    value.setText(checkBox.getText());

    if (checkBox.isSelected())
    {
      value.setText(value.getText() + "_True");
    }
    else
    {
      value.setText(value.getText() + "_False");
    }
    value.setPromptText(value.getText());
    addTemporaryProjectData(value);
  }






  /** <p>This method is used in conjunction with the "addTemporaryProjectData(TextField text) method".<br>
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * @param project This is a reference to the super class that all construction projects are a member of.
   * @param text This is a reference to the node containing the information to add to the project.
   * @return TRUE if data was added and FALSE if no data was added.
   * @Author: K. Dashnaw
   * */
  public boolean addCommonProjectData(ConstructionProject project, TextField text)
  {
    boolean dataAddedToProject = false;

    //TODO: Implement handling of picture file link!

    switch (text.getPromptText())
    {
      case "first name":
        project.getCustomer().setFirstName(text.getText());
        dataAddedToProject = true;
        break;
      case "last name":
        project.getCustomer().setLastName(text.getText());
        dataAddedToProject = true;
        break;
      case "Prefix (ie. +45)":
        project.getCustomer().setPhoneNumberPrefix(text.getText());
        dataAddedToProject = true;
        break;
      case "Number (8 digits)":
        project.getCustomer().setPhoneNumber(Integer.parseInt(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "email":
        project.getCustomer().setEmail(text.getText());
        dataAddedToProject = true;
        break;
      case "Company Name the customer is representing":
        project.getCustomer().getCustomerCompany().setName(text.getText());
        dataAddedToProject = true;
        break;
      case "Customers Street Name":
        project.getCustomer().getCustomerAddress().setStreet(text.getText());
        project.getCustomer().getCustomerCompany().getCompanyAddress().setStreet(text.getText());
        dataAddedToProject = true;
        break;
      case "Building number":
        project.getCustomer().getCustomerAddress().setStreetNumber(text.getText());
        project.getCustomer().getCustomerCompany().getCompanyAddress().setStreetNumber(text.getText());
        dataAddedToProject = true;
        break;
      case "Customer apartment number, if applicable.":
        project.getCustomer().getCustomerAddress().setApartment(text.getText());
        project.getCustomer().getCustomerCompany().getCompanyAddress().setApartment(text.getText());
        dataAddedToProject = true;
        break;
      case "ZIP code":
        project.getCustomer().getCustomerAddress().setPostalCode(Integer.parseInt(text.getText().trim()));
        project.getCustomer().getCustomerCompany().getCompanyAddress().setPostalCode(Integer.parseInt(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "City":
        project.getCustomer().getCustomerAddress().setCity(text.getText());
        project.getCustomer().getCustomerCompany().getCompanyAddress().setCity(text.getText());
        dataAddedToProject = true;
        break;
      case "Country":
        project.getCustomer().getCustomerAddress().setCountry(text.getText());
        project.getCustomer().getCustomerCompany().getCompanyAddress().setCountry(text.getText());
        dataAddedToProject = true;
        break;
      case "Street name":
        project.getProjectAddress().setStreet(text.getText());
        dataAddedToProject = true;
        break;
      case "Property number":
        project.getProjectAddress().setStreetNumber(text.getText());
        dataAddedToProject = true;
        break;
      case "Apartment number, if applicable. (else leave empty)":
        project.getProjectAddress().setApartment(text.getText());
        dataAddedToProject = true;
        break;
      case "Project ZIP code":
        project.getProjectAddress().setPostalCode(Integer.parseInt(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Project City":
        project.getProjectAddress().setCity(text.getText());
        dataAddedToProject = true;
        break;
      case "Project Country":
        project.getProjectAddress().setCountry(text.getText());
        dataAddedToProject = true;
        break;
      case "In hours":
        project.getHumanRessources().setTotalManHoursNeeded(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "in $USD":
        this.getSceneController().validateIsWithinNormalMargins(text);
        project.getFinances().setTotalBudget(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Enter any internal only notes directed towards the project manager":
        project.getProjectInformation().setProjectManagerComments(text.getText());
        dataAddedToProject = true;
        break;
      case "Start date":
        String receivedStartDate = text.getText();
        String dayStart = receivedStartDate.substring(0,2);
        String monthStart = receivedStartDate.substring(3,5);
        String yearStart = receivedStartDate.substring(6,10);
        project.getProjectStartDate().set(Integer.parseInt(dayStart), Integer.parseInt(monthStart), Integer.parseInt(yearStart));

        //Reset project duration textField field with new duration:
        tIndDuration.setText("" + getActiveModel().getDefaultIndustrialSettings().getProjectDuration());
        tResDuration.setText("" + getActiveModel().getDefaultResidentialSettings().getProjectDuration());
        tComDuration.setText("" + getActiveModel().getDefaultCommercialSettings().getProjectDuration());
        tRDDuration.setText("" + getActiveModel().getDefaultRoadSettings().getProjectDuration());

        if(project instanceof ResidentialProject)
        {
          project.setProjectDuration(getActiveModel().getDefaultResidentialSettings().getProjectDuration());
        }
        else if(project instanceof CommercialProject)
        {
          project.setProjectDuration(getActiveModel().getDefaultCommercialSettings().getProjectDuration());
        }
        else if(project instanceof IndustrialProject)
        {
          project.setProjectDuration(getActiveModel().getDefaultIndustrialSettings().getProjectDuration());
        }
        else if(project instanceof RoadProject)
        {
          project.setProjectDuration(getActiveModel().getDefaultRoadSettings().getProjectDuration());
        }

        //Update estimated Completion date:
        project.getProjectEndDate().set(project.getProjectStartDate().getDay(), project.getProjectStartDate().getMonth(), project.getProjectStartDate().getYear());
        for (int i = 0; i < project.getProjectDuration()*30; i++)
        {
          project.getProjectEndDate().stepForwardOneDay();
        }

        //Calculate months between the start and end dates, and update the "project duration" attribute:
        int daysBetweenStart = project.getProjectEndDate().daysBetween(project.getProjectStartDate());
        project.setProjectDuration(daysBetweenStart/30);
        date_EndDateField.getEditor().setText(project.getProjectEndDate().getDay() + "." + project.getProjectEndDate().getMonth() + "." + project.getProjectEndDate().getYear());
        dataAddedToProject = true;

        //Update project duration textField again field with new duration:
        if(project instanceof ResidentialProject)
        {
          tResDuration.setText("" + project.getProjectDuration());
          this.getSceneController().validateIsWithinNormalMargins(tResDuration);
        }
        else if(project instanceof CommercialProject)
        {
          tComDuration.setText("" + project.getProjectDuration());
          this.getSceneController().validateIsWithinNormalMargins(tComDuration);
        }
        else if(project instanceof IndustrialProject)
        {
          tIndDuration.setText("" + project.getProjectDuration());
          this.getSceneController().validateIsWithinNormalMargins(tIndDuration);
        }
        else if(project instanceof RoadProject)
        {
          tRDDuration.setText("" + project.getProjectDuration());
          this.getSceneController().validateIsWithinNormalMargins(tRDDuration);
        }
        break;
      case "Est. Completion Date":
        String receivedEndDate = text.getText();
        String dayEnd = receivedEndDate.substring(0,2);
        String monthEnd = receivedEndDate.substring(3,5);
        String yearEnd = receivedEndDate.substring(6,10);
        project.getProjectEndDate().set(Integer.parseInt(dayEnd), Integer.parseInt(monthEnd), Integer.parseInt(yearEnd));
        dataAddedToProject = true;

        //Calculate months between the start and end dates, and update the "project duration" attribute:
        int daysBetween = project.getProjectEndDate().daysBetween(project.getProjectStartDate());
        project.setProjectDuration(daysBetween/30);

        //Update project duration field with new duration:
        tIndDuration.setText("" + project.getProjectDuration());
        this.getSceneController().validateIsWithinNormalMargins(tIndDuration);
        tResDuration.setText("" + project.getProjectDuration());
        this.getSceneController().validateIsWithinNormalMargins(tResDuration);
        tComDuration.setText("" + project.getProjectDuration());
        this.getSceneController().validateIsWithinNormalMargins(tComDuration);
        tRDDuration.setText("" + project.getProjectDuration());
        this.getSceneController().validateIsWithinNormalMargins(tRDDuration);
        break;
      case "Enter a short project description. This information is exported and displayed on the company homepage":
        project.getProjectInformation().setProjectDescription(text.getText());
        dataAddedToProject = true;
        break;
      case "Project name. Will be displayed on homepage":
        project.getProjectInformation().setProjectName(text.getText());
        dataAddedToProject = true;
        break;
      case "Add project to Dashboard?_True", "Dashboard is already full._True":
        if(this.getActiveModel().getDashboardProgressReports().getCurrentCapacity() <= this.getActiveModel().getDashboardProgressReports().getMaxCapacity() && this.getActiveModel().getDashboardProgressReports().getCurrentCapacity() > 0)
        {
          project.setDashboardProject(true);
        }
        else
        {
          checkBox_AddToDashBoard.setSelected(false);
          checkBox_AddToDashBoard.setDisable(true);
          checkBox_AddToDashBoard.setText("Dashboard is already full.");
        }
        dataAddedToProject = true;
        break;
      case "Add project to Dashboard?_False", "Dashboard is already full._False":
        project.setDashboardProject(false);
        dataAddedToProject = true;
        break;
      case "Is project confidential?_True":
        project.setProjectConfidentiality(true);
        dataAddedToProject = true;
        break;
      case "Is project confidential?_False":
        project.setProjectConfidentiality(false);
        dataAddedToProject = true;
        break;
      default:
        break;
    }

    if(dataAddedToProject)
    {
      activateCreateButton();
      return true;
    }
    else
    {
      return false;
    }
  }






  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".<br>
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * @Author: K. Dashnaw
   * */
  public void addTemporaryResidentialData(ResidentialProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryResidentialData(project, text);
      activateCreateButton();
    }
  }






  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".<br>
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * @Author: K. Dashnaw
   * */
  public void addTemporaryCommercialData(CommercialProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryCommercialData(project, text);
      activateCreateButton();
    }
  }






  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".<br>
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * @Author: K. Dashnaw
   * */
  public void addTemporaryIndustrialData(IndustrialProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryIndustrialData(project, text);
      activateCreateButton();
    }
  }






  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".<br>
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * @Author: K. Dashnaw
   * */
  public void addTemporaryRoadData(RoadProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryRoadData(project, text);
      activateCreateButton();
    }
  }






  /** <p>This method checks if all required data fields have been filled out before enabling the "create project" button.</p>
   * @Author: K. Dashnaw
   * */
  public void activateCreateButton()
  {
    //First check if project has all necessary values for creation:

    boolean dataIsMissing = this.getSceneController().validateActiveProject(date_EndDateField);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    //If all required fields are present. Activate the create button now.
    buttonCreateProject.setDisable(dataIsMissing);
  }






  /** <p>This method finalizes the project creation by calling relevant methods from the MainModel, after data validation has been performed in previous method calls.<br>
   * It also asks the user to confirm their creation before finalizing.</p>
   * @Author: K. Dashnaw
   * */
  public void createNewProject()
  {

    //Prompt user to confirm:
    if(this.getSceneController().createPromptWindow("Add project to system?").equalsIgnoreCase("confirmationPressed"))
    {
      //Add project to system.
      if(this.getActiveModel().addProject(this.getActiveModel().getSelectedProject()))
      {
        //Project successfully added!
        //Now check if project should be added to the Dashboard:
        if (this.getActiveModel().getSelectedProject().isDashboardProject())
        {
          this.getActiveModel().getDashboardProgressReports().addProgressReport(this.activeModel.getSelectedProject().generateProgressReport());
        }
        //Update console with message:
        this.getSceneController().setGUI_ConsoleMessage("New projected added to system. System saved.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

        //Reset creation view.
        refresh();

        //Redirect user back to the "view project" page:
        try
        {
          this.getSceneController().openWindow("Projects", this.getGUI_Console());
        }
        catch(IOException error)
        {
          System.out.println("Unknown error occurred after project creation. No data has been lost.");
        }
      }
      else
      {
        //Project was NOT added successfully.
        //Update console with error message from the model:
        this.getSceneController().setGUI_ConsoleMessage(this.getActiveModel().getInitializationErrorMessage());
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      }
    }
  }
}