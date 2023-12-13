package dontsleep.application.packet.CPacket.process;

import java.net.URL;
import java.util.ArrayList;

import dontsleep.application.Main;
import dontsleep.application.model.Item;
import dontsleep.application.model.ItemType;
import dontsleep.application.model.Model;
import dontsleep.application.packet.CPacket.CPacketAddItem;
import dontsleep.application.packet.SPacket.SPacketAddItemRes;
import dontsleep.application.packet.SPacket.SPacketUpdateMenu;
import dontsleep.application.packet.process.ProcessPacket;
import dontsleep404.library.DClient;

public class ProcessCPacketAddItem extends ProcessPacket<CPacketAddItem> {
    public ProcessCPacketAddItem(DClient client, CPacketAddItem packet) {
        super(client, packet);
    }

    @Override
    public void process() {

        // check admin or staff

        SPacketAddItemRes packet = new SPacketAddItemRes();
        ArrayList<String> errors = new ArrayList<>();
        if(getPacket().name == null || getPacket().name.equals("")){
            errors.add("Name is required");
        }
        if(getPacket().description == null || getPacket().description.equals("")){
            errors.add("Description is required");
        }
        if(getPacket().price == 0){
            errors.add("Price is required");
        }
        if(getPacket().image == null || getPacket().image.equals("")){
            errors.add("Image is required");
        }else{
            try {
                new URL(getPacket().image);
            } catch (Exception e) {
                errors.add("Image is invalid");
            }
        }
        if(getPacket().type == 0 && getPacket().n_type.isEmpty()){
            errors.add("Type is required");
        }
        packet.success = false;
        if(errors.size() == 0){
            boolean addType = true;
            ItemType find = null;
            if (getPacket().type == 0) {
                addType = false;
                find = new ItemType(getPacket().n_type, "");
                if (find.insert()) addType = true;
            }else{
                find = Model.getById(ItemType.class, getPacket().type);
            }
            if (addType) {
                if (find == null) errors.add("Type not found");
                else{
                    Item item = new Item(getPacket().name, getPacket().description,find,getPacket().price ,true ,getPacket().image);
                    if (item.insert()) packet.success = true;
                    else errors.add("Add item failed");
                }
                
            }else{
                errors.add("Add type failed");
            }
        }
        if (errors.size() > 0) {
            packet.message = errors.get(0);
            
        }
        getClient().sendPacket(packet);
        if (packet.success) {
            ArrayList<ItemType> itemTypes = Model.getAll(ItemType.class);
            ArrayList<Item> items = Model.getAll(Item.class);

            SPacketUpdateMenu spacket = new SPacketUpdateMenu();
            spacket.itemTypes = itemTypes;
            spacket.items = items;

            for (DClient client : Main.manager.getDataManager().getAll()) {
                client.sendPacket(spacket);
            }
        }
    }
    
}
