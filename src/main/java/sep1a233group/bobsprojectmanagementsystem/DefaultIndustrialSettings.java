package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/** <p>This class contains the default industrial project settings, defined by the system user.<br>In this application, these data fields are both saved and loaded between sessions and are set in the GUI.<br><br>
 * If for some reason a fresh generation of system files is needed some hard-coded default values are used instead the first time the application loads (this is done from the MainModel class). These can be changed by the user in the GUI.</p>
 * @Author: Z. Banouri
 * */
public class DefaultIndustrialSettings implements Serializable
{
  private int projectDuration;





  /** <p>Constructs the DefaultIndustrialSettings object upon initialization, based on the passed arguments.</p>
   * @param projectDuration An integer containing the default project duration in months.
   * @Author: Z. Banouri
   * */
  public DefaultIndustrialSettings(int projectDuration)
  {
    this.projectDuration = projectDuration;
  }





  /** <p>Gets the default project duration (in months) for this project type</p>
   * @return An Integer with the default number of months for this project type.
   * @Author: Z. Banouri
   * */
  public int getProjectDuration()
  {
    return projectDuration;
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
