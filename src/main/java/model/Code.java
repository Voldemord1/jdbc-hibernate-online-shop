package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "code")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "value")
    private String value;

    @OneToOne (fetch = FetchType.EAGER, mappedBy = "code", cascade = CascadeType.ALL)
    private User user;

    public Code() {
    }

    public void generateCode(){
        value = String.valueOf(1000 + (int) (Math.random() * 9000));
    }
}
