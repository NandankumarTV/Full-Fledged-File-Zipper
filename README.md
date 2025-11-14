# FileZipper

FileZipper is a Java-based file compression and decompression utility
using **Huffman Coding**.\
It allows users to select files or folders from a source directory,
compress them, and extract them into a destination directory while
maintaining the folder structure.

------------------------------------------------------------------------

## 🚀 Features

-   Compress files using **Huffman Encoding**
-   Maintain directory structure using a linked-list--based tree
    representation
-   Decompress and reconstruct original files
-   User input for selecting specific files/folders
-   Recursive compression and extraction

------------------------------------------------------------------------

## 📁 Project Structure

Main components:

-   **makeFrequencyMap** -- Generates character frequency map\
-   **makeHuffmanTree** -- Builds Huffman Tree\
-   **makeCodes** -- Generates Huffman Codes\
-   **encodedDataAndReturnString** -- Converts file content to encoded
    bitstring\
-   **compressAndReturnPair** -- Returns encoded string + Huffman root\
-   **CreateAndCompress** -- Recursively compresses directories\
-   **decompressAndWriteIntoDestination** -- Reconstructs files and
    directories

------------------------------------------------------------------------

## ▶️ How to Run

1.  Compile the project:

```{=html}
<!-- -->
```
    javac FileZipper.java

2.  Run the program:

```{=html}
<!-- -->
```
    java FileZipper

3.  Enter the **source path** containing the items to compress\
4.  Select files/folders to zip\
5.  Enter the **destination path** for extraction

------------------------------------------------------------------------

## 📌 Example Input Flow

    Enter the source path of the items:
    C:\\Users\\MyFiles

    Option 1: Add item  
    Option 2: Quit

    Enter destination path to extract:
    D:\\ExtractedFiles

------------------------------------------------------------------------

## 🛠 Technologies Used

-   **Java**
-   **Huffman Coding Algorithm**
-   **File I/O**
-   **Recursion**
-   **Custom Linked List**

------------------------------------------------------------------------

## ⚠️ Notes

-   Only text-based file compression is currently supported\
-   Ensure valid file paths to avoid runtime errors\
-   Folder recursion is supported fully

------------------------------------------------------------------------

## 📄 License

This project is free to use and modify for educational purposes.
