# [Silver II] 시간표 짜기 - 14569 

### #비트마스크

[문제 링크](https://www.acmicpc.net/problem/14569) 

### 성능 요약

메모리: 19764 KB, 시간: 208 ms

### 분류

구현, 브루트포스 알고리즘, 집합과 맵, 비트마스킹

### 제출 일자

2025년 7월 3일 15:47:38

### 문제 설명

<p>연세대학교 수강신청 기간이 시작되었다. 많은 친구들은 비어 있는 시간에 어떤 과목을 추가로 신청할 수 있는지를 궁금해 한다.</p>

<p>이 친구들이 비어 있는 시간에 추가로 신청할 수 있는 과목의 후보 개수를 구해보자.</p>

<p>후보 개수를 세는 것이므로 현재 내 시간표에서 신청할 수 있는 과목끼리 시간이 겹치더라도 모두 세어야 한다.</p>

<p>즉, 월요일 1, 2, 3, 4, 5교시 시간이 비어 있고 한 과목의 시간이 월요일 1, 2, 3, 4교시이고 나머지 한 과목의 시간이 월요일 2, 3, 4, 5교시라면 2과목 모두 후보가 될 수 있다.</p>

### 입력 

 <p>연세대학교의 총 과목의 수 N (3 ≤ N ≤ 1000)이 주어진다.</p>

<p>N줄에 걸쳐서 각 과목의 수업시간의 수 k (4 ≤ k ≤ 50)가 주어지고 그 옆에 k개의 숫자 t<sub>i</sub> (1 ≤ t<sub>i</sub> ≤ 50)가 공백으로 구분되어 주어진다.</p>

<p>t<sub>i</sub>는 이 과목의 수업이 진행되는 교시를 의미하며 1 ~ 50의 값을 가진다.</p>

<p>(월요일 1~10교시: 1~10, 화요일 1~10교시: 11~20, …)</p>

<p>다음 줄에 학생수 M (1 ≤ M ≤ 10000) 이 주어진다.</p>

<p>M줄에 걸쳐서 각 학생들의 <strong>비어 있는</strong> 교시 개수 p (0 ≤ p ≤ 50)가 주어지고 그 옆에 p개의 숫자 q<sub>i </sub>(1 ≤ q<sub>i</sub> ≤ 50)가 공백으로 구분되어 주어진다.</p>

<p>Ex) 알고리즘의 수업시간이 화요일 2, 3교시, 수요일 4, 5교시라면 다음과 같이 입력이 주어진다.</p>

<pre>4 12 13 24 25</pre>

### 출력 

 <p>M줄에 걸쳐서 각 학생들의 들을 수 있는 과목 개수를 출력한다.</p>

