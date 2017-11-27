package publicTransportInformation2;

import java.util.HashMap;
import java.util.Scanner;

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
		String[] vertices={"��ӻ���","��ͼ���","���·","����·","���ڱ�","�人��վ","��ɽ�㳡","����·","��������԰","���໨԰","����","ѭ����","����·","�ӼҴ�","��ȹ㳡","������","�ڹ�","������","���繫˾","�ƽ��"};
		HashMap<String , Integer> map = new HashMap<String , Integer>(); 
		for(int i=0;i<vertices.length;i++){
			map.put(vertices[i], i);
		}
		Triple edges[]={new Triple(0,1,14297),
				new Triple(1,0,14297),new Triple(1,2,9884),new Triple(1,9,5629),
				new Triple(2,1,9884),new Triple(2,3,1662),new Triple(2,9,6409),new Triple(2,10,2535),
				new Triple(3,2,1662),new Triple(3,4,14780),new Triple(3,11,1055),new Triple(3,12,1518),
				new Triple(4,3,14780),
				new Triple(5,6,12539),
				new Triple(6,5,12539),new Triple(6,7,927),new Triple(6,12,7103),
				new Triple(7,6,927),new Triple(7,13,7795),new Triple(7,14,8149),
				new Triple(8,9,6356),
				new Triple(9,1,5629),new Triple(9,2,6409),new Triple(9,8,6356),new Triple(9,10,4280),
				new Triple(10,2,2535),new Triple(10,9,4280),new Triple(10,11,4903),new Triple(10,16,4916),
				new Triple(11,3,1055),new Triple(11,10,4903),new Triple(11,12,879),new Triple(11,16,6496),
				new Triple(12,3,1518),new Triple(12,6,7103),new Triple(12,11,879),new Triple(12,13,5693),
				new Triple(13,7,7795),new Triple(13,12,5693),new Triple(13,17,5889),new Triple(13,18,14287),
				new Triple(14,7,8149),
				new Triple(15,16,11402),
				new Triple(16,10,4916),new Triple(16,11,6496),new Triple(16,15,11402),new Triple(16,17,2647),
				new Triple(17,13,5889),new Triple(17,16,2647),new Triple(17,18,8125),new Triple(17,19,6346),
				new Triple(18,13,14287),new Triple(18,17,8125),
				new Triple(19,17,6346)
		};
		MatrixGraph<String> graph=new MatrixGraph<String>(vertices,edges);
		System.out.println(graph.toString());
		
		while(true){
			System.out.println();
			System.out.println("��ѡ����������"+"\n"+"��������֮�����·��������:1"+"\n"+"��������֮������·��������:2"+"\n"+"�����㷨������:3");
			Scanner in=new Scanner(System.in);
			int num=in.nextInt();
			if(num==1){
				System.out.println("��ѡ������Ҫ�Ĳ�ѯ��ʽ��"+"\n"+"��ȷ��ѯ�����룺0"+"\n"+"ģ����ѯ�����룺1");
				Scanner inStyle=new Scanner(System.in);
				int style=inStyle.nextInt();
				if(style==0){
					System.out.println("���������");
					Scanner startIn=new Scanner(System.in);
					String str=startIn.nextLine();
					int start=map.get(str);
					System.out.println("�������յ�");
					Scanner endIn=new Scanner(System.in);
					str=endIn.nextLine();
					int end=map.get(str);
					graph.shortestPath(start,end);
					
				}
				if(style==1){
					//�洢ģ����ѯ�п�����Ҫ���Ķ���
					String possibleStart[]=new String[vertices.length];
					String possibleEnd[]=new String[vertices.length];
					System.out.println("���������");
					Scanner startIn=new Scanner(System.in);
					String strStart=startIn.nextLine();
					//�����������ģ���һ�������㣩����������
					for(int i=0;i<vertices.length;i++){
						for(int j=0;j<strStart.length();j++){
							if(vertices[i].indexOf(strStart.charAt(j))!=-1){
								possibleStart[i]=vertices[i];
								break;
							}
						}
					}
					System.out.println("�������յ�");
					Scanner endIn=new Scanner(System.in);
					String strEnd=endIn.nextLine();
					for(int i=0;i<vertices.length;i++){
						for(int j=0;j<strEnd.length();j++){
							if(vertices[i].indexOf(strEnd.charAt(j))!=-1){
								possibleEnd[i]=vertices[i];
							}
						}
						
					}
					//�����������ܵĶ����������
					for(int i=0;i<possibleStart.length;i++){
						if (possibleStart[i]==null){
							continue;
						}
						for(int j=0;j<possibleEnd.length;j++){
							if(possibleEnd[j]==null){
								continue;
							}
							int start=map.get(possibleStart[i]);
							int end=map.get(possibleEnd[j]);
							graph.shortestPath(start,end);
							
						}
					}
				}
				
			}
			if(num==2){
				System.out.println("��ѡ������Ҫ�Ĳ�ѯ��ʽ��"+"\n"+"��ȷ��ѯ�����룺0"+"\n"+"ģ����ѯ�����룺1");
				Scanner inStyle=new Scanner(System.in);
				int style=inStyle.nextInt();
				if(style==0){
					System.out.println("���������");
					Scanner startIn=new Scanner(System.in);
					String str=startIn.nextLine();
					int start=map.get(str);
					System.out.println("�������յ�");
					Scanner endIn=new Scanner(System.in);
					str=endIn.nextLine();
					int end=map.get(str);
					graph.FindAllPath(start,end);
					
				}
				if(style==1){
					//�洢ģ����ѯ�п�����Ҫ���Ķ���
					String possibleStart[]=new String[vertices.length];
					String possibleEnd[]=new String[vertices.length];
					System.out.println("���������");
					Scanner startIn=new Scanner(System.in);
					String strStart=startIn.nextLine();
					//�����������ģ���һ�������㣩����������
					for(int i=0;i<vertices.length;i++){
						for(int j=0;j<strStart.length();j++){
							if(vertices[i].indexOf(strStart.charAt(j))!=-1){
								possibleStart[i]=vertices[i];
								break;
							}
						}
					}
					System.out.println("�������յ�");
					Scanner endIn=new Scanner(System.in);
					String strEnd=endIn.nextLine();
					for(int i=0;i<vertices.length;i++){
						for(int j=0;j<strEnd.length();j++){
							if(vertices[i].indexOf(strEnd.charAt(j))!=-1){
								possibleEnd[i]=vertices[i];
							}
						}
						
					}
					//�����������ܵĶ����������
					for(int i=0;i<possibleStart.length;i++){
						if (possibleStart[i]==null){
							continue;
						}
						for(int j=0;j<possibleEnd.length;j++){
							if(possibleEnd[j]==null){
								continue;
							}
							int start=map.get(possibleStart[i]);
							int end=map.get(possibleEnd[j]);
							graph.FindAllPath(start,end);
						}
					}
				}
				
			}
			if(num==3){
				System.out.println("�����ѽ���");
				break;
			}
		}
	
