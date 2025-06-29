import java.util.*;

public class HuffmanCoding {

    class Node {
        char character;
        int frequency;
        Node left, right;

        public Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }
    }

    public void buildHuffmanTree(char[] chars, int[] freq) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.frequency));

        for (int i = 0; i < chars.length; i++) {
            pq.offer(new Node(chars[i], freq[i]));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            Node newNode = new Node('-', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;

            pq.offer(newNode);
        }

        System.out.println("Huffman Tree built");
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] freq = {5, 9, 12, 13, 16, 45};

        HuffmanCoding huffmanCoding = new HuffmanCoding();
        huffmanCoding.buildHuffmanTree(chars, freq);
    }
}

