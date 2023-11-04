package it.unibo.generics.graph.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {
    Map<N,Set<N>> edges = new LinkedHashMap<>();

    public void addNode(N node) {
        //se il nodo non esiste nella mappa (grafo) lo aggiungo
        if (node != null && !edges.containsKey(node)) {
            edges.put(node, new HashSet<>());
        }
    }

    public void addEdge(N source, N target) {
        if(source != null && target != null && edges.containsKey(source) && edges.containsKey(target)) {
            edges.get(source).add(target);
        }
    }

    public Set<N> nodeSet() {
        return new HashSet<>(edges.keySet());
    }

    public Set<N> linkedNodes(N node) {
        return edges.getOrDefault(node, new HashSet<>());
        
    }

    public List<N> getPath(N source, N target) {
        return BreadthFirstSearch(source, target);
    }

    private List<N> buildPath(N source, N target, Map<N,N> parent) {
        List<N> path = new LinkedList<>();
        var curr = target;
        while (curr != null) {
            path.add(0, curr); //aggiungo in testa
            curr = parent.get(curr); //recupero il parente del nodo e lo aggiorno
        }
        return path;
    }

    private List<N> BreadthFirstSearch(N source, N target) {
        if (!edges.containsKey(source) || !edges.containsKey(target)) {
            return Collections.emptyList(); // Return an empty list if either source or target is not in the graph
        }
        Queue<N> queue = new LinkedList<>();
        Map<N, N> parent = new LinkedHashMap<>();
        Set<N> visited = new HashSet<>();

        queue.add(source);
        visited.add(source);

        while(!queue.isEmpty()) {
            var curr = queue.poll();

            if(curr.equals(target)) {
                break;
            }

            for (N neighbor : linkedNodes(curr)) {
                if(!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parent.put(neighbor, curr);
                }
            }
        }

        //se esiste un percorso da sorgente a destinazione lo costruisco
        return visited.contains(target) ? buildPath(source, target, parent) : Collections.emptyList();

    }
}
