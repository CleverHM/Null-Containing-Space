# Search

## Construction

![search_construction](../images/search_construction.png)

## File Definition

### :black_medium_square: src/views/search

| File 이름        | 기술                          |
| ---------------- | ----------------------------- |
| `SearchMain.vue` | 검색 페이지를 정의합니다.     |
| `TagResult.vue`  | Tag 결과 페이지를 정의합니다. |

### :black_medium_square: src/components/notice

| File 이름            | 기술                                          |
| -------------------- | --------------------------------------------- |
| `peopleItem.vue`     | 검색 결과 검색된 사람 component를 정의합니다. |
| `searchPostItem.vue` | 검색 결과 게시글 component를 정의합니다.      |
| `tagItem.vue`        | 검색 결과 tag component를 정의합니다.         |



## Code

#### `SearchMain.vue`

> Search의 **최상단 페이지**로 사용자들의 검색어를 받아 해당하는 사람이나 태그를 출력한다.

- `text` type의 `input`창에서 검색어를 입력받는다.
- 검색 결과를 axios 요청을 보낸다.
  - get방식: `/search/user/${검색어}/${닉네임}/${페이지}`
- 받아온 데이터를 `peopleItem.vue` 또는 `tagItem.vue`로 내려서 출력한다.
  - `peopleItem.vue`에서는 팔로우 or 프로필 페이지 이동이 가능하다.
  - `tagItem.vue`에서는 각 컴포넌트 별로 `TagResult.vue`로 이동할 수 있다.



#### `TagResult.vue`

> Search 결과로 나온 Tag를 클릭 시 해당 Tag에 관련된 게시글 목록 및 태그 팔로우 여부를 출력한다. 

- Tag 관련 게시글을 받아오기 위해 axios 요청을 보낸다.
  - get방식: `/post/hashall/${storage.getItem("NickName")}/${this.limit}`
- 받아온 데이터를 `searchPostItem.vue`로 내려서 출력한다.
  - 각 게시글 컴포넌트 클릭 시 해당 게시글의 상세 페이지로 이동한다.



## Reference

- stack overflow