package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// interface MedicoRepository cria um repositório de dados para a entidade Medico, permitindo que você acesse e manipule os dados relacionados aos médicos no banco de dados usando as operações padrão fornecidas pela interface JpaRepository
public interface MedicoRepository  extends JpaRepository<Medico,Long> {
    //O método "findAllByAtivoTrue" retorna uma página contendo todos os médicos que estão marcados como "ativo" no banco de dados. A condição de filtro "ativo = true" é especificada pelo nome do método, onde "Ativo" é uma propriedade booleana na classe Medico que representa o estado de ativação do médico
    Page<Medico> findAllByAtivoTrue(Pageable paginacao); // O tipo de retorno é Page<Medico>, indicando que o método retorna uma página de objetos da classe Medico.
}


