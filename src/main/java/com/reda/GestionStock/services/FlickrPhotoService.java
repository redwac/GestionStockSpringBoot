package com.reda.GestionStock.services;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface FlickrPhotoService {

    String savedPhoto (InputStream photo, String title) throws FlickrException;

}
