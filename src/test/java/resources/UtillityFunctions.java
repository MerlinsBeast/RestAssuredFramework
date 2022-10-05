package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class UtillityFunctions {
    String CollectedResponse;
    static RequestSpecification reqspec;
    PrintStream logger;
    static Properties prop;
    public static String getGlobalPropertyValues(String PropertyToFetch) throws IOException {
        prop= new Properties();
        FileInputStream fileLoc= new FileInputStream("src/test/java/resources/GlobalProperties.properties");
        prop.load(fileLoc);
        return prop.getProperty(PropertyToFetch);
    }

    public RequestSpecification RequestSpecifications() throws IOException {
        if(reqspec==null){
        logger= new PrintStream(new FileOutputStream("logFile.text"));
                    reqspec = new RequestSpecBuilder().setBaseUri(getGlobalPropertyValues("baseURI")).addQueryParam("key", "qaclick123").
                    setContentType(ContentType.JSON).
                    addFilter(RequestLoggingFilter.logRequestTo(logger))
                    .addFilter(ResponseLoggingFilter.logResponseTo(logger)).build();
            return reqspec;
        }
        return reqspec;

    }
    public String getJsonPath(Response response, String key){
        CollectedResponse=response.asString();
        System.out.println("String formated response"+ CollectedResponse);
        JsonPath js = new JsonPath(CollectedResponse);
    return js.get(key).toString();
    }
}

