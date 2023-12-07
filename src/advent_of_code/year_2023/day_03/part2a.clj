(ns advent-of-code.year-2023.day-03.part2a
  (:require [advent-of-code.year-2023.day-03.part1a :as part1]
            [advent-of-code.utils :as utils]))

(defn -main []
  (let [input (utils/get-resource! "year_2023/day03/input.txt")
        {:keys [parts part-nums]} (part1/parse-schematic input)
        output (->> parts
                    ; Only consider gear-looking parts
                    (filter (comp #{"*"} :value))
                    ; Map gear-looking parts to neighboring part number values
                    (map (fn [{:keys [coord]}]
                           (->> part-nums
                                ; Filter to only neighboring part numbers
                                (filter #((part1/part-num->neighbor-coords %) coord))
                                ; Get integer value of part number
                                (map (comp #(Integer/parseInt %) :value)))))
                    ; Not a gear unless it has exactly 2 part numbers
                    (filter (comp #{2} count))
                    ; Gear ratio is two part numbers multiplied
                    (map (partial apply *))
                    ; Sum all gear ratios
                    (apply +))]
    (println output)
    output))

