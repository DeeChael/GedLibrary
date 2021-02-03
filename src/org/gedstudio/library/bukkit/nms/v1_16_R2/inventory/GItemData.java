package org.gedstudio.library.bukkit.nms.v1_16_R2.inventory;

import net.minecraft.server.v1_16_R2.*;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.gedstudio.library.bukkit.exception.ValueNotEqualsError;
import org.gedstudio.library.bukkit.inventory.GItem;

import java.io.Serializable;
import java.util.HashMap;

public class GItemData implements Serializable, org.gedstudio.library.bukkit.inventory.GItemData {

    private transient NBTTagCompound nbtTagCompound;

    private HashMap<String, Object> maps;

    public GItemData() {
        this.nbtTagCompound = new NBTTagCompound();
        this.maps = new HashMap<>();
        nbtTagCompound.setString("createdBy", "EzTools");
        this.maps.put("createBy", "EzTools");
    }

    GItemData(NBTTagCompound nbtTagCompound) {
        this.nbtTagCompound = nbtTagCompound;
        this.maps = new HashMap<>();
        nbtTagCompound.setString("createdBy", "EzTools");
        if (nbtTagCompound.getKeys().size() != 0) {
            for (String key : nbtTagCompound.getKeys()) {
                if (nbtTagCompound.get(key) instanceof NBTTagString) {
                    this.maps.put(key, ((NBTTagString) nbtTagCompound.get(key)).asString());
                } else if (nbtTagCompound.get(key) instanceof NBTTagInt) {
                    this.maps.put(key, ((NBTTagInt) nbtTagCompound.get(key)).asInt());
                } else if (nbtTagCompound.get(key) instanceof NBTTagDouble) {
                    this.maps.put(key, ((NBTTagDouble) nbtTagCompound.get(key)).asDouble());
                } else if (nbtTagCompound.get(key) instanceof NBTTagFloat) {
                    this.maps.put(key, ((NBTTagFloat) nbtTagCompound.get(key)).asFloat());
                } else if (nbtTagCompound.get(key) instanceof NBTTagShort) {
                    this.maps.put(key, ((NBTTagShort) nbtTagCompound.get(key)).asShort());
                } else if (nbtTagCompound.get(key) instanceof NBTTagLong) {
                    this.maps.put(key, ((NBTTagLong) nbtTagCompound.get(key)).asLong());
                } else if (nbtTagCompound.get(key) instanceof NBTTagByte){
                    this.maps.put(key, ((NBTTagByte) nbtTagCompound.get(key)).asByte());
                } else if (nbtTagCompound.get(key) instanceof NBTTagIntArray){
                    this.maps.put(key, ((NBTTagIntArray) nbtTagCompound.get(key)).getInts());
                } else if (nbtTagCompound.get(key) instanceof NBTTagByteArray){
                    this.maps.put(key, ((NBTTagByteArray) nbtTagCompound.get(key)).getBytes());
                } else if (nbtTagCompound.get(key) instanceof NBTTagLongArray) {
                    this.maps.put(key, ((NBTTagLongArray) nbtTagCompound.get(key)).getLongs());
                }
            }
        }
    }

    public GItemData(GItem item) {
        ItemStack itemStack = CraftItemStack.asNMSCopy(item.getHandle());
        this.nbtTagCompound = itemStack.hasTag() ? itemStack.getTag() : new NBTTagCompound();
        this.maps = new HashMap<>();
        nbtTagCompound.setString("createdBy", "EzTools");
        if (itemStack.hasTag()) {
            if (nbtTagCompound.getKeys().size() != 0) {
                for (String key : nbtTagCompound.getKeys()) {
                    if (nbtTagCompound.get(key) instanceof NBTTagString) {
                        this.maps.put(key, ((NBTTagString) nbtTagCompound.get(key)).asString());
                    } else if (nbtTagCompound.get(key) instanceof NBTTagInt) {
                        this.maps.put(key, ((NBTTagInt) nbtTagCompound.get(key)).asInt());
                    } else if (nbtTagCompound.get(key) instanceof NBTTagDouble) {
                        this.maps.put(key, ((NBTTagDouble) nbtTagCompound.get(key)).asDouble());
                    } else if (nbtTagCompound.get(key) instanceof NBTTagFloat) {
                        this.maps.put(key, ((NBTTagFloat) nbtTagCompound.get(key)).asFloat());
                    } else if (nbtTagCompound.get(key) instanceof NBTTagShort) {
                        this.maps.put(key, ((NBTTagShort) nbtTagCompound.get(key)).asShort());
                    } else if (nbtTagCompound.get(key) instanceof NBTTagLong) {
                        this.maps.put(key, ((NBTTagLong) nbtTagCompound.get(key)).asLong());
                    } else if (nbtTagCompound.get(key) instanceof NBTTagByte){
                        this.maps.put(key, ((NBTTagByte) nbtTagCompound.get(key)).asByte());
                    } else if (nbtTagCompound.get(key) instanceof NBTTagIntArray){
                        this.maps.put(key, ((NBTTagIntArray) nbtTagCompound.get(key)).getInts());
                    } else if (nbtTagCompound.get(key) instanceof NBTTagByteArray){
                        this.maps.put(key, ((NBTTagByteArray) nbtTagCompound.get(key)).getBytes());
                    } else if (nbtTagCompound.get(key) instanceof NBTTagLongArray) {
                        this.maps.put(key, ((NBTTagLongArray) nbtTagCompound.get(key)).getLongs());
                    }
                }
            }
        }
    }

