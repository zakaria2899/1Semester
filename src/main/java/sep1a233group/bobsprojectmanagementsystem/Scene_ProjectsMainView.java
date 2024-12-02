package sep1a233group.bobsprojectmanagementsystem;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;





/** <p>This class controls the GUI related view and methods concerning the "View Projects" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.</p>
 * @Author: E. Kadrolli & K. Dashnaw
 * */
public class Scene_ProjectsMainView implements Scene_ControllerInterface
{
  @FXML TableView<ConstructionProject> mainTableView;
  @FXML TableColumn<ConstructionProject, String> colProjectType;
  @FXML TableColumn<ConstructionProject, String> colProjectName;
  @FXML TableColumn<ConstructionProject, String> colProjectCity;
  @FXML TableColumn<ConstructionProject, String> colProjectStatus;
  @FXML TableColumn<ConstructionProject, String> colProjectDeadline;
  @FXML TableColumn<ConstructionProject, String> colProjectBudget;
  @FXML TableColumn<ConstructionProject, String> colProjectExpenses;
  @FXML TableColumn<ConstructionProject, String> colProjectManHoursUsed;
  @FXML TableColumn<ConstructionProject, String> colProjectManHoursTotal;
  @FXML TableColumn<ConstructionProject, String> colProjectConfidentiality;
  @FXML TableColumn<ConstructionProject, String> colProjectIsDashboardProject;
  @FXML Button editButton;
  @FXML Button removeButton;
  @FXML Button detailsButton;

  @FXML TextField GUI_Console;
  @FXML Label labelLastProjectSave;
  @FXML Label labelHTMLExportDate;
  private MainModel activeModel;
  private SceneController sceneController;
  private ArrayList<ConstructionProject> projectListCopy;





  /** <p>Gets a reference to the GUI_Console on this page.</p>
   * @return TextField containing a reference to this pages' GUI Console.
   * @Author: K. Dashnaw
   * */
  public TextField getGUI_Console()
  {
    return GUI_Console;
  }





  /** <p>Sets a reference to the GUI_Console on this page.</p>
   * @param GUI_Console a TextField containing a reference to this pages' GUI Console.
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




/** <p> This method retrieves a copy of the master project list, in order to have a safe list to perform filtering
 * operations on, without possibly deleting projects from the master list unintentionally.</p>
 * @return A duplicate ArrayList of ConstructionProjects, based on the master list.
 * @Author: K. Dashnaw
 * */
  public ArrayList<ConstructionProject> getProjectListCopy()
  {
    return projectListCopy;
  }




