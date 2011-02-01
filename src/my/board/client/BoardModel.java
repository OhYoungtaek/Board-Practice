package my.board.client;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

public class BoardModel extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L; 
	
	public BoardModel(){}
	
	public BoardModel(String name, String title, String content){
		set("name",name);
		set("title",title);
		set("content",content);
	}
	
	public String getName(){
		return (String) get("name");
	}
	
	public String getTitle(){
		return (String) get("title");
	}
	
	public String getContent(){
		return (String) get("content");
	}
}

