package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.model.producto;

public interface productoRepository extends JpaRepository<producto, Long> {
}
