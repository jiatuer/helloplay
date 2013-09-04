package models;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.junit.Test;

import play.db.jpa.Model;

@Entity
public class Comment extends Model {

	public String author;
	
	@Lob
	public String content;
	
	public Date postedAt;
	
	@ManyToOne
	public Post post;
	

	
	
	public Comment(Post post, String author, String content) {
		this.post = post;
		this.author = author;
		this.content = content;
		this.postedAt = new Date();
	}


}
