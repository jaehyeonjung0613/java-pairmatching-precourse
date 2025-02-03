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
        return index + Config.PAIR_PER_MIN_COUNT * 2 - 1 >= length;
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

## 13-1. 화면 기본 인터페이스 정의

```java
// View.java

package pairmatching.view;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public interface View {
    void execute(InputHelper inputHelper, OutputHelper outputHelper);
}
```

화면 기본 인터페이스 정의

## 13-2. 화면 출력 동작 정의 및 구현

```java
// TextView.java

package pairmatching.view;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public abstract class TextView implements View {
    abstract void show(OutputHelper outputHelper);

    @Override
    public final void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        this.show(outputHelper);
    }
}
```

화면 출력 동작 정의.

```java
// CourseTextViewTest.java

package pairmatching.view;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.OutputHelper;

public class CourseTextViewTest {
    @Test
    void 과정_출력() {
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        CourseTextView courseTextView = new CourseTextView();
        String message = "과정: 백엔드 | 프론트엔드";
        courseTextView.show(outputHelper);
        Mockito.verify(outputHelper).println(message);
    }
}
```

과정 출력 테스트 케이스 생성.

```java
// CourseTextViewConstants.java

package pairmatching.view;

public final class CourseTextViewConstants {
    private CourseTextViewConstants() {
    }

    public static final String COURSE_NAME_SEPARATOR = " | ";

    public static final String COURSE_NAME_LIST_OUTPUT_FORMAT = "과정: %s";
}
```

CourseTextView 상수 클래스 정의.

과정 출력 관련 상수 정의.

```java
// CourseTextView.java

package pairmatching.view;

import static pairmatching.view.CourseTextViewConstants.*;

import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.ui.OutputHelper;

public class CourseTextView extends TextView {
    @Override
    void show(OutputHelper outputHelper) {
        List<String> nameList = Course.findAll().stream().map(Course::getName).collect(Collectors.toList());
        outputHelper.println(
            String.format(COURSE_NAME_LIST_OUTPUT_FORMAT, String.join(COURSE_NAME_SEPARATOR, nameList)));
    }
}
```

과정 출력 화면 구현.

```java
// MissionTextViewTest.java

package pairmatching.view;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.OutputHelper;

public class MissionTextViewTest {
    @Test
    void 미션_출력() {
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        MissionTextView missionTextView = new MissionTextView();
        missionTextView.show(outputHelper);
        Mockito.verify(outputHelper).println("미션:");
        Mockito.verify(outputHelper).println(" - 레벨1: 자동차경주 | 로또 | 숫자야구게임");
    }
}
```

미션 출력 테스트 케이스 생성.

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

    public static List<Mission> findAllByLevel(Level level) {
        return findAll().stream().filter(mission -> mission.getLevel().equals(level)).collect(Collectors.toList());
    }
}
```

동일 레벨 기준 미션 목록 반환 기능 구현.

```java
// MissionTextViewConstants.java

package pairmatching.view;

public final class MissionTextViewConstants {
    private MissionTextViewConstants() {
    }

    public static final String MISSION_OUTPUT_LOGO = "미션:";
    public static final String MISSION_NAME_SEPARATOR = " | ";

    public static final String MISSION_NAME_LIST_OUTPUT_FORMAT = " - %s: %s";
}
```

MissionTextView 상수 클래스 정의.

미션 출력 관련 상수 정의.

```java
// MissionTextView.java

package pairmatching.view;

import static pairmatching.view.MissionTextViewConstants.*;

import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.ui.OutputHelper;

public class MissionTextView extends TextView {
    @Override
    void show(OutputHelper outputHelper) {
        List<Level> levelList = Level.findAll();
        outputHelper.println(MISSION_OUTPUT_LOGO);
        for (Level level : levelList) {
            List<String> missionNameList = Mission.findAllByLevel(level)
                .stream()
                .map(Mission::getName)
                .collect(Collectors.toList());
            outputHelper.println(String.format(MISSION_NAME_LIST_OUTPUT_FORMAT, level.getName(),
                String.join(MISSION_NAME_SEPARATOR, missionNameList)));
        }
    }
}
```

미션 출력 화면 구현.

## 13-3. 화면 템플릿 구조 동작 정의 및 구현

```java
// FrameView.java

package pairmatching.view.layout;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public abstract class FrameView implements View {
    private final View view;

    public FrameView(View view) {
        this.view = view;
    }

    abstract String frame();

    @Override
    public final void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        String frame = this.frame();
        outputHelper.println(frame);
        this.view.execute(inputHelper, outputHelper);
        outputHelper.println(frame);
    }
}
```

화면 템플릿 구조 동작 정의.

```java
// SharpFrameViewTest.java

package pairmatching.view.layout;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public class SharpFrameViewTest {
    @Test
    void 샵_템플릿_출력() {
        String frame = "#############################################";
        String content = "내용";
        OutputHelper mockOutputHelper = Mockito.mock(OutputHelper.class);
        View view = (inputHelper, outputHelper) -> outputHelper.println(content);
        SharpFrameView sharpFrameView = new SharpFrameView(view);
        sharpFrameView.execute(null, mockOutputHelper);
        Mockito.verify(mockOutputHelper, Mockito.times(2)).println(frame);
        Mockito.verify(mockOutputHelper).println(content);
    }
}
```

테스트 케이스 생성.

```java
// SharpFrameView.java

package pairmatching.view.layout;

import pairmatching.view.View;

public class SharpFrameView extends FrameView {
    public SharpFrameView(View view) {
        super(view);
    }

    @Override
    String frame() {
        return "#############################################";
    }
}
```

샵 템플릿 구조 화면 구현.

## 13-4. 화면 반복 구조 동작 구현

```java
// RepeatViewTest.java

