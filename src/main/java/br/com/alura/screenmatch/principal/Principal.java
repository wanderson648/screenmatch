package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSeries;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverterDados;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverterDados conversor = new ConverterDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=d97119bc";

    private Scanner leitura = new Scanner(System.in);

    public void exibeMenu() {
        System.out.print("Digite o nome da série para a busca: ");
        var nomeSerie = leitura.nextLine();

        var tituloEncoded = URLEncoder.encode(nomeSerie, StandardCharsets.UTF_8);

        var json = consumo.obterDados(ENDERECO + tituloEncoded + API_KEY);
        DadosSeries dadosSeries = conversor.converter(json, DadosSeries.class);



        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i<= Integer.parseInt(dadosSeries.totalTemporadas()); i++) {
            json = consumo.obterDados(ENDERECO + tituloEncoded + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.converter(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);

        }

//        temporadas.forEach(System.out::println);
//        System.out.println("==================================");

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
