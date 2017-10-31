package publicTransportInformation2;

public class SinglyList<String> extends Object{
	public String data;													    //数据域
	public SinglyList<String> next;				 							//地址域
	public SinglyList<String> head;               							//头指针
	
	public SinglyList(String data,SinglyList<String> next){  				//构造结点
		this.data=data;
		this.next=next;
	}
	
	//构造函数
	public SinglyList(){                          							//构造空单链表
		this.head=new SinglyList<String> (null,null);		      			//创造头结点，data和next的值均为null	
	}
	
	public boolean isEmpty(){
		return this.head.next==null;
	}
	//取出元素
	public String get(int i){					  							//返回第i个元素，若i越界，则返回null
		SinglyList<String> p=this.head.next;	  
		for(int j=0;p!=null&&j<i;j++){			  							//遍历单链表，返回第i个元素
			p=p.next;
		}
		return (i>=0 && p!=null)?p.data:null;
	}
	
	//计算size
	public int size(){
		SinglyList<String> p=head;											//p指向头结点
		int n=0;								
		while(p!=null){														//通过遍历计算
			n++;
			p=p.next;
		}
		return n-1;															//将最后一个null也算进去了所以减一
	}
	
	//覆盖Object类的toString方法
	public java.lang.String toString(){
		java.lang.String str="(";
		for(SinglyList<String> p=this.head.next; p!=null; p=p.next){		//遍历单链表
			str=str+p.data.toString();
			if(p.next!=null){
				str+=",";													//不是最后一个节点时加分隔符
			}
		}
		return str+")";
	}
	
	
	//插入
	public SinglyList<String> insert(int i,String x){
		if(x==null){
			throw new NullPointerException("x==null");      				//抛出空对象异常
		}
		SinglyList<String> front=this.head;									//指向头结点
		for(int j=0;(front.next!=null)&&j<i;j++){
			front=front.next;
		}
		front.next=new SinglyList<String>(x,front.next);
		return front.next;
	}
	public SinglyList<String> insert(String x){
		return insert(Integer.MAX_VALUE,x);
	}
	
	//删除
	public String remove(int i){											//删除第i个元素，若i越界，则返回null
		SinglyList<String> front=this.head;									//指向头结点
		for(int j=0;front.next!=null&&j<i;j++){								//遍历寻找i-1个结点
			front=front.next;
		}
		if(i>=0&&front.next!=null){											//若front的后继节点存在，就删除
			String old=front.next.data;
			front.next=front.next.next;
			return old;
		}
		return null;
	}
}