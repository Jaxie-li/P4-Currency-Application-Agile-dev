package currencyConverter.ultils;

import java.util.ArrayList;
import java.util.Collections;

public class Calculator {

    /**
     * method from : <a href="http://www.java2s.com/example/java-utility-method/median/median-arraylist-double-values-82543.html">...</a>
     * Calculate Median in a Arraylist<Double>
     *
     * @param doubleRateList rate list in double type
     * @return median value
     */
    public static double Median(ArrayList<Double> doubleRateList) {
        Collections.sort(doubleRateList);

        if (doubleRateList.size() % 2 == 1)
            return doubleRateList.get((doubleRateList.size() + 1) / 2 - 1);

        else {

            double lower = doubleRateList.get(doubleRateList.size() / 2 - 1);
            double upper = doubleRateList.get(doubleRateList.size() / 2);

            return (lower + upper) / 2.0;
        }
    }

    /**
     * method from : <a href="https://www.programiz.com/java-programming/examples/standard-deviation">...</a>
     * calculate standard deviation in Arraylist<double>
     *
     * @param numArray Arraylist<double> >> rate list
     * @return standard value in double type
     */
    public static String calculateSD(ArrayList<Double> numArray) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.size();

        for (double num : numArray) {
            sum += num;
        }

        double mean = sum / length;

        for (double num : numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        double value = Math.sqrt(standardDeviation / length);
        return String.format("%.4f", value);
    }

}
