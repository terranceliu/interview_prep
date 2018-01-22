package ctci.Ch8;

import java.util.*;

public class Box {
    public int h;
    public int w;
    public int d;

    public Box(int height, int depth, int width) {
        h = height;
        w = width;
        d = depth;
    }

    public boolean canBeAbove(Box box) {
        return this.h < box.h && this.w < box.w && this.d < box.d;
    }


}
