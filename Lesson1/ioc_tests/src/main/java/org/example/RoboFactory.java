package org.example;

public class RoboFactory {

    private final RoboProductionLine productionLine;
    final int productionSize;

    public RoboFactory(RoboProductionLine productionLine, int productionSize) {
        this.productionLine = productionLine;
        this.productionSize = productionSize;
    }

    public void runProduction(){
        int i = 0;
        while (i < productionSize){
            productionLine.work();
            i += 1;
        }
    }
}
