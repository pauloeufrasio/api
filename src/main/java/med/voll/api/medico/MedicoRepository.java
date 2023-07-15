package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository  extends JpaRepository<Medico,Long> { // Repository, que são interfaces. O Spring já nos fornece a implementação.
}
