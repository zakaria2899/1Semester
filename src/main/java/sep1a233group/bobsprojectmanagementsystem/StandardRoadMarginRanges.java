package sep1a233group.bobsprojectmanagementsystem;

/** <p>This class contains the company defined standard operating ranges for critical planning parameters for ROAD Projects, such as "Budget" and "Project duration".<br><br>
 * This information is hard-coded here, and is not otherwise changed during system operation.<br>
 * As such, much of this class takes on a static/final approach.</p>
 * @Author: E. Kadrolli
 * */
public class StandardRoadMarginRanges {
  // Attributes
  private static final double budgetFloor = 1000000;
  private static final double budgetCeiling = 5000000;
  private static final int projectDurationFloor = 12;
  private static final int projectDurationCeiling = 24;





  /** <p>Gets the hard-coded standard margin value for the road budget floor/min..</p>
   * @return A Double containing the road budget floor value.
   * @Author: E. Kadrolli
   * */
  public static double getBudgetFloor() {
    return budgetFloor;
  }





  /** <p>Gets the hard-coded standard margin value for the road budget ceiling/max.</p>
   * @return A Double containing the road budget ceiling value.
   * @Author: E. Kadrolli
   * */
  public static double getBudgetCeiling() {
    return budgetCeiling;
  }





  /** <p>Gets the hard-coded standard margin value for the road project duration floor, expressed in months.</p>
   * @return An Integer containing the road project duration floor value, expressed as number of months.
   * @Author: E. Kadrolli
   * */
  public static int getProjectDurationFloor() {
    return projectDurationFloor;
  }





  /** <p>Gets the hard-coded standard margin value for the road project duration ceiling, expressed in months.</p>
   * @return An Integer containing the road project duration ceiling value, expressed as number of months.
   * @Author: E. Kadrolli
   * */
  public static int getProjectDurationCeiling() {
    return projectDurationCeiling;
  }


}
