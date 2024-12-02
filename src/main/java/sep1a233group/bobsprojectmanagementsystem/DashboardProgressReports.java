package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!
import java.util.Arrays;

/** <p>This class contains all the progress report information that are displayed on the GUI Dashboard.<br><br>
 * It is currently hardcoded to maximum 4 tracked projects on the Dashboard, but can be easily increased through adjustments in this class.</p>
 * @Author: A. Özer
 * */
public class DashboardProgressReports implements Serializable
{
  private ProgressReport[] dashboardProgressReports; // Contains the 4 progress reports that are shown on the GUI dashboard.
  private int maxCapacity; //Used to control the maximum number of progress reports to store here.




  /** <p>Constructs the DashboardProgressReports object upon initialization</p>
   * @Author: K. Dashnaw
   * */
  public DashboardProgressReports()
  {
    setMaxCapacity(4);
    setProgressReports(new ProgressReport[getMaxCapacity()]);
  }




  /** <p>Gets the maximum capacity for the ProgressReport array used to contain reports shown on the Dashboard in the GUI.</p>
   * @return An Integer containing maximum number of Progress Reports that can be displayed on the Dashboard GUI.
   * @Author: K. Dashnaw
   * */
  public int getMaxCapacity()
  {
    return maxCapacity;
  }




  /** <p>Sets the maximum capacity for the ProgressReport array used to contain reports shown on the Dashboard in the GUI.</p>
   * @param maxCapacity  An Integer containing maximum number of Progress Reports that can be displayed on the Dashboard GUI.
   * @Author: K. Dashnaw
   * */
  public void setMaxCapacity(int maxCapacity)
  {
    this.maxCapacity = maxCapacity;
  }




  /** <p>Gets the reports that are assigned to be shown on the Dashboard in the GUI. </p>
   * @return A ProgressReport[] containing all the ProgressReports that have been identified to be shown on the Dashboard in the GUI.
   * @Author: K. Dashnaw
   * */
  public ProgressReport[] getProgressReports()
  {
    return dashboardProgressReports;
  }




  /** <p>Sets the reports that are assigned to be shown on the Dashboard in the GUI.</p>
   * @param dashboardProgressReports A ProgressReport[] containing all the ProgressReports that have been identified to be shown on the Dashboard in the GUI.
   * @Author: K. Dashnaw
   * */
  public void setProgressReports(ProgressReport[] dashboardProgressReports)
  {
    this.dashboardProgressReports = dashboardProgressReports;
  }




  /** <p>Adds a Progress Reports to the current list/array of reports assigned to be shown on the Dashboard in the GUI.</p>
   * @param progressReport  A ProgressReport containing the report the user wishes to add for display on the Dashboard GUI.
   * @Author: Z. Banouri
   * */
  public void addProgressReport(ProgressReport progressReport)
  {
    //Check if received progressReport is already in list:
    boolean reportAlreadyInList = false;
    for (int j = 0; j < getProgressReports().length; j++)
    {
      if(getProgressReports()[j] != null && getProgressReports()[j].equals(progressReport))
      {
        reportAlreadyInList = true;
      }
    }
    if(!reportAlreadyInList)
    {
      for (int i = 0; i < getProgressReports().length; i++)
      {
        if(getProgressReports()[i] == null)
        {
          dashboardProgressReports[i] = progressReport;
          break;
        }
      }
    }
  }




  /** <p>Checks have many 'free' slots are left in the array. It is used to evaluate whether or not there is room for more Progress Reports,
   * for display on the Dashboard GUI.</p>
   * @return An Integer containing number of un-occupied 'slots' in this DashboardProgressReports object.
   * @Author: Z. Banouri
   * */
  public int getCurrentCapacity()
  {
    int currentCapacity = 0;

    for (int i = 0; i < this.getMaxCapacity(); i++)
    {
      if(this.getProgressReports()[i] == null)
      {
        currentCapacity++;
      }
    }
    return currentCapacity;
  }




  /** <p>Generates a String formatted expression of all the attributes in this DashboardProgressReports Object.</p>
   * @return A String containing all the information from this DashboardProgressReports Object.
   * @Author: K. Dashnaw
   * */
  @Override public String toString()
  {
    return "DashboardProgressReports{" + "dashboardProgressReports=" + Arrays.toString(dashboardProgressReports) + '}';
  }

}