package pairmatching.view.layout;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.exception.IllegalArgumentViewException;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public class RepeatViewTest {
    @Test
    void 반복_구조_동작() {
        int times = 3;
        String content = "내용";
        OutputHelper mockOutputHelper = Mockito.mock(OutputHelper.class);
        AtomicInteger count = new AtomicInteger(0);
        View view = (inputHelper, outputHelper) -> {
            outputHelper.println(content);
            if (count.getAndIncrement() < times) {
                throw new IllegalArgumentViewException("");
            }
        };
        RepeatView repeatView = new RepeatView(view);
        repeatView.execute(null, mockOutputHelper);
        Mockito.verify(mockOutputHelper, Mockito.times(times));
    }
}
```

테스트 케이스 생성.

```java
// RepeatView.java

package pairmatching.view.layout;

import pairmatching.exception.IllegalArgumentBeanException;
import pairmatching.exception.IllegalArgumentViewException;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public final class RepeatView implements View {
    private final View view;

    public RepeatView(View view) {
        this.view = view;
    }

    @Override
    public void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        do {
            try {
                view.execute(inputHelper, outputHelper);
                return;
            } catch (IllegalArgumentViewException e) {
                outputHelper.printError(e.getMessage());
                outputHelper.printNextLine();
            } catch (IllegalArgumentBeanException ignored) {
            }
        } while (true);
    }
}
```

반복 구조 동작 화면 구현(정상 실행시까지 루프 / 단, 서비스 예외는 제외).

## 13-5. 화면 연속 시행 구조 동작 구현

```java
// SerializeViewTest.java

package pairmatching.view.layout;

import static org.mockito.Mockito.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public class SerializeViewTest {
    @Test
    void 연속_시행_구조_동작() {
        OutputHelper mockOutputHelper = Mockito.mock(OutputHelper.class);
        AtomicInteger count = new AtomicInteger(0);
        View view = (inputHelper, outputHelper) -> outputHelper.println(String.valueOf(count.getAndIncrement()));
        SerializeView serializeView = new SerializeView(view, view, view);
        serializeView.execute(null, mockOutputHelper);
        InOrder inOrder = inOrder(mockOutputHelper);
        for (int i = 0; i < 3; i++) {
            inOrder.verify(mockOutputHelper).println(String.valueOf(i));
        }
    }
}
```

테스트 케이스 생성.

```java
// SerializeView.java

package pairmatching.view.layout;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public final class SerializeView implements View {
    private final View[] views;

    public SerializeView(View... views) {
        this.views = views;
    }

    @Override
    public void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        for (View view : this.views) {
            view.execute(inputHelper, outputHelper);
        }
    }
}
```

연속 시행 구조 동작 화면 구현

## 13-6. 화면 입력 동작 인터페이스 정의

```java
// FormView.java

package pairmatching.view.component;

import pairmatching.view.View;

public interface FormView extends View {
    void onEvent(String command);
}
```

화면 입력 동작 인터페이스 정의.

## 13-7. 화면 텍스트 입력 동작 정의 및 구현

```java
// InputFormView.java

package pairmatching.view.component;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public abstract class InputFormView implements FormView {
    abstract void query(OutputHelper outputHelper);

    @Override
    public final void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        this.query(outputHelper);
        String command = inputHelper.readline();
        outputHelper.printNextLine();
        this.onEvent(command);
    }
}
```

화면 텍스트 입력 동작 정의.

```java
// MissionInputFormViewTest.java

package pairmatching.view.component;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public class MissionInputFormViewTest {
    @Test
    void 텍스트_입력_동작() {
        String parameter = "test";
        InputHelper inputHelper = Mockito.mock(InputHelper.class);
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        Consumer<String> handler = Mockito.mock(Consumer.class);
        MissionInputFormView missionInputFormView = new MissionInputFormView(handler);
        Mockito.when(inputHelper.readline()).thenReturn(parameter);
        missionInputFormView.execute(inputHelper, outputHelper);
        Mockito.verify(outputHelper).println("과정, 레벨, 미션을 선택하세요.");
        Mockito.verify(outputHelper).println("ex) 백엔드, 레벨1, 자동차경주");
        Mockito.verify(handler, Mockito.atMostOnce()).accept(parameter);
    }
}
```

미션 텍스트 입력 동작 테스트 케이스 생성.

```java
// MissionInputFormView.java

package pairmatching.view.component;

import java.util.function.Consumer;

import pairmatching.ui.OutputHelper;

public class MissionInputFormView extends InputFormView {
    private final Consumer<String> handler;

    public MissionInputFormView(Consumer<String> handler) {
        this.handler = handler;
    }

    @Override
    void query(OutputHelper outputHelper) {
        outputHelper.println("과정, 레벨, 미션을 선택하세요.");
        outputHelper.println("ex) 백엔드, 레벨1, 자동차경주");
    }

    @Override
    public void onEvent(String command) {
        this.handler.accept(command);
    }
}
```

미션 텍스트 입력 동작 화면 구현.

## 13-8. 화면 선택 입력 동작 정의 및 구현

```java
// SelectItem.java

package pairmatching.view.component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface SelectItem {
    String getCommand();

    String getName();

    static List<SelectItem> findAll(Class<? extends SelectItem> selectItemClass) {
        return Arrays.stream(selectItemClass.getEnumConstants()).collect(Collectors.toList());
    }

    static Optional<SelectItem> findByCommand(Class<? extends SelectItem> selectItemClass, String command) {
        List<SelectItem> selectItemList = findAll(selectItemClass);
        for (SelectItem selectItem : selectItemList) {
            if (selectItem.getCommand().equals(command)) {
                return Optional.of(selectItem);
            }
        }
        return Optional.empty();
    }
}
```

선택 항목 인터페이스 정의.

전체 및 단건 조회 구현.

```java
// SelectHandler.java

