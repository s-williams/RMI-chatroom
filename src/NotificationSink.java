import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * Receives notifications
 */
public class NotificationSink extends UnicastRemoteObject implements NotificationSinkInterface {

    private Client client;

    NotificationSink(Client client) throws RemoteException {
        super();
        this.client = client;
    }

    @Override
    //Used by server to send a notification containing a chat update to the client
    public void update(Notification notification) throws RemoteException {
        client.update(notification.getAuthor(), notification.getMessage());
    }
}