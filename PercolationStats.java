public class PercolationStats {
    private double []perSimOpenSites;
    private int totalSims;
    private int gridSize;
    
    public PercolationStats(int N, int T) {
        if ((N <= 0) || (T <= 0)) {
            throw new IllegalArgumentException("No less than 0");
        }
        
        totalSims = T;
        gridSize = N*N;
        perSimOpenSites = new double[totalSims];
        
        for (int i = 0; i < totalSims; i++) {
            perSimOpenSites[i] = 0.0;
        }

    }
    
    public double mean() {
        double sum = 0;
        for (int i = 0; i < totalSims; i++) {
            sum = sum + perSimOpenSites[i];
        }
        return (double) sum/totalSims;
    }
        
    public double stddev() {
            double mean = mean();
            double sum = 0;
            for (int i = 0; i < totalSims; i++) {
                sum = sum + Math.pow(perSimOpenSites[i]-mean, 2);
            }
            sum = sum/(totalSims - 1);
        return Math.sqrt(sum);
    }
    
    public double confidenceLo() { // returns lower bound of the 95% confidence interval
        double mean = mean();
        double stddev = stddev();
        
        return (mean - ((1.96 * stddev)/(Math.sqrt(totalSims))));
    }
    public double confidenceHi() { // returns upper bound of the 95% confidence interval
        double mean = mean();
        double stddev = stddev();
        
        return (mean + ((1.96 * stddev)/(Math.sqrt(totalSims))));
    }
   
    public static void main(String args[]) {
        System.out.println("Hello, World");
        

        
        int length = args.length;
        if (length < 2) {
            System.out.println("You need to enter two arguments.");
            System.exit(0);
        }

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        
        PercolationStats stats = new PercolationStats(N, T);
            
        int numRunsPerSim = 0;
        
        while (T > 0) {
            Percolation simPerc = new Percolation(N);
            numRunsPerSim = 0;
            
            while (!simPerc.percolates())
            {    
            

            
                int randi = StdRandom.uniform(N+1);
                if (randi == 0) {
                    continue;
                }
            
                int randj = StdRandom.uniform(N+1);
                if (randj == 0) {
                    continue;
                }
            
        
                if (!simPerc.isOpen(randi, randj)) {
                    simPerc.open(randi, randj);
                }  
                else {
                    continue;
                }
                numRunsPerSim++;
           }
            stats.perSimOpenSites
[T-1] = (double) numRunsPerSim/stats.gridSize;
            T--;
        }
        
        System.out.println("mean                    = " + stats.mean());  
        System.out.println("stddev                  = " + stats.stddev());  
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());    }
}
    