import java.rmi.Remote; 
import java.rmi.RemoteException;  

// Creating Remote interface for our application 
public interface GraphRMI extends Remote {  
   String add_graph(String identifier, int nodes) throws RemoteException;
   String add_edge(String identifier, int u, int v, int w) throws RemoteException;
   int get_mst(String identifier) throws RemoteException;
} 
