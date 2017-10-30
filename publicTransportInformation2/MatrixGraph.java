package publicTransportInformation2;

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
}
