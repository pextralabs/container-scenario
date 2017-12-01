package br.ufes.inf.lprm.context;

public abstract class IntrinsicContext<T> extends Context<T> {
    protected Entity bearer;

    public IntrinsicContext(String id, Entity bearer) {
        super(id);
        this.bearer = bearer;
    }

    public IntrinsicContext(String id, Entity bearer, T value) {
        super(id, value);
        this.bearer = bearer;
    }

    public Entity getBearer() {
        return bearer;
    }
    public void setBearer(Entity bearer) {
        this.bearer = bearer;
    }
}
