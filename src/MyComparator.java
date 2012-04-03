import java.awt.*;
import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: Haw
 * Date: 03.04.12
 * Time: 1:34
 * To change this template use File | Settings | File Templates.
 */
public class MyComparator implements Comparator<MyPoint> {

    public int compare(MyPoint p, MyPoint q) {
        MyPoint vp = new MyPoint(p.minus(MyConst.originPt));
        MyPoint vq = new MyPoint(q.minus(MyConst.originPt));
        double pPolar = vp.polarAngle();
        double qPolar = vq.polarAngle();
        if (pPolar < qPolar) return -1;
        if (pPolar > qPolar) return 1;
        if (vp.length() < vq.length()) return -1;
        if (vp.length() > vq.length()) return 1;
        return 0;

    }
}
