(ns advent-of-code.year-2023.day-04.part1a
  (:require [advent-of-code.regex :as r]
            [advent-of-code.utils :as utils]
            [clojure.set :as set]
            [clojure.string :as s]))

(defn parse-card-nums [s]
  (->> (s/split s #"\s+")
       (filter seq)
       (map #(Integer/parseInt %))
       set))

(defn parse-card [s]
  (let [{[card winning-nums-str my-nums-str] :groups} (r/re-matches-details #"Card\s+(\d+):([\d ]+)\|([\d ]+)" s)]
    {:card (Integer/parseInt card)
     :winning-nums (parse-card-nums winning-nums-str)
     :my-nums (parse-card-nums my-nums-str)}))

(defn card->points [{:keys [winning-nums my-nums]}]
  (let [winning-num-count (count (set/intersection my-nums winning-nums))]
    (int (Math/pow 2 (dec winning-num-count)))))

(defn -main []
  (let [input (utils/get-resource! "year_2023/day04/input.txt")
        output (->> input
                    s/split-lines
                    (map (comp card->points parse-card))
                    (apply +))]
    (println output)
    output))
