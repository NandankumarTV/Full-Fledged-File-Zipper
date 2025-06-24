
// ---------------------------- Welcome to FileZipper Code --------------------------------------------------

import java.io.*;
import java.util.*;
import java.nio.file.*;

//------------------------------ Main FileZipper class down below --------------------------------------------

public class FileZipper {
    //--------------------------- To Check whether the item are present in the given location  ----------------------
    private static String getItemAndCheckIsPresentOrNot(Scanner sc, String srcPath) {
        System.out.print("Enter the item :- ");
        String itemName = sc.nextLine();
        String pathWithItem = srcPath + "\\" + itemName;
        File file = new File(pathWithItem);
        if (file.exists()) {
            return itemName;
        } else {
            System.out.println("Please enter proper item name");
        }
        return "";
    }

// ------------------------------------ To enter the items to be zipped -------------------------------------
    private static List<String> getItemNames(Scanner sc, String srcPath) {
        List<String> items = new ArrayList<>();
        while (true) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("Option 1:---> To add the item for zipping");
            System.out.println("Option 2:---> Quit");
            System.out.println("------------------------------------------------------------------");
            System.out.print("Enter the option :- ");
            int num = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline
            switch (num) {
                case 1:
                    String itemName = getItemAndCheckIsPresentOrNot(sc, srcPath);
                    if (itemName != "") {
                        items.add(itemName);
                    }
                    break;

                case 2:
                    break;

                case 3:
                    System.out.println("Enter the valid option if you want to stop please select 2");
            }
            return items;
        }
    }

// ---------------------------- To get the items in the directory path -------------------------------------
    public static List<String> getDirItems(String itemPath) {
        List<String> items = new ArrayList<>();
        File directory = new File(itemPath);
        File[] files = directory.listFiles();
        if (files == null) {
            return items;
        }
        for (File file : files) {
            items.add(file.getName());
        }
        return items;
    }

// ------------------------------ We need to have Frequency map (Character - count) in the file, to make Huffman tree ---------
    public static HashMap<Character, Integer> makeFrequencyMap(String itemPath) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();

        try (FileReader reader = new FileReader(itemPath)) {
            int character;
            while ((character = reader.read()) != -1) {
                frequencyMap.put((char) character, frequencyMap.getOrDefault((char) character, 0) + 1); // Print each character
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return frequencyMap;
    }

// -------------------------------- To make the Huffman tree by taking frequency map --------------------------------
  public static HuffmanNode makeHuffmanTree(HashMap<Character,Integer> frequencyMap)   {
    // Create a priority queue to build the Huffman Tree
    PriorityQueue<HuffmanNode> priorityQueue =
            new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

    // Create a Huffman node for each character and add it to the priority queue
        for(
    char c :frequencyMap.keySet())

    {
        priorityQueue.add(new HuffmanNode(c, frequencyMap.get(c)));
    }

    // Build the Huffman Tree
        while(priorityQueue.size()>1)

    {
        // Remove the two nodes with the lowest frequency
        HuffmanNode left = priorityQueue.poll();
        HuffmanNode right = priorityQueue.poll();

        // Create a new internal node with these two nodes
        // as children and add it back to the queue
        if (left != null && right != null) {
            HuffmanNode newNode =
                    new HuffmanNode('$', left.frequency + right.frequency);

            newNode.left = left;
            newNode.right = right;
            priorityQueue.add(newNode);
        }
    }

    // The remaining node is the root of the Huffman Tree
    HuffmanNode root = priorityQueue.poll();
        return root;
}

    private static void makeCodesHelper(HuffmanNode root, HashMap<Character, String> CharacterCodes, String code) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            CharacterCodes.put(root.data, code);
            return;
        }
        makeCodesHelper(root.left, CharacterCodes, code + "0");
        makeCodesHelper(root.right, CharacterCodes, code + "1");
    }

// ------------------------------- To make the code for Character and return that HashMap -----------------------------------
    public static HashMap<Character, String> makeCodes(HuffmanNode rootHuffmanNode){
        HashMap<Character, String> CharacterCodes = new HashMap<>();
        makeCodesHelper(rootHuffmanNode,CharacterCodes,"");
        return CharacterCodes;
    }

