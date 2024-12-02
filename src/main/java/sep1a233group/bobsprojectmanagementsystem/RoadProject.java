package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;





/** <p>This class defines one of the 4 construction project types. This class representing the Road class of projects.</p>
 * @Author: K. Dashnaw
 */
public class RoadProject extends ConstructionProject implements Serializable
{
  private String bridgeOrTunnelDetails; //Used to describe any details relating to possible bridges or tunnels on the construction route.
  private String environmentalOrGeographicalChallenges; //Used to describe any relevant details relating to environmental or geographical challenges on the route.
  private double roadLength; //Describes how long a piece of road the project will encompass.
  private double roadWidth; //Describes the width of the road.
  private int projectDuration; // How many months the build will take.





  /** <p>Constructs the RoadProject object upon initialization<br>
   * No arguments are passed to this object as the Application relies on loading data from previous,
   * or in the MainModel class having fresh data being set to the default values, if any. <br>
   * Remaining attributes are simply initializes here with placeholder values.</p>
   * @Author: K. Dashnaw
   * */
  public RoadProject()
  {
    super();
    setRoadLength(0);
    setRoadWidth(0);
    setBridgeOrTunnelDetails("");
    setEnvironmentalOrGeographicalChallenges("");
    setProjectDuration(5);
    setProjectType("Road");
  }





  /** <p>Gets the information concerning bridges and tunnels</p>
   * @return A String containing any information regarding bridges or tunnels.
   * @Author: K. Dashnaw
   * */
  public String getBridgeOrTunnelDetails()
  {
    return bridgeOrTunnelDetails;
  }





  /** <p>Sets the information concerning bridges and tunnels</p>
   * @param bridgeOrTunnelDetails A String containing any information regarding bridges or tunnels.
   * @Author: K. Dashnaw
   * */
  public void setBridgeOrTunnelDetails(String bridgeOrTunnelDetails)
  {
    this.bridgeOrTunnelDetails = bridgeOrTunnelDetails;
  }





  /** <p>Gets the information concerning environmental and geographical information</p>
   * @return A String containing any information regarding environment or geographical factors.
   * @Author: K. Dashnaw
   * */
  public String getEnvironmentalOrGeographicalChallenges()
  {
    return environmentalOrGeographicalChallenges;
  }





  /**<p>Sets the information concerning environmental and geographical information</p>
   * @param environmentalOrGeographicalChallenges A String containing any information regarding environment or geographical factors.
   * @Author: K. Dashnaw
   * */
  public void setEnvironmentalOrGeographicalChallenges(String environmentalOrGeographicalChallenges)
  {
    this.environmentalOrGeographicalChallenges = environmentalOrGeographicalChallenges;
  }





  /** <p>Gets the road length in meters</p>
   * @return Double containing the road length meters.
   * @Author: K. Dashnaw
   * */
  public double getRoadLength()
  {
    return roadLength;
  }





  /** <p>Sets the road length in meters</p>
   * @param roadLength Double containing the road length meters.
   * @Author: K. Dashnaw
   * */
  public void setRoadLength(double roadLength)
  {
    this.roadLength = roadLength;
  }





  /** <p>Gets the road width in meters</p>
   * @return Double containing the road width meters.
   * @Author: K. Dashnaw
   * */
  public double getRoadWidth()
  {
    return roadWidth;
  }





  /** <p>Sets the road width in meters</p>
   * @param roadWidth  Double containing the road width meters.
   * @Author: K. Dashnaw
   * */
  public void setRoadWidth(double roadWidth)
  {
    this.roadWidth = roadWidth;
  }





  /** <p>Gets the project duration (in months) for this project</p>
   * @return An Integer with the number of months (duration) for this project.
   * @Author: K. Dashnaw
   * */
  public int getProjectDuration()
  {
    return projectDuration;
  }





  /** <p>Sets the project duration (in months) for this project</p>
   * @param projectDuration An Integer with the number of months (duration) for this project.
   * @Author: K. Dashnaw
   * */
  public void setProjectDuration(int projectDuration)
  {
    this.projectDuration = projectDuration;
  }





