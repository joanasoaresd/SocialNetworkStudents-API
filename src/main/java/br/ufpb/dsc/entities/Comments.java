package br.ufpb.dsc.entities;

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

	private Date date;

	private String msg;

	private int isRemove;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

	public Comments() {}	

	public Comments(int id, Date date, String msg) {
		super();
		this.id = id;
		this.date = date;
		this.msg = msg;
		this.isRemove = 0;
	}

}