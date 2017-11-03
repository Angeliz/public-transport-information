package publicTransportInformation2;
//ÐèÇó£º
//1.ÓÃÍ¼½á¹¹´æ´¢Õ¾µãÊý¾Ý£¬²éÑ¯ÈÎÒâÁ½¸öÕ¾µãÖ®¼äµÄ¶àÌõÂ·¾¶¼°»»³Ë·½°¸
//2.¼ÆËã×î¶ÌÂ·¾¶£¬Ñ¡Ôñ×î¼Ñ»»³Ë·½°¸
//3.Ä£ºý²éÑ¯
//4.ËµÃ÷Êý¾ÝµÄÂß¼­½á¹¹ºÍ´æ´¢½á¹¹
//5.¿É²ÉÓÃÄÄÐ©´ëÊ©ºÍËã·¨ÒÔÌá¸ß²éÕÒ²Ù×÷Ð§ÂÊ£¬Çó²éÕÒËã·¨µÄÆ½¾ù²éÕÒ³¤¶ÈASL
//6.ËµÃ÷Ê²Ã´ÊÇ×î¼Ñ»»³Ë·½°¸ÒÔ¼°ÔõÑùÈ·¶¨×î¼Ñ»»³Ë·½°¸×î¼Ñ
//7.拥挤度？？
public class Test {
/*
 È¨Öµ¿¼ÂÇ
1.Ê±¼ä×î¶Ì£ºÂ·³Ì£¬»»³Ë
2.Æ±¼Û×îµÍ£º£¨Îäºº¹ìµÀ½»Í¨Í³Ò»°´Àï³ÌÏÞÊ±·Ö¶Î¼Æ¼Û£º2Ôª¿É³Ë×ø9¹«Àï£»3Ôª¿É³Ë×ø14¹«Àï£»3ÔªÒÔÉÏÃ¿Ôö¼Ó1Ôª¿É³Ë×øµÄ¹«ÀïÊý±ÈÉÏÒ»Çø¶ÎµÝÔö2¹«Àï£¨4Ôª¿É³Ë×ø21¹«Àï£¬5Ôª¿É³Ë×ø30¹«Àï£¬6Ôª¿É³Ë×ø41¹«Àï£©¡£
                       Ã¿´Î³Ë³µÏÞÊ±180·ÖÖÓ£©
3.Â·¾¶×î¶Ì
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
	}

}
