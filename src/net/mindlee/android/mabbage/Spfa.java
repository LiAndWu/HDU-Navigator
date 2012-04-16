package net.mindlee.android.mabbage;
import java.util.*;

public class Spfa {
	int inf = 0x7fffffff;
	public static int NV = 300;
	public static int NE = 500;
	int n, size;
	public static int[] dis = new int[NV];
	int[] head = new int[NV];
	boolean[] in = new boolean[NV];
	public static int[] pre = new int[NV];
	
	class Edge {
		int v, w, next;
		Edge() {};
		Edge (int V, int NEXT, int W) {
			v = V;
			next = NEXT;
			w = W;
		}
	}
	
	Edge[] E = new Edge[NE];
    void init(int nn) {
        n = nn;
        size = 0;
        for (int i = 0; i <= n; i++) {
            head[i] = -1;
            in[i] = false;
            dis[i] = inf;
            pre[i] = i;
        }
    }
	
    void insert(int u, int v, int w) {
        E[size] =  new Edge(v, head[u], w);
        head[u] = size++;
        E[size] =  new Edge(u, head[v], w);
        head[v] = size++;
    }
 
    boolean relax(int u, int v, int w) {
        if (dis[v] == inf || dis[u] + w < dis[v]) {
            dis[v] = dis[u] + w;
            pre[v] = u;
            return true;
        }
        return false;
    }
    
    int spfa(int src, int des) {
    	Queue<Integer> que = new LinkedList<Integer>();
        dis[src] = 0;
        que.offer(src);
        in[src] = true;
        while (que.peek()!= null) {
            int u = que.poll();
            in[u] = false;
            for (int i = head[u]; i != -1; i = E[i].next) {
                int v = E[i].v;
                if (relax(u, v, E[i].w) && !in[v] ) {
                    in[v] = true;
                    que.offer(v);
                }//if
            }//for
        }//while
       return dis[des];
    }//SFPA
}
