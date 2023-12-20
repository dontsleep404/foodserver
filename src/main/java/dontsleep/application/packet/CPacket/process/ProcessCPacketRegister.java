package dontsleep.application.packet.CPacket.process;

import java.util.ArrayList;

import dontsleep.application.model.EUserRole;
import dontsleep.application.model.Model;
import dontsleep.application.model.User;
import dontsleep.application.packet.CPacket.CPacketRegister;
import dontsleep.application.packet.SPacket.SPacketRegister;
import dontsleep.application.packet.process.ProcessPacket;
import dontsleep404.library.DClient;

public class ProcessCPacketRegister extends ProcessPacket<CPacketRegister>{

    public ProcessCPacketRegister(DClient client, CPacketRegister packet) {
        super(client, packet);
    }

    @Override
    public void process() {
        ArrayList<String> errors = new ArrayList<>();
        if (getPacket().username != null && getPacket().password != null) {
            if(Model.findBy(User.class, "username = ?", getPacket().username).size() > 0){
                errors.add("Username already exists");
            }else{
                User user = new User(getPacket().username, getPacket().password, getPacket().username, EUserRole.USER);
                if(!user.insert()){
                    errors.add("Could not register user");
                }
            }
        }else{
            errors.add("Please fill in username and password");
        }
        
        SPacketRegister packet = new SPacketRegister();
        packet.username = getPacket().username;
        packet.role = EUserRole.USER.getId();
        if(errors.size() > 0){
            packet.success = false;
            packet.error = errors.get(0);
        }else{
            packet.success = true;
        }
        getClient().sendPacket(packet);
    }
    
}
