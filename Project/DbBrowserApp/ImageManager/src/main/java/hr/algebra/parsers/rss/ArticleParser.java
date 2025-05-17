/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Image;
import hr.algebra.utilities.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author bubif
 */
public class ArticleParser {
    
    private static final String RSS_URL = "https://www.nasa.gov/feeds/iotd-feed";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    public static List<Image> parse() throws Exception {
        List<Image> images = new ArrayList<>();
        
        
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        
        try(InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);
            
            Optional<TagType> tagType = Optional.empty();
            Image image = null;
            StartElement startElement = null;
            
            
            
            while (reader.hasNext()) {                
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            image = new Image();
                            images.add(image);
                        }
                        
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (tagType.isPresent() && image != null) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            switch (tagType.get()) {
                            case TITLE:
                                if (!data.isEmpty()) {
                                    image.setTitle(data);
                                }
                                break;
                            case LINK:
                                if (!data.isEmpty()) {
                                    image.setLink(data);
                                }
                                break;
                            case DESCRIPTION:
                                if (!data.isEmpty()) {
                                    image.setDescription(data);
                                }
                                break;
                            case ENCLOSURE:
                                if (startElement != null && image.getPicturePath() == null) {
                                    Attribute att = startElement.getAttributeByName(new QName(ATTRIBUTE_URL));
                                    if (att != null) {
                                        handlePicture(image, att.getValue());
                                    }
                                }
                                break;
                            case PUB_DATE:
                                if (!data.isEmpty()) {
                                    image.setPublishedDate(LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME));
                                }
                                break;
                            }
                        }
                        break;

                }
            } 
        }
        
        return images;
    }

    private static void handlePicture(Image image, String pictureUrl) {
        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
            
            String localPath = DIR + File.separator + UUID.randomUUID() + ext;
            
            FileUtils.copyFromUrl(pictureUrl, localPath);
            image.setPicturePath(localPath);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArticleParser() {
    }
    
    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        LINK("link"),
        DESCRIPTION("description"),
        ENCLOSURE("enclosure"),
        PUB_DATE("pubDate");
        
        
        private String name;

        
        private TagType (String name) {
            this.name = name;
        }
        
        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            
            return Optional.empty();
        }

        
    }
    
}
