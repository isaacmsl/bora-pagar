package ufrn.imd.boraPagar.core;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class AbstractModel {
    @Id
    public String id;

    @Column(nullable = false)
    private Boolean isActive = true;
}