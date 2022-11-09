package Export_package;

public class PayPal implements PayMethod {
    @Override
    public void getPayMethod() {
        System.out.println("Pay method: PayPal");
    }
}
