package com.khairul.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Graph {

    private Set<Edge> edges;
    private GraphType type;

    private Graph(Set<Edge> edges) {
        this.edges = edges;
    }

    public static Graph of(Set<Edge> edges) {
        assertNotNull("edges is required !", edges);
        return new Graph(edges);
    }

    Predicate<Edge> hasNoEdge() {
        return edge -> edge.getTo() == null;
    }

    private Set<Node> findAllLeafNodes(Set<Edge> edges) {

        Set<Node> foundedLeafs = edges.stream()
        .filter(hasNoEdge())
        .map(edge -> edge.getFrom())
        .collect(Collectors.toSet());

        if (foundedLeafs.isEmpty()) {
            return Collections.emptySet();
        }

        Predicate<Edge> hasDestinationFoundedLeafNode = edge -> foundedLeafs.contains(edge.getTo());

        Set<Edge> removeLeafNode = edges.stream()
        .map(edge -> {
            if (hasDestinationFoundedLeafNode.test(edge)) {
                return Edge.of(edge.getFrom(), null);
            }
            return edge;
        })
        .filter(hasNoEdge().negate())
        .collect(Collectors.toSet());

        foundedLeafs.addAll(findAllLeafNodes(removeLeafNode));

        return foundedLeafs;
    }

    public boolean isAcyclic() {

        if (type == null) {
            leafNodes = findAllLeafNodes(edges);
            if (leafNodes.isEmpty()) {
                type = GraphType.CYCLIC;
            } else {
                type = GraphType.ACYCLIC;
            }
        }

        return type == GraphType.ACYCLIC;
    }
}
