package co.pextra.scenarios.SensitiveProductStorage2;

import java.util.*;

public class Responsibility {
    private Set<Person> responsible;
    private Set<Batch> batches;

    public Responsibility() {
        responsible = new HashSet<>();
        batches = new HashSet<>();
    }

    public Responsibility(Set<Person> responsible, Set<Batch> batches) {
        this.responsible = responsible;
        this.batches = batches;
    }

    public Responsibility(Person[] responsible, Batch[] batches) {
        this.responsible = new HashSet<>(Arrays.asList(responsible));
        this.batches = new HashSet<>(Arrays.asList(batches));
    }
    public Responsibility(List<Person> responsible, List<Batch> batches) {
        this.responsible = new HashSet<>(responsible);
        this.batches = new HashSet<>(batches);
    }

    public void addResponsible(Person... responsible) {
        this.responsible.addAll(Arrays.asList(responsible));
    }
    public void addRBatches(Batch... batches) {
        this.batches.addAll(Arrays.asList(batches));
    }

    public Set<Person> getResponsible() {
        return responsible;
    }

    public void setResponsible(Set<Person> responsible) {
        this.responsible = responsible;
    }

    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }
}
