package publicTransportInformation2;

import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.Stack;

public class MatrixGraph<T> extends AbstractGraph<T> {		//邻接矩阵表示的带权图类，T表示顶点元素类型，继承抽象图类
	protected Matrix matrix;								//矩阵对象，存储图的邻接矩阵
	public MatrixGraph(int length){
		super(length);										//构造容量为length的空顺序表
		this.matrix= new Matrix(length);					//构造length*length的邻接矩阵容量
	}
	public MatrixGraph(){									//构造空图，顶点数为0，边数为0
		this(10);											//顶点顺序表和邻接矩阵的默认容量为10
	}
	public MatrixGraph(T[] vertices){						//以vertices顶点集合构造图，边数为0
		this(vertices.length);
		for(int i=0;i<vertices.length;i++){
			this.insertVertex(vertices[i]); 				//插入顶点
		}
	}
	public MatrixGraph(T[] vertices, Triple[] edges){		//以顶点集合和边集合构造图
		this(vertices);
		for(int j=0;j<edges.length;j++){
			this.insertEdge(edges[j]);						//插入边
		}
	}
	public Node getVertices(int i){
		return this.vertexlist.getOne(i);
	}
	public String toString(){
		String str=super.toString()+"邻接矩阵： \n";
		int n=this.vertexCount();							//顶点数
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(this.matrix.get(i, j)==MAX_WEIGHT){
					str+=String.format("%6s","∞");
				}
				else{
					str+=String.format("%6d",this.matrix.get(i, j));
				}
			}
			str+="\n";
		}
		return str;
	}
	public void insertEdge(int i,int j,int weight){			//插入边，权值为weight
		if(i!=j){											//不表示自身环
			if(weight<=0||weight>MAX_WEIGHT){				//权值容错，视为无边，取值∞
				weight=MAX_WEIGHT;
			}
			this.matrix.set(i, j, weight);
		}
		else{
			throw new IllegalArgumentException("不能插入自身环");
		}
	}
	public void insertEdge(Triple edge){
		this.insertEdge(edge.row, edge.column, edge.value);
	}
	public int insertVertex(T x){
		int i=this.vertexlist.insert(x);					//顶点顺序表尾插入x，返回x序号，自动扩容
		if(i>=this.matrix.getRows()){
			this.matrix.setRowsColumns(i+1, i+1);			//邻接矩阵行列数和顶点数相同
		}
		for(int j=0;j<i;j++){
			this.matrix.set(i, j, MAX_WEIGHT);
			this.matrix.set(j, i, MAX_WEIGHT);
		}
		return i;
	}
	public void removeVertex(int i){
		int n=this.vertexCount();
		if(i>=0&&i<n){
			this.vertexlist.remove(i);
			for(int j=i+1;j<n;j++){
				for(int k=0;k<n;k++){
					this.matrix.set(j-1, k, this.matrix .get(j, k));			//第i+1到n-1行元素上移一行
				}
			}
			for(int j=0;j<n;j++){
				for(int k=i+1;k<n;k++){
					this.matrix.set(j, k-1, this.matrix .get(j, k));			//第i+1到n-1列元素左移一列
				}
			}
			this.matrix.setRowsColumns(n-1, n-1);
		}
		else{
			throw new IndexOutOfBoundsException("序号越界");
		}
	}
	public int weight(int i,int j){ 						//返回权值
		return this.matrix.get(i, j);						//返回矩阵元素[i,j]值
	}
	//返回顶点vi在vj之后的后继邻接顶点序号；若j=-1，返回vi第一个邻接顶点的序号
	//若不存在后继邻接顶点，返回-1
	protected int next(int i,int j){
		int n=this.vertexCount();
		if(i>=0&&i<n&&j>=-1&&j<n&&i!=j){
			for(int k=j+1;k<n;k++){
				if(this.matrix.get(i, k)>0&&this.matrix.get(i, k)<MAX_WEIGHT){	//权值表示有边
					return k;
				}
			}
		}
		return -1;
	}
	
	
	//最短路径,求每对顶点的最短路径及长度，Floyed算法
	public void shortestPath(){
		int n=this.vertexCount();							//图的顶点数
		Matrix path=new Matrix(n);							//最短路径矩阵,初值为0
		Matrix dist=new Matrix(n);							//长度矩阵，同上
		for(int i=0;i<n;i++){								//初始化两个矩阵
			for(int j=0;j<n;j++){
				int w=this.weight(i, j);
				dist.set(i, j, w);
				path.set(i, j, (i!=j&&w<MAX_WEIGHT?i:-1));
			}
		}
		for(int k=0;k<n;k++){								//以vk作为其他路径的中间顶点
			for(int i=0;i<n;i++){
				if(i!=k){
					for(int j=0;j<n;j++){
						if(j!=k&&j!=i&&dist.get(i, j)>(dist.get(i, k)+dist.get(k, j))){
							dist.set(i, j, dist.get(i, k)+dist.get(k, j));
							path.set(i, j, path.get(k, j));
						}
					}
				}
				
			}
		}
		System.out.println("每对顶点的最短路径如下：");
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i!=j){
//					System.out.print(toPath(path,i,j)+"长度"+(dist.get(i, j)==MAX_WEIGHT?"∞":dist.get(i, j))+",");
					String str=String.format("%16s", toPath(path,i,j)+"长度"+(dist.get(i, j)==MAX_WEIGHT?"∞":dist.get(i, j)));
					System.out.print(str);
				}
			}
			System.out.println();
		}
	}
	
	private String toPath(Matrix path,int i,int j){		//返回path路径矩阵中从顶点vi到vj的一条路径字符串
		SinglyList<String> pathlink=new SinglyList<String>();		//单链表，记录最短路径经过顶点，用于反序
		pathlink.insert(0, (String) this.getVertex(j));				//单链表插入最短路径终点vj
		for(int k=path.get(i, j);k!=i&&k!=j&&k!=-1;k=path.get(i, k)){
			pathlink.insert(0, (String) this.getVertex(k));
		}
		pathlink.insert(0, (String) this.getVertex(i));
		return pathlink.toString();	
	}
	
