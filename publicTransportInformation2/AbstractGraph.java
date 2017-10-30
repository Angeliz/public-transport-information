package publicTransportInformation2;

public abstract class AbstractGraph<String> {
	protected static final int MAX_WEIGHT=0x0000ffff; 	//最大权值（表示无穷大∞）
	protected SeqList<String> vertexlist;				//顶点顺序表，存储图的顶点集合
	public AbstractGraph(int length){					//有参构造方法，顶点数为0，length指定顺序表容量
		this.vertexlist=new SeqList(length);
	}
	public AbstractGraph(){								//无参构造方法
		this(10);										//顶点数为0，默认顺序表容量为10
	}
	public int vertexCount(){							//返回图的顶点数
		return this.vertexlist.size();					//顶点顺序表元素个数
	}
	public java.lang.String toString(){					//返回图的顶点集合描述字符串
		return "顶点集合： "+this.vertexlist.toString()+"\n";
	}
	public String getVertex(int i){						//返回顶点元素
		return this.vertexlist.get(i);					//若i越界，返回null
	}
	public void setVertex(int i,String x){				//设置第i个顶点为x
		this.vertexlist.set(i, x);   					//若i越界，抛出异常
	}
	
	//抽象方法
	public abstract int insertVertex(String x);			//插入一个顶点元素，返回顶点序号
	public abstract void removeVertex(int x);			//删除第i个顶点及相关的边
	public abstract int weight(int i,int j);			//返回边的权值
	protected abstract int next(int i,int j);			//返回vi在vj之后的后继邻接顶点的序号			
	
}
