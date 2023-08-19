import java.util.ArrayList;
import java.util.List;

public class Classifica
{
    private final List<Negozio> negozi;

    public Classifica()
    {
        this.negozi = new ArrayList<>();
    }

    public void aggiungiNegozio(Negozio negozio)
    {
        negozi.add(negozio);
    }

    public List<Negozio> getNegoziPerVendite()
    {
        negozi.sort((n1, n2) -> Double.compare(n2.getVenditeTotali(), n1.getVenditeTotali()));
        return new ArrayList<>(negozi);
    }

    public List<Negozio> getNegoziPerCategoria(String categoria)
    {
        List<Negozio> filteredNegozi = new ArrayList<>();
        for (Negozio n : negozi)
        {
            if (categoria.equals(n.getCategoriaPiuVenduta()))
                filteredNegozi.add(n);
        }

        filteredNegozi.sort((n1, n2) -> Double.compare(n2.getVenditeTotali(), n1.getVenditeTotali()));
        return filteredNegozi;
    }
}
