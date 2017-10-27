package publicTransportInformation2;

public class SeqList<String> extends Object {
	protected Object[] element; 									//��������洢˳��������Ԫ�أ�������Ա
	protected int n;												//˳���Ԫ�ظ��������ȣ�
	public SeqList(int length){										//���쳤��Ϊlength�Ŀձ�
		this.element=new Object[length];							//��������洢�ռ䣬Ԫ��Ϊnull
		this.n=0;													
	}
	public SeqList(){												//�޲ι���
		this(64);													//������һ�����췽��
	}
	public SeqList(String[] values){
		this(values.length);
		for(int i=0;i<values.length;i++){							//��������Ԫ��
			this.element[i]=values[i];
		}
		this.n=element.length;
	}
	
	public boolean isEmpyt(){										//�ж��б��Ƿ�Ϊ��
		return this.n==0;
	}
	public int size(){												//˳���Ԫ�ظ���
		return this.n;
	}
	public String get(int i){										//��ȡ��i��Ԫ�أ����±�Խ�磬����null
		if(i>=00 && i<this.n){
			return (String)this.element[i];
		}
		return null;
	}
	public void set(int i,String x){ 								//��ֵ
		if(x==null){
			throw new NullPointerException("x==null");    			//�׳��ն����쳣
		}
		if(i>=0 && i<this.n){
			this.element[i]=x;
		}
		else{
			throw new java.lang.IndexOutOfBoundsException(i+"");	//�׳�Խ���쳣
		}
	}
	public java.lang.String toString(){								//����toString����
		java.lang.String str="(";
		if(this.n>0){
			str+=this.element[0].toString();
		}
		for(int i=1;i<this.n;i++){
			str+=","+this.element[i].toString();
		}
		return str+")";
	}
	public int insert(int i,String x){
		if(x==null){
			throw new NullPointerException("x==null");
		}
		if(i<0){
			i=0;
		}
		if(i>this.n){
			i=this.n;
		}
		Object[] source=this.element;								//�������ø�ֵ
		if(this.n==element.length){									//��������������˳������������
			this.element=new Object[source.length*2];
			for(int j=0;j<i;j++){
				this.element[j]=source[j];
			}
		}
		for(int j=this.n-1;j>=i;j--){   							//����ƶ�
			this.element[j+1]=source[j];
		}
		this.element[i]=x;
		this.n++;
		return i;
	}
	public int insert(String x){
		return this.insert(this.n, x);
	}
	public String remove(int i){									//ɾ��
		if(this.n>0&&i>=0&&i<this.n){
			String old=(String)this.element[i];						//old�д洢��ɾԪ��
			for(int j=i;j<this.n-1;j++){
				this.element[j]=this.element[j+1];
			}
			this.element[this.n-1]=null;							//����ԭ�������Ϊ�գ��ͷ�ԭ����ʵ��
			this.n--;
			return old;												//����old�ֲ��������õĶ��󣬴������ö���
		}
		return null;
	}
}
