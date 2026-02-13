package br.com.alura.screenmatch.service;

public interface IConverterDados {
    <T> T converter(String json, Class<T> tClass);
}
