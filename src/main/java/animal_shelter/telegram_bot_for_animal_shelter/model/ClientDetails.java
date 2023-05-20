package animal_shelter.telegram_bot_for_animal_shelter.model;

import animal_shelter.telegram_bot_for_animal_shelter.model.enums.Status;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

// Класс для пользователей, которые взяли животное из приюта
@Entity
@Table(name = "client_details")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_details_id")
    private Long clientDetailsId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client clientId;

    @OneToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet petId;

    @Enumerated(EnumType.STRING)
    @Column(name = "previous_status")
    @Type(type = "pgsql_enum")
    private Status prevStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Type(type = "pgsql_enum")
    private Status status;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "was_notified_of_status_change", nullable = false)
    private boolean wasNotifiedOfStatusChange;

    public ClientDetails(Client clientId, Pet petId, Status status, LocalDate startDate, boolean wasNotifiedOfStatusChange) {
        this.clientId = clientId;
        this.petId = petId;
        this.status = status;
        this.startDate = startDate;
        this.wasNotifiedOfStatusChange = wasNotifiedOfStatusChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDetails that = (ClientDetails) o;
        return wasNotifiedOfStatusChange == that.wasNotifiedOfStatusChange && Objects.equals(clientDetailsId, that.clientDetailsId) && Objects.equals(clientId, that.clientId) && Objects.equals(petId, that.petId) && prevStatus == that.prevStatus && status == that.status && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientDetailsId, clientId, petId, prevStatus, status, startDate, wasNotifiedOfStatusChange);
    }

    @Override
    public String toString() {
        return "ClientDetails{" +
                "clientDetailsId=" + clientDetailsId +
                ", clientId=" + clientId +
                ", prevStatus=" + prevStatus +
                ", status=" + status +
                ", startDate=" + startDate +
                ", wasNotifiedOfStatusChange=" + wasNotifiedOfStatusChange +
                '}';
    }
}
