package dontsleep.application.packet.CPacket.process;

import java.util.ArrayList;

import dontsleep.application.Main;
import dontsleep.application.model.Bill;
import dontsleep.application.model.ETaskStatus;
import dontsleep.application.model.EUserRole;
import dontsleep.application.model.Item;
import dontsleep.application.model.Model;
import dontsleep.application.model.Task;
import dontsleep.application.model.User;
import dontsleep.application.packet.CPacket.CPacketLogin;
import dontsleep.application.packet.SPacket.SPacketAddItemToBill;
import dontsleep.application.packet.SPacket.SPacketLogin;
import dontsleep.application.packet.process.ProcessPacket;
import dontsleep404.library.DClient;

public class ProcessCPacketLogin extends ProcessPacket<CPacketLogin>{

    public ProcessCPacketLogin(DClient client, CPacketLogin packet) {
        super(client, packet);
    }

    @Override
    public void process() {
        SPacketLogin packet = new SPacketLogin();
        if(getPacket().isGuest == true){
            packet.username = "Guest";
            packet.role = 0;
            packet.success = true;
            
            User user = new User();
            user.tableId = getPacket().tableId;

            Main.manager.getDataManager().setUser(getClient(), user);

        }else{
            ArrayList<User> users = Model.findBy(User.class, "username = ? and password = ?", getPacket().username, getPacket().password);
            if(users.size() > 0){            
                packet.username = getPacket().username;
                packet.role = users.get(0).getRole().getId();
                packet.success = true;

                User user = users.get(0);
                user.tableId = getPacket().tableId;
                Main.manager.getDataManager().setUser(getClient(), user);

                if(user.getRole() == EUserRole.STAFF){
                    for(DClient client : Main.manager.getDataManager().getUsers()){
                        User u = Main.manager.getDataManager().getUser(client);
                        Bill bill = u.bill;
                        if(bill != null){
                            for(Task task : Model.findBy(Task.class, "billid = ? AND status = ?", bill.getId(), ETaskStatus.PENDING.getStatus())){
                                SPacketAddItemToBill packetItem = new SPacketAddItemToBill();
                                packetItem.tableID = u.tableId;
                                packetItem.taskID = task.getId();
                                packetItem.itemName = Model.getById(Item.class, task.getItemId()).getName();
                                packetItem.quantity = task.getQuantity();
                                packetItem.price = Model.getById(Item.class, task.getItemId()).getPrice();
                                getClient().sendPacket(packetItem);
                            }
                        }
                    }
                }
            }
        }
        getClient().sendPacket(packet);
    }
    
}
