/*
Author: Schuyler Asplin
Dijkstra's Algorithm: finding shortest paths from source vertex to all other vertices
CSCD320 Algorithms Prog5 Professor Xu

Node class. Used by SLL for representing edges.
Holds path weight which is the weight of the edge when travelling from the SLL containing a node to the SLL with a
key equal to the nodes SLL_Key; To be clear, the SLL_Key is not the key of the SLL containing the node, but instead it
is the key of the SLL that the node represents a directed edge toward.
 */

public class Node {

    private int SLL_Key; // value == SLL's key
    public Node next;
    public int pathWeight;
    //public String color;
    public boolean pathHighlighted; //if true, this is the path to take to achieve shortestPath to this node



    public Node(int SLL_Key, int pathWeight){
        this.SLL_Key = SLL_Key;
        this.pathWeight = pathWeight;
    }


    public int getSLL_Key(){
        return this.SLL_Key;
    }
    public Node getNext(){
        return this.next;
    }
    public void setNext(Node next){
        this.next = next;
    }


}