package pairmatching.view.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class SelectHandler {
    private final Map<SelectItem, Runnable> handlerMap = new HashMap<>();

    private SelectHandler() {
    }

    public static SelectHandler builder() {
        return new SelectHandler();
    }

    public SelectHandler addEventListener(SelectItem selectItem, Runnable handler) {
        this.handlerMap.put(selectItem, handler);
        return this;
    }

    public Optional<Runnable> select(SelectItem selectItem) {
        return Optional.ofNullable(this.handlerMap.get(selectItem));
    }
}
```

선택 이벤트 처리기 구현.

```java
// SelectFormViewConstants.java

package pairmatching.view.component;

public final class SelectFormViewConstants {
    private SelectFormViewConstants() {
    }

    public static final String NOT_EXISTS_COMMAND_MESSAGE = "존재하지 않은 기능입니다.";
    public static final String NOT_PROCESS_EVENT_MESSAGE = "기능에 맞는 처리할 수 없습니다.";
}
```

SelectFormView 상수 클래스 정의.

선택 예외 관련 메시지 정의.

```java
// SelectFormView.java

package pairmatching.view.component;

import static pairmatching.view.component.SelectFormViewConstants.*;

import java.util.List;

import pairmatching.exception.IllegalArgumentServiceException;
import pairmatching.exception.IllegalArgumentViewException;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public abstract class SelectFormView implements FormView {
    private final SelectHandler selectHandler;

    public SelectFormView(SelectHandler selectHandler) {
        this.selectHandler = selectHandler;
    }

    abstract Class<? extends SelectItem> getItemClass();

    abstract void show(OutputHelper outputHelper, List<SelectItem> selectItemList);

    @Override
    public final void onEvent(String command) {
        SelectItem selectItem = SelectItem.findByCommand(this.getItemClass(), command)
            .orElseThrow(() -> new IllegalArgumentViewException(NOT_EXISTS_COMMAND_MESSAGE));
        Runnable handler = this.selectHandler.select(selectItem)
            .orElseThrow(() -> new IllegalArgumentServiceException(NOT_PROCESS_EVENT_MESSAGE));
        handler.run();
    }

    @Override
    public final void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        List<SelectItem> selectItemList = SelectItem.findAll(this.getItemClass());
        this.show(outputHelper, selectItemList);
        String command = inputHelper.readline();
        outputHelper.printNextLine();
        this.onEvent(command);
    }
}
```

화면 선택 입력 동작 정의.

```java
// MenuSelectItem.java

package pairmatching.view.component;

public enum MenuSelectItem implements SelectItem {
    PAIR_MATCHING("1", "페어 매칭"),
    PAIR_SELECTION("2", "페어 조회"),
    PAIR_RESET("3", "페어 초기화"),
    END("Q", "종료");

    private final String command;
    private final String name;

