package publicTransportInformation2;

public class SinglyList<String> extends Object{
	public String data;													    //������
	public SinglyList<String> next;				 							//��ַ��
	public SinglyList<String> head;               							//ͷָ��
	
	public SinglyList(String data,SinglyList<String> next){  				//������
		this.data=data;
		this.next=next;
	}
	
	//���캯��
	public SinglyList(){                          							//����յ�����
		this.head=new SinglyList<String> (null,null);		      			//����ͷ��㣬data��next��ֵ��Ϊnull	
	}
	
	public boolean isEmpty(){
		return this.head.next==null;
	}
	//ȡ��Ԫ��
	public String get(int i){					  							//���ص�i��Ԫ�أ���iԽ�磬�򷵻�null
		SinglyList<String> p=this.head.next;	  
		for(int j=0;p!=null&&j<i;j++){			  							//�������������ص�i��Ԫ��
			p=p.next;
		}
		return (i>=0 && p!=null)?p.data:null;
	}
	
	//����size
	public int size(){
		SinglyList<String> p=head;											//pָ��ͷ���
		int n=0;								
		while(p!=null){														//ͨ����������
			n++;
			p=p.next;
		}
		return n-1;															//�����һ��nullҲ���ȥ�����Լ�һ
	}
	
	//����Object���toString����
	public java.lang.String toString(){
		java.lang.String str="(";
		for(SinglyList<String> p=this.head.next; p!=null; p=p.next){		//����������
			str=str+p.data.toString();
			if(p.next!=null){
				str+=",";													//�������һ���ڵ�ʱ�ӷָ���
			}
		}
		return str+")";
	}
	
	
	//����
	public SinglyList<String> insert(int i,String x){
		if(x==null){
			throw new NullPointerException("x==null");      				//�׳��ն����쳣
		}
		SinglyList<String> front=this.head;									//ָ��ͷ���
		for(int j=0;(front.next!=null)&&j<i;j++){
			front=front.next;
		}
		front.next=new SinglyList<String>(x,front.next);
		return front.next;
	}
	public SinglyList<String> insert(String x){
		return insert(Integer.MAX_VALUE,x);
	}
	
	//ɾ��
	public String remove(int i){											//ɾ����i��Ԫ�أ���iԽ�磬�򷵻�null
		SinglyList<String> front=this.head;									//ָ��ͷ���
		for(int j=0;front.next!=null&&j<i;j++){								//����Ѱ��i-1�����
			front=front.next;
		}
		if(i>=0&&front.next!=null){											//��front�ĺ�̽ڵ���ڣ���ɾ��
			String old=front.next.data;
			front.next=front.next.next;
			return old;
		}
		return null;
	}
}