  /** <p>Generates a String formatted expression of all the attributes in this Road Object.</p>
   * @return A String containing all the information from this Road Object.
   * @Author: K. Dashnaw
   * */
  public String toString()
  {
    String returnValue = this.getProjectType();
    returnValue += " RoadLength=" + this.getRoadLength() + "\n";
    returnValue += " RoadWidth=" + this.getRoadWidth() + "\n";
    returnValue += " BridgeOrTunnelsInfo=" + this.getBridgeOrTunnelDetails() + "\n";
    returnValue += " EnvironmentalOrGeoInfo=" + this.getEnvironmentalOrGeographicalChallenges() + "\n";
    returnValue += " displayOnDashboard?=" + this.isDashboardProject() + "\n";
    returnValue += " ProjectStartDate=" + this.getProjectStartDate().toString() + "\n";
    returnValue += " ProjectEndDate=" + this.getProjectEndDate().toString() + "\n";
    returnValue += " ProjectDuration=" + this.getProjectDuration() + "\n";
    returnValue += " Finances=" + this.getFinances() + "\n";
    returnValue += " HumanResources=" + this.getHumanRessources() + "\n";
    returnValue += " IsProjectFinished=" + this.isProjectFinished() + "\n";
    returnValue += " isProjectConfidential=" + this.isProjectConfidential() + "\n";
    returnValue += " Customer=" + this.getCustomer() + "\n";
    returnValue += " Customer=" + this.getProjectAddress() + "\n";
    returnValue += " Customer=" + this.getProjectInformation() + "\n";

    return returnValue;
  }





