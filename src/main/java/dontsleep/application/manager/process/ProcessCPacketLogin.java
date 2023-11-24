package dontsleep.application.manager.process;

import dontsleep.application.packet.CPacket.CPacketLogin;
import dontsleep404.library.DClient;

public class ProcessCPacketLogin extends ProcessPacket<CPacketLogin>{

    public ProcessCPacketLogin(DClient client, CPacketLogin packet) {
        super(client, packet);
    }

    @Override
    public void process() {
        System.out.println(getPacket().username + " " + getPacket().password);
        System.out.println(getClient());
    }
    
}
