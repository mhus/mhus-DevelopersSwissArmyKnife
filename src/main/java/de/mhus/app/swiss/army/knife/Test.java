package de.mhus.app.swiss.army.knife;

import java.net.URI;

public class Test {

    public static void main(String[] args) {
        String url = "http://user:pass@www.host.de/path";
        URI uri = URI.create(url);
        System.out.println("URL: " + url);
        System.out.println("URI: " + uri);
        System.out.println("RAW: " + uri.getRawUserInfo());
        System.out.println("UI : " + uri.getUserInfo());
        System.out.println("AU : " + uri.getAuthority());
    }

}
