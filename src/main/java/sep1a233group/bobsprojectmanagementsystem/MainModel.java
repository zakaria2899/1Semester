package sep1a233group.bobsprojectmanagementsystem;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;






/** <p>This is the main controller for the project management system. This controls most of the coding logic and method functionality that is called from the GUI<br><br></p>
 * <p><b>Author:</b> Combined effort from all team members.</p>
 * */
public class MainModel
{
  private DashboardProgressReports dashboardProgressReports; //An object containing all the project progress reports that are displayed on the GUI Dashboard.
  private ArrayList<ConstructionProject> allProjectsList; //Contains an ArrayList with ALL the construction projects in the system.
  private ArrayList<ConstructionProject> filteredProjectsList; //Contains a filtered list of projects in accordance to set filters.
  private FileIO fileManager; //Is the main file managing class, which handles file in and out operations.
  private DefaultResidentialSettings defaultResidentialSettings; //Handles the default residential project settings used when creating new projects!
  private DefaultCommercialSettings defaultCommercialSettings; //Handles the default commercial project settings used when creating new projects!
  private DefaultIndustrialSettings defaultIndustrialSettings; //Handles the default industrial project settings used when creating new projects!
  private DefaultRoadSettings defaultRoadSettings; //Handles the default road project settings used when creating new projects!
  private ConstructionProject selectedProject; //Container used to contain currently selected/active project information.
  private String initializationErrorMessage; //Created so the sceneController on initialization can know if there were any errors while loading the system files.
  private int projectIndexPosition; //Holds a reference to the index of the original project, while in the process of modifying existing project copies (before the original is replaced upon user save).
  private Object[] filterSettings; //Holds the active filtering settings used when displaying projects in a filtered manner.






  /** <p>Constructs the MainModel.
   * Also loads any available project details from system file.</p>
   * @Author: Z. Banouri
   */
  public MainModel()
  {
    //Initialize the file manager.
    setFileManager();

    //Load any system files that might be present:
    if(load())
    {
      //loading was successful
      System.out.println("Debug: Data successfully loaded and validated.");
      setInitializationErrorMessage(""); //Must be initialized!
    }
    else
    {
      //loading failed. The load method re-initialized a clean system file. Any prior data is lost.
      System.out.println("Debug: Data was not loaded successfully. New data has been initialized instead.");
      setInitializationErrorMessage("Data was not loaded successfully. New data has been initialized instead.");
    }
    setFilteredProjectsList(this.getAllProjectsList());
    refreshDashboardProjects();
  }






  /** <p>Gets the DashboardProgressReports object, which holds all the projects that are displayed on the Dashboard, at any given time.</p>
   * @return A DashboardProgressReports object with the marked Dashboard projects that the user wants shown on the GUI Dashboard.
   * @Author: K. Dashnaw
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public DashboardProgressReports getDashboardProgressReports()
  {
    return dashboardProgressReports;
  }






  /** <p>Sets the DashboardProgressReports object, which holds all the projects that are displayed on the Dashboard, at any given time.</p>
   * @param progressReports A DashboardProgressReports object with the marked Dashboard projects that the user wants shown on the GUI Dashboard.
   * @Author: K. Dashnaw
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void setDashboardProgressReport(DashboardProgressReports progressReports)
  {
    this.dashboardProgressReports = progressReports;
  }






  /** <p>Returns any potentially set error messages (or other messages). This is especially used during model initialization by the SceneController,
   * since the SceneController does not know what errors this models internal operations cast.<br>
   * It is used extensively for the integration of the console in the application. </p>
   * @return A String containing any messages (Error or otherwise) passed by any of the methods in the MainModel class.
   * @Author: K. Dashnaw
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public String getInitializationErrorMessage()
  {
    return initializationErrorMessage;
  }






  /** <p>Sets any potentially set error messages (or other messages). This is especially used during model initialization by the SceneController,
   * since the SceneController does not know what errors this models internal operations cast.<br>
   * It is used extensively for the integration of the console in the application. </p>
   * @param initializationErrorMessage  A String containing any messages (Error or otherwise) passed by any of the methods in the MainModel class.
   * @Author: K. Dashnaw
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void setInitializationErrorMessage(String initializationErrorMessage)
  {
    this.initializationErrorMessage = initializationErrorMessage;
  }






  /** <p>Gets the main system master project list which holds all the projects currently in the system files.</p>
   * @return An ArrayList of ConstructionProjects, containing all the projects in the system at any point in time.
   * @Author: K. Dashnaw
   */
  public ArrayList<ConstructionProject> getAllProjectsList()
  {
    return allProjectsList;
  }






  /** <p>Sets the main system master project list which holds all the projects currently in the system files.
   * @param allProjectsList An ArrayList of ConstructionProjects, containing all the projects in the system at any point in time.</p>
   * @Author: K. Dashnaw
   */
  public void setAllProjectsList(ArrayList<ConstructionProject> allProjectsList)
  {
    this.allProjectsList = allProjectsList;
  }





  /** <p>Gets a filtered version of the main project files list. This list is used to hold a copy of the master list,
   * with projects that don't fit within any applied filters removed.</p>
   * @return An ArrayList of ConstructionProjects.
   * @Author: K. Dashnaw
   */
  public ArrayList<ConstructionProject> getFilteredProjectsList()
  {
    return filteredProjectsList;
  }





