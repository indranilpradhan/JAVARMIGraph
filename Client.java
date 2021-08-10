import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;  
import java.util.*;
import javax.lang.model.util.ElementScanner6;

public class Client {  
   private Client() {}  
   public static void main(String[] args) {  
      try {  
         // Getting the registry
         String ip = args[0];
         int port = Integer.valueOf(args[1]);
         Registry registry = LocateRegistry.getRegistry(ip,port); 
    
         // Looking up the registry for the remote object 
         GraphRMI stub = (GraphRMI) registry.lookup("GraphRMI"); 
    
         // Calling the remote method using the obtained object 
         Scanner sc= new Scanner(System.in);
         while(sc.hasNextLine())
         {
            String command = sc.nextLine();
            StringTokenizer st = new StringTokenizer(command);
            String action = st.nextToken(" ");
            if(action.equals("add_graph"))
            {
               String identifier = String.valueOf(st.nextToken(" "));
               int nodes = Integer.valueOf(st.nextToken(" "));
               System.out.println(stub.add_graph(identifier, nodes));
            }
            else if(action.equals("add_edge"))
            {
               String identifier = String.valueOf(st.nextToken(" "));
               int u = Integer.valueOf(st.nextToken(" "));
               int v = Integer.valueOf(st.nextToken(" "));
               int w = Integer.valueOf(st.nextToken(" "));
               System.out.println(stub.add_edge(identifier, u, v, w));
            }
            else if(action.equals("get_mst"))
            {
               String identifier = String.valueOf(st.nextToken(" "));
               System.out.println("The Weight of the MST is "+ stub.get_mst(identifier));
            }
            else 
            {
               System.out.println("Wrong Command");
            }
         }
      } catch (Exception e) {
         System.err.println("Client exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
   } 
}
