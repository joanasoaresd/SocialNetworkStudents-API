package br.ufpb.dsc.dto;

import java.time.LocalDateTime;
import java.util.Date;

import br.ufpb.dsc.entities.Comments;
import lombok.Data;

@Data
public class CommentsDTO {
	private int id;
    private String userEmail;
	private LocalDateTime date;
	private String msg;

    public CommentsDTO(Comments c) {
		this.id = c.getId();
		this.userEmail = c.getUserEmail();
		this.date = c.getDate();
		this.msg = c.getMsg();
	}	

	public CommentsDTO(String msg, String email) {
		super();
		this.date = LocalDateTime.now();
		this.msg = msg;
		this.userEmail = email;
	}

    
}
