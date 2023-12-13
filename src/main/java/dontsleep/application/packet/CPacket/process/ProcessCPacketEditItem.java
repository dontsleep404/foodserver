package dontsleep.application.packet.CPacket.process;

import java.net.URL;
import java.util.ArrayList;

import dontsleep.application.Main;
import dontsleep.application.model.Item;
import dontsleep.application.model.ItemType;
import dontsleep.application.model.Model;
import dontsleep.application.packet.CPacket.CPacketEditItem;
import dontsleep.application.packet.SPacket.SPacketEditItemRes;
import dontsleep.application.packet.SPacket.SPacketUpdateMenu;
import dontsleep.application.packet.process.ProcessPacket;
import dontsleep404.library.DClient;

public class ProcessCPacketEditItem extends ProcessPacket<CPacketEditItem> {
    public ProcessCPacketEditItem(DClient client, CPacketEditItem packet) {
        super(client, packet);
    }

    @Override
    public void process() {

        // TODO : check admin or staff

        SPacketEditItemRes packet = new SPacketEditItemRes();
        packet.success = false;
        ArrayList<String> errors = new ArrayList<>();

        if (getPacket().isDelete) {
            Item item = Model.getById(Item.class, getPacket().id);
            if (item == null) {
                errors.add("Item not found");
            } else {
                if (item.delete()) {
                    packet.success = true;
                    packet.message = "Delete item success";
                } else {
                    errors.add("Delete item failed");
                }
            }
        } else {

            if (getPacket().name == null || getPacket().name.equals("")) {
                errors.add("Name is required");
            }
            if (getPacket().description == null || getPacket().description.equals("")) {
                errors.add("Description is required");
            }
            if (getPacket().price == 0) {
                errors.add("Price is required");
            }
            if (getPacket().image == null || getPacket().image.equals("")) {
                errors.add("Image is required");
            } else {
                try {
                    new URL(getPacket().image);
                } catch (Exception e) {
                    errors.add("Image is invalid");
                }
            }
            if (getPacket().type == 0 && getPacket().n_type.isEmpty()) {
                errors.add("Type is required");
            }
            if (errors.size() == 0) {
                boolean addType = true;
                ItemType find = null;
                if (getPacket().type == 0) {
                    addType = false;
                    find = new ItemType(getPacket().n_type, "");
                    if (find.insert())
                        addType = true;
                } else {
                    find = Model.getById(ItemType.class, getPacket().type);
                }
                if (addType) {
                    if (find == null)
                        errors.add("Type not found");
                    else {
                        Item item = Model.getById(Item.class, getPacket().id);
                        if (item == null)
                            errors.add("Item not found");
                        else {
                            item.setName(getPacket().name);
                            item.setDescription(getPacket().description);
                            item.setPrice(getPacket().price);
                            item.setImage(getPacket().image);
                            item.setType(find.getId());
                            if (item.update()) {
                                packet.success = true;
                                packet.message = "Update item success";
                            } else {
                                errors.add("Update item failed");
                            }
                        }
                    }

                } else {
                    errors.add("Add type failed");
                }
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
