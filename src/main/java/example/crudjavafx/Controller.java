package example.crudjavafx;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Alert;
        import javafx.scene.control.TextField;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.SQLException;

public class Controller {
    static final String DB_URL = "jdbc:mysql://localhost/crud";
    static final String USER = "root";
    static final String PASS = "gilmar2003";

    @FXML
    private TextField apellidoIngreso;

    @FXML
    private TextField codigoIngreso;

    @FXML
    private TextField edadIngreso;

    @FXML
    private TextField nombreIngreso;

    @FXML
    void crearBoton(ActionEvent event) {
        String codigo = codigoIngreso.getText();
        String nombre = nombreIngreso.getText();
        String apellido = apellidoIngreso.getText();
        String edad = edadIngreso.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO datos (codigo, nombre, apellido, edad) VALUES (?, ?, ?, ?)");

            pstmt.setInt(1, Integer.parseInt(codigo));
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido);
            pstmt.setInt(4, Integer.parseInt(edad));

            pstmt.executeUpdate();

            showAlert("Registro guardado correctamente.", Alert.AlertType.INFORMATION);

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error al guardar el registro.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void actualizarBoton(ActionEvent event) {

    }

    @FXML
    void buscarBoton(ActionEvent event) {

    }

    @FXML
    void eliminarBoton(ActionEvent event) {
        String codigo = codigoIngreso.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM datos WHERE codigo = ?");

            pstmt.setInt(1, Integer.parseInt(codigo));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Registro eliminado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("No se encontró ningún registro con el código proporcionado.", Alert.AlertType.WARNING);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error al eliminar el registro.", Alert.AlertType.ERROR);
        }
    }
}



