package net.lafox.muza.service;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.lafox.generic.GenericDao;
import net.lafox.generic.GenericServiceAbstract;
import net.lafox.muza.entity.Image;
import net.lafox.util.ImgRec;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageServiceImpl extends GenericServiceAbstract<Image, Long> implements ImageService {
    @Autowired
    public void setGenericDao(GenericDao<Image, Long> genericDao) {
        super.setGenericDao(genericDao);
    }

    @Override
    public byte[] crop(Image image, int w, int h) {
        return crop(image, w, h, 0.8, "jpg");
    }

    @Override
    public byte[] crop(Image image, int w, int h, double quality) {
        return crop(image, w, h, quality, "jpg");
    }

    private long EXPIRE;
    private Map<String, MediaType> TYPES = new HashMap<>();

    @PostConstruct
    private void init() {
        EXPIRE = new DateTime().plusYears(1).getMillis();
        TYPES.put("png", MediaType.IMAGE_PNG);
        TYPES.put("gif", MediaType.IMAGE_GIF);
        TYPES.put("jpg", MediaType.IMAGE_JPEG);
        TYPES = Collections.unmodifiableMap(TYPES);
    }

    @Override
    public byte[] crop(Image image, int w, int h, double quality, String ext) {
        try (InputStream in = new ByteArrayInputStream(image.getImg()); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImgRec imgRec = cropRec(w, h, image.getWidth(), image.getHeight());
            Thumbnails
                    .of(in)
                    .sourceRegion(Positions.CENTER, imgRec.getWidth(), imgRec.getHeight())
                    .forceSize(w, h)
                    .outputQuality(quality)
                    .outputFormat(ext)
                    .toOutputStream(out);
            return out.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public byte[] width(Image image, int w, double quality, String ext) {
        try (InputStream in = new ByteArrayInputStream(image.getImg()); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Thumbnails
                    .of(in).width(w)
                    .outputQuality(quality)
                    .outputFormat(ext)
                    .toOutputStream(out);
            return out.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public byte[] height(Image image, int h, double quality, String ext) {
        try (InputStream in = new ByteArrayInputStream(image.getImg()); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Thumbnails
                    .of(in).height(h)
                    .outputQuality(quality)
                    .outputFormat(ext)
                    .toOutputStream(out);
            return out.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public HttpHeaders getHttpHeaders(Image image, int w, int h, double quality, String ext, String op) {
        HttpHeaders headers = new HttpHeaders();
        headers.setExpires(EXPIRE);
        headers.setContentType(TYPES.get(ext));
        if (image != null && image.getImg() != null && image.getImg().length > 0) {
            headers.setETag("\""
                            + image.getId()
                            + "-" + op
                            + "-w" + w
                            + "-h" + h
                            + "-q" + String.format("%.2f", quality)
                            + "-v" + image.getVersion()
                            + "\""
            );
            headers.setLastModified(image.getUpdated().getTime());
        }
        return headers;
    }
///////////////////////////////////////
    private ImgRec cropRec(int width, int height, int widthOrig, int heightOrig){
        if (width<=0 || height <=0 || widthOrig <=0 || heightOrig <=0 ) return new ImgRec(1,1);
        double wCrop, hCrop;
        double kOrig = (double) widthOrig / (double) heightOrig;
        double kQuery = (double)width / (double)height;
        if (kOrig > kQuery) {
            hCrop = (double) heightOrig;
            wCrop = hCrop * kQuery;
        } else {
            wCrop = (double) widthOrig;
            hCrop = wCrop / kQuery;
        }
        return new ImgRec(wCrop,hCrop);
    }
}
