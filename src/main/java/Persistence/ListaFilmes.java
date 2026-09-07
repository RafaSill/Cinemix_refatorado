package Persistence;

import java.util.ArrayList;
import java.util.List;

public class ListaFilmes {

    private static final List<Filmes> lista = new ArrayList();

    public static List<Filmes> listar(){
        return lista;
    }
    
    public static void adicionar (Filmes filmes){
        lista.add(filmes);
    }
    
    public static void remover (int index){
        lista.remove(index);
        if (index >= 0 && index < lista.size()) {
            lista.remove(index);
        }
    }
    
}
    

    

