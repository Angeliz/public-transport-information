package publicTransportInformation2;

public class Matrix {
	private int rows,columns;				//行列
	private int[][] elements;				//二维数组
	public Matrix(int m,int n){				//有参构造方法，构造m*n零矩阵
		this.elements=new int[m][n];		//数组元素初值为0
		this.rows=m;
		this.columns=n;
	}
	public Matrix(int n){					//有参构造，n*n
		this(n,n);
	}
	public Matrix(int m,int n,int[][] value){	//m*n,数组提供元素
		this(m,n);
		for(int i=0;i<value.length&i<m;i++){
			for(int j=0;j<value[i].length&&j<n;j++){
				this.elements[i][j]=value[i][j];
			}
		}
	}
	
}
