package test;

public class MasterCC extends CreditCard{
    private String expirationDate;
    private String fullName;

    public MasterCC(String cardNumber) {
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
            if (value.charAt(1) == '1' || value.charAt(1) == '2' ||
                    value.charAt(1) == '3' || value.charAt(1) == '4' || value.charAt(1) == '5' && cardNumber.length() == 16) {
                return "MasterCard: " + cardNumber;
            }
        }catch(Exception e){
            return "Unable to instantiate from known credit types: " + cardNumber;
        }
        return "Unable to instantiate from known credit types: " + cardNumber;
    }
}
