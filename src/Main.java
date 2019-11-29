public class Main {
    public static void main(String[] args) {
        HermiteInterpolatingPolynomial polynomial = new HermiteInterpolatingPolynomial();
        double x[] = {-1, 0, 1};
        double fx[][] = {{0,2},{2,4,-4}, {-1, -14}};
        polynomial.printPolynomial(x,fx);
    }
}
