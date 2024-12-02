package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;






/** <p>This class controls the GUI related view and methods concerning the "quick update project" GUI functionality.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.</p>
 * @Author: K. Dashnaw
 */
public class SubScene_QuickUpdateProjectView implements Scene_ControllerInterface
{
  //Shared Control attributes
  @FXML TextField GUI_Console;
  @FXML Button buttonEditProject;
  @FXML Button buttonCancel;
  @FXML CheckBox checkBox_AddToDashBoard;
  @FXML GridPane gridCommonProjectDataContainer;

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

    System.out.println("Quick Update Scene is now the active stage.");

    //Loop though the shared project data gridPane and insert selected project values inside TextFields - while also ensuring fields are reset for previous views of this screen.
    for (Node node : gridCommonProjectDataContainer.getChildren())
    {
     if (node instanceof TextField)
      {
        ((TextField) node).setText(this.loadProjectData_String((TextField) node));
        ((TextField) node).setTooltip(null);
        node.setStyle("-fx-text-fill: black;");
      }
     else if(node instanceof CheckBox)
     {
       ((CheckBox) node).setSelected(this.getSceneController().loadProjectData_Checkbox((CheckBox) node));
       node.setDisable(false);
     }
    }
    checkBox_AddToDashBoard.setText("Track on Dashboard");

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
    //Prompt the user to confirm they wish to leave this screen.
    if (!(createPromptWindow()))
    {
      //Prompt the user to confirm they really wish to navigate away from the edit view - loosing all unsaved data.
      //If the user aborted, we break the current method here.
      return;
    }
    //Load the scene the user selected.
    String buttonText = ((Button) actionEvent.getSource()).getText().toLowerCase();
    this.getSceneController().openWindow(buttonText, this.getGUI_Console());
  }






  /** <p>>Method used to create a confirmation prompt window, in order to prompt the user before navigating away from the edit view,
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
        this.getSceneController().setGUI_ConsoleMessage("Cancel confirmed. No changes were saved.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel confirmed. No changes were saved.");

        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
        return true; //Proceed with cancel operation
      default:
        return false; //do not proceed with cancel operation
    }
  }






  /** <p>This method returns the selected projects Data Field values that correspond with the shared project data fields.
   * This is intended for insertion into the on-screen editable textFields, so that already existing data is pre-entered for the user.</p>
   * @param node is a reference to the TextField node in which the returned String value shall be inserted.
   * @return A String value intended to be inserted into the above TextField node.
   * @Author: K. Dashnaw
   * */
  public String loadProjectData_String (TextField node)
  {
    switch (node.getPromptText())
    {
      case "Man-Hours in hours":
        return "" + this.getActiveModel().getSelectedProject().getHumanRessources().getManHoursSpent();
      case "Est. total number of hours":
        return "" + this.getActiveModel().getSelectedProject().getHumanRessources().getTotalManHoursNeeded();
      case "Expenses in USD":
        return "" + this.getActiveModel().getSelectedProject().getFinances().getMaterialExpences();
      case "Budget in USD":
        return "" + this.getActiveModel().getSelectedProject().getFinances().getTotalBudget();
      default:
        break;
    }
    return "";
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
    buttonEditProject.setDisable(true);

    //Update console with an error:
    this.getSceneController().setGUI_ConsoleMessage("");
    this.getGUI_Console()
        .setText(this.getSceneController().getGUI_ConsoleMessage());

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
    addCommonProjectData(this.getActiveModel().getSelectedProject(), userInput);
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
    buttonEditProject.setDisable(true);
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
    addCommonProjectData(this.getActiveModel().getSelectedProject(), value);
  }






  /** <p>This method is used in conjunction with the "addTemporaryProjectData(TextField text) method".<br>
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * @param project This is a reference to the super class that all construction projects are a member of.
   * @param text This is a reference to the node containing the information to add to the project.
   * @Author: K. Dashnaw
   * */
  public void addCommonProjectData(ConstructionProject project, TextField text)
  {
    boolean dataAddedToProject = false;

    //TODO: Implement handling of picture file link!

    switch (text.getPromptText())
    {
      case "Man-Hours in hours":
        project.getHumanRessources().setManHoursSpent(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Est. total number of hours":
        project.getHumanRessources().setTotalManHoursNeeded(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Budget in USD":
        this.getSceneController().validateIsWithinNormalMargins(text);
        project.getFinances().setTotalBudget(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Expenses in USD":
        this.getSceneController().validateIsWithinNormalMargins(text);
        project.getFinances().setMaterialExpences(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Track on Dashboard_True", "Dashboard is already full._True":
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
      case "Mark project as confidential_True":
        project.setProjectConfidentiality(true);
        dataAddedToProject = true;
        break;
      case "Mark project as confidential_False":
        project.setProjectConfidentiality(false);
        dataAddedToProject = true;
        break;
      case "Mark project as completed_True":
        project.setProjectFinished(true);
        dataAddedToProject = true;
        break;
      case "Mark project as completed_False":
        project.setProjectFinished(false);
        dataAddedToProject = true;
        break;
      default:
        break;
    }

    if(dataAddedToProject)
    {
      activateEditButton();
    }
  }






   /** <p>This method checks if all required data fields have been filled out before enabling the "create project" button.</p>
    * @Author: K. Dashnaw
   * */
  public void activateEditButton()
  {
    //First check if project has all necessary values for creation:
    boolean dataIsMissing = false;
    ConstructionProject activeProject = this.getActiveModel().getSelectedProject();

    //Human Resources: Attributes checked are; Human Resources.
    if(activeProject.getHumanRessources().getTotalManHoursNeeded() == 0)
    {
      dataIsMissing = true;
    }
    //Finances: Attributes checked are; Total budget.
    else if(activeProject.getFinances().getTotalBudget() == 0)
    {
      dataIsMissing = true;
    }

    //If all required fields are present. Activate the edit button now.
    buttonEditProject.setDisable(dataIsMissing);
  }






  /** <p>This method finalizes the project creation by calling relevant methods from the MainModel after data has been properly validated. It also asks the user to confirm their creation before finalizing.</p>
   * @Author: K. Dashnaw
   * */
  public void editProject()
  {
    //Prompt user to confirm:
    if(this.getSceneController().createPromptWindow("Save changes?").equalsIgnoreCase("confirmationPressed"))
    {
      //Remove the original project from the project_list, and replace with this modified one.
      this.getActiveModel().removeProject(this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()));

      //Add modified project to system.
      if(this.getActiveModel().editProject(this.getActiveModel().getSelectedProject()))
      {
        //Project successfully added!

        //Now we refresh the dashboard object container:
        this.getActiveModel().refreshDashboardProjects();

        //Update console with message:
        this.getSceneController().setGUI_ConsoleMessage("Project successfully modified. System saved.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

        Stage stage = (Stage) buttonEditProject.getScene().getWindow();
        stage.close();
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