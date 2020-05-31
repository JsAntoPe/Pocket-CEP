package com.siddhiApi.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EventStructure {
    private List<String> parametersList;
    private HashMap<String, String> typeOfParameters;


    public EventStructure(String nameInputStream, String application) {
        typeOfParameters = new HashMap<>();
        int beginParameter, endParameter, beginTypeParameter, endTypeParameter;
        String parameters = obtainingParameters(application, nameInputStream);
        if (parameters != null){
            Pattern pattern = Pattern.compile("[(\\s](\\w*)");
            Matcher matcher = pattern.matcher(parameters);
            while(matcher.find()) {
                beginParameter = matcher.start() + 1;
                endParameter = matcher.end();
                matcher.find();
                beginTypeParameter = matcher.start() + 1;
                endTypeParameter = matcher.end();
                typeOfParameters.put(parameters.substring(beginParameter, endParameter), parameters.substring(beginTypeParameter, endTypeParameter));
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
