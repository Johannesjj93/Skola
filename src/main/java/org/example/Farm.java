package org.example;

import java.util.*;

class Farm {
    private List<Animal> animals;
    private List<Crop> crops;

    public Farm() {
        animals = new ArrayList<>();
        crops = new ArrayList<>();
        loadFromFiles();
    }

    public void MainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. View Animals");
            System.out.println("2. Add Animal");
            System.out.println("3. Remove Animal");
            System.out.println("4. View Crops");
            System.out.println("5. Add Crop");
            System.out.println("6. Remove Crop");
            System.out.println("7. Feed Animal");
            System.out.println("8. Save");
            System.out.println("9. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAnimals();
                    break;
                case 2:
                    addAnimal();
                    break;
                case 3:
                    removeAnimal();
                    break;
                case 4:
                    viewCrops();
                    break;
                case 5:
                    addCrop();
                    break;
                case 6:
                    removeCrop();
                    break;
                case 7:
                    feedAnimal();
                    break;
                case 8:
                    save();
                    break;
                case 9:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);
    }

    private void viewAnimals() {
        System.out.println("Animals on the farm:");
        for (Animal animal : animals) {
            System.out.println(animal.getDescription());
        }
    }

    private void addAnimal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Animal ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Animal Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Animal Age:");
        int age = scanner.nextInt();

        Animal newAnimal = new Animal(id, name, age);
        animals.add(newAnimal);
        System.out.println("Animal added successfully.");
    }

    private void removeAnimal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Animal ID to remove:");
        int id = scanner.nextInt();

        animals.removeIf(animal -> animal.getId() == id);
        System.out.println("Animal removed successfully.");
    }

    private void viewCrops() {
        System.out.println("Crops on the farm:");
        for (Crop crop : crops) {
            System.out.println(crop.getDescription());
        }
    }

    private void addCrop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Crop ID:");
        int id = scanner.nextInt();

        Crop existingCrop = getCropById(id);

        if (existingCrop != null) {
            System.out.println("Crop with ID " + id + " already exists.");
            System.out.println("Enter quantity to add:");
            int quantityToAdd = scanner.nextInt();
            existingCrop.increaseQuantity(quantityToAdd);
            System.out.println("Quantity updated successfully.");
        } else {
            scanner.nextLine();
            System.out.println("Enter Crop Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Crop Quantity:");
            int quantity = scanner.nextInt();

            Crop newCrop = new Crop(id, name, quantity);
            crops.add(newCrop);
            System.out.println("Crop added successfully.");
        }
    }

    private void removeCrop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Crop ID to remove:");
        int id = scanner.nextInt();

        crops.removeIf(crop -> crop.getId() == id);
        System.out.println("Crop removed successfully.");
    }

    private void feedAnimal() {
        Scanner scanner = new Scanner(System.in);

        if (crops.isEmpty() || animals.isEmpty()) {
            System.out.println("No animals or crops available to feed.");
            return;
        }

        System.out.println("Choose an animal to feed:");
        for (Animal animal : animals) {
            System.out.println(animal.getDescription());
        }

        int animalId = scanner.nextInt();
        Animal selectedAnimal = getAnimalById(animalId);

        if (selectedAnimal != null) {
            selectedAnimal.feed(crops);
        } else {
            System.out.println("Animal with ID " + animalId + " not found.");
        }
    }

    private void save() {
        FileHandler.save(animals, "animals.dat");
        FileHandler.save(crops, "crops.dat");
    }

    private void loadFromFiles() {
        animals = castList(Animal.class, FileHandler.load("animals.dat"));
        crops = castList(Crop.class, FileHandler.load("crops.dat"));

        if (animals.isEmpty() || crops.isEmpty()) {
            createDefaultData();
        }
    }

    private <T> List<T> castList(Class<? extends T> clazz, List<?> list) {
        List<T> result = new ArrayList<>();
        for (Object obj : list) {
            result.add(clazz.cast(obj));
        }
        return result;
    }

    private void createDefaultData() {
        animals.add(new Animal(1, "Cow", 3));
        animals.add(new Animal(2, "Sheep", 2));

        crops.add(new Crop(1, "Wheat", 10));
        crops.add(new Crop(2, "Corn", 15));
    }

    private Animal getAnimalById(int id) {
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }

    private Crop getCropById(int id) {
        for (Crop crop : crops) {
            if (crop.getId() == id) {
                return crop;
            }
        }
        return null;
    }
}