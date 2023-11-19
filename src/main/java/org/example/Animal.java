package org.example;

import java.util.List;
import java.util.Scanner;

class Animal extends Entity {
    private final String name;
    private final int age;

    public Animal(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String getDescription() {
        return "Animal ID: " + id + ", Name: " + name + ", Age: " + age;
    }

    public void feed(List<Crop> crops) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a crop to feed:");
        for (Crop crop : crops) {
            System.out.println(crop.getDescription());
        }

        int cropId = scanner.nextInt();
        Crop selectedCrop = null;

        for (Crop crop : crops) {
            if (crop.getId() == cropId) {
                selectedCrop = crop;
                break;
            }
        }

        if (selectedCrop != null && selectedCrop.getQuantity() > 0) {
            selectedCrop.decreaseQuantity();
            System.out.println("Fed the animal with " + selectedCrop.getDescription());
        } else {
            System.out.println("No more of the selected crop available to feed.");
        }
    }
}