//		while(true){
//			System.out.println();
//			System.out.println("��ѡ������Ҫ�Ĳ�ѯ��ʽ��"+"\n"+"��ȷ��ѯ�����룺1"+"\n"+"ģ����ѯ�����룺2"+"\n"+"�����㷨������:0");
//			Scanner inStyle=new Scanner(System.in);
//			int style=inStyle.nextInt();
//			if(style==1){
//				System.out.println("��ѡ����������"+"\n"+"��������֮�����·��������:1"+"\n"+"��������֮������·��������:2");
//				Scanner in=new Scanner(System.in);
//				int num=in.nextInt();
//				System.out.println("���������");
//				Scanner startIn=new Scanner(System.in);
//				String str=startIn.nextLine();
//				int start=map.get(str);
//				System.out.println("�������յ�");
//				Scanner endIn=new Scanner(System.in);
//				str=endIn.nextLine();
//				int end=map.get(str);
//				if(num==1){
//					graph.shortestPath(start,end);
//				}
//				if(num==2){
//					graph.FindAllPath(start,end);
//				}
//			}
//			if(style==2){
//				System.out.println("��ѡ����������"+"\n"+"��������֮�����·��������:1"+"\n"+"��������֮������·��������:2");
//				Scanner in=new Scanner(System.in);
//				int num=in.nextInt();
//				//�洢ģ����ѯ�п�����Ҫ���Ķ���
//				String possibleStart[]=new String[vertices.length];
//				String possibleEnd[]=new String[vertices.length];
//				System.out.println("���������");
//				Scanner startIn=new Scanner(System.in);
//				String strStart=startIn.nextLine();
//				//�����������ģ���һ�������㣩����������
//				for(int i=0;i<vertices.length;i++){
//					for(int j=0;j<strStart.length();j++){
//						if(vertices[i].indexOf(strStart.charAt(j))!=-1){
//							possibleStart[i]=vertices[i];
//							break;
//						}
//					}
//				}
//				System.out.println("�������յ�");
//				Scanner endIn=new Scanner(System.in);
//				String strEnd=endIn.nextLine();
//				for(int i=0;i<vertices.length;i++){
//					for(int j=0;j<strEnd.length();j++){
//						if(vertices[i].indexOf(strEnd.charAt(j))!=-1){
//							possibleEnd[i]=vertices[i];
//						}
//					}
//					
//				}
//				//�����������ܵĶ����������
//				for(int i=0;i<possibleStart.length;i++){
//					if (possibleStart[i]==null){
//						continue;
//					}
//					for(int j=0;j<possibleEnd.length;j++){
//						if(possibleEnd[j]==null){
//							continue;
//						}
//						int start=map.get(possibleStart[i]);
//						int end=map.get(possibleEnd[j]);
//						if(num==1){
//							graph.shortestPath(start,end);
//						}
//						if(num==2){
//							graph.FindAllPath(start,end);
//						}
//						
//					}
//				}
//			}
//			if(style==0){
//				System.out.println("�����ѽ���");
//				break;
//			}
//		}
		
		
	}

}
