package my.board.client;

import java.io.Serializable;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class BoardEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	@Column(name="name")
	private String name;
	
	@Persistent
	@Column(name="title")
	private String title;
	
	@Persistent
	@Column(name="content")
	private String content;
	
	public BoardEntity(){}
	
	public BoardEntity(String name, String title, String content){
		super();
		this.name = name;
		this.title = title;
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
