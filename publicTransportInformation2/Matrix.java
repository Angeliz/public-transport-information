package publicTransportInformation2;

public class Matrix {
	private int rows,columns;				//����
	private int[][] elements;				//��ά����
	public Matrix(int m,int n){				//�вι��췽��������m*n�����
		this.elements=new int[m][n];		//����Ԫ�س�ֵΪ0
		this.rows=m;
		this.columns=n;
	}
	public Matrix(int n){					//�вι��죬n*n
		this(n,n);
	}
	public Matrix(int m,int n,int[][] value){	//m*n,�����ṩԪ��
		this(m,n);
		for(int i=0;i<value.length&i<m;i++){
			for(int j=0;j<value[i].length&&j<n;j++){
				this.elements[i][j]=value[i][j];
			}
		}
	}
	
}
