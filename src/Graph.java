import java.util.ArrayList;

/*
Author: Schuyler Asplin
Topographical Sort of an Adjacency List representation of a graph
CSCD320 Algorithms Prog4 Professor Xu

Graph function with DFS and TopoSort
 */
public class Graph {

    public SLL[] adjList;    // adjacency list representation of our graph
    public ArrayList<SLL> set = new ArrayList<SLL>();


    public Graph (int size) {
        this.adjList = new SLL[size];
        //this.adjList[0].shortestPathSum = 0;

    }

    public void insert(int key, SLL sll){
            this.adjList[key] = sll;
    }

    public void SetUnion(SLL sll){
        sll.setExtractedToTrue();
        set.add(sll);
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



