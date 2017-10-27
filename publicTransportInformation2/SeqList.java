package publicTransportInformation2;

public class SeqList<String> extends Object {
	protected Object[] element; 									//对象数组存储顺序表的数据元素，保护成员
	protected int n;												//顺序表元素个数（长度）
	public SeqList(int length){										//构造长度为length的空表
		this.element=new Object[length];							//申请数组存储空间，元素为null
		this.n=0;													
	}
	public SeqList(){												//无参构造
		this(64);													//调用上一个构造方法
	}
	public SeqList(String[] values){
		this(values.length);
		for(int i=0;i<values.length;i++){							//复制数组元素
			this.element[i]=values[i];
		}
		this.n=element.length;
	}
	
	public boolean isEmpyt(){										//判断列表是否为空
		return this.n==0;
	}
	public int size(){												//顺序表元素个数
		return this.n;
	}
	public String get(int i){										//获取第i个元素，若下标越界，返回null
		if(i>=00 && i<this.n){
			return (String)this.element[i];
		}
		return null;
	}
	public void set(int i,String x){ 								//置值
		if(x==null){
			throw new NullPointerException("x==null");    			//抛出空对象异常
		}
		if(i>=0 && i<this.n){
			this.element[i]=x;
		}
		else{
			throw new java.lang.IndexOutOfBoundsException(i+"");	//抛出越界异常
		}
	}
	public java.lang.String toString(){								//重载toString方法
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
		Object[] source=this.element;								//数组引用赋值
		if(this.n==element.length){									//若数组满，扩充顺序表的数组容量
			this.element=new Object[source.length*2];
			for(int j=0;j<i;j++){
				this.element[j]=source[j];
			}
		}
		for(int j=this.n-1;j>=i;j--){   							//向后移动
			this.element[j+1]=source[j];
		}
		this.element[i]=x;
		this.n++;
		return i;
	}
	public int insert(String x){
		return this.insert(this.n, x);
	}
	public String remove(int i){									//删除
		if(this.n>0&&i>=0&&i<this.n){
			String old=(String)this.element[i];						//old中存储被删元素
			for(int j=i;j<this.n-1;j++){
				this.element[j]=this.element[j+1];
			}
			this.element[this.n-1]=null;							//设置原数组对象为空，释放原引用实例
			this.n--;
			return old;												//返回old局部变量引用的对象，传递引用对象
		}
		return null;
	}
}
