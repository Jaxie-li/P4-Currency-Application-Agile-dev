package ultils;

import currencyConverter.ultils.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator testCalculator;

    @BeforeEach
    public void init() {
        this.testCalculator = new Calculator();
    }

    @Test
    public void medianTest() {
        ArrayList<Double> actualDoubleRateList1 = new ArrayList<>(Arrays.asList(4.6, 4.7, 4.7, 4.8));
        ArrayList<Double> actualDoubleRateList2 = new ArrayList<>(Arrays.asList(4.6, 4.7, 4.7, 4.7, 4.8));
        double median1 = testCalculator.Median(actualDoubleRateList1);
        double median2 = testCalculator.Median(actualDoubleRateList2);
        assertEquals(4.7, median1);
        assertEquals(4.7, median2);
    }
}