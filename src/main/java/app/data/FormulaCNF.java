package app.data;

import java.util.*;

public class FormulaCNF {
    Set<Clause> clauses = new LinkedHashSet<>();
    List<Symbol> symbols = new LinkedList<>();

    public void addClause(Clause clause) {
        clauses.add(clause);
    }

    public Set<Clause> getClauses() {
        return clauses;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public boolean isSuccessfully() {
        for (Clause c: clauses) {
            if (!c.isTruth()) {
                return false;
            }
        }

        return true;
    }

    public void addSymbol(Symbol s) {
        symbols.add(s);
    }

    public void addAllSymbols(Collection<Symbol> symbols) {
        this.symbols.addAll(symbols);
    }

    public Set<Symbol> getAllSymbolsForCNF() {
        Set<Symbol> symbols = new HashSet<>();

        for (Clause c: clauses) {
            symbols.addAll(c.getAllSymbolsForClause());
        }

        return symbols;
    }






}
