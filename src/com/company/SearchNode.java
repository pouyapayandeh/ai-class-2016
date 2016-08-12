package com.company;

/**
 * Created by Pouya Payandeh on 8/12/2016.
 */
public class SearchNode<T>
{
    T data;
    SearchNode parent;
    int depth;
    double cost;

    public SearchNode(T data, SearchNode parent, int depth, double cost)
    {
        this.data = data;
        this.parent = parent;
        this.depth = depth;
        this.cost = cost;
    }

    public SearchNode(T data, SearchNode parent)
    {
        this.data = data;
        this.parent = parent;
        depth = parent.depth +1;

    }

    public SearchNode(T data)
    {
        this.data = data;
    }
}
