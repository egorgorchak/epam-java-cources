package com.epam.university.java.core.task039;
/*
 * Created by Laptev Egor 13.11.2020
 * */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Task039Impl implements Task039 {
    private Node node;

    @Override
    public Map<Character, String> getEncoding(Map<Character, Integer> charFrequencies) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Map.Entry<Character, Integer> pair : charFrequencies.entrySet()) {
            Character key = pair.getKey();
            Integer value = pair.getValue();
            nodes.add(new Node(key, value));
        }
        node = treeBuilder(nodes);

        TreeMap<Character, String> resultMap = new TreeMap<>();
        for (Character character : charFrequencies.keySet()) {
            String codeForChar = node.getCodeForChar(character, "");
            resultMap.put(character, codeForChar);
        }
        return resultMap;
    }

    @Override
    public String getEncodedString(Map<Character, Integer> charFrequencies, String string) {
        Map<Character, String> encoding = getEncoding(charFrequencies);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            Character c = string.charAt(i);
            String s = encoding.get(c);
            result.append(s);
        }
        return result.toString();
    }

    @Override
    public String getDecodedString(Map<Character, Integer> charFrequencies, String encodedString) {
        Map<Character, String> encoding = getEncoding(charFrequencies);
        StringBuilder result = new StringBuilder();
        Node currentNode = node;
        for (int i = 0; i < encodedString.length(); i++) {
            char curr = encodedString.charAt(i);
            if (curr == '0') {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
            if (currentNode.getCharacter() != 0) {
                result.append(currentNode.getCharacter());
                currentNode = node;
            }
        }
        return result.toString();
    }

    /**
     * Tree builder.
     *
     * @param nodes list of nodes
     * @return a tree
     */
    private Node treeBuilder(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.remove(nodes.size() - 1);
            Node right = nodes.remove(nodes.size() - 1);
            Node parent = new Node((char) 0, left.getFreq() + right.getFreq(), left, right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