// ------------------------------- By using the Character codes we will encode by 1's and 0's of the original charcaters ----
    public static String encodedDataAndReturnString(String itemPath,HashMap<Character,String> CharacterCodes){
        StringBuilder encodedData = new StringBuilder("");
        try (FileReader reader = new FileReader(itemPath)) {
            int character;
            while ((character = reader.read()) != -1) {
                encodedData.append(CharacterCodes.get((char) character));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return encodedData.toString();
    }

// ----------------------------------- This is the function it takes item's path to be compress that file ----------------
    // ------------------------------- And puts encoded data and huffman root to one pair object -------------------------
    public static PairOfEncodedAndHuffmanRoot compressAndReturnPair(String itemPath){

        HashMap<Character, Integer> frequencyMap = makeFrequencyMap(itemPath);
        HuffmanNode rootHuffmanNode = makeHuffmanTree(frequencyMap);
        HashMap<Character, String> CharacterCodes = makeCodes(rootHuffmanNode);
        String encoded = encodedDataAndReturnString(itemPath,CharacterCodes);
        PairOfEncodedAndHuffmanRoot pairObj = new PairOfEncodedAndHuffmanRoot(encoded,rootHuffmanNode);
        return pairObj;
    }

// --------------------------- This is the Recurssive function call to create file directory structure --------------------
// --------------------------- LinkedList act as file and folder storage and looks like a tree ----------------------------
    public static LinkedListNode CreateAndCompress(List<String> items, String srcPath){
        LinkedList LL = new LinkedList();
        for(String item : items){
            String itemPath = srcPath + "\\" + item;
            File file = new File(itemPath);
            if(file.isFile()){
                PairOfEncodedAndHuffmanRoot pairObject = compressAndReturnPair(itemPath);
                LinkedListNode LLNodeObj = new LinkedListNode(item,pairObject,null);
                LL.addLinkedListNode(LLNodeObj);
            } else if (file.isDirectory()) {
                List<String> diritems = getDirItems(itemPath);
                LinkedListNode headRoot = CreateAndCompress(diritems,itemPath);
                LinkedListNode LLNodeObj = new LinkedListNode(item,null,headRoot);
                LL.addLinkedListNode(LLNodeObj);
            }
        }
        return LL.headRoot;
    }

// ------------------------------------------- Creating a Directory in specified path ------------------------------------

    public static void createDirectory(String path){
        Path directoryPath = Paths.get(path);
        try{
            Files.createDirectory(directoryPath);
        }
        catch(Exception e){
            System.out.println("Error Creating Directory : " + e.getMessage());
        }
    }

// --------------- Adding character to decoded StringBuffer ------------------------------------------------------------
    private static void addToAns(HuffmanNode leafNodeHuffman, StringBuilder decoded) {
        char ch = leafNodeHuffman.data;
        decoded.append(ch);
    }

// ---------------------- Adding a character by traversing in the tree -------------------------------------------------
    private static int addChar(int start, StringBuilder decoded, HuffmanNode root, String encodedData) {
        if (root.left == null && root.right == null) {
            addToAns(root, decoded);
            return ++start;
        }
        while (root.left != null && root.right != null) {
            char c = encodedData.charAt(start);
            if (c == '0') {
                root = root.left;
            } else {
                root = root.right;
            }
            start++;
        }
        addToAns(root, decoded);
        return start;
    }

// ----------------------------- To Write the original data back to Specified path by decompressing by tree ------------
// ----------------------------- and encoded data String ---------------------------------------------------------------
    public static void decompressHelper(String encoded,HuffmanNode rootHuffman,String itemPath){
        int start = 0;
        StringBuilder decoded = new StringBuilder();
        while (start < encoded.length()) {
            start = addChar(start, decoded, rootHuffman, encoded);
        }
        String decodedString = decoded.toString();
        try (FileWriter writer = new FileWriter(itemPath)) {
            writer.write(decodedString);
            System.out.println("Successfully written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

// ------------------------------- This is the Decompression RECURSSIVE function to write the original data ------------
// ------------------------------- Into the destination given by the user ----------------------------------------------

    public static void decompressAndWriteIntoDestination(LinkedListNode headRoot, String destPath){
        while(headRoot != null){
            String itemPath = destPath + "\\" + headRoot.itemName;
            if(headRoot.headRoot == null){
                String encoded = headRoot.encodedAndRoot.encoded;
                HuffmanNode rootHuffman =headRoot.encodedAndRoot.root;
                decompressHelper(encoded,rootHuffman,itemPath);
            }
            else{
                createDirectory(itemPath);
                decompressAndWriteIntoDestination(headRoot.headRoot,itemPath);
            }
            headRoot = headRoot.next;
        }
    }

// ---------------------------------------------- Checking whether user has given the valid source address or not ------

    public static void checkPathValid(String srcPath){
        File file = new File(srcPath);
        if (!(file.exists())) {
            System.out.println("Please enter proper Source address");
            System.exit(0);
        }
    }

// ------------------------------driver code ---------------------------------------------------------------------------

    public static void main(String[] args){

        System.out.println("*********************** Welcome to File Zipper project ************************");
        System.out.println();
        System.out.println("Enter the source path of the items :- ");
        Scanner sc = new Scanner(System.in);
        String srcPath = sc.nextLine();
        checkPathValid(srcPath);
        List<String> items = getItemNames(sc, srcPath);
        System.out.println("Enter the destination path to extract the zipped items :- ");
        String destPath = sc.nextLine();
        LinkedListNode headRoot = CreateAndCompress(items, srcPath);
        decompressAndWriteIntoDestination(headRoot,destPath);
        System.out.println("Items are Extracted successfully in the specified location");
    }
}
