package dontsleep.application.packet.SPacket;

import dontsleep404.library.packet.Packet;

public class SPacketRegister extends Packet{
    public String username;
    public int role;
    public boolean success = false;
    public String error;
}
