package test;

public class AmExCC extends CreditCard{
    private String expirationDate;
    private String fullName;

    public AmExCC(String cardNumber) {
        super(cardNumber);
    }

    @Override
    public String validateCardType(String cardNumber){
        if(cardNumber.equals("")){
            return "Invalid";
        }
        try {
            String[] parts = cardNumber.split(",\\s*");
            long number = Double.valueOf(parts[0]).longValue();
            String value = Long.toString(number);
            if ((value.charAt(0) == '3') && (value.charAt(1) == '4' || value.charAt(1) == '7')) {
                return "AmericanExpress: " + cardNumber;
            }
        }catch(Exception e){
            return "Unable to instantiate from known credit types: " + cardNumber;
        }
        return "Unable to instantiate from known credit types: " + cardNumber;
    }
}