  /** <p>Sets a filtered version of the main project files list. This list is used to hold a copy of the master list,
   * with projects that don't fit within any applied filters removed.</p>
   * @param filteredProjectsList An ArrayList of ConstructionProjects.
   * @Author: K. Dashnaw
   */
  public void setFilteredProjectsList(ArrayList<ConstructionProject> filteredProjectsList)
  {
    this.filteredProjectsList = filteredProjectsList;
  }





  /** <p>Gets the project that is currently is being worked with, be it while editing, creating, update, removing or otherwise.<br>
   * This is used to hold a copy of the original project while manipulating - before replacing the original upon validation and confirmation.</p>
   * @return A ConstructionProject Object.
   * @Author: K. Dashnaw
   */
  public ConstructionProject getSelectedProject()
  {
    return selectedProject;
  }






  /** <p>Sets the project that is currently is being worked with, be it while editing, creating, update, removing or otherwise.<br>
   * This is used to hold a copy of the original project while manipulating - before replacing the original upon validation and confirmation.</p>
   * @param selectedProject A ResidentialProject Object.
   * @Author: K. Dashnaw
   */
  public void setSelectedProject(ResidentialProject selectedProject)
  {
    this.selectedProject = selectedProject;
  }






  /** <p>Sets the project that is currently is being worked with, be it while editing, creating, update, removing or otherwise.<br>
   * This is used to hold a copy of the original project while manipulating - before replacing the original upon validation and confirmation.</p>
   * @param selectedProject A CommercialProject Object.
   * @Author: K. Dashnaw
   */
  public void setSelectedProject(CommercialProject selectedProject)
  {
    this.selectedProject = selectedProject;
  }






  /** <p>Sets the project that is currently is being worked with, be it while editing, creating, update, removing or otherwise.<br>
   * This is used to hold a copy of the original project while manipulating - before replacing the original upon validation and confirmation.</p>
   * @param selectedProject A IndustrialProject Object.
   * @Author: K. Dashnaw
   */
  public void setSelectedProject(IndustrialProject selectedProject)
  {
    this.selectedProject = selectedProject;
  }






  /** <p>Sets the project that is currently is being worked with, be it while editing, creating, update, removing or otherwise.<br>
   * This is used to hold a copy of the original project while manipulating - before replacing the original upon validation and confirmation.</p>
   * @param selectedProject A RoadProject Object.
   * @Author: K. Dashnaw
   */
  public void setSelectedProject(RoadProject selectedProject)
  {
    this.selectedProject = selectedProject;
  }






  /** <p>Gets the file manager responsible for maintaining data persistence across sessions</p>
   * @return a FileIO object
   * @Author: K. Dashnaw
   */
  public FileIO getFileManager()
  {
    return fileManager;
  }






  /** <p>Sets the file manager responsible for maintaining data persistence across sessions.<br>
   * No arguments are accepted, as this just has a default initialization.</p>
   * @Author: K. Dashnaw
   */
  public void setFileManager()
  {
    this.fileManager = new FileIO();
  }






  /** <p>Gets the default residential project settings used when creating new projects</p>
   * @return A DefaultResidentialSettings Object containing the corresponding default settings.
   * Author: K. Dashnaw
   */
  public DefaultResidentialSettings getDefaultResidentialSettings()
  {
    return defaultResidentialSettings;
  }






  /** <p>Sets the default residential project settings used when creating new projects</p>
   * @param defaultResidentialSettings A DefaultResidentialSettings Object containing the corresponding default settings.
   * Author: K. Dashnaw
   */
  public void setDefaultResidentialSettings(
      DefaultResidentialSettings defaultResidentialSettings)
  {
    this.defaultResidentialSettings = defaultResidentialSettings;
  }






  /** <p>Gets the default commercial project settings used when creating new projects</p>
   * @return A DefaultCommercialSettings Object containing the corresponding default settings.
   * Author: K. Dashnaw
   */
  public DefaultCommercialSettings getDefaultCommercialSettings()
  {
    return defaultCommercialSettings;
  }






  /** <p>Sets the default commercial project settings used when creating new projects</p>
   * @param defaultCommercialSettings A DefaultCommercialSettings Object containing the corresponding default settings.
   * Author: K. Dashnaw
   */
  public void setDefaultCommercialSettings(
      DefaultCommercialSettings defaultCommercialSettings)
  {
    this.defaultCommercialSettings = defaultCommercialSettings;
  }






  /** <p>Gets the default industrial project settings used when creating new projects</p>
   * @return A DefaultIndustrialSettings Object containing the corresponding default settings.
   * Author: K. Dashnaw
   */
  public DefaultIndustrialSettings getDefaultIndustrialSettings()
  {
    return defaultIndustrialSettings;
  }






  /** <p>Sets the default industrial project settings used when creating new projects</p>
   * @param defaultIndustrialSettings A DefaultIndustrialSettings Object containing the corresponding default settings.
   * Author: K. Dashnaw
   */
  public void setDefaultIndustrialSettings(
      DefaultIndustrialSettings defaultIndustrialSettings)
  {
    this.defaultIndustrialSettings = defaultIndustrialSettings;
  }






  /** <p>Gets the default road project settings used when creating new projects</p>
   * @return A DefaultRoadSettings Object containing the corresponding default settings.
   * Author: K. Dashnaw
   */
  public DefaultRoadSettings getDefaultRoadSettings()
  {
    return defaultRoadSettings;
  }






