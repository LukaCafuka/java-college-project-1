/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bubif
 */
@XmlRootElement(name = "imagearchive")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImageArchive {
    public ImageArchive() {
        
    }


    @XmlElementWrapper   
    @XmlElement(name = "image")
    private List<Image> images;

    public ImageArchive(List<Image> images) {
        this.images = images;
    }

    public List<Image> getPapers() {
        return images;
    }
}
