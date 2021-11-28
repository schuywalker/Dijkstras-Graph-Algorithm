/*
Author: Schuyler Asplin
Dijkstra's Algorithm: finding shortest paths from source vertex to all other vertices
CSCD320 Algorithms Prog5 Professor Xu

Graph class with an adjacency list representation of the graph.
The set and union were not utilized in performing Djikstra's algorithm but I've left them in for future applications.
 */
import java.util.ArrayList;


public class Graph {

    public SLL[] adjList;    // adjacency list representation of our graph
    public ArrayList<SLL> set = new ArrayList<SLL>();


    public Graph (int size) {
        this.adjList = new SLL[size];
    }

    public void insert(int key, SLL sll){
            this.adjList[key] = sll;
    }

    public void SetUnion(SLL sll){
        sll.setExtractedToTrue();
        set.add(sll);
    }

    public String getShortestPath(int i,SLL source) {

        if (adjList[i].p_previousHopInShortestPath == null) {
            return "error, shortest path should not be called on node with node highlighted edges pointing to it";
        }
        //ArrayList<SLL> hopsReverseOrder = new ArrayList<SLL>();
        SLL cursor = adjList[i].p_previousHopInShortestPath;
        String hops = Integer.toString(cursor.key);
        while(cursor != source) {
            if (cursor.p_previousHopInShortestPath == cursor) {
                System.out.println("everything getting messed up at " + cursor.key);
                break;
            }
            cursor = cursor.p_previousHopInShortestPath;
            hops = cursor.key + "," + hops;
        }
        hops = "(" + hops + "," + adjList[i].key + ")";
        return hops;

    }


    public void printGraph(){
        for (int i = 0; i < this.adjList.length; i++){
            if (this.adjList[i] == null) {
                //do nothing. this saves program if a number (including 0) is not present in graph
            }
            else if (this.adjList[i].getLLsize() > 0) {
                this.adjList[i].printList();
            }
            else if (this.adjList[i].getLLsize() == 0)
            System.out.println(this.adjList[i].key);
        }
    }



}



