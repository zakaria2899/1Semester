package sep1a233group.bobsprojectmanagementsystem;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;






/** <p>This is the main class which is responsible for launching the Project Management application. It does little more than provide a clean start for the application.</p>
 * @Author: Combined group effort */
public class Main extends Application
{
  private SceneController sceneController;






  /** <p>This is the main method which is responsible for launching the Project Management application. It does little more than provide a clean start for the application.</p>
   * @Author: Combined group effort */
  public static void main(String[] args)
  {
    launch();
  }






  /** <p>The main entry point for all JavaFX applications. The start method is called after the init method has returned, and after the system is ready for the application to begin running.
   NOTE: This method is called on the JavaFX Application Thread.</p>
   @param activeStage the primary stage for this application, onto which the application scene can be set. Applications may create other stages, if needed, but they will not be primary stages.
   @throws Exception if something goes wrong*/
  @Override public void start(Stage activeStage) throws IOException
  {
    //Scene controller:
    MainModel activeModel = new MainModel();
    sceneController = new SceneController(activeModel, activeStage);

    //Launch the GUI at the Dashboard Scene:
    sceneController.loadNewWindow("Dashboard");
  }
}
