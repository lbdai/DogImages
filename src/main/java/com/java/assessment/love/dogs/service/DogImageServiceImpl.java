package com.java.assessment.love.dogs.service;

import com.java.assessment.love.dogs.rest.RestemplateServiceImpl;
import com.java.assessment.love.dogs.rest.responseModel.Breed;
import com.java.assessment.love.dogs.rest.responseModel.DogImage;
import com.java.assessment.love.dogs.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class DogImageServiceImpl implements  IDogImageService {

    private final RestemplateServiceImpl restTemplate;

    @Value("${dogs.api.key}")
    private String apiKey;

    @Value("${dogs.api.url}")
    private String apiUrl;

    @Autowired
    IBreedService breedService;

    private final String IMAGE_ENDPOINT = "/images";

    @Autowired
    public DogImageServiceImpl(RestemplateServiceImpl restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void uploadImage(MultipartFile file, String breedId, String subId) throws IOException {
        String url = apiUrl + IMAGE_ENDPOINT +  "/upload";
        log.debug(url);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("breed_ids", breedId);
        body.put("sub_id", subId);
        restTemplate.postFile(url, file, body);
    }

    @Override
    public void deleteImagesBreedId(String imageId,String breedId) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("imageId", imageId);
        body.put("breedId", breedId);
        String url = apiUrl + IMAGE_ENDPOINT  + "/{imageId}/breeds/{breedId}";
        restTemplate.delete(url, body);
    }

    @Override
    public List<DogImage> getImages(Integer page, Integer limit, String order) {
        String url = StringUtils.buildUrl(apiUrl + IMAGE_ENDPOINT, limit, page, order);
        log.debug(url);
        DogImage[] response = restTemplate.get(url, null, DogImage[].class);
        return Arrays.asList(response);
    }

    @Override
    public String getImageById(String id) {

        List<DogImage> dogImages = getImages(null, null, null);
        if(CollectionUtils.isEmpty(dogImages)) {
            return "";
        }

        DogImage dogImage = dogImages.stream().filter(item -> item.getId().equalsIgnoreCase(id)).findFirst().get();
        //dogImage.setUrl("https://78.media.tumblr.com/2bc94b9eec2d00f5d28110ba191da896/tumblr_nyled8DYKd1qg9kado1_1280.jpg");
        //String url = "https://78.media.tumblr.com/2bc94b9eec2d00f5d28110ba191da896/tumblr_nyled8DYKd1qg9kado1_1280.jpg";
        byte[] bytes =  restTemplate.get(dogImage.getUrl(), null, byte[].class);
        return Base64.getEncoder().encodeToString(bytes);
    }

}
