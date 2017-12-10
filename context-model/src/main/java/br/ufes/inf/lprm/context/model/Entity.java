package br.ufes.inf.lprm.context.model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Entity {
    protected String UID;
    private List<IntrinsicContext> intrinsicContexts;
    public Entity(String UID) {
        this.UID = UID;
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
            field.set(this, field.getType().getConstructor(String.class, Entity.class).newInstance(this.UID + "-" + field.getName(), this));
            intrinsicContexts.add((IntrinsicContext) field.get(this));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(accessibility);
        }
    }
    public String getUID() {
        return UID;
    }
    public void setUID(String UID) {
        this.UID = UID;
    }
    public List<IntrinsicContext> getIntrinsicContexts() {
        return intrinsicContexts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;

        Entity entity = (Entity) o;

        return getUID().equals(entity.getUID());
    }

    @Override
    public int hashCode() {
        return getUID().hashCode();
    }
}
