(ns advent-of-code.year-2023.day-01.part1a
  (:require [advent-of-code.utils :as utils]
            [clojure.string :as s]))

(defn -main []
  (let [input (utils/get-resource! "year_2023/day01/input.txt")
        output (->> input
                    s/split-lines
                    (map #(Integer/parseInt (str (re-find #"\d" %) (re-find #"\d(?=[^\d]*$)" %))))
                    (apply +))]
    (println output)
    output))
