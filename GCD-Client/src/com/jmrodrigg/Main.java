package com.jmrodrigg;

import com.google.gson.internal.Pair;

import java.io.IOException;
import java.util.Scanner;

import static com.jmrodrigg.GCPClient.search;

/**
 * Author: jrodriguezg
 * Date: 7/15/16
 */
public class Main {

    private static OAuth oAuth = new OAuth(Main.class.getClassLoader().getResourceAsStream("keys/api_key.json"));

    public static void main(String[] args) {
        System.out.print("Type your email address: ");
        String username = new Scanner(System.in).next();
        System.out.print("");

        if(oAuth.authorize(username)) {
            try {
                Pair<Integer,String> response = search(oAuth.getAccessToken());
                System.out.print(response.second);
            } catch (IOException ex) {
                System.out.print("IOException.");
            }
        } else System.out.print("Error: Unauthorized.");

    }
}
