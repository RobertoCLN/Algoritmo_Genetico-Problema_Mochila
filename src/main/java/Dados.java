import java.util.ArrayList;

public class Dados {

    private double capacidadeMochila = 3000;

    Objeto obj1 = new Objeto(1, 400, 200);
    Objeto obj2 = new Objeto(2, 500, 200);
    Objeto obj3 = new Objeto(3, 700, 300);
    Objeto obj4 = new Objeto(4, 900, 400);
    Objeto obj5 = new Objeto(5, 600, 100);
    Objeto obj6 = new Objeto(6, 100, 100);
    Objeto obj7 = new Objeto(7, 600, 5000);
    Objeto obj8 = new Objeto(8, 1000, 300);

    ArrayList<Objeto> lista_objetos = new ArrayList<>();

    public Dados() {
        lista_objetos.add(obj1);
        lista_objetos.add(obj2);
        lista_objetos.add(obj3);
        lista_objetos.add(obj4);
        lista_objetos.add(obj5);
        lista_objetos.add(obj6);
        lista_objetos.add(obj7);
        lista_objetos.add(obj8);
    }

    public double getCapacidadeMochila() {
        return capacidadeMochila;
    }
}