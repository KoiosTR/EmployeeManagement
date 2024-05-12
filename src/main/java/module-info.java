module ardagonca.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ardagonca.demo2 to javafx.fxml;
    exports ardagonca.demo2;
}