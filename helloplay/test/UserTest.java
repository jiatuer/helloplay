import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import models.Comment;
import models.Post;
import models.User;
import play.test.UnitTest;

public class UserTest extends UnitTest {

	@Test
	public void createAndRetrieveUser() {
		// 添加
		User user = new User("yh.sniaw@gmail.com", "123456", "小机");
		assertNotNull(user.save());
		// 查询条件下的所有信息，并返回第一个
		User search = user.find("byEmailLike", "%gmail%").first();
		assertNotNull(search);
		assertEquals("123456", search.password);
	}

	@Test
	public void testConnectMethod() {
		// 添加
		User user = new User("yh.sniaw@gmail.com", "123456", "小机");
		assertNotNull(user.save());
		// 查询
		assertNotNull(User.connect("gmail", "123456"));
		assertNull(User.connect("test", "123456"));
		assertNull(User.connect("yh.sniaw@gmail.com", "aa"));
		assertNotNull(User.connect("yh.sniaw@gmail.com", "123456"));
	}

	@Test
	public void createPost() {
		// 添加
		User user = new User("yh.sniaw@gmail.com", "123456", "小机");
		assertNotNull(user.save());
		Post post = new Post(user, "title", "content");
		assertNotNull(post.save());
		assertEquals(1, Post.count());
		List<Post> list = Post.find("byAuthor", user).fetch();
		assertEquals(1, list.size());
		Post firstPost = list.get(0);
		assertNotNull(firstPost);
		assertEquals("title", firstPost.title);
		assertEquals("content", firstPost.content);
		assertNotNull(firstPost.postedAt);
		assertEquals(user, firstPost.author);
	}

	@Test
	public void createComments() {
		User user = new User("yh.sniaw@gmail.com", "123456", "小机");
		assertNotNull(user.save());
		Post post = new Post(user, "title", "content");
		assertNotNull(post.save());
		new Comment(post, "author1", "content1").save();
		new Comment(post, "author2", "content2").save();
		List<Comment> list = Comment.find("byPost", post).fetch();
		assertEquals(2, list.size());
		Comment firstComment = list.get(0);
		assertEquals("author1", firstComment.author);
		assertNotNull(firstComment.postedAt);
	}

	@Test
	public void postAddComments() {
		User user = new User("yh.sniaw@gmail.com", "123456", "小机");
		assertNotNull(user.save());
		Post post = new Post(user, "title", "content");
		assertNotNull(post.save());
		post.comments.add(new Comment(post, "author1", "content1"));
		post.comments.add(new Comment(post, "author2", "content2"));
		post.save();
		Post firstPost = Post.find("byTitle", "title").first();
		assertEquals(2, firstPost.comments.size());
		assertEquals(2, Comment.count());
	}

	@Test
	public void showUser() {
		System.out.println(User.count());
		User user = User.find("byEmail", "bob@gmail.com").first();
		assertNotNull(user);
		System.out.println(user);
	}

}
