package publicTransportInformation2;

public class SeqStack {
	private SeqList list;						//使用顺序表存储栈元素
	public SeqStack(int length){						//构造容量为length的空栈
		this.list=new SeqList<String>(length);			
	}
	public SeqStack(){
		this(64);
	}
	public boolean isEmpty(){							//判断栈是否为空
		return this.list.isEmpyt();
	}
	public void push(int x){							//元素x入栈
		this.list.insert(x);							//尾插入
	}
	public int peek(){								//返回栈顶元素
		return (int)this.list.get(list.size()-1);
	}
	public int pop(){								//出栈
		return (int)list.remove(list.size()-1);
	}
	public boolean inStack(int i){
		if(this.isEmpty()==false){
			while(this.peek()!=i){
				this.pop();
				if(this.isEmpty()==true){
					return false;
				}
			}
		}
		return true;
	}
	public void outPut(){
		SeqStack reverse=new SeqStack();
		while(this.isEmpty()==false){
			reverse.push(this.peek());
			this.pop();
		}
		while(reverse.isEmpty()==false){
			System.out.print(reverse.peek()+" ");
			reverse.pop();
		}
	}
	
	
}
