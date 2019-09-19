package com.khairul.graph.impl;

import com.khairul.graph.Graph;
import com.khairul.graph.PathService;
import com.khairul.graph.model.Edge;
import com.khairul.graph.model.Node;
import com.khairul.graph.model.Type;

import java.util.OptionalInt;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class BruteForce implements PathService {

    private final Graph graph;

    public BruteForce(Graph graph) {
        this.graph = graph;
    }

    @Override
    public int optimalPath(Node origin) {
        assertNotNull("origin is required !", origin);

        if (graph.getType() != Type.ACYCLIC) {
            throw new IllegalStateException("Cyclic graph is not supported !");
        }
        Set<Edge> partialAdj = graph.getNodeAdjacency().get(origin);
        OptionalInt total = partialAdj.stream().mapToInt(edge -> traceWeight(edge, graph)).max();
        return total.orElse(origin.getWeight());
    }

    private int traceWeight(Edge edge, Graph graph) {
        Node destNode = edge.getTo();
        Set<Edge> nextPartialAdj = graph.getNodeAdjacency().get(destNode);
        OptionalInt maxWeight = nextPartialAdj.stream().mapToInt(traceEdge -> traceWeight(traceEdge, graph)).max();
        return edge.getFrom().getWeight() + maxWeight.orElse(destNode.getWeight());
    }
}
