package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {
    public static RequestSpecification res;

    public RequestSpecification requestSpecification() throws IOException {
        if (res == null){
            PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
        res = new RequestSpecBuilder().setBaseUri(globalValue("BaseUri"))
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
        return res;
    }
        return res;
    }

    public static String globalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/configuration.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }
    public String getJsonpath(Response response, String key){
        String resp= response.asString();
        JsonPath js=new JsonPath(resp);
        return js.get(key).toString();
    }
}
