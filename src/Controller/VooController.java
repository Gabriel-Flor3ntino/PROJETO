package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VooController {

    @FXML
    private ComboBox<String> aviaoComboBox;
    @FXML
    private TextField numeroVooField;
    @FXML
    private TextField dataField;
    @FXML
    private TextField horaField;

    @FXML
    public void saveVoo() {
        String aviao = aviaoComboBox.getValue();
        String numeroVoo = numeroVooField.getText();
        String data = dataField.getText();
        String hora = horaField.getText();

        // Lógica para salvar o voo no banco de dados
        // ...

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Voo salvo com sucesso!");
        alert.showAndWait();
    }
}