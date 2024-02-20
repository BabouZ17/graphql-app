package com.example.games;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.games.model.Game;
import com.example.games.model.GameType;
import com.example.games.model.Studio;
import com.example.games.model.Review;
import com.example.games.repository.GameRepository;
import com.example.games.repository.StudioRepository;
import com.example.games.repository.ReviewRepository;

@SpringBootApplication
public class GamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(GameRepository gameRepository, StudioRepository studioRepository,
			ReviewRepository reviewRepository) {
		return args -> {

			Studio blizzard = new Studio();
			blizzard.setName("Blizzard");

			studioRepository.save(blizzard);

			Game wow = new Game();
			wow.setTitle("World of warcraft");
			wow.setType(GameType.MMORPG);
			wow.setPlatform("PC");
			wow.setStudio(blizzard);

			gameRepository.save(wow);

			Review one = new Review();
			one.setNumberOfStars(4);
			one.setComment("Really fun");
			one.setGame(wow);

			Review two = new Review();
			two.setNumberOfStars(5);
			two.setComment("Adictive");
			two.setGame(wow);

			reviewRepository.save(one);
			reviewRepository.save(two);

			Game starcraft = new Game();
			starcraft.setTitle("Starcraft");
			starcraft.setType(GameType.RTS);
			starcraft.setPlatform("PC");
			starcraft.setStudio(blizzard);

			gameRepository.save(starcraft);

			Review third = new Review();
			third.setNumberOfStars(5);
			third.setComment("Amazing");
			third.setGame(starcraft);
			reviewRepository.save(third);
		};
	}
}
