package publicTransportInformation2;



public class SeqStack {
	private SeqList list;						//使用顺序表存储栈元素
	public int startLength=0;
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
		startLength++;
	}
	public int peek(){								//返回栈顶元素
		return (int) this.list.get(list.size()-1);
	}
	public int pop(){								//出栈
		startLength--;
		return (int)list.remove(list.size()-1);
		
	}
	
	
	public boolean inStack(int i){
		int[] duplicates=new int[this.startLength];
//		SeqStack reverse=new SeqStack();
//		while(this.isEmpty()==false){
//			reverse.push(this.peek());
//			this.pop();
//		}
		for(int j=0;j<duplicates.length;j++){
			duplicates[duplicates.length-j-1]=this.peek();
			this.pop();
		}
		for(int j=0;j<duplicates.length;j++){
			this.push(duplicates[j]);
		}
//		while(reverse.isEmpty()==false&&reverse.peek()!=i){
//			this.push(reverse.peek());
//			reverse.pop();
//			if(reverse.isEmpty()==true){
//				return false;
//			}
//		}
		for(int j=0;j<duplicates.length;j++){
			if(duplicates[j]==i){
				return true;
			}
		}
		return false;
	}
	public void outPut(){							//反序输出栈内元素
		SeqStack reverse=new SeqStack();
		while(this.isEmpty()==false){
			reverse.push(this.peek());
			this.pop();
		}
		while(reverse.isEmpty()==false){
			System.out.print(reverse.peek()+" ");
			this.push(reverse.peek());
			reverse.pop();
		}
	}
	
	
}
