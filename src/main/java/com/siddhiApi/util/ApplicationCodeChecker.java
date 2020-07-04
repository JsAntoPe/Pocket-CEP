package com.siddhiApi.util;

import com.siddhiApi.entity.Application;
import com.siddhiApi.entity.Stream;
import com.siddhiApi.services.StreamService;
import com.siddhiApi.services.StreamServiceImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationCodeChecker {

    static Logger logger = LoggerFactory.getLogger(ApplicationCodeChecker.class);

    private static StreamService streamService = new StreamServiceImpl();

    private static PropertyOrderedDatabase propertyOrderedDatabase = PropertyOrderedDatabase.getPropertyOrderedDatabase();

    public static void outputStreamCheck(Application application) throws Exception {
        String lastControlStatement;
        if(Pattern.compile("(group by)").matcher(application.getApplicationCode()).find()){
            lastControlStatement = "group by";
        } else {
            lastControlStatement = "insert into";
        }

        Stream stream = streamService.getStream(application.getOutputStreamName());
        PriorityQueue<PropertyFirstAppearance> propertyFirstAppearancePriorityQueue = new PriorityQueue<>();

        JSONObject propertiesJSON = new JSONObject(stream.getJsonSchema().get("properties").toString());
        for (String property : propertiesJSON.keySet()){
            Matcher matcher = Pattern.compile("select.*(" + property +").*" + lastControlStatement)
                    .matcher(application.getApplicationCode());
            logger.info("Pattern: " + "select.*(" + property +").*" + lastControlStatement);
            if(matcher.find()){
                int firstMatch = matcher.start();
                propertyFirstAppearancePriorityQueue.add(new PropertyFirstAppearance(property, firstMatch));
            } else {
                throw new Exception("The property does not appear on the select statement.");
            }
        }

        String[] propertiesSorted = new String[propertyFirstAppearancePriorityQueue.size()];
        int i = 0;
        for (PropertyFirstAppearance property: propertyFirstAppearancePriorityQueue){
            logger.info("Property sorted after priority queue: " + property.getProperty());
            propertiesSorted[i] = property.getProperty();
        }
        propertyOrderedDatabase.addOutputStreamPropertiesOrdered(application.getApplicationName(), propertiesSorted);
    }
}
