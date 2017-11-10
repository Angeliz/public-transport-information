package publicTransportInformation2;
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
		String[] vertices={"A","B","C","D","E"};
		Triple edges[]={new Triple(0,1,45),new Triple(0,2,28),new Triple(0,3,10),
				new Triple(1,0,45),new Triple(1,2,12),new Triple(1,4,21),
				new Triple(2,0,28),new Triple(2,1,12),new Triple(2,3,17),new Triple(2,4,26),
				new Triple(3,0,10),new Triple(3,2,17),new Triple(3,4,15),
				new Triple(4,1,21),new Triple(4,2,26),new Triple(4,3,15)	
		};
		MatrixGraph<String> graph=new MatrixGraph<String>(vertices,edges);
		System.out.println(graph.toString());
		graph.shortestPath();
		graph.FindAllPath(0, 3);
	}

}
