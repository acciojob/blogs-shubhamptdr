package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

     @Autowired
    BlogRepository blogRepository2;

    public Image createAndReturn(Blog blog, String description, String dimensions) {
        //create an image based on given parameters and add it to the imageList of given blog
//        if(!blogRepository2.findById(blog.getId()).isPresent()){
//            throw new Exception();
//        }
        Blog blog1 = blogRepository2.findById(blog.getId()).get();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog1);

        blog1.getImageList().add(image);

        blogRepository2.save(blog1);
        return image;
    }

    public void deleteImage(Image image){
        imageRepository2.deleteById(image.getId());
    }

    public Image findById(int id) {
        return  imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image1, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0

        String[] scrArray = screenDimensions.split("X");
        String imageDim = image1.getDimensions();
        String[] imgArray = imageDim.split("X");

        int scrl = Integer.parseInt(scrArray[0]);
        int scrb = Integer.parseInt(scrArray[1]);

        int imgl = Integer.parseInt(imgArray[0]);
        int imgb = Integer.parseInt(imgArray[1]);
        return noImages(scrl,scrb,imgl,imgb);
    }
    private int noImages(int scrl, int scrb, int imgl, int imgb){
        int lenC = scrl/imgl;
        int lenB = scrb/imgb;
        return lenC*lenB;
    }
}
