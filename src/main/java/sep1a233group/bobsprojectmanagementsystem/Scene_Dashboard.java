package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/** <p>This class controls the GUI related view and methods concerning the "Project Dashboard" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.</p>
 * @Author: Z. Banouri
 * */
public class Scene_Dashboard implements Scene_ControllerInterface
{
  private ConstructionProject project1;
  private ConstructionProject project2;
  private ConstructionProject project3;
  private ConstructionProject project4;

  private ProgressReport report1;
  private ProgressReport report2;
  private ProgressReport report3;
  private ProgressReport report4;
  @FXML Text address1, address2, address3, address4;
  @FXML Label projectType1, projectType2, projectType3, projectType4;
  @FXML Label tlfNo1, tlfNo2, tlfNo3, tlfNo4;
  @FXML Label hoursStatus1, hoursStatus2, hoursStatus3, hoursStatus4;
  @FXML GridPane slot1;
  @FXML GridPane slot2;
  @FXML GridPane slot3;
  @FXML GridPane slot4;
  @FXML ProgressBar hoursBar1, hoursBar2, hoursBar3, hoursBar4;
  @FXML Label hoursSpent1, hoursSpent2, hoursSpent3, hoursSpent4;
  @FXML Label expectedHours1, expectedHours2, expectedHours3, expectedHours4;
  @FXML Label budgetStatus1, budgetStatus2, budgetStatus3, budgetStatus4;
  @FXML ProgressBar budgetBar1, budgetBar2, budgetBar3, budgetBar4;
  @FXML Label expenses1, expenses2, expenses3, expenses4;
  @FXML Label budget1, budget2, budget3, budget4;
  @FXML Label timelineStatus1, timelineStatus2, timelineStatus3, timelineStatus4;
  @FXML ProgressBar timelineBar1, timelineBar2, timelineBar3, timelineBar4;
  @FXML Label startDate1, startDate2, startDate3, startDate4;
  @FXML Label expectedDate1, expectedDate2, expectedDate3, expectedDate4;


  @FXML TextField GUI_Console; //textField in the gui, where messages are shown to the user.
  @FXML Label labelLastProjectSave;
  @FXML Label labelHTMLExportDate;

  private MainModel activeModel;
  private SceneController sceneController;






  /**<p>this method resets all the 4 dashboard-projects, and finds the dashboard-projects from the project list again,
   * to get the updates' information. If there is less than 4 projects in dashboard then the remaining slots will be hidden</p>
   * @Author: Z. Banouri
   * */
  public void displayProgressReports()
  {
    project1 = null;
    project2 = null;
    project3 = null;
    project4 = null;

    //a loop to look through all projects and see if they are marked as a dashboard project.
    for (int i = 0; i < activeModel.getAllProjectsList().size(); i++)
    {
      if (activeModel.getAllProjectsList().get(i).isDashboardProject())
      {
        if (project1 == null)
        {
          project1 = activeModel.getAllProjectsList().get(i);
          continue;
        }
        if (project2 == null)
        {
          project2 = activeModel.getAllProjectsList().get(i);
          continue;
        }
        if (project3 == null)
        {
          project3 = activeModel.getAllProjectsList().get(i);
          continue;
        }
        if (project4 == null)
        {
          project4 = activeModel.getAllProjectsList().get(i);
          break;
        }
      }
    }

    //After we find the dashboard-projects, they will be displayed, and the empty slots will be hidden
    if (project1 != null){slot1.setVisible(true); displayReport1();}
    else {slot1.setVisible(false);}
    if (project2 != null){slot2.setVisible(true); displayReport2();}
    else {slot2.setVisible(false);}
    if (project3 != null){slot3.setVisible(true); displayReport3();}
    else {slot3.setVisible(false);}
    if (project4 != null){slot4.setVisible(true); displayReport4();}
    else {slot4.setVisible(false);}
  }






