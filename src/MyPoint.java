/**
 * Created by IntelliJ IDEA.
 * User: Haw
 * Date: 03.04.12
 * Time: 1:05
 * To change this template use File | Settings | File Templates.
 */
public class MyPoint {
    public double x;
    public double y;
/*    static int orientation(MyPoint p0, MyPoint p1, MyPoint p2) {
        MyPoint a = p1.minus(p0);
        MyPoint b = p2.minus(p0);
        double sa = a.x*b.y - b.x*a.y;
        if (sa > 0.0)
            return 1;
        if (sa < 0.0)
            return -1;
        return 0;
    }
*/
    MyPoint (double _x, double _y) {
        this.x = _x;
        this.y = _y;
    }
    MyPoint (MyPoint p) {
        this.x = p.x;
        this.y = p.y;
    }
    MyPoint plus(MyPoint p) {
        return (new MyPoint(this.x + p.x, this.y + p.y));
    }
    MyPoint minus(MyPoint p) {
        return (new MyPoint(this.x - p.x, this.y - p.y));
    }
    double length(){
        return (Math.sqrt(x*x + y*y));
    }
    double polarAngle() {
        if ( (x == 0.0) && (y == 0.0) )
            return -1.0;
        if ( x == 0.0 )
            return ( (y>0.0) ? 90 : 270);
        double theta = Math.atan(y/x);
        theta *= 360/(2*Math.PI);
        if ( x>0.0 )
            return ( (y>= 0.0) ? theta : (360 + theta) );
        else
            return (180 + theta);
    }
    int Classify(MyPoint p0, MyPoint p1) {
        MyPoint p2 = this;
        MyPoint a = p1.minus(p0);
        MyPoint b = p2.minus(p0);
        double sa = a.x * b.y - b.x * a.y;
        if (sa > 0.0) return MyConst.LEFT;
        if (sa < 0.0) return MyConst.RIGHT;
        if ( (a.x * b.x < 0.0) || (a.y * b.y < 0.0)) return MyConst.BEHIND;
        if ( a.length() < b.length() ) return MyConst.BEYOND;
        if ( p0 == p2 ) return MyConst.ORIGIN;
        if ( p1 == p2 ) return MyConst.DESTINATION;
        return MyConst.BETWEEN;
    }
    @Override
    public String toString() {
        return (new String("x = " + (int)x + "; y = " + (int)y ));
    }

}
