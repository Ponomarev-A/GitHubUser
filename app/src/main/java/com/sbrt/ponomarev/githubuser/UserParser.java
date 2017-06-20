package com.sbrt.ponomarev.githubuser;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import org.codehaus.jackson.map.module.SimpleModule;

import java.io.IOException;

/**
 * Created by Ponomarev on 24.05.2017.
 */

class UserParser {

    private static final String MODULE_NAME = "module name";

    private static ObjectMapper mObjectMapper;

    static {
        mObjectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule(MODULE_NAME, Version.unknownVersion());
        module.addDeserializer(User.class, new UserDeserializer());
        mObjectMapper.registerModule(module);
    }

    static User parseUserInfo(String jsonString) {

        User user = null;
        try {
            if (jsonString != null) {
                user = mObjectMapper.readValue(jsonString, User.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return user;
        }
    }

    private static class UserDeserializer extends StdDeserializer<User> {

        UserDeserializer() {
            this(null);
        }

        UserDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public User deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);

            String name = node.get("name").getTextValue();
            String email = node.get("email").getTextValue();
            String location = node.get("location").getTextValue();
            long id = node.get("id").getLongValue();

            return new User(id, name, email, location);
        }
    }
}
