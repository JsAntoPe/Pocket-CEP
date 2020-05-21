package com.siddhiApi.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class EventStructure {
    private Logger logger = LoggerFactory.getLogger(EventStructure.class);

    private List<String> parametersList;
    private HashMap<String, String> typeOfParameters;


    public EventStructure(String application, String nameInputStream) {
        typeOfParameters = new HashMap<>();
        int beginParameter, endParameter, beginTypeParameter, endTypeParameter;
        String parameters = obtainingParameters(application, nameInputStream);
        logger.info("Parameters string: " + parameters);
        if (parameters != null){
            Pattern pattern = Pattern.compile("[(\\s](\\w*)");
            Matcher matcher = pattern.matcher(parameters);
            while(matcher.find()) {
                logger.info("Inicio del patron" + matcher.start());
                beginParameter = matcher.start() + 1;
                endParameter = matcher.end();
                matcher.find();
                beginTypeParameter = matcher.start() + 1;
                endTypeParameter = matcher.end();
                logger.info("Parameter: " + parameters.substring(beginParameter, endParameter));
                logger.info("Type of parameter: " + parameters.substring(beginTypeParameter, endTypeParameter));
                //typeOfParameters.put(application.substring(beginParameter, endParameter), application.substring(beginTypeParameter, endTypeParameter));
            }
            parametersList = new ArrayList<>(typeOfParameters.keySet());
        }
    }

    /**
     *
     * @param application Codigo de la aplicacion tal y como se introduce en el json.
     * @param nameInputStream Nombre del stream de input. Será lo que venga impreso al principio.
     * @return String que posee todos los parametros, para que se extraiga de ellos los tipos.
     */
    private String obtainingParameters(String application, String nameInputStream) {
        Pattern patternStart = Pattern.compile(nameInputStream);
        Matcher matcherStart = patternStart.matcher(application);
        if(matcherStart.find()){
            int beginningOfParameters, endOfParameters;
            beginningOfParameters = matcherStart.end();
            endOfParameters = application.indexOf(";");
            return application.substring(beginningOfParameters, endOfParameters);
        }
        else {
            return null;
        }
    }

    public HashMap<String, String> getTypeOfParameters() {
        return typeOfParameters;
    }

    public void setTypeOfParameters(HashMap<String, String> typeOfParameters) {
        this.typeOfParameters = typeOfParameters;
    }

    public List<String> getParameters() {
        return parametersList;
    }

    public void setParameters(List<String> parametersList) {
        this.parametersList = parametersList;
    }
}