  /**<p>This method generates a progress report for the 1st project in the list marked as dashboard, thereafter relevant values will be displays in the dashboard.</p>
   * @Author: Z. Banouri
   * */
  public void displayReport1()
  {
    report1 = project1.generateProgressReport();
    projectType1.setText(project1.getProjectType());
    address1.setText("#1: " + this.getProject1().getProjectInformation().getProjectName() + ", " + this.getProject1().getProjectAddress().getPostalCode() + " " + this.getProject1().getProjectAddress().getCity());
    tlfNo1.setText(report1.getCustomer().getPhoneNumberPrefix() + " " + report1.getCustomer().getPhoneNumber());
    hoursSpent1.setText(Double.toString(report1.getProjectRessources().getManHoursSpent()));
    expectedHours1.setText(Double.toString(report1.getProjectRessources().getTotalManHoursNeeded()));
      if (report1.getProjectRessources().getManHoursSpent() > report1.getProjectRessources().getTotalManHoursNeeded())
      {
        hoursStatus1.setText("Over expected");
      }
      else
      {
        hoursStatus1.setText("Within expected");
      }
    hoursBar1.setProgress(report1.getProjectRessources().getManHoursSpent()/report1.getProjectRessources().getTotalManHoursNeeded());
    expenses1.setText(Double.toString(report1.getProjectFinances().getMaterialExpences()));
    budget1.setText(Double.toString(report1.getProjectFinances().getTotalBudget()));
      if (report1.getProjectFinances().getMaterialExpences() > report1.getProjectFinances().getTotalBudget())
      {
        budgetStatus1.setText("Over budget");
      }
      else
      {
        budgetStatus1.setText("Within budget");
      }
    budgetBar1.setProgress(report1.getProjectFinances().getMaterialExpences()/report1.getProjectFinances().getTotalBudget());
    startDate1.setText(report1.getProjectStartDate().toString());
    expectedDate1.setText(report1.getProjectEndDate().toString());
      if (report1.getProjectEndDate().isBefore(MyDate.now()))
      {
        timelineStatus1.setText("Behind schedule");
      }
      else
      {
        timelineStatus1.setText("On track");
      }
    timelineBar1.setProgress((double) report1.getProjectStartDate().daysBetween(MyDate.now())/report1.getProjectStartDate().daysBetween(report1.getProjectEndDate()));
  }






  /**<p>This method removes the 1st project from the dashboard, and then refreshes the page to show the new dashboard.</p>
   * @Author: A. Özer
   * */
  public void untrackProject1()
  {
    for (int i = 0; i < activeModel.getAllProjectsList().size(); i++)
    {
      if (project1.equals(activeModel.getAllProjectsList().get(i)))
      {
        activeModel.getAllProjectsList().get(i).setDashboardProject(false);
        break;
      }
    }
    refresh();
  }






  /**<p>This method generates a progress report for the 2nd project in the list marked as dashboard, thereafter relevant values will be displays in the dashboard.</p>
   * @Author: Z. Banouri
   * */
  public void displayReport2()
  {
    report2 = project2.generateProgressReport();
    projectType2.setText(project2.getProjectType());
    address2.setText("#2: " + this.getProject2().getProjectInformation().getProjectName() + ", " + this.getProject2().getProjectAddress().getPostalCode() + " " + this.getProject2().getProjectAddress().getCity());
    tlfNo2.setText(report2.getCustomer().getPhoneNumberPrefix() + " " + report2.getCustomer().getPhoneNumber());
    hoursSpent2.setText(Double.toString(report2.getProjectRessources().getManHoursSpent()));
    expectedHours2.setText(Double.toString(report2.getProjectRessources().getTotalManHoursNeeded()));
      if (report2.getProjectRessources().getManHoursSpent() > report2.getProjectRessources().getTotalManHoursNeeded())
      {
        hoursStatus2.setText("Over expected");
      }
      else
      {
        hoursStatus2.setText("Within expected");
      }
    hoursBar2.setProgress(report2.getProjectRessources().getManHoursSpent()/report2.getProjectRessources().getTotalManHoursNeeded());
    expenses2.setText(Double.toString(report2.getProjectFinances().getMaterialExpences()));
    budget2.setText(Double.toString(report2.getProjectFinances().getTotalBudget()));
      if (report2.getProjectFinances().getMaterialExpences() > report2.getProjectFinances().getTotalBudget())
      {
        budgetStatus2.setText("Over budget");
      }
      else
      {
        budgetStatus2.setText("Within budget");
      }
    budgetBar2.setProgress(report2.getProjectFinances().getMaterialExpences()/report2.getProjectFinances().getTotalBudget());
    startDate2.setText(report2.getProjectStartDate().toString());
    expectedDate2.setText(report2.getProjectEndDate().toString());
      if (report2.getProjectEndDate().isBefore(MyDate.now()))
      {
        timelineStatus2.setText("Behind schedule");
      }
      else
      {
        timelineStatus2.setText("On track");
      }
  }






