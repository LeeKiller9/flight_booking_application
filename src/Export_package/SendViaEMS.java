package Export_package;

public class SendViaEMS implements SendTicketMethod{

    @Override
    public void getSendTicketMethod() {
        System.out.println("Send ticket method: EMS");
    }
}
