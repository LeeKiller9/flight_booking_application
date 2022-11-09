package Export_package;

public class SendViaEmail implements SendTicketMethod{
    @Override
    public void getSendTicketMethod() {
        System.out.println("Send ticket method: Email");
    }
}
