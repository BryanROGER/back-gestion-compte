package fr.bryan_roger.gestionCompte.home;

import fr.bryan_roger.gestionCompte.tag.Tag;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class Home implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> incomeTags;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> spendTags;
    private String name;

    public Home(UUID id, List<Tag> incomeTags, List<Tag> spendTags, String name) {
        this.id = id;
        this.incomeTags = incomeTags;
        this.spendTags = spendTags;
        this.name = name;
    }


    public Home(List<Tag> incomeTags, List<Tag> spendTags, String name) {
        this.incomeTags = incomeTags;
        this.spendTags = spendTags;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Home() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Tag> getIncomeTags() {
        return incomeTags;
    }

    public void setIncomeTags(List<Tag> incomeTags) {
        this.incomeTags = incomeTags;
    }

    public List<Tag> getSpendTags() {
        return spendTags;
    }

    public void setSpendTags(List<Tag> spendTags) {
        this.spendTags = spendTags;
    }

}
