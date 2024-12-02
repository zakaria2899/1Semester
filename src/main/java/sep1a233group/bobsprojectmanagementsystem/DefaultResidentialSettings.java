package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!

/** <p>This class contains the default residential project settings, defined by the system user.<br>In this application, these data fields are both saved and loaded between sessions and are set in the GUI.<br><br>
 * If for some reason a fresh generation of system files is needed some hard-coded default values are used instead the first time the application loads (this is done from the MainModel class). These can be changed by the user in the GUI.</p>
 * @Author: Z. Banouri
 * */
public class DefaultResidentialSettings implements Serializable
{

  private int projectDuration, numberOfKitchens, numberOfBathrooms,numberOfOtherRoomsWithPlumbing;

  private boolean isRenovation;




  /** <p>Constructs the DefaultResidentialSettings object upon initialization, based on the passed arguments.</p>
   * @param projectDuration An integer containing the default project duration in months.
   * @param numberOfKitchens An integer containing the default number of kitchens in a new project.
   * @param numberOfBathrooms An integer containing the default number of bathrooms in a new project.
   * @param numberOfOtherRoomsWithPlumbing An integer containing the default number of 'other rooms with plumbing' in a new project.
   * @param isRenovation A default boolean that shows TRUE if the new project should default to be assigned as "renovation"<br>
   *                     or FALSE if they should be assigned to be "new constructions"
   * @Author: Z. Banouri
   * */
  public DefaultResidentialSettings(int projectDuration, int numberOfKitchens,
      int numberOfBathrooms, int numberOfOtherRoomsWithPlumbing,
      boolean isRenovation)
  {
    setProjectDuration(projectDuration);
    setNumberOfKitchens(numberOfKitchens);
    setNumberOfBathrooms(numberOfBathrooms);
    setNumberOfOtherRoomsWithPlumbing(numberOfOtherRoomsWithPlumbing);
    setRenovation(isRenovation);
  }




  /** <p>Gets the default project duration (in months) for this project type</p>
   * @return An Integer with the default number of months for this project type.
   * @Author: Z. Banouri
   * */
  public int getProjectDuration()
  {
    return projectDuration;
  }




  /** <p>Gets the default number of kitchens for this project type</p>
   * @return An integer containing the default number of kitchens in a new project.
   * @Author: Z. Banouri
   * */
  public int getNumberOfKitchens()
  {
    return numberOfKitchens;
  }




  /** <p>Gets the default number of bathrooms for this project type</p>
   * @return An integer containing the default number of bathrooms in a new project.
   * @Author: Z. Banouri
   * */
  public int getNumberOfBathrooms()
  {
    return numberOfBathrooms;
  }




  /** <p>Gets the default number of 'other rooms with plumbing' for this project type</p>
   * @return An integer containing the default number of 'other rooms with plumbing' in a new project.
   * @Author: Z. Banouri
   * */
  public int getNumberOfOtherRoomsWithPlumbing()
  {
    return numberOfOtherRoomsWithPlumbing;
  }




  /** <p>Gets the default renovation/new build status</p>
   * @return A boolean that shows TRUE if the new project should default to be assigned as "renovation" or FALSE if they should be assigned to be "new constructions"
   * @Author: Z. Banouri
   * */
  public boolean isRenovation()
  {
    return isRenovation;
  }




  /** <p>Sets the default number of kitchens for this project type</p>
   * @param numberOfKitchens  An integer containing the default number of kitchens in a new project.
   * @Author: Z. Banouri
   * */
  public void setNumberOfKitchens(int numberOfKitchens)
  {
    this.numberOfKitchens = numberOfKitchens;
  }




  /** <p>Sets the default number of bathrooms for this project type</p>
   * @param numberOfBathrooms  An integer containing the default number of bathrooms in a new project.
   * @Author: Z. Banouri
   * */
  public void setNumberOfBathrooms(int numberOfBathrooms)
  {
    this.numberOfBathrooms = numberOfBathrooms;
  }




  /** <p>Sets the default number of 'other rooms with plumbing' for this project type</p>
   * @param numberOfOtherRoomsWithPlumbing  An integer containing the default number of 'other rooms with plumbing' in a new project.
   * @Author: Z. Banouri
   * */
  public void setNumberOfOtherRoomsWithPlumbing(int numberOfOtherRoomsWithPlumbing)
  {
    this.numberOfOtherRoomsWithPlumbing = numberOfOtherRoomsWithPlumbing;
  }




  /** <p>Sets the default renovation/new build status</p>
   * @param renovation A boolean that shows TRUE if the new project should default to be assigned as "renovation" or FALSE if they should be assigned to be "new constructions"
   * @Author: Z. Banouri
   * */
  public void setRenovation(boolean renovation)
  {
    isRenovation = renovation;
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
