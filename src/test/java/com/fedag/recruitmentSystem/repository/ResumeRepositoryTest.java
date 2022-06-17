package com.fedag.recruitmentSystem.repository;


import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(
    properties = {
        "spring.jpa.properties.javax.persistence.validation.mode=none"
    }
)
class ResumeRepositoryTest {

//    @Autowired
//    private ResumeRepository resumeRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    void itShouldFindResumeById() {
//        Long userId = 1L;
//        String experienceDescription = "Java developing";
//
//        User user = new User(userId, "Ivan", "Petrov", "Ivan@gmail.com", LocalDateTime.now().minusYears(30), Role.USER, "some pass", "some activation code", ActiveStatus.ACTIVE, null,null, null, null, null, null);
//        Experience experience = new Experience(null, experienceDescription, LocalDateTime.now().minusYears(2), LocalDateTime.now(), null);
//        List<Experience> experiences = new ArrayList<>();
//        List<Skill> skills = new ArrayList<>();
//        experiences.add(experience);
//        Resume resume = new Resume(null, "Ivan", ActiveStatus.ACTIVE, LocalDateTime.now(), experiences, skills, user);
//
//        userRepository.save(user);
//        Optional<User> optionalUser = userRepository.findById(userId);
//        assertThat(optionalUser)
//                .isPresent()
//                .hasValueSatisfying(c -> assertThat(c).usingRecursiveComparison()
//                        .isEqualTo(user));
//
//        Resume resultResume = resumeRepository.save(resume);
//        assertThat(resultResume).isNotNull();
//        Optional<Resume> optionalResume = resumeRepository.findById(resultResume.getId());
//        assertThat(optionalResume)
//                .isPresent()
//                .hasValueSatisfying(c -> assertThat(c).usingRecursiveComparison()
//                        .isEqualTo(resultResume));
//    }
}