package ultils;

import currencyConverter.ultils.CSV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @version v1.0
 * @author: Kathrine Xu
 * @date: Created in 15/09/2022 2:52 am
 * @description: CSV Ultils Test
 */
public class CSVTest {
    CSV csv;

    @BeforeEach
    public void init() throws IOException {
        this.csv = new CSV("Book1.csv");
    }

    @Test
    public void constructTest(){
        assertNotNull(this.csv);
        assertThrows(NullPointerException.class,()->new CSV(null));
        assertThrows(FileNotFoundException.class,()->new CSV("SOFT2412isMagic.tmd"));
    }
}
