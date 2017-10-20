import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Sends notifications when events happen
 */
public class NotificationSource extends UnicastRemoteObject implements NotificationSourceInterface {

    private Server server;

    NotificationSource(Server server) throws RemoteException {
        super();
        this.server = server;
    }

    @Override
    //Used by clients to send a message to the chatroom
    public void sendMessage(Notification notification) throws RemoteException {
        server.updateAllSinks(notification);
    }

    @Override
    //Used by clients to subscribe to a chatroom
    public void subscribe(NotificationSinkInterface sinkInterface) throws RemoteException {
        server.addSink(sinkInterface);
    }

    @Override
    //Used by clients to create a new chatroom
    public void createNewSink(String binding, NotificationSinkInterface sinkInterface) throws RemoteException {
        Server newServer = new Server(binding);
        newServer.addSink(sinkInterface);
    }


}