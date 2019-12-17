package me.wcgod.inflearnjavatest;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

//클래스, 메소드 둘다 사용
//ReplaceUnserscores 언더바를 빈 공백으로 변환시켜준다.
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    //DisplayName을 권장
    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
        Study study = new Study(-10);
        //값이 null이 아닌지
        assertNotNull(study);
        //첫번쨰 파람 : 기대값, 두번째 파람 : 실제값 실제 값이 기대한 값과 같은지 확인
        //assertEquals(StudyStatus.DRAFT, study.getStatus(),"스터디를 처음 만들면 상태값이 DRAFT여야 한다.");
        //assertEquals(StudyStatus.DRAFT, study.getStatus(), ()->"스터디를 처음 만들면 상태값이 DRAFT여야 한다.");
        //인텔리제이 옵션 사용하여 람다로 변환 가능. 람다로 바꾸면 필요한 시점에만 문자열 연산을 한다.
        assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
            @Override
            public String get() {
                return "스터디 처음 만들면 DRAFT 상태다.";
            }
        });
        //다음 조건이 참인지 확인
        assertTrue(study.getLimit() > 0 , "스터디 최대 참석 가능 인원은 0보다 커야 한다.");
        //모든 확인 구문 확인
        assertAll(
                ()->assertNotNull(study),
                ()->assertEquals(StudyStatus.DRAFT, study.getStatus(),
                        ()-> "스터디를 처음 만들면"+StudyStatus.DRAFT + " 상태다"),
                ()->assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다")
        );
        //예외발생확인
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        assertEquals("limit 은 0보다 커야한다.", exception.getMessage());
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
        //설정한 타임아웃에서 바로 종료된다
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
        // TODO ThreadLocal 사용하는 코드가 있으면 예상하지 못한 결과가 발생할 수 있다.

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