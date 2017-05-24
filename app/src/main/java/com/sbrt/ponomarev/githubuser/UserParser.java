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

    public static User parseUserInfo(String jsonString) {

        try {
            User user = null;
            if (jsonString != null) {
                user = mObjectMapper.readValue(jsonString, User.class);
            }
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static class UserDeserializer extends StdDeserializer<User> {

        public UserDeserializer() {
            this(null);
        }

        public UserDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public User deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);

            String name = node.get("name").asText();
            String email = node.get("email").asText();
            String location = node.get("location").asText();
            Integer id = (Integer) node.get("id").getNumberValue();

            return new User(name, email, id, location);
        }
    }
}
