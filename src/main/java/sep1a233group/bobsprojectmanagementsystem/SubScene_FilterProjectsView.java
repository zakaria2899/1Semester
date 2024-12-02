package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/** <p>This class controls the GUI related view and methods concerning the "Set project filters" GUI stage.
 * This is where filters are set, so that only projects that fall within the filter parameters are shown on the project view stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.</p>
 * @Author: E. Kadrolli & K. Dashnaw
 * */
public class SubScene_FilterProjectsView implements Scene_ControllerInterface
{
  @FXML Button buttonSetFilters;
  @FXML CheckBox residentialProject;
  @FXML CheckBox commercialProject;
  @FXML CheckBox industrialProject;
  @FXML CheckBox roadBuildingProject;
  @FXML CheckBox hideFinishedProjects;
  @FXML CheckBox hideOngoingProjects;
  @FXML TextField ownerPhoneNumber;
  @FXML TextField budgetRangeMax;
  @FXML TextField durationMax;
  @FXML TextField durationMin;
  @FXML TextField budgetRangeMin;
  private MainModel activeModel;
  private SceneController sceneController;






  /** <p>This method initiates the scene/stage it is called on and ties it to the mapping done in the SceneController,
   * thus allowing the overall SceneController to know about this active stage/scene.<br>It is only run on the first initialization.</p>
   * @param activeModel a MainModel Object reference attached to each scene. It allows the scene to call methods from the model to perform operations.
   * @param sceneController a reference to the overall responsible SceneController, which ties all the sub-scenes/stages together.
   * */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    setActiveModel(activeModel);
    setSceneController(sceneController);
    refresh();
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






  /**<p>This method is called every time this scene/stage becomes active. It is used to refresh onscreen data. </p>*/
  @Override public void refresh()
  {
    //This method should do nothing apart from the below internal debug note. Not refreshing this page will let the previously entered
    //filters remain in view. On session restart these will likewise be reset to nothing, as intended.
    loadFilterSettings();
    enableSetFiltersButton();
    System.out.println("Set project filters scene is now the active stage.");
  }





/** <p> This method performs validation on the displayed and if the data fields pass that validation it enables the "save filters" button.
 * Else if applies a tooltip to the TextField with the incorrect data, so the user may adjust the entered values.</p>
 * @Author: K. Dashnaw
 * */
  public void enableSetFiltersButton()
  {
    boolean validationPassed = true;
    //Enables the filters button after input validation has been performed.

    TextField[] textFields = {this.budgetRangeMin, this.budgetRangeMax, this.durationMin, this.durationMax, this.ownerPhoneNumber}; //Insert TextFields from screen page

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

    buttonSetFilters.setDisable(!validationPassed);
  }





  /** <p> This method simply directs the user back to the main project overview, and does NOT apply
   * any if the filters the user may have been in the process of setting.</p>
   * @param actionEvent ActionEvent that contains a reference to the element which prompted this method to execute.
   * @throws IOException If an unexpected error occurs.
   * @Author: K. Dashnaw
   * */
  public void cancel(ActionEvent actionEvent) throws IOException
  {
    //Should be tied to the cancel button only!
    this.getSceneController().loadNewWindow("Projects_MainView");

    Button cancelButton = (Button) actionEvent.getSource();

    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }





/** This method loads any previous set filters upon first entering this stage, and displays these to the appropriate elements on screen.
 * @Author: K. Dashnaw
 * */
  public void loadFilterSettings()
  {
    Object[] filterSettings = this.getActiveModel().getFilterSettings();

    this.budgetRangeMin.setText("" + (filterSettings[0]));
    this.budgetRangeMax.setText("" + (filterSettings[1]));
    this.durationMin.setText("" + (filterSettings[2]));
    this.durationMax.setText("" + (filterSettings[3]));
    this.ownerPhoneNumber.setText("" + filterSettings[4]);
    this.hideFinishedProjects.setSelected((Boolean) filterSettings[5]);
    this.hideOngoingProjects.setSelected((Boolean) filterSettings[6]);
    this.residentialProject.setSelected((Boolean) filterSettings[7]);
    this.commercialProject.setSelected((Boolean) filterSettings[8]);
    this.industrialProject.setSelected((Boolean) filterSettings[9]);
    this.roadBuildingProject.setSelected((Boolean) filterSettings[10]);
  }





  /** This method reset any already set filters to their default values (which is NO FILTERS)
   * @Author: K. Dashnaw
   * */
  public void resetFilters()
  {
    this.budgetRangeMin.setText("");
    this.budgetRangeMax.setText("");
    this.durationMin.setText("");
    this.durationMax.setText("");
    this.ownerPhoneNumber.setText("");
    this.hideFinishedProjects.setSelected(false);
    this.hideOngoingProjects.setSelected(false);
    this.residentialProject.setSelected(false);
    this.commercialProject.setSelected(false);
    this.industrialProject.setSelected(false);
    this.roadBuildingProject.setSelected(false);

    this.getActiveModel().setFilterSettings("", "", "", "", "", false, false, false, false, false, false);
  }





  /** This method applies and saves the entered filtering data.
   * @Author: K. Dashnaw
   * */
  public void setFiltersButton()
  {
    String minBudget = this.budgetRangeMin.getText();
    String maxBudget = this.budgetRangeMax.getText();
    String minDuration = this.durationMin.getText();
    String maxDuration = this.durationMax.getText();
    String ownerPhoneNumber = this.ownerPhoneNumber.getText();
    boolean hideFinished = hideFinishedProjects.isSelected();
    boolean hideOngoing = hideOngoingProjects.isSelected();
    boolean hideResidential = residentialProject.isSelected();
    boolean hideCommercial = commercialProject.isSelected();
    boolean hideIndustrial = industrialProject.isSelected();
    boolean hideRoad = roadBuildingProject.isSelected();

    this.getActiveModel().setFilterSettings(minBudget, maxBudget, minDuration, maxDuration, ownerPhoneNumber, hideFinished, hideOngoing, hideResidential, hideCommercial, hideIndustrial, hideRoad);

    if(this.budgetRangeMin.getText().isBlank())
    {
      minBudget = "" + Integer.MIN_VALUE;
    }
    if(this.budgetRangeMax.getText().isBlank())
    {
      maxBudget = "" + Integer.MAX_VALUE;
    }
    if(this.durationMin.getText().isBlank())
    {
      minDuration = "" + Integer.MIN_VALUE;
    }
    if(this.durationMax.getText().isBlank())
    {
      maxDuration = "" + Integer.MAX_VALUE;
    }

    //Applies the filters.
    this.getActiveModel().setFilteredProjectsList(this.getActiveModel().filterProject(Integer.parseInt(minBudget),Integer.parseInt(maxBudget),Integer.parseInt(minDuration),Integer.parseInt(maxDuration), ownerPhoneNumber, hideFinished, hideOngoing, hideResidential, hideCommercial, hideIndustrial, hideRoad));

    Stage stage = (Stage) buttonSetFilters.getScene().getWindow();
    stage.close();
  }
}