    @Override
    public void setString(String key, String value) {
        if (this.maps.containsKey(key)) {
            if (this.maps.get(key) instanceof String) {
                this.maps.put(key, value);
            } else {
                throw new ValueNotEqualsError();
            }
        } else {
            this.maps.put(key, value);
        }
    }

    @Override
    public String getString(String key) {
        if (this.maps.containsKey(key)) {
            Object object = this.maps.get(key);
            if (object instanceof String) {
                return (String) object;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean hasString(String key) {
        return this.hasObject(key) && (this.maps.get(key) instanceof String);
    }

    @Override
    public void setInteger(String key, int value) {
        if (this.maps.containsKey(key)) {
            if (this.maps.get(key) instanceof Integer) {
                this.maps.put(key, value);
            } else {
                throw new ValueNotEqualsError();
            }
        } else {
            this.maps.put(key, value);
        }
    }

    @Override
    public int getInteger(String key) {
        if (this.maps.containsKey(key)) {
            Object object = this.maps.get(key);
            if (object instanceof Integer) {
                return (int) object;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public boolean hasInteger(String key) {
        return this.hasObject(key) && (this.maps.get(key) instanceof Integer);
    }

    @Override
    public void setDouble(String key, double value) {
        if (this.maps.containsKey(key)) {
            if (this.maps.get(key) instanceof Double) {
                this.maps.put(key, value);
            } else {
                throw new ValueNotEqualsError();
            }
        } else {
            this.maps.put(key, value);
        }
    }

    @Override
    public double getDouble(String key) {
        if (this.maps.containsKey(key)) {
            Object object = this.maps.get(key);
            if (object instanceof Double) {
                return (double) object;
            } else {
                return 0.0;
            }
        } else {
            return 0.0;
        }
    }

    @Override
    public boolean hasDouble(String key) {
        return this.hasObject(key) && (this.maps.get(key) instanceof Double);
    }

    @Override
    public void setFloat(String key, float value) {
        if (this.maps.containsKey(key)) {
            if (this.maps.get(key) instanceof Float) {
                this.maps.put(key, value);
            } else {
                throw new ValueNotEqualsError();
            }
        } else {
            this.maps.put(key, value);
        }
    }

    @Override
    public float getFloat(String key) {
        if (this.maps.containsKey(key)) {
            Object object = this.maps.get(key);
            if (object instanceof Float) {
                return (float) object;
            } else {
                return 0.0f;
            }
        } else {
            return 0.0f;
        }
    }

    @Override
    public boolean hasFloat(String key) {
        return this.hasObject(key) && (this.maps.get(key) instanceof Float);
    }

    @Override
    public void setLong(String key, long value) {
        if (this.maps.containsKey(key)) {
            if (this.maps.get(key) instanceof Long) {
                this.maps.put(key, value);
            } else {
                throw new ValueNotEqualsError();
            }
        } else {
            this.maps.put(key, value);
        }
    }

    @Override
    public long getLong(String key) {
        if (this.maps.containsKey(key)) {
            Object object = this.maps.get(key);
            if (object instanceof Long) {
                return (long) object;
            } else {
                return 0l;
            }
        } else {
            return 0l;
        }
    }

    @Override
    public boolean hasLong(String key) {
        return this.hasObject(key) && (this.maps.get(key) instanceof Long);
    }

    @Override
    public void setShort(String key, short value) {
        if (this.maps.containsKey(key)) {
            if (this.maps.get(key) instanceof Short) {
                this.maps.put(key, value);
            } else {
                throw new ValueNotEqualsError();
            }
        } else {
            this.maps.put(key, value);
        }
    }

    @Override
    public short getShort(String key) {
        if (this.maps.containsKey(key)) {
            Object object = this.maps.get(key);
            if (object instanceof Short) {
                return (short) object;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public boolean hasShort(String key) {
        return this.hasObject(key) && (this.maps.get(key) instanceof Short);
    }

    @Override
    public void setByte(String key, byte value) {
        if (this.maps.containsKey(key)) {
            if (this.maps.get(key) instanceof Byte) {
                this.maps.put(key, value);
            } else {
                throw new ValueNotEqualsError();
            }
        } else {
            this.maps.put(key, value);
        }
    }

    @Override
    public byte getByte(String key) {
        if (this.maps.containsKey(key)) {
            Object object = this.maps.get(key);
            if (object instanceof Byte) {
                return (byte) object;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public boolean hasByte(String key) {
        return this.hasObject(key) && (this.maps.get(key) instanceof Byte);
    }

    @Override
    public void setByteArray(String key, byte[] value) {
        if (this.maps.containsKey(key)) {
            if (this.maps.get(key) instanceof byte[]) {
                this.maps.put(key, value);
            } else {
                throw new ValueNotEqualsError();
            }
        } else {
            this.maps.put(key, value);
        }
    }

    @Override
    public byte[] getByteArray(String key) {
        if (this.maps.containsKey(key)) {
            Object object = this.maps.get(key);
            if (object instanceof byte[]) {
                return (byte[]) object;
            } else {
                return new byte[0];
            }
        } else {
            return new byte[0];
        }
    }

    @Override
    public boolean hasByteArray(String key) {
        return this.hasObject(key) && (this.maps.get(key) instanceof byte[]);
    }

    @Override
    public void setIntegerArray(String key, int[] value) {
        if (this.maps.containsKey(key)) {
            if (this.maps.get(key) instanceof int[]) {
                this.maps.put(key, value);
            } else {
                throw new ValueNotEqualsError();
            }
        } else {
            this.maps.put(key, value);
        }
    }

    @Override
    public int[] getIntegerArray(String key) {
        if (this.maps.containsKey(key)) {
            Object object = this.maps.get(key);
            if (object instanceof int[]) {
                return (int[]) object;
            } else {
                return new int[0];
            }
        } else {
            return new int[0];
        }
    }

    @Override
    public boolean hasIntegerArray(String key) {
        return this.hasObject(key) && (this.maps.get(key) instanceof int[]);
    }

    @Override
    public void setLongArray(String key, long[] value) {
        if (this.maps.containsKey(key)) {
            if (this.maps.get(key) instanceof long[]) {
                this.maps.put(key, value);
            } else {
                throw new ValueNotEqualsError();
            }
        } else {
            this.maps.put(key, value);
        }
    }

    @Override
    public long[] getLongArray(String key) {
        if (this.maps.containsKey(key)) {
            Object object = this.maps.get(key);
            if (object instanceof long[]) {
                return (long[]) object;
            } else {
                return new long[0];
            }
        } else {
            return new long[0];
        }
    }

    @Override
    public boolean hasLongArray(String key) {
        return this.hasObject(key) && (this.maps.get(key) instanceof long[]);
    }

    @Override
    public boolean hasObject(String key) {
        return this.maps.containsKey(key);
    }

    @Override
    public void remove(String key) {
        if (this.maps.containsKey(key)) {
            this.maps.remove(key);
        }
    }

    @Override
    public void setItemData(GItem item) {
        ItemStack itemStack = CraftItemStack.asNMSCopy(item.getHandle());
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        for (String key : this.maps.keySet()) {
            if (this.maps.get(key) instanceof String) {
                nbtTagCompound.setString(key, (String) this.maps.get(key));
            } else if (this.maps.get(key) instanceof Integer) {
                nbtTagCompound.setInt(key, (Integer) this.maps.get(key));
            } else if (this.maps.get(key) instanceof Double) {
                nbtTagCompound.setDouble(key, (Double) this.maps.get(key));
            } else if (this.maps.get(key) instanceof Long) {
                nbtTagCompound.setLong(key, (Long) this.maps.get(key));
            } else if (this.maps.get(key) instanceof Short) {
                nbtTagCompound.setShort(key, (Short) this.maps.get(key));
            } else if (this.maps.get(key) instanceof Byte) {
                nbtTagCompound.setByte(key, (Byte) this.maps.get(key));
            } else if (this.maps.get(key) instanceof byte[]) {
                nbtTagCompound.setByteArray(key, (byte[]) this.maps.get(key));
            } else if (this.maps.get(key) instanceof int[]) {
                nbtTagCompound.setIntArray(key, (int[]) this.maps.get(key));
            } else if (this.maps.get(key) instanceof long[]) {
                nbtTagCompound.set(key, new NBTTagLongArray((long[]) this.maps.get(key)));
            } else if (this.maps.get(key) instanceof Float) {
                nbtTagCompound.setFloat(key, (Float) this.maps.get(key));
            }
        }
        this.nbtTagCompound = nbtTagCompound;
        itemStack.setTag(nbtTagCompound);
    }

    @Override
    public GItemData clone() {
        return new GItemData(this.nbtTagCompound.clone());
    }

}
