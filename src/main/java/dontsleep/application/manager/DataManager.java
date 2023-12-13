package dontsleep.application.manager;

import java.util.ArrayList;
import java.util.HashMap;

import dontsleep.application.model.EUserRole;
import dontsleep.application.model.Task;
import dontsleep.application.model.User;
import dontsleep404.library.DClient;

public class DataManager {

    public HashMap<DClient, User> users;
    public HashMap<User, ArrayList<Task>> orderTask;

    public DataManager() {
        users = new HashMap<>();
        orderTask = new HashMap<>();
    }

    public User getUser(DClient client) {
        if (users.containsKey(client)) {
            return users.get(client);
        }
        return null;
    }

    public ArrayList<Task> getOrderTask(User user) {
        if (orderTask.containsKey(user)) {
            return orderTask.get(user);
        }
        return null;
    }

    public void setUser(DClient client, User user) {
        users.put(client, user);
        orderTask.put(user, new ArrayList<>());
    }

    public void deleteUser(DClient client) {
        if (users.containsKey(client)) {
            users.remove(client);
        }
        if (orderTask.containsKey(users.get(client))) {
            orderTask.remove(users.get(client));
        }
    }
    public ArrayList<DClient> getStaff(){
        ArrayList<DClient> staff = new ArrayList<>();
        for(DClient client : users.keySet()){
            if(users.get(client).getRole().getId() == EUserRole.STAFF.getId()){
                staff.add(client);
            }
        }
        return staff;
    }
    public ArrayList<DClient> getUsers(){
        ArrayList<DClient> clients = new ArrayList<>();
        for(DClient client : users.keySet()){
            if(users.get(client).getRole().getId() == EUserRole.USER.getId() || users.get(client).getRole().getId() == EUserRole.GUEST.getId()){
                clients.add(client);
            }
        }
        return clients;
    }

    public ArrayList<DClient> getAll(){
        ArrayList<DClient> clients = new ArrayList<>();
        for(DClient client : users.keySet()){
            clients.add(client);
        }
        return clients;
    }
}