  /**<p>This method removes the 2nd project from the dashboard, and then refreshes the page to show the new dashboard.</p>
   * @Author: Z. Banouri
   * */
  public void untrackProject2()
  {
    for (int i = 0; i < activeModel.getAllProjectsList().size(); i++)
    {
      if (project2.equals(activeModel.getAllProjectsList().get(i)))
      {
        activeModel.getAllProjectsList().get(i).setDashboardProject(false);
        break;
      }
    }
    refresh();
  }






  /**<p>This method generates a progress report for the 3rd project in the list marked as dashboard, thereafter relevant values will be displays in the dashboard.</p>
   * @Author: Z. Banouri
   * */
  public void displayReport3()
  {
    report3 = project3.generateProgressReport();
    projectType3.setText(project3.getProjectType());
    address3.setText("#3: " + this.getProject3().getProjectInformation().getProjectName() + ", " + this.getProject3().getProjectAddress().getPostalCode() + " " + this.getProject3().getProjectAddress().getCity());
    tlfNo3.setText(report3.getCustomer().getPhoneNumberPrefix() + " " + report3.getCustomer().getPhoneNumber());
    hoursSpent3.setText(Double.toString(report3.getProjectRessources().getManHoursSpent()));
    expectedHours3.setText(Double.toString(report3.getProjectRessources().getTotalManHoursNeeded()));
      if (report3.getProjectRessources().getManHoursSpent() > report3.getProjectRessources().getTotalManHoursNeeded())
      {
      hoursStatus3.setText("Over expected");
      }
      else
      {
        hoursStatus3.setText("Within expected");
      }
    hoursBar3.setProgress(report3.getProjectRessources().getManHoursSpent()/report3.getProjectRessources().getTotalManHoursNeeded());
    expenses3.setText(Double.toString(report3.getProjectFinances().getMaterialExpences()));
    budget3.setText(Double.toString(report3.getProjectFinances().getTotalBudget()));
      if (report3.getProjectFinances().getMaterialExpences() > report3.getProjectFinances().getTotalBudget())
      {
        budgetStatus3.setText("Over budget");
      }
      else
      {
        budgetStatus3.setText("Within budget");
      }
    budgetBar3.setProgress(report3.getProjectFinances().getMaterialExpences()/report3.getProjectFinances().getTotalBudget());
    startDate3.setText(report3.getProjectStartDate().toString());
    expectedDate3.setText(report3.getProjectEndDate().toString());
      if (report3.getProjectEndDate().isBefore(MyDate.now()))
      {
        timelineStatus3.setText("Behind schedule");
      }
      else
      {
        timelineStatus3.setText("On track");
      }
  }






  /**<p>This method removes the 3rd project from the dashboard, and then refreshes the page to show the new dashboard.</p>
   * @Author: A. Özer
   * */
  public void untrackProject3()
  {
    for (int i = 0; i < activeModel.getAllProjectsList().size(); i++)
    {
      if (project3.equals(activeModel.getAllProjectsList().get(i)))
      {
        activeModel.getAllProjectsList().get(i).setDashboardProject(false);
        break;
      }
    }
    refresh();
  }






