package dontsleep.application.packet.SPacket;

import java.util.ArrayList;

import dontsleep.application.model.Item;
import dontsleep.application.model.ItemType;
import dontsleep404.library.packet.Packet;

public class SPacketInit extends Packet{

    public ArrayList<ItemType> itemTypes;
    public ArrayList<Item> items;


    public SPacketInit() {
        this.itemTypes = new ArrayList<>();
        this.items = new ArrayList<>();
    }
}
