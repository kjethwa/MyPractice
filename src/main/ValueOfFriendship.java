package main;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class ValueOfFriendship {
    
    static public int[] parent;
    static public long[] allFriends;
    static long total = 0, lastTotal = 0;
    static boolean[] visited;
	
    static Map<Integer,Long> map = new HashMap<Integer, Long>();
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            parent = new int[n+1];
            allFriends = new long[n+1];
            visited = new boolean[n+1];
            map = new HashMap<Integer, Long>();
            Arrays.fill(visited, false);
            Arrays.fill(parent,-1);
            Arrays.fill(allFriends,1);
            int m = in.nextInt();
            total = 0; lastTotal = 0;
            for(int a1 = 0; a1 < m; a1++){
                int x = in.nextInt();
                int y = in.nextInt();
                                
                if (find(parent, x) != find(parent, y)) {
                	visitUnion(x,y);
				}                
            }
            
            for(int i=1;i<=n;i++){
				if (!visited[i]) {
					int x = visitFind(parent, i);
					if (x!=-1 && map.get(x) == null) {
						if(allFriends[x]>1)
							map.put(x, allFriends[x]);
					}
				}
            }
           
            map = MapUtil.sortByValue(map);
            
            for(Integer key :  map.keySet()){
            	for(int i=1;i<map.get(key);i++){
            		m--;
            		total =total + lastTotal+ 2*i;
            		lastTotal = lastTotal+ 2*i;
            	}
            }
            total = total + lastTotal*m;
            
            System.out.println(total);
        }
    }

	public static int find(int[] parent,int friend){
        if(parent[friend]==-1)
            return friend;
        return find(parent, parent[friend]);
    }
	
	public static int visitFind(int[] parent,int friend){
		if (!visited[friend]) {
			visited[friend] = true;
			if (parent[friend] == -1)
				return friend;
			return visitFind(parent, parent[friend]);
		}
		return -1;
    }
    
	public static void visitUnion(int friA, int friB) {
		int x = find(parent, friA);
		int y = find(parent, friB);

		parent[x] = y;
		allFriends[y] = allFriends[x] + allFriends[y];
	}
        
    static class MapUtil
    {
        public static <K, V extends Comparable<? super V>> Map<K, V> 
            sortByValue( Map<K, V> map )
        {
            List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>( map.entrySet() );
            Collections.sort( list, new Comparator<Map.Entry<K, V>>()
            {
                public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
                {
                    return (o2.getValue()).compareTo( o1.getValue() );
                }
            } );

            Map<K, V> result = new LinkedHashMap<K, V>();
            for (Map.Entry<K, V> entry : list)
            {
                result.put( entry.getKey(), entry.getValue() );
            }
            return result;
        }
    }
    
}
