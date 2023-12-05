(ns advent-of-code.year-2023.day-02.part2a
  (:require [advent-of-code.utils :as utils]
            [advent-of-code.year-2023.day-02.part1a :as part1]
            [clojure.string :as s]))

(defn game->power [game]
  (->> ["blue" "red" "green"]
       (map #(get-in game [:color-quantities %] 0))
       (apply *)))

(defn -main []
  (let [input (utils/get-resource! "year_2023/day02/input.txt")
        output (->> input
                    s/split-lines
                    (map (comp game->power part1/parse-game))
                    (apply +))]
    (println output)
    output))
