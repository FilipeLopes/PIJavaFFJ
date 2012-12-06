/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao; 
  
import java.util.ArrayList;  
import java.util.List;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="imgC")
@SessionScoped
public class ImageSwitchBean {  
  
    private List<String> images;  
  
    public ImageSwitchBean() {  
        images = new ArrayList<String>();  
        images.add("aaaa.jpg");  
        images.add("bbbb.jpg");    
    }  
  
    public List<String> getImages() {  
        return images;  
    }  
} 
