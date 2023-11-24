package dontsleep.application.manager;

import dontsleep.application.database.DatabaseHelper;

public class Manager {

    private DataManager dataManager;
    private DatabaseHelper databaseHelper;

    public Manager(){
        databaseHelper = new DatabaseHelper();
        if (databaseHelper.isConnected()) {
            System.out.println("Connected to database");
            dataManager = new DataManager();
            start();
        }else{
            System.err.println("Can't connect to database");
        }
    }
    public void start(){

    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }
}
