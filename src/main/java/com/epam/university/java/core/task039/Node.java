package com.epam.university.java.core.task039;
/*
 * Created by Laptev Egor 13.11.2020
 * */

public class Node implements Comparable<Node> {
    private final Character character;
    private final int freq;
    private Node left;
    private Node right;

    /**
     * Constructor #1.
     *
     * @param character character
     * @param freq weight
     */
    public Node(Character character, int freq) {
        this.character = character;
        this.freq = freq;
    }

    /**
     * Constructor #2.
     *
     * @param character character
     * @param freq weight
     * @param left left node
     * @param right right node
     */
    public Node(Character character, int freq, Node left, Node right) {
        this.character = character;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public char getCharacter() {
        return character;
    }

    public int getFreq() {
        return freq;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    /**
     * Get character encoding.
     *
     * @param ch character to encode
     * @param str string to accumulate codes
     * @return encoding
     */
    public String getCodeForChar(Character ch, String str) {
        if (this.character == ch) {
            return str;
        } else {
            if (left != null) {
                String curr = left.getCodeForChar(ch, str + "0");
                if (curr != null) {
                    return curr;
                }
            }
            if (right != null) {
                String curr = right.getCodeForChar(ch, str + "1");
                if (curr != null) {
                    return curr;
                }
            }
        }
        return null;
    }

    @Override
    public int compareTo(Node o) {
        int result = o.getFreq() - this.getFreq();
        if (result == 0) {
            return o.getCharacter() - this.getCharacter();
        }
        return result;
    }
}
