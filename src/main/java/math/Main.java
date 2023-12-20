package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class Main {

    public static void main(String[] args) {
        System.out.println("Система дифф. уравнений:");
        System.out.println("{(dy_1/dx) = y_2 \n{(dy_2/dx) = -0.01 * e^(0.8x)\n");

        System.out.println("Начальные условия:");
        System.out.println("На отрезке [0, 3] с шагом h = 0.1");
        System.out.println("{y_1(0) = 0 \n{y_2(0) = 0.25 \n{x_0 = 0");
        System.out.println("Решение:");

        List<List<Double>> result = eulerFunction(Main::equations, 0, 3, Arrays.asList(0.0, 0.0), 0.1);
        result.forEach(System.out::println);
    }

    private static List<Double> equations(double x, List<Double> y) {
        return Arrays.asList(y.get(1), (-0.03 * Math.exp(0.8 * x)));
    }

    private static List<List<Double>> eulerFunction(BiFunction<Double, List<Double>, List<Double>> func, double x_0, double xf, List<Double> y_0, double h) {
        int count = (int) Math.floor((xf - x_0) / h) + 1;
        List<List<Double>> y = new ArrayList<>();
        y.add(new ArrayList<>(y_0));

        double x = x_0;
        for (int i = 1; i < count; i++) {
            List<Double> rightParts = func.apply(x, y.get(i - 1));
            y.add(new ArrayList<>());
            for (int j = 0; j < y_0.size(); j++) {
                y.get(i).add(y.get(i - 1).get(j) + h * rightParts.get(j));
            }
            x += h;
        }
        return y;
    }

}
