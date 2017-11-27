package publicTransportInformation2;

import java.util.HashMap;
import java.util.Scanner;

//需求：
//1.用图结构存储站点数据，查询任意两个站点之间的多条路径及换乘方案
//2.计算最短路径，选择最佳换乘方案
//3.模糊查询
//4.说明数据的逻辑结构和存储结构
//5.可采用哪些措施和算法以提高查找操作效率，求查找算法的平均查找长度ASL
//6.说明什么是最佳换乘方案以及怎样确定最佳换乘方案最佳
public class Test {
/*
 权值考虑
1.时间最短：路程，换乘
2.票价最低：（武汉轨道交通统一按里程限时分段计价：2元可乘坐9公里；3元可乘坐14公里；3元以上每增加1元可乘坐的公里数比上一区段递增2公里（4元可乘坐21公里，5元可乘坐30公里，6元可乘坐41公里）。
                       每次乘车限时180分钟）
3.路径最短
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] vertices={"天河机场","宏图大道","香港路","大智路","汉口北","武汉火车站","洪山广场","中南路","金银湖公园","常青花园","范湖","循礼门","江汉路","钟家村","光谷广场","东吴大道","宗关","王家湾","东风公司","黄金口"};
		HashMap<String , Integer> map = new HashMap<String , Integer>(); 
		for(int i=0;i<vertices.length;i++){
			map.put(vertices[i], i);
		}
		Triple edges[]={new Triple(0,1,14297),
				new Triple(1,0,14297),new Triple(1,2,9884),new Triple(1,9,5629),
				new Triple(2,1,9884),new Triple(2,3,1662),new Triple(2,9,6409),new Triple(2,10,2535),
				new Triple(3,2,1662),new Triple(3,4,14780),new Triple(3,11,1055),new Triple(3,12,1518),
				new Triple(4,3,14780),
				new Triple(5,6,12539),
				new Triple(6,5,12539),new Triple(6,7,927),new Triple(6,12,7103),
				new Triple(7,6,927),new Triple(7,13,7795),new Triple(7,14,8149),
				new Triple(8,9,6356),
				new Triple(9,1,5629),new Triple(9,2,6409),new Triple(9,8,6356),new Triple(9,10,4280),
				new Triple(10,2,2535),new Triple(10,9,4280),new Triple(10,11,4903),new Triple(10,16,4916),
				new Triple(11,3,1055),new Triple(11,10,4903),new Triple(11,12,879),new Triple(11,16,6496),
				new Triple(12,3,1518),new Triple(12,6,7103),new Triple(12,11,879),new Triple(12,13,5693),
				new Triple(13,7,7795),new Triple(13,12,5693),new Triple(13,17,5889),new Triple(13,18,14287),
				new Triple(14,7,8149),
				new Triple(15,16,11402),
				new Triple(16,10,4916),new Triple(16,11,6496),new Triple(16,15,11402),new Triple(16,17,2647),
				new Triple(17,13,5889),new Triple(17,16,2647),new Triple(17,18,8125),new Triple(17,19,6346),
				new Triple(18,13,14287),new Triple(18,17,8125),
				new Triple(19,17,6346)
		};
		MatrixGraph<String> graph=new MatrixGraph<String>(vertices,edges);
		System.out.println(graph.toString());
		
		while(true){
			System.out.println();
			System.out.println("请选择您的需求："+"\n"+"查找两点之间最短路径请输入:1"+"\n"+"查找两点之间所有路径请输入:2"+"\n"+"结束算法请输入:3");
			Scanner in=new Scanner(System.in);
			int num=in.nextInt();
			if(num==1){
				System.out.println("请选择您需要的查询方式："+"\n"+"精确查询请输入：0"+"\n"+"模糊查询请输入：1");
				Scanner inStyle=new Scanner(System.in);
				int style=inStyle.nextInt();
				if(style==0){
					System.out.println("请输入起点");
					Scanner startIn=new Scanner(System.in);
					String str=startIn.nextLine();
					int start=map.get(str);
					System.out.println("请输入终点");
					Scanner endIn=new Scanner(System.in);
					str=endIn.nextLine();
					int end=map.get(str);
					graph.shortestPath(start,end);
					
				}
				if(style==1){
					//存储模糊查询中可能想要表达的顶点
					String possibleStart[]=new String[vertices.length];
					String possibleEnd[]=new String[vertices.length];
					System.out.println("请输入起点");
					Scanner startIn=new Scanner(System.in);
					String strStart=startIn.nextLine();
					//将符合条件的（有一个字满足）存入数组中
					for(int i=0;i<vertices.length;i++){
						for(int j=0;j<strStart.length();j++){
							if(vertices[i].indexOf(strStart.charAt(j))!=-1){
								possibleStart[i]=vertices[i];
								break;
							}
						}
					}
					System.out.println("请输入终点");
					Scanner endIn=new Scanner(System.in);
					String strEnd=endIn.nextLine();
					for(int i=0;i<vertices.length;i++){
						for(int j=0;j<strEnd.length();j++){
							if(vertices[i].indexOf(strEnd.charAt(j))!=-1){
								possibleEnd[i]=vertices[i];
							}
						}
						
					}
					//遍历两个可能的顶点数组输出
					for(int i=0;i<possibleStart.length;i++){
						if (possibleStart[i]==null){
							continue;
						}
						for(int j=0;j<possibleEnd.length;j++){
							if(possibleEnd[j]==null){
								continue;
							}
							int start=map.get(possibleStart[i]);
							int end=map.get(possibleEnd[j]);
							graph.shortestPath(start,end);
							
						}
					}
				}
				
			}
			if(num==2){
				System.out.println("请选择您需要的查询方式："+"\n"+"精确查询请输入：0"+"\n"+"模糊查询请输入：1");
				Scanner inStyle=new Scanner(System.in);
				int style=inStyle.nextInt();
				if(style==0){
					System.out.println("请输入起点");
					Scanner startIn=new Scanner(System.in);
					String str=startIn.nextLine();
					int start=map.get(str);
					System.out.println("请输入终点");
					Scanner endIn=new Scanner(System.in);
					str=endIn.nextLine();
					int end=map.get(str);
					graph.FindAllPath(start,end);
					
				}
				if(style==1){
					//存储模糊查询中可能想要表达的顶点
					String possibleStart[]=new String[vertices.length];
					String possibleEnd[]=new String[vertices.length];
					System.out.println("请输入起点");
					Scanner startIn=new Scanner(System.in);
					String strStart=startIn.nextLine();
					//将符合条件的（有一个字满足）存入数组中
					for(int i=0;i<vertices.length;i++){
						for(int j=0;j<strStart.length();j++){
							if(vertices[i].indexOf(strStart.charAt(j))!=-1){
								possibleStart[i]=vertices[i];
								break;
							}
						}
					}
					System.out.println("请输入终点");
					Scanner endIn=new Scanner(System.in);
					String strEnd=endIn.nextLine();
					for(int i=0;i<vertices.length;i++){
						for(int j=0;j<strEnd.length();j++){
							if(vertices[i].indexOf(strEnd.charAt(j))!=-1){
								possibleEnd[i]=vertices[i];
							}
						}
						
					}
					//遍历两个可能的顶点数组输出
					for(int i=0;i<possibleStart.length;i++){
						if (possibleStart[i]==null){
							continue;
						}
						for(int j=0;j<possibleEnd.length;j++){
							if(possibleEnd[j]==null){
								continue;
							}
							int start=map.get(possibleStart[i]);
							int end=map.get(possibleEnd[j]);
							graph.FindAllPath(start,end);
						}
					}
				}
				
			}
			if(num==3){
				System.out.println("程序已结束");
				break;
			}
		}
	
//		while(true){
//			System.out.println();
//			System.out.println("请选择您需要的查询方式："+"\n"+"精确查询请输入：1"+"\n"+"模糊查询请输入：2"+"\n"+"结束算法请输入:0");
//			Scanner inStyle=new Scanner(System.in);
//			int style=inStyle.nextInt();
//			if(style==1){
//				System.out.println("请选择您的需求："+"\n"+"查找两点之间最短路径请输入:1"+"\n"+"查找两点之间所有路径请输入:2");
//				Scanner in=new Scanner(System.in);
//				int num=in.nextInt();
//				System.out.println("请输入起点");
//				Scanner startIn=new Scanner(System.in);
//				String str=startIn.nextLine();
//				int start=map.get(str);
//				System.out.println("请输入终点");
//				Scanner endIn=new Scanner(System.in);
//				str=endIn.nextLine();
//				int end=map.get(str);
//				if(num==1){
//					graph.shortestPath(start,end);
//				}
//				if(num==2){
//					graph.FindAllPath(start,end);
//				}
//			}
//			if(style==2){
//				System.out.println("请选择您的需求："+"\n"+"查找两点之间最短路径请输入:1"+"\n"+"查找两点之间所有路径请输入:2");
//				Scanner in=new Scanner(System.in);
//				int num=in.nextInt();
//				//存储模糊查询中可能想要表达的顶点
//				String possibleStart[]=new String[vertices.length];
//				String possibleEnd[]=new String[vertices.length];
//				System.out.println("请输入起点");
//				Scanner startIn=new Scanner(System.in);
//				String strStart=startIn.nextLine();
//				//将符合条件的（有一个字满足）存入数组中
//				for(int i=0;i<vertices.length;i++){
//					for(int j=0;j<strStart.length();j++){
//						if(vertices[i].indexOf(strStart.charAt(j))!=-1){
//							possibleStart[i]=vertices[i];
//							break;
//						}
//					}
//				}
//				System.out.println("请输入终点");
//				Scanner endIn=new Scanner(System.in);
//				String strEnd=endIn.nextLine();
//				for(int i=0;i<vertices.length;i++){
//					for(int j=0;j<strEnd.length();j++){
//						if(vertices[i].indexOf(strEnd.charAt(j))!=-1){
//							possibleEnd[i]=vertices[i];
//						}
//					}
//					
//				}
//				//遍历两个可能的顶点数组输出
//				for(int i=0;i<possibleStart.length;i++){
//					if (possibleStart[i]==null){
//						continue;
//					}
//					for(int j=0;j<possibleEnd.length;j++){
//						if(possibleEnd[j]==null){
//							continue;
//						}
//						int start=map.get(possibleStart[i]);
//						int end=map.get(possibleEnd[j]);
//						if(num==1){
//							graph.shortestPath(start,end);
//						}
//						if(num==2){
//							graph.FindAllPath(start,end);
//						}
//						
//					}
//				}
//			}
//			if(style==0){
//				System.out.println("程序已结束");
//				break;
//			}
//		}
		
		
	}

}
