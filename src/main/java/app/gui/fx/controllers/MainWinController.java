package app.gui.fx.controllers;

import app.data.FormulaCNF;
import app.log.DataProcessing;
import app.log.DimacsReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MainWinController {
    @FXML
    private Button startBut;
    @FXML
    private Button cleanBut;
    @FXML
    private Button saveBut;
    @FXML
    private Button openBut;
    @FXML
    private TextArea inArea;
    @FXML
    private TextArea outArea;
    private Desktop desktop = Desktop.getDesktop();
    private final List<String> typesFile = Arrays.asList("txt");

    public MainWinController() {
    }

    @FXML
    private void startProcess(ActionEvent event) throws IOException {
        if (inArea.getText().trim().equals("")) {
            openErrorWin("Ошибка", "Пустое окно ввода", "Заполните окно ввода данными в формате Dimacs");
        } else {
            try {
                DimacsReader reader = new DimacsReader(inArea.getText().split("\n"));
                FormulaCNF formulaCNF = reader.parse(reader.getInput());
                DataProcessing processing = new DataProcessing(formulaCNF);
                outArea.setText("" + processing.dpll(processing.getModel(), processing.getSymbols(), processing.getClauseSet()));
            } catch (Throwable throwable) {
                openErrorWin("Ошибка", "Ошибка ввода", "Введите данные в формате Dimacs");
            }
        }
    }

    @FXML
    private void cleanOff(ActionEvent event) {
        inArea.setText("");
        outArea.setText("");
    }

    @FXML
    private void openFile(ActionEvent event) throws IOException {
        inArea.clear();
        Stage stage = (Stage) saveBut.getScene().getWindow();
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            String nameFile = file.getPath();
            int dotIndex = nameFile.lastIndexOf('.');
            String typeFile = (dotIndex == -1) ? "" : nameFile.substring(dotIndex + 1);

            if (typesFile.contains(typeFile)) {
                readFile(nameFile);
            } else {
                openErrorWin("Ошибка", "Неверный формат файла",
                        "Вы пытаетесь открыть файл нетекстового формата." +
                                " Текстовые форматы, которые может открыть программа: \"txt\"");
            }
        }/* else {
            openErrorWin("Ошибка", "Файл не найден.",
                    "Файл, который вы пытаетесь открыть не существует");
        }*/
    }

    private void readFile(String nameFile) throws IOException {
        List<String> listStringFile = Files.readAllLines(Paths.get(nameFile), StandardCharsets.UTF_8);
        for (String s: listStringFile) {
            inArea.appendText(s + "\n");
        }
    }

    private void openErrorWin (String title, String headerText, String ContentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(ContentText);

        alert.showAndWait();
    }

    @FXML
    public void openHelp() throws IOException {
        /*Parent root1 = FXMLLoader.load(getClass().getResource("/HelpWindow.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.NONE);
        stage.setTitle("Help");
        stage.setResizable(false);
        stage.setScene(new Scene(root1));
        stage.show();*/

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information for Application");

        alert.setHeaderText(null);

        alert.setContentText("Приложение предназначено для обработки логических формул в формате ввода Dimacs.\n" +
                "\n Пример ввода: \n" +
                        "c A sample .cnf file.\n" +
                        "p cnf 3 2\n" +
                        "1 -3 0\n" +
                        "2 3 -1 0 "
                );

        alert.showAndWait();
    }
}
