# Codec

Compressor de arquivos em Java que utiliza os algoritmos LZ77, ~Delta~ e Golomb (parcial) em sequência (extensível para mais algoritmos e pipelines).

Um jar é gerado ao realizar build com o maven.

Como rodar:

```
java -jar codec.jar <arquivo_de_origem> <-c|-d> <arquivo_de_destino>
```

Onde c = comprimir, e d = descomprimir
