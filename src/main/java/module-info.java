module example.crudjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens example.crudjavafx to javafx.fxml;
    exports example.crudjavafx;
}