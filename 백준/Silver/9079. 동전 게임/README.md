# [Silver II] 동전 게임 - 9079 

### #비트마스크

[문제 링크](https://www.acmicpc.net/problem/9079) 

### 성능 요약

메모리: 14620 KB, 시간: 112 ms

### 분류

그래프 이론, 브루트포스 알고리즘, 그래프 탐색, 너비 우선 탐색, 비트마스킹

### 제출 일자

2025년 7월 3일 17:36:38

### 문제 설명

<p>상우는 재미있는 게임을 생각해냈다. 동전 9개를 아래 그림과 같이 3행 3열로 놓는다. H는 앞면, T는 뒷면을 의미한다.</p>

<pre>H T T
H T T
T H H</pre>

<p>게임의 목적은 이 동전의 모양을 모두 같은 면(H나 T)이 보이도록 하는 것이다. 단, 하나의 동전만을 뒤집을 수는 없고, 한 행의 모든 동전, 한 열의 모든 동전 또는 하나의 대각선 상의 모든 동전을 한 번에 뒤집어야 한다. 그런 식으로 세 개의 동전을 뒤집는 것을 '한 번의 연산'으로 센다. 상우는 이것을 최소 횟수의 연산으로 실행하고 싶어한다. 오랜 시간 생각한 끝에 위의 경우는 두 번의 연산으로 가능하다는 것을 알아냈고, 또, 이것이 최소인 것도 알아내었다.</p>

<pre>H T T   T T T   T T T
H T T → T T T → T T T
T H H   H H H   T T T</pre>

<p>또한, 모두 같은 면으로 만드는 것이 불가능한 모양이 있다는 것도 알아내었다. 다음이 그 예이다.</p>

<pre>T H H
H H H
H H H</pre>

<p>상우를 도울 수 있는 프로그램을 작성하시오.</p>

### 입력 

 <p>입력의 첫 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 10)가 주어진다. 각 테스트 케이스는 세 줄로 이루어지며, 한 줄에 세 개의 동전모양이 주어지는데, 각각의 동전 표시 사이에는 하나의 공백이 주어진다.</p>

### 출력 

 <p>각 테스트 케이스에 대해서, 모두 같은 면이 보이도록 만들기 위한 최소 연산 횟수를 출력하고, 불가능한 경우에는 -1을 출력한다.</p>

