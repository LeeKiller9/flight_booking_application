package Export_package;

public class CreditDebitCard implements PayMethod {
    @Override
    public void getPayMethod() {
        System.out.println("Pay method: Credit Card");
    }
}
