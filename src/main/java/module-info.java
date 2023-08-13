module example.crudjavafx {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens example.crudjavafx to javafx.fxml;
    exports example.crudjavafx;
}