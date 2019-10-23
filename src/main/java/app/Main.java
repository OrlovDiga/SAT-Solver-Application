package app;

import app.data.FormulaCNF;
import app.log.DataProcessing;
import app.log.DimacsReader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Throwable {
        DimacsReader reader = new DimacsReader();
        FormulaCNF formulaCnf = new FormulaCNF();
        List<String> list = new ArrayList<>();
        list.add("c This Formular is generated by mcnf");
        list.add("p cnf 3 2");
        list.add("1 2 -3 0");
        list.add("-1 2 -3 0");
        formulaCnf = reader.parse(list);
        DataProcessing dpllAlg = new DataProcessing(formulaCnf);
        System.out.println(dpllAlg.dpll(dpllAlg.getModel(), dpllAlg.getSymbols(), dpllAlg.getClauseSet()));
    }
}
/*
    c This Formular is generated by mcnf
        p cnf 3 2
        1 2 -3 0
        -1 2 -3 0
        */