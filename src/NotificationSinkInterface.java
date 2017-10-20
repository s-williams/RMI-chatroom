import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the NotificationSink. Methods visible to the Server.
 */
public interface NotificationSinkInterface extends Remote {

    void update(Notification notification) throws RemoteException;
}