package com.company;

import java.util.Vector;

/**
 * Created by Pouya Payandeh on 8/12/2016.
 */
public class Vector2D
{
    int x , y;

    public Vector2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D()
    {
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        if (x != vector2D.x) return false;
        return y == vector2D.y;

    }

    @Override
    public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    static Vector2D add(Vector2D a, Vector2D b)
    {
        Vector2D o = new Vector2D();
        o.x = a.x+b.x;
        o.y = a.y+ b.y;
        return o;

    }
    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
