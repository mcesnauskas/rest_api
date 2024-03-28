package lt.mindaugas.rest_api.common.connection;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpConnection {
    private HttpRequest.Builder buildRequest;
    public static Request request = new Request();

    public static class Request {
        private StringBuilder url;
        private String bodyPublisher;
        private HttpRequest.Builder buildRequest;
        private HttpConnection httpConnection;

        public Request openUrl(String url) {
            this.url = new StringBuilder(url);
            return this;
        }

        public Request setParams(Map<String, String> queryParams) {
            StringBuilder queryBuilder = new StringBuilder();
            queryParams.forEach((key, value) -> {
                if (!queryBuilder.isEmpty()) {
                    queryBuilder.append("&");
                }
                queryBuilder
                        .append(encodeParameter(key))
                        .append("=")
                        .append(encodeParameter(value));
            });
            url.append("?").append(queryBuilder);
            return this;
        }

        public Request setHeaders(Map<String, String> headers) {
            buildRequest =
                    HttpRequest
                            .newBuilder()
                            .uri(URI.create(url.toString()));
            headers.forEach(buildRequest::header);
            return this;
        }

        public Request setBodyPublisher(Object src) {
            Gson gson = new Gson();
            bodyPublisher = gson.toJson(src);
            return this;
        }

        public HttpResponse<String> build(RequestMethod requestMethod) throws IOException, InterruptedException {
            httpConnection = new HttpConnection();
            httpConnection.buildRequest = this.buildRequest;
            setMethod(requestMethod);

            return HttpClient
                    .newHttpClient()
                    .send(
                            httpConnection.buildRequest.build(),
                            HttpResponse.BodyHandlers.ofString()
                    );
        }

        private String encodeParameter(String param) {
            return URLEncoder.encode(param, StandardCharsets.UTF_8);
        }

        private void setMethod(RequestMethod requestMethod) {
            switch (requestMethod) {
                case GET -> httpConnection.buildRequest.GET();
                case DELETE -> httpConnection.buildRequest.DELETE();
                case PUT -> httpConnection.buildRequest.PUT(HttpRequest.BodyPublishers.ofString(bodyPublisher));
                case POST -> httpConnection.buildRequest.POST(HttpRequest.BodyPublishers.ofString(bodyPublisher));
            }
        }
    }
}
