/*
Author: Schuyler Asplin
Dijkstra's Algorithm: finding shortest paths from source vertex to all other vertices
CSCD320 Algorithms Prog5 Professor Xu

Singly Linked List class, representing a vertex. nodes represent edges
The SLL's shortestPathSum is the sum of the weights of the edges needed to reach this vertex, in the
shortest possible path.
The set and extracted value were not necessary in this algorithm but I left them in for future applications.
 */

public class SLL {


    public int key; //the SLL's identifier
    private Node head = null;
//    public String color;
    public int shortestPathSum;//current shortest time to get to this vertex from source node
    public SLL p_previousHopInShortestPath;
    private int LLsize = 0;
    private boolean extraced = false;

    public SLL(int key){
        this.key = key;
        //head.setValue(key);//heads key is the array index of the LL
//        this.color = "white";
        this.shortestPathSum = Integer.MAX_VALUE;
        this.p_previousHopInShortestPath = null;

    }



    public void insertNode(Node newNode){
        if (this.LLsize == 0) {
            head = newNode;

        }
        else { // head.getNext() != null
            newNode.setNext(head);
            head = newNode;
        }
        LLsize++;
    }

    public int getNodeValue(){
        return this.key;
    }
    public void setNodeValue(int nodeValue){
        this.key = nodeValue;
    }
    public int getLLsize(){
        return this.LLsize;
    }

    public void setExtractedToTrue(){
        this.extraced = true;


    }

    public void printList(){
        if (this == null){
            System.out.println("error: print list on null list");
            return;
        }
        System.out.println("VERTEX: " + this.key + " with list path sum of "+this.shortestPathSum);
        Node cursor = this.head;
        while (cursor != null) {
            System.out.print("neighbor node value: "+ cursor.getSLL_Key() + ", path weight: " + cursor.pathWeight + "\n");
            cursor = cursor.getNext();
        }
        System.out.println();
    }

    public Node getHead(){
        return this.head;
    }



}

