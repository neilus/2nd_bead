package hu.elte.progtech1.cwjkl1;

public class RubikMatrix {
    protected int[][] rubik;

    /**
     * Create an NxN Matrix for a Rubik's Table
     * @param n  0 <= n <= 127
     */
    public RubikMatrix(int n){
        if(n > 0) {
            rubik = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    rubik[i][j] = modColor(n, i, j);
                }
            }
        }
    }

    public RubikMatrix(){
        this(4);
    }

    /**
     * Generate a pseudo-random number in the interval [0; n[
     * @param n
     * @param i
     * @param j
     * @return
     */
    private int modColor(int n, int i, int j){
        return (i + j) % n;
    }
    /**
     *
     * @return The dimension of the matrix, 0 <= N <= 127
     */
    public int getSize() {
        return rubik.length;
    }

    /**
     *  Shift the xth column down, and cycle the
     *  bottom element to the top
     * @param x the column that should be cycled in [0, getSize)
     */
    public void shiftDown(int x){
        int shift = rubik[x][getSize() - 1];
        for(int i = (getSize() - 1); i > 0; i--){
            rubik[x][i] = rubik[x][i - 1];
        }
        rubik[x][0] = shift;
    }

    /**
     *  Shift the xth column up, and cycle the
     *  top element to the bottom
     * @param x the column that should be cycled in [0, getSize)
     */
    public void shiftUp(int x){
        int shift = rubik[x][0];
        for(int i = 0; i < (getSize() - 1); i++){
            rubik[x][i] = rubik[x][i + 1];
        }
        rubik[x][getSize() - 1] = shift;
    }

    /**
     *  Shift the yth column right, and cycle the
     *  right element to the left
     * @param y the row that should be cycled in [0, getSize)
     */
    public void shiftRight(int y) {
        int shift = rubik[getSize() - 1][y];
        for(int j= (getSize() - 1); j > 0; j--) {
            rubik[j][y] = rubik[j - 1][y];
        }
        rubik[0][y] = shift;
    }

    /**
     *  Shift the yth column left, and cycle the
     *  left element to the right
     * @param y the row that should be cycled in [0, getSize)
     */
    public void shiftLeft(int y){
        int shift = rubik[0][y];
        for(int j = 0; j < (getSize() - 1); j++){
            rubik[j][y] = rubik[j + 1][y];
        }
        rubik[getSize() - 1][y] = shift;
    }
}
