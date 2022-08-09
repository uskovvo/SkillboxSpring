package org.example;

public class DeliveryRobotProdLine implements RoboProductionLine {
    @Override
    public void work() {
        System.out.println("New delivery robot build completed");
    }
}
