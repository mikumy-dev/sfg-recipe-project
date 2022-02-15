package guru.springframework.sfgrecipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne
    private UnitOfMeasure uom;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(amount.stripTrailingZeros());
        sb.append(" ");
        sb.append(uom.getDescription());
        sb.append(" ");
        sb.append("of");
        sb.append(" ");
        sb.append(description);
        return sb.toString();
    }
}
