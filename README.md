# JAVARMIGraph

The aim of the assignment is to get familiar with Java RMI. RMI stands for Remote Method Invocation.
It is a mechanism that allows an object residing in one system (JVM) to access/invoke an object running
on another JVM. RMI is used to build distributed applications, it provides remote communication between
Java programs.

For this assignment, you will be using RMI(Remote Method Invocation) in Java to implement a simple single
server architecture with support for multiple clients. The details are as follows:
• The server maintains a list of graphs each associated with a distinct identier.
• Clients can request to add a new graph, update an existing graph and query for the total weight of
the minimum weight spanning tree of a given graph.
• Clients can request to add a new graph using `add graph <graph identier> n'. This command will
add a new graph on the server with the identier graph identier and n number of nodes. The graph
identier is a string with a maximum length of 10 and it won't already exist. n will be in the range: 1
<= n <= 100,000.
• Clients can request to add a new edge in a graph using `add edge <graph identier> <u> <v> <w>'.
This will add an undirected edge between the nodes u and v with weight w. u and v are the node
numbers of the endpoints of the edge such that 1 <= u, v <= n and 0 <= w <= 10,000. n is the
number of nodes in the specied graph. A graph with identier graph identier will already exist.
There can be multiple edges and self-loops added to the graph.
• Clients can request for the total weight of the minimum weight spanning tree in a graph from the
server using `get mst <graph identier>'. The client will print the solution the server returns. In case
the graph does not have a spanning tree, -1 should be printed. A graph with identier graph identier
will already exist.
• All values should t in 32-bit signed integers.
• The server should be able to handle multiple clients simultaneously and should also work with clients
on other machines.
• You are free to use any algorithm for MST (can be sequential).
