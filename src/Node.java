/*
Author: Schuyler Asplin
Topographical Sort of an Adjacency List representation of a graph
CSCD320 Algorithms Prog4 Professor Xu

Node class. Used by SLL for representing edges and for TopoSLL for representing verticies.
The latter is only used for final result output.
 */

public class Node {

    private int SLL_Key; // value == SLL's key
    public Node next;
    public int pathWeight;
    //public String color;
    public boolean pathHighlighted; //if true, this is the path to take to achieve shortestPath to this node



    public Node() {}

    public Node(int SLL_Key){
        this.SLL_Key = SLL_Key;
        //this.color = "white";
        pathHighlighted = false;

    }

    public Node(int SLL_Key, int pathWeight){
        this.SLL_Key = SLL_Key;
        this.pathWeight = pathWeight;
    }



    public int getSLL_Key(){
        return this.SLL_Key;
    }
    public void setSLL_Key(int SLL_Key){
        this.SLL_Key = SLL_Key;
    }

    public Node getNext(){
        return this.next;
    }

    public void setNext(Node next){
        this.next = next;
    }


}
