package digdir.dc24_eu_wallet.service;

import jakarta.annotation.PreDestroy;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * This class represents a service witch is used to send and receive data
 * between endpoints. It gets given an url, token, and Json body, and return
 * a json string.
 *
 * @author Daniel Neset
 * @version 12.07.2024
 */
@Service
public class HttpService {

  private final CloseableHttpClient httpClient;

  /**
   * The constructor class that initialize the service.
   */
  public HttpService() {
    this.httpClient = HttpClients.createDefault();
  }

  /**
   * Handles the sending and receiving of data from an endpoint.
   *
   * @param url The url to send data to.
   * @param token The Authorization Bearer token.
   * @param body The Json body to be sent.
   * @return Return a Json String of the data.
   * @throws IOException Throws IOException if there is an error in the request.
   */
  public String postRequest(String url, String token, String body) throws IOException {
    HttpPost post = new HttpPost(url);
    post.setHeader("Content-type", "application/json");
    post.setHeader("Authorization", "Bearer " + token);
    post.setEntity(new StringEntity(body, ContentType.create("application/json", "UTF-8")));
    try (CloseableHttpResponse response = httpClient.execute(post)) {
      String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
      return responseBody;
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Destroy the object when closing the application.
   *
   * @throws IOException Throws IOException if there is an error when trying to close the HTTP client.
   */
  @PreDestroy
  public void close() throws IOException {
    httpClient.close();
  }

}
