public class minHeap {

    public SLL[] A;
    public int heap_size;

    public minHeap(SLL[] adjList) {
        A = new SLL[adjList.length+1];
        for (int i = 1; i < A.length; i++){
            A[i] = adjList[i-1];
        }
        this.heap_size = A.length - 1; // 0 doesnt count: 1-based indexing on heap
    }
    public void heapsort(){
        int initialHeap_size = heap_size;
        Build_Min_Heap();
        for(int i = initialHeap_size; i >= 2; i--){
            swap(1, i);
            heap_size--;
            Min_Heapify(1);
        }
        heap_size = initialHeap_size;//SLIDE DIFFERENCE
    }

    public SLL Extract_Min(){
        if (heap_size < 1) {
            System.out.println("Error: cannot call extract min when heap size is 0");
        }
        SLL smallest = A[1];
//        A[1] = A[heap_size];//SLIDE DIFFERENCE
        swap(1,heap_size);
        heap_size--;
        Min_Heapify(1);
        return smallest;
    }
    public void Build_Min_Heap(){
        //heap_size = A.length-1;
        //for(int i = (int)Math.floor((A.length-1)/2); i >= 2; i--){
        for(int i = heap_size; i > 0; i--){ // SLIDE DIFFERENCE trying heapsize because it will be run multiple times
            Min_Heapify(i);
        }
    }
    public void Min_Heapify(int i){
        if (i == 0) {return;} // we use 1 based indexing for heap, so index 0 is not used
        else if (i >= A.length) throw new IllegalArgumentException("i cannot be greater than array size");

        if (isLeafNode(i)){ return; }

        SLL smallestPathSumVertex;

        SLL leftChild = getLeftChild(i);
        SLL rightChild = getRightChild(i);

        if (rightChild != null && A[i].shortestPathSum > rightChild.shortestPathSum){
            smallestPathSumVertex = rightChild; // if right child is smaller, set it as smallestNode
        } else {
            smallestPathSumVertex = A[i];
        }
        if (leftChild != null && smallestPathSumVertex.shortestPathSum > leftChild.shortestPathSum){
            smallestPathSumVertex = leftChild; // if left child is smallest, move smallestNode pointer to left child
        }

        // if smallest node == i, entire subtree is a min heap. else, we need to repair the tree we swapped with.
        // of course, if swapped node is a leaf node, this is not necessary

        if (smallestPathSumVertex != A[i]) {
            // repair the damaged heap
            if (smallestPathSumVertex == leftChild) {
                swap(i,i*2);
                Min_Heapify(i*2);
            }
            else if(smallestPathSumVertex == rightChild){
                swap(i, (i*2)+1);
                Min_Heapify((i*2)+1);
            }

//            swap(smallestPathSumVertex.key+1,A[i].key+1);//keys are graph locations. must be +1 for heap locations
//            Min_Heapify(smallestPathSumVertex.key+1);
        }

    }

    public SLL getRightChild(int i) {
        int ret = (i*2)+1;
        if (ret > heap_size) {
            return null;//original heap code said: return -1// heap_size is array index -1, adjusts for A index 0
        }
        else return A[ret];
    }

    public SLL getLeftChild(int i) {
        int ret = (i * 2);
        if (ret > heap_size) {
            return null;
        } else {
            return A[ret];
        }
    }
    public SLL getParentVertex(int i){
        return A[(int)Math.floor(i/2)];
    }

    public void swap(int idx, int idx2){

        SLL temp = A[idx];
        A[idx] = A[idx2];
        A[idx2] = temp;
    }

    public boolean isLeafNode(int i){
        if (i > (this.heap_size)/2){// -1 adjusts for A index 0
            return true;
        }
        else return false;
    }

public void printHeap(Graph graph){
    System.out.println("heap size = "+heap_size);
    for (int i = 1; i <= A.length-1; i++) {
        if (i > heap_size){
            System.out.println("OUTSIDE HEAP");
        }
        System.out.println("heap position: "+i + ", SLL path sum: " + A[i].shortestPathSum);

        Node cursor = A[i].getHead();
        while (cursor != null){
            System.out.println("points to heap position "+(cursor.getSLL_Key()+1)+" in adjList with weight of "+cursor.pathWeight);
            cursor = cursor.getNext();
        }
        System.out.println();


    }
}




}
