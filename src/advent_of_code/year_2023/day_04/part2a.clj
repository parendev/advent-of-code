(ns advent-of-code.year-2023.day-04.part2a
  (:require [advent-of-code.utils :as utils]
            [advent-of-code.year-2023.day-04.part1a :as part1]
            [clojure.set :as set]
            [clojure.string :as s]))

(defn cards->score [cards]
  (loop [[{:keys [winning-nums my-nums] :as card} & remaining-cards] cards
         outstanding-copies [] ;tuples of [remaining-turns, num-copies]
         total-cards 0]
    (if-not card
      total-cards
      (let [card-wins (count (set/intersection winning-nums my-nums))
            copies-of-this-card (->> outstanding-copies
                                     (map second)
                                     (apply +)
                                     inc)]
        (recur remaining-cards
               (->> outstanding-copies
                    (map (fn [[remaining-turns num-copies]]
                           [(dec remaining-turns) num-copies]))
                    (cons [card-wins copies-of-this-card])
                    (filter (comp pos? first)))
               (+ total-cards copies-of-this-card))))))

(defn -main []
  (let [input (utils/get-resource! "year_2023/day04/input.txt")
        output (->> input
                    s/split-lines
                    (map part1/parse-card)
                    cards->score)]
    (println output)
    output))
