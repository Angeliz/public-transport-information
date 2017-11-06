package publicTransportInformation2;
import java.util.ArrayList; 
public class Node<String> {
	public String data;
	public Node<String> next;
	public Node(String data,Node<String> next){
		this.data=data;
		this.next=next;
	}
	public Node(){
		this(null,null);
	}
	public java.lang.String toString(){
		return this.data.toString();
	}
	
	//
	public ArrayList<Node> relationNodes = new ArrayList<Node>();  
	public String getData() {  
        return this.data;  
    } 
	public void setData(String data) {  
        this.data = data;  
    }  
  
    public ArrayList<Node> getRelationNodes() {  
        return relationNodes;  
    }  
  
    public void setRelationNodes(ArrayList<Node> relationNodes) {  
        this.relationNodes = relationNodes;  
    }
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}  
}
