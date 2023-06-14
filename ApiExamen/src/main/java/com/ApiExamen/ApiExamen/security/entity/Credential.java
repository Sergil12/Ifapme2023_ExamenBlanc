package com.ApiExamen.ApiExamen.security.entity;

import com.ApiExamen.ApiExamen.Entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long credential_id;
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean actif;

    @OneToOne(mappedBy = "credential", cascade = CascadeType.ALL)
    Account account;

    public Credential(String username, String password, Boolean actif, Account account) {
        this.username = username;
        this.password = password;
        this.actif = actif;
        this.account = account;
    }

    public static class Builder {

        private String username;
        private String password;
        private Boolean actif;
        private Account account;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setActif(boolean actif) {
            this.actif = actif;
            return this;
        }

        public Builder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public Credential build() {
            return new Credential(username, password, actif, account);
        }
    }
}
