import app.data.*;
import app.log.DataProcessing;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.util.*;

import static junit.framework.TestCase.*;

public class TestingClass {

    Symbol[] symbolsArr = {new Symbol(1), new Symbol(2)};
    private Model model = new Model(symbolsArr);
    private FormulaCNF formulaCNF;
    private List<Symbol> symbols = Arrays.asList(new Symbol(1), new Symbol(2));
    DataProcessing temp = new DataProcessing();

    private Set<Clause> clauseSet = new LinkedHashSet<>(Arrays.asList(
            new Clause(new Literal(symbolsArr[0], true), new Literal(symbolsArr[1], true)),
            new Clause(new Literal(symbolsArr[0], false), new Literal(symbolsArr[1], true)),
            new Clause(new Literal(symbolsArr[0], true), new Literal(symbolsArr[1], false)),
            new Clause(new Literal(symbolsArr[0], false), new Literal(symbolsArr[1], false))));

    @Test
    public void testingDpll() {
        Symbol[] symbolsArr = {new Symbol(1), new Symbol(2), new Symbol(3), new Symbol(4)};
        Model model = new Model(symbolsArr);
        List<Symbol> symbols = Arrays.asList(new Symbol(1), new Symbol(2), new Symbol(3), new Symbol(4));
        DataProcessing temp = new DataProcessing();

        Set<Clause> clauseSet = new LinkedHashSet<>(Arrays.asList(
                new Clause(new Literal(symbolsArr[0], true), new Literal(symbolsArr[2], true), new Literal(symbolsArr[3], false)),
                new Clause(new Literal(symbolsArr[3], false), new Literal(symbolsArr[1], true)),
                new Clause(new Literal(symbolsArr[0], true), new Literal(symbolsArr[1], false)),
                new Clause(new Literal(symbolsArr[2], false))));

        assertEquals(true, temp.dpll(model, symbols, clauseSet));
    }

    @Test
    public void testingReduceModelSearch() {
        assertEquals(Arrays.asList(symbolsArr[0]), temp.reduceModelSearch(symbolsArr[1], symbols));
    }

    @Test
    public void testingSearchPureSymbol() {
        assertEquals(null, temp.searchPureSymbol(symbols, clauseSet));
    }

    @Test
    public void testingSearchUnitClause() {
        assertEquals(null, temp.searchUnitClause(clauseSet, model));
    }
}
