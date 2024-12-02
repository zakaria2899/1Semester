package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/** <p>This class defines one of the 4 construction project types. This class representing the Industrial class of projects.</p>
 * @Author: K. Dashnaw
 */
public class IndustrialProject extends ConstructionProject implements Serializable
{
    private String facilityType; //Used to describe what type of facility is planned.
    private double facilitySize; //Size of building project in m^2.
    private int projectDuration; // How many months the build will take.





  /** <p>Constructs the IndustrialProject object upon initialization<br>
   * No arguments are passed to this object as the Application relies on loading data from previous,
   * or in the MainModel class having fresh data being set to the default values, if any. <br>
   * Remaining attributes are simply initializes here with placeholder values.</p>
   * @Author: K. Dashnaw
   * */
    public IndustrialProject()
    {
      super();
      setFacilitySize(0);
      setFacilityType("");
      setProjectDuration(5);
      setProjectType("Industrial");
    }





  /** <p>Gets the intended facility use information.</p>
   * @return A String containing information about the type of industrial facility.
   * @Author: K. Dashnaw
   * */
    public String getFacilityType()
    {
      return facilityType;
    }





  /** <p>sets the intended facility use information.</p>
   * @param facilityType  A String containing information about the type of industrial facility.
   * @Author: K. Dashnaw
   * */
    public void setFacilityType(String facilityType)
    {
      this.facilityType = facilityType;
    }





  /** <p>Gets the facility size in square-meters</p>
   * @return Double containing the building size in square-meters.
   * @Author: K. Dashnaw
   * */
    public double getFacilitySize()
    {
      return facilitySize;
    }





  /** <p>Sets the facility size in square-meters</p>
   * @param facilitySize A Double containing the building size in square-meters.
   * @Author: K. Dashnaw
   * */
    public void setFacilitySize(double facilitySize)
    {
      this.facilitySize = facilitySize;
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





  /** <p>Generates a String formatted expression of all the attributes in this Industrial Object.</p>
   * @return A String containing all the information from this Industrial Object.
   * @Author: K. Dashnaw
   * */
  public String toString()
  {
    String returnValue = this.getProjectType() + "\n";
    returnValue += " FacilitySize=" + this.getFacilitySize() + "\n";
    returnValue += " FacilityTYpe=" + this.getFacilityType() + "\n";
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
   * @Author: Z. Banouri
   * */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof IndustrialProject))
    {
      return false;
    }
    IndustrialProject other = (IndustrialProject) obj;

    //Compare local attributes for equality:
    if (!(other.getFacilitySize() == this.getFacilitySize() && other.getFacilityType().equals(this.getFacilityType())
        && other.getProjectDuration() == this.getProjectDuration()))
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
    else if(!(other.getProjectInformation().equals(this.getProjectInformation()) && other.getProjectStartDate().equals(this.getProjectStartDate()) && other.getProjectEndDate().equals(this.getProjectEndDate())))
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
   * @Author: Z. Banouri
   * */
  public ConstructionProject copy()
  {
    //Create fresh project.
    IndustrialProject copyProject = new IndustrialProject();

    //Copy attribute values: First project specific values.
    copyProject.setFacilityType(this.getFacilityType());
    copyProject.setFacilitySize(this.getFacilitySize());

    //Copy non-unique project attributes:
    copyProject.setProjectDuration(this.getProjectDuration());
    copyProject.setDashboardProject(this.isDashboardProject());
    copyProject.setProjectConfidentiality(this.isProjectConfidential());
    copyProject.setProjectType("Industrial");
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
