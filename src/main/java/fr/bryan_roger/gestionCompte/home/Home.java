package fr.bryan_roger.gestionCompte.home;

import fr.bryan_roger.gestionCompte.tag.Tag;
import fr.bryan_roger.gestionCompte.user.User;
import jakarta.persistence.*;

import java.io.Serial;
import java.util.List;
import java.util.UUID;

@Entity
public class Home {

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
    @OneToMany(fetch = FetchType.EAGER)
    private List<User> users;

    public Home(UUID id, List<Tag> incomeTags, List<Tag> spendTags, List<User> users) {
        this.id = id;
        this.incomeTags = incomeTags;
        this.spendTags = spendTags;
        this.users = users;
    }

    public Home(List<Tag> incomeTags, List<Tag> spendTags, List<User> users) {
        this.incomeTags = incomeTags;
        this.spendTags = spendTags;
        this.users = users;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
