package test;

public abstract class CreditCard {
    private String cardNumber;
    private String expirationDate;
    private String fullName;

    public CreditCard(String cardNumber){
        this.cardNumber = cardNumber;
    }

    abstract String validateCardType(String cardNumber);

    public String extractExpirationDate(String cardNumber){
        String[] parts = cardNumber.split(",\\s*");

        return this.expirationDate = parts[2];
    }
    public String extractFullname(String cardNumber){
        String[] parts = cardNumber.split(",\\s*");

        return this.fullName = parts[1];
    }
}
