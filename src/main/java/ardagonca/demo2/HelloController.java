package ardagonca.demo2;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class HelloController implements Initializable {



    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField password;

    @FXML
    private TextField username;
    // Database Tools

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;

    public void loginAdmin () {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();
            Alert alert;

            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hata Mesaji");
                alert.setHeaderText(null);
                alert.setContentText("Lütfen tüm boşlukları doldurunuz.");
                alert.showAndWait();
            } else {
                if (result.next()) {

                    getData.username = username.getText();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Bilgi Mesaji");
                    alert.setHeaderText(null);
                    alert.setContentText("Başarıyla giriş yaptınız.");
                    alert.showAndWait();


                    loginBtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });
                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hata Mesaji");
                    alert.setHeaderText(null);
                    alert.setContentText("Yanlış Kullanıcı Adı ya da Şifre");
                    alert.showAndWait();

                }
            }
        }catch (Exception e) {e.printStackTrace();}
    }
    @FXML
    public void close () {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
