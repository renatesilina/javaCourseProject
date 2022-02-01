package musicapplication.servercommunication;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

/**
 * Class for making credentials and getting an access token.
 * Access token is necessary for information retrieval from Spotify API
 */
public class RequestAccessToken {
    // Make request parameters
    private final String clientId = "your client id";
    private final String clientSecret = "your client secret token";

    /**
     * Method for getting a token to be able to use spotify web API
     *
     * @return token to use in every request to API
     * @throws IOException
     */
    public String getToken() throws IOException {

        // Make url encoded string from all parameters
        String headers = (Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));
        String token = null;

        try {
            // Send a post request to api/token
            HttpRequest request = HttpRequest.newBuilder(new URI("https://accounts.spotify.com/api/token/"))
                    // Request body parameter
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                    .setHeader("Authorization", "Basic " + headers)
                    .setHeader("Content-type", "application/x-www-form-urlencoded")
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            // Set response header
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Headers: " + response.headers());
            System.out.println("Body: " + response.body());

            // Split the token string from returned body string
            String[] splitToken = response.body().split("\"", 5);
            token = splitToken[3];
            System.out.println("token: " + token);

        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
        return token;
    }

//        public static void main(String[] args) throws IOException {
//                RequestAccessToken request = new RequestAccessToken();
//                request.getToken();
//        }
}


