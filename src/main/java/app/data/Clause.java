package app.data;

import java.util.*;

public class Clause {
    private Set<Literal> literals = new LinkedHashSet<>();
    private Set<Symbol> negativeSymbols = new LinkedHashSet<>();
    private Set<Symbol> positiveSymbols = new LinkedHashSet<>();
    private Boolean truth = null;

    public Clause(Literal... literals) {
        for (Literal l : literals) {
            this.literals.add(l);
            placeSymbol(l);
        }
    }

    public void addLiteral(Literal literal) {
        placeSymbol(literal);
        literals.add(literal);
    }

    //определяем атомарный символ в положительных или отрицателньых список
    private void placeSymbol(Literal l) {
        if (l.isAssignment()) {
            positiveSymbols.add(l.getId());
        } else {
            negativeSymbols.add(l.getId());
        }
    }

    public Set<Symbol> getAllSymbolsForClause() {
        Set<Symbol> symbols = new HashSet<>();

        for (Literal l: literals) {
            symbols.add(l.getId());
        }

        return symbols;
    }

    public Set<Symbol> getNegativeSymbols() {
        return negativeSymbols;
    }

    public Set<Symbol> getPositiveSymbols() {
        return positiveSymbols;
    }

    public Set<Literal> getLiterals() {
        return literals;
    }

    public boolean isEmpty() {
        return literals.isEmpty();
    }

    public boolean isUnitCause() {
        return literals.size() == 1;
    }

    public boolean isTruth() {
        if (truth == null) {
            for (Literal l : literals) {
                if (l.isTrue() && positiveSymbols.contains(l.getId())) {
                    truth = true;
                } else if (l.isFalse() && negativeSymbols.contains(l.getId())) {
                    truth = true;
                }
            }

            if (truth == null) {
                truth = false;
            }
        }

        return truth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clause clause = (Clause) o;
        return Objects.equals(literals, clause.literals) &&
                Objects.equals(negativeSymbols, clause.negativeSymbols) &&
                Objects.equals(positiveSymbols, clause.positiveSymbols) &&
                Objects.equals(truth, clause.truth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(literals, negativeSymbols, positiveSymbols, truth);
    }
}
