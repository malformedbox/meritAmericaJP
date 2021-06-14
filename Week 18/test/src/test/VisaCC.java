package test;

public class VisaCC extends CreditCard{
    private String expirationDate;
    private String fullName;

    public VisaCC(String cardNumber) {
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
            if ((value.charAt(0) == '4') && (value.length() == 13 || value.length() == 16)) {
                return "Visa: " + cardNumber;
            }
        }catch(Exception e){
            return "Unable to instantiate from known credit types: " + cardNumber;
        }
        return "Unable to instantiate from known credit types: " + cardNumber;
    }
}
