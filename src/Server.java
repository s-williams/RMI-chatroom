import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

/**
 * Implementation of Chat Server Interface
 */
public class Server {

    public static void main(String[] args){
        Server server = new Server();
    }

    private ArrayList<NotificationSinkInterface> sinks;

    private Server() {
        try{
            LocateRegistry.createRegistry(1099);
            NotificationSource notificationSource = new NotificationSource(new Server());
            Naming.rebind("b", notificationSource);
        } catch(Exception ignored){}
        sinks = new ArrayList<NotificationSinkInterface>();
    }

    //Alternative constructor with custom binding
    public Server(String binding) {
        try{
            NotificationSource notificationSource = new NotificationSource(new Server());
            Naming.rebind(binding, notificationSource);
        } catch(Exception e){e.printStackTrace();}
    }

    //Adds a sink to the sinks arrayList
    void addSink(NotificationSinkInterface sinkInterface) {
        sinks.add(sinkInterface);
    }

    //Sends every sink in the sink arrayList a update with the notification passed to it
    void updateAllSinks(Notification notification) throws RemoteException {
        for (NotificationSinkInterface sinkInterface : sinks) {
            sinkInterface.update(notification);
        }
    }
}
