package sep1a233group.bobsprojectmanagementsystem;

import java.io.*;

/** <p>This is the main file in-and-out controller used for maintaining data persistence across sessions,
 * and providing cross-platform functionality so project information can be dynamically loaded in the company homepage.<br><br>
 * Local files are saved in binary format.<br><br>
 * Exported files for webpage use are exported as json format, provided the proper
 * information is passed to this class as described in the documentation for the export method</p>
 * @Author: K. Dashnaw
 * */
public class FileIO
{
  private File systemSaveFile; //Reference to the system save file object.

  private File webpageFile; //Reference to the exported webpage compatible file object.
  private String systemFileName; //The name used for the system save file.
  private MyDate lastDataSaveTime; //Contains a MyDate/Time representation of when the system data was last saved.
  private MyDate lastWebExportTime; //Contains a MyDate/Time representation of when the system data was last saved.





  /** <p>Constructs the FileIO object and calls relevant methods for field attribute initialization.</p>
   * @Author: K. Dashnaw
   * */
  public FileIO()
  {
    setSystemFileName("mainProjectSaveFile.bin");
    setSystemSaveFile("Project Data Files/" + this.getSystemFileName());

    //Note: lastDataSaveTime and lastWebExportTime are set directly in the writeToBinary() and export() methods when needed, in composition relationship.
    // Until then, they are intentionally null/uninitialized.
  }





  /** <p>Gets the system file object that is currently used when saving project data.</p>
   * @return the File object that the system is currently using when saving project data.
   * @Author: K. Dashnaw
   * */
  public File getSystemSaveFile()
  {
    return systemSaveFile;
  }





  /** <p>Sets the file FILE used for saving the system project data. This is used in combination
   * with the systemFileName (which contains the name of the file to save). </p>
   * @param fileName a String containing the directory/path to which the file should be saved to.
   * @Author: K. Dashnaw
   * */
  public void setSystemSaveFile(String fileName)
  {
    this.systemSaveFile = new File(fileName);
  }





  /** <p>Gets the name used for the system projects file (persistence) </p>
   * @return a String value containing the name used for the system project file.
   * @Author: K. Dashnaw
   * */
  public String getSystemFileName()
  {
    return systemFileName;
  }





  /** <p>Sets the name used for the main project data file.</p>
   * @param systemFileName a String containing the name the system project file should use.
   * @Author: K. Dashnaw
   * */
  public void setSystemFileName(String systemFileName)
  {
    this.systemFileName = systemFileName;
  }





  /** <p>Used for retrieving a file reference to the currently identified webpage file, that the FileIO.export method writes to.</p>
   * @return a File object.
   * @Author: K. Dashnaw
   * */
  public File getWebpageFile()
  {
    return webpageFile;
  }





  /** <p>Sets the file that is used as the system save file, containing all project information.</p>
   * @param file the File object to be written to.
   * @Author: K. Dashnaw
   * */
  public void setWebpageFile(File file)
  {
    this.webpageFile = file;
  }





  /** <p>Gets the the attribute in FileIO documenting on which date the system was last saved.</p>
   * @return A MyDate object
   * @Author: K: Dashnaw
   * */
  public MyDate getLastDataSaveTime()
  {
    return lastDataSaveTime;
  }





  /** <p>Sets the attribute in FileIO documenting on which date the system was last saved.</p>
   * @param lastDataSaveTime A MyDate object containing the last date at which the writeToBinary method was executed.
   * @Author: K: Dashnaw
   * */
  public void setLastDataSaveTime(MyDate lastDataSaveTime)
  {
    this.lastDataSaveTime = lastDataSaveTime;
  }





  /** <p>Gets the last date upon which the export (as HTML) method was called.</p>
   * @return a MyDate object
   * @Author: K: Dashnaw
   * */
  public MyDate getLastWebExportTime()
  {
    return lastWebExportTime;
  }