  /** <p>Evaluates whether or not the Object passed as an argument is exactly equal to this Object.</p>
   * @param obj An object to evaluate against.
   * @return TRUE if both are equal, else FALSE.
   * @Author: K. Dashnaw
   * */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof RoadProject))
    {
      return false;
    }
    RoadProject other = (RoadProject) obj;

    //Compare local attributes for equality:
    if (!(other.getRoadWidth() == this.getRoadWidth() && other.getProjectDuration() == this.getProjectDuration() &&
        other.getRoadLength() == this.getRoadLength() && other.getEnvironmentalOrGeographicalChallenges().equals(this.getEnvironmentalOrGeographicalChallenges()) &&
        other.getBridgeOrTunnelDetails().equals(this.getBridgeOrTunnelDetails())))
    {
      return false;
    }
    //Compare Customer and Address info:
    else if(!(other.getCustomer().equals(this.getCustomer()) && other.getProjectAddress().equals(this.getProjectAddress())))
    {
      return false;
    }
    //Compare Finances and Human resources info:
    else if(!(other.getFinances().equals(this.getFinances()) && other.getHumanRessources().equals(this.getHumanRessources()) && other.isProjectConfidential() == this.isProjectConfidential() && other.isProjectFinished() == this.isProjectFinished()))
    {
      return false;
    }
    //Compare remaining info
    else if (!(other.getProjectInformation().equals(this.getProjectInformation()) && other.getProjectStartDate().equals(this.getProjectStartDate()) && other.getProjectEndDate().equals(this.getProjectEndDate())))
    {
      return false;
    }
    else
    {
      //Objects are identical!
      return true;
    }
  }




  /** <p>Creates a duplicate of the object this method is called on. It copies all attributes values.</p>
   * @return A new ConstructionProject of the specific type this method is called on.
   * @Author: K. Dashnaw
   * */
  public ConstructionProject copy()
  {
    //Create fresh project.
    RoadProject copyProject = new RoadProject();

    //Copy attribute values: First project specific values.
    copyProject.setBridgeOrTunnelDetails(this.getBridgeOrTunnelDetails());
    copyProject.setEnvironmentalOrGeographicalChallenges(this.getEnvironmentalOrGeographicalChallenges());
    copyProject.setRoadWidth(this.getRoadWidth());
    copyProject.setRoadLength(this.getRoadLength());

    //Copy non-unique project attributes:
    copyProject.setProjectDuration(this.getProjectDuration());
    copyProject.setDashboardProject(this.isDashboardProject());
    copyProject.setProjectConfidentiality(this.isProjectConfidential());
    copyProject.setProjectType("Road");
    copyProject.setProjectFinished(this.isProjectFinished());

    //Copy Date information:
    copyProject.setProjectStartDate(new MyDate(this.getProjectStartDate().getDay(), this.getProjectStartDate().getMonth(), this.getProjectStartDate().getYear()));
    copyProject.setProjectEndDate(new MyDate(this.getProjectEndDate().getDay(), this.getProjectEndDate().getMonth(), this.getProjectEndDate().getYear()));

    //Copy customer information:
    copyProject.setCustomer(new Customer(this.getCustomer().getFirstName(), this.getCustomer().getLastName(), this.getCustomer().getEmail(), this.getCustomer().getPhoneNumber(), new Address(this.getCustomer().getCustomerAddress().getStreet(), this.getCustomer().getCustomerAddress().getCity(), this.getCustomer().getCustomerAddress().getCountry(), this.getCustomer().getCustomerAddress().getPostalCode())));
    copyProject.getCustomer().getCustomerAddress().setStreetNumber(this.getCustomer().getCustomerAddress().getStreetNumber());
    copyProject.getCustomer().setPhoneNumberPrefix(this.getCustomer().getPhoneNumberPrefix());
    copyProject.getCustomer().getCustomerAddress().setApartment(this.getCustomer().getCustomerAddress().getApartment());
    copyProject.getCustomer().getCustomerCompany().setName(this.getCustomer().getCustomerCompany().getName());
    copyProject.getCustomer().getCustomerCompany().setCompanyAddress(new Address(this.getCustomer().getCustomerCompany().getCompanyAddress().getStreet(),this.getCustomer().getCustomerCompany().getCompanyAddress().getCity(), this.getCustomer().getCustomerCompany().getCompanyAddress().getCountry(), this.getCustomer().getCustomerCompany().getCompanyAddress().getPostalCode()));
    copyProject.getCustomer().getCustomerCompany().getCompanyAddress().setStreetNumber(this.getCustomer().getCustomerCompany().getCompanyAddress().getStreetNumber());
    copyProject.getCustomer().getCustomerCompany().getCompanyAddress().setApartment(this.getCustomer().getCustomerCompany().getCompanyAddress().getApartment());

    //Copy project location:
    copyProject.getProjectAddress().setStreet(this.getProjectAddress().getStreet());
    copyProject.getProjectAddress().setStreetNumber(this.getProjectAddress().getStreetNumber());
    copyProject.getProjectAddress().setCity(this.getProjectAddress().getCity());
    copyProject.getProjectAddress().setApartment(this.getProjectAddress().getApartment());
    copyProject.getProjectAddress().setPostalCode(this.getProjectAddress().getPostalCode());
    copyProject.getProjectAddress().setCountry(this.getProjectAddress().getCountry());

    //Copy Promotional Information:
    copyProject.getProjectInformation().setProjectName(this.getProjectInformation().getProjectName());
    copyProject.getProjectInformation().setProjectManagerComments(this.getProjectInformation().getProjectManagerComments());
    copyProject.getProjectInformation().setProjectDescription(this.getProjectInformation().getProjectDescription());
    copyProject.getProjectInformation().addPhotoURL(this.getProjectInformation().getPhotoURL());

    //Copy Human Resources information:
    copyProject.getHumanRessources().setManHoursSpent(this.getHumanRessources().getManHoursSpent());
    copyProject.getHumanRessources().setTotalManHoursNeeded(this.getHumanRessources().getTotalManHoursNeeded());

    //Copy Finances information:
    copyProject.getFinances().setTotalBudget(this.getFinances().getTotalBudget());
    copyProject.getFinances().setMaterialExpences(this.getFinances().getMaterialExpences());

    //Generate progress report:
    copyProject.generateProgressReport();

    return copyProject;
  }
}
