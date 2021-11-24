/*
Author: Schuyler Asplin
Topographical Sort of an Adjacency List representation of a graph
CSCD320 Algorithms Prog4 Professor Xu

Node class. Used by SLL for representing edges and for TopoSLL for representing verticies.
The latter is only used for final result output.
 */

public class Node {

    private int value; // value == SLL's key
    public Node next;
    public int pathWeight;
    //public String color;




    public Node() {}

    public Node(int value){
        this.value = value;
        //this.color = "white";


    }

    public Node(int value, int pathWeight){
        this.value = value;
        this.pathWeight = pathWeight;
    }



    public int getValue(){
        return this.value;
    }
    public void setValue(int value){
        this.value = value;
    }

    public Node getNext(){
        return this.next;
    }

    public void setNext(Node next){
        this.next = next;
    }


}
