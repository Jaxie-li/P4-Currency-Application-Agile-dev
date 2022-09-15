package ultils;

import currencyConverter.ultils.ReadDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 15/09/2022 3:52 am
 * @description: Read Data TestCase
 */
public class ReadDataTest {
    private ReadDate readDate;
    @BeforeEach
    public void init(){
        this.readDate = new ReadDate();
    }

    @Test
    public void constructorTest(){
        assertNotNull(this.readDate);
    }
    
    @Test
    public void readCSVTest() throws IOException {
//        assertDoesNotThrow(()->this.readDate.readCSV("Book1.csv"));
//        assertEquals("30/09/2022",this.readDate.readCSV("Book1.csv"));
//        assertEquals("Get date failed",this.readDate.readCSV("Book2.csv"));

    }
    @Test
    public void convertDateTest(){
        assertEquals("14/09/2022",this.readDate.convertDate("2022-09-14"));
    }
}
