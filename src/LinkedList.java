// ----------------------I have used my own Linked list because i need only few functions like adding --------------------

public class LinkedList {
    LinkedListNode headRoot;
    public LinkedList(){
        headRoot = null;
    }

    public void addLinkedListNode(LinkedListNode newLLN){
        LinkedListNode curr = headRoot;
        if (curr == null){
            headRoot = newLLN;
            return;
        }
        while (curr.next != null){
            curr = curr.next;
        }
        curr.next = newLLN;
        return;
    }
}
