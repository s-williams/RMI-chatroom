import java.rmi.*;

/**
 * Interface for the NotificationSource. Methods visible to the Client.
 */
public interface NotificationSourceInterface extends Remote {

    void sendMessage(Notification notification) throws RemoteException;
    void subscribe(NotificationSinkInterface sinkInterface) throws RemoteException;
    void createNewSink(String binding, NotificationSinkInterface sinkInterface) throws RemoteException;
}