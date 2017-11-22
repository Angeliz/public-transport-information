package publicTransportInformation2;

public class MatrixString {
	private int rows,columns;				//����
	private String[][] elementsString;
	public MatrixString(int m,int n){				//�вι��췽��������m*n�����
		this.elementsString=new String[m][n];		//����Ԫ�س�ֵΪ0
		this.rows=m;
		this.columns=n;
	}
	public MatrixString(int n){					//�вι��죬n*n
		this(n,n);
	}
//	public MatrixString(int m,int n,int[][] value){	//m*n,�����ṩԪ��
//		this(m,n);
//		for(int i=0;i<value.length&i<m;i++){
//			for(int j=0;j<value[i].length&&j<n;j++){
//				this.elements[i][j]=value[i][j];
//			}
//		}
//	}
	public int getRows(){					//���ؾ�������
		return this.rows;
	}
	public int getColumns(){				//��������
		return this.columns;
	}
	public String get(int i,int j){			//���ص�i�е�j�е�Ԫ��
		if(i>=0&&i<this.rows&&j>=0&&j<this.columns){
			return this.elementsString[i][j];
		}
		else{
			throw new IndexOutOfBoundsException("���Խ��");
		}
	}
	public void set(int i,int j,String x){		//���õ�i�е�j�е�Ԫ��
		if(i>=0&&i<this.rows&&j>=0&&j<this.columns){
			this.elementsString[i][j]=x;
		}
		else{
			throw new IndexOutOfBoundsException("���Խ��");
		}
	}
	public String toString(){
		String str=" ����"+"("+this.rows+"x"+this.columns+")";
		for(int i=0;i<this.rows;i++){
			for(int j=0;j<this.columns;j++){
				str+=String.format("%6d", this.elementsString[i][j]); 	//"%6"��ʽ��ʾʮ��������ռ6��
			}
			str+="/n";
		}
		return str;
		
	}
	
	//���þ���Ϊm��n�С�������ָ���������ϴ��򽫾������ݣ�������ԭ����Ԫ��
	public void setRowsColumns(int m,int n){
		if(m>0&&n>0){
			if(m>this.elementsString.length||n>this.elementsString[0].length){
				String[][] source=this.elementsString;
				this.elementsString=new String[m][n];						//���������ά����ռ䣬Ԫ�س�ֵΪ0
				for(int i=0;i<this.rows;i++){						//����ԭ��ά����
					for(int j=0;j<this.columns;j++){
						this.elementsString[i][j]=source[i][j];
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
