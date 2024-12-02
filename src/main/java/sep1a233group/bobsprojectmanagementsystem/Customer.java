package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/** <p>This class contains customer information, such as name, phone number, addresses, company relations, etc.</p>
 * @Author: Z. Banouri
 * */
public class Customer implements Serializable
{
  private String firstName, lastName, email, phoneNumberPrefix;
  private int phoneNumber;
  private Address customerAddress;
  private Company company;





  /** <p>Constructs the Customer object upon initialization, based on the passed arguments.</p>
   * @param firstName A String containing the Customers first name(s).
   * @param lastName A String containing the Customers last name(s).
   * @param email A String containing the Customers email address.
   * @param phoneNumber An Integer containing the Customers phone number (without country prefix).
   * @param customerAddress An Address Object containing address information about the customer.
   * @Author: Z. Banouri
   * */
  public Customer(String firstName, String lastName, String email, int phoneNumber, Address customerAddress)
  {
    set(firstName, lastName, email, phoneNumber);
    setCustomerAddress(customerAddress);
    setPhoneNumberPrefix("");
    setCustomerCompany(new Company("", new Address("", "", "", 0)));
  }




  /** <p>Gets the customers first name(s).</p>
   * @return A String containing the Customers first name(s).
   * @Author: Z. Banouri
   * */
  public String getFirstName()
  {
    return firstName;
  }




  /** <p>Gets the customers last name(s).</p>
   * @return A String containing the Customers last name(s).
   * @Author: Z. Banouri
   * */
  public String getLastName()
  {
    return lastName;
  }




  /** <p>Gets the customers email address.</p>
   * @return A String containing the Customers email address.
   * @Author: Z. Banouri
   * */
  public String getEmail()
  {
    return email;
  }




  /** <p>Gets the customers phone number, without country prefix.<br>
   * (Use the getPhoneNumberPrefix() to get the set country prefix.)</p>
   * @return An Integer containing the Customers phone number (without country prefix).
   * @Author: Z. Banouri
   * */
  public int getPhoneNumber()
  {
    return phoneNumber;
  }




  /** <p>Sets the country/region prefix for the customers phone number (example: +45 for Denmark).</p>
   * @return A String containing the customers phone number prefix (example: +45)
   * @Author: A. Özer
   * */
  public String getPhoneNumberPrefix()
  {
    return phoneNumberPrefix;
  }




  /** <p>Sets the customers first name(s).</p>
   * @param firstName  A String containing the Customers first name(s).
   * @Author: K. Dashnaw
   * */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }




  /** <p>Sets the customers last name(s).</p>
   * @param lastName A String containing the Customers last name(s)..
   * @Author: K. Dashnaw
   * */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }




  /** <p>Sets the customers email address.</p>
   * @param email  A String containing the Customers email address.
   * @Author: K. Dashnaw
   * */
  public void setEmail(String email)
  {
    this.email = email;
  }




  /** <p>Gets the country/region prefix for the customers phone number (example: +45 for Denmark).</p>
   * @param prefix  A String containing the customers phone number prefix (example: +45)
   * @Author: K. Dashnaw
   * */
  public void setPhoneNumberPrefix(String prefix)
  {
    this.phoneNumberPrefix = prefix;
  }




  /** <p>Sets the customers phone number, without country prefix.<br>
   * (Use the getPhoneNumberPrefix() to get the set country prefix.)</p>
   * @param number  An Integer containing the Customers phone number (without country prefix).
   * @Author: K. Dashnaw
   * */
  public void setPhoneNumber(int number)
  {
    this.phoneNumber = number;
  }




  /** <p>Gets the customers Address Information</p>
   * @return An Address Object containing address information about the customer.
   * @Author: K. Dashnaw
   * */
  public Address getCustomerAddress()
  {
    return this.customerAddress;
  }




  /** <p>Sets the customers Address Information</p>
   * @param address  An Address Object containing address information about the customer.
   * @Author: K. Dashnaw
   * */
  public void setCustomerAddress(Address address)
  {
    this.customerAddress = address;
  }




  /** <p>Gets the customers Company Information</p>
   * @return A Company Object containing company information about the customers' employer.
   * @Author: K. Dashnaw
   * */
  public Company getCustomerCompany()
  {
    return this.company;
  }




  /** <p>Sets the customers Company Information</p>
   * @param company A Company Object containing company information about the customers' employer.
   * @Author: K. Dashnaw
   * */
  public void setCustomerCompany(Company company)
  {
    this.company = company;
  }




  /** <p>Sets the customers personal information (name, email and phone number)</p>
   * @param firstName A String containing the Customers first name(s).
   * @param lastName A String containing the Customers last name(s).
   * @param email A String containing the Customers email address.
   * @param phoneNumber An Integer containing the Customers phone number (without country prefix).
   * @Author: Z. Banouri
   * */
  public void set(String firstName, String lastName, String email, int phoneNumber)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }




  /** <p>Generates a String formatted expression of all the attributes in this Customer Object.</p>
   * @return A String containing all the information from this Customer Object.
   * @Author: K. Dashnaw
   * */
  @Override public String toString()
  {
    return "Customer{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", company="
        + company + ", customerAddress=" + customerAddress + ", phoneNumber=" + phoneNumber + ", phoneNumberPrefix='" + phoneNumberPrefix
        + '\'' + '}';
  }





  /** <p>Evaluates whether or not the Object passed as an argument is exactly equal to this Object.</p>
   * @param obj An object to evaluate against.
   * @return TRUE if both are equal, else FALSE.
   * @Author: K. Dashnaw
   * */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Customer))
    {
      return false;
    }
    Customer other = (Customer) obj;

    return (other.getEmail().equals(this.getEmail()) && other.getFirstName().equals(this.getFirstName()) && other.getLastName()
        .equals(this.getLastName()) && other.getPhoneNumber() == this.getPhoneNumber() && other.getCustomerAddress()
        .equals(this.getCustomerAddress()) && other.getCustomerCompany().equals(this.getCustomerCompany()));
  }

}
