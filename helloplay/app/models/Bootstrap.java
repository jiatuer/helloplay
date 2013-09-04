package models;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;


@OnApplicationStart
public class Bootstrap extends Job {

	public void loadUsers() {
		if (User.count() < 1) {
			Fixtures.loadModels("init_user.yml");
		}
	}

}
