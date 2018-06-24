package br.ufes.inf.lprm.context.model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Entity {
    protected String eid;
    private List<IntrinsicContext> contexts;
    public Entity(String eid) {
        this.eid = eid;
        contexts = new LinkedList<>();
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
            field.set(this, field.getType().getConstructor(String.class, Entity.class).newInstance(this.eid + "-" + field.getName(), this));
            contexts.add((IntrinsicContext) field.get(this));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(accessibility);
        }
    }
    public String getEid() {
        return eid;
    }
    public void setEid(String eid) {
        this.eid = eid;
    }
    public List<IntrinsicContext> getContexts() {
        return contexts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;

        Entity entity = (Entity) o;

        return getEid().equals(entity.getEid());
    }

    @Override
    public int hashCode() {
        return getEid().hashCode();
    }
}
