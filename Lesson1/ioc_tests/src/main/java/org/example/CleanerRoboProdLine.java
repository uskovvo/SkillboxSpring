package org.example;

public class CleanerRoboProdLine implements RoboProductionLine {
    @Override
    public void work() {
        System.out.println("New cleaner robot build completed");
    }
}
