package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/** <p>This class controls the GUI related view and methods concerning the "View Project Details" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.</p>
 * @Author: K. Dashnaw
 */
public class SubScene_DetailsView implements Scene_ControllerInterface
{
  //Residential Project control attributes
  @FXML GridPane gridResidentialUniqueData;
  @FXML TextField tResBathroomNumber;
  @FXML TextField tResNumberKitchens;
  @FXML TextField tResNumberOfOtherPlumbing;
  @FXML TextField tResDuration;
  @FXML TextField tResBuildingSize;
  @FXML CheckBox isRenovation;

  //Commercial Project control attributes
  @FXML GridPane gridCommercialUniqueData;
  @FXML TextField tComNumberFloors;
  @FXML TextField tComDuration;
  @FXML TextField tComBuildingSize;
  @FXML TextArea taComIntendedUse;

  //Industrial Project control attributes
  @FXML GridPane gridIndustrialUniqueData;
  @FXML TextField tIndDuration;
  @FXML TextField tIndFacilitySize;
  @FXML TextArea taIndFacilityType;

  //Road Project control attributes
  @FXML GridPane gridRoadUniqueData;
  @FXML TextField tRDLength;
  @FXML TextField tRDWidth;
  @FXML TextField tRDDuration;
  @FXML TextArea taRDBridgeTunnelInfo;
  @FXML TextArea taRDEnvironmentInfo;

  //Shared Control attributes
  @FXML TextField GUI_Console;
  @FXML GridPane gridProjectDataContainer;
  @FXML GridPane gridCommonProjectDataContainer;
  @FXML Button buttonCancel;
  @FXML TextArea taProjectDescription;
  @FXML TextArea taManagersComments;
  @FXML DatePicker date_StartDateField;
  @FXML DatePicker date_EndDateField;
  @FXML CheckBox checkBox_AddToDashBoard;
  @FXML Label labelLastProjectSave;
  @FXML Label labelHTMLExportDate;

