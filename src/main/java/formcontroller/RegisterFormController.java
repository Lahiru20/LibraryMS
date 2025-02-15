package formcontroller;

import dbconnection.DBConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Login;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterFormController {
    public TextField txt_id;
    public TextField txt_username;
    public TextField txt_Email;
    public TextField txt_Password;
    public TextField txt_conform_Password;
    public AnchorPane register_frame;

    public void btn_Action_Action(ActionEvent actionEvent) {
        if(txt_username.getText().isEmpty() || txt_Email.getText().isEmpty() || txt_Password.getText().isEmpty() || txt_conform_Password.getText().isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"All fields are required.").show();
            return;
        }
        String key ="12345";

        Login basicTextEncryptor = new Login();
        basicTextEncryptor.setPassword(key);

        if (txt_Password.getText().equals(txt_conform_Password.getText())){
            System.out.println(true);
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = null;
            try {
                resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email=" + "'" + txt_Email.getText() + "'");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {

                if (!resultSet.next()){
                    System.out.println(false);

                    String SQL = "INSERT INTO users (username,email,password) VALUES (?,?,?)";
                    PreparedStatement psTm = connection.prepareStatement(SQL);
                    psTm.setString(1,txt_username.getText());
                    psTm.setString(2,txt_Email.getText());
                    psTm.setString(3,txt_Password.getText());
                    psTm.executeUpdate();

                }else{
                    System.out.println(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else {
            System.out.println(false);
        }


    }

    public void Login_Action(ActionEvent actionEvent) {
        Stage stage = (Stage) register_frame.getScene().getWindow();
        stage.close();
        stage=new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Login.fxml"))));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}











