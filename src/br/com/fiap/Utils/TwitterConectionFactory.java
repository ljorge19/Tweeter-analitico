package br.com.fiap.Utils;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterConectionFactory {

	public static Twitter main(String[] args) {
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = loadAccessToken();
		Twitter twitter = factory.getSingleton();
		twitter.setOAuthConsumer("RaF5GQKW2tm9aiMnAHXKApE6m", "RMXHYcdBRhPpxL2Av4spjP1bF8ZVkVVB7EQfDnNcT158GxSNb0");

		twitter.setOAuthAccessToken(accessToken);
		
		return twitter;

	}

	private static AccessToken loadAccessToken() {
		String token = "292034075-W76wjDEqKTy1GhphgxglyVWRtU49qDBGI2ORZAO2";
		String tokenSecret = "dUlw80GwNQXPd0JOewyWf09KbkS1yKv0cLLgkXE0UFJvA";
		return new AccessToken(token, tokenSecret);

	}

}
