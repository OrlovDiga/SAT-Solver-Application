package app.data;

import java.util.*;

public class FormulaCNF {
    Set<Clause> clauses = new LinkedHashSet<>(); //все клозы, котоыре содеражтся в нашей формуле снф
    List<Symbol> symbols = new LinkedList<>();// все символы, которые содрежатся в нашей формуле снф

    //метод добавленя клозов в в сет клозов объекта класса FormulaCNF
    public void addClause(Clause clause) {
        clauses.add(clause);
    }

    //получение всех клозов объекта данного класса
    public Set<Clause> getClauses() {
        return clauses;
    }

    //получение всех символов, содержащихся в поле symbols объекта класса FormulaCNF
    public List<Symbol> getSymbols() {
        return symbols;
    }

    //проверка выполнение всех клозов с заданными символами объекта класса FomulaCNF (грубо говоря проверяем истинность
    //нашей формулы
    public boolean isSuccessfully() {
        for (Clause c: clauses) {
            if (!c.isTruth()) {
                return false;
            }
        }

        return true;
    }

    //добавление во множество символов заданного символа
    public void addSymbol(Symbol s) {
        symbols.add(s);
    }

    //добавление коллекции(нескольких) сиволов во множество symbols объекта класса FormulaCNF
    public void addAllSymbols(Collection<Symbol> symbols) {
        this.symbols.addAll(symbols);
    }

    //получение множества symbols содержащихя в наших клозах данного обхекта FormulaCNF
    public Set<Symbol> getAllSymbolsForCNF() {
        Set<Symbol> symbols = new HashSet<>();

        for (Clause c: clauses) {
            symbols.addAll(c.getAllSymbolsForClause());
        }

        return symbols;
    }
}
