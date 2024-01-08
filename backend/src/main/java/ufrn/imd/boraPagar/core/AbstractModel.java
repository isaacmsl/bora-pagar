package ufrn.imd.boraPagar.core;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class AbstractModel {
    @JsonIgnore
    @Id
    public String id;

    @JsonIgnore
    @Column(nullable = false)
    private Boolean isActive = true;
}