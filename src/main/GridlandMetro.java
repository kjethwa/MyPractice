package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class GridlandMetro {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong(), M = sc.nextLong(), K = sc.nextLong();
        Map<Integer,TreeSet<TrainData>> map = new HashMap<Integer,TreeSet<TrainData>>();
        int r,c1,c2;
        long count=0;
        TreeSet<TrainData> list;
        TrainData tempTrainData;
        for (int i = 0; i < K; i++) {
            
            r = sc.nextInt();
            c1 = sc.nextInt();
            c2 = sc.nextInt();
            tempTrainData = new TrainData((long)c1,(long)c2); 
            if(map.get(r)==null){
                list = new TreeSet<TrainData>();
                list.add(tempTrainData);
                map.put(r,list);
            }
            else{
                list = map.get(r);
                list.add(tempTrainData);
            }
        }
        
        for (Integer i : map.keySet()) {
            long totalTrainLength = mergeCollidingDistanceAndCalculateTotalDistance(map.get(i));
            count += M - totalTrainLength;
        }
        count+=(N - map.keySet().size())*M;
        System.out.println(count);
        
    }
    
    private static long mergeCollidingDistanceAndCalculateTotalDistance(TreeSet<TrainData> treeSet) {
        long start = -1, end = -1, count = 0;

        for (TrainData trainData : treeSet) {
            if (start == -1) {
                start = trainData.getStart();
                end = trainData.getEnd();
            }
            else{
                if(trainData.getStart()>end){
                    count += end - start + 1;
                    start = trainData.getStart();
                    end = trainData.getEnd();
                }
                else{
                    if(trainData.getEnd()>end)
                        end = trainData.getEnd();
                }
            }
        }
        count += end - start + 1;
        return count;
    }

    private static class TrainData implements Comparable<TrainData>{
        private Long start;
        private Long end;
        
        public TrainData(Long start, Long end) {
            super();
            this.start = start;
            this.end = end;
        }
        public long getStart() {
            return start;
        }
        public void setStart(Long start) {
            this.start = start;
        }
        public Long getEnd() {
            return end;
        }
        public void setEnd(Long end) {
            this.end = end;
        }
        
        @Override
        public int compareTo(TrainData o) {
            if(this.start.compareTo(o.getStart())==0){
                return this.end.compareTo(o.getEnd());
            }
            return this.start.compareTo(o.getStart());
        }
        
    }
}
