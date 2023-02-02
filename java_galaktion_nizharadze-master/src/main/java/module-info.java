module com.example.java_galaktion_nizharadze{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens com.example.java_galaktion_nizharadze to javafx.fxml;
    exports com.example.java_galaktion_nizharadze;
}