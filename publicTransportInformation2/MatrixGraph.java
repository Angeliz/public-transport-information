package publicTransportInformation2;

public class MatrixGraph<T> extends AbstractGraph<T> {		//�ڽӾ����ʾ�Ĵ�Ȩͼ�࣬T��ʾ����Ԫ�����ͣ��̳г���ͼ��
	protected Matrix matrix;								//������󣬴洢ͼ���ڽӾ���
	public MatrixGraph(int length){
		super(length);										//��������Ϊlength�Ŀ�˳���
		this.matrix= new Matrix(length);					//����length*length���ڽӾ�������
	}
	public MatrixGraph(){									//�����ͼ��������Ϊ0������Ϊ0
		this(10);											//����˳�����ڽӾ����Ĭ������Ϊ10
	}
	public MatrixGraph(T[] vertices){						//��vertices���㼯�Ϲ���ͼ������Ϊ0
		this(vertices.length);
		for(int i=0;i<vertices.length;i++){
			this.insertVertex(vertices[i]); 				//���붥��
		}
	}
	public MatrixGraph(T[] vertices, Triple[] edges){		//�Զ��㼯�Ϻͱ߼��Ϲ���ͼ
		this(vertices);
		for(int j=0;j<edges.length;j++){
			this.insertEdge(edges[j]);						//�����
		}
	}
	public String toString(){
		String str=super.toString()+"�ڽӾ��� \n";
		int n=this.vertexCount();							//������
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(this.matrix.get(i, j)==MAX_WEIGHT){
					str+=String.format("%6s","��");
				}
				else{
					str+=String.format("%6d",this.matrix.get(i, j));
				}
			}
			str+="\n";
		}
		return str;
	}
	public void insertEdge(int i,int j,int weight){			//����ߣ�ȨֵΪweight
		if(i!=j){											//����ʾ����
			if(weight<=0||weight>MAX_WEIGHT){				//Ȩֵ�ݴ���Ϊ�ޱߣ�ȡֵ��
				weight=MAX_WEIGHT;
			}
			this.matrix.set(i, j, weight);
		}
		else{
			throw new IllegalArgumentException("���ܲ�������");
		}
	}
	public void insertEdge(Triple edge){
		this.insertEdge(edge.row, edge.column, edge.value);
	}
	public int insertVertex(T x){
		int i=this.vertexlist.insert(x);					//����˳���β����x������x��ţ��Զ�����
		if(i>=this.matrix.getRows()){
			this.matrix.setRowsColumns(i+1, i+1);			//�ڽӾ����������Ͷ�������ͬ
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
					this.matrix.set(j-1, k, this.matrix .get(j, k));			//��i+1��n-1��Ԫ������һ��
				}
			}
			for(int j=0;j<n;j++){
				for(int k=i+1;k<n;k++){
					this.matrix.set(j, k-1, this.matrix .get(j, k));			//��i+1��n-1��Ԫ������һ��
				}
			}
			this.matrix.setRowsColumns(n-1, n-1);
		}
		else{
			throw new IndexOutOfBoundsException("���Խ��");
		}
	}
	public int weight(int i,int j){ 						//����Ȩֵ
		return this.matrix.get(i, j);						//���ؾ���Ԫ��[i,j]ֵ
	}
	//���ض���vi��vj֮��ĺ���ڽӶ�����ţ���j=-1������vi��һ���ڽӶ�������
	//�������ں���ڽӶ��㣬����-1
	protected int next(int i,int j){
		int n=this.vertexCount();
		if(i>=0&&i<n&&j>=-1&&j<n&&i!=j){
			for(int k=j+1;k<n;k++){
				if(this.matrix.get(i, k)>0&&this.matrix.get(i, k)<MAX_WEIGHT){	//Ȩֵ��ʾ�б�
					return k;
				}
			}
		}
		return -1;
	}
	
	
	//���·��,��ÿ�Զ�������·�������ȣ�Floyed�㷨
	public void shortestPath(){
		int n=this.vertexCount();							//ͼ�Ķ�����
		Matrix path=new Matrix(n);							//���·������,��ֵΪ0
		Matrix dist=new Matrix(n);							//���Ⱦ���ͬ��
		for(int i=0;i<n;i++){								//��ʼ����������
			for(int j=0;j<n;j++){
				int w=this.weight(i, j);
				dist.set(i, j, w);
				path.set(i, j, (i!=j&&w<MAX_WEIGHT?i:-1));
			}
		}
		for(int k=0;k<n;k++){								//��vk��Ϊ����·�����м䶥��
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
		System.out.println("ÿ�Զ�������·�����£�");
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i!=j){
//					System.out.print(toPath(path,i,j)+"����"+(dist.get(i, j)==MAX_WEIGHT?"��":dist.get(i, j))+",");
					String str=String.format("%16s", toPath(path,i,j)+"����"+(dist.get(i, j)==MAX_WEIGHT?"��":dist.get(i, j)));
					System.out.print(str);
				}
			}
			System.out.println();
		}
	}
	
	private String toPath(Matrix path,int i,int j){		//����path·�������дӶ���vi��vj��һ��·���ַ���
		SinglyList<String> pathlink=new SinglyList<String>();		//��������¼���·���������㣬���ڷ���
		pathlink.insert(0, (String) this.getVertex(j));				//������������·���յ�vj
		for(int k=path.get(i, j);k!=i&&k!=j&&k!=-1;k=path.get(i, k)){
			pathlink.insert(0, (String) this.getVertex(k));
		}
		pathlink.insert(0, (String) this.getVertex(i));
		return pathlink.toString();
		
		
		
	}
}
