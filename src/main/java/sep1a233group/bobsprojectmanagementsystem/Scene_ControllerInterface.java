package sep1a233group.bobsprojectmanagementsystem;

/**<p>This interface provides basic navigability between the various .fxml scenes. It is tied to the SceneController, and is implemented by all the GUI related scene control models </p>*/
public interface Scene_ControllerInterface
{
  /** <p>This method initiates the scene/stage it is called on and ties it to the mapping done in the SceneController,
   * thus allowing the overall SceneController to know about this active stage/scene.<br>It is only run on the first initialization.</p>
   * @param activeModel a MainModel Object reference attached to each scene. It allows the scene to call methods from the model to perform operations.
   * @param sceneController a reference to the overall responsible SceneController, which ties all the sub-scenes/stages together.
   * */
  void init(MainModel activeModel, SceneController sceneController);


  /**<p>This method is called every time this scene/stage becomes active. It is used to refresh onscreen data. </p>*/
  void refresh();
}
