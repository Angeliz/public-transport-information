package publicTransportInformation2;



public class SeqStack {
	private SeqList list;						//ʹ��˳���洢ջԪ��
	public int startLength=0;
	public SeqStack(int length){						//��������Ϊlength�Ŀ�ջ
		this.list=new SeqList<String>(length);			
	}
	public SeqStack(){
		this(64);
	}
	public boolean isEmpty(){							//�ж�ջ�Ƿ�Ϊ��
		return this.list.isEmpyt();
	}
	public void push(int x){							//Ԫ��x��ջ
		this.list.insert(x);							//β����
		startLength++;
	}
	public int peek(){								//����ջ��Ԫ��
		return (int) this.list.get(list.size()-1);
	}
	public int pop(){								//��ջ
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
	public void outPut(){							//�������ջ��Ԫ��
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
