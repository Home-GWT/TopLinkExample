
#JAVA 8
* [Презентация java 8 (biplane)](Презентация%20java%208%20(biplane).pdf) **(** [Презентация java 8 (biplane)](https://github.com/Home-Java8/Java8/blob/master/Презентация%20java%208%20(biplane).pdf) **)**
* [Учебник Java 8](https://urvanov.ru/2016/03/23/учебник-java-8)
* `Дефолтные методы` [Учебник Java 8 — ещё раз о перегрузке методов](https://urvanov.ru/2016/04/21/java-8-ещё-раз-о-перегрузке-методов)
* `Лямбда`
* `Стримы` [Учебник Java 8 — потоки ввода-вывода](https://urvanov.ru/2016/05/13/java-8-потоки-вводавывода) **(** [Полное руководство по Java 8 Stream](http://javadevblog.com/polnoe-rukovodstvo-po-java-8-stream.html) **|** [Немного о Stream API](https://habrahabr.ru/post/302628) **)**
* try-with-resources [Учебник Java 8 — исключения](https://urvanov.ru/2016/05/02/java-8-исключения/) **(** [Exceptions and Assertions](http://eherrera.net/ocpj8-notes/06-exceptions-and-assertions) **|** [functional exception handling for Java 8](https://github.com/aol/cyclops/wiki/try-:-functional-exception-handling-for-java-8) **)**

###try-with-resources

- Метод **close()** в интерфейсе *java.lang.AutoCloseable* объявляет в клаузе throws исключение ***Exception***.
- А метод **close()** в интерфейсе *java.io.Closeable* объявляет ***IOException***, что позволяет наследникам *AutoCloseable* определять свои специфичные исключения.

> Поток `BufferedReader` реализует интерфейс `AutoCloseable` (`java.lang.AutoCloseable`) и теперь в конструкции try-with-resources нет необходимости закрывать его.


```javascript
try {
    InputStream is;
     // do something
}
finally {
    is.close();
}

/**
 * 'try-with-resources' vs AutoCloseable
 */
class Example {
    void foo() {
        try (FileReader reader1 = new FileReader("file1"); FileReader reader2 = new FileReader("file2")) {
            // do something
        }
    }
}

/**
 * 'try-with-resources' vs Closeable
 */
public class CloseableLock implements Closeable {
    private final Lock lock;

    private CloseableLock(Lock l) {
        lock = l;
    }

    public void close() {
        lock.unlock();
    }

    public static CloseableLock lock(Lock l) {
        l.lock();
        return new CloseableLock(l);
    }
}

try(CloseableLock l = CloseableLock.lock(lock)) { // acquire the lock
    // do something
} // release the lock
```

> Дело в том, что *Stream* (Stream API) в Java 8 - это другие потоки которые *монады* (не из Java I/O), и которые не наследуются от интерфейса *Closeable*, *AutoCloseable* и поэтому НЕподдерживают реализацию метода **close()**


###Stream в Java 8

**Stream** (`звучит очень похоже на InputStream или OutputStream из Java I/O, но в Java 8 это — Stream API: стримы/потоки`) — в Java 8, это *потоки*, они же являются [Монадами](https://ru.wikipedia.org/wiki/Монада_(программирование))
> В функциональном программировании монада является структурой, которая представляет вычисления в виде последовательности шагов.

*Поток* — представляет собой последовательность элементов и поддерживает различные виды операций для выполнения вычислений.

######Операции над потоком бывают:
- ***Промежуточные*** — все промежуточные операции возвращают поток, так что мы можем объединять несколько промежуточных операций без использования точки с запятой.

> Например: `filter`, `map`, `sorted`

- ***Терминальные*** — терминальные операции возвращают *void* или непотоковый результат.

> Например: `forEach`

Большинство операций потока принимают в качестве параметров какие-то лямбда-выражения, ...
***Неинтерферирующуя функция*** — не изменяет основной источник данных потока

> Например: `List` путем добавления или удаления элементов из коллекции.

***Лишенная состояния функция*** — выполнение операции является детерминированным

> Например: не зависит от изменяемой переменной или состояния из внешней среды во время выполнения.


```java
List<String> mList = Arrays.asList("aa1","cc2", "cc1", "aa2", "bb1");

mList
    .stream()
    .filter(s -> s.startsWith("a"))
    .map(String::toUpperCase)
    .sorted()
    .forEach(System.out::println);
//Результат выполнения:
// AA1
// AA2
```


######Различные виды потоков (Streams):
- Последовательные потоки (`.stream()`)
- Параллельные потоки (`.parallelStream()`)


######Порядок обработки:

В примере первая строка «dd2» полностью проходит фильтр forEach, потом обрабатывается вторая строка «aa2» и так далее:
```java
Stream.of("dd2", "aa2", "bb1", "bb3", "cc4")
    .filter(s -> {
        System.out.println("Фильтр: " + s);
        return true;
    })
    .forEach(s -> System.out.println("Печать с использованием forEach: " + s));
```
Выполнив этот код, на консоль выведется следующее:
```
Фильтр:  dd2
Печать с использованием forEach: dd2
Фильтр: aa2
Печать с использованием forEach: aa2
Фильтр: bb1
Печать с использованием forEach: bb1
Фильтр: bb3
Печать с использованием forEach: bb3
Фильтр: cc4
Печать с использованием forEach: cc4
```

Пример состоит из 2-ух промежуточных операций *map* и *filter*, а также выполнение терминала *forEach* (*map* и *filter* вызываются 5-раз для каждой строки в базовой коллекции, тогда как *forEach* вызывается только 1-раз):
```java
Stream.of("dd2", "aa2", "bb1", "bb3", "cc4")
    .map(s -> {
        System.out.println("map: " + s);
        return s.toUpperCase();
    })
    .filter(s -> {
        System.out.println("filter: " + s);
        return s.startsWith("A");
    })
    .forEach(s -> System.out.println("forEach: " + s));
// Результат выполнения
// map:     dd2
// filter:  DD2
// map:     aa2
// filter:  AA2
// forEach: AA2
// map:     bb1
// filter:  BB1
// map:     bb3
// filter:  BB3
// map:     cc
// filter:  CC
```

Можно сильно уменьшить фактическое количество выполнений, если мы изменим порядок операций (теперь *map* вызывается только 1-раз и будет выполняться быстрее для большого количества входных элементов)
```java
Stream.of("dd2", "aa2", "bb1", "bb3", "cc4")
    .filter(s -> {
        System.out.println("filter: " + s);
        return s.startsWith("a");
    })
    .map(s -> {
        System.out.println("map: " + s);
        return s.toUpperCase();
    })
    .forEach(s -> System.out.println("forEach: " + s));

// filter:  dd2
// filter:  aa2
// map:     aa2
// forEach: AA2
// filter:  bb1
// filter:  bb3
// filter:  cc
```

Сортировка является особым видом промежуточных операций (это так называемые *операции состояния*). Таким образом, sorted вызывается 8-раз для нескольких комбинаций на каждом элементе:
```java
Stream.of("dd2", "aa2", "bb1", "bb3", "cc4")
    .sorted((s1, s2) -> {
        System.out.printf("sort: %s; %s\n", s1, s2);
        return s1.compareTo(s2);
    })
    .filter(s -> {
        System.out.println("filter: " + s);
        return s.startsWith("a");
    })
    .map(s -> {
        System.out.println("map: " + s);
        return s.toUpperCase();
    })
    .forEach(s -> System.out.println("forEach: " + s));
```
Выполнение этого примера приводит следующий вывод консоли:
```
sort:    aa2; dd2
sort:    bb1; aa2
sort:    bb1; dd2
sort:    bb1; aa2
sort:    bb3; bb1
sort:    bb3; dd2
sort:    cc4; bb3
sort:    cc4; dd2
filter:  aa2
map:     aa2
forEach: AA2
filter:  bb1
filter:  bb3
filter:  cc4
filter:  dd2
```

Можно оптимизировать производительность (в этом примере sorted никогда не вызывали, потому что *filter* уменьшает входную коллекцию до 1-го элемента):
```java
Stream.of("dd2", "aa2", "bb1", "bb3", "cc4")
    .filter(s -> {
        System.out.println("filter: " + s);
        return s.startsWith("a");
    })
    .sorted((s1, s2) -> {
        System.out.printf("sort: %s; %s\n", s1, s2);
        return s1.compareTo(s2);
    })
    .map(s -> {
        System.out.println("map: " + s);
        return s.toUpperCase();
    })
    .forEach(s -> System.out.println("forEach: " + s));

// filter:  dd2
// filter:  aa2
// filter:  bb1
// filter:  bb3
// filter:  cc4
// map:     aa2
// forEach: AA2
```


######Параллельные потоки

> Параллельные потоки используют общий `ForkJoinPool` доступный через статический `ForkJoinPool.commonPool()` метод.

Размер основного пула потоков использует в зависимости от количества доступных физических ядер процессора:
```java
ForkJoinPool commonPool = ForkJoinPool.commonPool();
System.out.println(commonPool.getParallelism());    // 3
```

Давайте расширим например с помощью дополнительной операции потока — *sort* (на самом деле *sort* на параллельном потоке использует новый Java 8 метод *Arrays.parallelSort()* под капотом):
```java
Arrays.asList("a1", "a2", "b1", "c2", "c1")
    .parallelStream()
    .filter(s -> {
        System.out.format("filter: %s [%s]\n",
            s, Thread.currentThread().getName());
        return true;
    })
    .map(s -> {
        System.out.format("map: %s [%s]\n",
            s, Thread.currentThread().getName());
        return s.toUpperCase();
    })
    .sorted((s1, s2) -> {
        System.out.format("sort: %s <> %s [%s]\n",
            s1, s2, Thread.currentThread().getName());
        return s1.compareTo(s2);
    })
    .forEach(s -> System.out.format("forEach: %s [%s]\n",
        s, Thread.currentThread().getName()));
```
Результат может быть странным на первый взгляд:
```
filter:  c2 [ForkJoinPool.commonPool-worker-3]
filter:  c1 [ForkJoinPool.commonPool-worker-2]
map:     c1 [ForkJoinPool.commonPool-worker-2]
filter:  a2 [ForkJoinPool.commonPool-worker-1]
map:     a2 [ForkJoinPool.commonPool-worker-1]
filter:  b1 [main]
map:     b1 [main]
filter:  a1 [ForkJoinPool.commonPool-worker-2]
map:     a1 [ForkJoinPool.commonPool-worker-2]
map:     c2 [ForkJoinPool.commonPool-worker-3]
sort:    A2 <> A1 [main]
sort:    B1 <> A2 [main]
sort:    C2 <> B1 [main]
sort:    C1 <> C2 [main]
sort:    C1 <> B1 [main]
sort:    C1 <> C2 [main]
forEach: A1 [ForkJoinPool.commonPool-worker-1]
forEach: C2 [ForkJoinPool.commonPool-worker-3]
forEach: B1 [main]
forEach: A2 [ForkJoinPool.commonPool-worker-2]
forEach: C1 [ForkJoinPool.commonPool-worker-1]
```



