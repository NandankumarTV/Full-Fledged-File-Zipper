// This is the class where i store Encoded stuff like encoded data and Huffman tree to get back the original data ------

public class PairOfEncodedAndHuffmanRoot {
        String encoded;
        HuffmanNode root;
        public PairOfEncodedAndHuffmanRoot(String encoded, HuffmanNode root){
            this.encoded = encoded;
            this.root = root;
        }
}
