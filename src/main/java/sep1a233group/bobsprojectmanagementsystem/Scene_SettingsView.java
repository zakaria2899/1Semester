package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

/** <p>This class controls the GUI related view and methods concerning the "Settings View" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.</p>
 * @Author: Z. Banouri & K. Dashnaw
 */
public class Scene_SettingsView implements Scene_ControllerInterface
{
  private MainModel activeModel;
  private SceneController sceneController;
  @FXML TextField timeLineRP;
  @FXML TextField numberOfKitchensRP;
  @FXML TextField numberOfBathroomsRP;
  @FXML TextField roomsWithPlumbingRP;
  @FXML TextField timeLineCP;
  @FXML TextField numberOfFloorsCP;
  @FXML TextField timeLineIP;
  @FXML TextField timeLineRCP;
  @FXML TextField bridgesOrTunnelsRCP;
  @FXML TextField enviromentalOrGeographicalRCP;
  @FXML Label labelLastProjectSave;
  @FXML Label labelHTMLExportDate;
  @FXML CheckBox renovationCheckbox;
  @FXML CheckBox newBuildCheckbox;
  @FXML Button saveChangesButton;
  @FXML TextField GUI_Console;






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






  /** <p>This method initiates the scene/stage it is called on and ties it to the mapping done in the SceneController,
   * thus allowing the overall SceneController to know about this active stage/scene.<br>It is only run on the first initialization.</p>
   * @param activeModel a MainModel Object reference attached to each scene. It allows the scene to call methods from the model to perform operations.
   * @param sceneController a reference to the overall responsible SceneController, which ties all the sub-scenes/stages together.
   * */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    this.setActiveModel(activeModel);
    setSceneController(sceneController);
    this.setGUI_Console(this.GUI_Console);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    refresh();

    System.out.println("Project Settings view Scene is now active");
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






