package dontsleep.application.manager;

import java.util.ArrayList;

import dontsleep404.library.DClient;
import dontsleep404.library.event.EventHandle;
import dontsleep404.library.event.EventPacket;
import dontsleep404.library.packet.Packet;

public class PacketManager extends EventHandle{
  
    public PacketManager() {
        super(new ArrayList<Class<? extends Packet>>(){
            {

            }
        });
    }

    @Override
    public void onConnect(EventPacket arg0) {
        //
    }

    @Override
    public void onDisconnect(EventPacket arg0) {
        // 
    }

    @Override
    public void onPacketReceived(DClient arg0, Packet arg1) {
        String proc = "dontsleep.application.manager.process.Process" + arg1.getClass().getSimpleName();
        try{
            Class<?> c = Class.forName(proc);
            c.getConstructor(DClient.class, arg1.getClass()).newInstance(arg0, arg1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
