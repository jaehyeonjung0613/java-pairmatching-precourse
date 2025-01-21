# 🧐 미션 - 페어매칭관리 애플리케이션

[우아한테크코스](https://github.com/woowacourse) precourse 문제
중 [미션 - 페어매칭관리 애플리케이션](https://github.com/woowacourse/java-pairmatching-precourse) 풀이 기록하기.

계층형 아키텍처 구조와 MVC 패턴을 적용하여 TDD(테스트 주도 개발) 방식으로 개발하고, 입출력 및 프로그래밍 요구사항을 부합하도록 풀어 볼 예정.

## 0. 설계

### infrastructure

| 클래스  | 기능               |
|:----:|:-----------------|
| Game | - 애플리케이션 실행 및 종료 |

### view

|      클래스       | 기능                  |
|:--------------:|:--------------------|
|      View      | - 화면 기본 동작 정의       |
|    TextView    | - 화면 출력 동작 정의       |
|    FormView    | - 화면 입력 동작 정의       |
| InputFormView  | - 화면 텍스트 입력 동작 정의   |
| SelectFormView | - 화면 선택 입력 동작 정의    |
|   FrameView    | - 화면 템플릿 구조 동작 정의   |
|   RepeatView   | - 화면 반복 구조 동작 정의    |
| SerializeView  | - 화면 연속 시행 구조 동작 정의 |

### controller

|      클래스       | 기능                        |
|:--------------:|:--------------------------|
|   Controller   | - parameter 유효성 체크 및 가공   |
| ViewController | - View 생성<br/> - 화면 동작 처리 |

### service

|   클래스   | 기능        |
|:-------:|:----------|
| Service | - 비즈니스 처리 |

### repository

|    클래스     | 기능            |
|:----------:|:--------------|
| Repository | - CRUD 데이터 처리 |

### domain

|   클래스   | 기능   |
|:-------:|:-----|
| Course  | - 과정 |
|  Crew   | - 크루 |
|  Level  | - 레벨 |
| Mission | - 미션 |
|  Pair   | - 페어 |

### ui

|     클래스      | 기능                |
|:------------:|:------------------|
|    Input     | - 기본 입력 정의        |
| InputHelper  | - 기본 활용 가능한 입력 정의 |
|    Output    | - 기본 출력 정의        |
| OutputHelper | - 기본 활용 가능한 출력 정의 |

### exception

|               클래스               | 기능                   |
|:-------------------------------:|:---------------------|
|  IllegalArgumentBeanException   | - 아무 기능 없는 Exception |
| IllegalArgumentServiceException | - 서비스 관련 Exception   |
|  IllegalArgumentViewException   | - 입력 관련 Exception    |

## 1. 과정 전체 및 단건 조회

```java
// CourseTest.java

package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class CourseTest {
    private static final String[] names = {"백엔드", "프론트엔드"};

    @Test
    void 전체_조회() {
        List<String> nameList = Course.findAll().stream().map(Course::getName).collect(Collectors.toList());
        assertThat(nameList).containsExactly(names);
    }

    @Test
    void 존재_단건_조회() {
        Optional<Course> optionalCourse = Course.findByName(names[0]);
        assertThat(optionalCourse.isPresent()).isEqualTo(true);
    }

    @Test
    void 미존재_단건_조회() {
        Optional<Course> optionalCourse = Course.findByName("NOT EXISTS");
        assertThat(optionalCourse.isPresent()).isEqualTo(false);
    }
}
```

테스트 케이스 생성.

```java
// Course.java

package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Course {
    BACKEND("백엔드"), FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<Course> findAll() {
        return Arrays.stream(Course.values()).collect(Collectors.toList());
    }

    public static Optional<Course> findByName(String name) {
        return findAll().stream().filter(course -> course.name.equals(name)).findFirst();
    }
}
```

열거형 Course 클래스 정의.

전체 및 단건 조회 기능 구현.

## 2. 레벨 전체 및 단건 조회

```java
// LevelTest.java

package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class LevelTest {
    private static final String[] names = {"레벨1", "레벨2", "레벨3", "레벨4", "레벨5"};

    @Test
    void 전체_조회() {
        List<String> nameList = Level.findAll().stream().map(Level::getName).collect(Collectors.toList());
        assertThat(nameList).containsExactly(names);
    }

    @Test
    void 존재_단건_조회() {
        Optional<Level> optionalLevel = Level.findByName(names[0]);
        assertThat(optionalLevel.isPresent()).isEqualTo(true);
    }

    @Test
    void 미존재_단건_조회() {
        Optional<Level> optionalLevel = Level.findByName("NOT EXISTS");
        assertThat(optionalLevel.isPresent()).isEqualTo(false);
    }
}
```

테스트 케이스 생성.

```java
// Level.java

package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Level {
    LEVEL1("레벨1"), LEVEL2("레벨2"), LEVEL3("레벨3"), LEVEL4("레벨4"), LEVEL5("레벨5");

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<Level> findAll() {
        return Arrays.stream(Level.values()).collect(Collectors.toList());
    }

    public static Optional<Level> findByName(String name) {
        return findAll().stream().filter(level -> level.name.equals(name)).findFirst();
    }
}
```

열거형 Level 클래스 정의.

전체 및 단건 조회 기능 구현.

## 3. 크루 정보 저장 및 반환

```java
// CrewTest.java

package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CrewTest {
    @Test
    void 저장_및_반환() {
        Course course = Course.FRONTEND;
        String name = "anonymous";
        Crew crew = new Crew(course, name);
        assertThat(crew.getCourse()).isEqualTo(course);
        assertThat(crew.getName()).isEqualTo(name);
    }
}
```

테스트 케이스 생성.

```java
// Crew.java

package pairmatching.domain;

public class Crew {
    private final Course course;
    private final String name;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }
}
```

Crew 클래스 정의.

과정, 이름 저장 및 반환 기능 구현.

## 4. 페어 정보 저장 및 반환

```java
// PairTest.java

package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PairTest {
    @Test
    void 저장_및_반환() {
        Level level = Level.LEVEL1;
        Pair pair = new Pair(level);
        assertThat(pair.getLevel()).isEqualTo(level);
        assertThat(pair.getCrewList()).isEmpty();
    }
}
```

테스트 케이스 생성.

```java
// Pair.java

package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {
    private final Level level;
    private final List<Crew> crewList;

    public Pair(Level level) {
        this.level = level;
        this.crewList = new ArrayList<>();
    }

    public Level getLevel() {
        return level;
    }

    public List<Crew> getCrewList() {
        return Collections.unmodifiableList(this.crewList);
    }
}
```

Pair 클래스 정의.

레벨, 크루목록 저장 및 반환 기능 구현.

## 5. 페어 내 크루 추가 및 삭제

```java
// PairTest.java

package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PairTest {
    @Test
    void 페어_내_크루_추가() {
        Pair pair = new Pair(Level.LEVEL1);
        Crew crew = new Crew(Course.FRONTEND, "test");
        pair.add(crew);
        assertThat(pair.getCrewList()).hasSize(1);
    }

    @Test
    void 페어_내_크루_삭제() {
        Pair pair = new Pair(Level.LEVEL1);
        Crew crew = new Crew(Course.FRONTEND, "test");
        pair.add(crew);
        assertThat(pair.getCrewList()).hasSize(1);
        pair.remove(crew);
        assertThat(pair.getCrewList()).isEmpty();
    }
}
```

테스트 케이스 생성.

```java
// Pair.java

package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {
    public void add(Crew crew) {
        this.crewList.add(crew);
    }

    public void remove(Crew crew) {
        this.crewList.remove(crew);
    }
}
```

페어 내 크루 추가 및 삭제 기능 구현.

## 6. 동일 레벨 동일 페어 유효성 체크

```java
// PairTest.java

package pairmatching.domain;

public final class PairConstants {
    private PairConstants() {
    }

    public static final String EXISTS_SAME_LEVEL_PAIR_MATCHING_MESSAGE = "이미 동일 레벨에서 페어 매칭된 크루가 존재합니다.";
}
```

Pair 상수 클래스 정의.

유효성 관련 메시지 정의.

```java
// PairTest.java

package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;
import static pairmatching.domain.PairConstants.*;

import org.junit.jupiter.api.Test;

public class PairTest {
    @Test
    void 다른_레벨_페어_매칭() {
        Pair pairA = new Pair(Level.LEVEL1), pairB = new Pair(Level.LEVEL2);
        Crew crewA = new Crew(Course.FRONTEND, "testA"), crewB = new Crew(Course.FRONTEND, "testB");
        pairA.add(crewA);
        pairA.add(crewB);
        pairA.save();
        pairB.add(crewB);
        pairB.add(crewA);
        pairB.save();
        assertThat(pairB.getCrewList()).hasSize(2);
    }

    @Test
    void 같은_레벨_페어_매칭() {
        Level level = Level.LEVEL1;
        Pair pairA = new Pair(level), pairB = new Pair(level);
        Crew crewA = new Crew(Course.FRONTEND, "testA"), crewB = new Crew(Course.FRONTEND, "testB");
        pairA.add(crewA);
        pairA.add(crewB);
        pairA.save();
        pairB.add(crewB);
        assertThatThrownBy(() -> pairB.add(crewA)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(EXISTS_SAME_LEVEL_PAIR_MATCHING_MESSAGE);
        assertThat(pairB.getCrewList()).hasSize(1);
    }
}
```

테스트 케이스 생성.

```java
// Pair.java

package pairmatching.domain;

import static pairmatching.domain.PairConstants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {
    public boolean contains(Crew other) {
        return this.crewList.contains(other);
    }
}
```

```java
// Crew.java

package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Crew {
    private final Course course;
    private final String name;
    private final List<Pair> pairList;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
        this.pairList = new ArrayList<>();
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    protected void add(Pair pair) {
        this.pairList.add(pair);
    }

    public boolean existsLevelWithCrew(Level level, Crew other) {
        return this.pairList.stream().filter(pair -> pair.getLevel().equals(level))
            .anyMatch(pair -> pair.contains(other));
    }
}
```

페어 이력 등록 기능 구현.

동일 레벨 같은 크루 대상으로 페어 유무 확인 기능 구현.

```java
// Pair.java

package pairmatching.domain;

import static pairmatching.domain.PairConstants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {
    public void add(Crew crew) throws IllegalArgumentException {
        this.validateMatchingWithCrew(crew);
        this.crewList.add(crew);
    }

    private void validateMatchingWithCrew(Crew other) {
        if (this.crewList.stream()
            .anyMatch(
                crew -> crew.existsLevelWithCrew(this.level, other) || other.existsLevelWithCrew(this.level, crew))) {
            throw new IllegalArgumentException(EXISTS_SAME_LEVEL_PAIR_MATCHING_MESSAGE);
        }
    }

    protected void save() {
        for (Crew crew : this.crewList) {
            crew.add(this);
        }
    }
}
```

크루 추가시 매칭 유효성 확인.

크루들의 페어 이력을 기록하는 기능 구현.

## 7. 미션 전체 및 단건 조회

```java
// MissionTest.java

package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class MissionTest {
    private static final String[] names = {"자동차경주", "로또", "숫자야구게임", "장바구니", "결제", "지하철노선도", "성능개선", "배포"};

    @Test
    void 전체_조회() {
        List<String> nameList = Mission.findAll().stream().map(Mission::getName).collect(Collectors.toList());
        assertThat(nameList).containsExactly(names);
    }

    @Test
    void 존재_단건_조회() {
        Optional<Mission> optionalMission = Mission.findByLevelAndName(Level.LEVEL1, "자동차경주");
        assertThat(optionalMission.isPresent()).isEqualTo(true);
    }

    @Test
    void 미존재_단건_조회() {
        Optional<Mission> optionalMission = Mission.findByLevelAndName(Level.LEVEL1, "NOT EXISTS");
        assertThat(optionalMission.isPresent()).isEqualTo(false);
    }
}
```

테스트 케이스 생성.

```java
// Mission.java

package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Mission {
    RACING(Level.LEVEL1, "자동차경주"), LOTTO(Level.LEVEL1, "로또"), BASEBALL(Level.LEVEL1, "숫자야구게임"), CART(Level.LEVEL2,
        "장바구니"), PAYMENT(Level.LEVEL2, "결제"), SUBWAY(Level.LEVEL2, "지하철노선도"), PERFORMANCE(Level.LEVEL4,
        "성능개선"), DEPLOYMENT(Level.LEVEL4, "배포");

    private final Level level;
    private final String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public static List<Mission> findAll() {
        return Arrays.stream(Mission.values()).collect(Collectors.toList());
    }

    public static Optional<Mission> findByLevelAndName(Level level, String name) {
        Mission[] missions = Mission.values();
        return Arrays.stream(missions)
            .filter(mission -> mission.getLevel().equals(level) && mission.getName().equals(name))
            .findFirst();
    }
}
```

열거형 Mission 클래스 정의.

전체 및 단건 조회 기능 구현.

## 8. 미션 페어 매칭

```java
// Config.java

package pairmatching;

public final class Config {
    private Config() {
    }

    public static final int PAIR_PER_MIN_COUNT = 2;
}
```

애플리케이션 설정 클래스 정의.

페어 당 최소 인원 정의.

```java
// MissionTest.java

package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import pairmatching.Config;

public class MissionTest {
    @Test
    void 페어_기본_매칭() {
        Course course = Course.FRONTEND;
        Mission mission = Mission.RACING;
        assertThat(mission.exists(course)).isEqualTo(false);
        mission.match(course, this.createCrewList(course, Config.PAIR_PER_MIN_COUNT * 2));
        assertThat(mission.exists(course)).isEqualTo(true);
        assertThat(mission.getPairList()).hasSize(2);
    }

    @Test
    void 페어_홀수_포함_매칭() {
        Course course = Course.FRONTEND;
        Mission mission = Mission.RACING;
        assertThat(mission.exists(course)).isEqualTo(false);
        mission.match(course, this.createCrewList(course, Config.PAIR_PER_MIN_COUNT * 2 + 1));
        assertThat(mission.exists(course)).isEqualTo(true);
        assertThat(mission.getPairList()).hasSize(2);
    }

    private List<Crew> createCrewList(Course course, int count) {
        return IntStream.range(0, count)
            .mapToObj(number -> new Crew(course, String.valueOf(number)))
            .collect(Collectors.toList());
    }
}
```

테스트 케이스 생성.

```java
// Mission.java

package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import pairmatching.Config;

public enum Mission {
    RACING(Level.LEVEL1, "자동차경주"), LOTTO(Level.LEVEL1, "로또"), BASEBALL(Level.LEVEL1, "숫자야구게임"), CART(Level.LEVEL2,
        "장바구니"), PAYMENT(Level.LEVEL2, "결제"), SUBWAY(Level.LEVEL2, "지하철노선도"), PERFORMANCE(Level.LEVEL4,
        "성능개선"), DEPLOYMENT(Level.LEVEL4, "배포");

    private final Level level;
    private final String name;
    private final Map<Course, List<Pair>> pairOfCourse;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
        this.pairOfCourse = new HashMap<>();
    }

    public boolean exists(Course course) {
        return this.pairOfCourse.get(course) != null;
    }

    public void match(Course course, List<Crew> shuffledCrewList) {
        List<Pair> pairList = this.divide(shuffledCrewList);
        this.pairOfCourse.put(course, pairList);
        this.generate(course);
    }

    private List<Pair> divide(List<Crew> shuffledCrewList) {
        List<Pair> pairList = new ArrayList<>();
        int length = shuffledCrewList.size();
        for (int fromIndex = 0, toIndex = Config.PAIR_PER_MIN_COUNT;
             fromIndex < length; fromIndex = toIndex, toIndex += Config.PAIR_PER_MIN_COUNT) {
            Pair pair = new Pair(this.level);
            if (this.isLastPair(fromIndex, length)) {
                toIndex = length;
            }
            for (int index = fromIndex; index < toIndex; index++) {
                pair.add(shuffledCrewList.get(index));
            }
            pairList.add(pair);
        }
        return pairList;
    }

    private boolean isLastPair(int index, int length) {
        return index + Config.PAIR_PER_MIN_COUNT >= length - 1;
    }

    private void generate(Course course) {
        List<Pair> pairList = this.pairOfCourse.get(course);
        for (Pair pair : pairList) {
            pair.save();
        }
    }

    public Optional<List<Pair>> getPairList(Course course) {
        if (this.exists(course)) {
            return Optional.of(Collections.unmodifiableList(this.pairOfCourse.get(course)));
        }
        return Optional.empty();
    }
}
```

과정별 페어 목록 관리.

페어 목록이 존재하는지 확인 기능 구현.

매칭을 위해 크루 목록을 페어 단위로 배분하는 기능 구현.

매칭 후 이력 관리를 위한 페어 기록.

과정에 대한 페어 목록 반환 기능 구현.

## 9. 미션 페어 재매칭

```java
// MissionTest.java

package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import pairmatching.Config;

public class MissionTest {
    @Test
    void 페어_재_매칭() {
        Course course = Course.FRONTEND;
        Mission mission = Mission.RACING;
        Crew crew = new Crew(course, "test");
        List<Crew> crewListA = this.createCrewList(course, Config.PAIR_PER_MIN_COUNT);
        List<Crew> crewListB = this.createCrewList(course, Config.PAIR_PER_MIN_COUNT);
        crewListA.add(crew);
        crewListB.add(crew);
        mission.match(course, crewListA);
        mission.match(course, crewListB);
        assertThat(crew.getPairList()).hasSize(1);
    }
}
```

테스트 케이스 생성.

```java
// Crew.java

package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Crew {
    public List<Pair> getPairList() {
        return Collections.unmodifiableList(this.pairList);
    }

    protected void remove(Pair pair) {
        this.pairList.remove(pair);
    }
}
```

페어 이력 반환 기능 구현.

페어 제거 기능 구현.

```java
// Pair.java

package pairmatching.domain;

import static pairmatching.domain.PairConstants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {
    public void clear() {
        for (Crew crew : this.crewList) {
            crew.remove(this);
        }
        this.crewList.clear();
    }
}
```

크루 목록 초기화 기능 구현.

```java
// Mission.java

package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import pairmatching.Config;

public enum Mission {
    RACING(Level.LEVEL1, "자동차경주"), LOTTO(Level.LEVEL1, "로또"), BASEBALL(Level.LEVEL1, "숫자야구게임"), CART(Level.LEVEL2,
        "장바구니"), PAYMENT(Level.LEVEL2, "결제"), SUBWAY(Level.LEVEL2, "지하철노선도"), PERFORMANCE(Level.LEVEL4,
        "성능개선"), DEPLOYMENT(Level.LEVEL4, "배포");

    private final Level level;
    private final String name;
    private final Map<Course, List<Pair>> pairOfCourse;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
        this.pairOfCourse = new HashMap<>();
    }

    public void match(Course course, List<Crew> shuffledCrewList) {
        List<Pair> pairList = this.divide(shuffledCrewList);
        this.remove(course);
        this.pairOfCourse.put(course, pairList);
        this.generate(course);
    }

    private void remove(Course course) {
        List<Pair> pairList = this.pairOfCourse.remove(course);
        if (pairList != null) {
            for (Pair pair : pairList) {
                pair.clear();
            }
        }
    }
}
```

페어 매치시 기존 매칭 이력이 존재하면 삭제.

## 10. Exception 정의

```java
// IllegalArgumentBeanException.java

package pairmatching.exception;

public class IllegalArgumentBeanException extends RuntimeException {
    public IllegalArgumentBeanException() {
        super();
    }
}
```

아무 기능 하지 않은 예외(롤백용).

```java
// IllegalArgumentServiceException.java

package pairmatching.exception;

public class IllegalArgumentServiceException extends IllegalArgumentException {
    public IllegalArgumentServiceException(String message) {
        super(message);
    }
}
```

비즈니스단 처리 관련 예외.

```java
// IllegalArgumentViewException.java

package pairmatching.exception;

public class IllegalArgumentViewException extends IllegalArgumentException {
    public IllegalArgumentViewException(String message) {
        super(message);
    }
}
```

화면단 처리 관련 예외.

```java
// Pair.java

package pairmatching.domain;

import static pairmatching.domain.PairConstants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pairmatching.exception.IllegalArgumentServiceException;

public class Pair {
    private void validateMatchingWithCrew(Crew other) {
        if (this.crewList.stream()
            .anyMatch(
                crew -> crew.existsLevelWithCrew(this.level, other) || other.existsLevelWithCrew(this.level, crew))) {
            throw new IllegalArgumentServiceException(EXISTS_SAME_LEVEL_PAIR_MATCHING_MESSAGE);
        }
    }
}
``` 

매칭 예외 변경.

## 11. 입출력 인터페이스 정의

```java
// Input.java

package pairmatching.ui;

public interface Input {
    String readline();
}
```

입력 인터페이스 정의

```java
// InputHelper.java

package pairmatching.ui;

public abstract class InputHelper implements Input {
}
```

입력 추상 클래스 정의(추후 유용한 기능 필요시 구현).

```java
// Output.java

package pairmatching.ui;

public interface Output {
    void print(String message);

    void println(String message);
}
```

출력 인터페이스 정의.

```java
// OutputHelperConstants.java

package pairmatching.ui;

public final class OutputHelperConstants {
    private OutputHelperConstants() {
    }

    public static final String ERROR_OUTPUT_FORMAT = "[ERROR] %s";
}
```

출력 추상 상수 클래스 정의.

에러 메시지 형식 정의.

```java
// OutputHelper.java

package pairmatching.ui;

public abstract class OutputHelper implements Output {
    public final void printNextLine() {
        this.println("");
    }

    public final void printError(String message) {
        this.println(String.format("%s %s", "[ERROR]", message));
    }
}
```

출력 추상 클래스 정의.

줄 넘김, 에러 메시지 출력 구현.

## 12. 콘솔 입출력 구현

```java
// ConsoleInputHelper.java

package pairmatching.ui;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleInputHelper extends InputHelper {
    @Override
    public String readline() {
        return Console.readLine();
    }
}
```

콘솔 입력 구현.

```java
// ConsoleOutputHelper.java

package pairmatching.ui;

import java.io.PrintStream;

public class ConsoleOutputHelper extends OutputHelper {
    private final PrintStream console = System.out;

    @Override
    public void print(String message) {
        this.console.print(message);
    }

    @Override
    public void println(String message) {
        this.console.println(message);
    }
}
```

콘솔 출력 구현.

