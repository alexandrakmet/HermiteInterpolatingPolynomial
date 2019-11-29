public class HermiteInterpolatingPolynomial {

    private double[][] dividedDiff;
    private int polynomialDegree = -1;
    double[][] diffTable;


    public void printPolynomial(double[] x, double[][] fx) {
        calculateDividedDiff(x, fx);

        StringBuilder polynomial = new StringBuilder();

        polynomial.append(dividedDiff[0][1]);
        if (dividedDiff[0].length > 2) {
            if (dividedDiff[0][2] > 0)
                polynomial.append(" + ");
            else
                polynomial.append(" - ");
        }

        for (int i = 1; i < dividedDiff.length; i++) {
            if(dividedDiff[0][(i + 1)] != 0){
            polynomial.append(Math.abs(dividedDiff[0][(i + 1)]));
            for (int j = 0; j < i; j++) {
                polynomial.append("(x");
                if (dividedDiff[j][0] > 0)
                    polynomial.append(" - ");
                else if (dividedDiff[j][0] < 0)
                    polynomial.append(" + ");

                if (dividedDiff[j][0] != 0)
                    polynomial.append(Math.abs(dividedDiff[j][0]));

                polynomial.append(")");
            }

            if ((i + 1) != dividedDiff.length) {
                if (dividedDiff[0][(i + 2)] > 0)
                    polynomial.append(" + ");
                else
                    polynomial.append(" - ");

            }}
        }
        System.out.println("Hermite Interpolating Polynomial: ");
        System.out.println(polynomial.toString());
    }

    public void calculateDividedDiff(double[] x, double[][] fx) {
        for (double[] row : fx) {
            polynomialDegree += row.length;
        }

        dividedDiff = new double[polynomialDegree + 1][];
        diffTable = new double[polynomialDegree + 1][];

        int row = 0;
        for (int j = 0; j < fx.length; j++) {
            for (int k = 0; k < fx[j].length; k++) {
                dividedDiff[row] = new double[polynomialDegree + 2 - row];
                dividedDiff[row][0] = x[j];
                dividedDiff[row][1] = fx[j][0];

                diffTable[row] = fx[j];
                row++;
            }
        }

        for (int j = 2; j < polynomialDegree + 2; j++) {
            for (int i = 0; i < polynomialDegree + 2 - j; i++) {
                if (dividedDiff[i + (j - 1)][0] - dividedDiff[i][0] == 0)
                    dividedDiff[i][j] = diffTable[i][j - 1] / factorial(j - 1);
                else
                    dividedDiff[i][j] = (dividedDiff[i + 1][j - 1] - dividedDiff[i][j - 1]) / (dividedDiff[i + (j - 1)][0] - dividedDiff[i][0]);
            }
        }
    }

    int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

}
