package ctci.Ch16;

public class Line {
    double slope, yIntercept;

    public Line(Point p1, Point p2) {
        this.slope = (p1.y - p2.y) / (p1.x - p2.x);
        this.yIntercept = p1.y - this.slope * p1.x;
    }

    public static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void setLocation(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("(%.2g", x))
                    .append(String.format(", %.2g)", y));
            return sb.toString();
        }

        // only use when they are all on the same line
        public boolean isBetween(Point p1, Point p2) {
            return isBetween(p1.x, this.x, p2.x) && isBetween(p1.y, this.y, p2.y);
        }

        public static boolean isBetween(double start, double middle, double end) {
            if (start <= end)
                return start <= middle && middle <= end;
            else
                return end <= middle && middle <= start;
        }

        public static void swap(Point p1, Point p2) {
            double x = p1.x;
            double y = p1.y;
            p1.setLocation(p2.x, p2.y);
            p2.setLocation(x, y);
        }
    }
}
