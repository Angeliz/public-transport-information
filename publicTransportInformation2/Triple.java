package publicTransportInformation2;

public class Triple implements Comparable<Triple>, Addible<Triple>{		//ϡ������0Ԫ����Ԫ����
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
		this.(tri.row, tri.column, tri.value);
	}
	public String toString(){
		
	}

}
