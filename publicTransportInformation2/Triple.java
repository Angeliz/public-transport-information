package publicTransportInformation2;

public class Triple implements Comparable<Triple>{		//稀疏矩阵非0元素三元组类,用做图带权值的边
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
		this(tri.row, tri.column, tri.value);
	}
	public String toString(){
		return "("+row+","+column+","+value+")";
	}
	
	//根据行列位置比较三元组对象大小，与元素值无关，约定三元组排序次序
	public int compareTo(Triple tri){
		if(this.row==tri.row && this.column==tri.column){				//区别equals
			return 0;
		}
		return (this.row<tri.row||this.row==tri.row && this.column<tri.column)?-1:1;
	}
//	public void add(Triple term){										//加法运算
//		if(this.compareTo(term)==0){
//			this.value+=term.value;										
//		}
//		else{
//			throw new IllegalArgumentException("两项指数不同不能相加");
//		}
//	}
//	public boolean removable(){
//		return this.value==0;
//	}
	public Triple toSymmetry(){
		return new Triple(this.column,this.row,this.value);
	}

}
