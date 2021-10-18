package com.dognamegenerator;

import com.dognamegenerator.appfunctionality.DogNameGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;


public class Controller {
    private DogNameGenerator generator;
    @FXML
    private TextField dogName;

    @FXML
    private ListView<Character> alphabetListView;

    @FXML
    ToggleGroup group;

    public void initialize() {
        generator = new DogNameGenerator();
        alphabetListView.setItems(generator.getAlphabet());
    }

    @FXML
    public void generateDogName() throws IOException {
        String firstLetter = selectLetter();
        String name = "";
        String toggleGroupValue = "";
        RadioButton selectedButton = (RadioButton) group.getSelectedToggle();

        if (selectedButton != null) {
            toggleGroupValue =  selectedButton.getText();
        }


        if (!firstLetter.equals("") && !toggleGroupValue.isBlank()) {
            if (toggleGroupValue.equals("Female")) {
                name = generator.generateDogName('F', firstLetter);
            } else if (toggleGroupValue.equals("Male")) {
                name = generator.generateDogName('M', firstLetter);
            }
        } else if (firstLetter.equals("") && !toggleGroupValue.isBlank()) {
            if (toggleGroupValue.equals("Female")) {
                name = generator.generateDogName('F');
            } else if (toggleGroupValue.equals("Male")) {
                name = generator.generateDogName('M');
            }
        } else if (!firstLetter.equals("") &&  toggleGroupValue.isBlank()) {
            name = generator.generateDogName(firstLetter);
        } else {
            name = generator.generateDogName();
        }


        dogName.setText(name);
    }

    @FXML
    public String selectLetter() {
        return alphabetListView.getSelectionModel().isEmpty() ? "" : Character.toString(alphabetListView.getSelectionModel().getSelectedItem());
    }
}
