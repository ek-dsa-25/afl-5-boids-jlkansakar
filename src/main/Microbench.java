package main;

import main.simulation.FlockSimulation;
import main.model.BoidType;
import main.spatial.*;

public class Microbench {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private static final int NUM_BOIDS = 2000;

    private static final int WARMUP_STEPS = 50;

    private static final int MEASURE_STEPS = 200;

    private static final int NEIGHBOR_RADIUS = 60;


    public static void main(String[] args) {
        System.out.println("Starting microbench...");
        System.out.printf("Boids=%d, warmup=%d, measured steps=%d, neighbor radius=%d%n",
                NUM_BOIDS, WARMUP_STEPS, MEASURE_STEPS, NEIGHBOR_RADIUS);

        benchmarkIndex("NaiveSpatialIndex", new NaiveSpatialIndex());
        benchmarkIndex("KDTreeSpatialIndex", new KDTreeSpatialIndex());
        benchmarkIndex("QuadTreeSpatialIndex", new QuadTreeSpatialIndex(WIDTH, HEIGHT));
        benchmarkIndex("SpatialHashIndex", new SpatialHashIndex(WIDTH, HEIGHT, 60));
    }

    private static void benchmarkIndex(String name, SpatialIndex index) {
        System.out.println("\n---" + name + "---");

        FlockSimulation simulation = new FlockSimulation(WIDTH, HEIGHT);
        simulation.setSpatialIndex(index);
        simulation.setNeighborRadius(NEIGHBOR_RADIUS);

        for (int i = 0; i < WARMUP_STEPS; i++) {
            simulation.update();
        }

        long start = System.nanoTime();
        for (int i = 0; i < MEASURE_STEPS; i++) {
            simulation.update();
        }
        long end = System.nanoTime();

        double totalMs = (end - start) / 1_000_000.0;
        double avgMs = totalMs / MEASURE_STEPS;

        System.out.printf("%s: total = %.3f ms, avg per step = %.4f ms%n",
                name, totalMs, avgMs);
    }
}
