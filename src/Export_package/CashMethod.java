package Export_package;

public class CashMethod implements PayMethod{
    @Override
    public void getPayMethod() {
        System.out.println("Pay method: Cash");
    }
}
