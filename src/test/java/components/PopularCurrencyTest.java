package components;

import currencyConverter.components.PopularCurrency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PopularCurrencyTest {
    PopularCurrency popularCurrency;
    final String[] ARR = new String[]{"from:AUD", "a:1", "b:4.7619", "c:0.89", "d:0.68"};

    @BeforeEach
    public void init(){
        this.popularCurrency = new PopularCurrency(ARR);
    }

    @Test
    public void initTest(){
        assertNotNull(this.popularCurrency);
    }
    
    @Test
    public void generateStringTest(){
        assertEquals(":D",this.popularCurrency.generateString(":D"));
        assertEquals(":I",this.popularCurrency.generateString(":I"));
        assertEquals("MAGIC_COMPUTER",this.popularCurrency.generateString("MAGIC_COMPUTER_SCIENCE"));
    }
    @Test
    public void getFromTest(){
        assertEquals("from:AUD",this.popularCurrency.getFrom());
    }

    @Test
    public void getCurrencyATest(){
        assertEquals("a",this.popularCurrency.getCurrencyA());
    }
    @Test
    public void getCurrencyBTest(){
        assertEquals("b:4.76",this.popularCurrency.getCurrencyB());
    }
    @Test
    public void getCurrencyCTest(){
        assertEquals("c:0.",this.popularCurrency.getCurrencyC());
    }
    @Test
    public void getCurrencyDTest(){
        assertEquals("d:0.",this.popularCurrency.getCurrencyD());
    }

    @Test
    public void toStringTest(){
        assertEquals("from:from:AUD, a:a, b:b:4.76, c:c:0., d:d:0.\n",this.popularCurrency.toString());
    }


}