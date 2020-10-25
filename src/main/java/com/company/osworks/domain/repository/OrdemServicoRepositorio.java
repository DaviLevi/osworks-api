package com.company.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.osworks.domain.model.OrdemServico;

public interface OrdemServicoRepositorio extends JpaRepository<OrdemServico, Long>{
	
}
