import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: Haw
 * Date: 02.04.12
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
public class ConvexSet {
    public static void main(String args[]) {
        final int N = 600;
        JFrame f = new JFrame("Convex set");
        f.setSize(N,N);
        f.setLocation(N,N);
        f.setResizable(false);
        JPanel plot = new JPanel(){
            ArrayList<MyPoint> points = new ArrayList<MyPoint>();
            boolean drawPoint = false;
            boolean plotSet = false;
            ArrayList<MyPoint> grahamScan(ArrayList<MyPoint> pts) {
                //step 1
                int m = 0;
                for (int i = 1; i<pts.size();i++)
                    if ( (pts.get(i).y < pts.get(m).y) ||
                       ( (pts.get(i).y == pts.get(m).y) && (pts.get(i).x < pts.get(m).x)))
                               m = i;
                    Collections.swap(pts,0,m);
                MyConst.originPt = new MyPoint(pts.get(0));
                
                //step2
                Collections.sort(pts,new MyComparator());
                //step3
                int i;
                for(i = 1; pts.get(i).Classify(pts.get(0),pts.get(i)) == MyConst.BEYOND; i++);
                Stack<MyPoint> s = new Stack<MyPoint>();
                s.push(pts.get(0));
                s.push(pts.get(i));
                //step4
                for(i = i+1; i< pts.size();i++) {
                    while(pts.get(i).Classify(s.get(s.size()-2), s.lastElement()) != MyConst.LEFT)
                        s.pop();
                    s.push(pts.get(i));                    
                }
                pts.clear();
                while(!s.empty())
                    pts.add(s.pop());
                return pts;
            }
            {addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    points.add(new MyPoint(e.getX(),e.getY()));
                    if (e.getClickCount() == 1){
                        drawPoint = true;
                    } else {
                        plotSet = true;
                        drawPoint = true;
                    }
                    repaint();
                }
            });}
            public void paint(Graphics g) {
                super.paint(g);
                if (drawPoint == true) {
                    g.setColor(Color.RED);
                    for(MyPoint item : points) {
                        g.fillOval((int)item.x-3,(int)item.y-3,6,6);
                    }
                    drawPoint = false;
                }
                if (plotSet == true) {
                    System.out.println("I'm double click!");
                    points.remove(points.size()-1);
                    ArrayList<MyPoint> curr = new ArrayList<MyPoint>(grahamScan(points));
                    g.setColor(Color.BLUE);

                    g.drawLine((int)curr.get(0).x,(int)curr.get(0).y,(int)curr.get(curr.size()-1).x,(int)curr.get(curr.size()-1).y);
                    for(int i = 0; i< curr.size()-1;i++) {
                        g.fillOval((int)curr.get(i).x-3,(int)curr.get(i).y-3,6,6);
                        g.drawLine((int)curr.get(i).x,(int)curr.get(i).y,(int)curr.get(i+1).x,(int)curr.get(i+1).y);
                    }
                    plotSet = false;
                    points.clear();
                }
            }
        };
        f.add(plot);
        f.setVisible(true);
    }
}
