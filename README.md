🗜️ FileZipper — Huffman Compression & Decompression

📘 Overview

FileZipper is a Java-based file compression and decompression tool that implements Huffman Coding, a lossless data compression algorithm.
It allows users to select files and directories from a source path, compress them efficiently, and then decompress them back to their original form.

The project uses a custom LinkedList-based tree structure to manage files and directories, and integrates Huffman Encoding to minimize storage usage.

🚀 Features

📂 Compress multiple files and folders recursively

🌳 Huffman Tree generation for character frequency optimization

🔄 Decompression using stored Huffman trees

🧭 Interactive console-based UI

🧱 Supports directory structures

⚡ Efficient encoding and decoding using bit-level representation

🧩 Technologies Used
Technology	                            Purpose
Java (Core)	                              Main programming language

Collections Framework	                    Data structures like HashMap, LinkedList, PriorityQueue

File I/O (java.io, java.nio)             	Reading and writing files/directories

OOP Principles	                          Encapsulation, abstraction, recursion for directory traversal


⚙️ How It Works

1️⃣ Compression Process

Reads the given file and calculates character frequencies

Builds a Huffman Tree based on these frequencies

Generates binary codes for each character (0 and 1)

Encodes the file into a compressed binary format

Stores both the encoded data and Huffman Tree

2️⃣ Decompression Process

Reads the stored encoded string

Uses the corresponding Huffman Tree to decode

Writes the original data back into the specified destination path

🧠 Core Classes & Responsibilities

Class	Responsibility

FileZipper	Main class that handles user input, compression, and decompression logic

HuffmanNode	Represents a node in the Huffman Tree

LinkedList / LinkedListNode	Custom linked list to represent folder-file hierarchy

PairOfEncodedAndHuffmanRoot	Stores the encoded data string and corresponding Huffman Tree root

🖥️ How to Run
✅ Prerequisites

Java 8 or later installed
IDE like IntelliJ IDEA / Eclipse / VS Code, or use command-line

▶️ Steps

Clone or download the project
Open a terminal in the project directory
Compile the code:


💡 Example Run
*********************** Welcome to File Zipper project ************************

Enter the source path of the items :-
C:\Users\Nandan\Documents\source

------------------------------------------------------------------
Option 1:---> To add the item for zipping
Option 2:---> Quit
------------------------------------------------------------------
Enter the option :- 1
Enter the item :- test.txt

Enter the destination path to extract the zipped items :-
C:\Users\Nandan\Documents\unzipped

Items are Extracted successfully in the specified location


📂 Example Output Structure

Destination/
│
└── test.txt   # Original file restored after decompression


👨‍💻 Author

Nandan Kumar T V

GitHub: @NandankumarTV
