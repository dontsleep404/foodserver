package dontsleep.application.packet.CPacket.process;

import java.util.ArrayList;

import dontsleep.application.model.Model;
import dontsleep.application.model.User;
import dontsleep.application.packet.CPacket.CPacketLogin;
import dontsleep.application.packet.SPacket.SPacketLogin;
import dontsleep.application.packet.process.ProcessPacket;
import dontsleep404.library.DClient;

public class ProcessCPacketLogin extends ProcessPacket<CPacketLogin>{

    public ProcessCPacketLogin(DClient client, CPacketLogin packet) {
        super(client, packet);
    }

    @Override
    public void process() {
        ArrayList<User> users = Model.findBy(User.class, "username = ? and password = ?", getPacket().username, getPacket().password);
        SPacketLogin packet = new SPacketLogin();
        if(users.size() > 0){            
            packet.username = getPacket().username;
            packet.role = users.get(0).getRole().getId();
            packet.success = true;
        }
        getClient().sendPacket(packet);
        System.out.println(packet);
    }
    
}
