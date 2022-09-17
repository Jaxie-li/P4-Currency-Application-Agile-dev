package currencyConverter.components;

/**
 * @author Katherine Xu
 */
public class PopularCurrency {
    private String from;
    private String currencyA;
    private String currencyB;
    private String currencyC;
    private String currencyD;

    public PopularCurrency(String[] arr) {
        this.from = arr[0];
        this.currencyA = generateString(arr[1]);
        this.currencyB = generateString(arr[2]);
        this.currencyC = generateString(arr[3]);
        this.currencyD = generateString(arr[4]);
    }

    public String generateString(String s) {
        if (s.contains(":D") || s.contains(":I")) return s;
        return s.substring(0, s.length()-2);
    }

    public String getFrom() {
        return from;
    }

    public String getCurrencyA() {
        return currencyA;
    }

    public String getCurrencyB() {
        return currencyB;
    }

    public String getCurrencyC() {
        return currencyC;
    }

    public String getCurrencyD() {
        return currencyD;
    }

    public String toString() {
        return String.format("from:%s, a:%s, b:%s, c:%s, d:%s\n", from, currencyA, currencyB, currencyC, currencyD);
    }
}
