package net.aidencooper.vanth.media;

import org.komamitsu.spring.data.sqlite.EnableSqliteRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSqliteRepositories 
public class MediaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaServerApplication.class, args);
	}

}
