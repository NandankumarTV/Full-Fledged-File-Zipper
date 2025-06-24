// --------------------------- Linked List Node strore item name, sub tree linked list head then next and ----------
// --------------------------- Encoded stuff in one object Pair ----------------------------------------------------

public class LinkedListNode {
        String itemName;
        PairOfEncodedAndHuffmanRoot  encodedAndRoot;
        LinkedListNode headRoot = null;
        LinkedListNode next;

        public LinkedListNode(String itemName, PairOfEncodedAndHuffmanRoot  encodedAndRoot, LinkedListNode headRoot) {
            this.encodedAndRoot = encodedAndRoot;
            this.itemName = itemName;
            this.next = null;
        }
}
