package dontsleep.application.packet.CPacket;

import dontsleep404.library.packet.Packet;

public class CPacketEditItem extends Packet{
    public boolean isDelete = false;
    public int id;
    public String name;
    public String description;
    public int price;
    public int type = 0;
    public String n_type = "";
    public String image;   
}
