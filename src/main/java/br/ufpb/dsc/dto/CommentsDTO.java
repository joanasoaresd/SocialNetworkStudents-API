package br.ufpb.dsc.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CommentsDTO {
    private int idUser;
	private Date date;
	private String msg;
	private int isRemove;

    public CommentsDTO() {}	

	public CommentsDTO(int idUser, Date date, String msg) {
		super();
		this.idUser = idUser;
		this.date = date;
		this.msg = msg;
		this.isRemove = 0;
	}

    
}
