package com.amm.rpsgame;

import com.amm.rpsgame.exceptions.GameExecutionFailedException;
import com.amm.rpsgame.model.Game;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@AllArgsConstructor
public class RpsGameApplication implements CommandLineRunner {

	private final Game game;

	public static void main(String[] args) {
		SpringApplication.run(RpsGameApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			game.playGame();
		} catch(Exception e) {
			e.printStackTrace();
			throw new GameExecutionFailedException("Game failed to execute.");
		}
	}
}
