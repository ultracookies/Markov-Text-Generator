# Markov-Text-Generator

## Description

This JavaFX application not only acts as a text-generator but also as a text-editor. There is also a built-in spell check mechanism. This application can "learn" the text it reads from any text file and generate text based from that. When the program learns from a text file, it creates a linked hashset of every word and record a linked list for each one. Each linked list records each occurrence of the word that comes after the recorded word. For example, in this sentence "I like Java because I think it is a great language!" the linked hashset will look like:

  "I" -> "like" -> "think"

  "like" -> "Java"

  "Java" -> "because"

  "because" -> "I"

  "think" -> "it"

  "it" -> "is"

  "is" -> "a"

  "a" -> "great"

  "great" -> "language!"

  "language!"

The program will then be able to generate text at the start of any given word from the text. For example, if the user starts off with the word "think," the text will look like this: "think it is a great language!" With more words and larger text, there can be a larger variety of text that will be generated.
