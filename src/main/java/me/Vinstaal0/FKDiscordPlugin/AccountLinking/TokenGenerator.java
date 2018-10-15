package me.Vinstaal0.FKDiscordPlugin.AccountLinking;

import java.util.Random;

/**
 * Created by Vinstaal0 on 11-4-2018.
 */
public class TokenGenerator {

    private Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890";

    private String token;

    public TokenGenerator() {
        token = this.createToken();
    }

    public String getToken() {
        return token;
    }

    private String createToken() {
        StringBuilder token = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
            token.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }

        return token.toString();
    }

}
