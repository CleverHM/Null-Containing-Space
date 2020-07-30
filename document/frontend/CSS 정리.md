## CSS 정리

### 1. webkit

> css가 ie에서 적용되고 chrome에서 적용이 안되면, chrome에만 따로 클래스를 주는 방법
>
> 즉, 크롬 전용 css

- 사용법 : `-webkit-` + css 선언



### 2. Wildcard Selectors

> WildCard : 컴퓨터에서 특정 명령어로 명령을 내릴 때, 여러 파일을 한꺼번에 지정할 목적으로 사용하는 기호
>
> https://developer.mozilla.org/ko/docs/Web/CSS/Attribute_selectors

- `[attr]`

  - attr이라는 이름의 특성을 가진 요소를 선택

    ``` css
    div[lang] {
      font-weight: bold;
    }
    ```

    

- `[attr=value]` 

  - attr이라는 이름의 특성값이 정확히 value인 요소를 선택

    ``` css
    div[lang="pt"] {
      color: green;
    }
    ```

    

- `[attr~=value]`

  -  attr이라는 이름의 특성값이 정확히 value인 요소를 선택

  - attr특성은 공백으로 구분한 여러개의 값을 가질 수 있음

    ``` css
    div[lang~="en-us"] {
      color: blue;
    }
    ```

    

- `[attr|=value]` 

  - attr이라는 특성값을 가지고 있으며 그 특성값이 정확히 value이거나 value로 시작하면서 `-`문자가 곧바로뒤에 따라붙을때 선택

  - 보통 언어 서브코드(`en-US`, `ko-KR` 등)가 일치하는지 확인할때 사용

    ``` css
    div[lang|="zh"] {
      color: red;
    }
    ```

    

- `[attr^=value]` 

  - attr이라는 특성값을 가지고 있으며 접두사로 value가 값에 포함되어있으며 이 요소를 선택

    ``` css
    a[href^="#"] {background-color:gold;
    ```

    

- `[attr$=value]`

  - attr이라는 특성값을 가지고 있으며 접두사로 value가 값에 포함되어있으며 이 요소를 선택

    ``` css
    a[href$=".org"] {
      color: red;
    }
    ```

    

- `[attr*=value]`  

  - attr이라는 특성값을 가지고 있으며, 값안에 value라는 문자열이 적어도 하나 이상 존재한다면 선택

    ``` css
    a[href*="example"] {
      background-color: silver;
    }
    
    ```

    

- `[attr operator value i]`

  - 괄호를 닫기 전에 `i` 혹은 `I`를 붙이면 값의 대소문자를 구문하지 않음(ASCII 범위내 한정)

    ``` css
    a[href*="insensitive" i] {
      color: cyan;
    }
    ```

    

- `[attr operator value s]`

  - 괄호를 닫기전에 `s`, `S`를 붙여주면 값의 대소문자 구분(ASCII 범위 내 한정)

    ``` css
    a[href*="cAsE" s] { 
      color: pink; 
    }
    ```

    

### 3. CSS Pseudo Classes

| Selector                 | Example description                          |
| ------------------------ | -------------------------------------------- |
| `<element>:active`       | 링크가 활성화 될때                           |
| `<element>:checked`      | checked된 모든 `<element> `요소              |
| `<element>:disabled`     | disabled된 모든 `<element> `요소             |
| `<element>:first-child`  | 부모의 첫번째 자식인 모든 `element`요소 선택 |
| `<element>:last-child`   | 부모의 마지막 자식인 모든 `element`요소 선택 |
| `<element>:nth-child(n)` | 부모의 n번째 자식인 모든 `element`요소 선택  |
| `<element>:hover`        | 마우스가 올라가있을때                        |
| `<element>:visited`      | 방문된 링크                                  |
| `<element>:valid`        | valid value를 가진 element를 선택            |



### 4. css 너비&높이 속성