  /**<p>This method generates a progress report for the 4th project in the list marked as dashboard, thereafter relevant values will be displays in the dashboard.</p>
   * @Author: A. Özer
   * */
  public void displayReport4()
  {
    report4 = project4.generateProgressReport();
    projectType4.setText(project4.getProjectType());
    address4.setText("#4: " + this.getProject4().getProjectInformation().getProjectName() + ", " + this.getProject4().getProjectAddress().getPostalCode() + " " + this.getProject4().getProjectAddress().getCity());
    tlfNo4.setText(report4.getCustomer().getPhoneNumberPrefix() + " " + report4.getCustomer().getPhoneNumber());
    hoursSpent4.setText(Double.toString(report4.getProjectRessources().getManHoursSpent()));
    expectedHours4.setText(Double.toString(report4.getProjectRessources().getTotalManHoursNeeded()));
      if (report4.getProjectRessources().getManHoursSpent() > report4.getProjectRessources().getTotalManHoursNeeded())
      {
        hoursStatus4.setText("Over expected");
      }
      else
      {
        hoursStatus4.setText("Within expected");
      }
    hoursBar4.setProgress(report4.getProjectRessources().getManHoursSpent()/report4.getProjectRessources().getTotalManHoursNeeded());
    expenses4.setText(Double.toString(report4.getProjectFinances().getMaterialExpences()));
    budget4.setText(Double.toString(report4.getProjectFinances().getTotalBudget()));
      if (report4.getProjectFinances().getMaterialExpences() > report4.getProjectFinances().getTotalBudget())
      {
        budgetStatus4.setText("Over budget");
      }
      else
      {
        budgetStatus4.setText("Within budget");
      }
    budgetBar4.setProgress(report4.getProjectFinances().getMaterialExpences()/report4.getProjectFinances().getTotalBudget());
    startDate4.setText(report4.getProjectStartDate().toString());
    expectedDate4.setText(report4.getProjectEndDate().toString());
      if (report4.getProjectEndDate().isBefore(MyDate.now()))
      {
        timelineStatus4.setText("Behind schedule");
      }
      else
      {
        timelineStatus4.setText("On track");
      }
  }






  /**<p>This method removes the 4th project from the dashboard, and then refreshes the page to show the new dashboard.</p>
   * @Author: A. Özer
   * */
  public void untrackProject4()
  {
    for (int i = 0; i < activeModel.getAllProjectsList().size(); i++)
    {
      if (project1.equals(activeModel.getAllProjectsList().get(i)))
      {
        activeModel.getAllProjectsList().get(i).setDashboardProject(false);
        break;
      }
    }
    refresh();
  }





  /** <p>Gets the active project model.</p>
   * @return a MainModel object reference.
   * @Author: K. Dashnaw
   * */
  public MainModel getActiveModel()
  {
    return activeModel;
  }





  /** <p>Gets the project assigned to dashboard slot #1.</p>
   * @return The ConstructionProject assigned to dashboard slot #1.
   * @Author: K. Dashnaw
   * */
  public ConstructionProject getProject1()
  {
    return project1;
  }





  /** <p>Gets the project assigned to dashboard slot #2.</p>
   * @return The ConstructionProject assigned to dashboard slot #2.
   * @Author: K. Dashnaw
   * */
  public ConstructionProject getProject2()
  {
    return project2;
  }





  /** <p>Gets the project assigned to dashboard slot #3.</p>
   * @return The ConstructionProject assigned to dashboard slot #3.
   * @Author: K. Dashnaw
   * */
  public ConstructionProject getProject3()
  {
    return project3;
  }





  /** <p>Gets the project assigned to dashboard slot #4.</p>
   * @return The ConstructionProject assigned to dashboard slot #4.
   * @Author: K. Dashnaw
   * */
  public ConstructionProject getProject4()
  {
    return project4;
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






  /** <p>This method initiates the scene/stage it is called on and ties it to the mapping done in the SceneController,
   * thus allowing the overall SceneController to know about this active stage/scene.<br>It is only run on the first initialization.</p>
   * @param activeModel a MainModel Object reference attached to each scene. It allows the scene to call methods from the model to perform operations.
   * @param sceneController a reference to the overall responsible SceneController, which ties all the sub-scenes/stages together.
   * */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    this.activeModel = activeModel;
    setSceneController(sceneController);
    this.setGUI_Console(this.GUI_Console);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    refresh();

    System.out.println("Project Dashboard Scene is now active");
  }






