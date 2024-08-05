package Controller;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ReservaController {

    @FXML
    private ComboBox<String> vooComboBox;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cpfField;
    @FXML
    private TextField fileiraReservaField;
    @FXML
    private TextField assentoReservaField;

    @FXML
    public void saveReserva() {
        String voo = vooComboBox.getValue();
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        int fileira = Integer.parseInt(fileiraReservaField.getText());
        int assento = Integer.parseInt(assentoReservaField.getText());

        // Lógica para salvar a reserva no banco de dados
        // ...

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Reserva salva com sucesso!");
        alert.showAndWait();
    }
}