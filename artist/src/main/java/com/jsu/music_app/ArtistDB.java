package com.jsu.music_app;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ArtistDB {
    private static List<Artist> artists = new ArrayList<>();

    public static List<Artist> getArtists() {
        return artists;
    }

    public static Artist getArtist(String artistId) {
        for (Artist artist : artists) {
            if (artist.getId().equals(artistId)){
                List<String> albums = new ArrayList<String>();
                for (String id : artist.getAlbumIds()) {
                    System.out.println(GetRequest("http://192.168.99.100:8080/v1/Albums/id/" + id));
                    albums.add(GetRequest("http://192.168.99.100:8080/v1/Albums/id/" + id));
                    System.out.println(id);
                }
                artist.setAlbums(albums);

                return artist;
            }
        }

        return null;
    }

    public static void addArtist(Artist artist) {
        artists.add(artist);
    }

    public static void deleteArtist(String artistId) {
        for (Artist artist : artists) {
            if (artist.getId().equals(artistId)) {
                artists.remove(artist);
                break;
            }
        }
    }
    public static String GetRequest(String link) {
        try {

            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output = "";
            String tmp;
            //System.out.println("Output from Server .... \n");
            while ((tmp = br.readLine()) != null) {
                output += tmp;
            }

            conn.disconnect();
            return output;

        } catch (MalformedURLException e) {

            e.printStackTrace();
            return null;

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }
    }
}

