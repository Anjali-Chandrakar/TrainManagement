package com.anjali.train;
import com.anjali.train.TrainService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
public class DataBuffer {
   public static List<TrainVo> trainList;
   public static List<Station> stationList;
   public static List<ArrayList<Integer>> route = new ArrayList<>();
   public static HashMap<Integer,ArrayList<Integer>> stnTrainMap=new HashMap<>();
   public static HashMap<Integer,String> trainStnMap = new HashMap<>();
   @Autowired
   private TrainService trainService;
  
   public static List<TrainVo> getTrainList() {
       return trainList;
   }
   public static List<Station> getStationList() {
       return stationList;
   }
   public static void setTrainList(List<TrainVo> trainListx) {
       trainList = trainListx;
   }
   public static void setStationList(List<Station> stationListx) {
       stationList = stationListx;
   }
 
   public void createStationMap()
   {
	   trainList =trainService.listTrain();
	   for(TrainVo t: trainList)
	   {
		   trainStnMap.put(t.getId(),t.getPath());
		   List<String> stn=Arrays.asList(t.getPath().split(",[ ]*"));
		   List<Integer> newList = stn.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
		   for(int i=0;i<newList.size();i++ )
		   {
			   Integer stn1=newList.get(i);
			   if(stnTrainMap.containsKey(stn1))
			   {
				 ArrayList<Integer> val=stnTrainMap.get(stn1);
				 val.add(t.getId());
				 stnTrainMap.put(stn1,val);
			   }
			   else
				   stnTrainMap.put(stn1,new ArrayList<Integer>(t.getId()));
			   if(i != newList.size()-1)
			   {
				   ArrayList<Integer> temp=new ArrayList<Integer>();
				   temp.add(stn1);
				   temp.add(newList.get(i+1));
				   //System.out.println("\n\n\nControl 1: "+temp+"\n\n\n");
				   route.add(temp);
			   }
		   }
		   
	   }
       
   }
}