package math;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static java.lang.Math.pow;

public class AlgorithmFirst {
    public static void main(String[] args) {
        //Пример 1
        int x = 0;
        double h = 0.1;
        double[] y = {1};
        int n = 1;
        List<BiFunction<Double, Double, Double>> functions = new ArrayList<>();
        BiFunction<Double, Double, Double> function1 = (x_i, y_i) -> pow(x_i, 2) - 2 * y_i;
        functions.add(function1);
        int upperBound = 1;
        //eulerAlgorithm(n, x, y, h, functions, upperBound);
//Пример 1

        //Пример из лабораторной
        x = 0;
        h = 0.1;
        y = new double[]{0, 0};
        n = 2;
        function1 = (x_0, y_1) -> pow(Math.E, -x_0 * y_1);

        BiFunction<Double, Double, Double> function2 = (x_0, y_2) -> y_2;
        functions = new ArrayList<>();
        functions.add(function1);
        functions.add(function2);

        upperBound = 3;
        eulerAlgorithm(n, x, y, h, functions, upperBound);

        //Пример из лабораторной

    }

    /**
     * @param n          Порядок системы
     * @param x          Начальное значение x0
     * @param y          Массив из n чисел,содержащий начальное значение y
     * @param h          Начальное значение шага
     * @param p          Имя внешней программы p(X,Y,F) ,вычисляющей значения первых частей уравнения системы по заданным X и Y и размещающих их
     *                   в массиве F,F массив размерности N содержащий значений f(X,Y)
     * @param upperBound верхняя граница диапазона
     */

    public static void eulerAlgorithm(int n, double x, double[] y, double h, List<BiFunction<Double, Double, Double>> p, double upperBound) {
        double F[] = new double[n];
        for (x = x + h; x <= upperBound; x += h) {

            for (int l = 0; l < p.size(); l++) {

               //считаем правое значние
                F[l] = p.get(l).apply(x, y[l]);
                //меняем y на шаг.
                for (int k = 1; k < n; k++) {
                    y[l] += h * F[l];
                }
            }
            System.out.printf("X=%.3f   Y(1)=%.3f   Y(2)=%.3f\n",x,y[0],y[1]);
        F[0]=y[1];
        F[1]=p.get(1).apply(x,y[1]);
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