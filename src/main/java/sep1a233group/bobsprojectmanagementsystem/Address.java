package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/** <p>This class contains all information relating to addresses in the system. These being Customer addresses, Company addresses and Project locations/addresses.</p>
 * @Author: Z. Banouri
 * */
public class Address implements Serializable
{

  private String street, streetNumber, apartment, city, country;
  private int postalCode;




  /** <p>Constructs the Address object upon initialization of object.</p>
   * @param street A String containing the Street name to set.
   * @param city A String containing the City name to set.
   * @param country A String containing the Country name to set.
   * @param postalCode An Integer containing the ZIP Code / Postal Code to set.
   * @Author: Z. Banouri
   * */
  public Address(String street, String city, String country, int postalCode)
  {
    set(street, "", city, country, postalCode);
  }




  /** <p>Gets the Street name (not the street number). </p>
   * @return A String containing the currently set Street name.
   * @Author: Z. Banouri
   * */
  public String getStreet()
  {
    return street;
  }




  /** <p>Gets the Apartment information. </p>
   * @return A String containing the currently set Apartment information.
   * @Author: Z. Banouri
   * */
  public String getApartment()
  {
    return apartment;
  }




  /** <p>Gets the City name.</p>
   * @return A String containing the currently set City name.
   * @Author: Z. Banouri
   * */
  public String getCity()
  {
    return city;
  }




  /** <p>Gets the Country name.</p>
   * @return A String containing the currently set Country name.
   * @Author: Z. Banouri
   * */
  public String getCountry()
  {
    return country;
  }




  /** <p>Gets the ZIP Code / Postal code.</p>
   * @return An Integer containing the currently set ZIP Code.
   * @Author: Z. Banouri
   * */
  public int getPostalCode()
  {
    return postalCode;
  }




  /** <p>Gets the Street Number (typically used in conjunction with the streetName setter/getter).</p>
   * @return A String containing the currently set street number
   * @Author: Z. Banouri
   * */
  public String getStreetNumber()
  {
    return streetNumber;
  }




  /** <p>Sets the Street name (typically used in conjunction with the streetNumber setter/getter).</p>
   * @param street A String containing the currently set Street name.
   * @Author: Z. Banouri
   * */
  public void setStreet(String street)
  {
    this.street = street;
  }




  /** <p>Sets the Street number (typically used in conjunction with the streetName setter/getter).</p>
   * @param streetNumber A String containing the currently set street number.
   * @Author: Z. Banouri
   * */
  public void setStreetNumber(String streetNumber)
  {
    this.streetNumber = streetNumber;
  }




  /** <p>Sets the Apartment information.</p>
   * @param apartment A String containing the currently set Apartment information.
   * @Author: K. Dashnaw
   * */
  public void setApartment(String apartment)
  {
    this.apartment = apartment;
  }




  /** <p>Sets the ZIP Code / Postal code.</p>
   * @param postalCode An Integer containing the currently set ZIP Code.
   * @Author: K. Dashnaw
   * */
  public void setPostalCode(int postalCode)
  {
    this.postalCode = postalCode;
  }




  /** <p>Sets the City name.</p>
   * @param city A String containing the currently set City name.
   * @Author: K. Dashnaw
   * */
  public void setCity(String city)
  {
    this.city = city;
  }




  /** <p>Sets the Country name.</p>
   * @param country A String containing the currently set Country name.
   * @Author: K. Dashnaw
   * */
  public void setCountry(String country)
  {
    this.country = country;
  }




  /** <p>Sets the Address information through this single method. </p>
   * @param street A String containing the currently set Street name.
   * @param apartment A String containing the currently set Apartment information.
   * @param city A String containing the currently set City name.
   * @param country A String containing the currently set Country name.
   * @param postalCode An Integer containing the currently set ZIP Code.
   * @Author: Z. Banouri
   * */
  public void set(String street, String apartment, String city, String country, int postalCode)
  {
    setStreet(street);
    setCity(city);
    setCountry(country);
    setPostalCode(postalCode);
    setStreetNumber("");
    setApartment(apartment);
  }





  /** <p>Evaluates whether or not the Object passed as an argument is exactly equal to this Object.</p>
   * @param other An object to evaluate against.
   * @return TRUE if both are equal, else FALSE.
   * @Author: Z. Banouri
   * */
  public boolean equals(Object other)
  {
    if (!(other instanceof Address))
    {
      return false;
    }
    Address otherAddress = (Address) other;

    return (otherAddress.getStreet().equals(this.getStreet()) && otherAddress.getStreetNumber().equals(this.getStreetNumber()) &&
        otherAddress.getApartment().equals(this.getApartment()) && otherAddress.getCity().equals(this.getCity())
            && otherAddress.getPostalCode() == this.getPostalCode() && otherAddress.getCountry().equals(this.getCountry()));
  }




  /** <p>Generates a String formatted expression of all the attributes in this Address Object.</p>
   * @return A String containing all the information from this Address Object.
   * @Author: K. Dashnaw
   * */
  @Override public String toString()
  {
    return "Address{" + "street='" + street + '\'' + ", streetNumber='" + streetNumber + '\'' + ", apartment='" + apartment + '\''
        + ", city='" + city + '\'' + ", country='" + country + '\'' + ", postalCode=" + postalCode + '}';
  }
}
