package controllers;

import models.User;
import play.mvc.Controller;

public class UserTask extends Controller {


	public static void add(User u){
		u.save();
		render(u);
	}
	
}
