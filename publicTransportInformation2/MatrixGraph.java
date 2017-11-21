package publicTransportInformation2;

import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.Stack;

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
	public Node getVertices(int i){
		return this.vertexlist.getOne(i);
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
	//���ض���vi��vj֮��ĺ���ڽӶ�����ţ���j=0������vi��һ���ڽӶ�������
	//�������ں���ڽӶ��㣬����-1
	protected int next(int i,int j){
		int n=this.vertexCount();
		if(i==j){
			j++;
		}
		if(i>=0&&i<n&&j>=0&&j<n){
			for(int k=j;k<n;k++){
				if(this.matrix.get(i, k)>0&&this.matrix.get(i, k)<MAX_WEIGHT){	//Ȩֵ��ʾ�б�
					return k;
				}
				else{
					j++;
				}
			}
		}
		
		return -1;
	}
	//��Դ���·����Dijkstra�㷨
//	public void shortestPath(int i){
//		int n=this.vertexCount();							//ͼ�Ķ�����
//		boolean[] vset=new boolean[n];    					//��������·���Ķ��㼯�ϣ���ֵfalse
//		vset[i]=true;										//��ʼ��Դ���ڼ�����
//		int[] dist=new int[n];								//���·������
//		int[] path=new int[n];								//���·�����յ��ǰһ������
//		for(int j=0;j<n;j++){								//��ʼ����������
//			dist[j]=this.weight(i, j);
//			path[j]=(j!=i&&dist[j]<MAX_WEIGHT)?i:-1;
//		}
//		for(int j=(i+1)%n;j!=i;j=(j+1)%n){					//Ѱ��vi��vj�����·����vj��δ֪������
//			int mindist=MAX_WEIGHT;
//			int min=0;										//��·��������Сֵ�����±�
//			for(int k=0;k<n;k++){
//				if(!vset[k]&&dist[k]<mindist){
//					mindist=dist[k];						//·��������Сֵ
//					min=k;									//·��������Сֵ�±�
//				}
//			}
//			if(mindist==MAX_WEIGHT){						//��û���������·�����㷨����
//				break;
//			}
//			vset[min]=true;									//ȷ��һ�����·����vi��min�����յ�min����֪����
//			
//			for(int k=0;k<n;k++){
//				if(!vset[k]&&this.weight(min, k)<MAX_WEIGHT&&dist[min]+this.weight(min, k)<dist[k]){
//					dist[k]=dist[min]+this.weight(min, k);	//�ø���·���滻
//					path[k]=min;							//���·������min��
//				}
//			}
//		}
//		System.out.println(this.getVertex(i)+"����ĵ�Դ���·����");
//		for(int j=0;j<n;j++){								//�����Դ·��
//			if(j!=i){
//				SinglyList<T> pathlink=new SinglyList<T>();	//���������ڷ���
//				pathlink.insert(0, this.getVertex(j));
//				for(int k=path[j];k!=i&&k!=j&&k!=-1;k=path[k]){
//					pathlink.insert(0, this.getVertex(k));
//				}
//				pathlink.insert(0, this.getVertex(i));		//���
//				System.out.println(pathlink.toString()+"����"+(dist[j]==MAX_WEIGHT?"��":dist[j]));
//			}
//		}
//	}
	
	
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
	
	//����·��
	public void FindAllPath(int i,int goal){
//		int value[][]={{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
		Matrix visited=new Matrix(this.vertexCount(),this.vertexCount());	//�洢���Ƿ񱻷���
		for(int m=0;m<this.vertexCount();m++){
			for(int n=0;n<this.vertexCount();n++){
				if(m==n||this.weight(m, n)==MAX_WEIGHT){
					visited.set(m, n, -1);
				}
			}
		}																	//�������û�б����ӵ���Ϊ-1������Ϊ0��ʾδ�����ʣ���Ϊ1ʱ��ʾ�ѷ���
		
		SeqStack path=new SeqStack();										//˳��ջ�洢·��
		path.push(i);														//�����ջ
//		path.outPut();
//		int j=this.next(i, 0);												//��ʼ��j
		int j=0;
//		boolean allVisited=false;
//		boolean end=false;
//		int m=j;
		while(path.isEmpty()==false){
//		while(end=true){
			if(visited.get(i, j)!=0||path.inStack(j)==true){
				for(int k=0;k<this.vertexCount();k++){
					if(visited.get(i, k)==0&&path.inStack(k)==false&&i!=k){
//						j=this.next(i, k);
						j=k;
						break;
					}
				}
				if(j==0){
					int s=path.peek();
					path.pop();
					if(path.isEmpty()==true){
						break;
					}
					i=path.peek();
					for(int t=0;t<vertexCount();t++){
						if(path.inStack(t)==false&&visited.get(s, t)==1){
							visited.set(s, t, 0);
							visited.set(t, s, 0);
						}
					}
				}
			}
			if(visited.get(i, j)==0&&path.inStack(j)==false&&path.peek()!=goal&&i>-1){
				path.push(j);
				visited.set(i, j, 1);
				visited.set(j, i, 1);
				i=j;
//				j=this.next(i, 0);
				j=0;
			}
			boolean allVisited=true;
			for(int t=0;t<vertexCount();t++){
				
				if(visited.get(i, t)==0){
					allVisited=false;
					break;
				}
			}
			if(allVisited==true&&path.peek()!=goal){
				int s=path.peek();
				path.pop();
				if(path.isEmpty()==true){
					break;
				}
				i=path.peek();
				for(int t=0;t<vertexCount();t++){
					if(path.inStack(t)==false&&visited.get(s, t)==1){
						visited.set(s, t, 0);
						visited.set(t, s, 0);
					}
				}
				for(int k=0;k<this.vertexCount();k++){
					if(visited.get(i, k)==0&&path.inStack(k)==false){
//						j=this.next(i, k);
						j=k;
						break;
					}
				}
			}
//			System.out.println(path.peek());
			if(path.peek()==goal&&i>-1){
				path.outPut();
				System.out.println();
				path.pop();
				i=path.peek();
				for(int k=0;k<this.vertexCount();k++){
					if(visited.get(i, k)==0&&path.inStack(k)==false){
//						j=this.next(i, k);
						j=k;
						break;
					}
				}
			}
		}
		
		
		
	}
	

	
}
