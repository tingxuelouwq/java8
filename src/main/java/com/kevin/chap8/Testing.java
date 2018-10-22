package main.java.com.kevin.chap8;

import org.junit.Test;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @类名: Testing
 * @包名：com.kevin.cha8
 * @作者：kevin[wangqi2017@xinhua.org]
 * @时间：2018/4/18 9:30
 * @版本：1.0
 * @描述：
 */
public class Testing {

    public static void main(String[] args) {

    }

    private static class Point {
        private int x;
        private int y;

        public static final Comparator<Point> compareByXAndThenY =
                Comparator.comparing(Point::getX).thenComparing(Point::getY);

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public static List<Point> moveAllPointsRightBy(List<Point> points, int x) {
            return points.stream()
                    .map(p -> new Point(p.getX() + x, p.getY()))
                    .collect(Collectors.toList());
        }
    }

    @Test
    public void testComparingTwoPoints() throws Exception {
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);
        int result = Point.compareByXAndThenY.compare(p1 , p2);
        assertEquals(-1, result);
    }

    @Test
    public void testMoveAllPointsRightBy() throws Exception{
        List<Point> points =
                Arrays.asList(new Point(5, 5), new Point(10, 5));
        List<Point> expectedPoints =
                Arrays.asList(new Point(15, 5), new Point(20, 5));
        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
        assertEquals(expectedPoints, newPoints);
    }
}
