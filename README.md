# WordQuiz

A small Java vocabulary trainer from university coursework. It models words and a dictionary, supports multiple translations and dictionary inversion, and quizzes the user through a Swedish console interface.

This repository preserves the original exercise and is not actively maintained.

## Structure

- `Word.java` defines word equality and hashing.
- `Dictionary.java` stores translations and reads or writes `term:translation` pairs.
- `WordQuiz.java` provides the menu and quiz loop.

## Build and setup limitations

With a Java Development Kit (JDK) installed, the expected compilation command is:

```sh
mkdir -p out
javac -encoding UTF-8 -d out *.java
```

The entry point is `UPG4.WordQuiz`, but the application is **not ready to run from a fresh checkout**. It reads and writes an `ordlista.txt` file at a hardcoded path from the original Eclipse workspace. That data file is not included. Before running, replace the paths in `WordQuiz.java` with an appropriate local path and supply a dictionary, for example:

```text
hej:hello
hund:dog
```

After that setup and recompilation, the launch command is `java -cp out UPG4.WordQuiz`.

## Known limitations

Input and dictionary-file validation are limited. A word with several valid translations can produce both correct and incorrect feedback for one answer, because each translation is checked independently. The quiz restarts recursively and has no explicit exit option. There is no automated test suite.
