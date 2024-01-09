package ufrn.imd.boraPagar.core;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class AbstractModel {
    @JsonView(Views.Admin.class)
    @Id
    public String id;

    @JsonView(Views.Admin.class)
    @Column(nullable = false)
    private Boolean isActive = true;
}