  /** <p>Sets the default road project settings used when creating new projects</p>
   * @param defaultRoadSettings A DefaultRoadSettings Object containing the corresponding default settings.
   * Author: K. Dashnaw
   */
  public void setDefaultRoadSettings(DefaultRoadSettings defaultRoadSettings)
  {
    this.defaultRoadSettings = defaultRoadSettings;
  }






  /** <p>This method is used to GET the value of the projectIndexPosition attribute.<br>
   * This attribute is especially used while modifying projects in the master list.<br>
   * When a copy of the master list project is created, this attribute is set so that the system knows which position
   * the master copy was taken from, when later replacing the original - if the copy passed both validation and user confirmation.
   * Returns a reference to the index position in the master project list from which a copy of the selected project is currently being modified.</p>
   * @return An integer containing the index position value.
   * @Author: K. Dashnaw
   * */
  public int getProjectIndexPosition()
  {
    return projectIndexPosition;
  }






  /** <p>This method is used to SET the value of the projectIndexPosition attribute.<br>
   * This attribute is especially used while modifying projects in the master list.<br>
   * When a copy of the master list project is created, this attribute is set so that the system knows which position
   * the master copy was taken from, when later replacing the original - if the copy passed both validation and user confirmation.
   * Returns a reference to the index position in the master project list from which a copy of the selected project is currently being modified.</p>
   * @param projectIndexPosition An integer containing the index position value.
   * @Author: K. Dashnaw
   * */
  public void setProjectIndexPosition(int projectIndexPosition)
  {
    this.projectIndexPosition = projectIndexPosition;
  }





/**<p> This method is used to get the Object[] which holds the set filtering settings during runtime.<br>
 *  These are not saved between sessions, but are kept in memory while the application is launched.</p>
 * @return An Object[] with the following data: {}"String minBudget", "String maxBudget", "String minDuration", "String maxDuration", "String ownerPhoneNumber", "Boolean hideFinished", "Boolean hideOngoing", "Boolean hideResidential", "Boolean hideCommercial", "Boolean hideIndustrial", "Boolean hideRoad"}
 * @Aythor: K. Dashnaw
 * */
  public Object[] getFilterSettings()
  {
    return filterSettings;
  }





  /**<p> This method is used to set the Object[] which holds the set filtering settings during runtime.<br>
   *  These are not saved between sessions, but are kept in memory while the application is launched.</p>
   * @param minBudget a String containing a number representing the minimum budget size filter
   * @param maxBudget a String containing a number representing the maximum budget size filter
   * @param minDuration a String containing a number representing the minimum project duration in months, size filter
   * @param maxDuration a String containing a number representing the minimum project duration in months, size filter
   * @param ownerPhoneNumber a String containing a number representing the phone number filter
   * @param hideFinished a Boolean set to TRUE if completed projects should be hidden from project view, else FALSE.
   * @param hideOngoing a Boolean set to TRUE if ongoing projects should be hidden from project view, else FALSE.
   * @param hideResidential a Boolean set to TRUE if Residential projects should be hidden from project view, else FALSE.
   * @param hideCommercial a Boolean set to TRUE if Commercial projects should be hidden from project view, else FALSE.
   * @param hideIndustrial a Boolean set to TRUE if Industrial projects should be hidden from project view, else FALSE.
   * @param hideRoad a Boolean set to TRUE if Road projects should be hidden from project view, else FALSE.
   * @Aythor: K. Dashnaw
   * */
  public void setFilterSettings(String minBudget,String maxBudget,String minDuration, String maxDuration, String ownerPhoneNumber, Boolean hideFinished, Boolean hideOngoing, Boolean hideResidential, Boolean hideCommercial, Boolean hideIndustrial, Boolean hideRoad)
  {
    this.filterSettings = new Object[] {minBudget, maxBudget, minDuration, maxDuration, ownerPhoneNumber, hideFinished, hideOngoing, hideResidential, hideCommercial, hideIndustrial, hideRoad};
  }






  /** <p>Adds a single construction project to the project management system.</p>
   * @return a Boolean that is TRUE if the project was successfully added to the master list - else false.
   * @Author: K. Dashnaw
   */
  public boolean addProject(ConstructionProject project)
  {
    setInitializationErrorMessage("");
    //Check if an identical project already exists in the system files:
    for (int i = 0; i < getAllProjectsList().size(); i++)
    {
      if(project.equals(getAllProjectsList().get(i)))
      {
        //Identical project identified. Do NOT add project.
        //Let user know why.
        setInitializationErrorMessage("ERROR: Duplicate project already exists in system. Unable to add this project.");
        return false;
      }
    }
    //Adds the project to the project list!
    if(this.getAllProjectsList().add(project))
    {
      //Project was added. Save system.
      this.save();
      return true;
    }
    else
    {
      //Project was NOT added.
      return false;
    }
  }






