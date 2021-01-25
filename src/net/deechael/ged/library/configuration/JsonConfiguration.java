package net.deechael.ged.library.configuration;

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

    JsonConfiguration(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonConfiguration getJsonObject(String key) {
        return new JsonConfiguration(this.jsonObject.getAsJsonObject(key));
    }

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

    public Object get(String key) {
        return this.jsonObject.get(key);
    }

    public String getString(String key) {
        return this.jsonObject.get(key).getAsString();
    }

    public Boolean getBoolean(String key) {
        return this.jsonObject.get(key).getAsBoolean();
    }

    public Integer getInteger(String key) {
        return this.jsonObject.get(key).getAsInt();
    }

    public Double getDouble(String key) {
        return this.jsonObject.get(key).getAsDouble();
    }

    public Float getFloat(String key) {
        return this.jsonObject.get(key).getAsFloat();
    }

    public Long getLong(String key) {
        return this.jsonObject.get(key).getAsLong();
    }

    public Byte getByte(String key) {
        return this.jsonObject.get(key).getAsByte();
    }

    public Short getShort(String key) {
        return this.jsonObject.get(key).getAsShort();
    }

    public Character getCharacter(String key) {
        return this.jsonObject.get(key).getAsCharacter();
    }

    public JsonConfiguration getJsonArray(String key, int witch) {
        return this.getJsonArray(key).get(0);
    }

    public List<JsonConfiguration> getJsonArray(String key) {
        List<JsonConfiguration> jsonArrays = new ArrayList<>();
        for (JsonElement jsonElement : this.jsonObject.get(key).getAsJsonArray()) {
            jsonArrays.add(new JsonConfiguration(jsonElement.getAsJsonObject()));
        }
        return jsonArrays;
    }

    public void save(File file) {
        String json = new Gson().toJson(jsonObject);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static List<JsonConfiguration> fromJsonArray(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fromJsonArray(fileInputStream);
    }

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
