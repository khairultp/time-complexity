package com.khairul.graph;

import static org.junit.Assert.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Graph {

    private Map<Node, Set<Edge>> nodeAdjacency;
    private Type type;
    private int size;

    public Graph(Map<Node, Set<Edge>> nodeAdjacency) {
        assertNotNull("nodeAdjacency is required !", nodeAdjacency);
        this.nodeAdjacency = nodeAdjacency;
        size = nodeAdjacency.size();
    }

    public boolean isAcyclic() {
        if (type == null) {
            Map<Node, Set<Edge>> copyAdj = new HashMap<>(nodeAdjacency);
            Set<Node> foundedLeafs = findAllLeafNodes(copyAdj);
            if (foundedLeafs.isEmpty() || foundedLeafs.size() != size) {
                type = Type.CYCLIC;
            } else {
                type = Type.ACYCLIC;
            }
        }
        return type == Type.ACYCLIC;
    }

    public Map<Node, Set<Edge>> getNodeAdjacency() {
        return nodeAdjacency;
    }

    private Set<Node> findAllLeafNodes(Map<Node, Set<Edge>> nodeEdges) {

        Set<Node> foundedLeaf = getLeaf(nodeEdges);

        if (foundedLeaf.isEmpty()) {
            return Collections.emptySet();
        }

        Map<Node, Set<Edge>> slicingEdge = removeLeafNode(nodeEdges, foundedLeaf);
        foundedLeaf.addAll(findAllLeafNodes(slicingEdge));
        return foundedLeaf;
    }

    private Predicate<Map.Entry<Node, Set<Edge>>> isALeaf() {
        return entry -> entry.getValue().isEmpty();
    }

    private Set<Node> getLeaf(Map<Node, Set<Edge>> nodeEdges) {
        return nodeEdges.entrySet().stream()
                .filter(isALeaf())
                .map(item -> item.getKey())
                .collect(Collectors.toSet());
    }

    private Map<Node, Set<Edge>> removeLeafNode(Map<Node, Set<Edge>> nodeEdges, Set<Node> foundedLeaf) {

        Predicate<Edge> isNotALeaf = edge -> !foundedLeaf.contains(edge.getTo());

        return nodeEdges.entrySet().stream()
        .filter(isALeaf().negate())
        .map(item -> {
            Set<Edge> partialEdge = item.getValue().stream().filter(isNotALeaf).collect(Collectors.toSet());
            item.setValue(partialEdge);
            return item;
        })
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
