package app.data;

import java.util.Objects;
import java.util.Set;

public class Literal {
    private Symbol id;
    private boolean assignment = true;

    public Literal(Symbol id) {
        this.id = id;
    }

    public Literal(Symbol id, boolean assignment) {
        this.id = id;
        this.assignment = assignment;
    }

    public boolean isTrue() {
        return assignment;
    }

    public boolean isFalse() {
        return !assignment;
    }

    public Symbol getId() {
        return id;
    }

    public boolean isAssignment() {
        return assignment;
    }

    public void setAssignment(boolean assignment) {
        this.assignment = assignment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Literal literal = (Literal) o;
        return assignment == literal.assignment &&
                Objects.equals(id, literal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assignment);
    }


}
