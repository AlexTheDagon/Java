package queueSimulator.model;

public class SimulationConstraints {
    private int n;
    private int q;
    private int maxSimulation;
    private int minArrival;
    private int maxArrival;
    private int minService;
    private int maxService;

    public int getN() { return n; }
    public void setN(int n) { this.n = n; }

    public int getQ() { return q; }
    public void setQ(int q) { this.q = q; }

    public int getMaxSimulation() { return maxSimulation; }
    public void setMaxSimulation(int maxSimulation) { this.maxSimulation = maxSimulation; }

    public int getMinArrival() { return minArrival; }
    public void setMinArrival(int minArrival) { this.minArrival = minArrival; }

    public int getMaxArrival() { return maxArrival; }
    public void setMaxArrival(int maxArrival) { this.maxArrival = maxArrival; }

    public int getMinService() { return minService; }
    public void setMinService(int minService) { this.minService = minService; }

    public int getMaxService() { return maxService; }
    public void setMaxService(int maxService) { this.maxService = maxService; }

    public SimulationConstraints(int n, int q, int maxSimulation, int minArrival, int maxArrival, int minService, int maxService) {
        this.n = n;
        this.q = q;
        this.maxSimulation = maxSimulation;
        this.minArrival = minArrival;
        this.maxArrival = maxArrival;
        this.minService = minService;
        this.maxService = maxService;
    }

    @Override
    public String toString() {
        return "SimulationConstraints{" +
                "n=" + n +
                ", q=" + q +
                ", maxSimulation=" + maxSimulation +
                ", minArrival=" + minArrival +
                ", maxArrival=" + maxArrival +
                ", minService=" + minService +
                ", maxService=" + maxService +
                '}';
    }
}
