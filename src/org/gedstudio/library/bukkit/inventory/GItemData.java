package org.gedstudio.library.bukkit.inventory;

public interface GItemData extends Cloneable{

    /**
     * To set the key String value to value
     * @param key key
     * @param value value
     */
    void setString(String key, String value);

    /**
     * To set the key Integer value to value
     * @param key key
     * @param value value
     */
    void setInteger(String key, int value);

    /**
     * To set the key Double value to value
     * @param key key
     * @param value value
     */
    void setDouble(String key, double value);

    /**
     * To set the key Float value to value
     * @param key key
     * @param value value
     */
    void setFloat(String key, float value);

    /**
     * To set the key Long value to value
     * @param key key
     * @param value value
     */
    void setLong(String key, long value);

    /**
     * To set the key Short value to value
     * @param key key
     * @param value value
     */
    void setShort(String key, short value);

    /**
     * To set the key Byte value to value
     * @param key key
     * @param value value
     */
    void setByte(String key, byte value);

    /**
     * To set the key Byte Array value to value
     * @param key key
     * @param value value
     */
    void setByteArray(String key, byte[] value);

    /**
     * To set the key Integer Array value to value
     * @param key key
     * @param value value
     */
    void setIntegerArray(String key, int[] value);

    /**
     * To set the key Long Array value to value
     * @param key key
     * @param value value
     */
    void setLongArray(String key, long[] value);

    /**
     * To get the String value of key
     * @param key key
     * @return value
     */
    String getString(String key);

    /**
     * To get the Integer value of key
     * @param key key
     * @return value
     */
    int getInteger(String key);

    /**
     * To get the Double value of key
     * @param key key
     * @return value
     */
    double getDouble(String key);

    /**
     * To get the Float value of key
     * @param key key
     * @return value
     */
    float getFloat(String key);

    /**
     * To get the Long value of key
     * @param key key
     * @return value
     */
    long getLong(String key);

    /**
     * To get the Short value of key
     * @param key key
     * @return value
     */
    short getShort(String key);

    /**
     * To get the Byte value of key
     * @param key key
     * @return value
     */
    byte getByte(String key);

    /**
     * To get the Byte Array value of key
     * @param key key
     * @return value
     */
    byte[] getByteArray(String key);

    /**
     * To get the Integer Array value of key
     * @param key key
     * @return value
     */
    int[] getIntegerArray(String key);

    /**
     * To get the Long Array value of key
     * @param key key
     * @return value
     */
    long[] getLongArray(String key);

    /**
     * To check if item data has a String value of key
     * @param key key
     * @return boolean
     */
    boolean hasString(String key);

    /**
     * To check if item data has a Integer value of key
     * @param key key
     * @return boolean
     */
    boolean hasInteger(String key);

    /**
     * To check if item data has a Double value of key
     * @param key key
     * @return boolean
     */
    boolean hasDouble(String key);

    /**
     * To check if item data has a Float value of key
     * @param key key
     * @return boolean
     */
    boolean hasFloat(String key);

    /**
     * To check if item data has a Long value of key
     * @param key key
     * @return boolean
     */
    boolean hasLong(String key);

    /**
     * To check if item data has a Short value of key
     * @param key key
     * @return boolean
     */
    boolean hasShort(String key);

    /**
     * To check if item data has a Byte value of key
     * @param key key
     * @return boolean
     */
    boolean hasByte(String key);

    /**
     * To check if item data has a Byte Array value of key
     * @param key key
     * @return boolean
     */
    boolean hasByteArray(String key);

    /**
     * To check if item data has a Integer Array value of key
     * @param key key
     * @return boolean
     */
    boolean hasIntegerArray(String key);

    /**
     * To check if item data has a Long Array value of key
     * @param key key
     * @return boolean
     */
    boolean hasLongArray(String key);

    /**
     * To check if item data has a value of key
     * @param key key
     * @return boolean
     */
    boolean hasObject(String key);

    /**
     * To remove the value of key
     * @param key key
     */
    void remove(String key);

    /**
     * To set the item data of the item to this data
     * @param item item
     */
    void setItemData(GItem item);

    /**
     * To clone a item data
     * @return item data
     */
    GItemData clone();

}
