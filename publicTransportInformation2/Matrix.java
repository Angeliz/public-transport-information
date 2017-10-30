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
	public int getRows(){					//���ؾ�������
		return this.rows;
	}
	public int getColumns(){				//��������
		return this.columns;
	}
	public int get(int i,int j){			//���ص�i�е�j�е�Ԫ��
		if(i>=0&&i<this.rows&&j>=0&&j<this.columns){
			return this.elements[i][j];
		}
		else{
			throw new IndexOutOfBoundsException("���Խ��");
		}
	}
	public void set(int i,int j,int x){		//���õ�i�е�j�е�Ԫ��
		if(i>=0&&i<this.rows&&j>=0&&j<this.columns){
			this.elements[i][j]=x;
		}
		else{
			throw new IndexOutOfBoundsException("���Խ��");
		}
	}
	public String toString(){
		String str=" ����"+"("+this.rows+"x"+this.columns+")";
		for(int i=0;i<this.rows;i++){
			for(int j=0;j<this.columns;j++){
				str+=String.format("%6d", this.elements[i][j]); 	//"%6"��ʽ��ʾʮ��������ռ6��
			}
			str+="/n";
		}
		return str;
		
	}
	
	//���þ���Ϊm��n�С�������ָ���������ϴ��򽫾������ݣ�������ԭ����Ԫ��
	public void setRowsColumns(int m,int n){
		if(m>0&&n>0){
			if(m>this.elements.length||n>this.elements[0].length){
				int[][] source=this.elements;
				this.elements=new int[m][n];						//���������ά����ռ䣬Ԫ�س�ֵΪ0
				for(int i=0;i<this.rows;i++){						//����ԭ��ά����
					for(int j=0;j<this.columns;j++){
						this.elements[i][j]=source[i][j];
					}
				}
			}
			this.rows=m;
			this.columns=n;
		}
		else{
			throw new IllegalArgumentException("����������С�ڵ���0");
		}
	}
	
	
}
