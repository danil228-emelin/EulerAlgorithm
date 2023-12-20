package math;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Runge {

    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Система дифф. уравнений:");
        System.out.println("{(dy_1/dx) = y_2 \n{(dy_2/dx) = -0.01 * Math.exp(0.8*x)\n");

        System.out.println("Начальные условия:");
        System.out.println("На отрезке [0, 3] с шагом h = 0.1");
        System.out.println("{y_1(0) = 0 \n{y_2(0) = 0.5 \n{x_0 = 0");
        System.out.println("------------------------------");
        System.out.println("Решение:");


        List<List<Double>> rkResult = rk(Runge::equations, 0, 3, List.of(0.0, 0.0), 0.1);
        rkResult.forEach(System.out::println);
    }

    private static List<Double> equations(double x, List<Double> y) {
        return List.of(y.get(1), (-0.03 * Math.exp(0.8 * x)));
    }


    private static List<List<Double>> rk(BiFunction<Double, List<Double>, List<Double>> func, double x_0, double xf, List<Double> y_0, double h) {
        int count = (int) Math.floor((xf - x_0) / h) + 1;
        List<List<Double>> y = new ArrayList<>();
        y.add(new ArrayList<>(y_0));

        double x = x_0;
        for (int i = 1; i < count; i++) {
            List<Double> k1 = func.apply(x, y.get(i - 1));
            List<Double> k2 = func.apply(x + h / 2, addLists(y.get(i - 1), multiplyList(k1, h / 2)));
            List<Double> k3 = func.apply(x + h / 2, addLists(y.get(i - 1), multiplyList(k2, h / 2)));
            List<Double> k4 = func.apply(x + h, addLists(y.get(i - 1), multiplyList(k3, h)));

            y.add(new ArrayList<>());
            for (int j = 0; j < y_0.size(); j++) {
                y.get(i).add(y.get(i - 1).get(j) + h / 6 * (k1.get(j) + 2 * k2.get(j) + 2 * k3.get(j) + k4.get(j)));
            }
            x += h;
        }
        return y;
    }

    private static List<Double> addLists(List<Double> list1, List<Double> list2) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            result.add(list1.get(i) + list2.get(i));
        }
        return result;
    }

    private static List<Double> multiplyList(List<Double> list, double scalar) {
        List<Double> result = new ArrayList<>();
        for (Double value : list) {
            result.add(value * scalar);
        }
        return result;
    }

}
