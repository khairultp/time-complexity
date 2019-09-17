package com.khairul.graph;

import java.util.Objects;

import static org.junit.Assert.*;

public class Edge {

    private Node from;
    private Node to;

    private Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

    public static Edge of(Node from, Node to) {
        assertNotNull("from is required !", from);
        return new Edge(from, to);
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return from.equals(edge.from) &&
                Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
