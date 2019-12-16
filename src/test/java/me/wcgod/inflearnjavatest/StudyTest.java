package me.wcgod.inflearnjavatest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//클래스, 메소드 둘다 사용
//ReplaceUnserscores 언더바를 빈 공백으로 변환시켜준다.
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    //DisplayName을 권장
    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }

    //해당 테스트는 실행하지 않기 위해 사용
    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE31")
    @Disabled
    void create_new_study_again() {
        System.out.println("create");
    }

    //모든 테스트가 실행 되기 전 한번 호출
    //static 사용, return 사용 불가, private 불가
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    //모든 테스트가 실행 된 이후에 딱 한번만 호출
    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    //각각의 테스트를 실행 전에 한번씩 실행
    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    //각각의 테스트를 실행 에 한번씩 실행
    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }
}