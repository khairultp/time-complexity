package com.khairul.graph.model;

import java.util.Objects;

import static org.junit.Assert.assertNotNull;

public class Node {
    private final String label;
    private final int weight;

    private Node(String label, int weight) {
        this.label = label;
        this.weight = weight;
    }

    public static Node of(String label, int weight) {
        assertNotNull("label is required !", label);
        assertNotNull("weight is required !", weight);
        return new Node(label, weight);
    }

    public int getWeight() {
        return weight;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return weight == node.weight &&
                Objects.equals(label, node.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, weight);
    }

    public Node copy() {
        return new Node(label, weight);
    }
}
