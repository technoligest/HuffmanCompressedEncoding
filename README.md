# HuffmanCompressedEncoding

This work is licensed under a Creative Commons Attribution 3.0 Unported License.

Provided are all the java files along with with testing txt file.

The algorithm takes a text file and searches through it to figure order the characters found in it according to the number of their occurance. After that a huffman tree is build where characters that have a higher number of occurances are found closer to the root. This allows us for efficient binary encoding of a text file because the code for each character is calculated by the route from the root where each left branch is a zero and each right branch is a one.

The huffman tree file is saved so it can be reused to decode the file multiple times afterwards.

The main method in the prject simply takes in a filename, creates the huffman tree, encodes the file, and decodes it out. This is only for testing purposes, but the algorithm can be used in many ways and encorporated into other projects where encoding is needed. 

For any questions about the algorithm or the use of it please contact yaser.alkayale@dal.ca
