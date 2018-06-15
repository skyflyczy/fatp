package com.fatp.util;



import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapUtil {
    /**
     * Object转化为Map
     * @return Map<String,Object>
     */
    @SuppressWarnings("rawtypes")
	public static Map<String, Object> getValueMap(Object obj) {  
    	  
    	Map<String,Object> returnMap = new HashMap<String, Object>(); 
    	try {
    		Class type = obj.getClass(); 
            BeanInfo beanInfo = Introspector.getBeanInfo(type); 

            PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
            for (int i = 0; i< propertyDescriptors.length; i++) { 
                PropertyDescriptor descriptor = propertyDescriptors[i]; 
                String propertyName = descriptor.getName(); 
                if (!propertyName.equals("class")) { 
                    Method readMethod = descriptor.getReadMethod(); 
                    Object result = readMethod.invoke(obj, new Object[0]); 
                    if (result != null) { 
                        returnMap.put(propertyName, result); 
                    }
                } 
            } 
		} catch (Exception e) {
			
		}
        return returnMap; 
  
    } 
    
}
