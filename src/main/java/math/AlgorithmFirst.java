package math;

import java.util.function.BiFunction;

import static java.lang.Math.pow;

public class AlgorithmFirst {
    public static void main(String[] args) {
        //Пример 1
        int x = 0;
        double h = 0.1;
        double[] y = {1};
        int n = 1;
        BiFunction<Double, Double, Double> function = (x_i, y_i) -> pow(x_i, 2) - 2 * y_i;
        int upperBound = 1;
        eulerAlgorithm(n, x, y, h, function, upperBound);
//Пример 1
    }

    /**
     * @param n          Порядок системы
     * @param x          Начальное значение x0
     * @param y          Массив из n чисел,содержащий начальное значение y
     * @param h          Начальное значение шага
     * @param p          Имя внешней программы p(X,Y,F) ,вычисляющей значения первых частей уравнения системы по заданным X и Y и размещающих их
     *                   в массиве F,F массив размерности N содержащий значений f(X,Y)
     * @param upperBound верхняя граница диапазона [0,1]
     */

    public static void eulerAlgorithm(int n, double x, double[] y, double h, BiFunction<Double, Double, Double> p, double upperBound) {
        int i = 0;
        double y_value = y[0];
        System.out.println("i    xi       yi       f(xi;yi)    hf(xi;yi)");
        for (x = x; x <= upperBound; x += h) {
            i++;
            Double f = p.apply(x, y_value);
            double hf = f * h;
            System.out.printf("%d   %.3f    %.4f    %.4f    %.4f\n", i, x, y_value, f, hf);
            y_value += hf;
        }

    }

    /**
     * Выходные параметры  алгоритма Эйлера*
     */
    private static class Result {
        /**
         * значение x0+h
         */
        private int x;
        /**
         * массив из n чисел,содержащий приближенное решение в точке x0+h
         */
        private int y;

    }


}