package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;




/** <p>This abstract super class defines the overall functionality, objects, relations and attributes for all construction projects.<br><br>
 * It is extended by specific project types that add a specialized focus to these general fields and data.</p>
 * @Author: Primarily A. Özer, with additions from the rest of the team.
 */
public abstract class ConstructionProject implements Serializable
{
  private Address projectAddress; //What is the address of the Project?
  private Customer customer; // who is the project for?
  private PromotionalInformation projectInformation; // What information do we have about the project?
  private HumanRessources humanRessources; // how many man-hours is used for the project(current and expected to finish)?
  private Finances finances; //how much is spent on the project?. what is the budget for the project?
  private ProgressReport progressReport; // how is the project going?
  private boolean isProjectFinished; //is the project finished?
  private boolean projectConfidentiality; // is the project confidential?
  private MyDate projectStartDate; //When was the project created/initiated?
  private MyDate projectEndDate; //When was the project finished (or expected to finish?)
  private boolean isDashboardProject; //Used to identify projects that should be shown on the dashboard.
  private String projectType; //Used to contain the type of project.





  /** <p>Constructs the main ConstructionProject super class object upon initialization<br>
   * No arguments are passed to this object as the Application relies on loading data from previous,
   * or in the MainModel class having fresh data being set to the default values, if any. <br>
   * Remaining attributes are simply initialized here with placeholder values.</p>
   * @Author: A. Özer
   * */
  public ConstructionProject()
  {
    setProjectInformation(new PromotionalInformation(""));
    setProjectAddress(new Address("","","",0));
    setCustomer(new Customer("","","",0,new Address("","","",0)));
    setProjectConfidentiality(false);
    setProjectStartDate(MyDate.now());
    setProjectEndDate(MyDate.now());
    setHumanRessources(new HumanRessources(0,0));
    setFinances(new Finances(0,0));
    setProgressReport(new ProgressReport(getCustomer(), getProjectAddress(), getFinances(), getProjectStartDate(), getProjectEndDate(), getHumanRessources()));
    setProjectFinished(false);
    setDashboardProject(false);
    setProjectType("Undefined");
  }




  /** <p>Gets the information about whether this project should be displayed on the GUI dashboard or not</p>
   * @return A boolean that if TRUE indicates this project should be displayed on the GUI Dashboard.
   * @Author: K. Dashnaw
   * */
  public boolean isDashboardProject()
  {
    return isDashboardProject;
  }




  /** <p>Sets the information about whether this project should be displayed on the GUI dashboard or not</p>
   * @param dashboardProject A boolean that if TRUE indicates this project should be displayed on the GUI Dashboard.
   * @Author: K. Dashnaw
   * */
  public void setDashboardProject(boolean dashboardProject)
  {
    isDashboardProject = dashboardProject;
  }




  /** <p>Gets this projects' address/location information</p>
   * @return An Address object containing all this projects' address/locational information.
   * @Author: A. Özer
   * */
  public Address getProjectAddress() { return projectAddress; }




  /**<p>Sets this projects' address/location information</p>
   * @param projectAddress An Address object containing all this projects' address/locational information.
   * @Author: A. Özer
   * */
  public void setProjectAddress(Address projectAddress)
  {
    this.projectAddress = projectAddress;
  }




  /** <p>Gets this projects' Customer information</p>
   * @return A Customer object containing all this projects' Customer information.
   * @Author: A. Özer
   * */
  public Customer getCustomer() { return customer; }




