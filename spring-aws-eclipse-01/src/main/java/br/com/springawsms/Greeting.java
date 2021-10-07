package br.com.springawsms;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Greeting {
	private Long id;
	private String content;
	
	public Greeting(Long id, String content) {
		this.id = id;
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}
