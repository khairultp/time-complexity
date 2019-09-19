package com.khairul.graph;

import com.khairul.graph.model.Edge;
import com.khairul.graph.model.Node;
import com.khairul.graph.model.Type;

import static org.junit.Assert.assertNotNull;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Graph {

    private final Map<Node, Set<Edge>> nodeAdjacency;
    private final int nodeNumbers;
    private Type type;

    public Graph(Map<Node, Set<Edge>> nodeAdjacency) {
        assertNotNull("nodeAdjacency is required !", nodeAdjacency);
        this.nodeAdjacency = nodeAdjacency;
        nodeNumbers = nodeAdjacency.keySet().size();
        type = isAcyclic() ? Type.ACYCLIC : Type.CYCLIC;
    }

    private boolean isAcyclic() {
        Map<Node, Set<Edge>> copyAdj = new HashMap<>(nodeAdjacency);
        Set<Node> foundedLeafs = findAllLeafNodes(copyAdj);
        if (foundedLeafs.isEmpty() || foundedLeafs.size() != nodeNumbers) {
            return false;
        }
        return true;
    }

    public Map<Node, Set<Edge>> getNodeAdjacency() {
        return nodeAdjacency;
    }

    public Type getType() {
        return type;
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
