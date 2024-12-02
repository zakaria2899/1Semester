package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/** <p>This class contains the default road project settings, defined by the system user.<br>In this application, these data fields are both saved and loaded between sessions and are set in the GUI.<br><br>
 * If for some reason a fresh generation of system files is needed some hard-coded default values are used instead the first time the application loads (this is done from the MainModel class). These can be changed by the user in the GUI.</p>
 * @Author: Z. Banouri
 * */
public class DefaultRoadSettings implements Serializable
{
  private String bridgesOrTunnelDetails, enviromentalOrGeographicalChallenges;
  private int projectDuration;





  /** <p>Constructs the DefaultRoadSettings object upon initialization, based on the passed arguments.</p>
   * @param bridgesOrTunnelDetails A String containing any default information regarding bridges or tunnels.
   * @param enviromentalOrGeographicalChallenges A String containing any default information regarding environment or geographical factors.
   * @param projectDuration An integer containing the default project duration in months.
   * @Author: Z. Banouri
   * */
  public DefaultRoadSettings(String bridgesOrTunnelDetails,
      String enviromentalOrGeographicalChallenges, int projectDuration)
  {
    this.bridgesOrTunnelDetails = bridgesOrTunnelDetails;
    this.enviromentalOrGeographicalChallenges = enviromentalOrGeographicalChallenges;
    this.projectDuration = projectDuration;
  }





  /** <p>Gets the default information concerning bridge and/or tunnel information</p>
   * @return A String containing any default information regarding bridges or tunnels.
   * @Author: Z. Banouri
   * */
  public String getBridgesOrTunnelDetails()
  {
    return bridgesOrTunnelDetails;
  }





  /** <p>Gets the default information concerning environmental and geographical information</p>
   * @return A String containing any default information regarding environment or geographical factors.
   * @Author: Z. Banouri
   * */
  public String getEnviromentalOrGeographicalChallenges()
  {
    return enviromentalOrGeographicalChallenges;
  }





  /** <p>Gets the default project duration (in months) for this project type</p>
   * @return An Integer with the default number of months for this project type.
   * @Author: Z. Banouri
   * */
  public int getProjectDuration()
  {
    return projectDuration;
  }





  /** <p>Sets the default information concerning bridge tunnel information</p>
   * @param text A String containing any default information regarding bridges or tunnels.
   * @Author: Z. Banouri
   * */
  public void setBridgesOrTunnelDetails(String text)
  {
    this.bridgesOrTunnelDetails = text;
  }





  /** <p>Sets the default information concerning environmental and geographical information</p>
   * @param text  A String containing any default information regarding environment or geographical factors.
   * @Author: Z. Banouri
   * */
  public void setEnviromentalOrGeographicalChallenges(
      String text)
  {
    this.enviromentalOrGeographicalChallenges = text;
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
