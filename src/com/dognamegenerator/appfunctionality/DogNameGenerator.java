package com.dognamegenerator.appfunctionality;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DogNameGenerator {
    private ArrayList<DogName> names;
    private final ObservableList<Character> alphabet;

    public DogNameGenerator() {
        names = new ArrayList<>();
        this.alphabet = FXCollections.observableArrayList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    }

    public ArrayList<DogName> initializeDogNames() throws IOException {
        return readNamesFromFiles();
    }

    public String generateDogName(char sex, String firstLetter) throws  IOException {
        names = initializeDogNames();
        names = filterBasedOnSex(names, sex);
        names = filterBasedOnfirstLetter(names, firstLetter);
        return names.get(generateRandomNumber(names.size())).getName();
    }

    public String generateDogName(char sex) throws IOException {
        names = filterBasedOnSex(initializeDogNames(), sex);
        return names.get(generateRandomNumber(names.size())).getName();
    }

    public String generateDogName(String firstLetter) throws IOException {
        names = filterBasedOnfirstLetter(initializeDogNames(), firstLetter);
        return names.get(generateRandomNumber(names.size())).getName();
    }

    public String generateDogName() throws  IOException {
        names = initializeDogNames();
        return names.get(generateRandomNumber(names.size())).getName();
    }

    public  ObservableList<Character> getAlphabet() {
        return this.alphabet;
    }

    public ArrayList<DogName> readNamesFromFiles() throws IOException {
        String file1 = "C:\\Users\\obert\\IdeaProjects\\Random Dog Name Generator\\src\\dognames.txt";
        String file2 = "C:\\Users\\obert\\IdeaProjects\\Random Dog Name Generator\\src\\girldognames.txt";
        ArrayList<DogName> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            while (reader.ready()) {
                result.add(new DogName(reader.readLine(), 'M'));
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file2))) {
            while (reader.ready()) {
                result.add(new DogName(reader.readLine(), 'F'));
            }
        }

        return result;
    }

    public ArrayList<DogName> filterBasedOnSex(ArrayList<DogName> names, char sex) {
        return sex == 'F' ? names.stream().filter(n -> n.getSex() == 'F').collect(Collectors.toCollection(ArrayList<DogName>::new))
                :names.stream().filter(n -> n.getSex() == 'M').collect(Collectors.toCollection(ArrayList<DogName>::new));
    }

    public ArrayList<DogName> filterBasedOnfirstLetter(ArrayList<DogName> names,String firstLetter) {
        return names.stream().filter(s -> s.getName().startsWith(firstLetter)).collect(Collectors.toCollection(ArrayList::new));
    }

    public int generateRandomNumber(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

}

