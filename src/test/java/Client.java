import dontsleep404.library.DClient;

public class Client {
    public static void main(String[] args) {
        DClient client = new DClient("localhost", 8080);
        client.setEventHandle(new Handle());
        if (client.connect()) {
            
        }        
    }
}
