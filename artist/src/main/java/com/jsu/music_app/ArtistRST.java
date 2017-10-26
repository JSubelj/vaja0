package com.jsu.music_app;


import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("artists")
public class ArtistRST {

    @GET
    public Response getAllArtists(){
        List<Artist> artists=ArtistDB.getArtists();
        return Response.ok(artists).build();
    }

    @POST
    public Response addNewArtist(Artist artist) {
        ArtistDB.addArtist(artist);
        return Response.noContent().build();
    }

    @GET
    @Path("/query")
    public Response getArtist(@QueryParam("id") String id){

        Artist artist=ArtistDB.getArtist(id);
        if(artist == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(artist).build();
    }


}
