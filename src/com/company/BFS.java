package com.company;

import java.util.*;
import java.util.stream.Collectors;
import javax.swing.Timer;

/**
 * Created by Pouya Payandeh on 8/12/2016.
 */
public class BFS
{
    int depth = Integer.MAX_VALUE;
    private int[][] grid;
    int w ,h;
    final Set<Vector2D> marked  =new HashSet<>();
    Deque<SearchNode<Vector2D>> deque;
    private final ArrayList<Vector2D> solution = new ArrayList<>();

    synchronized public Set<Vector2D> getMarked()
    {
            return marked;
    }
    void init(Vector2D start, Vector2D end , int d)
    {
        marked.clear();
        solution.clear();
        deque = new LinkedList<>();
        deque.add(new SearchNode<>(start, null, 0, 0));
    }
    synchronized  Set<Vector2D>inStack()
    {
        Set<Vector2D> s = deque.stream().map(e -> e.data).collect(Collectors.toSet());
        return s;
    }
    void animatedFindSolution(Vector2D start, Vector2D end , int d)
    {
        init(start,end,d);
        new Timer(100,e -> {
            ArrayList<Vector2D> o = iteration( end, d);
            if(o != null)
            {
                solution.addAll(o);
                deque.clear();
            }
        }).start();
    }
    boolean findSolution(Vector2D start, Vector2D end , int d)
    {
        init(start,end,d);
        while (!deque.isEmpty())
        {
            ArrayList<Vector2D> o = iteration( end, d);
            if(o != null)
            {
                solution.addAll(o);
                deque.clear();
                return true;
            }
        }
        return false;
    }
    synchronized ArrayList<Vector2D> iteration( Vector2D end , int d)
    {
            if (!deque.isEmpty())
            {
                SearchNode<Vector2D> top = deque.poll();
                if (top.data.equals(end))
                {
                    return createPath(top);
                } else
                {
                    if (top.depth < d)
                    {
                        ArrayList<SearchNode<Vector2D>> childs = successors(top);
                        marked.add(top.data);
                        Set<Vector2D> s=childs.stream().map(v-> v.data).collect(Collectors.toSet());
                        marked.addAll(s);
                        deque.addAll(childs);
                    }
                }

            }
            return null;
    }
    private ArrayList<Vector2D> createPath(SearchNode<Vector2D> top)
    {
        SearchNode<Vector2D> node = top;
        ArrayList<Vector2D> out = new ArrayList<>();
        while(node != null)
        {
            out.add(node.data);
            node = node.parent;
        }
        Collections.reverse(out);
        return out;
    }
    synchronized Vector2D getTop()
    {
        if(deque != null && !deque.isEmpty())
            return deque.peek().data;
        return null;
    }
    ArrayList<SearchNode<Vector2D>> successors(SearchNode<Vector2D> me )
    {
        Vector2D[] vd = {
                new Vector2D(0,1),
                new Vector2D(1,0),
                new Vector2D(-1,0),
                new Vector2D(0,-1)
        };
        ArrayList<SearchNode<Vector2D>> out = new ArrayList<>();
        for(int i = 0 ; i < 4; i++)
        {
            Vector2D child = Vector2D.add(me.data, vd[i]);
            if(isValid(child) && !marked.contains(child) )
                out.add(new SearchNode<>(child,me,me.depth+1,0));
        }
//        Collections.shuffle(out);
        return out;
    }

    private boolean isValid(Vector2D child)
    {
        if(child.x < 0|| child.x >= h || child.y < 0 || child.y >= w)
            return false;
        if(grid[child.x][child.y] == Grid.BLOCKED)
            return false;
        return true;
    }

    public void setGrid(int[][] grid)
    {
        this.grid = grid;
        w = grid[0].length;
        h = grid.length;
    }

    synchronized public ArrayList<Vector2D> getSolution()
    {
            return solution;
    }
}
