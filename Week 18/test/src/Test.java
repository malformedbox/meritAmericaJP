import test.*;

import java.util.*;

public class Test {
    public static void main(String[] args) {

        List<String> card = new ArrayList<>();
        card.add("5100123412341234,07/22,John Doe");
        card.add("601112341234123,09/23,Jane Doe");
        card.add("6011123412346011,09/23,Jane Doe");

        for(int i = 0; i < card.size(); i++){
            String[] parts = card.get(i).split(",\\s*");
            long number = Double.valueOf(parts[0]).longValue();
            String value = Long.toString(number);

            if (value.length() <= 16) {
                if (value.length() == 15) {
                    AmExCC amExCC = new AmExCC(card.get(i));
                    System.out.println(amExCC.validateCardType(card.get(i)));
                } else if (value.length() == 16 && value.charAt(0) == '5' || value.charAt(0) == '2') {
                    MasterCC masterCC = new MasterCC(card.get(i));
                    System.out.println(masterCC.validateCardType(card.get(i)));
                } else if (value.length() == 16 && value.charAt(0) == '6') {
                    DiscoverCC discoverCC = new DiscoverCC(card.get(i));
                    System.out.println(discoverCC.validateCardType(card.get(i)));
                } else if ((value.length() == 13 || value.length() == 16) && value.charAt(0) == '4') {
                    VisaCC visaCC = new VisaCC(card.get(i));
                    System.out.println(visaCC.validateCardType(card.get(i)));
                } else {
                    System.out.println("Unable to instantiate from known credit types: " + card.get(i));
                }
            }
        }
    }
}