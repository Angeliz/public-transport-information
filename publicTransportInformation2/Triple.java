package publicTransportInformation2;

public class Triple implements Comparable<Triple>, Addible<Triple>{		//稀疏矩阵非0元素三元组类
	int row,column,value;												//行号、列号、元素值
	public Triple (int row,int column,int value){						//有参构造方法
		if(row>=0&&column>=0){
			this.row=row;
			this.column=column;
			this.value=value;
		}
		else{
			throw new IllegalArgumentException("行、列号不能为负数");
		}
	}
	public Triple(Triple tri){											//拷贝构造方法，复制一个三元数组
		this.(tri.row, tri.column, tri.value);
	}
	public String toString(){
		
	}

}
