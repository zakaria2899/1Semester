package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!

import java.util.Calendar;
import java.util.GregorianCalendar;


/** <p>This class is used for handling internal Date related calculations and operations.</p>
 * @Author: A. Özer
 * */
public class MyDate implements Serializable
{
  private int day;
  private int month;
  private int year;

  /** <p>Constructs a MyDate object from the passed parameters.</p>
   * @param day An integer representing the day of the month (1-31) to set.
   * @param month An integer representing the month of th year (1-12) to set.
   * @param year An integer representing the year to set (example: 2023).
   * @Author: A. Özer
   * */
  public MyDate(int day, int month, int year)
  {
    set(day, month, year);
  }




  /** <p>Constructs a MyDate object with the initial date being set to today.</p>
   * @Author: A. Özer
   * */
  public MyDate()
  {
    Calendar now = GregorianCalendar.getInstance();
    setYear(now.get(Calendar.YEAR));
    setMonth(now.get(Calendar.MONTH) + 1);
    setDay(now.get(Calendar.DAY_OF_MONTH));
  }




  /** <p>Gets the day of the month.</p>
   * @return An integer containing the day of the month (1-31)
   * @Author: A. Özer
   * */
  public int getDay()
  {
    return day;
  }




  /** <p>Gets the month of the year.</p>
   * @return An integer containing the month of the year (1-12)
   * @Author: A. Özer
   * */
  public int getMonth()
  {
    return month;
  }




  /** <p>Gets the year.</p>
   * @return An integer containing the year (example: 2023)
   * @Author: A. Özer
   * */
  public int getYear()
  {
    return year;
  }




  /** <p>Sets the day of the month.</p>
   * @param day An integer containing the day of the month (1-31)
   * @Author: A. Özer
   * */
  public void setDay(int day)
  {
    if (day < 1 || day > numberOfDaysInMonth())
    {
      if (day < 1)
      {
        day = 1;
        this.day = day;
      }
      else
      {
        day = numberOfDaysInMonth();
        this.day = day;
      }
    }
    else
    {
      this.day = day;
    }
  }




  /** <p>Sets the month of the year.</p>
   * @param month An integer containing the month of the year (1-12)
   * @Author: A. Özer
   * */
  public void setMonth(int month)
  {
    if (month < 1 || month > 12)
    {
      if (month < 1)
      {
        month = 1;
        this.month = month;
      }
      else
      {
        month = 12;
        this.month = month;
      }
    }
    else
    {
      this.month = month;
    }
  }




  /** <p>Sets the year.</p>
   * @param year An integer containing the year (example: 2023)
   * @Author: A. Özer
   * */
  public void setYear(int year)
  {
    if (year < 0)
    {
      year = -year;
      this.year = year;
    }
    else
    {
      this.year = year;
    }
  }




