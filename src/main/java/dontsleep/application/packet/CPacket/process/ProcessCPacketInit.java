package dontsleep.application.packet.CPacket.process;

import java.util.ArrayList;

import dontsleep.application.model.Item;
import dontsleep.application.model.ItemType;
import dontsleep.application.model.Model;
import dontsleep.application.packet.CPacket.CPacketInit;
import dontsleep.application.packet.SPacket.SPacketInit;
import dontsleep.application.packet.process.ProcessPacket;
import dontsleep404.library.DClient;

public class ProcessCPacketInit extends ProcessPacket<CPacketInit>{

    public ProcessCPacketInit(DClient client, CPacketInit packet) {
        super(client, packet);
    }

    @Override
    public void process() {
        ArrayList<ItemType> itemTypes = Model.getAll(ItemType.class);
        ArrayList<Item> items = Model.getAll(Item.class);

        SPacketInit packet = new SPacketInit();
        packet.itemTypes = itemTypes;
        packet.items = items;

        getClient().sendPacket(packet);
    }
    
}
