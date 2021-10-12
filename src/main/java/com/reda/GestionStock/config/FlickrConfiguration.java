package com.reda.GestionStock.config;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.blogs.Service;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Configuration
public class FlickrConfiguration {

    @Value("${flickr.apiKey}") // spring va automatiquement charger les données (key) de flickr qui se trouve dans la class application.yml
    private String apiKey ;
    @Value("${flickr.apiSecret}")
    private String apiSecret;

    @Value("${flickr.appKey}") // spring va automatiquement charger les données (key) de flickr qui se trouve dans la class application.yml
    private String appKey ;
    @Value("${flickr.appSecret}")
    private String appSecret;

  /*  @Bean // pour que spring execute cette methode au demmarage
    public Flickr getFlickr() throws InterruptedException, ExecutionException, IOException, FlickrException {
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

        OAuth10aService service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));// DELETE pour données tous les droit lecture erciture et suppression
        final Scanner scanner = new Scanner(System.in);

        final OAuth1RequestToken requestToken = service.getRequestToken();

        final String authUrl = service.getAuthorizationUrl(requestToken);

        System.out.println(authUrl);
        System.out.println("past it here >> ");

        final String authorizationVerified = scanner.nextLine();

        OAuth1AccessToken accessToken = service.getAccessToken(requestToken, authorizationVerified);

        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());

        // verification que le token est valid
        Auth auth = flickr.getAuthInterface().checkToken(accessToken);

        System.out.println("--------------");
        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());

        return flickr;
    }

   */
    @Bean
  public Flickr getFlickr(){
      Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

      Auth auth = new Auth();

      auth.setPermission(Permission.DELETE);

      auth.setToken(appKey);
      auth.setTokenSecret(appSecret);

      RequestContext requestContext = RequestContext.getRequestContext();
      requestContext.setAuth(auth);

      flickr.setAuth(auth);

      return flickr;
  }
}
