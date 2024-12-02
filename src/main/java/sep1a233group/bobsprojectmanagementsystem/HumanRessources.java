package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/** <p>This class contains project information related to the human resources used with each project , such as total man-hours required and man-hours spent.</p>
 * @Author: A. Özer
 * */
public class HumanRessources implements Serializable
{
  private double manHoursSpent, totalManHoursNeeded;





  /** <p>Constructs the ProjectInformation object upon initialization, based on the passed argument.</p>
   * @param manHoursSpent A Double containing the number of man-hours that have currently been spent on this project.
   * @param totalManHoursNeeded A Double containing the estimated number of man-hours that are needed to complete this project.
   * @Author: A. Özer
   * */
  public HumanRessources(double manHoursSpent, double totalManHoursNeeded)
  {
    setManHoursSpent(manHoursSpent);
    setTotalManHoursNeeded(totalManHoursNeeded);
  }





  /** <p>Gets the number of Man-Hours that have currently been spent on this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @return A Double containing the number of man-hours that have currently been spent on this project.
   * @Author: A. Özer
   * */
  public double getManHoursSpent()
  {
    return manHoursSpent;
  }





  /** <p>Sets the number of Man-Hours that have currently been spent on this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @param number A Double containing the number of man-hours that have currently been spent on this project.
   * @Author: A. Özer
   * */
  public void setManHoursSpent(double number)
  {
    this.manHoursSpent = number;
  }





  /** <p>Gets the estimated total number of Man-Hours required to finish this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @return A Double containing the estimated number of man-hours that are needed to complete this project.
   * @Author: A. Özer
   * */
  public double getTotalManHoursNeeded()
  {
    return totalManHoursNeeded;
  }





  /** <p>Sets the estimated total number of Man-Hours required to finish this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @param number  A Double containing the estimated number of man-hours that are needed to complete this project.
   * @Author: A. Özer
   * */
  public void setTotalManHoursNeeded(double number)
  {
    this.totalManHoursNeeded = number;
  }





  /** <p>Generates a String formatted expression of all the attributes in this HumanRessources Object.</p>
   * @return A String containing all the information from this HumanRessources Object.
   * @Author: K. Dashnaw
   * */
  @Override public String toString()
  {
    return "HumanRessources{" + "manHoursSpent=" + manHoursSpent + ", totalManHoursNeeded=" + totalManHoursNeeded + '}';
  }





  /** <p>Evaluates whether or not the Object passed as an argument is exactly equal to this Object.</p>
   * @param obj An object to evaluate against.
   * @return TRUE if both are equal, else FALSE.
   * @Author: K. Dashnaw
   * */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof HumanRessources))
    {
      return false;
    }
    HumanRessources other = (HumanRessources) obj;
    return (other.getTotalManHoursNeeded() == this.getTotalManHoursNeeded() && other.getManHoursSpent() == this.getManHoursSpent());
  }
}
