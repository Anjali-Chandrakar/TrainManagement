package com.anjali.train;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service; 

@Service
public class RouteService { 

@Bean
public DataBuffer dataBuffer() {
    return new DataBuffer();
}
	// adjacency list 
	private ArrayList<Integer>[] adjList; 
	HashMap<Integer,ArrayList<Integer>> stnTrainMap;
	HashMap<Integer,String> trainStnMap;
	List<ArrayList<Integer>> rt;
	
	// No. of stations
	private int v;
	
	public RouteService() {
		super();
	}
	
	@SuppressWarnings("unchecked") 
	private void initAdjList() 
	{ 
		adjList = new ArrayList[v+1]; 
		
		for(int i = 0; i <= v; i++) 
		{ 
			adjList[i] = new ArrayList<>(); 
		} 
	} 
	
	// add edge from u to v 
	public void addEdge(int u, int v) 
	{ 
		// Add v to u's list. 
		adjList[u].add(v); 
	} 
	
	public List<Map<ArrayList<Integer>, ArrayList<Integer>>> printAllPaths(int s, int d) 
	{ 
		stnTrainMap=DataBuffer.stnTrainMap;
		trainStnMap=DataBuffer.trainStnMap;
		rt=DataBuffer.route;
		v = stnTrainMap.size();
		initAdjList();
		//addEdge(0,1);
		for(ArrayList<Integer> al :rt)
		{
			int a=al.get(0);
			int b=al.get(1);
			addEdge(a,b);
		}
		//System.out.println(adjList);
		//System.out.println("########    "+v+"     #######");
		boolean[] isVisited = new boolean[v+1]; 
		ArrayList<Integer> pathList = new ArrayList<>(); 
		ArrayList<ArrayList<Integer> > routeList = new ArrayList<ArrayList<Integer> >();
		pathList.add(s); 
		printAllPathsUtil(s, d, isVisited, pathList, routeList); 
		System.out.println(routeList);
		List<Map<ArrayList<Integer>, ArrayList<Integer>>> trainList=findTrains(routeList);
		return trainList;
	} 
	private void printAllPathsUtil(Integer u, Integer d, boolean[] isVisited, ArrayList<Integer> localPathList, ArrayList<ArrayList<Integer> > routeList) 
 { 
		// Mark the current node 
		
		isVisited[u] = true; 
		
		if (u.equals(d)) 
		{ 
		    routeList.add((ArrayList<Integer>)localPathList.clone());
			System.out.println(localPathList); 
			isVisited[u]= false; 
			return; 
		} 
		for (Integer i : adjList[u]) 
		{ 
			if (!isVisited[i]) 
			{ 
				localPathList.add(i); 
				printAllPathsUtil(i, d, isVisited, localPathList, routeList); 				
				localPathList.remove(i); 
			} 
		} 
		isVisited[u] = false;

	} 
	private List<Map<ArrayList<Integer>, ArrayList<Integer>>> findTrains(ArrayList<ArrayList<Integer> > routeList)
	{
		
		List<Map<ArrayList<Integer>, ArrayList<Integer>>> routeTrainList=new ArrayList<Map<ArrayList<Integer>, ArrayList<Integer>>>();
		
		for(ArrayList<Integer> route : routeList)
		{
			ArrayList<Integer> list = new ArrayList<>();
			list= stnTrainMap.get(route.get(0));
			for(int stn : route)
			{
				list=intersection(list,stnTrainMap.get(stn));
			}
			routeTrainList.add(confirmTrains(route,list));
		}
		return routeTrainList;
	}
	
	private Map<ArrayList<Integer>, ArrayList<Integer>> confirmTrains(ArrayList<Integer> route, ArrayList<Integer> list){
		String routeStr=route.toString();
		Map<ArrayList<Integer>, ArrayList<Integer>> response=new LinkedHashMap<ArrayList<Integer>, ArrayList<Integer>>();
		routeStr = routeStr.substring(1,routeStr.length()-1);
		ArrayList<Integer> trains=new ArrayList<Integer>();
		for(Integer t : list)
		{
			if((trainStnMap.get(t)).contains(routeStr))
			{
				System.out.println(route + "-->"+t);	
				trains.add(t);
			}
		}
		response.put(route, trains);
		return response;
	}
	
	public static <Integer> ArrayList<Integer> intersection(ArrayList<Integer> list1, ArrayList<Integer> list2) {
	    ArrayList<Integer> list = new ArrayList<>();

	    for (Integer t : list1) {
	        if (list2.contains(t)) {
	            list.add(t);
	        }
	    }

	    return list;
	}

	// Driver program 
/*	public static void main(String[] args) 
	{ 
		// Create a sample graph 
		RouteService g = new RouteService(4); 
		g.addEdge(0,1); 
		g.addEdge(0,2); 
		g.addEdge(0,3); 
		g.addEdge(2,0); 
		g.addEdge(2,1); 
		g.addEdge(1,3); 
	
		// arbitrary source 
		int s = 2; 
	
		// arbitrary destination 
		int d = 3; 
	
		System.out.println("Following are all different paths from "+s+" to "+d); 
		g.printAllPaths(s, d); 

	}*/
} 