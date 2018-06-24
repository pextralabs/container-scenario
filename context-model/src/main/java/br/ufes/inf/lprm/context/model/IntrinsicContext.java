package br.ufes.inf.lprm.context.model;

public abstract class IntrinsicContext<T> extends  Context<T> {
    protected Entity bearer;

    public IntrinsicContext(String cid, Entity bearer) {
        super(cid);
        this.bearer = bearer;
    }
    public Entity getBearer() {
        return bearer;
    }

    public void setBearer(Entity bearer) {
        this.bearer = bearer;
    }
}
