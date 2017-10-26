package com.jsu.music_app;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistDB {


    private static List<Artist> artists = new ArrayList<>();

    public static List<Artist> getArtists() {
        return artists;
    }

    public static Artist getArtist(String artistId) {
        for (Artist artist : artists) {
            if (artist.getId().equals(artistId)){
                List<Album> albums = new ArrayList<Album>();

                for (String id : artist.getAlbumIds()) {
                    //System.out.println(id);
                    //System.out.println(getAlbums(id));
                    albums.add(getAlbums(id));

                }
                System.out.println(albums);
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

    public static Album getAlbums(String albumId) {
        Optional<String> baseUrl=Optional.of("http://192.168.99.100:8080");

        Client httpClient = ClientBuilder.newClient();

        if (baseUrl.isPresent()) {

            try {
                return httpClient
                        .target(baseUrl.get() + "/v1/albums/query?id=" + albumId)
                        .request().get(new GenericType<Album>() {
                        });
            } catch (WebApplicationException | ProcessingException e) {
                System.out.println(e);
                throw new InternalServerErrorException(e);
            }
        } else {
            return new Album();
        }
    }
}

