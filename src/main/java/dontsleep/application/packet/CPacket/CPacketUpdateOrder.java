package dontsleep.application.packet.CPacket;

import dontsleep404.library.packet.Packet;

public class CPacketUpdateOrder extends Packet{
    public enum EnumOrderStatus {
        ACCEPTED,
        CANCELED
    }
    public EnumOrderStatus status;
    public int taskID;
}
