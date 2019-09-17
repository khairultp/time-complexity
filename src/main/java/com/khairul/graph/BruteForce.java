package com.khairul.graph;

import java.util.OptionalInt;
import java.util.Set;

public class BruteForce implements PathService {

    private final Graph graph;

    public BruteForce(Graph graph) {
        this.graph = graph;
    }

    @Override
    public int optimalWeight(Node origin) {
        if (!graph.isAcyclic()) {
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
