package br.ufpb.dsc.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
	private int id;

	private LocalDateTime date;

	private String msg;

	private int isRemove = 0;

	private String userEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

	public Comments() {}	

	public Comments(Subject s, String uEmail, String msg) {
		super();
		this.subject = s;
		this.userEmail = uEmail;
		this.msg = msg;
	}

}