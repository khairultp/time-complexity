package com.khairul.graph;

import com.khairul.graph.model.Edge;
import com.khairul.graph.model.Node;
import com.khairul.graph.model.Type;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void GetType_Given_Graph_Have_A_Leaf_Node_AndNo_Cycle_Return_Acyclic() {
        //Arrange
        Node n1 = Node.of("1", 1);
        Node n2 = Node.of("2", 2);
        Node n3 = Node.of("3", 3);
        Node n4 = Node.of("4", 4);
        Node n5 = Node.of("5", 5);
        Node n6 = Node.of("6", 6);

        Edge e12 = Edge.of(n1, n2);
        Edge e23 = Edge.of(n2, n3);
        Edge e24 = Edge.of(n2, n4);
        Edge e3  = Edge.of(n3, null);
        Edge e45 = Edge.of(n4, n5);
        Edge e46 = Edge.of(n4, n6);
        Edge e56 = Edge.of(n5, n6);
        Edge e63 = Edge.of(n6, n3);

        Set<Edge> edges = new HashSet<>(Arrays.asList(e12,e23,e3,e24,e45,e46,e56,e63));
        Graph graph = GraphBuilder.createBy(edges);

        //Act
        Type result = graph.getType();

        //Assert
        assertEquals(Type.ACYCLIC, result);
    }

    @Test
    public void GetType_Given_Graph_Have_A_Leaf_Node_And_Cycle_Return_Cyclic() {
        //Arrange
        Node n1 = Node.of("1", 1);
        Node n2 = Node.of("2", 2);
        Node n3 = Node.of("3", 3);
        Node n4 = Node.of("4", 4);
        Node n5 = Node.of("5", 5);
        Node n6 = Node.of("6", 6);

        Edge e12 = Edge.of(n1, n2);
        Edge e23 = Edge.of(n2, n3);
        Edge e24 = Edge.of(n2, n4);
        Edge e3  = Edge.of(n3, null);
        Edge e45 = Edge.of(n4, n5);
        Edge e56 = Edge.of(n5, n6);
        Edge e63 = Edge.of(n6, n3);
        Edge e64 = Edge.of(n6, n4);

        Set<Edge> edges = new HashSet<>(Arrays.asList(e12,e23,e3,e24,e45,e56,e63,e64));
        Graph graph = GraphBuilder.createBy(edges);

        //Act
        Type result = graph.getType();

        //Assert
        assertEquals(Type.CYCLIC, result);
    }
}