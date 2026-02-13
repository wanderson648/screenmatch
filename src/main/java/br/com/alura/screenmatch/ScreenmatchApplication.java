package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSeries;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverterDados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ScreenmatchApplication.class);

    public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        var consumoAPI = new ConsumoAPI();
        log.info("Consumindo series da API");
        var jsonSeries = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=d97119bc");
        System.out.println(jsonSeries);

        log.info("Convertendo JSON para DadosSeries");
        ConverterDados converteDadosSeries = new ConverterDados();
        DadosSeries dadosSeries = converteDadosSeries.converter(jsonSeries, DadosSeries.class);
        System.out.println(dadosSeries);

        log.info("Consumindo epsodios da API");
        var jsonEp = consumoAPI.obterDados("https://omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=d97119bc");
        System.out.println(jsonEp);

        log.info("Convertendo JSON para DadosEpsodios");
        ConverterDados converterDadosEp = new ConverterDados();
        DadosEpisodio dadosEp = converterDadosEp.converter(jsonEp, DadosEpisodio.class);
        System.out.println(dadosEp);

        System.out.println("=======================================");
        log.info("Dados das temporadas");
        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i<= dadosSeries.totalTemporadas(); i++) {
            jsonEp = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=d97119bc");
            DadosTemporada dadosTemporada = converterDadosEp.converter(jsonEp, DadosTemporada.class);
            temporadas.add(dadosTemporada);

        }
        temporadas.forEach(System.out::println);
    }
}