  /** <p>Sets this projects' Customer information</p>
   * @param customer A Customer object containing all this projects' Customer information.
   * @Author: A. Özer
   * */
  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }




  /** <p>Gets this projects' promotional information</p>
   * @return A PromotionalInformation object containing all this projects' promotional information.
   * @Author: A. Özer
   * */
  public PromotionalInformation getProjectInformation() { return projectInformation; }




  /** <p>Sets this projects' promotional information</p>
   * @param projectInformation A PromotionalInformation object containing all this projects' promotional information.
   * @Author: A. Özer
   * */
  public void setProjectInformation(PromotionalInformation projectInformation)
  {
    this.projectInformation = projectInformation;
  }




  /** <p>Gets this projects' human resources information</p>
   * @return A HumanRessources object containing all this projects' human resources information.
   * @Author: A. Özer
   * */
  public HumanRessources getHumanRessources() { return humanRessources; }




  /** <p>Sets this projects' human resources information</p>
   * @param humanRessources A HumanRessources object containing all this projects' human resources information.
   * @Author: A. Özer
   * */
  public void setHumanRessources(HumanRessources humanRessources)
  {
    this.humanRessources = humanRessources;
  }




  /** <p>Gets this projects' financial information</p>
   * @return A Finances object containing all this projects' financial information.
   * @Author: A. Özer
   * */
  public Finances getFinances() { return finances; }




  /** <p>Sets this projects' financial information</p>
   * @param finances A Finances object containing all this projects' financial information.
   * @Author: A. Özer
   * */
  public void setFinances(Finances finances)
  {
    this.finances = finances;
  }





  /** <p>This method generates a progress report based on the given project</p>
   * @return A ProgressReport object containing relevant progress report information for this project.
   * @Author: A. Özer
   */
  public ProgressReport generateProgressReport() { return new ProgressReport(this.customer,this.projectAddress,this.finances,this.projectStartDate,this.projectEndDate,this.humanRessources); }




  /** <p>This method sets the progress report object that contains relevant project data for reporting and statistics</p>
   * @param progressReport A ProgressReport object containing relevant progress report information for this project.
   * @Author: A. Özer
   */
  public void setProgressReport(ProgressReport progressReport)
  {
    this.progressReport = progressReport;
  }




  /** <p>Used to check whether the project is ongoing or has been completed</p>
   * @return A boolean that is TRUE if project is marked as completed, or FALSE if it is marked as ongoing.
   * @Author: A. Özer
   */
  public boolean isProjectFinished() { return isProjectFinished; }




  /** <p>Sets a boolean that indicates if the project is ongoing or has been completed</p>
   * @param projectFinished A boolean that is TRUE if project is marked as completed, or FALSE if it is marked as ongoing.
   * @Author: A. Özer
   */
  public void setProjectFinished(boolean projectFinished) { isProjectFinished = projectFinished; }




  /** <p>Used to check whether the project is marked as a confidential project.<br>
   * If it is marked confidential, information from this project WILL NOT be exported when exporting HTML data for use on a webpage.</p>
   * @return A boolean that is TRUE if project is marked as confidential, or FALSE if not.
   * @Author: A. Özer
   */
  public boolean isProjectConfidential() { return projectConfidentiality; }




  /** <p>Sets a boolean that indicates whether or not the project is marked as a confidential project.<br>
   * If it is marked confidential, information from this project WILL NOT be exported when exporting HTML data for use on a webpage.</p>
   * @param projectConfidentiality A boolean that is TRUE if project is marked as confidential, or FALSE if not.
   * @Author: A. Özer
   */
  public void setProjectConfidentiality(boolean projectConfidentiality) { this.projectConfidentiality = projectConfidentiality; }




  /** <p>Gets the date this project started/starts.</p>
   * @return A MyDate object containing date information regarding the start date of this project.
   * @Author: A. Özer
   * */
  public MyDate getProjectStartDate() { return projectStartDate; }




  /** <p>Sets the date this project started/starts.</p>
   * @param projectStartDate A MyDate object containing date information regarding the start date of this project.
   * @Author: A. Özer
   * */
  public void setProjectStartDate(MyDate projectStartDate)
  {
    this.projectStartDate = projectStartDate;
  }




  /** <p>Gets the date this project ended/ends.</p>
   * @return A MyDate object containing date information regarding the end date of this project.
   * @Author: A. Özer
   * */
  public MyDate getProjectEndDate() { return projectEndDate;}






  /** <p>Gets the type this project is as a String value</p>
   * @return A string value containing either "Residential", "Commercial", "Industrial" or "Road", depending on the specific project that this is.
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public String getProjectType()
  {
    return this.projectType;
  }




  /** <p>Sets the type this project is as a String value</p>
   * @param type A string value containing either "Residential", "Commercial", "Industrial" or "Road", depending on the specific project that this is.
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void setProjectType(String type)
  {
    projectType = type;
  }




  /** <p>Sets the date this project ended/ends.</p>
   * @param projectEndDate A MyDate object containing date information regarding the end date of this project.
   * @Author: A. Özer
   * */
  public void setProjectEndDate(MyDate projectEndDate)
  {
    this.projectEndDate = projectEndDate;
  }





  /** <p>Abstract method is compares the given ConstructionProject object with the current ConstructionProject object and returns true if they are identical.
   * It is for instance used to validate attempts of adding projects to the system in order to avoid adding duplicates.</p>
   * @param project An object (does not need to be a project) that this project should be compared against.
   * @return A boolean that is TRUE if both objects are 100% identical, or FALSE if they aren't.
   * @Author: K. Dashnaw
   * */
  public abstract boolean equals(Object project);





  /** <p>Abstract method for getting the project duration in months</p>
   * @return An Integer with the number of months between the start date and set end dates.
   * @Author: K. Dashnaw
   * */
  public abstract int getProjectDuration();





  /** <p>Abstract method for setting the project duration in months</p>
   * @param projectDuration An Integer with the number of months between the start date and set end dates.
   * @Author: K. Dashnaw
   * */
  public abstract void setProjectDuration(int projectDuration);





  /** <p>Abstract method to, once implemented, generate a String formatted expression of all the attributes in this ConstructionProject.</p>
   * @return A String containing all the information from this Road Object.
   * @Author: K. Dashnaw
   * */
  public abstract String toString();





  /** <p>This abstract method, once implemented, returns a copy of the selected Construction Project type.</p>
   * @return A new ConstructionProject, with the same attributes and values as the original.
   * @Author: K. Dashnaw
   */
  public abstract ConstructionProject copy();

}