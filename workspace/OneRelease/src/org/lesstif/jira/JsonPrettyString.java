package org.lesstif.jira;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * class to json string formatter
 *
 */
public class JsonPrettyString {
	
	final public String toPrettyJsonString() {
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
		
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, this);
		} catch (IOException e) {
			return toString();
		}
		
		return sw.toString();
	}
	
	/**
	 * Map to Pretty Json String
	 * 
	 * @param map map data
	 * @return Json String 
	 */
	public static String mapToPrettyJsonString(Map<String, Object> map) {
	    ObjectMapper mapper = new ObjectMapper();
        
        mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
        
        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(map);
        } catch (IOException e) {           
            e.printStackTrace();
        } 
        
        return jsonStr;
	}
	
}
