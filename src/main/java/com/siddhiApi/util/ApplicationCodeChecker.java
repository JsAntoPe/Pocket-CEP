package com.siddhiApi.util;

import com.siddhiApi.entity.Application;
import com.siddhiApi.entity.Stream;
import com.siddhiApi.services.StreamService;
import com.siddhiApi.services.StreamServiceImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        Matcher matcher;
        for (String property : propertiesJSON.keySet()){
            matcher = Pattern.compile("select.*(" + property +").*" + lastControlStatement)
                    .matcher(application.getApplicationCode());
            logger.info("Pattern: " + "select.*(" + property +").*" + lastControlStatement);
            if(matcher.find()){
                logger.info("What matcher has found: " + application.getApplicationCode().substring(183));
                int firstMatch = matcher.start(1); //By writing 1 here, we achieve that JAVA only looks for what it is between the parenthesis.
                propertyFirstAppearancePriorityQueue.add(new PropertyFirstAppearance(property, firstMatch));
            } else {
                throw new Exception("The property " + property + " does not appear on the select statement.");
            }
        }

        String[] propertiesSorted = new String[propertyFirstAppearancePriorityQueue.size()];
        logger.info("Priority Queue Size: " + propertyFirstAppearancePriorityQueue.size());

        int i = 0;
        while(propertyFirstAppearancePriorityQueue.size() != 0){
            PropertyFirstAppearance property = propertyFirstAppearancePriorityQueue.poll();
            logger.info("Property extracted firstAppearance: " + property.getFirstAppearance());
            logger.info("Property value: " + property.getProperty());
            propertiesSorted[i] = property.getProperty();
            ++i;
        }
        propertyOrderedDatabase.addOutputStreamPropertiesOrdered(application.getOutputStreamName(), propertiesSorted);
    }
}
