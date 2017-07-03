## Butter Knife

- 안드로이드 라이브러리
(Activity, View의 findViewById, onClickListener 등을 편하게 사용)

- 라이브러리 사용법
> app:Gradle에 추가
```java
compile 'com.jakewharton:butterknife:8.+'
annotationProcessor 'com.jakewharton:butterknife-compiler:8.6.0'
```


- findViewById 사용 예시
> 사용전

```java
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    @Override
     protected void onCreate(Bundle savedInstanceState) {  
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
         btn1 = (Button)findViewById(R.id.btn1);
         btn2 = (Button)findViewById(R.id.btn2);
         btn3 = (Button)findViewById(R.id.btn3);
         btn4 = (Button)findViewById(R.id.btn4);
}
```
> 사용후
```java
public class MainActivity extends AppCompatActivity {
        // 타입 상관없음
        @BindView(R.id.recyclerView) RecyclerView recyclerView;
        @BindView(R.id.btn1) Button btn1;
        @BindView(R.id.btn2) Button btn2;
        @BindView(R.id.btn3) Button btn3;
        @BindView(R.id.btn4) Button btn4;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);  // 사용할 곳에 bind 시키기
  }
}

```

- onClickListener 사용

```java
@OnClick(R.id.btnPost) void goWrite(){  // 한줄로 onClickListener 구현 가능
    Toast.makeText(this, "Yoyo", Toast.LENGTH_SHORT).show();
}
```
> @OnClick({R.id.btnPost, R.id.btnPost2, R.id.btnPost3...})
-> 여러개 가능


## Annotation
- 빠른 개발과 유지 보수를 위한 오픈 소스 프레임 워크
- @를 붙여서 사용
- 라이브러리 사용법
> app:Gradle에 추가
```java
def AAVersion = "4.+"   // 상단에 추가

annotationProcessor "org.androidannotations:androidannotations:$AAVersion"
compile "org.androidannotations:androidannotations-api:$AAVersion"
```
> Manifest 수정
```java
<activity android:name=".MainActivity_">   // _써줘야 함
```

- 간결한 코드화 예시

> 사용전
```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      public void goWrite(){
          Intent intent = new Intent(this, WriteActivity.class);
          startActivity(intent);
      }
   }
}
```


> 사용후
```java
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public void goWrite(){
        Intent intent = new Intent(this, WriteActivity.class);
        startActivity(intent);
    }
}
```
![enter image description here](http://www.nextree.co.kr/content/images/2016/09/eykim-20140205-annotation-01.jpg)

## OK HTTP
- HttpUrlConnection(http 프로토콜로 통신을 하기위한 API)을 쓰기 쉽게 해 줌
- Thread가 없으면 안돌아감

> App: Gradle에 추가
```java
compile 'com.squareup.okhttp3:okhttp:3.8.1'
```
> to use
```java
OkHttpClient client = new OkHttpClient();

// 요청 정보를 담고 있는 객체
        Request request = new Request.Builder()
                .url(url)
                .build();

        // 응답 정보를 담고 있는 객체
        Response response = null;
                  // 서버로 요청
        response = client.newCall(request).execute();
        ResponseBody resBody = response.body();  // body 실제 우리가 봐야하는 내용들(html)을
        return resBody.string();   // String으로 받겠다는 의미
```


## Retrofit
- HttpUrlConnection(http 프로토콜로 통신을 하기위한 API)을 쓰기 쉽게 해 줌
- RestAPI를 쓰기 쉽게 해 줌
- 내장 Thread가 있음
> App: Gradle에 추가
```java
compile 'com.squareup.retrofit2:retrofit:2.3.0'
compile 'com.squareup.retrofit2:converter-gson' // gson으로 변환
```
> to use
```java
// retrofit정의
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://172.30.1.15:8080/")   // 도메인 주소 끝에 /꼭 써줘야 함!!!!!!!!!!!!!!!
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                // 실제 서비스 인터페이스 생성
                BbsService service = retrofit.create(BbsService.class);
                // 서비스 호출
                Call<Result> call = service.createBbs(bbs);
```

