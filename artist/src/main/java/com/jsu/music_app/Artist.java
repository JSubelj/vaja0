package com.jsu.music_app;


import java.util.List;

public class Artist {
    private String name;
    private String id;
    private List<String> AlbumIds;
    private List<String> Albums;

    public List<String> getAlbums() {

        return Albums;
    }

    public void setAlbums(List<String> Albums) {
        this.Albums = Albums;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbumIds(List<String> AlbumIds) {
        this.AlbumIds = AlbumIds;
    }

    public List<String> getAlbumIds() {
        return AlbumIds;
    }

}
