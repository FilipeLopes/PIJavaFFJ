/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao; 
  
import java.util.ArrayList;  
import java.util.List;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="banC")
@SessionScoped
public class ImageSwitchBeanBanner {  
  
    private List<String> images;  
  
    public ImageSwitchBeanBanner() {  
        images = new ArrayList<String>();  
        images.add("cccc.jpg");  
        images.add("dddd.jpg");    
    }  
  
    public List<String> getImages() {  
        return images;  
    }  
} 
