package test;

public class DiscoverCC extends CreditCard{
    private String expirationDate;
    private String fullName;

    public DiscoverCC(String cardNumber) {
        super(cardNumber);
    }

    @Override
    public String validateCardType(String cardNumber){
        if (cardNumber.equals("")){
            return "Invalid";
        }
        try {
            String[] parts = cardNumber.split(",\\s*");
            long number = Double.valueOf(parts[0]).longValue();
            String value = Long.toString(number);
            if (value.substring(0, 4).equals("6011")) {
                return "Discover: " + cardNumber;
            }
        }catch(Exception e) {
            return "Unable to instantiate from known credit types: " + cardNumber;
        }
        return "Unable to instantiate from known credit types: " + cardNumber;
    }
}
