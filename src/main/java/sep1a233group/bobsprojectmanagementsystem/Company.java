package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/** <p>This class contains information relating to any potential companies that the Customer is representing, as well as embedded address information regarding this company. </p>
 * @Author: Z. Banouri
 * */
public class Company implements Serializable
{
  private String name;
  private Address companyAddress;




  /** <p>Constructs the Address object upon initialization of object.</p>
   * @param name A String containing the Company name to set.
   * @param companyAddress An Address Object, containing the address information for this company.
   * @Author: Z. Banouri
   * */
  public Company(String name, Address companyAddress)
  {
    setName(name);
    setCompanyAddress(companyAddress);
  }




  /** <p>Gets the Company name.</p>
   * @return A String containing the currently set Company name.
   * @Author: Z. Banouri
   * */
  public String getName()
  {
    return name;
  }




  /** <p>Sets the Company name.</p>
   * @param name  A String containing the currently set Company name.
   * @Author: Z. Banouri
   * */
  public void setName(String name)
  {
    this.name = name;
  }




  /** <p>Gets the Company Address.</p>
   * @return An Address object containing company address information.
   * @Author: Z. Banouri
   * */
  public Address getCompanyAddress()
  {
    return companyAddress;
  }





  /** <p>Sets the Company Address.</p>
   * @param companyAddress  An Address object containing company address information to set.
   * @Author: K. Dashnaw
   * */
  public void setCompanyAddress(Address companyAddress)
  {
    this.companyAddress = companyAddress;
  }




  /** <p>Generates a String formatted expression of all the attributes in this Company Object.</p>
   * @return A String containing all the information from this Company Object.
   * @Author: K. Dashnaw
   * */
  @Override public String toString()
  {
    return "Company{" + "name='" + name + '\'' + ", companyAddress=" + companyAddress + '}';
  }





  /** <p>Evaluates whether or not the Object passed as an argument is exactly equal to this Object.</p>
   * @param other An object to evaluate against.
   * @return TRUE if both are equal, else FALSE.
   * @Author: Z. Banouri
   * */
  public boolean equals(Object other)
  {
    if(!(other instanceof Company))
    {
      return false;
    }
    Company otherCompany = (Company) other;
    return (otherCompany.getName().equals(this.getName()) && otherCompany.getCompanyAddress().equals(this.getCompanyAddress()));
  }

}