  /** <p>Sets a MyDate Object containing the last date and time the system files were exported to webpage compatible format</p>
   * @param lastWebExportTime A MyDate() Object must be passed as a parameter.
   * @Author: K: Dashnaw
   * */
  public void setLastWebExportTime(MyDate lastWebExportTime)
  {
    this.lastWebExportTime = lastWebExportTime;
  }





  /** <p>Writes the persistence system data to a local binary file.
   * File references are defined directly in the FileIO constructor.</p>
   * @param objectList is an Object[] array containing the <b>serialized</b> data to be written into a binary file.
   * @return TRUE if data was successfully written to file, or FALSE if not.
   * @Author: K. Dashnaw
   * */
  public boolean writeToBinary(Object[] objectList)
  {
    System.out.println("WriteToBinary method called.");
    //Create the fileOutputStream with "try-with-resources")
    try (FileOutputStream fos = new FileOutputStream(this.getSystemSaveFile()))
    {
      //Create the ObjectOutputStreams, as a "try-with-resources")
      try (ObjectOutputStream out = new ObjectOutputStream(fos))
      {
        //We now write the received Object to the Binary File:
        out.writeObject(objectList);
        out.flush(); //Force it to write the text, emptying the buffer.
        //out.close(); - Not needed since we are using "try-with-resources"

        setLastDataSaveTime(MyDate.now());
        return true; //Data successfully saved.
      }
    }
    catch (IOException e)
    {
      System.out.println("'WriteToBinary' failed.");
      System.out.println("IOException Error: " + e);
      return false; //Write to Binary failed.
    }
  }





  /** <p>Writes the relevant project data needed for proper presistance to a local user defined file for use on the company homepage..
   * This implementation is basically a textFile writer, which requires the received String data to already be formatted into the proper string pattern.
   * Used incorrectly the output may not be a proper json object array.
   * In this application the text formatting is conducted in the MainModel.exportAsJson() method.
   * Prior to calling this method the developer should ensure that FileIo.setWebpageFile(File) is properly defined,
   * since this method simply uses the directory defined here.</p>
   * @param exportData A String containing the data to export.
   * @param fileName A string containing the name the exported JSON file should have.
   * @param fileType A string containing the file type to append after the name. In the MainModel class we use '.json'.
   * @throws FileNotFoundException if any exceptions parsing, transforming, writing or reading.
   * @Author K. Dashnaw
   * */
  public void export(String exportData, String fileName, String fileType) throws FileNotFoundException
  {
    File exportFile = new File(getWebpageFile().getAbsolutePath() + "\\" + fileName + fileType);

    System.out.println("Data exported to: " + getWebpageFile().getAbsolutePath() + "\\" + fileName + fileType);

    //Give the File to the PrintWriter. We use try with resources.
    try (PrintWriter out = new PrintWriter(exportFile))
    {
      //Now we write to the file using
      out.println(exportData);
      out.flush(); //Force it to write the text, emptying the buffer.
      setLastWebExportTime(MyDate.now());
    }
  }





  /** <p>Loads system persistence data from a local binary file.
   * File references are defined directly in the FileIO constructor.</p>
   * @return An Object Array containing all the system projects and related information, or throws a NullPointerException.
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ClassNotFoundException
   * @throws NullPointerException
   * @Author K. Dashnaw
   * */
  public Object[] readFromBinary() throws FileNotFoundException, IOException, ClassNotFoundException, NullPointerException
  {
    System.out.println("Loading data from binary file");

    //Try with resources:
    //1. Create fileInput Stream:
    try(FileInputStream fis = new FileInputStream(getSystemSaveFile()))
    {
      //2. Create ObjectStream:
      ObjectInputStream in = new ObjectInputStream(fis);
      Object[] objectList = (Object[]) in.readObject();
      System.out.println("Data successfully read from Binary.");

      if(objectList == null)
      {
        System.out.println("Data is corrupt!");
        throw new NullPointerException();
      }
      else
      {
        return objectList;
      }
    }
  }

}