  /** <p>Initializes a new default project of the given type with default values inserted. <br>Available types are:<br>
   * 'ResidentialProjectType',<br> 'CommercialProjectType',<br> 'IndustrialProjectType' or<br> 'RoadProjectType'</p>
   * @param projectType A String containing the type of which to create a new project. Use the Strings presented above.
   * @Author: K. Dashnaw
   */
  public void newActiveProject(String projectType)
  {
    //Create a new project of the selected type.
    switch (projectType)
    {
      case "ResidentialProjectType":
        ResidentialProject newResProject = new ResidentialProject();
        //Set the default values:
        newResProject.setProjectType("Residential");
        newResProject.setProjectDuration(getDefaultResidentialSettings().getProjectDuration());
        newResProject.setNumberOfKitchens(getDefaultResidentialSettings().getNumberOfKitchens());
        newResProject.setNumberOfBathrooms(getDefaultResidentialSettings().getNumberOfBathrooms());
        newResProject.setNumberOfOtherRoomsWithPlumbing(getDefaultResidentialSettings().getNumberOfOtherRoomsWithPlumbing());
        newResProject.setIsRenovation(getDefaultResidentialSettings().isRenovation());
        this.setSelectedProject(newResProject);
        break;
      case "CommercialProjectType":
        CommercialProject newComProject = new CommercialProject();
        //Set the default values:
        newComProject.setProjectType("Commercial");
        newComProject.setNumberOfFloors(getDefaultCommercialSettings().getNumberOfFloors());
        newComProject.setProjectDuration(getDefaultCommercialSettings().getProjectDuration());
        this.setSelectedProject(newComProject);
        break;
      case "IndustrialProjectType":
        IndustrialProject newIndProject = new IndustrialProject();
        //Set the default values:
        newIndProject.setProjectType("Industrial");
        newIndProject.setProjectDuration(getDefaultIndustrialSettings().getProjectDuration());
        this.setSelectedProject(newIndProject);
        break;
      case "RoadProjectType":
        RoadProject newRoadProject = new RoadProject();
        //Set the default values:
        newRoadProject.setProjectType("Road");
        newRoadProject.setProjectDuration(getDefaultResidentialSettings().getProjectDuration());
        newRoadProject.setProjectDuration(getDefaultRoadSettings().getProjectDuration());
        newRoadProject.setEnvironmentalOrGeographicalChallenges(getDefaultRoadSettings().getEnviromentalOrGeographicalChallenges());
        newRoadProject.setBridgeOrTunnelDetails(getDefaultRoadSettings().getBridgesOrTunnelDetails());
        this.setSelectedProject(newRoadProject);
        break;
    }
  }





  /** <p>Method is called from the 'Create Project' GUI.
   * It ensures that the proper default project specific data are loaded into proper .fxml elements in the GUI.
   * Parameters are references to the specific JavaFX elements the default data should be loaded into.</p>
   * @param bathroomGUIID A TextField used on a .fxml scene to show the number of bathrooms for residential projects.
   * @param kitchenGUIID A TextField used on a .fxml scene to show the number of kitchens for residential projects.
   * @param otherPlumbingGUIID A TextField used on a .fxml scene to show the number of 'other rooms with plumbing' for residential projects.
   * @param durationGUIID A TextField used on a .fxml scene to show the project duration in months.
   * @param buildingSizeGUIID A TextField used on a .fxml scene to show the size of the building for residential projects.
   * @Author: K. Dashnaw
   */
  public void initializeCreateProjectGUI(TextField bathroomGUIID, TextField kitchenGUIID, TextField otherPlumbingGUIID, TextField durationGUIID, TextField buildingSizeGUIID)
  {
    ResidentialProject currentProject = (ResidentialProject) this.getSelectedProject();

    bathroomGUIID.setText("" + currentProject.getNumberOfBathrooms());
    kitchenGUIID.setText("" + currentProject.getNumberOfKitchens());
    otherPlumbingGUIID.setText("" + currentProject.getNumberOfOtherRoomsWithPlumbing());
    durationGUIID.setText("" + currentProject.getProjectDuration());
    buildingSizeGUIID.setText("" + currentProject.getBuildingSize());
  }






  /** <p>Method is called from the 'Create Project' GUI.
   * It ensures that the proper default project specific data are loaded into proper .fxml elements in the GUI.
   * Parameters are references to the specific JavaFX elements the default data should be loaded into.</p>
   * @param floorsGUIID A TextField used on a .fxml scene to show the number of floors for commercial projects.
   * @param durationGUIID A TextField used on a .fxml scene to show the project duration in months.
   * @param buildingSizeGUIID A TextField used on a .fxml scene to show the size of the building for commercial projects.
   * @param intendedUseGUIID A TextArea used to show the String data relating to the intended use of the commercial building.
   * @Author: K. Dashnaw
   */
  public void initializeCreateProjectGUI(TextField floorsGUIID, TextField durationGUIID, TextField buildingSizeGUIID, TextArea intendedUseGUIID)
  {
    CommercialProject currentProject = (CommercialProject) this.getSelectedProject();

    floorsGUIID.setText("" + currentProject.getNumberOfFloors());
    durationGUIID.setText("" + currentProject.getProjectDuration());
    buildingSizeGUIID.setText("" + currentProject.getBuildingSize());
    intendedUseGUIID.setText(currentProject.getIntendedBuildingUse());
  }






