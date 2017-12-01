package br.ufes.inf.lprm.context;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Entity {
    protected String id;
    private List<IntrinsicContext> intrinsicContexts;
    public Entity(String id) {
        this.id = id;
        intrinsicContexts = new LinkedList<>();
        Arrays.stream(this.getClass().getDeclaredFields())
                .filter(this::isIntrinsicContextField)
                .forEach(this::initializeIntrinsicContextField);
    }
    private boolean isIntrinsicContextField(Field field) {
        return IntrinsicContext.class.isAssignableFrom(field.getType());
    }
    private void initializeIntrinsicContextField(Field field) {
        boolean accessibility = field.isAccessible();
        try {
            field.setAccessible(true);
            field.set(this, field.getType().getConstructor(String.class, Entity.class).newInstance(this.id + "-" + field.getName(), this));
            intrinsicContexts.add((IntrinsicContext) field.get(this));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(accessibility);
        }
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<IntrinsicContext> getIntrinsicContexts() {
        return intrinsicContexts;
    }
}
