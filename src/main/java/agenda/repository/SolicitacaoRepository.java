package agenda.repository;

import agenda.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    @Query("SELECT c.id FROM Cliente c WHERE c.cpf = :cpf")
    public Long getIdByCpf(@Param("cpf") String cpf);
    
    
    
    @Query(value = "SELECT count(*) FROM cliente WHERE cpf = :cpf", nativeQuery = true)
    public Long countIdByCpf(@Param("cpf") String cpf);


    }

