import com.zupacademy.trips.model.TravelPackage
import java.math.BigDecimal

class TravelPackageDAO {

    companion object {
        private val listTravelPackage = mutableListOf(
                TravelPackage("São Paulo", "sao_paulo_sp", 2,  BigDecimal(243.99)),
                TravelPackage("Belo Horizonte", "belo_horizonte_mg", 3,  BigDecimal(421.50)),
                TravelPackage("Recife", "recife_pe", 4,  BigDecimal(754.20)),
                TravelPackage("Rio de Janeiro", "rio_de_janeiro_rj", 6,  BigDecimal(532.55)),
                TravelPackage("Salvador", "salvador_ba", 5,  BigDecimal(899.99)),
                TravelPackage("Foz do Iguaçu", "foz_do_iguacu_pr", 1,  BigDecimal(289.90))
        )
    }

    fun allTravelPackage (): List<TravelPackage> {
        return listTravelPackage.toList()
    }

    fun getPackage(position: Int): TravelPackage {
        return listTravelPackage[position]
    }
}