package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/** <p>This class contains the default commercial project settings, defined by the system user.<br>In this application, these data fields are both saved and loaded between sessions and are set in the GUI.<br><br>
 * If for some reason a fresh generation of system files is needed some hard-coded default values are used instead the first time the application loads (this is done from the MainModel class). These can be changed by the user in the GUI.</p>
 * @Author: Z. Banouri
 * */
public class DefaultCommercialSettings implements Serializable
{
  private int numberOfFloors, projectDuration;




  /** <p>Constructs the DefaultCommercialSettings object upon initialization, based on the passed arguments.</p>
   * @param numberOfFloors An integer containing the default project duration in months.
   * @param projectDuration An integer containing the default number of floors in a new project.
   * @Author: Z. Banouri
   * */
  public DefaultCommercialSettings(int numberOfFloors, int projectDuration)
  {
    this.numberOfFloors = numberOfFloors;
    this.projectDuration = projectDuration;
  }




  /** <p>Gets the default number of floors for this project type</p>
   * @return An integer containing the default project duration in months.
   * @Author: Z. Banouri
   * */
  public int getNumberOfFloors()
  {
    return numberOfFloors;
  }




  /** <p>Gets the default project duration (in months) for this project type</p>
   * @return An Integer with the default number of months for this project type.
   * @Author: Z. Banouri
   * */
  public int getProjectDuration()
  {
    return projectDuration;
  }




  /** <p>Sets the default number of floors for this project type</p>
   * @param numberOfFloors An integer containing the default project duration in months.
   * @Author: Z. Banouri
   * */
  public void setNumberOfFloors(int numberOfFloors)
  {
    this.numberOfFloors = numberOfFloors;
  }




  /** <p>Sets the default project duration (in months) for this project type</p>
   * @param projectDuration An Integer with the default number of months for this project type.
   * @Author: Z. Banouri
   * */
  public void setProjectDuration(int projectDuration)
  {
    this.projectDuration = projectDuration;
  }

}
