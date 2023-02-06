package mscs.hms.model;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Landlord {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany
    private List<Inquiry> inquiries;

    @ManyToMany
    private List<RentalAgreement> rentalAgreements;

    @OneToOne
    private LegalEntity legalEntity;

    @OneToMany
    private List<Property> properties;
}
