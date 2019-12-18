package app.data;

import java.util.*;

public class Clause {
    private Set<Literal> literals = new LinkedHashSet<>(); //Множетсво литералов,
    private Set<Symbol> negativeSymbols = new LinkedHashSet<>(); //все негатвиные символы, содержащиеся в нашем клозе
    private Set<Symbol> positiveSymbols = new LinkedHashSet<>(); // все подожительные символы, содержащиеся в нашем клозе
    private Boolean truth = null;

    public Clause(Literal... literals) {
        for (Literal l : literals) {
            this.literals.add(l);
            placeSymbol(l);
        }
    }

    public void addSymbol(Symbol symbol, boolean place) {
        if (place) {
            positiveSymbols.add(symbol);
        } else {
            negativeSymbols.add(symbol);
        }
        literals.add(new Literal(symbol));
    }

    public void addLiteral(Literal literal, boolean place) {
        if (place) {
            positiveSymbols.add(literal.getId());
        } else {
            negativeSymbols.add(literal.getId());
        }
        literals.add(literal);
    }

    //определяем атомарный символ в положительных или отрицателных списках
    // (в моем случае атомарным символом явялется класс symbol, так как его
    // основная переменная является final( но это не точно)
    private void placeSymbol(Literal l) {
        if (l.isAssignment()) {
            positiveSymbols.add(l.getId());
        } else {
            negativeSymbols.add(l.getId());
        }
    }

    // получаем все символы, содержащиеся в нашем клозе
    Set<Symbol> getAllSymbolsForClause() {
        Set<Symbol> symbols = new HashSet<>();

        for (Literal l: literals) {
            symbols.add(l.getId());
        }

        return symbols;
    }

    //получаемся все негативные символы в клозе
    public Set<Symbol> getNegativeSymbols() {
        return negativeSymbols;
    }

    //получаем все позитивные символы в клозе
    public Set<Symbol> getPositiveSymbols() {
        return positiveSymbols;
    }

    //получаем все литералы в клозе( ЛИТЕРАЛ - ЭТО АТОМАРНЫЙ ОБЪЕКТ) потому что его основное значения постоянно
    public Set<Literal> getLiterals() {
        return literals;
    }

    //проверяет пустоту нашего клоза
    public boolean isEmpty() {
        return literals.isEmpty();
    }

    //проверяет яявляется ли наш клоз с одной переменной
    public boolean isUnitCause() {
        return literals.size() == 1;
    }

    //выполняеся ли наш клоз при уже заданных значениях
    public boolean isTruth() {
        if (truth == null && literals.size() > 0) {
            for (Literal l : literals) {
                if (l.isTrue() && positiveSymbols.contains(l.getId())) {

                    truth = true;
                    return true;
                } else if (l.isFalse() && negativeSymbols.contains(l.getId())) {
                    truth = true;
                    return true;
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
