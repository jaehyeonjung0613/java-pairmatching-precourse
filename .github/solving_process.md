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