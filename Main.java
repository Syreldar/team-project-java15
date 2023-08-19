import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        Classifica classifica = new Classifica();

        List<Negozio> negoziList = Arrays.asList(
                new Negozio("A", 250.0, "Cibo"),
                new Negozio("B", 1500.0, "Elettronica"),
                new Negozio("C", 750.0, "Abbigliamento"),
                new Negozio("D", 1250.0, "Elettronica"),
                new Negozio("E", 500.0, "Cibo"),
                new Negozio("F", 1000.0, "Abbigliamento")
        );

        for (Negozio negozio : negoziList)
            classifica.aggiungiNegozio(negozio);

        System.out.println("Classifica per vendite:");
        for (Negozio negozio : classifica.getNegoziPerVendite())
            System.out.printf("%s - %.2f\n", negozio.getNome(), negozio.getVenditeTotali());

        Set<String> categorieUniche = new HashSet<>();
        for (Negozio negozio : negoziList)
            categorieUniche.add(negozio.getCategoriaPiuVenduta());

        for (String categoria : categorieUniche)
        {
            System.out.printf("\nClassifica dei negozi di %s:\n", categoria);
            for (Negozio negozio : classifica.getNegoziPerCategoria(categoria))
                System.out.printf("%s - %.2f\n", negozio.getNome(), negozio.getVenditeTotali());
        }
    }
}
