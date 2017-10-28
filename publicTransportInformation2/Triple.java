package publicTransportInformation2;

public class Triple implements Comparable<Triple>{		//ϡ������0Ԫ����Ԫ����,����ͼ��Ȩֵ�ı�
	int row,column,value;												//�кš��кš�Ԫ��ֵ
	public Triple (int row,int column,int value){						//�вι��췽��
		if(row>=0&&column>=0){
			this.row=row;
			this.column=column;
			this.value=value;
		}
		else{
			throw new IllegalArgumentException("�С��кŲ���Ϊ����");
		}
	}
	public Triple(Triple tri){											//�������췽��������һ����Ԫ����
		this(tri.row, tri.column, tri.value);
	}
	public String toString(){
		return "("+row+","+column+","+value+")";
	}
	
	//��������λ�ñȽ���Ԫ������С����Ԫ��ֵ�޹أ�Լ����Ԫ���������
	public int compareTo(Triple tri){
		if(this.row==tri.row && this.column==tri.column){				//����equals
			return 0;
		}
		return (this.row<tri.row||this.row==tri.row && this.column<tri.column)?-1:1;
	}
//	public void add(Triple term){										//�ӷ�����
//		if(this.compareTo(term)==0){
//			this.value+=term.value;										
//		}
//		else{
//			throw new IllegalArgumentException("����ָ����ͬ�������");
//		}
//	}
//	public boolean removable(){
//		return this.value==0;
//	}
	public Triple toSymmetry(){
		return new Triple(this.column,this.row,this.value);
	}

}
