package publicTransportInformation2;

public class MatrixString {
	private int rows,columns;				//行列
	private String[][] elementsString;
	public MatrixString(int m,int n){				//有参构造方法，构造m*n零矩阵
		this.elementsString=new String[m][n];		//数组元素初值为0
		this.rows=m;
		this.columns=n;
	}
	public MatrixString(int n){					//有参构造，n*n
		this(n,n);
	}
//	public MatrixString(int m,int n,int[][] value){	//m*n,数组提供元素
//		this(m,n);
//		for(int i=0;i<value.length&i<m;i++){
//			for(int j=0;j<value[i].length&&j<n;j++){
//				this.elements[i][j]=value[i][j];
//			}
//		}
//	}
	public int getRows(){					//返回矩阵行数
		return this.rows;
	}
	public int getColumns(){				//返回列数
		return this.columns;
	}
	public String get(int i,int j){			//返回第i行第j列的元素
		if(i>=0&&i<this.rows&&j>=0&&j<this.columns){
			return this.elementsString[i][j];
		}
		else{
			throw new IndexOutOfBoundsException("序号越界");
		}
	}
	public void set(int i,int j,String x){		//设置第i行第j列的元素
		if(i>=0&&i<this.rows&&j>=0&&j<this.columns){
			this.elementsString[i][j]=x;
		}
		else{
			throw new IndexOutOfBoundsException("序号越界");
		}
	}
	public String toString(){
		String str=" 矩阵"+"("+this.rows+"x"+this.columns+")";
		for(int i=0;i<this.rows;i++){
			for(int j=0;j<this.columns;j++){
				str+=String.format("%6d", this.elementsString[i][j]); 	//"%6"格式表示十进制整数占6列
			}
			str+="/n";
		}
		return str;
		
	}
	
	//设置矩阵为m行n列。若参数指定行列数较大，则将矩阵扩容，并复制原矩阵元素
	public void setRowsColumns(int m,int n){
		if(m>0&&n>0){
			if(m>this.elementsString.length||n>this.elementsString[0].length){
				String[][] source=this.elementsString;
				this.elementsString=new String[m][n];						//重新申请二维数组空间，元素初值为0
				for(int i=0;i<this.rows;i++){						//复制原二维数组
					for(int j=0;j<this.columns;j++){
						this.elementsString[i][j]=source[i][j];
					}
				}
			}
			this.rows=m;
			this.columns=n;
		}
		else{
			throw new IllegalArgumentException("行列数不能小于等于0");
		}
	}
	
}
