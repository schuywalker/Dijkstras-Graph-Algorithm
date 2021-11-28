/*
Author: Schuyler Asplin
Dijkstra's Algorithm: finding shortest paths from source vertex to all other vertices
CSCD320 Algorithms Prog5 Professor Xu

main class, including Djikstra function and printDjikstra function.
 */


import java.io.File;
import java.util.Scanner;

public class Dijkstra {

    public static void main(String[] args) {

        Graph graph = null;
        File file = new File(args[0]);

        // file io, filling adjacency list
        try { // this try clause gets the number of lines
            Scanner sc = new Scanner(file);
        /*
        key is the array index at which linked lists are added to the adjacency list.
        Heap uses 1 based indexing so heap functions have all been modified to use key+1 when necessary.
         */
            int numOfLines = 0;
            while (sc.hasNextLine()) {

                numOfLines++;
                sc.nextLine();
            }

            graph = new Graph(numOfLines);

            sc.close();

        } catch (Exception eLineGetter) {
        }

        try { // this try clause fills the array with SLL's

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] splitLine = line.split(":");
                int key = Integer.parseInt(splitLine[0]);
                SLL sll = new SLL(key);
                graph.insert(key, sll);
                if (splitLine.length > 1) {

                    String[] segments = splitLine[1].split(";");
                    for (int i = 0; i < segments.length; i++) {
                        String[] ValueAndPathWeight = segments[i].split(",");
                        Node nextHopNeighbor = new Node(Integer.parseInt(ValueAndPathWeight[0]), Integer.parseInt(ValueAndPathWeight[1]));
                        sll.insertNode(nextHopNeighbor);
                    }
                }

                //sll.printList();
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("e: " + e.getStackTrace());
        }

        minHeap heap = new minHeap(graph.adjList);

        heap.Build_Min_Heap();

        int SourceNodeKey = Integer.valueOf(args[1]);

        Dijkstra(graph, heap, graph.adjList[SourceNodeKey]);


    }

    public static void Dijkstra(Graph graph, minHeap heap, SLL source){
        source.shortestPathSum = 0;//path from vertex 0 to itself is 0
        heap.A[source.key+1].shortestPathSum = 0;

        if (heap.A[1] != source) { // every other shortestPathSum will be infinitly large,
            // so we can just swap to put source on top and we will have a min heap
            heap.swap(1, source.key+1);
        }


//        heap.printHeap(graph);

        while (heap.heap_size != 0){
            SLL heap_u = heap.Extract_Min();
            SLL u = graph.adjList[heap_u.key];
            //graph.SetUnion(u);
//            Node v_cursor = u.getHead();
            Node v_cursor = graph.adjList[u.key].getHead();
            while (v_cursor != null){

                // relax edge
//                int nextHopNeighborPathSum = graph.adjList[v_cursor.getSLL_Key()].shortestPathSum;
                int nextHopNeighborPathSum = graph.adjList[v_cursor.getSLL_Key()].shortestPathSum;
                int extractedVertexU_PathSum = u.shortestPathSum;
                int weightFromUtoV = v_cursor.pathWeight;
                int relaxed_edge = extractedVertexU_PathSum + weightFromUtoV;
                if (relaxed_edge < 0) {
                    relaxed_edge = extractedVertexU_PathSum; // max int + (num > 0) = a negative number.
                    //this will mess up the comparison below, so if relaxed edge is inadvertantly made to be negative,
                    //we reset it to be the extracted vertex's pathSum, which will make it max int.
                }

                if (nextHopNeighborPathSum > relaxed_edge) {
                    //relax edge

                    graph.adjList[v_cursor.getSLL_Key()].shortestPathSum = relaxed_edge;

                    //repair minheap
                    heap.Build_Min_Heap();

                    // set vertex with highlighted edge pointing to v to be u
                    graph.adjList[v_cursor.getSLL_Key()].p_previousHopInShortestPath = graph.adjList[u.key];

                }
                v_cursor = v_cursor.getNext();
            }
        }
        printDijkstra(graph,heap,source);

    }

    public static void printDijkstra(Graph graph, minHeap heap, SLL source){
        for (int i = 0; i < graph.adjList.length; i++){

            if (graph.adjList[i] == source) {
                //do nothing
            }
            else if (graph.adjList[i].p_previousHopInShortestPath == null) {
                System.out.println("["+i+"]"+"unreachable");
            }
            else {
                System.out.println("["+i+"]"+"shortest path:"+ graph.getShortestPath(i,source) + " shortest distance:"+graph.adjList[i].shortestPathSum);
            }

        }
    }

    public static void printListAndHeap(Graph graph, minHeap heap) {

        for (int i = 1; i < heap.A.length; i++) {
            System.out.println(i - 1 + " GRAPH KEY: " + graph.adjList[i - 1].key);
            System.out.println(i + " heap key: " + heap.A[i].key);
            SLL leftChild = heap.getRightChild(i);
            SLL rightChild = heap.getRightChild(i);
            if (leftChild != null) {
                System.out.print("  heap right child: at " + i * 2 + ": " + leftChild.key);
            } else {
                System.out.print("  left child null");
            }
            if (rightChild != null) {
                System.out.print("  heap left child: " + (i * 2 + 1) + ": " + rightChild.key);
            } else {
                System.out.print("  right child null");
            }
            System.out.println();

        }


    }
}


