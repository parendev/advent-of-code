(ns advent-of-code.year-2023.day-02.part1a
  (:require [advent-of-code.utils :as utils]
            [clojure.set :as set]
            [clojure.string :as s]))

(defn parse-game [line]
  (let [[game-str game-data] (s/split line #": ")]
    {:game-id (Integer/parseInt (re-find #"\d+" game-str))
     :color-quantities (->> (re-seq #"(\d+) ([a-zA-Z]+)" game-data)
                            (map (fn [[_ quantity color]]
                                   [(Integer/parseInt quantity) color]))
                            (reduce (fn [m [quantity color]]
                                      (if (or (not (contains? m color))
                                              (< (m color) quantity))
                                        (assoc m color quantity)
                                        m))
                                    {}))}))

(defn valid-game? [game]
  (and (set/subset? (keys (:color-quantities game))
                    #{"red" "blue" "green"})
       (every? (fn [[color quantity]]
                 (<= (get-in game [:color-quantities color] 0)
                     quantity))
               {"red" 12, "green" 13, "blue" 14})))

(defn -main []
  (let [input (utils/get-resource! "year_2023/day02/input.txt")
        output (->> input
                    s/split-lines
                    (map parse-game)
                    (filter valid-game?)
                    (map :game-id)
                    (apply +))]
    (println output)
    output))
