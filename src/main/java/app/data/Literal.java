package app.data;

import java.util.Objects;
import java.util.Set;
public class Literal {
    private Symbol id; //id нашего символа
    private boolean assignment = true; //заданное значения нашего символа, изначально задан значением true

    public Literal(Symbol id) {
        this.id = id;
    }

    //конструктор для создания объекта класса Literal
    public Literal(Symbol id, boolean assignment) {
        this.id = id;
        this.assignment = assignment;
    }

    //возвращает тру, если наш литерал положителен
    public boolean isTrue() {
        return assignment;
    }

    //возвращает тру, если наш литерал имеет значение false в поле this.объекта assignment
    public boolean isFalse() {
        return !assignment;
    }

    //возвращает id объекта этого класса
    public Symbol getId() {
        return id;
    }

    //возвращает значение assignment (не знаю зачем сделал, так как у нас и так есть два метода,
    // котрые возращают assignment(ПОСМОТРЕТЬ ГДЕ ИСПОЛЬУЗЕТСЯ И ЗАМЕНИТЬ НА ТЕ ДВА ДРУГИХ)
    boolean isAssignment() {
        return assignment;
    }

    public void setAssignment(boolean assignment) {
        this.assignment = assignment;
    }

    //переопределенный метод сравнения(использовал идеевское переопределние, с хешом то же самое)
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
