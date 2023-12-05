# advent-of-code

My solutions to Advent of Code challenges (https://adventofcode.com/).

## Additional Content

I have coded some of these solutions on livestreams on Twitch and YouTube.

You may be able to find VODs showing my development process and containing additional details about these solutions on my [YouTube VODs](https://youtube.com/@ParenDevStreams) channel.
Or try to catch a programming livestream on my [Twitch](https://www.twitch.tv/parendev) or [YouTube](https://www.youtube.com/@ParenDev) channel.

Feel free to join our [Community Discord Server](https://discord.gg/5ceguV2JEh) if you have questions or want to discuss these programming challenges!

## Dependencies

### Java

This project aims to be compatible with the latest LTS version of Java, currently [OpenJDK 21](https://jdk.java.net/21/).

### Leiningen

[Leiningen](https://leiningen.org/) is used as the project's build system.

Add the `lein` script to your terminal's PATH.
If setup correctly you should be able to run the command `lein --version` in your terminal and see what version of Leiningen you have installed.

## Running Code

### Start REPL

1. Navigate to the project root directory in your terminal.
2. Run `lein repl`
3. Wait until you see the prompt `advent-of-code.core=> `

You should now be able to evaluate Clojure code.
You can test this by evaluating `(+ 1 1)` which should return `2`.

### Running Solution To A Channel

1. Identify name of namespace containing the solution you want to run
    * Solutions are organized by year, then day, then solution number
    * Namespace name is found at the top of the file, the name of the namespace for year 2023, day 01, solution 01 is `advent-of-code.year-2023.day-01.solution01`
2. Load code from that namespace by requiring it
    * `(require 'advent-of-code.year-2023.day-01.solution01)`
3. Switch REPL to that namespace
    * `(in-ns 'advent-of-code.year-2023.day-01.solution01)`
4. Call the main function
    * `(-main)`

## License

Copyright © 2023 ParenDev
