/*
Author: Schuyler Asplin
Dijkstra's Algorithm
CSCD320 Algorithms Prog Professor Xu

main class
 */


import java.io.File;
import java.util.Scanner;

public class Dijkstra {

public static void main (String[] args){

        Graph graph = null;
        File file = new File(args[0]);

        // file io, filling adjacency list
        try { // this try clause gets the number of lines
        Scanner sc = new Scanner(file);
        /*
        Currently, key is the array index at which linked lists are added to the adjacency list.
        If keys are inserted into occupied indexes, room is made by moving
         */
        int numOfLines = 0;
        while (sc.hasNextLine()) {

        numOfLines++;
        sc.nextLine();
        }

        graph = new Graph(numOfLines);

        sc.close();

        } catch (Exception eLineGetter) {}

        try { // this try clause fills the array with SLL's

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {

            String line = sc.nextLine();
            String[] splitLine = line.split(":");
            int key = Integer.parseInt(splitLine[0]);
            SLL sll = new SLL(key);
            graph.insert(key, sll);
            if (splitLine.length > 1){

                String[] segments = splitLine[1].split(";");
                for (int i = 0; i < segments.length; i++) {
                    String[] ValueAndPathWeight = segments[i].split(",");
                    Node nextHopNeighbor = new Node(Integer.parseInt(ValueAndPathWeight[0]), Integer.parseInt(ValueAndPathWeight[1]));
                    sll.insertNode(nextHopNeighbor);
                }
            }
            sll.printList();
        }
        sc.close();
        } catch (Exception e) {
        System.out.println("e: " + e.getStackTrace());
        }



    }

}

