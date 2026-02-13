package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosSeries;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverterDados;
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
    public void run(String... args) throws Exception {
        var consumoAPI = new ConsumoAPI();
        log.info("Consumindo API");
        var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=d97119bc");
        System.out.println(json);

        log.info("Convertendo JSON para DadosSeries");
        ConverterDados converteDados = new ConverterDados();
        DadosSeries dados = converteDados.converter(json, DadosSeries.class);
        System.out.println(dados);
    }
}
