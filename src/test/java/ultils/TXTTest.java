package ultils;

import currencyConverter.ultils.ReadDate;
import currencyConverter.ultils.TXT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TXTTest {
    private TXT txt;
    @BeforeEach
    public void init(){
        this.txt = new TXT();
    }
    
    @Test
    public void appliedChangesTest() throws IOException {
        ReadDate todayReadDate = new ReadDate();
        String checkDate = todayReadDate.getDate("Book1.csv");
        assertDoesNotThrow(()->{this.txt.appliedChanges("Changes.txt", "Book1.csv", checkDate);});
        assertDoesNotThrow(()->{this.txt.appliedChanges("Changes2.txt", "Book1.csv", checkDate);});
    }
}
