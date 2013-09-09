public class Percolation {
    private int [][]grid;
    private int dim;
   
    private WeightedQuickUnionUF wquf;
    private boolean no_open;
    

    public Percolation() {
        this(20);
        System.out.println("Defaulting to grid size 20x20");
    }
    
    public Percolation(int N) {        // create N-by-N grid, with all sites blocked
        wquf = new WeightedQuickUnionUF(N*N);
        dim = N;
        grid = new int[N+1][N+1];
        no_open = true;
        
   
        for (int i = 1; i<=N; i++) {
            for (int j = 1; j<=N; j++) {
                grid[i][j] = -1;
            }
        }
    }
   
  // A union method is not needed  
 //   public void union(int a, int b) {
 //       head
 //   }
    
        
    
    public void open(int i, int j) {        // open site (row i, column j) if it is not already

        no_open = false;
        grid[i][j] = 0;
        
        int p = i-1;
        int q = j-1;
        
  

        
        

                if (i!= 1) {
                    if (grid[i-1][j] == 0) {
                        wquf.union(p*dim + q, (p-1)*dim + q);
                    }
                }
        
        
                if (i != dim) {
                    if ((grid[i+1][j] == 0)) {
                        wquf.union((p+1)*dim + q, p*dim + q);
                    }
                }
         
                if (j != 1) {
                    if (grid[i][j-1] == 0) {
                        wquf.union(p*dim + q,  p*dim + (q-1));
                    }
                }
        
                if (j!=dim) {
                    if (grid[i][j+1] == 0) {
                        wquf.union(p*dim + q , p*dim + (q+1));
                    }
                }

    }
    
    public boolean isOpen(int i, int j) {    // is site (row i, column j) open?
        if ((i <= 0) || (j <=0) || (i > dim) || (j > dim)) {
            throw new IndexOutOfBoundsException("Array index should be >= 0");
        }
        if (grid[i][j] == 0) {
            return true;
        }
        return false;
    }
    
        public boolean isFull(int i, int j) {    // is site (row i, column j) open?
        if ((i <= 0) || (j <=0) || (i > dim) || (j > dim)) {
            throw new IndexOutOfBoundsException("Array index should be >= 0");
        }
        
        if (no_open == true)
            return false;
        
      
        if (grid[i][j] == -1) {
            return true;
        }
        return false;
    }
    
    public boolean percolates() {            // does the system percolate?
     
        for (int j = 1; j<=dim; j++) {
            for (int i = 1; i<=dim; i++) {
                if (wquf.connected(j, i)) {
                    if (grid[i][j] == -1) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}