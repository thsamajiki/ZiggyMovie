# ZiggyMovie
영화 검색 앱



## 개요
영화 정보 검색 서비스 <br>


## 프로젝트 기간
2022\. 09 ~ 2022\. 11


## 기여도
- 기획, 개발, 디자인 모두 했습니다.
- (현재 디자인 수정중입니다.)


## 사용 프로그램 및 언어
- 사용 프로그램 : Android Studio, GitHub
- 사용 언어 : Kotlin


## 앱의 버전
- minSdkVersion : 27
- targetSdkVersion : 34


## 주요 기능
- 영화를 검색할 수 있다.
- 원하는 영화에 관한 자세한 정보를 확인할 수 있다.


## 사용된 기술
- Retrofit
- RxJava
- Coroutine
- MVVM 디자인 패턴
- DataBinding
- AAC ViewModel
- Hilt
- GridLayout
- Paging
- RecyclerView


## 사용된 라이브러리
- Retrofit (com.squareup.retrofit2:retrofit:2.5.0)
- RxJava2 (io.reactivex.rxjava2:rxjava:2.2.9)
- Paging (androidx.paging:paging-rxjava2:3.1.1)
- Hilt (com.google.dagger:hilt-android:2.44)
- 머티리얼 디자인 (com.google.android.material:material:1.6.1)
- 사진 첨부 (com.github.bumptech.glide:glide:4.11.0)


## 문제 및 해결 과정
- 네이버 영화 API에서 제공하는 영화 데이터 속성 중에서 영화 제목, 부제, 포스터 이미지, 감독, 배우들의 이름이 데이터바인딩을 통해 화면에 표시되지 않는 문제가 있었습니다.
그래서 @BindingAdapter를 통해 속성에 대한 setter를 직접 커스터마이징하는 BindingAdapter 메소드를 직접 구현해서 문제를 해결했습니다.<br>
포스터 이미지는 Glide가 있는 메소드로 커스터마이징했고, 영화 제목/부제는 HTML을 TextView로 표현하고 영화 감독/배우들은 joinToString으로 구분하여 처리했습니다.
- 처음에 EditText와 MaterialButton을 이용하여 원하는 영화 정보를 검색하도록 구현했지만, 이보다는 검색어 입력에 따른 예상 결과를 화면에 표시하면 좋겠다고 생각했습니다.
이는 AAC ViewModel의 LiveData와 Observer의 장점을 좀더 극대화하면 좋겠다는 생각에서 RxJava의 debounce 기능을 이용하여 해결했습니다.



## 개발 후 느낀 점
- Retrofit을 통해 네이버 영화 검색 API를 가져왔지만,
각 영화정보에 제공되는 포스터의 해상도가 별로 좋지 않은 것과 '더보기'를 통해 연결되는 링크가 네이버 영화의 상세정보 페이지가 아닌 점은 아쉬운 점입니다.<br>
2021년만해도 상세 페이지로 링크가 연결되었는데, 네이버 측에서 모바일 페이지 형식으로 제공하지 않은 것으로 보입니다.<br>
- 처음에 배우기 어려웠던 RxJava를 성공적으로 구현했다는 점이 뿌듯했고 리액티브 프로그래밍에 대해 좀 더 공부하고 싶은 생각이 들었습니다.
하지만 RxJava에 있는 수많은 기능들 중에 일부만 이용하여 검색 기능을 구현한 것은 아쉬웠습니다.<br>
추후에 현업에서 주로 쓰이는 Coroutine을 이용하여 앱에 기능을 추가, 수정할 생각입니다.



## 스크린샷
<img src="/images/intro_app.png" width="310px" height="640px" title="intro_app" alt="intro_app"></img>
<img src="/images/search_movie.png" width="310px" height="640px" title="search_movieo" alt="search_movie"></img>
<img src="/images/movie_list.png" width="310px" height="640px" title="movie_list" alt="movie_list"></img>
<img src="/images/movie_detail.png" width="310px" height="640px" title="movie_detail" alt="movie_detail"></img>
<img src="/images/movie_naver_detail.png" width="310px" height="640px" title="movie_naver_detail" alt="movie_naver_detail"></img>



## 시연 영상
<img src="/images/ziggymovie_시연영상.gif" width="320px" height="640px" title="test_video" alt="Test_video"></img>
