package com.anjali.train;
//JAVA program to print all 
//paths from a source to 
//destination. 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List; 
//A directed graph using 
//adjacency list representation 
public class RouteService { 

	// No. of vertices in graph 
	private int v; 
	
	// adjacency list 
	private ArrayList<Integer>[] adjList; 
	HashMap<Integer,ArrayList<Integer>> stnTrainMap=new HashMap<>();
	HashMap<Integer,String> trainStnMap = new HashMap<>();
	
	public RouteService() {
		super();
		RouteService g = new RouteService(4); 
		g.addEdge(0,1); 
		g.addEdge(0,2); 
		g.addEdge(0,3); 
		g.addEdge(2,0); 
		g.addEdge(2,1); 
		g.addEdge(1,3);
	}
	
	//Constructor 
	public RouteService(int vertices){ 
		
		//initialise vertex count 
		this.v = vertices; 
		
		// initialise adjacency list 
		initAdjList(); 
		initStnHash();
		initTrainHash();
	} 
	
	// utility method to initialise 
	// adjacency list 
	@SuppressWarnings("unchecked") 
	private void initAdjList() 
	{ 
		adjList = new ArrayList[v]; 
		
		for(int i = 0; i < v; i++) 
		{ 
			adjList[i] = new ArrayList<>(); 
		} 
	} 
	
	private void initStnHash()
	{
		stnTrainMap.put(0,new ArrayList(Arrays.asList(1,2,3)));
		stnTrainMap.put(1,new ArrayList(Arrays.asList(1,2,4,5)));
		stnTrainMap.put(2,new ArrayList(Arrays.asList(1,4,3)));
		stnTrainMap.put(3,new ArrayList(Arrays.asList(1,3,5)));
	}
	
	private void initTrainHash()
	{
		trainStnMap.put(1,"2, 0, 1, 3");
		trainStnMap.put(2,"1, 3");
		trainStnMap.put(3,"2, 0, 3");
		trainStnMap.put(4,"2, 1, 3");
	}
	// add edge from u to v 
	public void addEdge(int u, int v) 
	{ 
		// Add v to u's list. 
		adjList[u].add(v); 
	} 
	
	// Prints all paths from 
	// 's' to 'd' 
	public void printAllPaths(int s, int d) 
	{ 
		boolean[] isVisited = new boolean[v]; 
		ArrayList<Integer> pathList = new ArrayList<>(); 
		ArrayList<ArrayList<Integer> > routeList = new ArrayList<ArrayList<Integer> >();
		//add source to path[] 
		pathList.add(s); 
		//Call recursive utility 
		printAllPathsUtil(s, d, isVisited, pathList, routeList); 
		findTrains(routeList);
	} 

	// A recursive function to print 
	// all paths from 'u' to 'd'. 
	// isVisited[] keeps track of 
	// vertices in current path. 
	// localPathList<> stores actual 
	// vertices in the current path 
	private void printAllPathsUtil(Integer u, Integer d, 
									boolean[] isVisited, 
							ArrayList<Integer> localPathList, ArrayList<ArrayList<Integer> > routeList) 
 { 
		// Mark the current node 
		isVisited[u] = true; 
		
		if (u.equals(d)) 
		{ 
		    routeList.add((ArrayList<Integer>)localPathList.clone());
			System.out.println(localPathList); 
			// if match found then no need to traverse more till depth 
			isVisited[u]= false; 
			return; 
		} 
		
		// Recur for all the vertices 
		// adjacent to current vertex 
		for (Integer i : adjList[u]) 
		{ 
			if (!isVisited[i]) 
			{ 
				// store current node 
				// in path[] 
				localPathList.add(i); 
				printAllPathsUtil(i, d, isVisited, localPathList, routeList); 
				
				// remove current node 
				// in path[] 
				localPathList.remove(i); 
			} 
		} 
		
		// Mark the current node 
		isVisited[u] = false;

	} 
	private void findTrains(ArrayList<ArrayList<Integer> > routeList)
	{
		for(ArrayList<Integer> route : routeList)
		{
			//route=new ArrayList(Arrays.asList(2,1,3,0));
			System.out.println(route);
			ArrayList<Integer> list = new ArrayList<>();
			list= stnTrainMap.get(route.get(0));
			for(int stn : route)
			{
				list=intersection(list,stnTrainMap.get(stn));
			}
			confirmTrains(route,list);
		}
	}
	
	private void confirmTrains(ArrayList<Integer> route, ArrayList<Integer> list){
		String routeStr=route.toString();
		routeStr = routeStr.substring(1,routeStr.length()-1);
		for(Integer t : list)
		{
			if((trainStnMap.get(t)).contains(routeStr))
			{
				System.out.println(route + "-->"+t);	
			}
		}
		
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