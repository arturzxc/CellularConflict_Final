package core;

import java.util.*;

/**
 * This is an attributes list which was supposed to hold all of the attributes
 * of an agent. The advantage of such approach is that attributes can be added
 * and removed at a run time as opposed to declaring fields inside a class.
 *
 * @author Artur Krzynowek
 */
public class AttributeList implements Map<String, Object> {

    private Map<String, Object> attrubutes = new HashMap();

    /**
     * Returns attribute as a value converted to float. Throws exception if
     * conversion was impossible.
     */
    public float getAttribAsFloat(String key) throws NumberFormatException {
        Object obj = attrubutes.get(key);
        if (obj == null) {
            throw new Error("No such attribute! " + key);
        }
        float val;
        val = Float.parseFloat(obj.toString());
        return val;
    }

    /*
     * Gets atribute as String.
     */
    public String getAttribAsString(String key) {
        Object obj = attrubutes.get(key);
        if (obj == null) {
            throw new Error("No such attribute! " + key);
        }
        return obj.toString();
    }

    /**
     * Resets an attribute to be either "" or 0.0f
     */
    public void resetValue(String key) {
        Object obj = attrubutes.get(key);
        if (obj instanceof String) {
            replace(key, "");
        } else if (obj instanceof Float) {
            replace(key, 0.0f);
        } else {
            replace(key, null);
        }
    }

    /*
     * Replaces existing attribute. If it doesnt exist then it throws error.
     */
    public Object replace(String key, Object val) {
        if (attrubutes.get(key) == null) {
            throw new Error("Attribute does not exist and cannot be replaced!");
        }
        return attrubutes.put(key, val);
    }

    /**
     * Increments numerical attribute by 1
     */
    public Object increment(String key) {
        return replace(key, getAttribAsFloat(key) + 1);
    }

    /**
     * Decrements numerical attribute by 1
     */
    public Object decrement(String key) {
        return replace(key, getAttribAsFloat(key) - 1);
    }

    @Override
    public int size() {
        return attrubutes.size();
    }

    @Override
    public boolean isEmpty() {
        return attrubutes.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return attrubutes.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return attrubutes.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return attrubutes.get(key);
    }

    /**
     * There can be only 1 attribute with the same key and value
     */
    @Override
    public Object put(String key, Object value) {
        if (!containsKey(key)) {
            return attrubutes.put(key, value);
        }
        return null;
    }

    @Override
    public Object remove(Object key) {
        return attrubutes.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        attrubutes.putAll(m);
    }

    @Override
    public void clear() {
        attrubutes.clear();
    }

    @Override
    public Set<String> keySet() {
        return attrubutes.keySet();
    }

    @Override
    public Collection<Object> values() {
        return attrubutes.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return attrubutes.entrySet();
    }

    @Override
    public String toString() {
        String str = "";

        Iterator iterator = attrubutes.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entry = (Entry) iterator.next();
            str += entry.getKey() + " " + entry.getValue() + "\n";
        }

        return str;
    }

    /**
     * Test main method
     */
    public static void main(String[] args) {
        AttributeList attributeList = new AttributeList();
        attributeList.put("health", 100);
        attributeList.put("attackCounter", 100);
        System.out.println(attributeList);
        float health = attributeList.getAttribAsFloat("health");
        float onExistantAttribute = attributeList.getAttribAsFloat("nothing");
        System.out.println("health " + health);
    }
}
