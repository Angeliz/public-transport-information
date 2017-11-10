package publicTransportInformation2;

public class SeqStack {
	private SeqList list;						//ʹ��˳���洢ջԪ��
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
	}
	public int peek(){								//����ջ��Ԫ��
		return (int)this.list.get(list.size()-1);
	}
	public int pop(){								//��ջ
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
