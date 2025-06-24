// -------- This is the Huffman Tree Node where i store data and frequency of the character ----------------------------

public class HuffmanNode {
        // Character data
        char data;

        // Frequency of the character
        int frequency;

        // Left and right child nodes
        HuffmanNode left, right;

        // Constructor to initialize the node
        HuffmanNode(char data, int frequency) {
            this.data = data;
            this.frequency = frequency;
            left = right = null;
        }
}
