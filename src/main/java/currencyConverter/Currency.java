package currencyConverter;


public class Currency {

    private String From;
    private String CNY;

    private String AUD;
    private String JPY;
    private String USD;
    private String GBP;
    private String CAD;
    private String Date;


    public Currency() {
    }

    public Currency(Currency currency) {
        this.From = currency.From;
        this.AUD = currency.AUD;
        this.CNY = currency.CNY;
        this.JPY = currency.JPY;
        this.USD = currency.USD;
        this.GBP = currency.GBP;
        this.CAD = currency.CAD;
        this.Date = currency.Date;
    }


    public Currency(String From, String AUD, String CNY, String JPY, String USD, String GBP, String CAD, String Date) {
        this.From = From;
        this.AUD = AUD;
        this.CNY = CNY;
        this.JPY = JPY;
        this.USD = USD;
        this.GBP = GBP;
        this.CAD = CAD;
        this.Date = Date;
    }

    public String getFrom() {
        return From;
    }

    public String getCAD() {
        return CAD;
    }

    public String getCNY() {
        return CNY;
    }

    public String getDate() {
        return Date;
    }

    public String getGBP() {
        return GBP;
    }

    public String getJPY() {
        return JPY;
    }

    public String getUSD() {
        return USD;
    }

    public void setCAD(String CAD) {
        this.CAD = CAD;
    }

    public void setCNY(String CNY) {
        this.CNY = CNY;
    }

    public void setFrom(String From) {
        From = From;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setGBP(String GBP) {
        this.GBP = GBP;
    }

    public void setJPY(String JPY) {
        this.JPY = JPY;
    }

    public void setUSD(String USD) {
        this.USD = USD;
    }

    public String getAUD() {
        return this.AUD;
    }

    public void setAUD(String AUD) {
        this.AUD = AUD;
    }

    @Override
    public Currency clone() throws CloneNotSupportedException {
        return (Currency) super.clone();
    }
}