  /** <p>This method reads the already existing default system settings from the MainModel
   * (connected through the sceneController) and displays this information in appropriate
   * fields on the settings GUI.</p>
   * @Author: Z. Banouri
    */
  public void loadSystemSettings()
  {
    //Settings for Residential Projects
    timeLineRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getProjectDuration());
    numberOfKitchensRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getNumberOfKitchens());
    numberOfBathroomsRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getNumberOfBathrooms());
    roomsWithPlumbingRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getNumberOfOtherRoomsWithPlumbing());

    if(this.getActiveModel().getDefaultResidentialSettings().isRenovation())
    {
      renovationCheckbox.setSelected(true);
      newBuildCheckbox.setSelected(false);
    }
    else
    {
      renovationCheckbox.setSelected(false);
      newBuildCheckbox.setSelected(true);
    }

    //Settings for Commercial Projects
    timeLineCP.setText("" + this.getActiveModel().getDefaultCommercialSettings().getProjectDuration());
    numberOfFloorsCP.setText("" + this.getActiveModel().getDefaultCommercialSettings().getNumberOfFloors());

    //Settings for Industrial Projects
    timeLineIP.setText("" + this.getActiveModel().getDefaultIndustrialSettings().getProjectDuration());

    //Settings for Road construction Projects
    timeLineRCP.setText("" + this.getActiveModel().getDefaultRoadSettings().getProjectDuration());
    bridgesOrTunnelsRCP.setText(this.getActiveModel().getDefaultRoadSettings().getBridgesOrTunnelDetails());
    enviromentalOrGeographicalRCP.setText(this.getActiveModel().getDefaultRoadSettings().getEnviromentalOrGeographicalChallenges());
  }






  /** <p>This method handles the process of saving all the user entered default settings information.</p>
   * @Author: Z. Banouri
   */
  public void saveSystemSettings()
  {
    //Settings for Residential Projects
    this.getActiveModel().getDefaultResidentialSettings().setProjectDuration(Integer.parseInt(timeLineRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setNumberOfKitchens(Integer.parseInt(numberOfKitchensRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setNumberOfBathrooms(Integer.parseInt(numberOfBathroomsRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setNumberOfOtherRoomsWithPlumbing(Integer.parseInt(roomsWithPlumbingRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setRenovation(!newBuildCheckbox.isSelected());

    //Settings for Commercial Projects
    this.getActiveModel().getDefaultCommercialSettings().setProjectDuration(Integer.parseInt(timeLineCP.getText().trim()));
    this.getActiveModel().getDefaultCommercialSettings().setNumberOfFloors(Integer.parseInt(numberOfFloorsCP.getText().trim()));

    //Settings for Industrial Projects
    this.getActiveModel().getDefaultIndustrialSettings().setProjectDuration(Integer.parseInt(timeLineIP.getText().trim()));

    //Settings for Road construction Projects
    this.getActiveModel().getDefaultRoadSettings().setProjectDuration(Integer.parseInt(timeLineRCP.getText().trim()));
    this.getActiveModel().getDefaultRoadSettings().setBridgesOrTunnelDetails(bridgesOrTunnelsRCP.getText().trim());
    this.getActiveModel().getDefaultRoadSettings().setEnviromentalOrGeographicalChallenges(enviromentalOrGeographicalRCP.getText().trim());

    //Update console
    this.getSceneController().setGUI_ConsoleMessage("Default project settings saved.");
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    //Send user back to dashboard:
    try
    {
      this.getSceneController().loadNewWindow("Dashboard");
    }
    catch(IOException error)
    {
      this.getSceneController().setGUI_ConsoleMessage("Unknown error occurred after saving settings. Try restarting the application");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }






  /**<p>This method is called every time this scene/stage becomes active. It is used to refresh onscreen data. </p>*/
  @Override public void refresh()
  {
    loadSystemSettings();

    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
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

    System.out.println("Project Settings Scene is now the active stage.");
  }






  /** <p>This method simply calls the common method with the same name, from the SceneController.<br>
   * Check SceneController.openWindow() for a more detailed description.</p>
   * @param actionEvent ActionEvent that contains a reference to the element which prompted this method to execute.
   * @throws IOException If something unexpected occurs.
   * @Author: K. Dashnaw
   * */
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

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
  }






  /** <p>This method simply calls the common method with the same name, from the SceneController.<br>
   * Check SceneController.exitApplication() for a more detailed description.</p>
   * @Author: K. Dashnaw
   * */
  public void exitApplication()
  {
    this.getSceneController().exitApplication();
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
  public void validate_NotEmpty(KeyEvent keyNode)
  {
    resetValidation();
    if(!(getSceneController().validate_NotEmpty(keyNode)))
    {
      //Update console with error set in SceneController
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
    else
    {
      enableSaveButton();
    }
  }






  /** <p>Method disables the "save" button and is used in conjunction with the validation fields to ensure that the
   * "save" button only is enabled when proper data is ready to be added to the system.</p>
   * @Author: K. Dashnaw
   * */
  private void resetValidation()
  {
    saveChangesButton.setDisable(true);

    //Update console:
    this.getSceneController().setGUI_ConsoleMessage("");
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }





  /** <p>Method enables the "save" button and is used in conjunction with the validation fields to ensure that the
   * "save" button only is enabled when proper data is validated and ready to be added to the system.</p>
   * @Author: K. Dashnaw
   * */
  public void enableSaveButton()
  {
    boolean validationPassed = true;
    //Enables the filters button after input validation has been performed.

    TextField[] textFields = {this.timeLineRP, this.numberOfKitchensRP, this.numberOfBathroomsRP, this.roomsWithPlumbingRP,
        this.timeLineCP, this.numberOfFloorsCP, this.timeLineIP, this.timeLineRCP}; //Insert TextFields from screen page

    //Validate all textFields:
    for (int i = 0; i < textFields.length; i++)
    {
      if(!(textFields[i].getText().isBlank()))
      {
        //Check if field is a String:
        try
        {
          //If the below test succeeds, then this is a number.
          double testValue = Double.parseDouble(textFields[i].getText());
          if (testValue < 0)
          {
            //Check if it is a negative value. If so, throw an error and catch it later.
            throw new NumberFormatException();
          }

          //Passed validation, ensure previous tooltips are removed and text colors reverted:
          if (textFields[i].getTooltip() != null)
          {
            textFields[i].setTooltip(null);
            textFields[i].setStyle("-fx-text-fill: black;");
          }
        }
        catch (NumberFormatException error)
        {
          //Field is a number. Show a tooltip!
          this.getSceneController().addErrorTooltip(textFields[i], "-fx-text-fill: red;", "Field must be a positive number");
          validationPassed = false;

        }
      }
    }
    saveChangesButton.setDisable(!validationPassed);
  }






  /** <p>This method is called from "On Action" EventHandlers in the .fxml scene
   * Method performs calls a validation check on all screen data fields, and if all data is valid, enables the 'save button'<br><br>
   * It receives a "ActionEvent node" parses this as a "CheckBox" and checks if it is selected or not.<br><br>
   * <b>Warning: ActionEvent node must have a source type of CheckBox, else errors will occur.</b></p>
   * @param actionEvent A ActionEvent reference to the source element which prompted this method to execute.
   * @Author: K. Dashnaw
   * */
  public void checkBoxChecker(ActionEvent actionEvent)
  {
    saveChangesButton.setDisable(true);
    CheckBox checkBox = (CheckBox) actionEvent.getSource();

    //Check which box has been selected, and unselect the other
    if(checkBox.getText().equalsIgnoreCase("Renovation project") && checkBox.isSelected())
    {
      //RenovationCheckbox was selected
      newBuildCheckbox.setSelected(false);
      renovationCheckbox.setSelected((true));
    }
    else
    {
      newBuildCheckbox.setSelected(true);
      renovationCheckbox.setSelected((false));
    }
    enableSaveButton();
  }
}

