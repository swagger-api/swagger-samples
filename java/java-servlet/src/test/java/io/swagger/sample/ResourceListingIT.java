package io.swagger.sample;

import io.swagger.models.Info;
import io.swagger.models.Scheme;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.util.Json;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

public class ResourceListingIT {

    @Test
    public void readerTest() throws IOException {
        final Swagger swagger = Json.mapper().readValue(new URL("http://localhost:8002/api/swagger.json"), Swagger.class);

        Assert.assertNotNull(swagger);

        final Info info = swagger.getInfo();
        Assert.assertNotNull(info);
        Assert.assertEquals(info.getDescription(), "This is a sample server");
        Assert.assertEquals(info.getVersion(), "1.0.0");
        Assert.assertEquals(info.getTitle(), "Swagger Sample Servlet");

        Assert.assertEquals(info.getContact().getName(), "Sponge-Bob");
        Assert.assertEquals(info.getContact().getUrl(), "http://swagger.io");
        Assert.assertEquals(info.getContact().getEmail(), "apiteam@swagger.io");

        Assert.assertEquals(info.getLicense().getName(), "Apache 2.0");
        Assert.assertEquals(info.getLicense().getUrl(), "http://www.apache.org/licenses/LICENSE-2.0.html");

        Assert.assertEquals(swagger.getHost(), "localhost:8002");
        Assert.assertEquals(swagger.getSwagger(), "2.0");

        Assert.assertEquals(swagger.getTags(), Collections.singletonList(new Tag().name("users").description("Operations about user")));
        Assert.assertEquals(swagger.getSchemes(), Arrays.asList(Scheme.HTTP, Scheme.HTTPS));
        Assert.assertEquals(swagger.getConsumes(), Arrays.asList("application/json", "application/xml"));
        Assert.assertEquals(swagger.getConsumes(), Arrays.asList("application/json", "application/xml"));

        Assert.assertNotNull(swagger.getPath("/sample/users/getUser"));
        Assert.assertTrue(swagger.getDefinitions().containsKey("ApiResponse"));
        Assert.assertTrue(swagger.getDefinitions().containsKey("SampleData"));
    }
}
