package com.example.games;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.games.model.Game;
import com.example.games.model.GameType;
import com.example.games.model.Studio;
import com.example.games.repository.GameRepository;
import com.example.games.repository.StudioRepository;

@SpringBootApplication
public class GamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(GameRepository gameRepository, StudioRepository studioRepository) {
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

			Game starcraft = new Game();
			starcraft.setTitle("Starcraft");
			starcraft.setType(GameType.RTS);
			starcraft.setPlatform("PC");
			starcraft.setStudio(blizzard);

			gameRepository.save(starcraft);
		};
	}
}
