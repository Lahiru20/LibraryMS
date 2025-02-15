package formcontroller;

import controller.LoginController;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import model.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txt_username;
    public TextField txt_password;
    public AnchorPane login_form_frame;

    public void Register_on_Action(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) login_form_frame.getScene().getWindow();
        stage.close();
        stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Register.fxml"))));
        stage.setResizable(false);
        stage.setTitle("Register");
        stage.show();
    }



    public void btn_Login_Action(ActionEvent actionEvent) throws SQLException {
        Login User= LoginController.getInstance().getUser(txt_username.getText(),txt_password.getText());
        if(txt_username.getText().isEmpty() || txt_password.getText().isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"All fields are required. Please fill in both username and password.").show();
            return;
        }

        if (User==null){
            new Alert(Alert.AlertType.INFORMATION,"Login Failed. Please check your username and password and try again.").show();
            return;
        }
        Stage stage = (Stage) login_form_frame.getScene().getWindow();
        stage.close();
        stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Dashboard.fxml"))));
            stage.setTitle("Dashboard");
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
