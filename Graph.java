import java.util.*;

import javax.swing.text.Style;
// Implementing the remote interface 
public class Graph implements GraphRMI {  
   HashMap<String,int[][]> graphMap= new HashMap<>();
   HashMap<String, Integer> graphNodes = new HashMap<>();
   // Implementing the interface method
   public String add_graph(String identifier, int nodes) 
   {  
      if(graphMap.containsKey(identifier))
      {
         return "Graph already exists";
      }
      else
      {
         graphMap.put(identifier, new int[nodes][nodes]);
         graphNodes.put(identifier, nodes);
         return "Graph has been ceated successfully";
      }
   }

   public String add_edge(String identifier, int u, int v, int w)
   {
      if(!graphMap.containsKey(identifier))
      {
         return "Graph not found";
      }
      else
      {
         int nodes = graphNodes.get(identifier);
         if(u<=0 || u>nodes || v<=0 || v>nodes || w < 0)
         {
            return "Wrong Input";
         }
         if(u != v)
         {
            int[][] graph = graphMap.get(identifier);
            graph[u-1][v-1] = w;
            graph[v-1][u-1] = w;
            graphMap.put(identifier, graph);
         }
         return "Edge added successfully";
      }
   }

   boolean isconnected(int graph[][],int nodes)
   {
      Stack<Integer> stack = new Stack<Integer>();
      int visited[] = new int[nodes];		
      int node;		
      int current_node = 0;	
      boolean connected = true;
      visited[0] = 1;		
      stack.push(0);
 
      while (!stack.isEmpty())
      {
         node = stack.peek();
         current_node = node;	
	      while (current_node < nodes)
	      {
     	      if (visited[current_node] == 0 && graph[node][current_node] != 0)
	         {
               stack.push(current_node);
               visited[current_node] = 1;
               node = current_node;
               current_node = 0;
	            continue;
            }
            current_node++;
	      }
         stack.pop();	
      }
 
      for (int j = 0; j < nodes; j++)
      {
         if (visited[j] == 0) 
         {
            connected = false;
         } 
      }
      return connected;
   }

   int minKey(int key[], Boolean mstSet[], int nodes) 
    {  
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        for (int v = 0; v < nodes; v++) 
            if (mstSet[v] == false && key[v] < min) { 
                min = key[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 

   int mst(String identifier)
   {
      int[][] graph = graphMap.get(identifier);
      int nodes = graphNodes.get(identifier);
      int parent[] = new int[nodes]; 
      int key[] = new int[nodes]; 
      Boolean mstSet[] = new Boolean[nodes];
      
      if(!isconnected(graph, nodes))
         return -1;

      for (int i = 0; i < nodes; i++) 
      { 
         key[i] = Integer.MAX_VALUE; 
         mstSet[i] = false; 
      }
      key[0] = 0;
      parent[0] = -1; 
      for (int count = 0; count < nodes - 1; count++) 
      {
         int u = minKey(key, mstSet, nodes); 
         mstSet[u] = true; 
         for (int v = 0; v < nodes; v++)
         {
            if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) 
            { 
               parent[v] = u; 
               key[v] = graph[u][v]; 
            }
         }
      }
      int weight = 0;
      for(int i=1; i<nodes; i++)
      {
         weight = weight+graph[i][parent[i]];
      }
      return weight;
   }

   public int get_mst(String identifier)
   {
      if(!graphMap.containsKey(identifier))
      {
         return -1;
      }
      return mst(identifier);
   }
} 
