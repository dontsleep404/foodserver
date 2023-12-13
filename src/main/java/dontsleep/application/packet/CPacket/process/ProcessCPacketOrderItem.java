package dontsleep.application.packet.CPacket.process;

import dontsleep.application.Main;
import dontsleep.application.model.Bill;
import dontsleep.application.model.EBillStatus;
import dontsleep.application.model.ETaskStatus;
import dontsleep.application.model.Item;
import dontsleep.application.model.Model;
import dontsleep.application.model.Task;
import dontsleep.application.model.User;
import dontsleep.application.packet.CPacket.CPacketOrderItem;
import dontsleep.application.packet.SPacket.SPacketAddItemToBill;
import dontsleep.application.packet.process.ProcessPacket;
import dontsleep404.library.DClient;

public class ProcessCPacketOrderItem extends ProcessPacket<CPacketOrderItem> {

    public ProcessCPacketOrderItem(DClient client, CPacketOrderItem packet) {
        super(client, packet);
    }

    @Override
    public void process() {
        Item item = Model.getById(Item.class, getPacket().itemID);
        if (item == null)
            return;
        User user = Main.manager.getDataManager().getUser(getClient());
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        if (user.bill == null) {
            Bill bill = new Bill(user.getId(), EBillStatus.PENDING, timestamp);
            if (bill.insert()) {
                user.bill = bill;
            }else{
                return;
            }
        }

        Task task = new Task(user.bill.getId(), getPacket().itemID, getPacket().quantity, (long) timestamp, "",
                ETaskStatus.PENDING);
        if (task.insert()) {
            SPacketAddItemToBill packet = new SPacketAddItemToBill();
            packet.tableID = Main.manager.getDataManager().getUser(getClient()).tableId;
            packet.taskID = task.getId();
            packet.itemName = item.getName();
            packet.quantity = getPacket().quantity;
            packet.price = item.getPrice();

            getClient().sendPacket(packet);
            for (DClient client : Main.manager.getDataManager().getStaff()) {
                if (client != getClient()) {
                    client.sendPacket(packet);
                }
            }
        }

    }

}
