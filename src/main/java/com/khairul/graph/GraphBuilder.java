package com.khairul.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphBuilder {

    private GraphBuilder(){}

    public static Graph createBy(Set<Edge> edges) {
        Map<Node, Set<Edge>> adj = prepareNodeWithAdj(edges);
        return new Graph(adj);
    }

    private static Map<Node, Set<Edge>> prepareNodeWithAdj(Set<Edge> edges) {

        Set<Node> nodes = new HashSet();
        edges.forEach(edge -> {
            nodes.add(edge.getFrom().copy());
            if (edge.getTo() != null) {
                nodes.add(edge.getTo().copy());
            }
        });

        Map<Node, Set<Edge>> nodeAdjacency = new HashMap();
        nodes.forEach(node -> {
            Set<Edge> adjs = edges.stream()
                    .filter(edge -> edge.getFrom().equals(node))
                    .filter(edge -> edge.getTo() != null)
                    .collect(Collectors.toSet());
            nodeAdjacency.put(node, adjs);
        });

        return nodeAdjacency;
    }
}
