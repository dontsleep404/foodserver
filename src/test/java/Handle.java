import java.util.ArrayList;

import dontsleep404.library.DClient;
import dontsleep404.library.event.EventHandle;
import dontsleep404.library.event.EventPacket;
import dontsleep404.library.packet.Packet;

public class Handle extends EventHandle{
   
    public Handle() {
        super(new ArrayList<Class<? extends Packet>>(){
            {

            }
        });
    }

    @Override
    public void onConnect(EventPacket arg0) {
    }

    @Override
    public void onDisconnect(EventPacket arg0) {
        // 
    }

    @Override
    public void onPacketReceived(DClient arg0, Packet arg1) {
    }
}
