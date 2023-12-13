package dontsleep.application.packet.CPacket.process;

import dontsleep.application.Main;
import dontsleep.application.model.ETaskStatus;
import dontsleep.application.model.Model;
import dontsleep.application.model.Task;
import dontsleep.application.packet.CPacket.CPacketUpdateOrder;
import dontsleep.application.packet.CPacket.CPacketUpdateOrder.EnumOrderStatus;
import dontsleep.application.packet.SPacket.SPacketUpdateOrder;
import dontsleep.application.packet.process.ProcessPacket;
import dontsleep404.library.DClient;

public class ProcessCPacketUpdateOrder extends ProcessPacket<CPacketUpdateOrder>{

    public ProcessCPacketUpdateOrder(DClient client, CPacketUpdateOrder packet) {
        super(client, packet);
    }

    @Override
    public void process() {
        Task task = Model.getById(Task.class, getPacket().taskID);
        if (task == null || task.getStatus() != ETaskStatus.PENDING) return;
        SPacketUpdateOrder packet = new SPacketUpdateOrder();
        packet.status = getPacket().status == EnumOrderStatus.ACCEPTED ? dontsleep.application.packet.SPacket.SPacketUpdateOrder.EnumOrderStatus.ACCEPTED : dontsleep.application.packet.SPacket.SPacketUpdateOrder.EnumOrderStatus.CANCELED;
        packet.taskID = task.getId();
        task.setStatus(getPacket().status == EnumOrderStatus.ACCEPTED ? ETaskStatus.COMPLETED : ETaskStatus.CANCELLED);
        task.update();
        getClient().sendPacket(packet);
        for (DClient client : Main.manager.getDataManager().getAll()) {
            if (client != getClient()) {
                client.sendPacket(packet);
            }
        }
    }
    
}
