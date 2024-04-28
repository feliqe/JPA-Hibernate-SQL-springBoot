package com.feliqe.springboot.jpa.springbootjpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Embeddable
public class Audit {

    // ---------- Metodo de ciclo de persistencia --------------------
    //CILCO DE VIDA PERSIST - campos
    @Column(name = "create_at")
    private LocalDateTime creatAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //---------- Metodo de ciclo de persistencia --------------------
    @PrePersist
    public void prePersist() {
        System.out.println("evento de ciclo de viuda del entity pre persist");
        this.creatAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdated() {
        System.out.println("evento del ciclo de vida del objeto entity pre-update");
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
