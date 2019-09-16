package com.khairul.graph;

import java.util.Objects;

import static org.junit.Assert.*;

public class Node {
    private String label;
    private int weight;

    private Node(String label, int weight) {
        this.label = label;
        this.weight = weight;
    }

    public static Node of(String label, int weight) {
        assertNotNull("label is required !", label);
        assertNotNull("weight is required !", weight);
        return new Node(label, weight);
    }

    public String getLabel() {
        return label;
    }

    public int getWeight() {
        return weight;
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
}