  /** <p> This method sets a copy of the master project list, in order to have a safe list to perform filtering
   * operations on, without possibly deleting projects from the master list unintentionally.</p>
   * @param projectListCopy A duplicate ArrayList of ConstructionProjects, based on the master list.
   * @Author: K. Dashnaw
   * */
  public void setProjectListCopy(ArrayList<ConstructionProject> projectListCopy)
  {
    this.projectListCopy = projectListCopy;
  }





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
    setGUI_Console(this.GUI_Console);
    GUI_Console.setText(this.getSceneController().getGUI_ConsoleMessage());
    getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    //Set the default view filter settings:
    this.getActiveModel().setFilterSettings("", "", "", "", "", false, false, false, false, false, false);
    refresh();
  }





  /**<p>This method is called every time this scene/stage becomes active. It is used to refresh onscreen data. </p>*/
  @Override public void refresh()
  {
    setProjectListCopy(this.getActiveModel().getFilteredProjectsList());

    mainTableView.getItems().clear();
    editButton.setDisable(true);
    removeButton.setDisable(true);
    detailsButton.setDisable(true);

    if(!getProjectListCopy().isEmpty())
    {
      displayProjects();
    }

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

    System.out.println("Project Table View is now the active stage.");
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




  /** <p>This method loads all project data that have passed the filtering operations, and displays these to screen in an overview manner.</p>
   * @Author: K. Dashnaw
   * */
  public void displayProjects()
  {
    mainTableView.setEditable(false);
    this.setProjectListCopy(this.getActiveModel().getFilteredProjectsList());

    DecimalFormat dFormat = new DecimalFormat("#.##");
    dFormat.setRoundingMode(RoundingMode.HALF_UP);

    DecimalFormat dFormat1 = new DecimalFormat("###,###.##");
    dFormat.setRoundingMode(RoundingMode.HALF_UP);

    colProjectType.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getProjectType())));
    colProjectType.setStyle( "-fx-alignment: CENTER;");
    colProjectName.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getProjectInformation().getProjectName())));
    colProjectName.setStyle( "-fx-alignment: CENTER;");
    colProjectCity.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getProjectAddress().getCity())));
    colProjectCity.setStyle( "-fx-alignment: CENTER;");
    colProjectStatus.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isProjectFinished())));
    colProjectStatus.setStyle( "-fx-alignment: CENTER;");
    colProjectDeadline.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getProjectEndDate())));
    colProjectDeadline.setStyle( "-fx-alignment: CENTER;");
    colProjectBudget.setCellValueFactory(data -> new SimpleStringProperty("$" + dFormat.format((data.getValue().getFinances().getTotalBudget() / 1000)) + "k"));
    colProjectBudget.setStyle( "-fx-alignment: CENTER;");
    colProjectExpenses.setCellValueFactory(data -> new SimpleStringProperty("$" + dFormat.format(data.getValue().getFinances().getMaterialExpences() / 1000) + "k"));
    colProjectExpenses.setStyle( "-fx-alignment: CENTER;");
    colProjectManHoursUsed.setCellValueFactory(data -> new SimpleStringProperty(dFormat1.format(data.getValue().getHumanRessources().getManHoursSpent())));
    colProjectManHoursUsed.setStyle( "-fx-alignment: CENTER;");
    colProjectManHoursTotal.setCellValueFactory(data -> new SimpleStringProperty(dFormat1.format(data.getValue().getHumanRessources().getTotalManHoursNeeded())));
    colProjectManHoursTotal.setStyle( "-fx-alignment: CENTER;");
    colProjectConfidentiality.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isProjectConfidential())));
    colProjectConfidentiality.setStyle( "-fx-alignment: CENTER;");
    colProjectIsDashboardProject.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isDashboardProject())));
    colProjectIsDashboardProject.setStyle( "-fx-alignment: CENTER;");

    mainTableView.getItems().addAll(getProjectListCopy());

    //Manipulate the booleans for better readability:
    colProjectStatus.setCellValueFactory(cellData -> {
          boolean status = cellData.getValue().isProjectFinished();
          String statusAsString;
          if(status)
          {
            statusAsString = "Completed";
          }
          else
          {
            statusAsString = "Ongoing";
          }
          return new ReadOnlyStringWrapper(statusAsString);
    });

    colProjectConfidentiality.setCellValueFactory(cellData -> {
      boolean confidentiality = cellData.getValue().isProjectConfidential();
      String statusAsString;
      if(confidentiality)
      {
        statusAsString = "Confidential";
      }
      else
      {
        statusAsString = "Non-Confidential";
      }
      return new ReadOnlyStringWrapper(statusAsString);
    });

    colProjectIsDashboardProject.setCellValueFactory(cellData -> {
      boolean dashboardStatus = cellData.getValue().isDashboardProject();
      String statusAsString;
      if(dashboardStatus)
      {
        statusAsString = "Yes";
      }
      else
      {
        statusAsString = "No";
      }
      return new ReadOnlyStringWrapper(statusAsString);
    });
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

    String buttonText = ((Button)actionEvent.getSource()).getText().toLowerCase();
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
   * @Author: K. Dashnaw */
  public void exitApplication()
  {
    this.getSceneController().exitApplication();

    //Update console message, in case an error occurred above:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }




  /** <p>This method updates the selected project that belongs to the MainModel. It evaluates what the user has interacted
   * with and provides the necessary logic to then create a copy of the same project in the master list, where the copy is
   * then set as the selected project - in order to avoid the unintentional deletion of projects.</p>
   * @return TRUE if the project was found in the project list, and false if it wasn't. If false then nothing was selected.
   * @Author: K. Dashnaw
   * */
  public boolean updateSelectedProject()
  {
    String projectType = mainTableView.getSelectionModel().getSelectedItem().getProjectType();
    int originalProjectIndex = -1;

    if(projectType.equalsIgnoreCase("residential"))
    {
      ResidentialProject selectedProject = (ResidentialProject) mainTableView.getSelectionModel().getSelectedItem();
      for (int i = 0; i < this.getActiveModel().getAllProjectsList().size(); i++)
      {
        if(this.getActiveModel().getAllProjectsList().get(i) instanceof ResidentialProject &&
            selectedProject.equals(this.getActiveModel().getAllProjectsList().get(i)))
        {
          originalProjectIndex = i;
          break;
        }
      }
    }
    else if(projectType.equalsIgnoreCase("commercial"))
    {
      CommercialProject selectedProject = (CommercialProject) mainTableView.getSelectionModel().getSelectedItem();
      for (int i = 0; i < this.getActiveModel().getAllProjectsList().size(); i++)
      {
        if(this.getActiveModel().getAllProjectsList().get(i) instanceof CommercialProject &&
            selectedProject.equals(this.getActiveModel().getAllProjectsList().get(i)))
          {
            originalProjectIndex = i;
            break;
          }
      }
    }
    else if(projectType.equalsIgnoreCase("industrial"))
    {
      IndustrialProject selectedProject = (IndustrialProject) mainTableView.getSelectionModel().getSelectedItem();
      for (int i = 0; i < this.getActiveModel().getAllProjectsList().size(); i++)
      {
        if(this.getActiveModel().getAllProjectsList().get(i) instanceof IndustrialProject &&
            selectedProject.equals(this.getActiveModel().getAllProjectsList().get(i)))
        {
          originalProjectIndex = i;
          break;
        }
      }
    }
    else if(projectType.equalsIgnoreCase("road"))
    {
      RoadProject selectedProject = (RoadProject) mainTableView.getSelectionModel().getSelectedItem();
      for (int i = 0; i < this.getActiveModel().getAllProjectsList().size(); i++)
      {
        if(this.getActiveModel().getAllProjectsList().get(i) instanceof RoadProject &&
            selectedProject.equals(this.getActiveModel().getAllProjectsList().get(i)))
        {
          originalProjectIndex = i;
          break;
        }
      }
    }

    if(originalProjectIndex == -1)
    {
      return false; //The project was not found in the system files.
    }
    this.getActiveModel().setProjectIndexPosition(originalProjectIndex);

    //Set active project. Make sure we set COPIES, or else unintended changes might be saved directly to the real project before the user presses the save button!
    if(projectType.equalsIgnoreCase("residential"))
    {
      this.getActiveModel().setSelectedProject((ResidentialProject) this.getActiveModel().getAllProjectsList().get(originalProjectIndex).copy());
      return true; //The active project has been updated!
    }
    else if(projectType.equalsIgnoreCase("commercial"))
    {
      this.getActiveModel().setSelectedProject((CommercialProject) this.getActiveModel().getAllProjectsList().get(originalProjectIndex).copy());
      return true; //The active project has been updated!
    }
    else if(projectType.equalsIgnoreCase("industrial"))
    {
      this.getActiveModel().setSelectedProject((IndustrialProject) this.getActiveModel().getAllProjectsList().get(originalProjectIndex).copy());
      return true; //The active project has been updated!
    }
    else if(projectType.equalsIgnoreCase("road"))
    {
      this.getActiveModel().setSelectedProject((RoadProject) this.getActiveModel().getAllProjectsList().get(originalProjectIndex).copy());
      return true; //The active project has been updated!
    }
    return false; //The project was not found in the system files.
  }




/** <p>This method is executed when the user selects a project from the displayed overview. It then ensures that the proper context buttons are activated </p>
 * @Author: K. Dashnaw
 * */
  public void validateClick_ActivateButtons()
  {
    if(mainTableView.getSelectionModel().getSelectedItem() != null &&
        (mainTableView.getSelectionModel().getSelectedItem() instanceof ResidentialProject ||
            mainTableView.getSelectionModel().getSelectedItem() instanceof CommercialProject ||
              mainTableView.getSelectionModel().getSelectedItem() instanceof IndustrialProject ||
                mainTableView.getSelectionModel().getSelectedItem() instanceof RoadProject))
    {
      editButton.setDisable(false);
      removeButton.setDisable(false);
      detailsButton.setDisable(false);
    }
    else
    {
      editButton.setDisable(true);
      removeButton.setDisable(true);
      detailsButton.setDisable(true);
    }
  }





  /**<p>This method initializes the edit sequence allowing the user to modify existing projects in the system.
   * It initializes a temporary copy of the user selected project that collects changes before replacing the
   * originally selected project with the modified one on user confirmation.</p>
   * @param actionEvent An ActionEvent which provides a reference to the source element that prompted the execution of this method.
   * @Author: K. Dashnaw
   * */
  public void editProjectSelected(ActionEvent actionEvent)
  {
    if(updateSelectedProject())
    {
      //Selected project has been marked as the active project. Open the edit project window!
      try
      {
        this.openWindow(actionEvent);
      }
      catch (IOException error)
      {
        this.getSceneController().setGUI_ConsoleMessage("ERROR: Unable to edit selected project. Reason unknown.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      }
    }
  }




  /**<p>This method initializes the remove project sequence allowing the user to delete existing projects from the system.
   * It asks the user to verify this action before finalizing the deletion. </p>
   * @Author: K. Dashnaw
   * */
  public void removeProjectSelected()
  {
    if(updateSelectedProject())
    {
      //Selected project has been marked as the active project. Confirm the user wishes to delete it!
      if(this.getSceneController().createPromptWindow("Deleting project can not be undone. Are you sure?").equals("confirmationPressed"))
      {
        //Delete project:
        if(this.getActiveModel().removeProject(this.getActiveModel().getSelectedProject()))
        {
          //Update console:
          this.getSceneController().setGUI_ConsoleMessage("Project has been permanently deleted.");
          this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

          //Refresh view:
          refresh();
        }
        else
        {
          this.getSceneController().setGUI_ConsoleMessage("ERROR: Unable to delete selected project. Reason unknown.");
          this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        }
      }
      else
      {
        //DO NOT DELETE PROJECT:
        this.getSceneController().setGUI_ConsoleMessage("ERROR: Unable to delete selected project. Reason unknown.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      }
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("ERROR: Unable to delete selected project. Reason unknown.");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }




  /**<p>This method initializes the "view project details"" sequence allowing the user to view all existing project
   * information in the system, relating to the selected project.</p>
   * @param actionEvent An ActionEvent which provides a reference to the source element that prompted the execution of this method.
   * @Author: K. Dashnaw
   * */
  public void viewProjectDetails(ActionEvent actionEvent)
  {
    if(updateSelectedProject())
    {
      //Selected project has been marked as the active project. Re-direct the user to the "project details" window.!
      try
      {
        this.openWindow(actionEvent);
      }
      catch (IOException error)
      {
        this.getSceneController().setGUI_ConsoleMessage("ERROR: Unable to edit selected project. Reason unknown.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      }
    }
  }




  /**<p>This method applies any filters that the user has set on the "filtering" page. It is used in conjunction with
   * the methods relating to displaying the projects on the overview.</p>
   * @Author: K. Dashnaw
   * */
  public void setFilters()
  {
    //Create the set filters window:
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Projects_FilterView.fxml"));
    try
    {
      Stage newStage = new Stage();
      newStage.initModality(Modality.WINDOW_MODAL);
      newStage.initOwner(this.getSceneController().getActiveStage());

      Scene updateScene = new Scene(fxmlLoader.load(), 800, 250);

      Scene_ControllerInterface controller = fxmlLoader.getController();
      controller.init(this.getActiveModel(), this.getSceneController());

      newStage.setScene(updateScene);
      newStage.setResizable(false);
      newStage.setTitle("Set filters");

      // show the dialog
      newStage.showAndWait();
      refresh();
      this.getSceneController().setGUI_ConsoleMessage("Selected filters have been applied.");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
    catch(IOException error)
    {
      System.out.println("Unable to display refresh pop-up. Something went wrong.");
    }
  }
}
