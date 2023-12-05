(ns advent-of-code.year-2023.day-01.part2a
  (:require [advent-of-code.utils :as utils]
            [clojure.string :as s]))

(def re-digit #"\d|one|two|three|four|five|six|seven|eight|nine")
(def re-first-digit (re-pattern (str ".*?(" re-digit ").*")))
(def re-last-digit (re-pattern (str ".*(" re-digit ").*?")))

(def word->digit
  {"one" 1
   "two" 2
   "three" 3
   "four" 4
   "five" 5
   "six" 6
   "seven" 7
   "eight" 8
   "nine" 9})

(defn parse-digit-str [s]
  (if (contains? word->digit s)
    (word->digit s)
    (Integer/parseInt s)))

(defn get-first-digit [line]
  (->> line
       (re-matches re-first-digit)
       second
       parse-digit-str))

(defn get-last-digit [line]
  (->> line
       (re-matches re-last-digit)
       second
       parse-digit-str))

(defn -main []
  (let [input (utils/get-resource! "year_2023/day01/input.txt")
        output (->> input
                    s/split-lines
                    (map #(Integer/parseInt (str (get-first-digit %) (get-last-digit %))))
                    (apply +))]
    (println output)
    output))
