package net.lafox.muza.controller;

import net.lafox.muza.entity.Image;
import net.lafox.muza.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("i")
public class ImageViewController {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ImageViewController.class);

    @Qualifier("imageServiceImpl")
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "{id:\\d+}-{op:w|h|c|e|o}-w{w:\\d+}-h{h:\\d+}-q{q:\\d+}-v{ver:\\d+}.{ext:png|jpg|gif}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getAvatar(
            @PathVariable long id,
            @PathVariable String op,
            @PathVariable int w,
            @PathVariable int h,
            @PathVariable int q,
            @SuppressWarnings("unused") @PathVariable long ver,
            @PathVariable String ext
    ) throws IOException {
        double quality = q > 0 && q <= 100 ? q / 100.0 : 0.7;
        byte[] imgBytes;
        Image img = imageService.get(id);
        switch (op) {
            case "c":
                imgBytes = imageService.crop(img, w, h, quality, ext);
                break;
            case "w":
                imgBytes = imageService.width(img, w, quality, ext);
                break;
            case "h":
                imgBytes = imageService.height(img, h, quality, ext);
                break;
            default:
                imgBytes = null;
                break;
        }
        //TODO: default image!
        return new ResponseEntity<>(imgBytes, imageService.getHttpHeaders(img, w, h, quality, ext, op), HttpStatus.OK);
    }
}
