package dontsleep.application.packet.CPacket;

import dontsleep404.library.packet.Packet;

public class CPacketLogin extends Packet{
    public String username;
    public String password;
    public boolean isGuest = false;
    public String tableId;
}
