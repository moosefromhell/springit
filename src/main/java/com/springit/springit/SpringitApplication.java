package com.springit.springit;

import com.springit.springit.config.SpringitProperties;
import com.springit.springit.domain.Comment;
import com.springit.springit.domain.Link;
import com.springit.springit.repository.CommentRepository;
import com.springit.springit.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class SpringitApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
		System.out.println("Welcome to SpringIt!");
	}

	@Bean
	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
		return args -> {
			Link link = new Link("Getting started with spring boot 2", "https://therealdanvega.com/spring-boot-2");
			linkRepository.save(link);

			Comment comment = new Comment("The size of your lips is unprecedented!", link);
			commentRepository.save(comment);
			link.addComment(comment);

			System.out.println("We just inserted a link and a comment");
			System.out.println("8=====================================D");

			Link firstLink = linkRepository.findByTitle("Getting started with spring boot 2");
			System.out.println(firstLink.getTitle());
		};
	}
}