- 속성 값

  | 값     | 설명                                 |
  | ------ | ------------------------------------ |
  | `auto` | 요소의 너비를 브라우저가 계산해 지정 |

  

- 상대길이

  - 컨테이닝 블록 : 

    > https://developer.mozilla.org/ko/docs/Web/CSS/All_About_The_Containing_Block

  - 뷰포트 :

    - 현재 화면에 보여지고 있는 영역
    - 웹브라우저에서는 현재 창에서 문서를 볼 수 있는 부분

  | 단위  | 설명                                                         |
  | ----- | ------------------------------------------------------------ |
  | `em`  | 요소 `font`의 계산값, 요소의 font-size에 사용하면 상속받는 font-size 값을 나타냄 |
  | `rem` | 루트 요소(보통 `html`)의 font-size, 기본값 : 16px            |
  | `vh`  | 뷰포트의 초기 컨테이닝 블록 높이 1%                          |
  | `vw`  | 뷰포트의 초기 컨테이닝 블록 높이 1%                          |



### 5. `position`

> 문서상 요소를 배치하는 방법
>
> https://developer.mozilla.org/ko/docs/Web/CSS/position

- static 
  - 일반적인 문서 흐름에 따라 배치
  - `top`, `right`, `left`, `bottom`, `z-index` 속성이 아무런 영향을 주지 않음
- relative
  - 요소를 일반적인 문서흐름에 따라 배치
  - __자기 자신__을 기준으로 `top`, `right`, `left`, `bottom`의 값에 따라 오프셋 적용
  - 오프셋은 다른 요소에 영향 x
  - 페이지 레이아웃에서 요소가 차지하는 공간은 static과 동일
- absolute 
  - 요소를 일반적인 문서 흐름에서 제거
  - 가장 가까운 위치 지정 조상요소에 대해 상대적으로 배치
  - 조상중 위치 지정 요소가 없다면 초기 컨테이닝 블록을 기준
  - 페이지 레이아웃에 공간 배정 x
- fixed
  - 요소를 일반적인 문서 흐름에서 제거
  - 뷰포트의 초기 컨테이닝 블록을 기준으로 한 위치에 배치
  - 만약 요소의 조상 중 하나가 `transform`, `perspective`, `filter` 속성 중 어느 하나라도 none이 아니면 뷰포트 대신 그 조상을 컨테이닝 블록으로 삼음
  - 페이지 레이아웃에 공간 배정 x
- sticky
  - 요소를 일반적인 문서 흐름에 따라 배치
  - 테이블 관련 요소를포함, 가장 가까운 스크롤되는 조상과 가장 가까운 블록레벨을 기준으로 `top`, `right`, `bottom`, `left`의 값에 따라 오프셋 적용

- z-index

  > 위치 지정 요소(`position`)와, 그 자손 또는 하위 플렉스 아이템의 z축 순서를 지정
  >
  > 더 큰 `z-index`값을 가진 요소가 작은 값의 요소 위를 덮음

  

### 6. transition

> css 속성을 변경할 때 애니메이션 속도를 조절

> `transition: [transition-property] [transition-duration] [transition-timing-function] [transition-delay]`

- transition-property : css 속성 설정 전환 효과 적용

- transition-duration : 효과 적용시키기 까지 몇초걸릴지 지정

- transition-timing-function

  | 속성                       | 설명                                      |
  | -------------------------- | ----------------------------------------- |
  | `ease`                     | 느리게 시작 -> 빠르게 전환 -< 천천히 종료 |
  | `linear`                   | 처음부터 끝까지 같은속도로 전환           |
  | `ease-in`                  | 느린 시작으로 전환효과                    |
  | `ease-out`                 | 느린 끝으로 전환효과                      |
  | `ease-in-out`              | 느린 시작 + 느린끝                        |
  | `cubic-bezier(n, n, n, n)` | 3차 베지어함수에서 값을 정의              |

  

- transition-delay

  > 전환 효과에 대한 지연(초) 지정

  