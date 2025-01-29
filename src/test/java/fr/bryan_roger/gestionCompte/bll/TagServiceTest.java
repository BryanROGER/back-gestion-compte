//package fr.bryan_roger.gestionCompte.bll;
//
//import fr.bryan_roger.gestionCompte.TestConfig;
//import fr.bryan_roger.gestionCompte.bo.Tag;
//
//import fr.bryan_roger.gestionCompte.dal.TagRepository;
//import jakarta.persistence.EntityManager;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//
//
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@DataJpaTest
//public class TagServiceTest {
//
//    @Autowired
//    private TagRepository tagRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//
//
//    @Test
//    public void test_addTag(){
//        new Tag();
//        var tag = Tag.builder()
//                .label("test")
//                .isSpend(true)
//                .isSpend(true)
//                .build();
//
//        var addedTag = tagRepository.save(tag);
//
//        assertThat(addedTag).isNotNull();
//        assertThat(addedTag.getId()).isEqualTo(tag.getId());
//    }
//
//    @Test
//    public void test_deleteTag(){
//        new Tag();
//        var tag = Tag.builder()
//                .label("test")
//                .spend(true)
//                .income(true)
//                .build();
//
//        entityManager.persist(tag);
//
//        tagRepository.delete(tag);
//
//        assertThat(entityManager.find(Tag.class, tag.getId())).isNull();
//
//    }
//
//    @Test
//    public void test_updateTag(){
//        new Tag();
//        var tag = Tag.builder()
//                .label("testTagById")
//                .spend(true)
//                .income(true)
//                .build();
//
//        var tagAdded = entityManager.persist(tag);
//        entityManager.flush();
//
//        tagAdded.setLabel("label updated");
//        var tagUpdated = tagRepository.save(tagAdded);
//
//        assertThat(entityManager.find(Tag.class, tagAdded.getId())).isNotNull();
//        var findedTag = entityManager.find(Tag.class, tagAdded.getId());
//        assertThat(findedTag.getLabel()).isEqualTo("label updated");
//    }
//
//    @Test
//    public void test_findAll(){
//        initData();
//
//        var tags = tagRepository.findAll();
//
//        assertThat(tags).isNotNull();
//        assertThat(tags.size()).isEqualTo(2);
//    }
//
//    @Test
//    public void test_findTagById(){
//        new Tag();
//        var tag = Tag.builder()
//                .label("testTagById")
//                .spend(true)
//                .income(true)
//                .build();
//
//        var tagId = entityManager.persistAndGetId(tag);
//        System.out.println(tagId);
//
//        if (tagId instanceof UUID){
//
//        var findedTag = tagRepository.findById((UUID) tagId);
//
//        assertThat(findedTag).isNotNull();
//        assertThat(findedTag).isPresent();
//        assertThat(findedTag.get().getLabel()).isEqualTo(tag.getLabel());
//        }
//
//    }
//
//    private void initData(){
//        var tag = Tag.builder()
//                .label("test")
//                .spend(true)
//                .income(true)
//                .build();
//
//        var tag2 = Tag.builder()
//                .label("test")
//                .spend(true)
//                .income(true)
//                .build();
//
//        entityManager.persist(tag);
//        entityManager.persist(tag2);
//
//        entityManager.flush();
//    }
//}
