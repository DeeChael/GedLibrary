package org.gedstudio.library.bukkit.configuration;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class JsonConfiguration {

    private JsonObject jsonObject;

    /**
     * To load json from a website url
     * @param url the url of the website
     */
    public JsonConfiguration(String url) {
        try {
            URL url1 = new URL(url);
            InputStream inputStream = url1.openStream();
            char[] cbuf = new char[10000];
            InputStreamReader input = new InputStreamReader(inputStream);
            int len = 0;
            try {
                len = input.read(cbuf);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String text = new String(cbuf, 0, len);
            this.jsonObject = new Gson().fromJson(text, JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To load json from a file
     * @param file the json file
     */
    public JsonConfiguration(File file) {
        char[] cbuf = new char[10000];
        InputStreamReader input = null;
        try {
            input = new InputStreamReader(new FileInputStream(file), "UTF-8");
        } catch (UnsupportedEncodingException| FileNotFoundException e) {
            e.printStackTrace();
        }
        int len = 0;
        try {
            len = input.read(cbuf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = new String(cbuf, 0, len);
        this.jsonObject = new Gson().fromJson(text, JsonObject.class);
    }

    /**
     * To load json from inputStream
     * @param inputStream the json inputStream
     */
    public JsonConfiguration(InputStream inputStream) {
        char[] cbuf = new char[10000];
        InputStreamReader input = new InputStreamReader(inputStream);
        int len = 0;
        try {
            len = input.read(cbuf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = new String(cbuf, 0, len);
        this.jsonObject = new Gson().fromJson(text, JsonObject.class);
    }

    /**
     * private method
     * @param jsonObject jsonObject
     */
    JsonConfiguration(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * To get jsonObject
     * @param key the key in json
     * @return jsonObject
     */
    public JsonConfiguration getJsonObject(String key) {
        return new JsonConfiguration(this.jsonObject.getAsJsonObject(key));
    }

    /**
     * To set the value of key in json
     * @param key key
     * @param value value
     */
    public void set(String key, Object value) {
        if (value instanceof String) {
            this.jsonObject.addProperty(key, (String) value);
        } else if (value instanceof Number) {
            this.jsonObject.addProperty(key, (Number) value);
        } else if (value instanceof Boolean) {
            this.jsonObject.addProperty(key, (Boolean) value);
        } else if (value instanceof Character) {
            this.jsonObject.addProperty(key, (Character) value);
        } else if (value instanceof JsonObject) {
            this.jsonObject.add(key, (JsonObject) value);
        } else if (value instanceof JsonArray) {
            this.jsonObject.add(key, (JsonArray) value);
        } else {
            this.jsonObject.addProperty(key, value.toString());
        }
    }

    /**
     * To get the value of key
     * @param key key
     * @return the value of key
     */
    public Object get(String key) {
        return this.jsonObject.get(key);
    }

    /**
     * To get the String value of key
     * @param key key
     * @return the String value of key
     */
    public String getString(String key) {
        return this.jsonObject.get(key).getAsString();
    }

    /**
     * To get the Boolean value of key
     * @param key key
     * @return the Boolean value of key
     */
    public Boolean getBoolean(String key) {
        return this.jsonObject.get(key).getAsBoolean();
    }

    /**
     * To get the Integer value of key
     * @param key key
     * @return the Integer value of key
     */
    public Integer getInteger(String key) {
        return this.jsonObject.get(key).getAsInt();
    }

    /**
     * To get the Double value of key
     * @param key key
     * @return the Double value of key
     */
    public Double getDouble(String key) {
        return this.jsonObject.get(key).getAsDouble();
    }

    /**
     * To get the Float value of key
     * @param key key
     * @return the Float value of key
     */
    public Float getFloat(String key) {
        return this.jsonObject.get(key).getAsFloat();
    }

    /**
     * To get the Long value of key
     * @param key key
     * @return the Long value of key
     */
    public Long getLong(String key) {
        return this.jsonObject.get(key).getAsLong();
    }

    /**
     * To get the Byte value of key
     * @param key key
     * @return the Byte value of key
     */
    public Byte getByte(String key) {
        return this.jsonObject.get(key).getAsByte();
    }

    /**
     * To get the Short value of key
     * @param key key
     * @return the Short value of key
     */
    public Short getShort(String key) {
        return this.jsonObject.get(key).getAsShort();
    }

    /**
     * To get the Character value of key
     * @param key key
     * @return the Character value of key
     */
    public Character getCharacter(String key) {
        return this.jsonObject.get(key).getAsCharacter();
    }

    /**
     * To get the jsonArray of key
     * @param key key
     * @param witch the index of jsonArray
     * @return the jsonArray witch is index
     */
    public JsonConfiguration getJsonArray(String key, int witch) {
        return this.getJsonArray(key).get(0);
    }

    /**
     * To get the jsonArray of key
     * @param key key
     * @return jsonArray
     */
    public List<JsonConfiguration> getJsonArray(String key) {
        List<JsonConfiguration> jsonArrays = new ArrayList<>();
        for (JsonElement jsonElement : this.jsonObject.get(key).getAsJsonArray()) {
            jsonArrays.add(new JsonConfiguration(jsonElement.getAsJsonObject()));
        }
        return jsonArrays;
    }

    /**
     * To save as file
     * @param file the file to save
     */
    public void save(File file) {
        String json = new Gson().toJson(jsonObject);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To load json start as jsonArray
     * @param url the url of the website
     * @return jsonObjects
     */
    public static List<JsonConfiguration> fromJsonArray(String url) {
        URL url1 = null;
        try {
            url1 = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream inputStream = null;
        try {
            inputStream = url1.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fromJsonArray(inputStream);
    }

    /**
     * To load json start as jsonArray
     * @param file the json files
     * @return jsonObjects
     */
    public static List<JsonConfiguration> fromJsonArray(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fromJsonArray(fileInputStream);
    }

    /**
     * To load json start as jsonArray
     * @param inputStream the json input stream
     * @return jsonObjects
     */
    public static List<JsonConfiguration> fromJsonArray(InputStream inputStream) {
        char[] cbuf = new char[10000];
        InputStreamReader input = new InputStreamReader(inputStream);
        int len = 0;
        try {
            len = input.read(cbuf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = new String(cbuf, 0, len);
        JsonArray jsonArray = new Gson().fromJson(text, JsonArray.class);
        List<JsonConfiguration> list = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            list.add(new JsonConfiguration(jsonElement.getAsJsonObject()));
        }
        return list;
    }

}