  /** <p>Method is called from the 'Create Project' GUI.
   * It ensures that the proper default project specific data are loaded into proper .fxml elements in the GUI.
   * Parameters are references to the specific JavaFX elements the default data should be loaded into.</p>
   * @param durationGUIID A TextField used on a .fxml scene to show the project duration in months.
   * @param buildingSizeGUIID A TextField used on a .fxml scene to show the size of the building for industrial projects.
   * @param intendedUseGUIID A TextArea used to show the String data relating to the intended use of the industrial building.
   * @Author: K. Dashnaw
   */
  public void initializeCreateProjectGUI(TextField durationGUIID, TextField buildingSizeGUIID, TextArea intendedUseGUIID)
  {
    IndustrialProject currentProject = (IndustrialProject) this.getSelectedProject();

    durationGUIID.setText("" + currentProject.getProjectDuration());
    buildingSizeGUIID.setText("" + currentProject.getFacilitySize());
    intendedUseGUIID.setText(currentProject.getFacilityType());
  }






  /** <p>Method is called from the 'Create Project' GUI.
   * It ensures that the proper default project specific data are loaded into proper .fxml elements in the GUI.
   * Parameters are references to the specific JavaFX elements the default data should be loaded into.</p>
   * @param lengthGUIID A TextField used on a .fxml scene to show the length of the road for road projects.
   * @param widthGUIID A TextField used on a .fxml scene to show the width of the road for road projects.
   * @param durationGUIID A TextField used on a .fxml scene to show the project duration in months.
   * @param bridgeOrTunnelInfoGUIID A TextArea used to show the String data relating to any bridge or tunnel information.
   * @param environmentalInfoGUIID A TextArea used to show the String data relating to any environmental or geographical information.
   * @Author: K. Dashnaw
   */
  public void initializeCreateProjectGUI(TextField lengthGUIID, TextField widthGUIID, TextField durationGUIID, TextArea bridgeOrTunnelInfoGUIID, TextArea environmentalInfoGUIID)
  {
    RoadProject currentProject = (RoadProject) this.getSelectedProject();

    lengthGUIID.setText("" + currentProject.getRoadLength());
    widthGUIID.setText("" + currentProject.getRoadWidth());
    durationGUIID.setText("" + currentProject.getProjectDuration());
    bridgeOrTunnelInfoGUIID.setText(currentProject.getBridgeOrTunnelDetails());
    environmentalInfoGUIID.setText(currentProject.getEnvironmentalOrGeographicalChallenges());
  }






  /** <p>Edits a single construction project in the project management system's master list.<br>
   * This method is mainly here for better understanding with the naming. addProject() is the important one, editProject merely sends
   * the information on to the addProject() method. <br><br>See more details about the edit code and sequence in the SubScene_EditProjectView.java</p>
   * @param project The ConstructionProject that is being edited.
   * @return A boolean that is TRUE if the edit was successful, else FALSE.
   * @Author: K. Dashnaw
   */
  public boolean editProject(ConstructionProject project)
  {
    return this.addProject(project);
  }






  /** <p>This method is used to refresh the contents of the Dashboard project object,
   * containing references to the projects shown on the Dashboard
   * It is called whenever projects are modified.</p>
   * @Author: K. Dashnaw
   * */
  public void refreshDashboardProjects()
  {
    setDashboardProgressReport(new DashboardProgressReports());
    for (int i = 0; i < getAllProjectsList().size(); i++)
    {
      if(getAllProjectsList().get(i).isDashboardProject() && getDashboardProgressReports().getCurrentCapacity() <= getDashboardProgressReports().getMaxCapacity() && getDashboardProgressReports().getCurrentCapacity() > 0)
      {
        getDashboardProgressReports().addProgressReport(getAllProjectsList().get(i).generateProgressReport());
      }
    }
  }






  /** <p>Removes a single construction project from the project management system.</p>
   * @param project The ConstructionProject to remove from the master list.
   * @Author: Z. Banouri
   */
  public boolean removeProject(ConstructionProject project)
  {
    // Checking if the project is not in the list.
    boolean projectFound = false;
    int projectIndex = -1;

    if(project.getProjectType().equalsIgnoreCase("residential"))
    {
      ResidentialProject selectedProject = (ResidentialProject) project;
      for (int i = 0; i < getAllProjectsList().size(); i++)
      {
        if(getAllProjectsList().get(i) instanceof ResidentialProject &&
            selectedProject.equals(getAllProjectsList().get(i)))
        {
          projectIndex = i;
          projectFound = true;
          break;
        }
      }
    }
    else if(project.getProjectType().equalsIgnoreCase("commercial"))
    {
      CommercialProject selectedProject = (CommercialProject) project;
      for (int i = 0; i < getAllProjectsList().size(); i++)
      {
        if(getAllProjectsList().get(i) instanceof CommercialProject &&
            selectedProject.equals(getAllProjectsList().get(i)))
        {
          projectIndex = i;
          projectFound = true;
          break;
        }
      }
    }
    else if(project.getProjectType().equalsIgnoreCase("industrial"))
    {
      IndustrialProject selectedProject = (IndustrialProject) project;
      for (int i = 0; i < getAllProjectsList().size(); i++)
      {
        if(getAllProjectsList().get(i) instanceof IndustrialProject &&
            selectedProject.equals(getAllProjectsList().get(i)))
        {
          projectIndex = i;
          projectFound = true;
          break;
        }
      }
    }
    else if(project.getProjectType().equalsIgnoreCase("road"))
    {
      RoadProject selectedProject = (RoadProject) project;
      for (int i = 0; i < getAllProjectsList().size(); i++)
      {
        if(getAllProjectsList().get(i) instanceof RoadProject &&
            selectedProject.equals(getAllProjectsList().get(i)))
        {
          projectIndex = i;
          projectFound = true;
          break;
        }
      }
    }

    if (!projectFound)
    {
      System.out.println("Project not found in the system!");
      setInitializationErrorMessage("Project not found in the system!");
      return false;
    }

    // if desired project is found, then removing it from the list.
    if (getAllProjectsList().remove(projectIndex) != null)
    {
      //The updated list gets saved.
      save();
      setInitializationErrorMessage("");
      setProjectIndexPosition(-1);
      return true;
    }
    else
    {
      // In case a removal fails, an error message gets printed.
      System.out.println("Error occurred!");
      setInitializationErrorMessage("Error occurred!");
      return false;
    }
  }






