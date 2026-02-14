package br.com.alura.screenmatch;

import br.com.alura.screenmatch.principal.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ScreenmatchApplication.class);

    public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @Override
    public void run(String... args) {
        Principal principal = new Principal();
        principal.exibeMenu();
    }
}
