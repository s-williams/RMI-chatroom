import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Implementation of Chat Client Interface
 */
public class Client {

    public static void main(String[] args){
        NotificationSink notificationSink = null;
        NotificationSourceInterface remoteSource = null;
        boolean seeking = true;
        while (seeking) {
            try {
                Thread.sleep(1000);
                remoteSource = (NotificationSourceInterface) Naming.lookup("rmi://localhost/b");
                seeking = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Client client = new Client(remoteSource);
    }

    private NotificationSourceInterface remoteSource;
    private NotificationSink notificationSink;
    private String username;

    private Scanner scanner;

    private Client(NotificationSourceInterface remoteSource) {
        this.remoteSource = remoteSource;
        scanner = new Scanner(System.in);
        try {
            //Creates a subscribes sink to source
            notificationSink = new NotificationSink(this);
            remoteSource.subscribe(notificationSink);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        enterUsername();
        loop();
    }

    //Main program loop which looks for new messages from user and sends them.
    private void loop() {
        System.out.println("[SYSTEM] Welcome to the chat");
        String message;
        do {
            message = scanner.nextLine();
            try {
                remoteSource.sendMessage(new Notification(username, message));
            } catch (RemoteException e) {
                System.out.println("[SYSTEM] Connection failed \n[SYSTEM] Restarting");
                main(new String[0]);
            }
        } while (true);
    }

    //Used to receive messages from other users
    void update(String author, String message) {
        System.out.println("[CHAT] " + author + ": " + message);
    }

    //Used to create a new chatroom with a custom binding. Not implemented.
    private void createNewSink(String binding) {
        try {
            remoteSource.createNewSink(binding, notificationSink);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void enterUsername() {
        System.out.println("[SYSTEM] Please enter your name");
        username = scanner.nextLine();
    }
}
