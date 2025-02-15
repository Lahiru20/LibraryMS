package formcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomepageFormController {
    public AnchorPane homepage_frame;

    public void Lets_Go_Action(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) homepage_frame.getScene().getWindow();
        stage.close();
        stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")))));
        stage.show();
        stage.setTitle("Login Form");

    }
}
