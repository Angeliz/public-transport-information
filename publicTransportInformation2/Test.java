package publicTransportInformation2;
//����
//1.��ͼ�ṹ�洢վ�����ݣ���ѯ��������վ��֮��Ķ���·�������˷���
//2.�������·����ѡ����ѻ��˷���
//3.ģ����ѯ
//4.˵�����ݵ��߼��ṹ�ʹ洢�ṹ
//5.�ɲ�����Щ��ʩ���㷨����߲��Ҳ���Ч�ʣ�������㷨��ƽ�����ҳ���ASL
//6.˵��ʲô����ѻ��˷����Լ�����ȷ����ѻ��˷������
public class Test {
/*
 Ȩֵ����
1.ʱ����̣�·�̣�����
2.Ʊ����ͣ����人�����ͨͳһ�������ʱ�ֶμƼۣ�2Ԫ�ɳ���9���3Ԫ�ɳ���14���3Ԫ����ÿ����1Ԫ�ɳ����Ĺ���������һ���ε���2���4Ԫ�ɳ���21���5Ԫ�ɳ���30���6Ԫ�ɳ���41�����
                       ÿ�γ˳���ʱ180���ӣ�
3.·�����
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] vertices={"A","B","C","D","E"};
		Triple edges[]={new Triple(0,1,45),new Triple(0,2,28),new Triple(0,3,10),
				new Triple(1,0,45),new Triple(1,2,12),new Triple(1,4,21),
				new Triple(2,0,28),new Triple(2,1,12),new Triple(2,3,17),new Triple(2,4,26),
				new Triple(3,0,10),new Triple(3,2,17),new Triple(3,4,15),
				new Triple(4,1,21),new Triple(4,2,26),new Triple(4,3,15)	
		};
		MatrixGraph<String> graph=new MatrixGraph<String>(vertices,edges);
		System.out.println(graph.toString());
		graph.shortestPath();
		graph.FindAllPath(0, 3);
	}

}
