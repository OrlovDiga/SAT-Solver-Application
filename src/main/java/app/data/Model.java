package app.data;

import java.util.HashMap;
import java.util.List;
//класс представляет из себя модель символов с их буленским значением(грубо говоря
//объекты этого класса используются для подстановки в объект класса FormulaCNF)
public class Model {
    HashMap<Symbol, Boolean> model = new HashMap<>();

    public Model() {}

    //конструктор для заполнения хеш мапы( по дефолту в значению bool летит true)
    public Model(Literal... literals) {
        for (Literal l: literals) {
            model.put(l.getId(), l.isAssignment());
        }
    }

    public Model(Symbol... symbols) {
        for (Symbol s: symbols) {
            model.put(s, true);
        }
    }

    public Model(List<Symbol> symbolList) {
        for (int i = 0; i < symbolList.size(); i++) {
            model.put(symbolList.get(i), true);
        }
    }

    //метод проверки выполнения определенного клоза при заданных значениях
    public Boolean doneClause(Clause clause) {
        Boolean result = null;
        if (clause.isTruth()) {
            result = true;
        } else if (!clause.isTruth()) {
            result = false;
        } else {
            Boolean val = null;
            for (Symbol s: clause.getPositiveSymbols()) {
                val = model.get(s);

                if (val != null) {
                    if (Boolean.TRUE.equals(val)) {
                        result = true;
                        break;
                    }
                }
            }

            for (Symbol s: clause.getNegativeSymbols()) {
                val = model.get(s);

                if (val != null) {
                    if (Boolean.FALSE.equals(val)) {
                        result = true;
                        break;
                    }
                }
            }
        }
           //isprav
            /*boolean unassignedSymbols = false;
            Boolean value = null;
            for (Symbol positive : clause.getPositiveSymbols()) {
                value = model.get(positive);
                if (value != null) {
                    if (Boolean.TRUE.equals(value)) {
                        result = Boolean.TRUE;
                        break;
                    }
                } else {
                    unassignedSymbols = true;
                }
            }
            if (result == null) {
                for (Symbol negative : clause.getNegativeSymbols()) {
                    value = model.get(negative);
                    if (value != null) {
                        if (Boolean.FALSE.equals(value)) {
                            result = Boolean.TRUE;
                            break;
                        }
                    } else {
                        unassignedSymbols = true;
                    }
                }

                if (result == null) {
                    if (!unassignedSymbols) {
                        result = Boolean.FALSE;
                    }
                }
            }
        }*/

        return result;
    }

    //полчение символа в хэш мапе данного объекта класса
    public  Boolean getValue(Symbol symbol) {
        return model.get(symbol);
    }

    public Model addition(Symbol s, Boolean bool) {
        Model m = new Model();

        m.model.putAll(model);
        m.model.put(s, bool);

        return m;
    }

    public String printVals() {
        StringBuilder temp = new StringBuilder();

        for (Symbol s: model.keySet()) {
            temp.append(s + " = " + model.get(s) + "\n");
        }

        return temp.toString();
    }
    //переопределенный метод toString
    @Override
    public String toString() {
        return model.toString();
    }
}
