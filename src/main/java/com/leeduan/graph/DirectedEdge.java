package com.leeduan.graph;

/**
 * An directed edge which points from tail to the head.
 * @param <T>
 */
class DirectedEdge<T> extends AbstractEdge<T> implements Edge<T> {

    public DirectedEdge(Vertex<T> tail, Vertex<T> head) {
        super(tail, head);
    }

    @Override
    public Vertex<T> getTail() {
        return getPair().get(0);
    }

    @Override
    public Vertex<T> getHead() {
        return getPair().get(1);
    }
}
