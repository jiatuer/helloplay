package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
//        render();
    	Post firstPost = Post.find(" order by postedAt desc").first();
    	List<Post> postList = Post.find(" order by postedAt desc").from(0).fetch(10);
    	render(firstPost,postList);    	
    }

}