  /** <p>Sets the current filtering options that are applied when projects are displayed in the GUI.
   * I.e.: For instance a filtering option could be to only show projects with a budget between 100,000 and 500,000.</p>
   * @param minBudget a String containing a number representing the minimum budget size filter
   * @param maxBudget a String containing a number representing the maximum budget size filter
   * @param minDuration a String containing a number representing the minimum project duration in months, size filter
   * @param maxDuration a String containing a number representing the minimum project duration in months, size filter
   * @param phoneNumber a String containing a number representing the phone number filter
   * @param hideFinishedProjects a Boolean set to TRUE if completed projects should be hidden from project view, else FALSE.
   * @param hideOngoingProjects a Boolean set to TRUE if ongoing projects should be hidden from project view, else FALSE.
   * @param hideResidential a Boolean set to TRUE if Residential projects should be hidden from project view, else FALSE.
   * @param hideCommercial a Boolean set to TRUE if Commercial projects should be hidden from project view, else FALSE.
   * @param hideIndustrial a Boolean set to TRUE if Industrial projects should be hidden from project view, else FALSE.
   * @param hideRoad a Boolean set to TRUE if Road projects should be hidden from project view, else FALSE.
   * @Author: E. Kadrolli
   */
  public ArrayList<ConstructionProject> filterProject(double minBudget, double maxBudget, int minDuration, int maxDuration, String phoneNumber, boolean hideFinishedProjects, boolean hideOngoingProjects, boolean hideResidential, boolean hideCommercial, boolean hideIndustrial, boolean hideRoad)
  {
    ArrayList<ConstructionProject> projectListCopy = new ArrayList<>();

    for (int i = 0; i < getAllProjectsList().size(); i++)
    {
      projectListCopy.add(getAllProjectsList().get(i).copy());
    }

    //SET THE ACTUAL FILTERS.
    for (int i = 0; i < projectListCopy.size(); i++)
    {
      if(projectListCopy.get(i).getFinances().getTotalBudget() < minBudget || projectListCopy.get(i).getFinances().getTotalBudget() > maxBudget)
      {
        projectListCopy.remove(projectListCopy.get(i));
        i--;
      }
      else if(projectListCopy.get(i).getProjectDuration() < minDuration || projectListCopy.get(i).getProjectDuration() > maxDuration)
      {
        projectListCopy.remove(projectListCopy.get(i));
        i--;
      }
      else if(!phoneNumber.isBlank() && projectListCopy.get(i).getCustomer().getPhoneNumber() != Integer.parseInt(phoneNumber))
      {
        projectListCopy.remove(projectListCopy.get(i));
        i--;
      }
      else if(hideFinishedProjects && projectListCopy.get(i).isProjectFinished())
      {
        projectListCopy.remove(projectListCopy.get(i));
        i--;
      }
      else if(hideOngoingProjects && !projectListCopy.get(i).isProjectFinished())
      {
        projectListCopy.remove(projectListCopy.get(i));
        i--;
      }
      else if(hideResidential && projectListCopy.get(i).getProjectType().equalsIgnoreCase("residential"))
      {
        projectListCopy.remove(projectListCopy.get(i));
        i--;
      }
      else if(hideCommercial && projectListCopy.get(i).getProjectType().equalsIgnoreCase("commercial"))
      {
        projectListCopy.remove(projectListCopy.get(i));
        i--;
      }
      else if(hideIndustrial && projectListCopy.get(i).getProjectType().equalsIgnoreCase("industrial"))
      {
        projectListCopy.remove(projectListCopy.get(i));
        i--;
      }
      else if(hideRoad && projectListCopy.get(i).getProjectType().equalsIgnoreCase("road"))
      {
        projectListCopy.remove(projectListCopy.get(i));
        i--;
      }
    }
    return projectListCopy;
  }






  /** <p>Enables data persistence across sessions by saving relevant system information to a file.<br<br>
   *  Note: Validation of data integrity should be conducted prior to calling this save method,
   *  ideally as early as while adding data to the model field attributes.</p>
   *  @return true if operation was successful, false if unsuccessful.
   * @Author: K. Dashnaw
   * */
  public boolean save ()
  {
    Object[] objectList = new Object[9]; //Pack all the different system Object into a single Object array before saving.

    objectList[0] = this.getAllProjectsList();
    objectList[1] = this.getDashboardProgressReports();
    objectList[2] = this.getDefaultResidentialSettings();
    objectList[3] = this.getDefaultCommercialSettings();
    objectList[4] = this.getDefaultIndustrialSettings();
    objectList[5] = this.getDefaultRoadSettings();
    objectList[6] = this.getFileManager().getLastDataSaveTime();
    objectList[7] = this.getFileManager().getLastWebExportTime();
    objectList[8] = this.getFileManager().getWebpageFile();

    if (this.getFileManager().writeToBinary(objectList))
    {
      System.out.println("Data saved successfully");
      return true; //Saving was successful
    }
    else
    {
      return false; //Saving failed.
    }
  }






