package com.stargazerproject.resources.parameter;

import com.stargazerproject.resources.annotation.Parameters;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *  @name Schema 参数列表 schemaParameters
 *  @illustrate schemaParameters 参数
 *  @author Felixerio
 *  **/
@Component(value="schemaParameters")
@Qualifier("schemaParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Parameters(value="schemaParameters")
public class SchemaParameters {


    //schema 参数 Start
    /** Transaction Schema **/
    /** @illustrate 参数类 **/
    private static final String Parameters_Module_Kernel_Serializables_Transaction_Schema = "{\"namespace\": \"com.stargazercloud\",\n" +
            "    \"type\": \"record\",\n" +
            "    \"name\":\"Transaction\",\n" +
            "    \"fields\": [\n" +
            "             {\"name\": \"transactionResultCache\", \"type\": [{\"type\":\"map\", \"values\": \"string\"}]},\n" +
            "             {\"name\": \"transactionParametersCache\", \"type\": [{\"type\":\"map\", \"values\": \"string\"}]},\n" +
            "             {\"name\": \"events\", \"type\": [array], \"items\":{\"name\": \"event\", \"type\": \"record\", \"fields\":[\n" +
            "                                                                           {\"name\": \"eventResultCache\", \"type\": [{\"type\":\"map\", \"values\": \"string\"}]},\n" +
            "                                                                           {\"name\": \"eventParametersCache\", \"type\": [{\"type\":\"map\", \"values\": \"string\"}]}\n" +
            "                                                                                                       ]\n" +
            "                                                         }\n" +
            "             }\n" +
            "               ]\n" +
            "}";

    //schema 参数 End

}
