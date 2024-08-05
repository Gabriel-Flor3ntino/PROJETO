package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class AviaoController {

    @FXML
    private TextField modeloField;
    @FXML
    private TextField fileiraField;
    @FXML
    private TextField assentosField;

    @FXML
    public void saveAeronave() {
        String modelo = modeloField.getText();
        int fileiras = Integer.parseInt(fileiraField.getText());
        int assentos = Integer.parseInt(assentosField.getText());

        // Lógica para salvar a aeronave no banco de dados
        // ...

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Aeronave salva com sucesso!");
        alert.showAndWait();
    }
}