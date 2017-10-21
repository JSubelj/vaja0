package com.jsu.music_app;


import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("Artists")
public class ArtistRST {

    @GET
    public Response getAllArtists(){
        List<Artist> albums=ArtistDB.getArtists();
        return Response.ok(albums).build();
    }

    @POST
    public Response addNewArtist(Artist artist) {
        ArtistDB.addArtist(artist);
        return Response.noContent().build();
    }

    @GET
    @Path("/id/{id}")
    public Response getArtist(@PathParam("id") String id){
        Artist artist=ArtistDB.getArtist(id);
        if(artist == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(artist).build();
    }
}