//	public void FindAllPath(String startNodeKey,String endNodeKey){
//		
//	}
	
	public static Stack<Node> stack = new Stack<Node>();  
    /* 存储路径的集合 */  
    public static ArrayList<Object[]> sers = new ArrayList<Object[]>();  
  
    /* 判断节点是否在栈中 */  
    public static boolean isNodeInStack(Node node)  
    {  
        Iterator<Node> it = stack.iterator();  
        while (it.hasNext()) {  
            Node node1 = (Node) it.next();  
            if (node == node1)  
                return true;  
        }  
        return false;  
    }  
  
    /* 此时栈中的节点组成一条所求路径，转储并打印输出 */  
    public static void showAndSavePath()  
    {  
        Object[] o = stack.toArray();  
        for (int i = 0; i < o.length; i++) {  
            Node nNode = (Node) o[i];  
              
            if(i < (o.length - 1))  
                System.out.print(nNode.getName() + "->");  
            else  
                System.out.print(nNode.getName());  
        }  
        sers.add(o); /* 转储 */  
        System.out.println("\n");  
    }  
  
    /* 
     * 寻找路径的方法  
     * cNode: 当前的起始节点currentNode 
     * pNode: 当前起始节点的上一节点previousNode 
     * sNode: 最初的起始节点startNode 
     * eNode: 终点endNode 
     */  
    public static boolean getPaths(Node cNode, Node pNode, Node sNode, Node eNode) {  
        Node nNode = null;  
        /* 如果符合条件判断说明出现环路，不能再顺着该路径继续寻路，返回false */  
        if (cNode != null && pNode != null && cNode == pNode)  
            return false;  
  
        if (cNode != null) {  
            int i = 0;  
            /* 起始节点入栈 */  
            stack.push(cNode);  
            /* 如果该起始节点就是终点，说明找到一条路径 */  
            if (cNode == eNode)  
            {  
                /* 转储并打印输出该路径，返回true */  
                showAndSavePath();  
                return true;  
            }  
            /* 如果不是,继续寻路 */  
            else  
            {  
                /*  
                 * 从与当前起始节点cNode有连接关系的节点集中按顺序遍历得到一个节点 
                 * 作为下一次递归寻路时的起始节点  
                 */  
                nNode = (Node) cNode.getRelationNodes().get(i);  
                while (nNode != null) {  
                    /* 
                     * 如果nNode是最初的起始节点或者nNode就是cNode的上一节点或者nNode已经在栈中 ，  
                     * 说明产生环路 ，应重新在与当前起始节点有连接关系的节点集中寻找nNode 
                     */  
                    if (pNode != null  
                            && (nNode == sNode || nNode == pNode || isNodeInStack(nNode))) {  
                        i++;  
                        if (i >= cNode.getRelationNodes().size())  
                            nNode = null;  
                        else  
                            nNode = (Node) cNode.getRelationNodes().get(i);  
                        continue;  
                    }  
                    /* 以nNode为新的起始节点，当前起始节点cNode为上一节点，递归调用寻路方法 */  
                    if (getPaths(nNode, cNode, sNode, eNode))/* 递归调用 */  
                    {  
                        /* 如果找到一条路径，则弹出栈顶节点 */  
                        stack.pop();  
                    }  
                    /* 继续在与cNode有连接关系的节点集中测试nNode */  
                    i++;  
                    if (i >= cNode.getRelationNodes().size())  
                        nNode = null;  
                    else  
                        nNode = (Node) cNode.getRelationNodes().get(i);  
                }  
                /*  
                 * 当遍历完所有与cNode有连接关系的节点后， 
                 * 说明在以cNode为起始节点到终点的路径已经全部找到  
                 */  
                stack.pop();  
                return false;  
            }  
        } else  
            return false;  
    }  
	
	
}
