package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/** <p>This class contains financial information related to the finances used with each project, such as amount of money spent on materials and the total budget size.</p>
 * @Author: A. Özer
 * */
public class Finances implements Serializable
{
  private double materialExpences, totalBudget;




  /** <p>Constructs the Finances object upon initialization, based on the passed argument.</p>
   * @param materialExpences A Double containing the amount of money already spent on project materials.
   * @param totalBudget A Double containing the total budget assigned to this project.
   * @Author: A. Özer
   * */
  public Finances(double materialExpences, double totalBudget)
  {
    setMaterialExpences(materialExpences);
    setTotalBudget(totalBudget);
  }




  /** <p>Gets the amount of money spent on materiels for this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @return A Double containing the amount of money already spent on project materials.
   * @Author: A. Özer
   * */
  public double getMaterialExpences()
  {
    return materialExpences;
  }




  /** <p>Sets the amount of money spent on materiels for this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @param number A Double containing the amount of money already spent on project materials.
   * @Author: A. Özer
   * */
  public void setMaterialExpences(double number)
  {
    this.materialExpences = number;
  }




  /** <p>Gets the total budget size assigned to this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @return A Double containing the total budget assigned to this project.
   * @Author: A. Özer
   * */
  public double getTotalBudget()
  {
    return totalBudget;
  }




  /** <p>Sets the total budget size assigned to this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @param number  A Double containing the total budget assigned to this project.
   * @Author: A. Özer
   * */
  public void setTotalBudget(double number)
  {
    this.totalBudget = number;
  }




  /** <p>Generates a String formatted expression of all the attributes in this Finances Object.</p>
   * @return A String containing all the information from this Finances Object.
   * @Author: K. Dashnaw
   * */
  @Override public String toString()
  {
    return "Finances{" + "materialExpences=" + materialExpences + ", totalBudget=" + totalBudget + '}';
  }




  /** <p>Evaluates whether or not the Object passed as an argument is exactly equal to this Object.</p>
   * @param obj An object to evaluate against.
   * @return TRUE if both are equal, else FALSE.
   * @Author: K. Dashnaw
   * */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Finances))
    {
      return false;
    }
    Finances other = (Finances) obj;
    return (other.getMaterialExpences() == this.getMaterialExpences() && other.getTotalBudget() == this.getTotalBudget());
  }
}