  //Other field Attributes:
  private MainModel activeModel;
  private SceneController sceneController;






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
    refresh();
  }






  /**<p>This method is called every time this scene/stage becomes active. It is used to refresh onscreen data. </p>*/
  @Override public void refresh()
  {
    //Refresh the page, as it is shown on a clean load:
    this.getSceneController().showUniqueProjectDataFields(gridResidentialUniqueData, gridCommercialUniqueData, gridIndustrialUniqueData, gridRoadUniqueData);
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

    System.out.println("Edit Scene is now the active stage.");

    //Loop though the shared project data gridPane and insert selected project values inside TextFields - while also ensuring fields are reset for previous views of this screen.
    for (Node node : gridProjectDataContainer.getChildren())
    {
      if (node instanceof TextArea)
      {
        ((TextArea) node).setText("");
      }
      else if (node instanceof TextField)
      {
        ((TextField) node).setText(this.getSceneController().loadProjectData_String((TextField) node));
        ((TextField) node).setTooltip(null);
        node.setStyle("-fx-text-fill: black;");
      }
      else if(node instanceof DatePicker)
      {
        ((DatePicker) node).getEditor().setText("");
      }
    }

    //Loop though the commonly used project data gridPane and insert selected project values inside TextFields - while also ensuring fields are reset for previous views of this screen.
    //Loop though non fx:id referenced data fields and reset these.
    for (Node node : gridCommonProjectDataContainer.getChildren())
    {
      if (node instanceof TextField)
      {
        ((TextField) node).setText(this.getSceneController().loadProjectData_String((TextField) node));
        ((TextField) node).setTooltip(null);
        node.setStyle("-fx-text-fill: black;");
      }
      else if(node instanceof CheckBox)
      {
        ((CheckBox) node).setSelected(this.getSceneController().loadProjectData_Checkbox((CheckBox) node));
      }
    }

    DecimalFormat dFormat = new DecimalFormat("###,###.####");
    dFormat.setRoundingMode(RoundingMode.HALF_UP);

    //Set the remaining more "hard to get to" data fields:
    taProjectDescription.setText(this.getActiveModel().getSelectedProject().getProjectInformation().getProjectDescription());
    taManagersComments.setText(this.getActiveModel().getSelectedProject().getProjectInformation().getProjectManagerComments());
    date_StartDateField.getEditor().setText("" + this.getActiveModel().getSelectedProject().getProjectStartDate());
    date_EndDateField.getEditor().setText("" + this.getActiveModel().getSelectedProject().getProjectEndDate());

    //Set the Project specific datafields:
    if(this.getActiveModel().getSelectedProject().getProjectType().equalsIgnoreCase("residential"))
    {
      ResidentialProject project = (ResidentialProject) this.getActiveModel().getSelectedProject();
      tResBathroomNumber.setText("" + project.getNumberOfBathrooms());
      tResNumberKitchens.setText("" + project.getNumberOfKitchens());
      tResNumberOfOtherPlumbing.setText("" + project.getNumberOfOtherRoomsWithPlumbing());
      tResDuration.setText("" + project.getProjectDuration());
      tResBuildingSize.setText(dFormat.format(project.getBuildingSize()));
      isRenovation.setSelected(project.getIsRenovation());
    }
    else if(this.getActiveModel().getSelectedProject().getProjectType().equalsIgnoreCase("commercial"))
    {
      CommercialProject project = (CommercialProject) this.getActiveModel().getSelectedProject();
      tComNumberFloors.setText("" + project.getNumberOfFloors());
      tComDuration.setText("" + project.getProjectDuration());
      tComBuildingSize.setText(dFormat.format(project.getBuildingSize()));
      taComIntendedUse.setText(project.getIntendedBuildingUse());
    }
    else if(this.getActiveModel().getSelectedProject().getProjectType().equalsIgnoreCase("industrial"))
    {
      IndustrialProject project = (IndustrialProject) this.getActiveModel().getSelectedProject();
      tIndDuration.setText("" + project.getProjectDuration());
      tIndFacilitySize.setText(dFormat.format(project.getFacilitySize()));
      taIndFacilityType.setText(project.getFacilityType());
    }
    else if(this.getActiveModel().getSelectedProject().getProjectType().equalsIgnoreCase("road"))
    {
      RoadProject project = (RoadProject) this.getActiveModel().getSelectedProject();
      tRDLength.setText(dFormat.format(project.getRoadLength()));
      tRDWidth.setText(dFormat.format(project.getRoadWidth()));
      tRDDuration.setText("" + project.getProjectDuration());
      taRDBridgeTunnelInfo.setText(project.getBridgeOrTunnelDetails());
      taRDEnvironmentInfo.setText(project.getEnvironmentalOrGeographicalChallenges());
    }

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
    //Prompt the user to confirm they wish to leave this screen.
    if (!(createPromptWindow()))
    {
      //Prompt the user to confirm they really wish to navigate away from this view - loosing any unsaved data.
      //If the user aborted, we break the current method here.
      return;
    }
    //Proceed with application termination.
    this.getSceneController().exitApplication();

    //Update console message, in case an error occurred above:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }






  /** <p>Method ensures that the user is directed back to the previous view pane upon satisfied with
   *  his/her browsing of the selected projects details.</p>
   * @param actionEvent A ActionEvent reference to the source element which prompted this method to execute.
   * @Author: K. Dashnaw
   */
  public void returnToPreviousView (ActionEvent actionEvent) throws IOException
  {
    //Load the scene the user selected.
    String buttonText = ((Button) actionEvent.getSource()).getText().toLowerCase();
    this.getSceneController().openWindow(buttonText, this.getGUI_Console());
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
    this.getSceneController().checkBoxChecker(actionEvent);
  }






  /** <p>>Method used to create a confirmation prompt window, in order to prompt the user before navigating away from the creation view,
   * which could otherwise result loss of entered non-saved data.!</p
   * @return A boolean that returns TRUE if the uses confirms they wish to proceed with the action, or FALSE if the user aborts.
   * @Author: K. Dashnaw
   */
  public boolean createPromptWindow()
  {
    String confirmationAction = this.getSceneController().createPromptWindow("You are terminating this application.\n\nDo you wish to proceed?\n");

    switch (confirmationAction)
    {
      case "cancelPressed":
        this.getSceneController().setGUI_ConsoleMessage("Exit aborted.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Exit aborted.");
        return false; //do not proceed with cancel operation
      case "confirmationPressed":
        this.getSceneController().setGUI_ConsoleMessage("Terminating application");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Terminating application.");
        refresh();
        return true; //Proceed with cancel operation
      default:
        return false; //do not proceed with cancel operation
    }
  }
}