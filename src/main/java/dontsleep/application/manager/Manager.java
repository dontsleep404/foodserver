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
        }else{
            System.err.println("Can't connect to database");
            System.exit(0);
        }
    }
    public DataManager getDataManager() {
        return dataManager;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }
}
