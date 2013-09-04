package models;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Post extends Model {

	public String title;
	public Date postedAt;
	@Lob
	public String content;
	@ManyToOne
	public User author;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	public
	 Set<Comment> comments;	

	public Post(User author, String title, String content) {
		comments = new LinkedHashSet<Comment>(0);		
		this.author = author;
		this.title = title;
		this.content = content;
		this.postedAt = new Date();
	}

}
