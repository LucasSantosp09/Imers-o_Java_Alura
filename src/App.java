import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        

        // FAZER A CONEXÃO HTTP E BUSCAR OS TOP 250 FILMES
        
        String url = "https://alura-imdb-api.herokuapp.com/movies";

        URI endereco = URI.create(url);
        
        var Client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = Client.send(request, BodyHandlers.ofString());

        String body = response.body();

        System.out.println(body);

        //PEGAR SÓ OS DADOS QUE INTERESSAM (TITULO, POSTER, CLASSIFICAÇÃO)
        var parser = new JsonParser();
        List<Map<String, String>> ListadeFilmes = parser.parse(body);

        //EXIBIR E MANIPULAR OS DADOS
        for (Map<String,String> filme : ListadeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}
