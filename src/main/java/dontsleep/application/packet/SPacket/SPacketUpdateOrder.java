package dontsleep.application.packet.SPacket;

import dontsleep404.library.packet.Packet;

public class SPacketUpdateOrder extends Packet{
    public enum EnumOrderStatus {
        ACCEPTED,
        CANCELED
    }
    public EnumOrderStatus status;
    public int taskID;
}
