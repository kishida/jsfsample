/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.jsfsample;

import java.lang.reflect.InvocationTargetException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author kishida
 */
public class EnumConverter<T extends Enum> implements Converter{

    Class<T> cls;

    public EnumConverter(Class<T> cls) {
        this.cls = cls;
    }

    public EnumConverter() {
        this(null);
    }
            
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return cls.getMethod("valueOf", String.class).invoke(null, value);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return "";
        }
        if(cls.isInstance(value)){
            return ((T)value).name();
        }
        return "";
    }
    
}
