package fr.bryan_roger.gestionCompte.bll;

import fr.bryan_roger.gestionCompte.bo.Household;
import fr.bryan_roger.gestionCompte.bo.Spend;
import fr.bryan_roger.gestionCompte.bo.Tag;
import fr.bryan_roger.gestionCompte.bo.User;
import fr.bryan_roger.gestionCompte.dal.SpendRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SpendServiceTest {

    @Autowired
    private SpendRepository spendRepository;

    @Autowired
    private TestEntityManager entityManager;
//
//    private PasswordEncoder passwordEncoder;
//
//    public SpendServiceTest(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    private Tag tag;
    private Household household;
    private User user1;
    private User user2;

    @Test
    public void test_addSpend() {
        initData();

        Spend spend = new Spend().builder()
                .amount(BigDecimal.valueOf(50.00))
                .tag(tag)
                .date("12-2024")
                .payer(user1)
                .recipients(List.of(user1, user2))
                .order(1)
                .name("TestSpend")
                .household(household)
                .build();

        var savedSpend =  spendRepository.saveAndFlush(spend);
        assertThat(savedSpend.getId()).isNotNull();

        assertThat(savedSpend.getAmount()).isEqualTo(BigDecimal.valueOf(50.00));
        assertThat(savedSpend.getTag()).isEqualTo(tag);
        assertThat(savedSpend.getDate()).isEqualTo("12-2024");
        assertThat(savedSpend.getPayer()).isEqualTo(user1);
        assertThat(savedSpend.getRecipients()).containsExactlyInAnyOrder(user1, user2);
        assertThat(savedSpend.getOrder()).isEqualTo(1);
        assertThat(savedSpend.getName()).isEqualTo("TestSpend");
        assertThat(savedSpend.getHousehold()).isEqualTo(household);
        assertThat(savedSpend.getHousehold().getTags()).contains(tag);
        assertThat(savedSpend.getPayer()).isEqualTo(user1);
        assertThat(savedSpend.getRecipients()).contains(user1, user2);

    }

    private void initData(){
        var newTag = new Tag().builder().label("TestLabel").build();
        tag = entityManager.persistAndFlush(newTag);

        var newHousehold = new Household().builder().tags(List.of(tag)).name("foyerTest").build();
        household = entityManager.persistAndFlush(newHousehold);

        var u1 = new User().builder().email("user1@mail.com").backgroundColor("FFFFFF").letterColor("FFFFFF").households(List.of(household)).firstname("Prénom").lastname("Nom Famille").role("ROLE_USER").build();
        user1 = entityManager.persistAndFlush(u1);

        var u2 = new User().builder().email("user2@mail.com").backgroundColor("FFFFFF").letterColor("FFFFFF").households(List.of(household)).firstname("Prénom").lastname("Nom Famille").role("ROLE_USER").build();
        user2 = entityManager.persistAndFlush(u2);

    }
}