    MenuSelectItem(String command, String name) {
        this.command = command;
        this.name = name;
    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
```

메뉴에 출력될 항목 정의.

```java
// MenuSelectFormViewTest.java

package pairmatching.view.component;

import static org.assertj.core.api.Assertions.*;
import static pairmatching.view.component.SelectFormViewConstants.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public class MenuSelectFormViewTest {
    @Test
    void 올바른_선택_입력_동작() {
        InputHelper inputHelper = Mockito.mock(InputHelper.class);
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        AtomicInteger selected = new AtomicInteger(0);
        MenuSelectFormView menuSelectFormView = new MenuSelectFormView(
            SelectHandler.builder().addEventListener(MenuSelectItem.PAIR_MATCHING, selected::getAndIncrement));
        Mockito.when(inputHelper.readline()).thenReturn("1");
        menuSelectFormView.execute(inputHelper, outputHelper);
        assertThat(selected.get()).isEqualTo(1);
        Mockito.verify(outputHelper).println("기능을 선택하세요.");
        Mockito.verify(outputHelper).println("1. 페어 매칭");
    }

    @Test
    void 올바른_않은_선택_입력_동작() {
        InputHelper inputHelper = Mockito.mock(InputHelper.class);
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        MenuSelectFormView menuSelectFormView = new MenuSelectFormView(
            SelectHandler.builder().addEventListener(MenuSelectItem.PAIR_MATCHING, () -> {
            }));
        Mockito.when(inputHelper.readline()).thenReturn("NOT VALID COMMAND");
        assertThatThrownBy(() -> menuSelectFormView.execute(inputHelper, outputHelper))
            .isInstanceOf(IllegalArgumentException.class).hasMessage(NOT_EXISTS_COMMAND_MESSAGE);
    }
}
```

메뉴 선택 입력 동작 테스트 케이스 생성.

```java
// MenuSelectFormView.java

package pairmatching.view.component;

import java.util.List;

import pairmatching.ui.OutputHelper;

public class MenuSelectFormView extends SelectFormView {

    public MenuSelectFormView(SelectHandler selectHandler) {
        super(selectHandler);
    }

    @Override
    Class<? extends SelectItem> getItemClass() {
        return MenuSelectItem.class;
    }

    @Override
    void show(OutputHelper outputHelper, List<SelectItem> selectItemList) {
        outputHelper.println("기능을 선택하세요.");
        for (SelectItem selectItem : selectItemList) {
            outputHelper.println(String.format("%s. %s", selectItem.getCommand(), selectItem.getName()));
        }
    }
}
```

메뉴 선택 입력 동작 화면 구현.

```java
// ReMatchingSelectItem.java

package pairmatching.view.component;

public enum ReMatchingSelectItem implements SelectItem {
    YES("네", "네"),
    NO("아니오", "아니오");

    private final String command;
    private final String name;

    ReMatchingSelectItem(String command, String name) {
        this.command = command;
        this.name = name;
    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
```

리매칭에 출력될 항목 정의.

```java
// ReMatchingSelectFormViewTest.java

package pairmatching.view.component;

import static org.assertj.core.api.Assertions.*;
import static pairmatching.view.component.SelectFormViewConstants.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public class ReMatchingSelectFormViewTest {
    @Test
    void 올바른_선택_입력_동작() {
        InputHelper inputHelper = Mockito.mock(InputHelper.class);
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        AtomicInteger selected = new AtomicInteger(0);
        ReMatchingSelectFormView reMatchingSelectFormView = new ReMatchingSelectFormView(
            SelectHandler.builder().addEventListener(ReMatchingSelectItem.YES, selected::getAndIncrement));
        Mockito.when(inputHelper.readline()).thenReturn("네");
        reMatchingSelectFormView.execute(inputHelper, outputHelper);
        assertThat(selected.get()).isEqualTo(1);
        Mockito.verify(outputHelper).println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        Mockito.verify(outputHelper).println("네 | 아니오");
    }

    @Test
    void 올바른_않은_선택_입력_동작() {
        InputHelper inputHelper = Mockito.mock(InputHelper.class);
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        ReMatchingSelectFormView reMatchingSelectFormView = new ReMatchingSelectFormView(
            SelectHandler.builder().addEventListener(MenuSelectItem.PAIR_MATCHING, () -> {
            }));
        Mockito.when(inputHelper.readline()).thenReturn("NOT VALID COMMAND");
        assertThatThrownBy(() -> reMatchingSelectFormView.execute(inputHelper, outputHelper))
            .isInstanceOf(IllegalArgumentException.class).hasMessage(NOT_EXISTS_COMMAND_MESSAGE);
    }
}
```

리매칭 선택 입력 동작 테스트 케이스 생성.

```java
// RematchingSelectFormView.java

package pairmatching.view.component;

import java.util.List;
import java.util.stream.Collectors;

import pairmatching.ui.OutputHelper;

public class ReMatchingSelectFormView extends SelectFormView {
    public ReMatchingSelectFormView(SelectHandler selectHandler) {
        super(selectHandler);
    }

    @Override
    Class<? extends SelectItem> getItemClass() {
        return ReMatchingSelectItem.class;
    }

    @Override
    void show(OutputHelper outputHelper, List<SelectItem> selectItemList) {
        List<String> nameList = selectItemList.stream().map(SelectItem::getName).collect(Collectors.toList());
        outputHelper.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        outputHelper.println(String.join(" | ", nameList));
    }
}
```

리매칭 선택 입력 화면 구현.

## 14. 애플리케이션 Skeleton

```java
// Game.java

package pairmatching.infrastructure;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public class Game {
    private static Game instance;

    private final InputHelper inputHelper;
    private final OutputHelper outputHelper;

    private Game(InputHelper inputHelper, OutputHelper outputHelper) {
        this.inputHelper = inputHelper;
        this.outputHelper = outputHelper;
    }

    protected static Game getInstance(InputHelper inputHelper, OutputHelper outputHelper) {
        if (instance == null) {
            instance = new Game(inputHelper, outputHelper);
        }
        return instance;
    }

    public void run() {
    }

    public void finish() {
        instance = null;
    }
}
```

애플리케이션 런처 동작 및 종료 구성.

```java
// GameManager.java

package pairmatching.infrastructure;

import pairmatching.ui.ConsoleInputHelper;
import pairmatching.ui.ConsoleOutputHelper;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public class GameManager {
    private InputHelper inputHelper = new ConsoleInputHelper();
    private OutputHelper outputHelper = new ConsoleOutputHelper();

    public GameManager inputHelper(InputHelper inputHelper) {
        this.inputHelper = inputHelper;
        return this;
    }

    public GameManager outputHelper(OutputHelper outputHelper) {
        this.outputHelper = outputHelper;
        return this;
    }

    public Game build() {
        return Game.getInstance(this.inputHelper, this.outputHelper);
    }
}
```

런처 builder 생성.

```java
// Application.java

package pairmatching;

import pairmatching.infrastructure.Game;
import pairmatching.infrastructure.GameManager;

public class Application {
    public static void main(String[] args) {
        Game game = new GameManager().build();
        try {
            game.run();
        } finally {
            game.finish();
        }
    }
}
```

애플리케이션 실행 및 종료.

## 15. 메뉴 화면 정의 및 종료 구현

```java
// ViewController.java

package pairmatching.controller.view;

import pairmatching.view.View;

public interface ViewController {
    View make();
}
```

화면 생성 인터페이스 Controller 정의.

```java
// MenuViewController.java

package pairmatching.controller.view;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;
import pairmatching.view.component.MenuSelectFormView;
import pairmatching.view.component.MenuSelectItem;
import pairmatching.view.component.SelectHandler;

public class MenuViewController implements ViewController {
    private final InputHelper inputHelper;
    private final OutputHelper outputHelper;
    private final Runnable endHandler;

    public MenuViewController(InputHelper inputHelper, OutputHelper outputHelper, Runnable endHandler) {
        this.inputHelper = inputHelper;
        this.outputHelper = outputHelper;
        this.endHandler = endHandler;
    }

    @Override
    public View make() {
        return new MenuSelectFormView(SelectHandler.builder()
            .addEventListener(MenuSelectItem.END, this.endHandler));
    }
}
```

메뉴 화면 정의.

종료 이벤트 핸들러 등록.

```java
// Game.java

package pairmatching.infrastructure;

import pairmatching.controller.view.MenuViewController;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public class Game {
    private static Game instance;

    private final InputHelper inputHelper;
    private final OutputHelper outputHelper;
    private final MenuViewController menuViewController;

    private boolean end = false;

    private Game(InputHelper inputHelper, OutputHelper outputHelper) {
        this.inputHelper = inputHelper;
        this.outputHelper = outputHelper;
        this.menuViewController = new MenuViewController(inputHelper, outputHelper, this::end);
    }

    public void run() {
        View menuView = menuViewController.make();
        do {
            try {
                menuView.execute(inputHelper, outputHelper);
            } catch (IllegalArgumentException e) {
                outputHelper.printError(e.getMessage());
                outputHelper.printNextLine();
            }
        } while (!this.end);
    }

    private void end() {
        this.end = true;
    }
}
```

애플리케이션 구동시 메뉴 화면 출력되도록 구현.

종료 이벤트 발생시까지 메뉴 화면 출력.

## 16. 매칭 조회

```java
// Service.java

package pairmatching.service;

public interface Service {
}
```

비즈니스 인터페이스 Service 정의.

```java
// MissionService.java

package pairmatching.service;

import java.util.List;

import pairmatching.domain.Course;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.exception.IllegalArgumentServiceException;

public class MissionService implements Service {

    public List<Pair> select(Course course, Mission mission) {
        return mission.getPairList(course).get();
    }
}
```

Pair 목록 반환 기능 생성.

```java
// Controller.java

package pairmatching.controller;

public interface Controller {
}
```

비즈니스 인터페이스 Controller 정의.

```java
// MissionController.java

package pairmatching.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.service.MissionService;

public class MissionController implements Controller {
    private final MissionService missionService = new MissionService();

    public List<List<String>> select(String input) {
        String[] commands = input.split(", ");
        String courseName = commands[0];
        String levelName = commands[1];
        String missionName = commands[2];
        Course course = this.getOneByCourse(courseName);
        Level level = this.getOneByLevel(levelName);
        Mission mission = this.getOneByMission(level, missionName);
        List<Pair> pairList = this.missionService.select(course, mission);
        List<List<String>> pairOfNameList = new ArrayList<>();
        for (Pair pair : pairList) {
            List<String> nameList = pair.getCrewList().stream().map(Crew::getName).collect(Collectors.toList());
            pairOfNameList.add(nameList);
        }
        return pairOfNameList;
    }

    private Course getOneByCourse(String courseName) {
        return Course.findByName(courseName).get();
    }

    private Level getOneByLevel(String levelName) {
        return Level.findByName(levelName).get();
    }

    private Mission getOneByMission(Level level, String missionName) {
        return Mission.findByLevelAndName(level, missionName).get();
    }
}
```

페어 내 크루명 목록 반환 기능 생성.

```java
// PairSelectionViewControllerConstants.java

package pairmatching.controller.view;

public final class PairSelectionViewControllerConstants {
    private PairSelectionViewControllerConstants() {
    }

    public static final String PAIR_IN_CREW_SEPARATOR = " : ";
}
```

페어 내 크루 구분자 표현 정의.

```java
// PairSelectionViewController.java

package pairmatching.controller.view;

import static pairmatching.controller.view.PairSelectionViewControllerConstants.*;

import java.util.List;

import pairmatching.controller.MissionController;
import pairmatching.ui.OutputHelper;
import pairmatching.view.CourseTextView;
import pairmatching.view.MissionTextView;
import pairmatching.view.View;
import pairmatching.view.component.MissionInputFormView;
import pairmatching.view.layout.RepeatView;
import pairmatching.view.layout.SerializeView;
import pairmatching.view.layout.SharpFrameView;

public class PairSelectionViewController implements ViewController {
    private final OutputHelper outputHelper;
    // Controller
    private final MissionController missionController = new MissionController();

    public PairSelectionViewController(OutputHelper outputHelper) {
        this.outputHelper = outputHelper;
    }

    @Override
    public View make() {
        return new SerializeView(new SharpFrameView(
            new SerializeView(new CourseTextView(), new MissionTextView())),
            new RepeatView(new MissionInputFormView(this::select)));
    }

    public void select(String command) {
        List<List<String>> pairOfNameList = this.missionController.select(command);
        this.outputHelper.println("페어 매칭 결과입니다.");
        for (List<String> pairOfName : pairOfNameList) {
            this.outputHelper.println(String.format(String.join(PAIR_IN_CREW_SEPARATOR, pairOfName)));
        }
        this.outputHelper.printNextLine();
    }
}

```

페어 조회 기능 구현.

```java
// MenuViewController.java

package pairmatching.controller.view;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;
import pairmatching.view.component.MenuSelectFormView;
import pairmatching.view.component.MenuSelectItem;
import pairmatching.view.component.SelectHandler;

public class MenuViewController implements ViewController {
    private final InputHelper inputHelper;
    private final OutputHelper outputHelper;
    private final Runnable endHandler;
    // ViewController
    private final PairSelectionViewController pairSelectionViewController;

    public MenuViewController(InputHelper inputHelper, OutputHelper outputHelper, Runnable endHandler) {
        this.inputHelper = inputHelper;
        this.outputHelper = outputHelper;
        this.endHandler = endHandler;
        // ViewController
        pairSelectionViewController = new PairSelectionViewController(outputHelper);
    }

    @Override
    public View make() {
        return new MenuSelectFormView(SelectHandler.builder()
            .addEventListener(MenuSelectItem.PAIR_SELECTION, this::openPairSelection)
            .addEventListener(MenuSelectItem.END, this.endHandler));
    }

    public void openPairSelection() {
        View pairSelectionView = pairSelectionViewController.make();
        pairSelectionView.execute(inputHelper, outputHelper);
    }
}
```

페어 조회 이벤트 핸들러 등록.

## 17. 페어 조회 유효성 체크

```java
// MissionControllerConstants.java

package pairmatching.controller;

public final class MissionControllerConstants {
    private MissionControllerConstants() {
    }

    public static final String MISSION_INPUT_SEPARATOR = ", ";

    public static final String NOT_VALID_MISSION_INPUT_MESSAGE = "올바르지 않은 입력 형식입니다.";
    public static final String NOT_EXISTS_COURSE_INPUT_MESSAGE = "입력에 해당되는 과정이 존재하지 않습니다.";
    public static final String NOT_EXISTS_LEVEL_INPUT_MESSAGE = "입력에 해당되는 레벨이 존재하지 않습니다.";
    public static final String NOT_EXISTS_MISSION_INPUT_MESSAGE = "입력에 해당되는 미션이 존재하지 않습니다.";
}
```

입력 구분자 정의.

MissionController 유효성 관련 상수 정의.

```java
// MissionController.java

package pairmatching.controller;

import static pairmatching.controller.MissionControllerConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.exception.IllegalArgumentViewException;
import pairmatching.service.MissionService;

public class MissionController implements Controller {
    private final MissionService missionService = new MissionService();

    public List<List<String>> select(String input) {
        Command command = new Command(input);
        Course course = this.getOneByCourse(command.getCourseName());
        Level level = this.getOneByLevel(command.getLevelName());
        Mission mission = this.getOneByMission(level, command.getMissionName());
        List<Pair> pairList = this.missionService.select(course, mission);
        List<List<String>> pairOfNameList = new ArrayList<>();
        for (Pair pair : pairList) {
            List<String> nameList = pair.getCrewList().stream().map(Crew::getName).collect(Collectors.toList());
            pairOfNameList.add(nameList);
        }
        return pairOfNameList;
    }

    private Course getOneByCourse(String courseName) {
        return Course.findByName(courseName)
            .orElseThrow(() -> new IllegalArgumentViewException(NOT_EXISTS_COURSE_INPUT_MESSAGE));
    }

    private Level getOneByLevel(String levelName) {
        return Level.findByName(levelName)
            .orElseThrow(() -> new IllegalArgumentViewException(NOT_EXISTS_LEVEL_INPUT_MESSAGE));
    }

    private Mission getOneByMission(Level level, String missionName) {
        return Mission.findByLevelAndName(level, missionName)
            .orElseThrow(() -> new IllegalArgumentViewException(NOT_EXISTS_MISSION_INPUT_MESSAGE));
    }

    private static class Command {
        private final String courseName;
        private final String levelName;
        private final String missionName;

        public Command(String command) {
            this.validateCommand(command);
            String[] commands = command.split(MISSION_INPUT_SEPARATOR);
            this.courseName = commands[0];
            this.levelName = commands[1];
            this.missionName = commands[2];
        }

        private void validateCommand(String command) {
            if (command.split(MISSION_INPUT_SEPARATOR).length < 3) {
                throw new IllegalArgumentViewException(NOT_VALID_MISSION_INPUT_MESSAGE);
            }
        }

        public String getCourseName() {
            return courseName;
        }

        public String getLevelName() {
            return levelName;
        }

        public String getMissionName() {
            return missionName;
        }
    }
}
```

입력 정보에 대한 DAO 클래스 별도 정의.

입력 정보 받아올때 올바른 형식인지 확인.

Service 전달 전 과정/레벨/미션 존재 유효성 확인.

```java
// MissionServiceConstants.java

package pairmatching.service;

public final class MissionServiceConstants {
    private MissionServiceConstants() {
    }

    public static final String NOT_EXISTS_MATCHING_HISTORY_MESSAGE = "매칭 이력이 없습니다.";
}
```

MissionService 유효성 관련 상수 정의.

```java
// MissionService.java

package pairmatching.service;

import static pairmatching.service.MissionServiceConstants.*;

import java.util.List;

import pairmatching.domain.Course;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.exception.IllegalArgumentServiceException;

public class MissionService implements Service {

    public List<Pair> select(Course course, Mission mission) {
        return mission.getPairList(course)
            .orElseThrow(() -> new IllegalArgumentServiceException(NOT_EXISTS_MATCHING_HISTORY_MESSAGE));
    }
}
```

매칭 조회시 이력이 존재하지 않은지 유효성 확인.

## 18. 크루 목록 저장

```java
// Repository.java

package pairmatching.controller.repository;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
}
```

Repository 인터페이스 정의.

```java
// CrewRepository.java

package pairmatching.controller.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pairmatching.domain.Crew;

public class CrewRepository implements Repository<Crew> {
    private static final List<Crew> crews = new ArrayList<>();

    @Override
    public List<Crew> findAll() {
        return Collections.unmodifiableList(crews);
    }
}

```

Crew Repository 정의.

전체 조회 기능 구현.

## 19. 크루 목록 초기화

```java
// CrewRepositoryTest.java

package pairmatching.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CrewRepositoryTest {
    @Test
    void 크루_목록_초기화() {
        CrewRepository crewRepository = new CrewRepository();
        assertThat(crewRepository.findAll()).isEmpty();
        CrewRepository.init();
        assertThat(crewRepository.findAll()).hasSize(35);
    }
}
```

테스트 케이스 생성.

```java
// CrewRepository.java

package pairmatching.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public class CrewRepository implements Repository<Crew> {
    private static final List<Crew> crews = new ArrayList<>();

    public static void init() {
        List<Resource> resourceList = Resource.findAll();
        for (Resource resource : resourceList) {
            List<String> crewNameList = loadCrewName(resource.getFilename());
            for (String crewName : crewNameList) {
                Crew crew = new Crew(resource.getCourse(), crewName);
                crews.add(crew);
            }
        }
    }

    private static List<String> loadCrewName(String filename) {
        List<String> crewNameList = new ArrayList<>();
        String filePath = Objects.requireNonNull(CrewRepository.class.getClassLoader().getResource(filename)).getPath();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                crewNameList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return crewNameList;
    }

    private enum Resource {
        BACKEND(Course.BACKEND, "backend-crew.md"), FRONTEND(Course.FRONTEND, "frontend-crew.md");

        private final Course course;
        private final String filename;

        Resource(Course course, String filename) {
            this.course = course;
            this.filename = filename;
        }

        public Course getCourse() {
            return course;
        }

        public String getFilename() {
            return filename;
        }

        public static List<Resource> findAll() {
            return Arrays.stream(Resource.values()).collect(Collectors.toList());
        }
    }
}
```

리소스 파일을 참조해 크루명 목록 로드.

로드된 크루명을 크루 목록에 저장.

```java
// Game.java

package pairmatching.infrastructure;

import pairmatching.controller.view.MenuViewController;
import pairmatching.exception.IllegalArgumentServiceException;
import pairmatching.repository.CrewRepository;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public class Game {
    public void run() {
        this.init();
        View menuView = menuViewController.make();
        do {
            try {
                menuView.execute(inputHelper, outputHelper);
            } catch (IllegalArgumentServiceException e) {
                outputHelper.printError(e.getMessage());
                outputHelper.printNextLine();
            }
        } while (!this.end);
    }

    private void init() {
        CrewRepository.init();
    }
}
```

애플리케이션 실행시 크루 목록 초기화 시행.

## 20. 페어 매칭

```java
// CrewRepository.java

package pairmatching.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public class CrewRepository implements Repository<Crew> {
    public List<Crew> findAllByCourse(Course course) {
        return crews.stream().filter(crew -> crew.getCourse().equals(course)).collect(Collectors.toList());
    }
}
```

해당 과정에 소속된 크루 목록 조회 기능 구현.

```java
// MissionService.java

package pairmatching.service;

import static pairmatching.service.MissionServiceConstants.*;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.exception.IllegalArgumentServiceException;
import pairmatching.repository.CrewRepository;

public class MissionService implements Service {
    private final CrewRepository crewRepository = new CrewRepository();

    public void match(Course course, Mission mission) {
        List<Crew> crewList = this.crewRepository.findAllByCourse(course);
        List<Crew> shuffledCrewList = this.shuffleCrewList(crewList);
        mission.match(course, shuffledCrewList);
    }

    private List<Crew> shuffleCrewList(List<Crew> crewList) {
        return Randoms.shuffle(crewList);
    }
}
```

페어 매칭 기능 구현.

```java
// MissionController.java

package pairmatching.controller;

import static pairmatching.controller.MissionControllerConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.exception.IllegalArgumentViewException;
import pairmatching.service.MissionService;

public class MissionController implements Controller {
    public boolean checkPairMatched(String input) {
        Command command = new Command(input);
        Course course = this.getOneByCourse(command.getCourseName());
        Level level = this.getOneByLevel(command.getLevelName());
        Mission mission = this.getOneByMission(level, command.getMissionName());
        return mission.exists(course);
    }

    public void match(String input) {
        Command command = new Command(input);
        Course course = this.getOneByCourse(command.getCourseName());
        Level level = this.getOneByLevel(command.getLevelName());
        Mission mission = this.getOneByMission(level, command.getMissionName());
        this.missionService.match(course, mission);
    }
}
```

페어 매칭 존재 확인 기능 구현.

페어 매칭 기능 정의.

```java
// PairMatchingViewController.java

package pairmatching.controller.view;

import java.util.concurrent.atomic.AtomicBoolean;

import pairmatching.controller.MissionController;
import pairmatching.exception.IllegalArgumentBeanException;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.CourseTextView;
import pairmatching.view.MissionTextView;
import pairmatching.view.View;
import pairmatching.view.component.MissionInputFormView;
import pairmatching.view.component.ReMatchingSelectFormView;
import pairmatching.view.component.ReMatchingSelectItem;
import pairmatching.view.component.SelectHandler;
import pairmatching.view.layout.RepeatView;
import pairmatching.view.layout.SerializeView;import pairmatching.view.layout.SharpFrameView;

public class PairMatchingViewController implements ViewController {
    private final InputHelper inputHelper;
    private final OutputHelper outputHelper;
    // View Controller
    private final PairSelectionViewController pairSelectionViewController;
    // Controller
    private final MissionController missionController = new MissionController();

    public PairMatchingViewController(InputHelper inputHelper, OutputHelper outputHelper) {
        this.inputHelper = inputHelper;
        this.outputHelper = outputHelper;
        // View Controller
        this.pairSelectionViewController = new PairSelectionViewController(outputHelper);
    }

    @Override
    public View make() {
        return new SerializeView(
            new SharpFrameView(new SerializeView(new CourseTextView(), new MissionTextView())),
            new RepeatView(new MissionInputFormView(this::match)));
    }

    public void match(String command) {
        if (this.missionController.checkPairMatched(command) && !this.queryReMatching()) {
            throw new IllegalArgumentBeanException();
        }
        this.missionController.match(command);
        this.pairSelectionViewController.select(command);
    }

    private boolean queryReMatching() {
        AtomicBoolean result = new AtomicBoolean(false);
        View view = new RepeatView(new ReMatchingSelectFormView(SelectHandler.builder()
            .addEventListener(ReMatchingSelectItem.YES, () -> result.set(true))
            .addEventListener(ReMatchingSelectItem.NO, () -> result.set(false))));
        view.execute(this.inputHelper, this.outputHelper);
        return result.get();
    }
}
```

페어 매칭 화면 동작 정의 및 처리.

```java
// MenuViewController.java

package pairmatching.controller.view;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;
import pairmatching.view.component.MenuSelectFormView;
import pairmatching.view.component.MenuSelectItem;
import pairmatching.view.component.SelectHandler;

public class MenuViewController implements ViewController {
    private final InputHelper inputHelper;
    private final OutputHelper outputHelper;
    private final Runnable endHandler;
    // ViewController
    private final PairMatchingViewController pairMatchingViewController;
    private final PairSelectionViewController pairSelectionViewController;

    public MenuViewController(InputHelper inputHelper, OutputHelper outputHelper, Runnable endHandler) {
        this.inputHelper = inputHelper;
        this.outputHelper = outputHelper;
        this.endHandler = endHandler;
        // ViewController
        this.pairMatchingViewController = new PairMatchingViewController(inputHelper, outputHelper);
        this.pairSelectionViewController = new PairSelectionViewController(outputHelper);
    }

    @Override
    public View make() {
        return new MenuSelectFormView(SelectHandler.builder()
            .addEventListener(MenuSelectItem.PAIR_MATCHING, this::openPairMatching)
            .addEventListener(MenuSelectItem.PAIR_SELECTION, this::openPairSelection)
            .addEventListener(MenuSelectItem.END, this.endHandler));
    }

    public void openPairMatching() {
        View pairMatchingView = pairMatchingViewController.make();
        pairMatchingView.execute(this.inputHelper, this.outputHelper);
    }
}
```

페어 매칭 이벤트 핸들러 등록.

## 21. 페어 매칭 유효성 체크

```java
// MissionServiceConstants.java

package pairmatching.service;

public final class MissionServiceConstants {
    private MissionServiceConstants() {
    }

    public static final int MAX_MATCHING_COUNT = 3;

    public static final String NOT_EXISTS_MATCHING_HISTORY_MESSAGE = "매칭 이력이 없습니다.";
    public static final String NOT_EXISTS_MATCHING_APPROACH_MESSAGE = "매칭할 수 있는 방법이 없습니다.";
    public static final String NOT_MATCH_MAX_COUNT_MESSAGE = "지정된 횟수안에 매칭이 되지 않았습니다.";
}
```

유효성 관련 상수 정의.

```java
// Pair.java

package pairmatching.domain;

import static pairmatching.domain.PairConstants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pairmatching.exception.IllegalArgumentServiceException;

public class Pair {
    public int size() {
        return this.crewList.size();
    }
}
```

```java
// Crew.java


package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Crew {
    public int count(Level level) {
        return this.pairList.stream()
            .filter(pair -> pair.getLevel().equals(level))
            .mapToInt(pair -> pair.size() - 1)
            .sum();
    }
}
```

```java
// MissionService.java

package pairmatching.service;

import static pairmatching.service.MissionServiceConstants.*;

import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.Config;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.exception.IllegalArgumentServiceException;
import pairmatching.repository.CrewRepository;

public class MissionService implements Service {
    public void match(Course course, Mission mission) {
        List<Crew> crewList = this.crewRepository.findAllByCourse(course);
        if (!this.checkPossibleMatching(mission.getLevel(), crewList)) {
            throw new IllegalArgumentServiceException(NOT_EXISTS_MATCHING_APPROACH_MESSAGE);
        }
        if (this.tryMatching(course, mission, crewList) > MAX_MATCHING_COUNT) {
            throw new IllegalArgumentServiceException(NOT_MATCH_MAX_COUNT_MESSAGE);
        }
    }

    private boolean checkPossibleMatching(Level level, List<Crew> crewList) {
        int totalCount = crewList.size();
        int totalGroupPerCount = this.getGroupPerCount(totalCount), totalLastGroupPerCount = this.getLastGroupPerCount(
            totalCount);
        int groupPerCount = 0, lastGroupPerCount = 0;
        List<Integer> countOfPossibleMatchingList = this.getCountOfPossibleMatchingList(level, crewList);
        for (int matchingCount : countOfPossibleMatchingList) {
            if (lastGroupPerCount < totalLastGroupPerCount && matchingCount >= totalLastGroupPerCount) {
                lastGroupPerCount++;
                continue;
            }
            groupPerCount++;
        }
        return groupPerCount == totalGroupPerCount && lastGroupPerCount == totalLastGroupPerCount;
    }

    private List<Integer> getCountOfPossibleMatchingList(Level level, List<Crew> crewList) {
        return crewList.stream()
            .map(crew -> crewList.size() - crew.count(level))
            .filter(count -> count >= Config.PAIR_PER_MIN_COUNT - 1)
            .collect(Collectors.toList());
    }

    private int getGroupPerCount(int totalCount) {
        return (totalCount / Config.PAIR_PER_MIN_COUNT - 1) * Config.PAIR_PER_MIN_COUNT;
    }

    private int getLastGroupPerCount(int totalCount) {
        return Config.PAIR_PER_MIN_COUNT + totalCount % Config.PAIR_PER_MIN_COUNT;
    }

    private int tryMatching(Course course, Mission mission, List<Crew> crewList) {
        int count = 1;
        do {
            try {
                List<Crew> shuffledCrewList = this.shuffleCrewList(crewList);
                mission.match(course, shuffledCrewList);
                break;
            } catch (IllegalArgumentServiceException ignored) {
            }
        } while (count++ < MAX_MATCHING_COUNT);
        return count;
    }

    private List<Crew> shuffleCrewList(List<Crew> crewList) {
        return Randoms.shuffle(crewList);
    }
}
```

페어 매칭 전 경우의 수 를 확인하고 할 수 없으면 에러 메시지 출력.

지정된 시도까지 매칭이 되지 않으면 에러 메시지 출력.