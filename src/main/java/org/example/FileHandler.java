package org.example;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class FileHandler {
    private static final Logger LOGGER = Logger.getLogger(FileHandler.class.getName());

    public static void save(List<? extends Entity> entities, String fileName) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            stream.writeObject(entities);
            LOGGER.log(Level.INFO, "Data saved successfully.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving data.", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<? extends Entity> load(String fileName) {
        List<? extends Entity> entities = List.of();

        File file = new File(fileName);

        if (file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName))) {
                entities = (List<? extends Entity>) stream.readObject();
                LOGGER.log(Level.INFO, "Data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "Error loading data.", e);
            }
        }

        return entities;
    }
}