  /**<p>This method is called every time this scene/stage becomes active. It is used to refresh onscreen data. </p>*/
  @Override public void refresh()
  {
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

    this.getActiveModel().refreshDashboardProjects();

    displayProgressReports();

    System.out.println("Dashboard now the active stage.");
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






  /**<p>This method provides update functionality to the 1st project in the dashboard menu,
   * which allows the user to quickly update values relating to the HumanRessources and Finances information of the displayed project.</p>
   * @Author: K. Dashnaw
   * */
  public void updateProject1()
  {
    if(this.getProject1() != null)
    {
      this.quickUpdate_Project(1);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }






  /**<p>This method provides update functionality to the 2nd project in the dashboard menu,
   * which allows the user to quickly update values relating to the HumanRessources and Finances information of the displayed project.</p>
   * @Author: K. Dashnaw
   * */
  public void updateProject2()
  {

    if(this.getProject2() != null)
    {
      this.quickUpdate_Project(2);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }






  /**<p>This method provides update functionality to the 3rd project in the dashboard menu,
   * which allows the user to quickly update values relating to the HumanRessources and Finances information of the displayed project.</p>
   * @Author: K. Dashnaw
   * */
  public void updateProject3()
  {
    if(this.getProject3() != null)
    {
      this.quickUpdate_Project(3);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }






  /**<p>This method provides update functionality to the 4th project in the dashboard menu,
   * which allows the user to quickly update values relating to the HumanRessources and Finances information of the displayed project.</p>
   * @Author: K. Dashnaw
   * */
  public void updateProject4()
  {
    if(this.getProject4() != null)
    {
      this.quickUpdate_Project(4);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }





  /**<p>This method is connected to the 4 updateProject methods described above. It ensures that the proper project is passed on to the "update" window,
   * and also ensures that a popup-window actually appears in which the user may quickly update the displayed data.</p>
   * @param reportNumber An integer between 1-4 that corresponds with the currently active projects 1-4, that the user wishes to update.
   * @Author: K. Dashnaw
   * */
  public void quickUpdate_Project(int reportNumber)
  {
    try
    {
      //Find index position of the selected progress report:
      for (int i = 0; i < this.getActiveModel().getAllProjectsList().size(); i++)
      {
        if(reportNumber == 1 && this.getProject1().equals(this.getActiveModel().getAllProjectsList().get(i)))
        {
          this.getActiveModel().setProjectIndexPosition(i);
        }
        else if(reportNumber == 2 && this.getProject2().equals(this.getActiveModel().getAllProjectsList().get(i)))
        {
          this.getActiveModel().setProjectIndexPosition(i);
        }
        else if(reportNumber == 3 && this.getProject3().equals(this.getActiveModel().getAllProjectsList().get(i)))
        {
          this.getActiveModel().setProjectIndexPosition(i);
        }
        else if(reportNumber == 4 && this.getProject4().equals(this.getActiveModel().getAllProjectsList().get(i)))
        {
          this.getActiveModel().setProjectIndexPosition(i);
        }
      }

      String projectType = this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).getProjectType();

      //Set active project. Make sure we set COPIES, or else unintended changes might be saved directly to the real project before the user presses the save button!
      if(projectType.equalsIgnoreCase("residential"))
      {
        this.getActiveModel().setSelectedProject((ResidentialProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
      }
      else if(projectType.equalsIgnoreCase("commercial"))
      {
        this.getActiveModel().setSelectedProject((CommercialProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
      }
      else if(projectType.equalsIgnoreCase("industrial"))
      {
        this.getActiveModel().setSelectedProject((IndustrialProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
      }
      else if(projectType.equalsIgnoreCase("road"))
      {
        this.getActiveModel().setSelectedProject((RoadProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
      }


      //Create the update window:
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Projects_QuickUpdateProjectView.fxml"));
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
        newStage.setTitle("Update project");

        // show the dialog
        newStage.showAndWait();
        refresh();
      }
      catch(IOException error)
      {
        System.out.println("Unable to display update pop-up. Something went wrong.");
      }
    }
    catch(NullPointerException error)
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! " + error);
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }





  /**<p>This method provides the functionality needed to allow the user to quickly view all available information on the project tied to the activated button.</p>
   * @param reportNumber An integer between 1-4 that corresponds with the currently active projects 1-4, that the user wishes to view.
   * @param actionEvent An ActionEvent which provides a reference to the source element that prompted the execution of this method.
   * @Author: K. Dashnaw
   * */
  public void viewProjectDetails(int reportNumber, ActionEvent actionEvent)
  {
    //Find index position of the selected progress report:
    for (int i = 0; i < this.getActiveModel().getAllProjectsList().size(); i++)
    {
      if(reportNumber == 1 && this.getProject1().equals(this.getActiveModel().getAllProjectsList().get(i)))
      {
        this.getActiveModel().setProjectIndexPosition(i);
      }
      else if(reportNumber == 2 && this.getProject2().equals(this.getActiveModel().getAllProjectsList().get(i)))
      {
        this.getActiveModel().setProjectIndexPosition(i);
      }
      else if(reportNumber == 3 && this.getProject3().equals(this.getActiveModel().getAllProjectsList().get(i)))
      {
        this.getActiveModel().setProjectIndexPosition(i);
      }
      else if(reportNumber == 4 && this.getProject4().equals(this.getActiveModel().getAllProjectsList().get(i)))
      {
        this.getActiveModel().setProjectIndexPosition(i);
      }
    }

    String projectType = this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).getProjectType();

    //Set active project. Make sure we set COPIES, or else unintended changes might be saved directly to the real project before the user presses the save button!
    if(projectType.equalsIgnoreCase("residential"))
    {
      this.getActiveModel().setSelectedProject((ResidentialProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
    }
    else if(projectType.equalsIgnoreCase("commercial"))
    {
      this.getActiveModel().setSelectedProject((CommercialProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
    }
    else if(projectType.equalsIgnoreCase("industrial"))
    {
      this.getActiveModel().setSelectedProject((IndustrialProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
    }
    else if(projectType.equalsIgnoreCase("road"))
    {
      this.getActiveModel().setSelectedProject((RoadProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
    }
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






  /** <p>This method is tied together with the "viewProjectDetails" method above.
   * It ensures that the proper project information is displayed to the user, based on which button the user chose to activate.</p>
   * @param actionEvent An ActionEvent which provides a reference to the source element that prompted the execution of this method.
   * @Author: K. Dashnaw
   * */
  public void viewProject1(ActionEvent actionEvent)
  {
    if(this.getProject1() != null)
    {
      this.viewProjectDetails(1, actionEvent);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }






  /** <p>This method is tied together with the "viewProjectDetails" method above.
   * It ensures that the proper project information is displayed to the user, based on which button the user chose to activate.</p>
   * @param actionEvent An ActionEvent which provides a reference to the source element that prompted the execution of this method.
   * @Author: K. Dashnaw
   * */
  public void viewProject2(ActionEvent actionEvent)
  {

    if(this.getProject2() != null)
    {
      this.viewProjectDetails(2, actionEvent);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }






  /** <p>This method is tied together with the "viewProjectDetails" method above.
   * It ensures that the proper project information is displayed to the user, based on which button the user chose to activate.</p>
   * @param actionEvent An ActionEvent which provides a reference to the source element that prompted the execution of this method.
   * @Author: K. Dashnaw
   * */
  public void viewProject3(ActionEvent actionEvent)
  {
    if(this.getProject3() != null)
    {
      this.viewProjectDetails(3, actionEvent);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }






  /** <p>This method is tied together with the "viewProjectDetails" method above.
   * It ensures that the proper project information is displayed to the user, based on which button the user chose to activate.</p>
   * @param actionEvent An ActionEvent which provides a reference to the source element that prompted the execution of this method.
   * @Author: K. Dashnaw
   * */
  public void viewProject4(ActionEvent actionEvent)
  {
    if(this.getProject4() != null)
    {
      this.viewProjectDetails(4, actionEvent);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }

}