  /** <p>Enables data persistence across sessions by loading relevant system information from a binary file.<br><br>
   * Warnings are suppressed due to an unchecked cast - however since data is serialized we know the data loaded must be of the given type. </p>
   * @return A boolean containing TRUE if operation was successful, else FALSE.
   * @Author: K. Dashnaw
   * */
  @SuppressWarnings("unchecked")
  public boolean load ()
  {
    Object[] objectList = new Object[7];
    boolean returnValue = true;

    //Try to load the system binary file:
    try
    {
      objectList = getFileManager().readFromBinary();
    }
    catch(FileNotFoundException error1)
    {
      System.out.println("Unable to load local system file: " + error1);
      returnValue = false;
    }
    catch(IOException error2)
    {
      System.out.println("Unable to load. Unknown exception: " + error2);
      returnValue = false;
    }
    catch(ClassNotFoundException | NullPointerException error3)
    {
      System.out.println("Unable to load. Data is corrupted");
      returnValue = false;
    }

    //If the above operation failed, we create new project files.
    if(!returnValue)
    {
      setAllProjectsList(new ArrayList<>());
      setDashboardProgressReport(new DashboardProgressReports());
      setDefaultResidentialSettings(new DefaultResidentialSettings(9,1,1,1,false));
      setDefaultCommercialSettings(new DefaultCommercialSettings(1,18));
      setDefaultIndustrialSettings(new DefaultIndustrialSettings(30));
      setDefaultRoadSettings(new DefaultRoadSettings("0","None",18));
    }
    else
    {
      //Now we evaluate the data integrity of the loaded classes.
      try
      {
        setAllProjectsList((ArrayList<ConstructionProject>) objectList[0]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate construction project files. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setAllProjectsList(new ArrayList<>());
        returnValue = false;
      }
      try
      {
        setDashboardProgressReport((DashboardProgressReports) objectList[1]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate DashBoard progress report files. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setDashboardProgressReport(new DashboardProgressReports());
        returnValue = false;
      }
      try
      {
        setDefaultResidentialSettings((DefaultResidentialSettings) objectList[2]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate Default Residential Settings. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setDefaultResidentialSettings(new DefaultResidentialSettings(9,1,1,1,false));
        returnValue = false;
      }
      try
      {
        setDefaultCommercialSettings((DefaultCommercialSettings) objectList[3]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate Default Commercial Settings. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setDefaultCommercialSettings(new DefaultCommercialSettings(1,18));
        returnValue = false;
      }
      try
      {
        setDefaultIndustrialSettings((DefaultIndustrialSettings) objectList[4]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate Default Industrial Settings. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setDefaultIndustrialSettings(new DefaultIndustrialSettings(30));
        returnValue = false;
      }
      try
      {
        setDefaultRoadSettings((DefaultRoadSettings) objectList[5]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate Default Road Settings. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setDefaultRoadSettings(new DefaultRoadSettings("0","None",18));
        returnValue = false;
      }
      try
      {
        getFileManager().setLastDataSaveTime((MyDate) objectList[6]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate last save date.");
      }
      try
      {
        getFileManager().setLastWebExportTime((MyDate) objectList[7]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate last HTML export date.");
      }
      try
      {
        getFileManager().setWebpageFile((File) objectList[8]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to find any prior export directory.");
      }
    }
  return returnValue;
  }






  /** <p>Exports progress reports for all projects to JSON files for use on the Company Homepage.<br>
   * File references are defined directly in FileIO.<br>
   * Errors are handled within this method, and confirmation messages relating to the successful or failed exported are written to
   * the 'initializationErrorMessage' attribute in this class.<br> This allows for the scenes calling this method to retrieve
   * these messages and display in the GUI console.<br><br></p>
   * <p>Note: This method handles the conversion of ongoing and finished projects relevant information into json compatible strings / json array.</p>
   * @Author: K. Dashnaw
   * */
  public void exportAsJson()
  {
    //First we separate our projects into 2 categories, the finished ones and the ongoing ones.
    //Se filter out any confidential projects in this phase also.
    ArrayList<ConstructionProject> finishedProjectsList = new ArrayList<>();
    ArrayList<ConstructionProject> ongoingProjectsList = new ArrayList<>();
    String finishedProjectsFileName = "exported-FinishedProjects";
    String ongoingProjectsFileName = "exported-OngoingProjects";

    //Run through projects:
    for (int i = 0; i < this.getAllProjectsList().size(); i++)
    {
      if(this.getAllProjectsList().get(i).isProjectFinished() && !(this.getAllProjectsList().get(i).isProjectConfidential()))
      {
        //This is a non-confidential finished project.
        finishedProjectsList.add(this.getAllProjectsList().get(i));
      }
      else
      {
        //This is a non-confidential ongoing project.
        ongoingProjectsList.add(this.getAllProjectsList().get(i));
      }
    }

    //Projects have now been isolated - an unnecessary/confidential projects filtered out. We now extrapolate the data needed for the website.
    //Since json, like xml, is a text-based document, we need to ensure that our data can be represented as a String before writing.
    //Below we format the information as a proper json array with the required information.

    //Add all needed data points from ongoing projects to our String list.
    List<String> ongoingStringList = new ArrayList<>();
    char asciiQuote = '"';
    String stringData;
    for (int i = 0; i < ongoingProjectsList.size(); i++)
    {
      stringData = "{";
      stringData += asciiQuote + "ProjectType" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectType() + asciiQuote + ","; //ProjectType
      stringData += asciiQuote + "ProjectName" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectInformation().getProjectName() + asciiQuote + ","; //Project Name
      stringData += asciiQuote + "ProjectDescription" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectInformation().getProjectDescription() + asciiQuote + ","; //Project Description
      stringData += asciiQuote + "PhotoURL" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectInformation().getPhotoURL() + asciiQuote + ","; //Project photo URL
      stringData += asciiQuote + "ProjectStartDate" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectStartDate() + asciiQuote + ","; //Project start date
      stringData += asciiQuote + "ProjectEndDate" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectEndDate() + asciiQuote + ","; //Project expected finish date
      stringData += asciiQuote + "ManHoursSpent" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getHumanRessources().getManHoursSpent() + asciiQuote + ","; //Project man-hours spent
      stringData += asciiQuote + "TotalManHoursNeeded" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getHumanRessources().getTotalManHoursNeeded() + asciiQuote + ","; //Total man-hours expected to use.
      stringData += asciiQuote + "MaterialExpences" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getFinances().getMaterialExpences() + asciiQuote + ","; //Expenses so far.
      stringData += asciiQuote + "TotalBudget" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getFinances().getTotalBudget() + asciiQuote; //Total budget/price.
      stringData += "}";

      if(i != ongoingProjectsList.size()-1)
      {
        stringData += ",";
      }
      ongoingStringList.add(stringData);
    }

    //Add all needed data points from finished projects to our String list.
    List<String> finishedStringList = new ArrayList<>();
    for (int i = 0; i < finishedProjectsList.size(); i++)
    {
      stringData = "{";
      stringData += asciiQuote + "ProjectType" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getProjectType() + asciiQuote + ","; //ProjectType
      stringData += asciiQuote + "ProjectName" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getProjectInformation().getProjectName() + asciiQuote + ","; //Project Name
      stringData += asciiQuote + "ProjectDescription" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getProjectInformation().getProjectDescription() + asciiQuote + ","; //Project Description
      stringData += asciiQuote + "PhotoURL" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getProjectInformation().getPhotoURL() + asciiQuote + ","; //Project photo URL
      stringData += asciiQuote + "ProjectEndDate" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getProjectEndDate() + asciiQuote + ","; //Project finish date
      stringData += asciiQuote + "TotalManHoursNeeded" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getHumanRessources().getTotalManHoursNeeded() + asciiQuote + ","; //Total man-hours expected to use.
      stringData += asciiQuote + "TotalBudget" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getFinances().getTotalBudget() + asciiQuote; //Total budget/price.
      stringData += "}";

      if(i != finishedProjectsList.size()-1)
      {
        stringData += ",";
      }

      finishedStringList.add(stringData);
    }

    //Convert to single string compatible with the parser:
    String stringWrapperStart = "{" + asciiQuote + "ongoingProjectArray" + asciiQuote + ":[";
    String stringWrapperEnd = "]}";
    String finalOngoingStr = "";
    for (String s : ongoingStringList)
    {
      finalOngoingStr += s;
    }
    finalOngoingStr = stringWrapperStart + finalOngoingStr + stringWrapperEnd;

    stringWrapperStart = "{" + asciiQuote + "finishedProjectArray" + asciiQuote + ":[";
    stringWrapperEnd = "]}";
    String finalFinishedStr = "";
    for (String s : finishedStringList)
    {
      finalFinishedStr += s;
    }
    finalFinishedStr = stringWrapperStart + finalFinishedStr + stringWrapperEnd;

    //Check if these are null
    if(finalOngoingStr.length() <= stringWrapperStart.length() + stringWrapperEnd.length())
    {
      finalOngoingStr = "null";
    }

    if(finalFinishedStr.length() <= stringWrapperStart.length() + stringWrapperEnd.length())
    {
      finalFinishedStr = "null";
    }

    //Export data:
    try
    {
      getFileManager().export(finalOngoingStr, ongoingProjectsFileName, ".json"); //Exports ongoing projects
      getFileManager().export(finalFinishedStr, finishedProjectsFileName, ".json"); //Exports finished projects

      //Export was successful
      System.out.println("EXPORT: Successfully exported non-confidential finished and ongoing projects to json.");
      setInitializationErrorMessage("EXPORT: Successfully exported non-confidential finished and ongoing projects to json.");
    }
    catch(FileNotFoundException error)
    {
      System.out.println("ERROR: Unable to export data. Parsing failed." + error);
      setInitializationErrorMessage("ERROR: Failed to export data. Exception occurred. Please contact support for more details.");
    }
    catch(RuntimeException error2)
    {
      System.out.println("ERROR: RUNTIME EXCEPTION OCCURRED WHILE EXPORTING DATA!" + error2);
      setInitializationErrorMessage("ERROR: Failed to export data. Exception occurred. Please contact support for more details.");
    }
  }

}
