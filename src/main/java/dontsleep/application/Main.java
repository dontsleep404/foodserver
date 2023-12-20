package dontsleep.application;

import dontsleep.application.manager.Manager;
import dontsleep.application.packet.PacketManager;
import dontsleep404.library.DServer;

public class Main{
    public static Manager manager;
    public static void main(String[] args) {
        manager = new Manager();
        DServer server = new DServer(25565);
        server.setEventHandle(new PacketManager());
        server.listen();
        System.out.println("Server is running on port 25565");
    }
}
