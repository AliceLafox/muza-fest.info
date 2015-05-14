package net.lafox.muza.service;

import net.lafox.generic.GenericService;
import net.lafox.muza.entity.Image;
import org.springframework.http.HttpHeaders;

@SuppressWarnings("unused")
public interface ImageService extends GenericService<Image, Long> {

    byte[] crop(Image image, int w, int h);

    byte[] crop(Image image, int w, int h, double quality);

    byte[] crop(Image image, int w, int h, double quality, String ext);

    byte[] width(Image image, int w, double quality, String ext);

    byte[] height(Image image, int h, double quality, String ext);

    HttpHeaders getHttpHeaders(Image image, int w, int h, double quality, String ext, String op);
}
