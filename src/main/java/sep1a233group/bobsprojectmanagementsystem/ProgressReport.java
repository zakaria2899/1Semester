package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/** <p>This class contains project information related to the actual progress of this project. Most of this information (except customer details) is later exported to the webpage. </p>
 * @Author: A. Özer
 * */
public class ProgressReport implements Serializable
{
  private Customer customer;
  private Address projectAddress;
  private Finances projectFinances;
  private MyDate projectStartDate, projectEndDate;
  private HumanRessources projectRessources;




  /** <p>Constructs the ProgressReport object upon initialization, based on the passed argument.</p>
   * @param customer A Customer object containing this projects customer information.
   * @param projectAddress An Address object containing this projects location/address information.
   * @param projectFinances A Finances object containing the financial information about this project.
   * @param projectStartDate A MyDate object containing date information regarding the start date of this project.
   * @param projectEndDate A MyDate object containing date information regarding the end date of this project.
   * @param projectRessources a HumanRessources object containing man-hour spent/total information for this project.
   * @Author: A. Özer
   * */
  public ProgressReport(Customer customer, Address projectAddress,
      Finances projectFinances, MyDate projectStartDate, MyDate projectEndDate,
      HumanRessources projectRessources)
  {
    setProgressReport(customer, projectAddress, projectFinances, projectStartDate, projectEndDate, projectRessources);
  }





  /** <p>Sets all the required information for a progress report in this single method.
   * This information is exported to the webpage when export method is executed.</p>
   * @param customer A Customer object containing this projects customer information.
   * @param projectAddress An Address object containing this projects location/address information.
   * @param projectFinances A Finances object containing the financial information about this project.
   * @param projectStartDate A MyDate object containing date information regarding the start date of this project.
   * @param projectEndDate A MyDate object containing date information regarding the end date of this project.
   * @param projectRessources a HumanRessources object containing man-hour spent/total information for this project.
   * @Author: A. Özer
   * */
  public void setProgressReport(Customer customer, Address projectAddress,
      Finances projectFinances, MyDate projectStartDate, MyDate projectEndDate,
      HumanRessources projectRessources)
  {
    this.customer = customer;
    this.projectAddress = projectAddress;
    this.projectFinances = projectFinances;
    this.projectStartDate = projectStartDate;
    this.projectEndDate = projectEndDate;
    this.projectRessources = projectRessources;
  }




  /** <p>Gets the customer information from this project.</p>
   * @return A Customer object containing this projects customer information.
   * @Author: A. Özer
   * */
  public Customer getCustomer()
  {
    return customer;
  }




  /** <p>Gets the project address/location information for this project.</p>
   * @return An Address object containing this projects location/address information.
   * @Author: A. Özer
   * */
  public Address getProjectAddress()
  {
    return projectAddress;
  }




  /** <p>Gets the financial information for this project.</p>
   * @return A Finances object containing the financial information about this project.
   * @Author: A. Özer
   * */
  public Finances getProjectFinances()
  {
    return projectFinances;
  }




  /** <p>Gets the date this project started/starts.</p>
   * @return A MyDate object containing date information regarding the start date of this project.
   * @Author: A. Özer
   * */
  public MyDate getProjectStartDate()
  {
    return projectStartDate;
  }




  /** <p>Gets the date this project ended (or is planned to end).</p>
   * @return A MyDate object containing date information regarding the end date of this project.
   * @Author: A. Özer
   * */
  public MyDate getProjectEndDate()
  {
    return projectEndDate;
  }




  /** <p>Gets the human resources related information for this project.</p>
   * @return a HumanRessources object containing man-hour spent/total information for this project.
   * @Author: A. Özer
   * */
  public HumanRessources getProjectRessources()
  {
    return projectRessources;
  }




  /** <p>Generates a String formatted expression of all the attributes in this ProgressReport Object.</p>
   * @return A String containing all the information from this ProgressReport Object.
   * @Author: K. Dashnaw
   * */
  @Override public String toString()
  {
    return "ProgressReport{" + "customer=" + customer + ", projectAddress=" + projectAddress + ", projectFinances=" + projectFinances
        + ", projectStartDate=" + projectStartDate + ", projectEndDate=" + projectEndDate + ", projectRessources=" + projectRessources
        + '}';
  }





  /** <p>Evaluates whether or not the Object passed as an argument is exactly equal to this Object.</p>
   * @param obj An object to evaluate against.
   * @return TRUE if both are equal, else FALSE.
   * @Author: K. Dashnaw
   * */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof ProgressReport))
    {
      return false;
    }
    ProgressReport other = (ProgressReport) obj;
    return (other.getCustomer().equals(this.getCustomer()) && other.getProjectAddress().equals(this.getProjectAddress()) && other.getProjectFinances().equals(this.getProjectFinances()) && other.getProjectStartDate().equals(this.getProjectStartDate()) && other.getProjectEndDate().equals(this.getProjectEndDate()) && other.getProjectRessources().equals(this.getProjectRessources()));
  }

}