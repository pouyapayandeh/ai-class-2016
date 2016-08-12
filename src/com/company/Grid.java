package com.company;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Pouya Payandeh on 7/31/2016.
 */
public class Grid implements DrawableComponent
{
    static final int PLAIN = 0;
    static final int START = 1;
    static final int FINISH = 2;
    static final int BLOCKED = -1;

    int grid[][];
    int h, w;
    Container container;
    private Vector2D start;
    private Vector2D fin;
    BFS bfs = new BFS();
    private ArrayList<Vector2D> solution = new ArrayList<>();

    void intit(String path) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File(path));
        h = scanner.nextInt();
        w = scanner.nextInt();
        grid = new int[h][w];
        scanner.nextLine();
        for (int i = 0; i < h; i++)
        {
            String line = scanner.nextLine();
            for (int j = 0; j < w; j++)
            {
                char c = line.charAt(j);
                switch (c)
                {
                    case 'O':
                        grid[i][j] = PLAIN;
                        break;
                    case '*':
                        grid[i][j] = START;
                        start = new Vector2D(i,j);
                        break;
                    case '+':
                        grid[i][j] = FINISH;
                        fin = new Vector2D(i,j);
                        break;
                    case '#':
                        grid[i][j] = BLOCKED;
                        break;
                }
            }
        }
    }

    @Override
    public void setParent(Container container)
    {
        this.container = container;
    }

    @Override
    public void draw(Graphics g)
    {
        int dx = container.getWidth() / w;

        int dy = container.getHeight() / h;

        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {

                switch (grid[i][j])
                {
                    case PLAIN:
                        g.setColor(Color.ORANGE);
                        break;
                    case START:
                        g.setColor(Color.GREEN);
                        break;
                    case FINISH:
                        g.setColor(Color.CYAN);
                        break;
                    case BLOCKED:
                        g.setColor(Color.BLACK);
                        break;
                }
                solution = bfs.getSolution();
                Set<Vector2D> marked = bfs.inStack();
                Vector2D pos = new Vector2D(i, j);
                int index = solution.indexOf(pos);
                if(marked.contains(pos) && solution.size()==0)
                {
                    g.setColor(Color.GRAY);
                }
                if(index != -1)
                {
                    Color c = g.getColor();
                    g.setColor(c.darker());
                }
                Vector2D top = bfs.getTop();
                if(top != null && top.equals(pos))
                {
                    g.setColor(Color.RED);
                }

                g.fillRect(j * dx, i * dy, dx - 5, dy - 5);
                if(index != -1)
                {
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(index),j * dx, (i+1) * dy-5);
                }

            }
        }
    }

    public void findSolution()
    {
        bfs.setGrid(grid);
        bfs.findSolution(start,fin,30);
    }
    public void animatedFindSolution()
    {
        bfs.setGrid(grid);
        bfs.animatedFindSolution(start,fin,30);
    }
}
