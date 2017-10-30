package publicTransportInformation2;

public abstract class AbstractGraph<String> {
	protected static final int MAX_WEIGHT=0x0000ffff; 	//���Ȩֵ����ʾ�����ޣ�
	protected SeqList<String> vertexlist;				//����˳����洢ͼ�Ķ��㼯��
	public AbstractGraph(int length){					//�вι��췽����������Ϊ0��lengthָ��˳�������
		this.vertexlist=new SeqList(length);
	}
	public AbstractGraph(){								//�޲ι��췽��
		this(10);										//������Ϊ0��Ĭ��˳�������Ϊ10
	}
	public int vertexCount(){							//����ͼ�Ķ�����
		return this.vertexlist.size();					//����˳���Ԫ�ظ���
	}
	public java.lang.String toString(){					//����ͼ�Ķ��㼯�������ַ���
		return "���㼯�ϣ� "+this.vertexlist.toString()+"\n";
	}
	public String getVertex(int i){						//���ض���Ԫ��
		return this.vertexlist.get(i);					//��iԽ�磬����null
	}
	public void setVertex(int i,String x){				//���õ�i������Ϊx
		this.vertexlist.set(i, x);   					//��iԽ�磬�׳��쳣
	}
	
	//���󷽷�
	public abstract int insertVertex(String x);			//����һ������Ԫ�أ����ض������
	public abstract void removeVertex(int x);			//ɾ����i�����㼰��صı�
	public abstract int weight(int i,int j);			//���رߵ�Ȩֵ
	protected abstract int next(int i,int j);			//����vi��vj֮��ĺ���ڽӶ�������			
	
}