  /** <p>Checks whether the the currently set year is a leap year.</p>
   * @return TRUE if the year is a leap year, else FALSE.
   * @Author: A. Özer
   * */
  public boolean isLeapYear()
  {
    if (this.getYear() % 4 == 0 && this.getYear() % 100 != 0 || this.getYear() % 400 == 0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }




  /** <p>Evaluates how many days the currently set month has. </p>
   * @return An integer containing the number of days this month has.
   * @Author: A. Özer
   * */
  public int numberOfDaysInMonth()
  {
    if (this.getMonth() == 1 || this.getMonth() == 3 || this.getMonth() == 5
        || this.getMonth() == 7 || this.getMonth() == 8 || this.getMonth() == 10
        || this.getMonth() == 12)
    {
      return 31; //Days in uneven months.
    }
    else if (this.getMonth() == 4 || this.getMonth() == 6
        || this.getMonth() == 9 || this.getMonth() == 11)
    {
      return 30; //Days in uneven months.
    }
    else if (this.getMonth() == 2)
    {
      if (this.isLeapYear())
      {
        return 29; //Leap year
      }
      else
      {
        return 28; //not leap year.
      }
    }
    else
    {
      return 0; //Unknown error!
    }
  }




  /** <p>Evaluates how many days there are between two MyDate objects </p>
   * @param other A MyDate object containing the date information to evaluate against.
   * @return An integer the number of days between this MyDate and the MyDate passed as a parameter.
   * @Author: A. Özer
   * */
  public int daysBetween(MyDate other)
  {
    int counterDate = 0; //Used to count the number of days between the two.

    if (this.isBefore(other))
    {
      MyDate copyOfEarlistDateOBJ = this.copy();

      while (copyOfEarlistDateOBJ.isBefore(other))
      {
        counterDate++;
        copyOfEarlistDateOBJ.stepForwardOneDay();
      }

    }
    else if (other.isBefore(this))
    {
      MyDate copyOfEarlistDateOBJ = other.copy();

      while (copyOfEarlistDateOBJ.isBefore(this))
      {
        counterDate++;
        copyOfEarlistDateOBJ.stepForwardOneDay();
      }

    }
    else
    {
      return 0;
    }
    return counterDate;
  }




  /** <p>Evaluates whether or not the set date is before the one passed as an argument </p>
   * @param other A MyDate object containing the date information to evaluate against.
   * @return A boolean that is TRUE if this date is before the one evaluated against, otherwise FALSE if not.
   * @Author: A. Özer
   * */
  public boolean isBefore(MyDate other)
  {
    if (this.getYear() < other.getYear())
    {
      return true;
    }
    else if (this.getYear() > other.getYear())
    {
      return false;
    }
    else if (this.getMonth() < other.getMonth())
    {
      return true;
    }
    else if (this.getMonth() > other.getMonth())
    {
      return false;
    }
    else if (this.getDay() < other.getDay())
    {
      return true;
    }
    else
    {
      return false;
    }
  }




  /** <p>Sets the complete date at once. </p>
   * @param day day An integer containing the day of the month (1-31)
   * @param month An integer containing the month of the year (1-12)
   * @param year An integer containing the year (example: 2023)
   * @Author: A. Özer
   * */
  public void set(int day, int month, int year)
  {
    setYear(year);
    setMonth(month);
    setDay(day);
  }





  /** <p>Increases the current Date with +1 day, properly handling transitions between months and years. </p>
   * @Author: A. Özer
   * */
  public void stepForwardOneDay()
  {
    //Increment the day with 1 if the given day is less than the last day of the given month.
    if (this.getDay() < 28)
    {
      this.set(this.getDay() + 1, this.getMonth(), this.getYear());
    }
    //Increment the day with 1, but this loop catches the specifics around february and leap years.
    else if (this.getDay() == 28 && this.getMonth() == 2 && !this.isLeapYear())
    {
      this.set(1, 3, this.getYear());
    }
    else if (this.getDay() == 29 && this.getMonth() == 2 && this.isLeapYear())
    {
      this.set(1, 3, this.getYear());
    }

    //Check if the date is not the last day of the month (even months) and increment with 1.
    else if (this.getDay() < 30)
    {
      this.set(this.getDay() + 1, this.getMonth(), this.getYear());
    }
    else if ((this.getDay() == 31 && this.numberOfDaysInMonth() == 31)
        && this.getMonth() == 12)
    {//This is december... increase the year by one.
      this.set(1, 1, this.getYear() + 1);
    }
    else if ((this.getDay() == 30 && this.numberOfDaysInMonth() == 30) || (
        this.getDay() == 31 && this.numberOfDaysInMonth() == 31))
    {
      this.set(1, this.getMonth() + 1, this.getYear());
    }
    else
    {
      this.set(this.getDay() + 1, this.getMonth(),
          this.getYear()); //Add a single day, as this remaining loop will only run if the day is nr. 30, but the number of days in the month is 31!
    }
  }




  /** <p>Evaluates whether or not the Object passed as an argument is exactly equal to this date.</p>
   * @param obj An object to evaluate against.
   * @return TRUE if both are equal, else FALSE.
   * @Author: A. Özer
   * */
  public boolean equals(Object obj)
  {
    if (obj instanceof MyDate)
    {
      MyDate otherdate = (MyDate)obj;
      return (this.getDay() == otherdate.getDay() &&
          this.getMonth() == otherdate.getMonth() &&
          this.getYear() == otherdate.getYear());
    }
    else
    {
      return false;
    }
  }




  /** <p>Formats the current Date as a String in a format compatible with this application.</p>
   * @return A String formatted version of this MyDate object, in the format "DD.MM.YYYY".
   * @Author: A. Özer
   * */
  @Override public String toString()
  {
    return this.getDay() + "." + this.getMonth() + "." + this.getYear();
  }




  /** <p>Copies the selected Date and returns a new MyDate Object with the same Date information.</p>
   * @return A new MyDate object with attributes identical to the MyDate object this method is called on.
   * @Author: A. Özer
   * */
  public MyDate copy()
  {
    return new MyDate(this.getDay(), this.getMonth(), this.getYear());
  }




  /** <p>A Static method used for getting the current date, based on system time. </p>
   * @return A new MyDate object containing today's date.
   * @Author: A. Özer
   * */
  public static MyDate now()
  {
    return new MyDate();
  }
}
