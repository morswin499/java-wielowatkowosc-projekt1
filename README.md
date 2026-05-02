# Projekt 1: Wielowątkowość wysokopoziomowa
**Przedmiot:** Programowanie współbieżne w języku JAVA

## O projekcie
Celem projektu jest analiza porównawcza dwóch mechanizmów zarządzania wielowątkowością w języku Java. Aplikacja symuluje równoległe pobieranie wielu plików za pomocą niezależnie rosnących pasków postępu (`JProgressBar`).

Projekt demonstruje różnice wydajnościowe i architektoniczne między:
1. **Podejściem klasycznym:** Wykorzystanie interfejsu `Runnable` oraz klasy `Thread`.
2. **Podejściem wysokopoziomowym:** Wykorzystanie puli wątków za pomocą interfejsu `ExecutorService`.

## Instrukcja uruchomienia i testowania

Główną klasą uruchomieniową aplikacji jest `ProgressRace.java`. 

Program został zaprojektowany tak, aby w łatwy sposób demonstrować różnice między dwoma mechanizmami współbieżności. Wewnątrz metody `main` znajduje się przełącznik architektoniczny (flaga):
```java
boolean USE_THREAD_POOL = false;
```
Aby przetestować konkretne podejście, przed kompilacją zmień wartość tej zmiennej:
- false – Uruchamia symulację w trybie klasycznym. System operacyjny powołuje do życia 10 wątków (Thread), które ruszają natychmiast, walcząc o czas procesora.

- true – Uruchamia symulację z użyciem puli wątków (ExecutorService). Pula dysponuje jedynie 3 wątkami roboczymi dla 10 zadań. Na ekranie widać mechanizm kolejkowania (paski ładują się partiami po trzy), co chroni procesor przed zbytnim obciążeniem.
## Lista zadań (TODO)

### Faza 1: Architektura i GUI
- [x] Stworzenie interfejsu graficznego (10 pasków `JProgressBar`, układ `GridLayout`).
- [x] Implementacja natywnego wyglądu (Look & Feel) dla systemów Linux/Windows.
- [x] Symulacja obciążenia sprzętowego (losowe wartości pobierania i usypiania).
- [x] Zabezpieczenie aktualizacji GUI za pomocą `SwingUtilities.invokeLater`.

### Faza 2: Implementacja współbieżności
- [x] Implementacja klasycznego podejścia z użyciem klasy `Thread` (10 wątków).
- [x] Zapewnienie oczekiwania na zakończenie zadań (metoda `join()`).
- [x] Wprowadzenie wysokopoziomowej puli wątków za pomocą `ExecutorService`.
- [x] Obsługa kolejkowania zadań (3 wątki robocze dla 10 zadań).
- [x] Wdrożenie poprawnego zamykania puli (`shutdown` + `awaitTermination`).
- [x] Zaimplementowanie pomiaru całkowitego czasu wykonania (`System.currentTimeMillis()`).

### Faza 3: Profilowanie i Analiza
- [ ] Wykonanie pomiarów z użyciem profilera (np. VisualVM lub narzędzia `jcmd`).
- [ ] Wygenerowanie wykresów zużycia pamięci RAM i obciążenia CPU dla podejścia klasycznego.
- [ ] Wygenerowanie wykresów zużycia pamięci i CPU dla puli wątków.
- [ ] Zrzut zrzutów ekranu osi czasu pokazujących stany wątków (Running, Sleeping, Waiting).

### Faza 4: Dokumentacja
- [ ] Opracowanie wniosków porównujących oba podejścia.
- [ ] Wyjaśnienie zjawiska narzutu na tworzenie wątków oraz zalet kolejkowania zadań.
- [ ] Złożenie końcowego sprawozdania.

## Autorzy
- Mateusz Moskwin
- Beniamin Raczyński
- Monika Szczerba
- Kacper Marciniak
- Maciej Wojnowski
