package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/** <p>This class contains project information related to the promotional aspect, such as project name, description, project photo, etc. </p>
 * @Author: A. Özer
 * */
public class PromotionalInformation implements Serializable
{
  private String photoURL, projectDescription, projectName, projectManagerComments;




  /** <p>Constructs the ProjectInformation object upon initialization, based on the passed argument.</p>
   * @param projectName A String containing the name of the project.
   * @Author: A. Özer
   * */
  public PromotionalInformation(String projectName)
  {
    this.photoURL = "";
    this.projectDescription = "";
    this.projectName = projectName;
    this.projectManagerComments = "";
  }




  /** <p>Gets the String reference to the location and name of the picture associated with this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @return A String containing the absolute location and name of the associated project photo, if set. Else et returns an empty String.
   * @Author: A. Özer
   * */
  public String getPhotoURL()
  {
    return photoURL;
  }




  /** <p>Sets the String reference to the location and name of the picture associated with this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @param photoURL  A String containing the absolute location and name of the associated project photo, if set. Else et returns an empty String.
   * @Author: A. Özer
   * */
  public void addPhotoURL(String photoURL)
  {
    this.photoURL = photoURL;
  }



  /** <p>Gets the description of this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @return A String containing the description of/for this specific project.
   * @Author: A. Özer
   * */
  public String getProjectDescription()
  {
    return projectDescription;
  }




  /** <p>Sets the description of this project.
   * This information is exported to the webpage when export method is executed.</p>
   * @param projectDescription  A String containing the description of/for this specific project.
   * @Author: A. Özer
   * */
  public void setProjectDescription(String projectDescription)
  {
    this.projectDescription = projectDescription;
  }




  /** <p>Gets the project name for this project.<br><br>
   * This information is exported to the webpage when export method is executed.</p>
   * @return A String containing the name of the project.
   * @Author: A. Özer
   * */
  public String getProjectName()
  {
    return projectName;
  }




  /** <p>Sets the project name for this project.<br><br>
   * This information is exported to the webpage when export method is executed.</p>
   * @param projectName  A String containing the name of the project.
   * @Author: A. Özer
   * */
  public void setProjectName(String projectName)
  {
    this.projectName = projectName;
  }




  /** <p>Gets the project managers comments for this project.<br><br>
   * These comments are kept internally in this application and are NOT exported to any webpages. </p>
   * @return A String containing any internal comments made by the project manager.
   * @Author: A. Özer
   * */
  public String getProjectManagerComments()
  {
      return projectManagerComments;
  }




  /** <p>Sets the project managers comments for this project.<br><br>
   * These comments are kept internally in this application and are NOT exported to any webpages. </p>
   * @param message  A String containing any internal comments made by the project manager.
   * @Author: A. Özer
   * */
  public void setProjectManagerComments(String message)
  {
    this.projectManagerComments = message;
  }




  /** <p>Generates a String formatted expression of all the attributes in this ProjectInformation Object.</p>
   * @return A String containing all the information from this ProjectInformation Object.
   * @Author: K. Dashnaw
   * */
  @Override public String toString()
  {
    return "PromotionalInformation{" + "photoURL='" + photoURL + '\'' + ", projectDescription='" + projectDescription + '\''
        + ", projectName='" + projectName + '\'' + ", projectManagerComments=" + projectManagerComments + '}';
  }




  /** <p>Evaluates whether or not the Object passed as an argument is exactly equal to this Object.</p>
   * @param obj An object to evaluate against.
   * @return TRUE if both are equal, else FALSE.
   * @Author: K. Dashnaw
   * */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof PromotionalInformation))
    {
      return false;
    }
    PromotionalInformation other = (PromotionalInformation) obj;
    return (other.getProjectDescription().equals(this.getProjectDescription()) && other.getProjectName().equals(this.getProjectName()) && other.getPhotoURL().equals(this.getPhotoURL()) && other.getProjectManagerComments().equals(this.getProjectManagerComments()));
  }

}
