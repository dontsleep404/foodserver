package dontsleep.application.manager.process;

import dontsleep404.library.DClient;
import dontsleep404.library.packet.Packet;

public abstract class ProcessPacket<T extends Packet> {
    private T packet;
    private DClient client;
    public ProcessPacket(DClient client, T packet) {
        this.packet = packet;
        this.client = client;
        process();
    }
    public abstract void process();
    public T getPacket() {
        return packet;
    }
    public DClient getClient() {
        return client;
    }
}
